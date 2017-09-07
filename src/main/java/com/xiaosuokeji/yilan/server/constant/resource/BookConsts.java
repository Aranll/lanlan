package com.xiaosuokeji.yilan.server.constant.resource;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 图书常量<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public class BookConsts {

    public static final StatusPair BOOK_EXIST = StatusPair.build(1027, "图书已存在");

    public static final StatusPair BOOK_NOT_EXIST = StatusPair.build(1028, "图书不存在");
}
