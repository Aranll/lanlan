<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/10
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="app-aside hidden-xs bg-black">
    <div class="aside-wrap ">
        <div class="navi-wrap">
            <nav class="navi">
                <ul id="a_nav" class="nav">
                    <li>
                        <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.publicNumber_category})">
                        <a href="<%=request.getContextPath()%>/admin/publicNumber/publicNumberCategory">
                            <i class="fa fa-empire"></i>
                            <span>公众号分类</span>
                        </a>
                        </sec:authorize>
                    </li>
                    <li>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.publicNumber_publicNumber})">
                        <a href="<%=request.getContextPath()%>/admin/publicNumber/publicNumber">
                            <i class="fa fa-empire"></i>
                            <span>公众号管理</span>
                        </a>
                    </sec:authorize>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>

