<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.picture_category})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/picture/pictureCategory">
                            <i class="icon-flag"></i>
                            <span>图片分类</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.picture_picture})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/picture/picture">
                            <i class="fa fa-empire"></i>
                            <span>图片管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                </ul>
            </nav>
        </div>
    </div>
</div>