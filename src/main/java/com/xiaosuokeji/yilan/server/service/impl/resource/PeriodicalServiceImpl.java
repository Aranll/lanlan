package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.PeriodicalConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.dao.resource.PeriodicalDao;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.MiniApp;
import com.xiaosuokeji.yilan.server.model.resource.Periodical;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.PeriodicalService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 期刊ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class PeriodicalServiceImpl implements PeriodicalService {

    @Autowired
    private PeriodicalDao periodicalDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(Periodical periodical) throws BusinessException {
        Periodical existent = new Periodical();
        existent.setName(periodical.getName());
        if (periodicalDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(PeriodicalConsts.PERIODICAL_EXIST);
        }
        periodicalDao.save(periodical);
    }

    @Override
    @Transactional
    public void saveMulti(Periodical periodical) throws BusinessException {
        if (periodical.getPeriodicals().size() > 0) {
            for (int i=0; i<periodical.getPeriodicals().size(); ++i) {
                Periodical item = periodical.getPeriodicals().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<Periodical>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, Periodical.Save.class);
                for (ConstraintViolation<Periodical> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<periodical.getPeriodicals().size(); ++j) {
                    if (i != j && item.getName().equals(periodical.getPeriodicals().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                Periodical existent = new Periodical();
                existent.setName(item.getName());
                if (periodicalDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，期刊已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                periodicalDao.save(item);
            }
        }
    }

    @Override
    public void remove(Periodical periodical) {
        periodicalDao.remove(periodical);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        Periodical periodical = new Periodical();
        periodical.setCategory(category);
        if (periodicalDao.count(periodical) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(Periodical periodical) throws BusinessException {
        if (periodical.getName() != null) {
            Periodical existent = new Periodical();
            existent.setName(periodical.getName());
            List<Periodical> existents = periodicalDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(periodical.getId());
                if (!isSelf) {
                    throw new BusinessException(PeriodicalConsts.PERIODICAL_EXIST);
                }
            }
        }
        periodicalDao.update(periodical);
    }

    @Override
    public Periodical get(Periodical periodical) throws BusinessException {
        Periodical existent = periodicalDao.get(periodical);
        if (existent == null) {
            throw new BusinessException(PeriodicalConsts.PERIODICAL_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<Periodical> listAndCount(Periodical periodical) {
        Category category = new Category();
        category.setType(CategoryConsts.PERIODICAL);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, periodical.getCategory());
        periodical.setCategories(nodes);
        PageModel<Periodical> result = PageModel.build(periodicalDao.list(periodical), periodicalDao.count(periodical));
        for (Periodical item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<Periodical> listHot() {
        Periodical periodical = new Periodical();
        periodical.setHot(1);
        return periodicalDao.listCombo(periodical);
    }

    @Override
    public List<Category> listRecommend() {
        Periodical periodical = new Periodical();
        periodical.setRecommend(1);
        List<Periodical> list = periodicalDao.listCombo(periodical);
        Category category = new Category();
        category.setType(CategoryConsts.PERIODICAL);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (Periodical item : list) {
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
        List<Periodical> list = periodicalDao.listCombo(new Periodical());
        Category category = new Category();
        category.setType(CategoryConsts.PERIODICAL);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (Periodical item : list) {
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
    public List<Periodical> listCombo(Periodical periodical) {
        Category category = new Category();
        category.setType(CategoryConsts.PERIODICAL);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, periodical.getCategory());
        periodical.setCategories(nodes);
        return periodicalDao.listCombo(periodical);
    }
}
