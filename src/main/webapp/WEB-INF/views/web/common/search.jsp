<%--
  Created by IntelliJ IDEA.
  User: 阿展
  Date: 2017-08-22
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main-search">
    <form id="searchForm" action="<%=request.getContextPath()%>/search" type="get">
        <img src="<%=request.getContextPath()%>/assets/web/img/logo.png">
        <input id="main-search-input" name="name" type="text" placeholder="请输入名字或者关键字"
               <c:if test="${not empty name}">value="${name}" </c:if>>
        <input id="main-search-btn" type="button" value="搜索">
    </form>
</div>
<script>
    $('#main-search-btn').click(function () {
        $('#searchForm').submit();
    })
</script>