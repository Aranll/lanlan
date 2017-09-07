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
    <title>医览网-讲坛-讲坛管理-讲坛详情</title>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/lightbox.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./video_nav.jsp" %>

    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3 inline">
                    视频管理 > 视频详情
                </h1>
                <a class="btn btn-default pull-right" style="margin-top: -3px" href="${redirectUrl}">
                    返回
                </a>
            </div>

            <div class="wrapper-md">
                <div class="panel panel-default m-b-none">
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            名称
                        </label>
                        <label class="pull-right">
                            ${video.name}
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            作者
                        </label>
                        <label class="pull-right">
                            ${video.author}
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            热门
                        </label>
                        <label class="pull-right">
                            <c:if test="${video.hot eq 1}">是</c:if>
                            <c:if test="${video.hot eq 0}">否</c:if>
                        </label>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            首页推荐
                        </label>
                        <label class="pull-right">
                            <c:if test="${video.recommend eq 1}">是</c:if>
                            <c:if test="${video.recommend eq 0}">否</c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            所属
                        </label>
                        <label class="pull-right">
                            ${video.category.name}
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            VIP资源
                        </label>
                        <label class="pull-right">
                            <c:if test="${video.accessVipLevel eq 1}">是</c:if>
                            <c:if test="${video.accessVipLevel eq 0}">否</c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            来源
                        </label>
                        <label class="pull-right">
                            <c:if test="${video.accessVipLevel eq 1}">外站</c:if>
                            <c:if test="${video.accessVipLevel eq 0}">本站</c:if>
                        </label>
                    </div>

                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <label class="font-bold">
                            URL
                        </label>
                        <label style="margin-left: 20px">
                            <c:if test="${video.url ne null && video.url ne ''}">
                                <a href="${video.url}">${video.url}</a>
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
                            ${video.profile}
                        </div>
                    </div>
                    <div class="clearfix">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
