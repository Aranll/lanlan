package com.xiaosuokeji.yilan.server.service.impl.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.resource.CategoryConsts;
import com.xiaosuokeji.yilan.server.dao.resource.CategoryDao;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.service.intf.resource.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类ServiceImpl<br/>
 * Created by xuxiaowei on 2017/7/25.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void save(Category category) throws BusinessException {
        Category existent = new Category();
        existent.setType(category.getType());
        existent.setName(category.getName());
        if (categoryDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(CategoryConsts.CATEGORY_EXIST);
        }
        categoryDao.save(category);
    }

    @Override
    public void remove(Category category) throws BusinessException {
        Category existent = new Category();
        existent.setParent(category);
        if (categoryDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(CategoryConsts.CATEGORY_USED);
        }
        categoryDao.remove(category);
    }

    @Override
    @Transactional
    public void update(Category category) throws BusinessException {
        if (category.getName() != null) {
            Category existent = new Category();
            existent.setType(category.getType());
            existent.setName(category.getName());
            List<Category> existents = categoryDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(category.getId());
                if (!isSelf) {
                    throw new BusinessException(CategoryConsts.CATEGORY_EXIST);
                }
            }
        }
        if (category.getStatus() != null || category.getHot() != null) {
            Map<Long, Category> tree = listTree(new Category());
            List<Category> children = deepSearch(tree, category);
            List<Category> parents = getPath(tree, category);
            if (category.getStatus() != null) {
                if (category.getStatus().equals(0)) {
                    category.setCategories(children);
                }
                else {
                    category.setCategories(parents);
                }
                categoryDao.updateStatus(category);
            }
            if (category.getHot() != null) {
                if (category.getHot().equals(0)) {
                    category.setCategories(children);
                }
                else {
                    category.setCategories(parents);
                }
                categoryDao.updateHot(category);
            }
        }
        categoryDao.update(category);
    }

    @Override
    public Category get(Category category) throws BusinessException {
        Category existent = categoryDao.get(category);
        if (existent == null) {
            throw new BusinessException(CategoryConsts.CATEGORY_NOT_EXIST);
        }
        return existent;
    }

    @Override
    public PageModel<Category> listAndCount(Category category) {
        return PageModel.build(categoryDao.list(category), categoryDao.count(category));
    }

    @Override
    public List<Category> listCombo(Category category) {
        category.setDefaultSort("seq", "DESC");
        return categoryDao.listCombo(category);
    }

    @Override
    public List<Category> listTreeCombo(Category category) {
        category.setDefaultSort("seq", "DESC");
        List<Category> list = categoryDao.listTree(category);
        Map<Long, Category> map = new HashMap<>();
        for (Category item : list) {
            map.put(item.getId(), item);
        }
        List<Category> tree = new ArrayList<>();
        for (Category item : list) {
            Long parentId = item.getParent().getId();
            if (parentId.equals(0L)) {
                tree.add(item);
            }
            else {
                map.get(parentId).addChild(item);
            }
        }
        return tree;
    }

    @Override
    public Map<Long, Category> listTree(Category category) {
        category.setDefaultSort("seq", "DESC");
        List<Category> list = categoryDao.listTree(category);
        Map<Long, Category> tree = new HashMap<>();
        for (Category item : list) {
            tree.put(item.getId(), item);
        }
        for (Category item : list) {
            Long parentId = item.getParent().getId();
            if (!parentId.equals(0L)) {
                tree.get(parentId).addChild(item);
                item.setParent(tree.get(parentId));
            }
        }
        return tree;
    }

    @Override
    public List<Category> deepSearch(Category node) {
        Map<Long, Category> tree = listTree(new Category());
        return deepSearch(tree, node);
    }

    @Override
    public List<Category> deepSearch(Map<Long, Category> tree, Category node) {
        if (node == null || node.getId() == null) return new ArrayList<>();
        Category root = tree.get(node.getId());
        List<Category> nodes = new ArrayList<>();
        deepSearch(root, nodes);
        return nodes;
    }

    @Override
    public List<Category> getPath(Category node) {
        Map<Long, Category> tree = listTree(new Category());
        return getPath(tree, node);
    }

    @Override
    public List<Category> getPath(Map<Long, Category> tree, Category node) {
        if (node == null || node.getId() == null) return new ArrayList<>();
        Category treeNode = tree.get(node.getId());
        List<Category> path = new ArrayList<>();
        while (treeNode != null && !treeNode.getId().equals(0L)) {
            Category item = new Category();
            item.setId(treeNode.getId());
            item.setName(treeNode.getName());
            path.add(0, item);
            treeNode = treeNode.getParent();
        }
        return path;
    }

    @Override
    public void clearBlankNode(Map<Long, Category> tree) {
        List<Category> roots = new ArrayList<>();
        for (Map.Entry<Long, Category> item : tree.entrySet()) {
            Category temp = item.getValue();
            if (temp.getParent().getId().equals(0L)) {
                roots.add(temp);
            }
        }
        List<Category> blankNodes = new ArrayList<>();
        for (Category root : roots) {
            removeBlankNode(root, blankNodes);
        }
        for (Category root : blankNodes) {
            tree.remove(root.getId());
        }
    }

    private void deepSearch(Category root, List<Category> nodes) {
        if (root != null) {
            nodes.add(root);
            if (root.getChildren() != null && root.getChildren().size() > 0) {
                for (Category item : root.getChildren()) {
                    deepSearch(item, nodes);
                }
            }
        }
    }

    private void removeBlankNode(Category root, List<Category> blankNodes) {
        if (root != null) {
            if (root.getChildren() != null && root.getChildren().size() > 0) {
                for (Category item : root.getChildren()) {
                    removeBlankNode(item, blankNodes);
                }
                for (Category item : blankNodes) {
                    root.removeChild(item);
                }
            }
            boolean isBlank = (root.getResources() == null || root.getResources().size() == 0)
                    && (root.getChildren() == null || root.getChildren().size() == 0);
            if (isBlank) {
                blankNodes.add(root);
            }
        }
    }
}
