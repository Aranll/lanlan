package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Picture;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 图片Controller<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class PictureController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/admin/v1/picture/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.PICTURE);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        pictureService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.PICTURE);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/picture/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.PICTURE);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/picture/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.PICTURE);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/picture/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.PICTURE);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/picture/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(Picture.PictureSave.class) @RequestBody Picture picture) throws BusinessException {
        pictureService.save(picture);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody Picture picture) throws BusinessException {
        pictureService.saveMulti(picture);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody Picture picture) {
        pictureService.remove(picture);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(Picture.PictureUpdate.class) @RequestBody Picture picture) throws BusinessException {
        pictureService.update(picture);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/picture/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody Picture picture) throws BusinessException {
        return ServiceResult.build().data(pictureService.get(picture));
    }

    @RequestMapping(value = "/admin/v1/picture/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody Picture picture) {
        return ServiceResult.build().data(pictureService.listAndCount(picture));
    }

    @RequestMapping(value = "/app/v1/picture/category/combo", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryCombo4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.PICTURE);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/app/v1/picture/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.PICTURE);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/picture/category/path", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryPath(@RequestBody Category category) {
        category.setType(CategoryConsts.PICTURE);
        return ServiceResult.build().data(categoryService.getPath(category));
    }

    @RequestMapping(value = "/app/v1/picture/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult list4App(@RequestBody(required = false) Picture picture) {
        if (picture == null) picture = new Picture();
        return ServiceResult.build().data(pictureService.listComboAndCount(picture));
    }

    @RequestMapping(value = "/app/v1/picture/get", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult get4App(@RequestBody Picture picture) throws BusinessException {
        return ServiceResult.build().data(pictureService.get(picture));
    }
}
