package com.xiaosuokeji.yilan.admin.controller.book;


import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.Book;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("adminBookManageController")
@RequestMapping("/admin/book/book")
public class BookController {
    @Autowired
    private SecurityTokenServer securityTokenServer;
    @Security(pKey = "book", key = "book_book")
    @RequestMapping("")
    @Pagination(items = "books", api = API.BOOK_LIST, itemClass = Book.class)
    public String bookList(Model model, HttpServletRequest request, Book book) {
        model.addAttribute("id", book.getId());
        model.addAttribute("hot", book.getHot());
        model.addAttribute("dynamic", book.getDynamic());
        model.addAttribute("recommend", book.getRecommend());
        model.addAttribute("category", book.getCategory());
        model.addAttribute("accessVipLevel",book.getAccessVipLevel());
        String redirectUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            redirectUrl += "?" + queryString;
        }
        model.addAttribute("redirectUrl",CodingUtils.urlEncode(redirectUrl));

        return "admin/book/book";
    }

    @RequestMapping(value="/book/batchUpload",method = RequestMethod.GET)
    public String batchUpload(Model model){
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "/admin/book/bookBatchUpload";
    }


    @RequestMapping("/new")
    public String saveBook(Model model, HttpServletRequest request,String redirectUrl) {
        model.addAttribute("redirectUrl",redirectUrl);
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "admin/book/bookNew";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Book book) {
        return WebUtils.doRawRequest(API.BOOK_SAVE, book).toString();
    }

    @RequestMapping("/edit")
    public String editBook(Model model, HttpServletRequest request, String redirectUrl,Book book) {
        model.addAttribute("redirectUrl", redirectUrl);
        JSONObject response = WebUtils.doRawRequest(API.BOOK_GET, book);
        model.addAttribute("book", GsonUtils.fromJson(response.get("data").toString(), Book.class));
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "admin/book/bookEdit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateBook(Book book) {
        return WebUtils.doRawRequest(API.BOOK_UPDATE, book).toString();
    }

    @RequestMapping("/get")
    public String getBook(Model model, HttpServletRequest request,String redirectUrl, Book book) {
        model.addAttribute("redirectUrl",redirectUrl);
        JSONObject response = WebUtils.doRawRequest(API.BOOK_GET, book);
        model.addAttribute("book", GsonUtils.fromJson(response.get("data").toString(), Book.class));
        return "admin/book/bookDetails";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(Book book) {
        return WebUtils.doRawRequest(API.BOOK_REMOVE, book).toString();
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public String tree(Book book) {
        return WebUtils.doRawRequest(API.BOOK_CATEGORY_TREE, book).toString();
    }

}
