package com.xiaosuokeji.yilan.server.controller.system;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.system.Article;
import com.xiaosuokeji.yilan.server.service.intf.system.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api")
@XSProxy
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/admin/v1/article/save", method = RequestMethod.POST)
	@XSProxy
	@XSAuth
	public ServiceResult save(@Validated(Article.Save.class) @RequestBody Article article) throws BusinessException {
		articleService.save(article);
		return ServiceResult.build();
	}

	@RequestMapping(value = "/admin/v1/article/remove", method = RequestMethod.POST)
	@XSProxy
	@XSAuth
	public ServiceResult remove(@RequestBody Article article) {
		articleService.remove(article);
		return ServiceResult.build();
	}

	@RequestMapping(value = "/admin/v1/article/update", method = RequestMethod.POST)
	@XSProxy
	@XSAuth
	public ServiceResult update(@Validated(Article.Update.class) @RequestBody Article article) throws BusinessException {
		articleService.update(article);
		return ServiceResult.build();
	}

	@RequestMapping(value = "/admin/v1/article/get", method = RequestMethod.POST)
	@XSProxy
	@XSAuth
	public ServiceResult get(@RequestBody Article article) throws BusinessException {
		return ServiceResult.build().data(articleService.get(article));
	}

	@RequestMapping(value = "/app/v1/article/get", method = RequestMethod.POST)
	@XSProxy
	public ServiceResult get4App(@RequestBody Article article) throws BusinessException {
		return ServiceResult.build().data(articleService.get(article));
	}

	@RequestMapping(value = "/admin/v1/article/list", method = RequestMethod.POST)
	@XSProxy
	@XSAuth
	public ServiceResult listAndCount(@RequestBody Article article) {
		return ServiceResult.build().data(articleService.listAndCount(article));
	}
}