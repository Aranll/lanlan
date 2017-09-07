<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>医览网</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>

<div class="yilan-center clearfix">
    <%@include file="../common/search.jsp" %>

    <div class="yilan-hot" style="margin-top: 35px">
        <span class="soft-span">热门软件</span>
        <c:forEach items="${appHot}" var="h" varStatus="i">
            <c:if test="${i.index lt 8}">
                <div class="soft-hot" style="position: relative">
                    <span>${h.name}</span>
                    <div class="soft-hot-img soft-hot-hidden">
                        <img src="${h.qrCode}">
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="yilan-website ">
        <span style="display: block;">医学软件</span>
        <div class="left-nav">
            <ul class="nav" style="position: relative">
                <c:forEach items="${appCategories}" var="h">
                    <li class="nav-li">
                        <a>${h.name}</a>
                        <div class="nav-navbar nav-navbar-hidden">
                            <ul class="nav">
                                <c:forEach items="${h.children}" var="c">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/app/all?id=${c.id}">${c.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <c:forEach items="${appRecommends}" var="p" varStatus="i">
            <div class="yilan-recommend-soft" style="margin-top:
            <c:choose>
            <c:when test="${i.index eq 0}">10px</c:when>
            <c:otherwise>40px</c:otherwise>
            </c:choose>;margin-left: 20px;float: right">
                <span style="margin-left: 0;font-size: 18px;color: #33AC79">${p.name}</span>
                <a class="recommend-last" href="<%=request.getContextPath()%>/app/all?id=${p.id}">更多>></a>
                <HR class="recommend-hr"/>
                <div>
                    <c:forEach items="${p.resources}" var="r">
                        <div class="soft-codesmall" style="position: relative">
                            <img style="width: 150px;height: 150px" src="${r.qrCode}">
                            <div class="soft-detail soft-detail-hidden">
                                <span>开发商:${r.name}</span>
                            </div>
                            <span style=" width: 150px;text-align: center;height: 50px;overflow: hidden;
                                text-overflow: ellipsis;">${r.name}</span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="yilan-hot" style="margin-top: 40px;clear: both">
        <span class="soft-span">热门小程序</span>
        <c:forEach items="${miniAppHot}" var="h" varStatus="i">
            <c:if test="${i.index lt 9}">
                <div class="soft-hot" style="position: relative">
                    <span>${h.name}</span>
                    <div class="soft-hot-img soft-hot-hidden">
                        <img src="${h.qrCode}">
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="yilan-website ">
        <span style="display: block;">医学小程序</span>
        <div class="left-nav">
            <ul class="nav" style="position: relative">
                <c:forEach items="${miniAppCategories}" var="h">
                    <li class="nav-li">
                        <a>${h.name}</a>
                        <div class="nav-navbar nav-navbar-hidden">
                            <ul class="nav">
                                <c:forEach items="${h.children}" var="c">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/miniapp/all?id=${c.id}">${c.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <c:forEach items="${miniAppRecommends}" var="p" varStatus="i">
            <div class="yilan-recommend-soft" style="margin-top:
            <c:choose>
            <c:when test="${i.index eq 0}">10px</c:when>
            <c:otherwise>40px</c:otherwise>
            </c:choose>;margin-left: 20px;float: right">
                <span style="margin-left: 0;font-size: 18px;color: #33AC79">${p.name}</span>
                <a class="recommend-last" href="<%=request.getContextPath()%>/miniapp/all?id=${p.id}">更多>></a>
                <HR class="recommend-hr"/>
                <div>
                    <c:forEach items="${p.resources}" var="r">
                        <div class="soft-codesmall" style="position: relative">
                            <img style="width: 150px;height: 150px" src="${r.qrCode}">
                            <div class="soft-detail soft-detail-hidden">
                                <span>开发商:${r.name}</span>
                            </div>
                            <span style=" width: 150px;text-align: center;height: 50px;overflow: hidden;
                                text-overflow: ellipsis;">${r.name}</span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>

</body>
</html>