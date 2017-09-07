package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 网站Controller<br/>
 * Created by xuxiaowei on 2017/7/31.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class WebsiteController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping(value = "/admin/v1/website/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.WEBSITE);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        websiteService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.WEBSITE);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/website/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.WEBSITE);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/website/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.WEBSITE);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/website/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.WEBSITE);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/website/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(Website.Save.class) @RequestBody Website website) throws BusinessException {
        websiteService.save(website);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody Website website) throws BusinessException {
        websiteService.saveMulti(website);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody Website website) {
        websiteService.remove(website);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(Website.Update.class) @RequestBody Website website) throws BusinessException {
        websiteService.update(website);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/website/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody Website website) throws BusinessException {
        return ServiceResult.build().data(websiteService.get(website));
    }

    @RequestMapping(value = "/admin/v1/website/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody Website website) {
        return ServiceResult.build().data(websiteService.listAndCount(website));
    }

    @RequestMapping(value = "/app/v1/website/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(websiteService.listHot());
    }

    @RequestMapping(value = "/app/v1/website/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.WEBSITE);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/website/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(websiteService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/website/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(websiteService.listAll());
    }

    @RequestMapping(value = "/app/v1/website/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody Website website) {
        return ServiceResult.build().data(websiteService.listCombo(website));
    }
}
