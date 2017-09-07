<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 阿展
  Date: 2017-08-23
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="yilan-hot" style="margin-top: 40px;">
        <span class="soft-span">热门图书</span>
        <c:forEach items="${bookHot}" var="h" varStatus="i">
            <c:if test="${i.index lt 8}">
                <div class="soft-hot" style="position: relative">
                    <a href="<%=request.getContextPath()%>/book/detail?id=${h.id}"><span>${h.name}</span></a>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="yilan-website  book-img">
        <span style="display: block;">医学微信图书</span>

        <div class="left-nav">
            <ul class="nav" style="position: relative">
                <c:forEach items="${bookCategories}" var="h">
                    <li class="nav-li">
                        <a>${h.name}</a>
                        <div class="nav-navbar nav-navbar-hidden">
                            <ul class="nav">
                                <c:forEach items="${h.children}" var="c">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/book/all?id=${c.id}">${c.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>


        <c:forEach items="${bookRecommends}" var="b">
            <div class="yilan-recommend-soft" style="margin-top: 10px;margin-left: 20px;float: right">
                <span style="margin-left: 0;font-size: 18px;color: #33AC79">${b.name}</span>
                <a class="recommend-last" href="<%=request.getContextPath()%>/book/all?id=${b.id}">更多>></a>
                <HR class="recommend-hr"/>
                <div>
                    <c:forEach items="${b.resources}" var="r">
                        <div style="margin-bottom: 20px;vertical-align: middle">
                            <a href="<%=request.getContextPath()%>/book/detail?id=${r.id}"><img src="${r.cover}"></a>
                            <span style="width: 160px;height: 50px">${r.name}</span>
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
