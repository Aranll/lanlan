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
        <div class="navi-wrap">
            <nav class="navi">
                <ul id="a_nav" class="nav">
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_article})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/article">
                            <i class=" icon-book-open"></i>
                            <span>文章管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_property})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/property">
                            <i class=" icon-equalizer"></i>
                            <span>系统设置</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_operationLog})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/operationLog">
                            <i class=" icon-equalizer"></i>
                            <span>系统日志</span>
                        </a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_resource})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/resource">
                            <i class="icon-link"></i>
                            <span>资源管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_role})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/role">
                            <i class="icon-ghost"></i>
                            <span>角色管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_org})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/organization">
                            <i class="fa fa-sitemap"></i>
                            <span>组织管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_staff})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/staff">
                            <i class="icon-users"></i>
                            <span>员工管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole(${sessionScope.sec_left_menu.system_goods})">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/system/security/goods">
                            <i class="icon-users"></i>
                            <span>会员管理</span>
                        </a>
                    </li>
                    </sec:authorize>
                </ul>
            </nav>
        </div>
    </div>
</div>