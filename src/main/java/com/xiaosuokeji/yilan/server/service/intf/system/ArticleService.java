package com.xiaosuokeji.yilan.server.service.intf.system;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.system.Article;

import java.util.List;

/**
 * 文章Service<br/>
 * Created by xuxiaowei on 2017/4/20.
 */
public interface ArticleService {

	/**
	 * 保存文章
	 * @param article 必填：title，content，url，publisher
	 * @throws BusinessException 文章已存在
	 */
	void save(Article article) throws BusinessException;

	/**
	 * 删除文章
	 * @param article 必填：id
	 */
	void remove(Article article);

	/**
	 * 更新文章
	 * @param article 必填：id，可选更新字段：title，content，url
	 * @throws BusinessException 文章已存在
	 */
	void update(Article article) throws BusinessException;

	/**
	 * 获取单个文章
	 * @param article 必填：id
	 * @return 文章，字段：所有
	 * @throws BusinessException 文章不存在
	 */
	Article get(Article article) throws BusinessException;

	/**
	 * 获取和统计多个文章<br/>
	 * 支持排序和分页
	 * @param article 可选条件：title(支持模糊)，dynamic.startTime，dynamic.endTime
	 * @return 文章列表和数量，字段：所有
	 */
	PageModel<Article> listAndCount(Article article);
}