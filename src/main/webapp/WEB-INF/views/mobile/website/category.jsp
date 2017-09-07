<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <title>医览网</title>

</head>
<body>

<div class="container">
    <%@include file="../common/search-bar.jsp" %>

    <div class="category">
        <div class="category-box clearfix">
        <c:forEach items="${webSite}" var="site">
                <div class="default-item"><a href="${site.url}">${site.name}</a></div>
        </c:forEach>
        </div>
    </div>

</div>
</body>
</html>