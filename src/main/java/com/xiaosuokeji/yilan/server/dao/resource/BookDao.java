package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.Book;

import java.util.List;

/**
 * 图书Dao<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public interface BookDao {

    /**
     * 保存图书
     * @param book 必填：name，author，publisher，publishDate，cover，pdf，category.id，hot，recommend，accessVipLevel
     *             选填：profile
     * @return 受影响行数
     */
    int save(Book book);

    /**
     * 删除图书
     * @param book 必填：id
     * @return 受影响行数
     */
    int remove(Book book);

    /**
     * 更新图书
     * @param book 必填：id，可选更新字段：name，author，publisher，publishDate，cover，pdf，category.id，hot，recommend，
     *             profile，accessVipLevel
     * @return 受影响行数
     */
    int update(Book book);

    /**
     * 获取单个图书
     * @param book 必填：id
     * @return 图书，字段：所有，category.id，category.name
     */
    Book get(Book book);

    /**
     * 获取多个图书<br/>
     * 支持排序和分页
     * @param book 可选条件：id，name（支持模糊），hot，recommend，accessVipLevel，category.id，categories[i].id
     * @return 图书列表，字段：所有，category.id，category.name
     */
    List<Book> list(Book book);

    /**
     * 获取多个图书<br/>
     * 支持排序和分页
     * @param book 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 图书列表，字段：id，name，cover，pdf，category.id
     */
    List<Book> listCombo(Book book);

    /**
     * 统计图书数量
     * @param book 可选条件：id，name（支持模糊），hot，recommend，accessVipLevel，category.id，categories[i].id
     * @return 图书数量
     */
    Long count(Book book);
}
