package com.xiaosuokeji.yilan.web.enumeration;

import org.springframework.http.HttpMethod;


/**
 * Created by GustinLau on 2017-05-01.
 */
public enum API {

//  2.1 获取用户
    USER_REGISTER_CAPTCHA_GET("/api/app/v1/user/register/captcha/get"),
//  2.2 注册
    USER_REGISTER("/api/app/v1/user/register"),
//  2.3 登录
    USER_LOGIN("/api/app/v1/user/login"),
//  2.4 获取忘记密码验证码
    USER_PASSWORD_FORGET_CAPTCHA_GET("/api/app/v1/user/password/forget/captcha/get"),
//  2.5 忘记密码
    USER_PASSWORD_FORGET_UPDATE("/api/app/v1/user/password/forget/update"),
//  2.6 获取用户
    USER_GET("/api/app/v1/user/get"),
//  2.7 更新用户
    USER_UPDATE("/api/app/v1/user/update"),
//  2.8 更新用户密码
    USER_PASSWORD_UPDATE("/api/app/v1/user/password/update"),
//  2.9 获取用户会员信息
    USER_VIP_GET("/api/app/v1/user/vip/get"),
//  2.10 检验验证码
    USER_CAPTCHA_VERIFY("/api/app/v1/user/password/forget/captcha/verify"),


//  3.1 获取热门网站
    WEBSITE_HOT_LIST("/api/app/v1/website/hot/list"),
//  3.2 获取网站分类
    WEBSITE_CATEGORY_TREE("/api/app/v1/website/category/tree"),
//  3.3 获取首页推荐网站
    WEBSITE_RECOMMEND_LIST("/api/app/v1/website/recommend/list"),
//  3.4 获取全部网站
    WEBSITE_ALL_LIST("/api/app/v1/website/all/list"),


//  4.1 获取热门公众号
    PUBLICNUMBER_HOT_LIST("/api/app/v1/publicNumber/hot/list"),
//  4.2 获取公众号分类
    PUBLICNUMBER_CATEGORY_TREE("/api/app/v1/publicNumber/category/tree"),
//  4.3 获取首页推荐公众号
    PUBLICNUMBER_RECOMMEND_LIST("/api/app/v1/publicNumber/recommend/list"),
//  4.4 获取全部公众号
    PUBLICNUMBER_ALL_LIST("/api/app/v1/publicNumber/all/list"),


//    5.1 获取热门软件
    APP_HOT_LIST("/api/app/v1/app/hot/list"),
//    5.2 获取软件分类
    APP_CATEGORY_TREE("/api/app/v1/app/category/tree"),
//  5.3 获取首页推荐软件
    APP_RECOMMEND_LIST("/api/app/v1/app/recommend/list"),
//    5.4 获取全部软件
    APP_ALL_LIST("/api/app/v1/app/all/list"),
//    5.5 获取软件
    APP_LIST("/api/app/v1/app/list"),

    //    6.1 获取热门小程序
    MINAPP_HOT_LIST("/api/app/v1/miniApp/hot/list"),
    //    6.2 获取小程序分类
    MINAPP_CATEGORY_TREE("/api/app/v1/miniApp/category/tree"),
    //  6.3 获取首页推荐小程序
    MINAPP_RECOMMEND_LIST("/api/app/v1/miniApp/recommend/list"),
    //    6.4 获取全部小程序
    MINAPP_ALL_LIST("/api/app/v1/miniApp/all/list"),


    //  7.1 获取热门期刊
    PERIODICAL_HOT_LIST("/api/app/v1/periodical/hot/list"),
    //  7.2 获取期刊分类
    PERIODICAL_CATEGORY_TREE("/api/app/v1/periodical/category/tree"),
    //  7.3 获取首页推荐期刊
    PERIODICAL_RECOMMEND_LIST("/api/app/v1/periodical/recommend/list"),
    //  7.4 获取全部期刊
    PERIODICAL_ALL_LIST("/api/app/v1/periodical/all/list"),

//  8.1 获取图片分类
    PICTURE_CATEGORY_COMBO("/api/app/v1/picture/category/combo"),
//  8.2 获取图片分类树
    PICTURE_CATEGORY_TREE("/api/app/v1/picture/category/tree"),
//  8.3 获取图片列表
    PICTURE_LIST("/api/app/v1/picture/list"),
//  8.4 获取图片
    PICTURE_GET("/api/app/v1/picture/get"),
//  8.5 获取图书分类路径
    PICTURE_CATEGORY_PATH("/api/app/v1/picture/category/path"),


//   9.1 获取热门图书
    BOOK_HOT_LIST("/api/app/v1/book/hot/list"),
//   9.2 获取图书分类
    BOOK_CATEGORY_TREE("/api/app/v1/book/category/tree"),
//    9.3 获取首页推荐图书
    BOOK_RECOMMEND_LIST("/api/app/v1/book/recommend/list"),
//   9.4 获取全部图书
    BOOK_ALL_LIST("/api/app/v1/book/all/list"),
//    9.5 获取图书
    BOOK_GET("/api/app/v1/book/get"),
//  9.6 获取图书分类路径
    BOOK_CATEGORY_PATH("/api/app/v1/book/category/path"),


//    10.1 获取热门视频
    VIDEO_HOT_LIST("/api/app/v1/video/hot/list"),
//    10.2 获取视频分类
    VIDEO_CATEGORY_TREE("/api/app/v1/video/category/tree"),
//    10.3 获取首页推荐视频
    VIDEO_RECOMMEND_LIST("/api/app/v1/video/recommend/list"),
//10.4 获取全部视频
    VIDEO_ALL_LIST("/api/app/v1/video/all/list"),
//    10.5 获取视频
    VIDEO_GET("/api/app/v1/video/get"),


//    11.1 获取商品列表
    GOODS_COMBO("/api/app/v1/goods/combo"),

//    12.1 获取文章
    ARTICLE_GET("/api/app/v1/article/get"),
//12.2 获取系统属性
    SYSTEM_PROPERTY_GET("/api/app/v1/system/property/get"),

//    13.1 全站搜索
    RESOURCE_LIST("/api/app/v1/resource/list"),

    ;

    private String url;
    private HttpMethod method;

    API(String url) {
        this.url = url;
        this.method = HttpMethod.POST;
    }

    API(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }

    public String url() {
        return url;
    }

    public HttpMethod method() {
        return method;
    }
}
