<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/7
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="app-aside hidden-xs bg-black">
    <div class="aside-wrap ">
        <div class="navi-wrap">
            <nav class="navi">
                <ul id="a_nav" class="nav">
                    <li>
                        <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.website_category})">
                        <a href="<%=request.getContextPath()%>/admin/website/websiteCategory/">
                            <i class="icon-flag"></i>
                            <span>网站分类</span>
                        </a>
                        </sec:authorize>
                    </li>
                    <li>
                        <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.website_website})">
                        <a href="<%=request.getContextPath()%>/admin/website/website/">
                            <i class="fa fa-empire"></i>
                            <span>网站管理</span>
                        </a>
                        </sec:authorize>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
