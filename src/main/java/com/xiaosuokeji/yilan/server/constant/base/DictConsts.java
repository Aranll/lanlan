package com.xiaosuokeji.yilan.server.constant.base;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 字典常量<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
public class DictConsts {

    public static final StatusPair DICT_EXIST = StatusPair.build(1007, "字典已存在");

    public static final StatusPair DICT_USED = StatusPair.build(1008, "字典已被使用");

    public static final StatusPair DICTDATA_EXIST = StatusPair.build(1009, "字典数据项已存在");

    public static final StatusPair DICTDATA_FIXED = StatusPair.build(1010, "字典数据项已固定");
}
