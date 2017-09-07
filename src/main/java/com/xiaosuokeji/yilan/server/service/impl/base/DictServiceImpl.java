package com.xiaosuokeji.yilan.server.service.impl.base;

import com.xiaosuokeji.framework.xsjframework.provider.XSDictDataProvider;
import com.xiaosuokeji.yilan.server.dao.base.DictDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class DictServiceImpl implements XSDictDataProvider {

	@Autowired
	private DictDao dictDao;

	@Override
	public String getDesc(String key, String value) {
		return dictDao.getDataDesc(key, value);
	}
}