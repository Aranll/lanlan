package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.MiniAppConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.dao.resource.MiniAppDao;
import com.xiaosuokeji.yilan.server.model.resource.Book;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.MiniApp;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.MiniAppService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 小程序ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class MiniAppServiceImpl implements MiniAppService {
    
    @Autowired
    private MiniAppDao miniAppDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(MiniApp miniApp) throws BusinessException {
        MiniApp existent = new MiniApp();
        existent.setName(miniApp.getName());
        if (miniAppDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(MiniAppConsts.MINIAPP_EXIST);
        }
        miniAppDao.save(miniApp);
    }

    @Override
    @Transactional
    public void saveMulti(MiniApp miniMiniApp) throws BusinessException {
        if (miniMiniApp.getMiniApps().size() > 0) {
            for (int i=0; i<miniMiniApp.getMiniApps().size(); ++i) {
                MiniApp item = miniMiniApp.getMiniApps().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<MiniApp>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, MiniApp.Save.class);
                for (ConstraintViolation<MiniApp> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<miniMiniApp.getMiniApps().size(); ++j) {
                    if (i != j && item.getName().equals(miniMiniApp.getMiniApps().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                MiniApp existent = new MiniApp();
                existent.setName(item.getName());
                if (miniAppDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，小程序已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                miniAppDao.save(item);
            }
        }
    }

    @Override
    public void remove(MiniApp miniApp) {
        miniAppDao.remove(miniApp);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        MiniApp miniApp = new MiniApp();
        miniApp.setCategory(category);
        if (miniAppDao.count(miniApp) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(MiniApp miniApp) throws BusinessException {
        if (miniApp.getName() != null) {
            MiniApp existent = new MiniApp();
            existent.setName(miniApp.getName());
            List<MiniApp> existents = miniAppDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(miniApp.getId());
                if (!isSelf) {
                    throw new BusinessException(MiniAppConsts.MINIAPP_EXIST);
                }
            }
        }
        miniAppDao.update(miniApp);
    }

    @Override
    public MiniApp get(MiniApp miniApp) throws BusinessException {
        MiniApp existent = miniAppDao.get(miniApp);
        if (existent == null) {
            throw new BusinessException(MiniAppConsts.MINIAPP_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<MiniApp> listAndCount(MiniApp miniApp) {
        Category category = new Category();
        category.setType(CategoryConsts.MINI_APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, miniApp.getCategory());
        miniApp.setCategories(nodes);
        PageModel<MiniApp> result = PageModel.build(miniAppDao.list(miniApp), miniAppDao.count(miniApp));
        for (MiniApp item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<MiniApp> listHot() {
        MiniApp miniApp = new MiniApp();
        miniApp.setHot(1);
        return miniAppDao.listCombo(miniApp);
    }

    @Override
    public List<Category> listRecommend() {
        MiniApp miniApp = new MiniApp();
        miniApp.setRecommend(1);
        List<MiniApp> list = miniAppDao.listCombo(miniApp);
        Category category = new Category();
        category.setType(CategoryConsts.MINI_APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (MiniApp item : list) {
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
        List<MiniApp> list = miniAppDao.listCombo(new MiniApp());
        Category category = new Category();
        category.setType(CategoryConsts.MINI_APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (MiniApp item : list) {
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
    public List<MiniApp> listCombo(MiniApp miniApp) {
        Category category = new Category();
        category.setType(CategoryConsts.MINI_APP);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, miniApp.getCategory());
        miniApp.setCategories(nodes);
        return miniAppDao.listCombo(miniApp);
    }
}
