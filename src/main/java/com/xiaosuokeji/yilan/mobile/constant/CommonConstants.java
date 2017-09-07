package com.xiaosuokeji.yilan.mobile.constant;


import com.xiaosuokeji.framework.xsjframework.spring.SpringAppContext;
import com.xiaosuokeji.yilan.admin.bean.AdminBean;

/**
 * Created by GustinLau on 2017-05-02.
 */
public class CommonConstants {

    private static final AdminBean adminBean = SpringAppContext.getBean("adminBean");

    public static final String ADMIN_DOMAIN = adminBean.getDomain();


}
