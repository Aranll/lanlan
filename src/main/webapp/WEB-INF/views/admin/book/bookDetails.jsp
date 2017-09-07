<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: GustinLau
  Date: 2017-05-02
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-图书-图书管理-图书详情</title>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/lightbox.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./book_nav.jsp" %>

<sec:authorize access="hasAnyRole(${sessionScope.sec_op.book_book_get})">
<div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3 inline">
                    图书管理 > 图书详情
                </h1>
                <a class="btn btn-default pull-right" style="margin-top: -3px" href="${redirectUrl}">
                    返回
                </a>
            </div>

            <div class="wrapper-md">
                <div class="panel panel-default m-b-none">
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            书名
                        </label>
                        <label class="pull-right">
                            ${book.name}
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            作者
                        </label>
                        <label class="pull-right">
                            ${book.author}
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            出版社
                        </label>
                        <label class="pull-right">
                            ${book.publisher}
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            出版时间
                        </label>
                        <label class="pull-right">
                            ${book.publishDate}
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            热门
                        </label>
                        <label class="pull-right">
                            <c:if test="${book.hot eq 1}">是</c:if>
                            <c:if test="${book.hot eq 0}">否</c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            首页推荐
                        </label>
                        <label class="pull-right">
                            <c:if test="${book.recommend eq 1}">是</c:if>
                            <c:if test="${book.recommend eq 0}">否</c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            所属
                        </label>
                        <label class="pull-right">
                            ${book.category.name}
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            VIP资源
                        </label>
                        <label class="pull-right">
                            <c:if test="${book.accessVipLevel eq 1}">是</c:if>
                            <c:if test="${book.accessVipLevel eq 0}">否</c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            封面
                        </label>
                        <label style="margin-left: 20px">
                            <c:if test="${news.image ne null && news.image ne ''}">
                                <a class="block text-center" href='${news.image}' data-lightbox='detailsBox'>
                                    <img src='${news.image}' style="max-height: 80px;max-width: 350px"/>
                                </a>
                            </c:if>
                            <c:if test="${news.image eq null || news.image eq ''}">
                                无
                            </c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            PDF
                        </label>
                        <label style="margin-left: 20px">
                            <c:if test="${book.pdf ne null && book.pdf ne ''}">
                                <a href="${book.pdf}">${book.pdf}</a>
                            </c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-12">
                                <label class="font-bold">
                                    简介
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <div>
                            ${book.profile}
                        </div>
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
