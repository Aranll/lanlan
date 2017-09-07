package com.xiaosuokeji.yilan.admin.controller.system;


import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.system.Article;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("adminArticleController")
@RequestMapping("/admin/system/article")
public class ArticleController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Pagination(items = "articles", api = API.ARTICLE_LIST, itemClass = Article.class)
    @Security(pKey = "system", key = "system_article")
    public String index(Model model, HttpServletRequest request, Article article) {
        model.addAttribute("title", article.getTitle());
        model.addAttribute("dynamic", article.getDynamic());
        model.addAttribute("dynamicStartTime", article.getDynamicStartTime());
        model.addAttribute("dynamicEndTime", article.getDynamicEndTime());

        String redirectUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            redirectUrl += "?" + queryString;
        }
        model.addAttribute("redirectUrl", CodingUtils.urlEncode(redirectUrl));

        return "admin/system/article/article";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, String redirectUrl) {
        model.addAttribute("redirectUrl", CodingUtils.urlDecode(redirectUrl));
        return "admin/system/article/articleCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(Article article) {
        return WebUtils.doRawRequest(API.ARTICLE_SAVE, article).toString();
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, String redirectUrl, Article article) {
        model.addAttribute("redirectUrl", CodingUtils.urlDecode(redirectUrl));

        if (article.getId() != null) {
            JSONObject articleResponse = WebUtils.doRawRequest(API.ARTICLE_GET, article);
            if (articleResponse.getBoolean("status")) {
                model.addAttribute("article", GsonUtils.fromJson(articleResponse.getJSONObject("data").toString(), Article.class));
            }
        }
        return "admin/system/article/articleUpdate";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public String get(Article article) {
        return WebUtils.doRawRequest(API.ARTICLE_GET, article).toString();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(Model model, String redirectUrl, Article article) {
        model.addAttribute("redirectUrl", CodingUtils.urlDecode(redirectUrl));

        if (article.getId() != null) {
            JSONObject articleResponse = WebUtils.doRawRequest(API.ARTICLE_GET, article);
            if (articleResponse.getBoolean("status")) {
                model.addAttribute("article", GsonUtils.fromJson(articleResponse.getJSONObject("data").toString(), Article.class));
            }
        }
        return "admin/system/article/articleDetails";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Article article) {
        return WebUtils.doRawRequest(API.ARTICLE_REMOVE, article).toString();
    }


}
