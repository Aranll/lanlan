package com.xiaosuokeji.yilan.server.constant.system;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 文章常量<br/>
 * Created by xuxiaowei on 2017/8/12.
 */
public class ArticleConsts {

    public static final StatusPair ARTICLE_EXIST = StatusPair.build(1042, "文章已存在");

    public static final StatusPair ARTICLE_NOT_EXIST = StatusPair.build(1043, "文章不存在");
}
