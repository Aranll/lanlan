package com.xiaosuokeji.yilan.server.controller.resource;

/**
 * 小程序Controller<br/>
 * Created by xuxiaowei on 2017/8/1.
 */

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.Book;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.MiniApp;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.MiniAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api")
@XSProxy
public class MiniAppController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MiniAppService miniAppService;

    @RequestMapping(value = "/admin/v1/miniApp/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.MINI_APP);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        miniAppService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.MINI_APP);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/miniApp/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.MINI_APP);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/miniApp/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.MINI_APP);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/miniApp/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.MINI_APP);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/miniApp/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(MiniApp.Save.class) @RequestBody MiniApp miniApp) throws BusinessException {
        miniAppService.save(miniApp);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody MiniApp miniApp) throws BusinessException {
        miniAppService.saveMulti(miniApp);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody MiniApp miniApp) {
        miniAppService.remove(miniApp);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(MiniApp.Update.class) @RequestBody MiniApp miniApp) throws BusinessException {
        miniAppService.update(miniApp);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/miniApp/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody MiniApp miniApp) throws BusinessException {
        return ServiceResult.build().data(miniAppService.get(miniApp));
    }

    @RequestMapping(value = "/admin/v1/miniApp/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody MiniApp miniApp) {
        return ServiceResult.build().data(miniAppService.listAndCount(miniApp));
    }

    @RequestMapping(value = "/app/v1/miniApp/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(miniAppService.listHot());
    }

    @RequestMapping(value = "/app/v1/miniApp/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.MINI_APP);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/miniApp/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(miniAppService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/miniApp/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(miniAppService.listAll());
    }

    @RequestMapping(value = "/app/v1/miniApp/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody MiniApp miniApp) {
        return ServiceResult.build().data(miniAppService.listCombo(miniApp));
    }
}
