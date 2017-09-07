<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/26
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-系统管理-文章管理-新增文章</title>
    <%@include file="../../common/head.jsp" %>
    <%@include file="../../common/validate.jsp" %>
    <%@include file="../../common/ueditor.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../../common/header.jsp" %>
    <%@include file="../system_nav.jsp" %>

<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_get})">
<div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3 inline">
                    文章管理 > 文章预览
                </h1>
                <a class="btn btn-default pull-right" style="margin-top: -3px" href="${redirectUrl}">
                    返回
                </a>
            </div>
            <div class="wrapper-md">
                <div class="panel panel-default m-b-none">
                    <div class="col-xs-24 col-md-12 b  line-dashed wrapper-sm padder">
                        <h3 style="text-align: center">
                            ${article.title}
                        </h3>
                    </div>
                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <article class="wrapper-sm padder">
                            ${article.content}
                        </article>
                    </div>
                    <div class="clearfix">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</sec:authorize>
</body>
</html>

