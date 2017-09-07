package com.xiaosuokeji.yilan.server.service.impl.system;

import com.xiaosuokeji.framework.xsjframework.util.ConvertUtil;
import com.xiaosuokeji.yilan.server.dao.system.SystemPropertyDao;
import com.xiaosuokeji.yilan.server.model.system.SystemProperty;
import com.xiaosuokeji.yilan.server.service.intf.system.SystemPropertyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统属性ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/14.
 */
@Service
public class SystemPropertyServiceImpl implements SystemPropertyService {

    @Autowired
    private SystemPropertyDao systemPropertyDao;

    @Override
    public void save(SystemProperty systemProperty) {
        systemProperty.setMiniAppQrcodes(ConvertUtil.listToStr(systemProperty.getMiniAppQrcodeList(), "|"));
        systemProperty.setMiniAppNames(ConvertUtil.listToStr(systemProperty.getMiniAppNameList(), "|"));
        systemProperty.setPartners(ConvertUtil.listToStr(systemProperty.getPartnerList(), "|"));
        systemProperty.setPartnerUrls(ConvertUtil.listToStr(systemProperty.getPartnerUrlList(), "|"));
        systemPropertyDao.save(systemProperty);
    }

    @Override
    public SystemProperty get() {
        SystemProperty existent = systemPropertyDao.get();
        existent.setMiniAppQrcodeList(ConvertUtil.strToList(existent.getMiniAppQrcodes(), "\\|"));
        existent.setMiniAppNameList(ConvertUtil.strToList(existent.getMiniAppNames(), "\\|"));
        existent.setPartnerList(ConvertUtil.strToList(existent.getPartners(), "\\|"));
        existent.setPartnerUrlList(ConvertUtil.strToList(existent.getPartnerUrls(), "\\|"));
        return existent;
    }
}
