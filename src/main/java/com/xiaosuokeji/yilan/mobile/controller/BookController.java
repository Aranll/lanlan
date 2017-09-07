package com.xiaosuokeji.yilan.mobile.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.mobile.annotation.Pagination;
import com.xiaosuokeji.yilan.mobile.enumeration.API;
import com.xiaosuokeji.yilan.mobile.model.resource.Book;
import com.xiaosuokeji.yilan.mobile.model.resource.Category;
import com.xiaosuokeji.yilan.mobile.model.resource.SearchResource;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import com.xiaosuokeji.yilan.web.util.CodingUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/31.
 */
@Controller("mobileBookController")
@RequestMapping(value = "/mobile/book")
public class BookController {
    @RequestMapping(value="",method= RequestMethod.GET)
    public String getBooks(Model model, Category category, HttpServletRequest request){
//        String redirect = request.getRequestURI();
//        String queryString = request.getQueryString();
//        if(queryString != null){
//            redirect += "?" + queryString;
//        }
//        model.addAttribute("redirect", CodingUtils.urlEncode(redirect));
        model.addAttribute("category",category);
        JSONObject  allResponse = WebUtils.doRawRequest(API.BOOK_CATEGORY_TREE,"");
        if(allResponse.getBoolean("status")){
            model.addAttribute("bookCategory", GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),new TypeToken<List<SearchResource>>() {}.getType()));
        }
        return "mobile/book/cg-medicine_book";
    }

    @RequestMapping(value = "/category",method = RequestMethod.POST)
    @Pagination(items = "books",api = API.BOOK_LIST,itemClass = Book.class)
    @ResponseBody
    public String getCategory(Model model, Book book){
        book.setLimit(15L);
        return WebUtils.doRawRequest(API.APP_LIST,book).toString();
    }
    @RequestMapping(value = "/category/get",method = RequestMethod.GET)
    public String getBook(Book book,Model model){
        JSONObject bookResponse = WebUtils.doRawRequest(API.BOOK_GET,book);
        if(bookResponse.getBoolean("status")){
            model.addAttribute("book",GsonUtils.fromJson(bookResponse.getJSONObject("data").toString(),Book.class));
        }
        return "mobile/book/cg-book_particulars";
    }

}
