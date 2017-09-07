package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Periodical;
import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.PublicNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 公众号Controller
 * Created by xuxiaowei on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class PublicNumberController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublicNumberService publicNumberService;

    @RequestMapping(value = "/admin/v1/publicNumber/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        publicNumberService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/publicNumber/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/publicNumber/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/publicNumber/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/publicNumber/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(PublicNumber.Save.class) @RequestBody PublicNumber publicNumber) throws BusinessException {
        publicNumberService.save(publicNumber);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody PublicNumber publicNumber) throws BusinessException {
        publicNumberService.saveMulti(publicNumber);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody PublicNumber publicNumber) {
        publicNumberService.remove(publicNumber);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(PublicNumber.Update.class) @RequestBody PublicNumber publicNumber) throws BusinessException {
        publicNumberService.update(publicNumber);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/publicNumber/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody PublicNumber publicNumber) throws BusinessException {
        return ServiceResult.build().data(publicNumberService.get(publicNumber));
    }

    @RequestMapping(value = "/admin/v1/publicNumber/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody PublicNumber publicNumber) {
        return ServiceResult.build().data(publicNumberService.listAndCount(publicNumber));
    }

    @RequestMapping(value = "/app/v1/publicNumber/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(publicNumberService.listHot());
    }

    @RequestMapping(value = "/app/v1/publicNumber/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.PUBLIC_NUMBER);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/publicNumber/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(publicNumberService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/publicNumber/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(publicNumberService.listAll());
    }

    @RequestMapping(value = "/app/v1/publicNumber/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody PublicNumber publicNumber) {
        return ServiceResult.build().data(publicNumberService.listCombo(publicNumber));
    }
}
