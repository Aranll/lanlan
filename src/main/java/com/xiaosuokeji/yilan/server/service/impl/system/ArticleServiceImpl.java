package com.xiaosuokeji.yilan.server.service.impl.system;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.system.ArticleConsts;
import com.xiaosuokeji.yilan.server.dao.system.ArticleDao;
import com.xiaosuokeji.yilan.server.model.system.Article;
import com.xiaosuokeji.yilan.server.service.intf.system.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章ServiceImpl<br/>
 * Created by xuxiaowei on 2017/4/20.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Value("${article.urlPrefix}")
	private String urlPrefix;

	@Override
	@Transactional
	public void save(Article article) throws BusinessException {
		Article existent = new Article();
		existent.setTitle(article.getTitle());
		if (articleDao.count(existent).compareTo(0L) > 0) throw new BusinessException(ArticleConsts.ARTICLE_EXIST);
		articleDao.save(article);
		if (StringUtils.isBlank(article.getUrl())) {
			article.setUrl(urlPrefix + "/" + String.valueOf(article.getId()));
			articleDao.update(article);
		}
	}

	@Override
	public void remove(Article article) {
		articleDao.remove(article);
	}

	@Override
	public void update(Article article) throws BusinessException {
		if (article.getTitle() != null) {
			Article existent = new Article();
			existent.setTitle(article.getTitle());
			List<Article> existents = articleDao.list(existent);
			if (existents.size() > 0) {
				boolean isSelf = existents.get(0).getId().equals(article.getId());
				if (!isSelf) throw new BusinessException(ArticleConsts.ARTICLE_EXIST);
			}
		}
		articleDao.update(article);
	}

	@Override
	public Article get(Article article) throws BusinessException {
		Article existent = articleDao.get(article);
		if (existent == null) throw new BusinessException(ArticleConsts.ARTICLE_NOT_EXIST);
		return existent;
	}

	@Override
	public PageModel listAndCount(Article article) {
		article.setDefaultSort("id", "DESC");
		return PageModel.build(articleDao.list(article), articleDao.count(article));
	}


}