<%--
  Created by IntelliJ IDEA.
  User: GustinLau
  Date: 2017-05-02
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="app-aside hidden-xs bg-black">
    <div class="aside-wrap ">
        <div class="nav-wrap">
            <nav class="navi">
                <ul id="a_navi" class="nav">
                    <li>
                        <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.user_user})">
                        <a href="<%=request.getContextPath()%>/admin/user/user">
                            <i class="icon-user"></i>
                            <span>用户管理</span>
                        </a>
                        </sec:authorize>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>