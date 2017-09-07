package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.App;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.AppService;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 软件Controller<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class AppController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/admin/v1/app/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.APP);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        appService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.APP);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/app/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.APP);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/app/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.APP);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/app/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.APP);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/app/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(App.Save.class) @RequestBody App app) throws BusinessException {
        appService.save(app);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody App app) throws BusinessException {
        appService.saveMulti(app);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody App app) {
        appService.remove(app);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(App.Update.class) @RequestBody App app) throws BusinessException {
        appService.update(app);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/app/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody App app) throws BusinessException {
        return ServiceResult.build().data(appService.get(app));
    }

    @RequestMapping(value = "/admin/v1/app/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody App app) {
        return ServiceResult.build().data(appService.listAndCount(app));
    }

    @RequestMapping(value = "/app/v1/app/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(appService.listHot());
    }

    @RequestMapping(value = "/app/v1/app/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.APP);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/app/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(appService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/app/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(appService.listAll());
    }

    @RequestMapping(value = "/app/v1/app/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody App app) {
        return ServiceResult.build().data(appService.listCombo(app));
    }
}
