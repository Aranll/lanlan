<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
</div>
<HR class="search-hr"/>
<div class="yilan-center clearfix">
    <div class="search-category">
        <span>分类</span>
        <a onclick="scrollDiv('website')">医学网站</a>
        <a onclick="scrollDiv('periodical')">医学期刊</a>
        <a onclick="scrollDiv('publicNumber')">医学公众号</a>
        <a onclick="scrollDiv('app')">医学软件</a>
        <a onclick="scrollDiv('miniApp')">医学小程序</a>
        <a onclick="scrollDiv('book')">医学图书</a>
        <a onclick="scrollDiv('video')">医学讲坛</a>
    </div>
    <%--网站--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '网站'}">
            <div class="yilan-website" id="website">
                <span style="display: block;">医学网站</span>
                <div class="yilan-recommend yilan-website-all">
                    <c:forEach items="${r.resources}" var="rr" varStatus="i">
                        <c:if test="${i.index % 8 eq 0}">
                            <div>
                            <div class="search-result" style="display:inline-block;">
                        </c:if>
                        <a href="${rr.url}" target="_blank">${rr.name}</a>
                        <c:if test="${i.index % 8 eq 7 || i.last }">
                            </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <%--期刊--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '期刊'}">
            <div class="yilan-website" id="periodical">
                <span style="display: block;">医学期刊</span>
                <div class="yilan-recommend yilan-website-all">
                    <c:forEach items="${r.resources}" var="rr" varStatus="i">
                        <c:if test="${i.index % 8 eq 0}">
                            <div>
                            <div class="search-result" style="display:inline-block;">
                        </c:if>
                        <a href="${rr.url}" target="_blank">${rr.name}</a>
                        <c:if test="${i.index % 8 eq 7 || i.last }">
                            </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <%--公众号--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '公众号'}">
            <div class="yilan-website" id="publicNumber">
                <span style="display: block;margin-bottom: 14px">医学公众号</span>
                <div class="yilan-software-all">
                    <div style="margin-bottom: 0">
                        <c:forEach items="${r.resources}" var="rr">
                            <div class="search-soft-codesmall" style="position: relative">
                                <img src="${rr.qrCode}">
                                <div class="search-soft-detail search-soft-detail-hidden">
                                    <span>开发商:${rr.developer}</span>
                                </div>
                                <span style="max-width: 150px">${rr.name}</span>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <%--软件--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '软件'}">
            <div class="yilan-website" id="app">
                <span style="display: block;margin-bottom: 14px">医学软件</span>
                <div class="yilan-software-all">
                    <div style="margin-bottom: 0">
                        <c:forEach items="${r.resources}" var="rr">
                            <div class="search-soft-codesmall" style="position: relative">
                                <img src="${rr.qrCode}">
                                <div class="search-soft-detail search-soft-detail-hidden">
                                    <span>开发商:${rr.developer}</span>
                                </div>
                                <span style="max-width: 150px">${rr.name}</span>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <%--小程序--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '小程序'}">
            <div id="miniApp" class="yilan-website">
                <span style="display: block;margin-bottom: 14px">医学小程序</span>
                <div class="yilan-software-all">
                    <div style="margin-bottom: 0">
                        <c:forEach items="${r.resources}" var="rr">
                            <div class="search-soft-codesmall" style="position: relative">
                                <img src="${rr.qrCode}">
                                <div class="search-soft-detail search-soft-detail-hidden">
                                    <span>开发商:${rr.developer}</span>
                                </div>
                                <span style="max-width: 150px">${rr.name}</span>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <%--图书--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '图书'}">
            <div class="yilan-website" id="book">
                <span style="display: block;margin-bottom: 14px">医学图书</span>
                <div class="search-book-all">
                    <div style="margin-bottom: 40px">
                        <c:forEach items="${r.resources}" var="rr">
                            <div class="soft-codesmall" style="position: relative">
                                <a href="<%=request.getContextPath()%>/book/detail?id=${rr.id}"><img style="height: 200px;width: 150px" src="${rr.cover}"></a>
                                <span>${rr.name}</span>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>

    <%--视频--%>
    <c:forEach items="${results}" var="r">
        <c:if test="${r.name eq '视频'}">
            <div class="yilan-website" id="video">
                <span style="display: block;margin-bottom: 14px">医学讲坛</span>
                <div class="yilan-video-all">
                    <div style="margin-bottom: 0">
                        <c:forEach items="${r.resources}" var="rr">
                        <div class="soft-codesmall" style="position: relative">
                            <a href="<%=request.getContextPath()%>/video/detail?id=${rr.id}"><img
                                    style="height: 120px;width: 220px" src="${rr.cover}"></a>
                            <span>${rr.name}</span>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>
</body>

<script>
    function scrollDiv(id){
        $("html,body").animate({scrollTop:$("#"+id).offset().top},1000);
    }
</script>

</html>