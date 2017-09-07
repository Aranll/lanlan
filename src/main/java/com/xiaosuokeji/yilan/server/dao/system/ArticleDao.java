package com.xiaosuokeji.yilan.server.dao.system;

import com.xiaosuokeji.yilan.server.model.system.Article;

import java.util.List;

/**
 * 文章Dao<br/>
 * Created by xuxiaowei on 2017/4/20.
 */
public interface ArticleDao {

	/**
	 * 保存文章
	 * @param article 必填：title，content，url，publisher
	 * @return 受影响行数
	 */
	int save(Article article);

	/**
	 * 删除文章
	 * @param article 必填：id
	 * @return 受影响行数
	 */
	int remove(Article article);

	/**
	 * 更新文章
	 * @param article 必填：id，可选更新字段：title，content，url
	 * @return 受影响行数
	 */
	int update(Article article);

	/**
	 * 获取单个文章
	 * @param article 必填：id
	 * @return 文章，字段：所有
	 */
	Article get(Article article);

	/**
	 * 获取多个文章<br/>
	 * 支持排序和分页
	 * @param article 可选条件：title(支持模糊)，dynamic.startTime，dynamic.endTime
	 * @return 文章，字段：所有
	 */
	List<Article> list(Article article);

	/**
	 * 统计文章数量
	 * @param article 可选条件：title(支持模糊)，dynamic.startTime，dynamic.endTime
	 * @return 文章数量
	 */
	Long count(Article article);
}