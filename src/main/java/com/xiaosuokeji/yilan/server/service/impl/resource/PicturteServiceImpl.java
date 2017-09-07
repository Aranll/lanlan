package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.PictureConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.dao.resource.PictureDao;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Picture;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.service.intf.resource.PictureService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 图片ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
@Service
public class PicturteServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(Picture picture) {
        pictureDao.save(picture);
    }

    @Override
    @Transactional
    public void saveMulti(Picture picture) throws BusinessException {
        if (picture.getPictures().size() > 0) {
            for (int i=0; i<picture.getPictures().size(); ++i) {
                Picture item = picture.getPictures().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<Picture>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, Picture.Save.class);
                for (ConstraintViolation<Picture> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<picture.getPictures().size(); ++j) {
                    if (i != j && item.getName().equals(picture.getPictures().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                Picture existent = new Picture();
                existent.setName(item.getName());
                if (pictureDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，图片已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                pictureDao.save(item);
            }
        }
    }
    
    @Override
    public void remove(Picture picture) {
        pictureDao.remove(picture);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        Picture picture = new Picture();
        picture.setCategory(category);
        if (pictureDao.count(picture) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(Picture picture) {
        pictureDao.update(picture);
    }

    @Override
    public Picture get(Picture picture) throws BusinessException {
        Picture existent = pictureDao.get(picture);
        if (existent == null) {
            throw new BusinessException(PictureConsts.PICTURE_NOT_EXIST);
        }
        return existent;
    }

    @Override
    public PageModel<Picture> listAndCount(Picture picture) {
        Category category = new Category();
        category.setType(CategoryConsts.PICTURE);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, picture.getCategory());
        picture.setCategories(nodes);
        PageModel<Picture> result = PageModel.build(pictureDao.list(picture), pictureDao.count(picture));
        return result;
    }

    @Override
    public PageModel<Picture> listComboAndCount(Picture picture) {
        Category category = new Category();
        category.setType(CategoryConsts.PICTURE);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, picture.getCategory());
        picture.setCategories(nodes);
        PageModel<Picture> result = PageModel.build(pictureDao.listCombo(picture), pictureDao.count(picture));
        return result;
    }
}
