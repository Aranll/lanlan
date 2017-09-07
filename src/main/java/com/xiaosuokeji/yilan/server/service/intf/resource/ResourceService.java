package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Resource;

import java.util.List;

/**
 * 资源Service<br/>
 * Created by xuxiaowei on 2017/8/4.
 */
public interface ResourceService {

    /**
     * 获取多个资源<br/>
     * 按分类分组
     * @param resource 可选条件：name(模糊)
     * @return 分类列表，字段：id，name，url，category.id
     */
    List<Category> list(Resource resource);
}
