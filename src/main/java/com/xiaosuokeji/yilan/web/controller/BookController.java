package com.xiaosuokeji.yilan.web.controller;


import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.resource.Book;
import com.xiaosuokeji.yilan.web.model.resource.Category;
import com.xiaosuokeji.yilan.web.model.resource.SearchResource;
import com.xiaosuokeji.yilan.web.model.user.User;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.UserUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("webBookController")
@RequestMapping("/book")
public class BookController {

    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @RequestMapping(value = "")
    @Index
    public String bookIndex(Model model, HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject bookHotResponse = WebUtils.doRawRequest(API.BOOK_HOT_LIST,"");
        if (bookHotResponse.getBoolean("status")) {
            model.addAttribute("bookHot",
                    GsonUtils.fromJson(bookHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Book>>() {
                            }.getType()));
        }

        JSONObject bookCategoriesResponse = WebUtils.doRawRequest(API.BOOK_CATEGORY_TREE,"");
        if (bookCategoriesResponse.getBoolean("status"))
            model.addAttribute("bookCategories",
                    GsonUtils.fromJson(bookCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject bookRecommendResponse = WebUtils.doRawRequest(API.BOOK_RECOMMEND_LIST,"");
        if (bookRecommendResponse.getBoolean("status")) {
            model.addAttribute("bookRecommends",
                    GsonUtils.fromJson(bookRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "web/book/bookIndex";
    }

    @RequestMapping("/all")
    @Index
    public String videoAll(Category category, Model model, HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        model.addAttribute("category",category);

        JSONObject allResponse = WebUtils.doRawRequest(API.BOOK_ALL_LIST, "");
        if (allResponse.getBoolean("status")) {
            model.addAttribute("books",
                    GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "web/book/bookAll";
    }

    @RequestMapping("/detail")
    @Index
    public String bookDetail(Book book,Model model,HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject bookResponse = WebUtils.doRawRequest(API.BOOK_GET, book);
        if (bookResponse.getBoolean("status"))
            model.addAttribute("book",
                    GsonUtils.fromJson(bookResponse.getJSONObject("data").toString(), Book.class));

        return "/web/book/detail";
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public String getBook(@RequestBody Book book, HttpServletRequest request){
        JSONObject bookResponse = WebUtils.doRawRequest(API.BOOK_GET, book);
        JSONObject result = new JSONObject();
        if (bookResponse.getBoolean("status")){
            Book b = GsonUtils.fromJson(bookResponse.getJSONObject("data").toString(), Book.class);
            if(b.getAccessVipLevel() != 0 ){
                if(!UserUtils.checkLogin(request)){
                    result.put("status",false);
                    result.put("code",700);
                } else if(!UserUtils.checkUserVip(request)) {
                    result.put("status", false);
                    result.put("code", 701);
                }else {
                    result.put("status", true);
                    result.put("code", 200);
                    result.put("data",b.getPdf());
                }
            }else {
                result.put("status", true);
                result.put("code", 200);
                result.put("data",b.getPdf());
            }
        }

        return result.toString();
    }


}
