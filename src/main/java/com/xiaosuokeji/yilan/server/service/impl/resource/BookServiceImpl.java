package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.BookConsts;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.constant.resource.ResourceConsts;
import com.xiaosuokeji.yilan.server.dao.resource.BookDao;
import com.xiaosuokeji.yilan.server.model.resource.App;
import com.xiaosuokeji.yilan.server.model.resource.Book;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Website;
import com.xiaosuokeji.yilan.server.service.intf.resource.BookService;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import com.xiaosuokeji.yilan.server.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * 图书ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void save(Book book) throws BusinessException {
        Book existent = new Book();
        existent.setName(book.getName());
        if (bookDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(BookConsts.BOOK_EXIST);
        }
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void saveMulti(Book book) throws BusinessException {
        if (book.getBooks().size() > 0) {
            for (int i=0; i<book.getBooks().size(); ++i) {
                Book item = book.getBooks().get(i);
                item.setHot(0);
                item.setRecommend(0);
                //校验模型格式
                Set<ConstraintViolation<Book>> constraintViolations = ValidateUtil.getValidator()
                        .validate(item, Book.Save.class);
                for (ConstraintViolation<Book> constraintViolation : constraintViolations) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，" + constraintViolation.getMessage();
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                //校验名称唯一
                for (int j=0; j<book.getBooks().size(); ++j) {
                    if (i != j && item.getName().equals(book.getBooks().get(j).getName())) {
                        String errorMsg = "第" + (i + 2) + "行数据出错，和第" + (j + 2) + "行名称重复";
                        throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                    }
                }
                Book existent = new Book();
                existent.setName(item.getName());
                if (bookDao.count(existent).compareTo(0L) > 0) {
                    String errorMsg = "第" + (i + 2) + "行数据出错，图书已存在";
                    throw new BusinessException(ResourceConsts.IMPORT_ERROR.get(errorMsg));
                }
                bookDao.save(item);
            }
        }
    }

    @Override
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Override
    public void removeCategory(Category category) throws BusinessException {
        Book book = new Book();
        book.setCategory(category);
        if (bookDao.count(book) > 0L) throw new BusinessException(CategoryConsts.CATEGORY_USED);
        categoryService.remove(category);
    }

    @Override
    public void update(Book book) throws BusinessException {
        if (book.getName() != null) {
            Book existent = new Book();
            existent.setName(book.getName());
            List<Book> existents = bookDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(book.getId());
                if (!isSelf) {
                    throw new BusinessException(BookConsts.BOOK_EXIST);
                }
            }
        }
        bookDao.update(book);
    }

    @Override
    public Book get(Book book) throws BusinessException {
        Book existent = bookDao.get(book);
        if (existent == null) {
            throw new BusinessException(BookConsts.BOOK_NOT_EXIST);
        }
        List<Category> path = categoryService.getPath(existent.getCategory());
        existent.setCategories(path);
        return existent;
    }

    @Override
    public PageModel<Book> listAndCount(Book book) {
        Category category = new Category();
        category.setType(CategoryConsts.BOOK);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, book.getCategory());
        book.setCategories(nodes);
        PageModel<Book> result = PageModel.build(bookDao.list(book), bookDao.count(book));
        for (Book item : result.getList()) {
            List<Category> path = categoryService.getPath(tree, item.getCategory());
            item.setCategories(path);
        }
        return result;
    }

    @Override
    public List<Book> listHot() {
        Book book = new Book();
        book.setHot(1);
        return bookDao.listCombo(book);
    }

    @Override
    public List<Category> listRecommend() {
        Book book = new Book();
        book.setRecommend(1);
        List<Book> list = bookDao.listCombo(book);
        Category category = new Category();
        category.setType(CategoryConsts.BOOK);
        Map<Long, Category> tree = categoryService.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (Book item : list) {
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
        List<Book> list = bookDao.listCombo(new Book());
        Category category = new Category();
        category.setType(CategoryConsts.BOOK);
        Map<Long, Category> tree = categoryService.listTree(category);
        for (Book item : list) {
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
    public List<Book> listCombo(Book book) {
        Category category = new Category();
        category.setType(CategoryConsts.BOOK);
        Map<Long, Category> tree = categoryService.listTree(category);
        List<Category> nodes = categoryService.deepSearch(tree, book.getCategory());
        book.setCategories(nodes);
        return bookDao.listCombo(book);
    }
}
