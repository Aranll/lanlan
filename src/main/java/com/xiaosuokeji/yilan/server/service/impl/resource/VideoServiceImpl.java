package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.constant.resource.VideoConsts;
import com.xiaosuokeji.yilan.server.dao.resource.VideoDao;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;
import com.xiaosuokeji.yilan.server.model.resource.Video;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.VideoService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 视频ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(Video video) throws BusinessException {
        Video existent = new Video();
        existent.setName(video.getName());
        if (videoDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(VideoConsts.VIDEO_EXIST);
        }
        videoDao.save(video);
    }

    @Override
    @Transactional
    public void saveMulti(Video video) throws BusinessException {
        if (video.getVideos().size() > 0) {
            for (int i=0; i<video.getVideos().size(); ++i) {
                Video item = video.getVideos().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<Video>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, Video.Save.class);
                for (ConstraintViolation<Video> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<video.getVideos().size(); ++j) {
                    if (i != j && item.getName().equals(video.getVideos().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                Video existent = new Video();
                existent.setName(item.getName());
                if (videoDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，视频已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                videoDao.save(item);
            }
        }
    }

    @Override
    public void remove(Video video) {
        videoDao.remove(video);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        Video video = new Video();
        video.setCategory(category);
        if (videoDao.count(video) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(Video video) throws BusinessException {
        if (video.getName() != null) {
            Video existent = new Video();
            existent.setName(video.getName());
            List<Video> existents = videoDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(video.getId());
                if (!isSelf) {
                    throw new BusinessException(VideoConsts.VIDEO_EXIST);
                }
            }
        }
        videoDao.update(video);
    }

    @Override
    public Video get(Video video) throws BusinessException {
        Video existent = videoDao.get(video);
        if (existent == null) {
            throw new BusinessException(VideoConsts.VIDEO_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<Video> listAndCount(Video video) {
        Category category = new Category();
        category.setType(CategoryConsts.VIDEO);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, video.getCategory());
        video.setCategories(nodes);
        PageModel<Video> result = PageModel.build(videoDao.list(video), videoDao.count(video));
        for (Video item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<Video> listHot() {
        Video video = new Video();
        video.setHot(1);
        return videoDao.listCombo(video);
    }

    @Override
    public List<Category> listRecommend() {
        Video video = new Video();
        video.setRecommend(1);
        List<Video> list = videoDao.listCombo(video);
        Category category = new Category();
        category.setType(CategoryConsts.VIDEO);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (Video item : list) {
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
        List<Video> list = videoDao.listCombo(new Video());
        Category category = new Category();
        category.setType(CategoryConsts.VIDEO);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (Video item : list) {
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
    public List<Video> listCombo(Video video) {
        Category category = new Category();
        category.setType(CategoryConsts.VIDEO);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, video.getCategory());
        video.setCategories(nodes);
        return videoDao.listCombo(video);
    }
}
