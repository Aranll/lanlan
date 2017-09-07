package com.xiaosuokeji.yilan.server.constant.resource;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 分类常量<br/>
 * Created by xuxiaowei on 2017/7/25.
 */
public class CategoryConsts {

    /**
     * 网站
     */
    public static final Integer WEBSITE = 0;

    /**
     * 公众号
     */
    public static final Integer PUBLIC_NUMBER = 1;

    /**
     * 软件
     */
    public static final Integer APP = 2;

    /**
     * 小程序
     */
    public static final Integer MINI_APP = 3;

    /**
     * 期刊
     */
    public static final Integer PERIODICAL = 4;

    /**
     * 图片
     */
    public static final Integer PICTURE = 5;

    /**
     * 图书
     */
    public static final Integer BOOK = 6;

    /**
     * 视频
     */
    public static final Integer VIDEO = 7;

    public static final StatusPair CATEGORY_EXIST = StatusPair.build(1011, "分类已存在");

    public static final StatusPair CATEGORY_USED = StatusPair.build(1012, "分类已被使用");

    public static final StatusPair CATEGORY_NOT_EXIST = StatusPair.build(1020, "分类不存在");
}
