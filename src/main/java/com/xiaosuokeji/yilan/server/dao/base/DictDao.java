package com.xiaosuokeji.yilan.server.dao.base;

import org.apache.ibatis.annotations.Param;

/**
 * 字典Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface DictDao {

	/**
	 * 获取单个字典数据项描述
	 * @param key 字典值
	 * @param value 字典数据项值
	 * @return 字典数据项描述
	 */
	String getDataDesc(@Param("key") String key, @Param("value") String value);
}