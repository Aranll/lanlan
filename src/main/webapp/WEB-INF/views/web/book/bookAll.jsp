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
        <span style="display: block;text-align: center">医学图书</span>
        <c:forEach items="${books}" var="b">
        <span class="modal-class" >${b.name}</span>
        <div class="yilan-book-all">
            <c:forEach items="${b.children}" var="c">
            <span style="margin-left: 0;font-size: 18px;color: #33AC79;" id="${c.id}">${c.name}</span>
            <HR class="recommend-hr"/>
            <div style="margin-bottom: 40px">
                <c:forEach items="${c.resources}" var="r">
                    <div class="soft-codesmall" style="position: relative">
                        <a href="<%=request.getContextPath()%>/book/detail?id=${r.id}">
                            <img style="height: 200px !important;width: 150px !important;" src="${r.cover}"></a>
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
