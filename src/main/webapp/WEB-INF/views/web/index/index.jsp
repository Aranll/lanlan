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
    <div class="yilan-hot">
        <span>热门网站</span>
        <c:forEach items="${hotWebsites}" var="h" varStatus="i">
            <c:if test="${i.index lt 8}">
                <a href="${h.url}" title="${h.name}----${h.developer}" target="_blank">${h.name}</a>
            </c:if>
        </c:forEach>
    </div>
    <div class="yilan-website ">
        <span style="display: block;">医学网站</span>
        <div class="left-nav">
            <ul class="nav" style="position: relative">
                <c:forEach items="${websiteCategories}" var="h">
                    <li class="nav-li">
                        <a>${h.name}</a>
                        <div class="nav-navbar nav-navbar-hidden">
                            <ul class="nav">
                                <c:forEach items="${h.children}" var="c">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/website/all?id=${c.id}">${c.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="yilan-recommend" style="margin-top: 10px">
            <c:forEach items="${websiteRecommends}" var="w">
                <div>
                    <span style="display: inline-block">${w.name}</span>
                    <div class="hot-websites" style="display:inline-block;">
                        <c:forEach items="${w.resources}" var="r">
                            <a href="${r.url}" title="${r.name}----${r.developer}" target="_blank">${r.name}</a>
                        </c:forEach>
                    </div>
                    <a class="recommend-last" href="<%=request.getContextPath()%>/website/all?id=${w.id}">更多>></a>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="yilan-hot" style="margin-top: 40px;">
        <span class="soft-span">热门公众号</span>
        <c:forEach items="${hotPublicNumbers}" var="h" varStatus="i">
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
        <span style="display: block;">医学微信公众号</span>
        <div class="left-nav">
            <ul class="nav" style="position: relative">
                <c:forEach items="${publishNumberCategories}" var="h">
                    <li class="nav-li">
                        <a>${h.name}</a>
                        <div class="nav-navbar nav-navbar-hidden">
                            <ul class="nav">
                                <c:forEach items="${h.children}" var="c">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/publicNumber/all?id=${c.id}">${c.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <c:forEach items="${publishNumberRecommend}" var="p" varStatus="i">
            <div class="yilan-recommend-soft" style="margin-top:
            <c:choose>
            <c:when test="${i.index eq 0}">10px</c:when>
            <c:otherwise>40px</c:otherwise>
            </c:choose>;margin-left: 20px;float: right">
                <span style="margin-left: 0;font-size: 18px;color: #33AC79">${p.name}</span>
                <a class="recommend-last" href="<%=request.getContextPath()%>/publicNumber/all?id=${p.id}">更多>></a>
                <HR class="recommend-hr"/>
                <div>
                    <c:forEach items="${p.resources}" var="r">
                        <div class="soft-codesmall" style="position: relative">
                            <img style="width: 150px;height: 150px" src="${r.qrCode}">
                            <div class="soft-detail soft-detail-hidden">
                                <span>开发商:${r.name}</span>
                            </div>
                            <span>${r.name}</span>
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