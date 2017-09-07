package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.model.resource.App;
import com.xiaosuokeji.yilan.server.model.resource.Book;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.service.intf.resource.BookService;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 图书Controller<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class BookController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/admin/v1/book/category/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveCategory(@Validated(Category.Save.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.BOOK);
        categoryService.save(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/category/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeCategory(@RequestBody Category category) throws BusinessException {
        bookService.removeCategory(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/category/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateCategory(@Validated(Category.Update.class) @RequestBody Category category) throws BusinessException {
        category.setType(CategoryConsts.BOOK);
        categoryService.update(category);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/category/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getCategory(@RequestBody Category category) throws BusinessException {
        return ServiceResult.build().data(categoryService.get(category));
    }

    @RequestMapping(value = "/admin/v1/book/category/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCountCategory(@RequestBody Category category) {
        category.setType(CategoryConsts.BOOK);
        return ServiceResult.build().data(categoryService.listAndCount(category));
    }

    @RequestMapping(value = "/admin/v1/book/category/combo", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryCombo(@RequestBody Category category) {
        category.setType(CategoryConsts.BOOK);
        return ServiceResult.build().data(categoryService.listCombo(category));
    }

    @RequestMapping(value = "/admin/v1/book/category/tree", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listCategoryTree(@RequestBody Category category) {
        category.setType(CategoryConsts.BOOK);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/admin/v1/book/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(Book.Save.class) @RequestBody Book book) throws BusinessException {
        bookService.save(book);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/multi/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveMulti(@RequestBody Book book) throws BusinessException {
        bookService.saveMulti(book);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult remove(@RequestBody Book book) {
        bookService.remove(book);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(Book.Update.class) @RequestBody Book book) throws BusinessException {
        bookService.update(book);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/book/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody Book book) throws BusinessException {
        return ServiceResult.build().data(bookService.get(book));
    }

    @RequestMapping(value = "/admin/v1/book/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult list(@RequestBody Book book) {
        return ServiceResult.build().data(bookService.listAndCount(book));
    }

    @RequestMapping(value = "/app/v1/book/hot/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listHot() {
        return ServiceResult.build().data(bookService.listHot());
    }

    @RequestMapping(value = "/app/v1/book/category/tree", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryTree4App(@RequestBody(required = false) Category category) {
        if (category == null) category = new Category();
        category.setType(CategoryConsts.BOOK);
        category.setStatus(1);
        return ServiceResult.build().data(categoryService.listTreeCombo(category));
    }

    @RequestMapping(value = "/app/v1/book/recommend/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listRecommend() {
        return ServiceResult.build().data(bookService.listRecommend());
    }

    @RequestMapping(value = "/app/v1/book/all/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listAll() {
        return ServiceResult.build().data(bookService.listAll());
    }

    @RequestMapping(value = "/app/v1/book/get", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult get4App(@RequestBody Book book) throws BusinessException {
        return ServiceResult.build().data(bookService.get(book));
    }

    @RequestMapping(value = "/app/v1/book/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCombo(@RequestBody Book book) {
        return ServiceResult.build().data(bookService.listCombo(book));
    }

    @RequestMapping(value = "/app/v1/book/category/path", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listCategoryPath(@RequestBody Category category) {
        category.setType(CategoryConsts.BOOK);
        return ServiceResult.build().data(categoryService.getPath(category));
    }
}
