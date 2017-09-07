package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.PublicNumberConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.dao.resource.PublicNumberDao;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Periodical;
import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.PublicNumberService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 公众号ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class PublicNumberServiceImpl implements PublicNumberService {

    @Autowired
    private PublicNumberDao publicNumberDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(PublicNumber publicNumber) throws BusinessException {
        PublicNumber existent = new PublicNumber();
        existent.setName(publicNumber.getName());
        if (publicNumberDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(PublicNumberConsts.PUBLICNUMBER_EXIST);
        }
        publicNumberDao.save(publicNumber);
    }

    @Override
    @Transactional
    public void saveMulti(PublicNumber publicNumber) throws BusinessException {
        if (publicNumber.getPublicNumbers().size() > 0) {
            for (int i=0; i<publicNumber.getPublicNumbers().size(); ++i) {
                PublicNumber item = publicNumber.getPublicNumbers().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<PublicNumber>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, PublicNumber.Save.class);
                for (ConstraintViolation<PublicNumber> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<publicNumber.getPublicNumbers().size(); ++j) {
                    if (i != j && item.getName().equals(publicNumber.getPublicNumbers().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                PublicNumber existent = new PublicNumber();
                existent.setName(item.getName());
                if (publicNumberDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，公众号已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                publicNumberDao.save(item);
            }
        }
    }

    @Override
    public void remove(PublicNumber publicNumber) {
        publicNumberDao.remove(publicNumber);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        PublicNumber publicNumber = new PublicNumber();
        publicNumber.setCategory(category);
        if (publicNumberDao.count(publicNumber) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(PublicNumber publicNumber) throws BusinessException {
        if (publicNumber.getName() != null) {
            PublicNumber existent = new PublicNumber();
            existent.setName(publicNumber.getName());
            List<PublicNumber> existents = publicNumberDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(publicNumber.getId());
                if (!isSelf) {
                    throw new BusinessException(PublicNumberConsts.PUBLICNUMBER_EXIST);
                }
            }
        }
        publicNumberDao.update(publicNumber);
    }

    @Override
    public PublicNumber get(PublicNumber publicNumber) throws BusinessException {
        PublicNumber existent = publicNumberDao.get(publicNumber);
        if (existent == null) {
            throw new BusinessException(PublicNumberConsts.PUBLICNUMBER_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<PublicNumber> listAndCount(PublicNumber publicNumber) {
        Category category = new Category();
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, publicNumber.getCategory());
        publicNumber.setCategories(nodes);
        PageModel<PublicNumber> result = PageModel.build(publicNumberDao.list(publicNumber), publicNumberDao.count(publicNumber));
        for (PublicNumber item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<PublicNumber> listHot() {
        PublicNumber publicNumber = new PublicNumber();
        publicNumber.setHot(1);
        return publicNumberDao.listCombo(publicNumber);
    }

    @Override
    public List<Category> listRecommend() {
        PublicNumber publicNumber = new PublicNumber();
        publicNumber.setRecommend(1);
        List<PublicNumber> list = publicNumberDao.listCombo(publicNumber);
        Category category = new Category();
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (PublicNumber item : list) {
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
        List<PublicNumber> list = publicNumberDao.listCombo(new PublicNumber());
        Category category = new Category();
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (PublicNumber item : list) {
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
    public List<PublicNumber> listCombo(PublicNumber publicNumber) {
        Category category = new Category();
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, publicNumber.getCategory());
        publicNumber.setCategories(nodes);
        return publicNumberDao.listCombo(publicNumber);
    }
}
