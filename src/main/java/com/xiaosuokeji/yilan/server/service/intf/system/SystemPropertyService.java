package com.xiaosuokeji.yilan.server.service.intf.system;

import com.xiaosuokeji.yilan.server.model.system.SystemProperty;

/**
 * 系统属性Service<br/>
 * Created by xuxiaowei on 2017/8/14.
 */
public interface SystemPropertyService {

    /**
     * 保存系统属性
     * @param systemProperty 可选保存字段：webName，miniAppImageList，miniAppNameList，email，address，phone，qq，copyright，
     *                       recordNumber，license，aboutUs，partnerList，partnerUrlList
     */
    void save(SystemProperty systemProperty);

    /**
     * 获取系统属性
     * @return 系统属性，字段：所有
     */
    SystemProperty get();
}
