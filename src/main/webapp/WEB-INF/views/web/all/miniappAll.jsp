<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>医览网</title>
    <%@include file="../common/head.jsp" %>
</head>
<%@include file="../common/header.jsp" %>

<div class="yilan-center clearfix">
    <%@include file="../common/search.jsp" %>
    <div class="yilan-website">
        <span style="display: block;text-align: center">医学小程序</span>
        <c:forEach items="${miniapps}" var="w">
            <span class="modal-class">${w.name}</span>
            <div class="yilan-software-all">
                <c:forEach items="${w.children}" var="c">
                    <span id="${c.id}" style="margin-left: 0;font-size: 18px;color: #33AC79;">${c.name}</span>
                    <HR class="recommend-hr"/>
                    <div>
                        <c:forEach items="${c.resources}" var="r">
                            <div class="search-soft-codesmall" style="position: relative">
                                <img src="${r.qrCode}">
                                <div class="search-soft-detail search-soft-detail-hidden">
                                    <span>开发商:${r.developer}</span>
                                </div>
                                <span>${r.name}</span>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>
<script>
    scrollDiv('${category.id}');
</script>
</body>
</html>
