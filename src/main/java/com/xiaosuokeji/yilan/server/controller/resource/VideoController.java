package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;
import com.xiaosuokeji.yilan.server.model.resource.Video;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 视频Controller<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class VideoController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/admin/v1/video/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.VIDEO);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        videoService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.VIDEO);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/video/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.VIDEO);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/video/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.VIDEO);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/video/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.VIDEO);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/video/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(Video.Save.class) @RequestBody Video video) throws BusinessException {
        videoService.save(video);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody Video video) throws BusinessException {
        videoService.saveMulti(video);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody Video video) {
        videoService.remove(video);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(Video.Update.class) @RequestBody Video video) throws BusinessException {
        videoService.update(video);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/video/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody Video video) throws BusinessException {
        return ServiceResult.build().data(videoService.get(video));
    }

    @RequestMapping(value = "/admin/v1/video/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody Video video) {
        return ServiceResult.build().data(videoService.listAndCount(video));
    }

    @RequestMapping(value = "/app/v1/video/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(videoService.listHot());
    }

    @RequestMapping(value = "/app/v1/video/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.VIDEO);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/video/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(videoService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/video/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(videoService.listAll());
    }

    @RequestMapping(value = "/app/v1/video/get", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult get4App(@RequestBody Video video) throws BusinessException {
        return ServiceResult.build().data(videoService.get(video));
    }

    @RequestMapping(value = "/app/v1/video/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody Video video) {
        return ServiceResult.build().data(videoService.listCombo(video));
    }

    @RequestMapping(value = "/app/v1/video/category/path", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryPath(@RequestBody Category category) {
        category.setType(CategoryConsts.VIDEO);
        return ServiceResult.build().data(categoryService.getPath(category));
    }
}
