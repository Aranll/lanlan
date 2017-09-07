package com.xiaosuokeji.yilan.admin.enumeration;

import org.springframework.http.HttpMethod;


/**
 * Created by GustinLau on 2017-05-01.
 */
public enum API {

    //2.1 获取用户列表
    USER_LIST("/api/admin/v1/user/list"),
    //2.2获取用户
    USER_GET("/api/admin/v1/user/get"),
    //2.3 更新用户状态
    USER_UPDATE("/api/admin/v1/user/status/update"),

    //region 3 网站管理
    //3.1 保存网站分类
    WEBSITE_CATEGORY_SAVE("/api/admin/v1/website/category/save"),
    //3.2 删除网站分类
    WEBSITE_CATEGORY_REMOVE("/api/admin/v1/website/category/remove"),
    //3.3 更新网站分类
    WEBSITE_CATEGORY_UPDATE("/api/admin/v1/website/category/update"),
    //3.4 获取网站分类
    WEBSITE_CATEGORY_GET("/api/admin/v1/website/category/get"),
    //3.5 获取网站分类列表
    WEBSITE_CATEGORY_LIST("/api/admin/v1/website/category/list"),
    //3.6 获取网站分类组合框
    WEBSITE_CATEGORY_COMBO("/api/admin/v1/website/category/combo"),
    //3.7 获取网站分类树形组合框
    WEBSITE_CATEGORY_COMBO_TREE("/api/admin/v1/website/category/tree"),
    //3.8 保存网站
    WEBSITE_SAVE("/api/admin/v1/website/save"),
    //3.9 删除网站
    WEBSITE_REMOVE("/api/admin/v1/website/remove"),
    // 3.10 更新网站
    WEBSITE_UPDATE("/api/admin/v1/website/update"),
    //3.11 获取网站
    WEBSITE_GET("/api/admin/v1/website/get"),
    //3.12 获取网站列表
    WEBSITE_LIST("/api/admin/v1/website/list"),

    //4.1 保存公众号分类
    PUBLICNUMBER_CATEGORY_SAVE("/api/admin/v1/publicNumber/category/save"),
    //4.2 删除公众号分类
    PUBLICNUMBER_CATEGORY_REMOVE("/api/admin/v1/publicNumber/category/remove"),
    //4.3 更新公众号分类
    PUBLICNUMBER_CATEGORY_UPDATE("/api/admin/v1/publicNumber/category/update"),
    //4.4 获取公众号分类
    PUBLICNUMBER_CATEGORY_GET("/api/admin/v1/publicNumber/category/get"),
    //4.5 获取公众号分类列表
    PUBLICNUMBER_CATEGORY_LIST("/api/admin/v1/publicNumber/category/list"),
    //4.6 获取公众号分类组合框
    PUBLICNUMBER_CATEGORY_COMBO("/api/admin/v1/publicNumber/category/combo"),
    //4.7 获取公众号分类树形组合框
    PUBLICNUMBER_CATEGORY_TREE("/api/admin/v1/publicNumber/category/tree"),
    //4.8 保存公众号
    PUBLICNUMBER_SAVE("/api/admin/v1/publicNumber/save"),
    //4.9 删除公众号
    PUBLICNUMBER_REMOVE("/api/admin/v1/publicNumber/remove"),
    //4.10 更新公众号
    PUBLICNUMBER_UPDATE("/api/admin/v1/publicNumber/update"),
    //4.11 获取公众号
    PUBLICNUMBER_GET("/api/admin/v1/publicNumber/get"),
    //4.12获取公众号列表
    PUBLICNUMBER_LIST("/api/admin/v1/publicNumber/list"),
    //4.13 批量保存公众号
    PUBLICNUMBER_MULTI_SAVE("/api/admin/v1/publicNumber/multi/save"),
        //5.1保存软件分类
    APP_CATEGORY_SAVE("/api/admin/v1/app/category/save"),
    //5.2 删除软件分类
    APP_CATEGORY_REMOVE("/api/admin/v1/app/category/remove"),
    //5.3 更新软件分类
    APP_CATEGORY_UPDATE("/api/admin/v1/app/category/update"),
    //5.4 获取软件分类
    APP_CATEGORY_GET("/api/admin/v1/app/category/get"),
    //5.5 获取软件分类列表
    APP_CATEGORY_LIST("/api/admin/v1/app/category/list"),
    //5.6 获取软件分类组合框
    APP_CATEGORY_COMBO("/api/admin/v1/app/category/combo"),
    //5.7 获取软件分类树形组合框
    APP_CATEGORY_TREE("/api/admin/v1/app/category/tree"),
    //5.8 保存软件
    APP_SAVE("/api/admin/v1/app/save"),
    //5.9 删除软件
    APP_REMOVE("/api/admin/v1/app/remove"),
    //5.10 更新软件
    APP_UPDATE("/api/admin/v1/app/update"),
    //5.11 获取软件
    APP_GET("/api/admin/v1/app/get"),
    //5.12 获取软件列表
    APP_LIST("/api/admin/v1/app/list"),

    //6.1 保存小程序分类
    MINIAPP_CATEGORY_SAVE("/api/admin/v1/miniApp/category/save"),
    // 6.2 删除小程序分类
    MINIAPP_CATEGORY_REMOVE("/api/admin/v1/miniApp/category/remove"),
    //6.3 更新小程序分类
    MINIAPP_CATEGORY_UPDATE("/api/admin/v1/miniApp/category/update"),
    //6.4 获取小程序分类
    MINIAPP_CATEGORY_GET("/api/admin/v1/miniApp/category/get"),
    // 6.5 获取小程序分类列表
    MINIAPP_CATEGORY_LIST("/api/admin/v1/miniApp/category/list"),
    // 6.6 获取小程序分类组合框
    MINIAPP_CATEGORY_COMBO("/api/admin/v1/miniApp/category/combo"),
    // 6.7 获取小程序分类树形组合框
    MINIAPP_CATEGORY_TREE("/api/admin/v1/miniApp/category/tree"),
    // 6.8 保存小程序
    MINIAPP_SAVE("/api/admin/v1/miniApp/save"),
    // 6.9 删除小程序
    MINIAPP_REMOVE("/api/admin/v1/miniApp/remove"),
    // 6.10 更新小程序
    MINIAPP_UPDATE("/api/admin/v1/miniApp/update"),
    // 6.11 获取小程序
    MINIAPP_GET("/api/admin/v1/miniApp/get"),
    // 6.12 获取小程序列表
    MINIAPP_LIST("/api/admin/v1/miniApp/list"),

    //7.1 保存期刊分类
    PERIODICAL_CATEGORY_SAVE("/api/admin/v1/periodical/category/save"),
    //7.2 删除期刊分类
    PERIODICAL_CATEGORY_REMOVE("/api/admin/v1/periodical/category/remove"),
    //7.3 更新期刊分类
    PERIODICAL_CATEGORY_UPDATE("/api/admin/v1/periodical/category/update"),
    //7.4 获取期刊分类
    PERIODICAL_CATEGORY_GET("/api/admin/v1/periodical/category/get"),
    //7.5 获取期刊分类列表
    PERIODICAL_CATEGORY_LIST("/api/admin/v1/periodical/category/list"),
    //7.6 获取期刊分类组合框
    PERIODICAL_CATEGORY_COMBO("/api/admin/v1/periodical/category/combo"),
    //7.7 获取期刊分类树形组合框
    PERIODICAL_CATEGORY_TREE("/api/admin/v1/periodical/category/tree"),
    //7.8 保存期刊
    PERIODICAL_SAVE("/api/admin/v1/periodical/save"),
    //7.9 删除期刊
    PERIODICAL_REMOVE("/api/admin/v1/periodical/remove"),
    //7.10 更新期刊
    PERIODICAL_UPDATE("/api/admin/v1/periodical/update"),
    //7.11 获取期刊
    PERIODICAL_GET("/api/admin/v1/periodical/get"),
    //7.12 获取期刊列表
    PERIODICAL_LIST("/api/admin/v1/periodical/list"),

    //8.1 保存图片分类
    PICTURE_CATEGORY_SAVE("/api/admin/v1/picture/category/save"),
    //8.2 删除图片分类
    PICTURE_CATEGORY_REMOVE("/api/admin/v1/picture/category/remove"),
    //8.3 更新图片分类
    PICTURE_CATEGORY_UPDATE("/api/admin/v1/picture/category/update"),
    //8.4 获取图片分类
    PICTURE_CATEGORY_GET("/api/admin/v1/picture/category/get"),
    //8.5 获取图片分类列表
    PICTURE_CATEGORY_LIST("/api/admin/v1/picture/category/list"),
    //8.6 获取图片分类组合框
    PICTURE_CATEGORY_COMBO("/api/admin/v1/picture/category/combo"),
    //8.7 获取图片分类树形组合框
    PICTURE_CATEGORY_TREE("/api/admin/v1/picture/category/tree"),
    //8.8 保存图片
    PICTURE_SAVE("/api/admin/v1/picture/save"),
    //8.9 删除图片
    PICTURE_REMOVE("/api/admin/v1/picture/remove"),
    //8.10 更新图片
    PICTURE_UPDATE("/api/admin/v1/picture/update"),
    //8.11 获取图片
    PICTURE_GET("/api/admin/v1/picture/get"),
    //8.12 获取图片列表
    PICTURE_LIST("/api/admin/v1/picture/list"),

    //9.1 保存图书分类
    BOOK_CATEGORY_SAVE("/api/admin/v1/book/category/save"),
    //9.2 删除图书分类
    BOOK_CATEGORY_REMOVE("/api/admin/v1/book/category/remove"),
    //9.3 更新图书分类
    BOOK_CATEGORY_UPDATE("/api/admin/v1/book/category/update"),
    //9.4 获取图书分类
    BOOK_CATEGORY_GET("/api/admin/v1/book/category/get"),
    //9.5 获取图书分类列表
    BOOK_CATEGORY_LIST("/api/admin/v1/book/category/list"),
    //9.6 获取图书分类组合框
    BOOK_CATEGORY_COMBO("/api/admin/v1/book/category/combo"),
    //9.7 获取图书分类树形组合框
    BOOK_CATEGORY_TREE("/api/admin/v1/book/category/tree"),
    //9.8 保存图书
    BOOK_SAVE("/api/admin/v1/book/save"),
    //9.9 删除图书
    BOOK_REMOVE("/api/admin/v1/book/remove"),
    //9.10 更新图书
    BOOK_UPDATE("/api/admin/v1/book/update"),
    //9.11 获取图书
    BOOK_GET("/api/admin/v1/book/get"),
    //9.12 获取图书列表
    BOOK_LIST("/api/admin/v1/book/list"),

    //10.1 保存视频分类
    VIDEO_CATEGORY_SAVE("/api/admin/v1/video/category/save"),
    //10.2 删除视频分类
    VIDEO_CATEGORY_REMOVE("/api/admin/v1/video/category/remove"),
    //10.3 更新视频分类
    VIDEO_CATEGORY_UPDATE("/api/admin/v1/video/category/update"),
    //10.4 获取视频分类
    VIDEO_CATEGORY_GET("/api/admin/v1/video/category/get"),
    //10.5 获取视频分类列表
    VIDEO_CATEGORY_LIST("/api/admin/v1/video/category/list"),
    //10.6 获取视频分类组合框
    VIDEO_CATEGORY_COMBO("/api/admin/v1/video/category/combo"),
    //10.7 获取视频分类树形组合框
    VIDEO_CATEGORY_TREE("/api/admin/v1/video/category/tree"),
    //10.8 保存视频
    VIDEO_SAVE("/api/admin/v1/video/save"),
    //10.9 删除视频
    VIDEO_REMOVE("/api/admin/v1/video/remove"),
    //10.10 更新视频
    VIDEO_UPDATE("/api/admin/v1/video/update"),
    //10.13 视频获取
    VIDEO_GET("/api/admin/v1/video/get"),
    //10.12 获取视频列表
    VIDEO_LIST("/api/admin/v1/video/list"),

    //11.1 保存商品
    GOODS_SAVE("/api/admin/v1/goods/save"),
    //11.2 删除商品
    GOODS_REMOVE("/api/admin/v1/goods/remove"),
    //11.3 更新商品
    GOODS_UPDATE("/api/admin/v1/goods/update"),
    //11.4 获取商品
    GOODS_GET("/api/admin/v1/goods/get"),
    //11.5 获取商品列表
    GOODS_LIST("/api/admin/v1/goods/list"),





    //12.1 系统日志列表
    SECURITY_OPERATIONLOG_LIST("/api/admin/v1/security/operationLog/list"),
    //12.2 新增文章
    ARTICLE_SAVE("/api/admin/v1/article/save"),
    //12.3 文章列表
    ARTICLE_LIST("/api/admin/v1/article/list"),
    //12.4 文章详情
    ARTICLE_GET("/api/admin/v1/article/get"),
    //12.5 更新文章
    ARTICLE_UPDATE("/api/admin/v1/article/update"),
    //12.6 删除文章
    ARTICLE_REMOVE("/api/admin/v1/article/remove"),
    //12.7 保存系统属性
    SYSTEM_PROPERTY_SAVE("/api/admin/v1/system/property/save"),
    //12.8 获取系统属性
    SYSTEM_PROPERTY_GET("/api/admin/v1/system/property/get"),



        //bulkio 98 批量导入
    //98.1 批量导入网站
    BULK_WEBSITE_IMPORT("/api/admin/v1/website/multi/save"),
    BULK_APP_IMPORT("/api/admin/v1/app/multi/save"),
    BULK_PERIODICAL_IMPORT("/api/admin/v1/periodical/multi/save"),
    BULK_MINIAPP_IMPORT("/api/admin/v1/miniApp/multi/save"),
    BULK_VIDEO_IMPORT("/api/admin/v1/video/multi/save"),
    BULK_PICTURE_IMPORT("/api/admin/v1/picture/multi/save"),
    BULK_BOOK_IMPORT("/api/admin/v1/book/multi/save"),

    //99 安全
    //99.1 新增资源
    SECURITY_RESOURCE_CREATE("/api/admin/v1/security/resource/create"),
    //99.2 资源列表
    SECURITY_RESOURCE_LIST("/api/admin/v1/security/resource/list"),
    //99.3 资源详情
    SECURITY_RESOURCE_FIND("/api/admin/v1/security/resource/find"),
    //99.4 更新资源
    SECURITY_RESOURCE_UPDATE("/api/admin/v1/security/resource/update"),
    //99.5 删除资源
    SECURITY_RESOURCE_DELETE("/api/admin/v1/security/resource/delete"),
    //99.6 资源树
    SECURITY_RESOURCE_TREE("/api/admin/v1/security/resource/tree"),
    //99.7 新增角色
    SECURITY_ROLE_CREATE("/api/admin/v1/security/role/create"),
    //99.8 角色列表
    SECURITY_ROLE_LIST("/api/admin/v1/security/role/list"),
    //99.9 角色详情
    SECURITY_ROLE_FIND("/api/admin/v1/security/role/find"),
    //99.10 更新角色
    SECURITY_ROLE_UPDATE("/api/admin/v1/security/role/update"),
    //99.11 删除角色
    SECURITY_ROLE_DELETE("/api/admin/v1/security/role/delete"),
    //99.12 角色键值对
    SECURITY_ROLE_PAIR("/api/admin/v1/security/role/pair"),
    //99.13 角色资源树
    SECURITY_ROLE_RES_TREE("/api/admin/v1/security/role/res/tree"),
    //99.14 角色授权
    SECURITY_ROLE_RES_AUTHORIZE("/api/admin/v1/security/role/res/authorize"),
    //99.15 新增员工
    SECURITY_STAFF_CREATE("/api/admin/v1/security/staff/create"),
    //99.16 员工列表
    SECURITY_STAFF_LIST("/api/admin/v1/security/staff/list"),
    //99.17 员工详情
    SECURITY_STAFF_FIND("/api/admin/v1/security/staff/find"),
    //99.18 更新员工
    SECURITY_STAFF_UPDATE("/api/admin/v1/security/staff/update"),
    //99.19 删除员工
    SECURITY_STAFF_DELETE("/api/admin/v1/security/staff/delete"),
    //99.20 员工的角色键值对
    SECURITY_STAFF_ROLE_PAIR("/api/admin/v1/security/staff/role/pair"),
    //99.21 员工授权
    SECURITY_STAFF_ROLE_AUTHORIZE("/api/admin/v1/security/staff/role/authorize"),
    //99.22 新增组织
    SECURITY_ORGANIZATION_CREATE("/api/admin/v1/security/organization/create"),
    //99.23 组织列表
    SECURITY_ORGANIZATION_LIST("/api/admin/v1/security/organization/list"),
    //99.24 组织详情
    SECURITY_ORGANIZATION_FIND("/api/admin/v1/security/organization/find"),
    //99.25 更新组织
    SECURITY_ORGANIZATION_UPDATE("/api/admin/v1/security/organization/update"),
    //99.26 删除组织
    SECURITY_ORGANIZATION_DELETE("/api/admin/v1/security/organization/delete"),
    //99.27 组织树
    SECURITY_ORGANIZATION_TREE("/api/admin/v1/security/organization/tree"),
    //99.28 组织的角色键值对
    SECURITY_ORGANIZATION_ROLE_PAIR("/api/admin/v1/security/organization/role/pair"),
    //99.29 组织授权
    SECURITY_ORGANIZATION_ROLE_AUTHORIZE("/api/admin/v1/security/organization/role/authorize"),
    //99.30 员工的组织树
    SECURITY_STAFF_ORG_TREE("/api/admin/v1/security/staff/org/tree"),
    //99.31 加入组织
    SECURITY_STAFF_ORG_JOIN("/api/admin/v1/security/staff/org/join"),
    //99.32 员工登录需要的信息
    SECURITY_LOGIN("/api/admin/v1/security/login"),
    //99.33 查询请求需要的角色
    SECURITY_ROLEBSEQ("/api/admin/v1/security/roleByReq"),
    //99.34 根据父键查询子资源及相应角色
    SECURITY_ROLEBYRES("/api/admin/v1/security/roleByRes")
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
