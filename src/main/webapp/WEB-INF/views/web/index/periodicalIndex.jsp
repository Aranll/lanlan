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
        <span>热门期刊</span>
        <c:forEach items="${periodicalHot}" var="h" varStatus="i">
            <c:if test="${i.index lt 8}">
                <a href="${h.url}" title="${h.name}----${h.developer}" target="_blank">${h.name}</a>
            </c:if>
        </c:forEach>
    </div>
    <div class="yilan-website ">
        <span style="display: block;">医学期刊</span>
        <div class="left-nav">
            <ul class="nav" style="position: relative">
                <c:forEach items="${periodicalCategories}" var="h">
                    <li class="nav-li">
                        <a>${h.name}</a>
                        <div class="nav-navbar nav-navbar-hidden">
                            <ul class="nav">
                                <c:forEach items="${h.children}" var="c">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/periodical/all?id=${c.id}">${c.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="yilan-recommend" style="margin-top: 10px">
            <c:forEach items="${periodicalRecommends}" var="w">
                <div>
                    <span style="display: inline-block">${w.name}</span>
                    <div class="hot-websites" style="display:inline-block;">
                        <c:forEach items="${w.resources}" var="r">
                            <a href="${r.url}" title="${r.name}----${r.developer}" target="_blank">${r.name}</a>
                        </c:forEach>
                    </div>
                    <a class="recommend-last" href="<%=request.getContextPath()%>/periodical/all?id=${w.id}">更多>></a>
                </div>
            </c:forEach>
        </div>

    </div>
</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>

</body>
</html>