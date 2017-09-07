package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.constant.resource.WebsiteConsts;
import com.xiaosuokeji.yilan.server.dao.resource.WebsiteDao;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Picture;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.WebsiteService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 网站ServiceImpl<br/>
 * Created by xuxiaowei on 2017/7/31.
 */
@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteDao websiteDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(Website website) throws BusinessException {
        Website existent = new Website();
        existent.setName(website.getName());
        if (websiteDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(WebsiteConsts.WEBSITE_EXIST);
        }
        websiteDao.save(website);
    }

    @Override
    @Transactional
    public void saveMulti(Website website) throws BusinessException {
        if (website.getWebsites().size() > 0) {
            for (int i=0; i<website.getWebsites().size(); ++i) {
                Website item = website.getWebsites().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<Website>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, Website.Save.class);
                for (ConstraintViolation<Website> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<website.getWebsites().size(); ++j) {
                    if (i != j && item.getName().equals(website.getWebsites().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                Website existent = new Website();
                existent.setName(item.getName());
                if (websiteDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，网站已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                websiteDao.save(item);
            }
        }
    }

    @Override
    public void remove(Website website) {
        websiteDao.remove(website);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        Website website = new Website();
        website.setCategory(category);
        if (websiteDao.count(website) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(Website website) throws BusinessException {
        if (website.getName() != null) {
            Website existent = new Website();
            existent.setName(website.getName());
            List<Website> existents = websiteDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(website.getId());
                if (!isSelf) {
                    throw new BusinessException(WebsiteConsts.WEBSITE_EXIST);
                }
            }
        }
        websiteDao.update(website);
    }

    @Override
    public Website get(Website website) throws BusinessException {
        Website existent = websiteDao.get(website);
        if (existent == null) {
            throw new BusinessException(WebsiteConsts.WEBSITE_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<Website> listAndCount(Website website) {
        Category category = new Category();
        category.setType(CategoryConsts.WEBSITE);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, website.getCategory());
        website.setCategories(nodes);
        PageModel<Website> result = PageModel.build(websiteDao.list(website), websiteDao.count(website));
        for (Website item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<Website> listHot() {
        Website website = new Website();
        website.setHot(1);
        return websiteDao.listCombo(website);
    }

    @Override
    public List<Category> listRecommend() {
        Website website = new Website();
        website.setRecommend(1);
        List<Website> list = websiteDao.listCombo(website);
        Category category = new Category();
        category.setType(CategoryConsts.WEBSITE);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (Website item : list) {
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
        List<Website> list = websiteDao.listCombo(new Website());
        Category category = new Category();
        category.setType(CategoryConsts.WEBSITE);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (Website item : list) {
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
    public List<Website> listCombo(Website website) {
        Category category = new Category();
        category.setType(CategoryConsts.WEBSITE);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, website.getCategory());
        website.setCategories(nodes);
        return websiteDao.listCombo(website);
    }
}
