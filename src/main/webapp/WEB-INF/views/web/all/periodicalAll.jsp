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
        <span style="display: block;text-align: center">医学期刊</span>

        <c:forEach items="${periodicals}" var="w">
            <span class="modal-class">${w.name}</span>
            <div class="yilan-recommend yilan-website-all">
                <c:forEach items="${w.children}" var="c">
                    <div id="${c.id}">
                        <span style="display: inline-block" >${c.name}</span>
                        <div class="hot-websites" style="display:inline-block;">
                            <c:forEach items="${c.resources}" var="r">
                                <a title="${r.name}----${r.developer}" href="${r.url}" target="_blank">${r.name}</a>
                            </c:forEach>
                        </div>
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
