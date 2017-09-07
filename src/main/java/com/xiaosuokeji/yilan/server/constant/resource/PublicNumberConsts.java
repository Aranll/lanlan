package com.xiaosuokeji.yilan.server.constant.resource;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 公众号常量<br/>
 * Created by xuxiaowei on 2017/8/3.
 */
public class PublicNumberConsts {

    public static final StatusPair PUBLICNUMBER_EXIST = StatusPair.build(1014, "公众号已存在");

    public static final StatusPair PUBLICNUMBER_NOT_EXIST = StatusPair.build(1024, "公众号不存在");
}
