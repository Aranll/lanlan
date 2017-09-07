package com.xiaosuokeji.yilan.server.dao.system;

import com.xiaosuokeji.yilan.server.model.system.SystemProperty;

/**
 * 系统属性Dao<br/>
 * Created by xuxiaowei on 2017/8/13.
 */
public interface SystemPropertyDao {

    /**
     * 保存系统属性
     * @param systemProperty 可选保存字段：webName，miniAppImages，miniAppNames，email，address，phone，qq，copyright，
     *                       recordNumber，license，aboutUs，partners，partnerUrls
     * @return 受影响行数
     */
    int save(SystemProperty systemProperty);

    /**
     * 获取系统属性
     * @return 系统属性，字段：所有
     */
    SystemProperty get();
}
