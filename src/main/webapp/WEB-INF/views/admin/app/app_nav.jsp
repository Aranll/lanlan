<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/10
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="app-aside hidden-xs bg-black">
    <div class="aside-wrap ">
        <div class="navi-wrap">
            <nav class="navi">
                <ul id="a_nav" class="nav">
                    <li>
                        <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.app_category})">
                        <a href="<%=request.getContextPath()%>/admin/app/appCategory">
                            <i class="icon-flag"></i>
                            <span>APP分类</span>
                        </a>
                        </sec:authorize>
                    </li>
                    <li>
                        <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.app_app})">
                        <a href="<%=request.getContextPath()%>/admin/app/app">
                            <i class="fa fa-empire"></i>
                            <span>APP管理</span>
                        </a>
                        </sec:authorize>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>