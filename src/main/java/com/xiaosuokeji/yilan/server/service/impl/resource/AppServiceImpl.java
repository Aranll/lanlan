package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.AppConsts;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.dao.resource.AppDao;
import com.xiaosuokeji.yilan.server.model.resource.App;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.AppService;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 软件ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class AppServiceImpl implements AppService {
    
    @Autowired
    private AppDao appDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(App app) throws BusinessException {
        App existent = new App();
        existent.setName(app.getName());
        if (appDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(AppConsts.APP_EXIST);
        }
        appDao.save(app);
    }

    @Override
    @Transactional
    public void saveMulti(App app) throws BusinessException {
        if (app.getApps().size() > 0) {
            for (int i=0; i<app.getApps().size(); ++i) {
                App item = app.getApps().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<App>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, App.Save.class);
                for (ConstraintViolation<App> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<app.getApps().size(); ++j) {
                    if (i != j && item.getName().equals(app.getApps().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                App existent = new App();
                existent.setName(item.getName());
                if (appDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，软件已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                appDao.save(item);
            }
        }
    }

    @Override
    public void remove(App app) {
        appDao.remove(app);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        App app = new App();
        app.setCategory(category);
        if (appDao.count(app) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(App app) throws BusinessException {
        if (app.getName() != null) {
            App existent = new App();
            existent.setName(app.getName());
            List<App> existents = appDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(app.getId());
                if (!isSelf) {
                    throw new BusinessException(AppConsts.APP_EXIST);
                }
            }
        }
        appDao.update(app);
    }

    @Override
    public App get(App app) throws BusinessException {
        App existent = appDao.get(app);
        if (existent == null) {
            throw new BusinessException(AppConsts.APP_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<App> listAndCount(App app) {
        Category category = new Category();
        category.setType(CategoryConsts.APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, app.getCategory());
        app.setCategories(nodes);
        PageModel<App> result = PageModel.build(appDao.list(app), appDao.count(app));
        for (App item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<App> listHot() {
        App app = new App();
        app.setHot(1);
        return appDao.listCombo(app);
    }

    @Override
    public List<Category> listRecommend() {
        App app = new App();
        app.setRecommend(1);
        List<App> list = appDao.listCombo(app);
        Category category = new Category();
        category.setType(CategoryConsts.APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (App item : list) {
            Long categoryId = item.getCategory().getId();
            if (map.get(categoryId) == null) {
                Category group = new Category();
                group.setId(categoryId);
                group.setName(tree.get(categoryId).getName());
                group.addResource(item);
                map.put(categoryId, group);
            }
            else {
                map.get(categoryId).addResource(item);
            }
        }
        return new ArrayList<Category>(map.values());
    }

    @Override
    public List<Category> listAll() {
        List<App> list = appDao.listCombo(new App());
        Category category = new Category();
        category.setType(CategoryConsts.APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (App item : list) {
            tree.get(item.getCategory().getId()).addResource(item);
        }
        categoryService.clearBlankNode(tree);
        List<Category> result = new ArrayList<>();
        for (Map.Entry<Long, Category> item : tree.entrySet()) {
            Category temp = item.getValue();
            if (temp.getParent().getId().equals(0L)) {
                result.add(temp);
            }
            temp.setParent(null);
        }
        return result;
    }

    @Override
    public List<App> listCombo(App app) {
        Category category = new Category();
        category.setType(CategoryConsts.APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, app.getCategory());
        app.setCategories(nodes);
        return appDao.listCombo(app);
    }
}
