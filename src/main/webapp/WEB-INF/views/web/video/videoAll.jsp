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
        <span style="display: block;text-align: center">医学讲坛</span>
        <c:forEach items="${videos}" var="v">
            <span class="modal-class">${v.name}</span>
            <div class="yilan-video-all">
                <c:forEach items="${v.children}" var="c">
                    <span id="${c.id}" style="margin-left: 0;font-size: 18px;color: #33AC79;">${c.name}</span>
                    <HR class="recommend-hr"/>
                    <div style="margin-bottom: 40px">
                        <c:forEach items="${c.resources}" var="r">
                            <div class="soft-codesmall" style="position: relative">
                                <a href="<%=request.getContextPath()%>/video/detail?id=${r.id}"><img src="${r.cover}"></a>
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
