package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.MiniApp;
import com.xiaosuokeji.yilan.server.model.resource.Periodical;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 期刊Controller<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class PeriodicalController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PeriodicalService periodicalService;

    @RequestMapping(value = "/admin/v1/periodical/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.PERIODICAL);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        periodicalService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.PERIODICAL);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/periodical/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.PERIODICAL);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/periodical/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.PERIODICAL);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/periodical/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.PERIODICAL);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/periodical/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(Periodical.Save.class) @RequestBody Periodical periodical) throws BusinessException {
        periodicalService.save(periodical);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody Periodical periodical) throws BusinessException {
        periodicalService.saveMulti(periodical);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody Periodical periodical) {
        periodicalService.remove(periodical);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(Periodical.Update.class) @RequestBody Periodical periodical) throws BusinessException {
        periodicalService.update(periodical);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/periodical/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody Periodical periodical) throws BusinessException {
        return ServiceResult.build().data(periodicalService.get(periodical));
    }

    @RequestMapping(value = "/admin/v1/periodical/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody Periodical periodical) {
        return ServiceResult.build().data(periodicalService.listAndCount(periodical));
    }

    @RequestMapping(value = "/app/v1/periodical/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(periodicalService.listHot());
    }

    @RequestMapping(value = "/app/v1/periodical/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.PERIODICAL);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/periodical/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(periodicalService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/periodical/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(periodicalService.listAll());
    }

    @RequestMapping(value = "/app/v1/periodical/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody Periodical periodical) {
        return ServiceResult.build().data(periodicalService.listCombo(periodical));
    }
}
