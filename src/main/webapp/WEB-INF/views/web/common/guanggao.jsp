<%--
  Created by IntelliJ IDEA.
  User: 阿展
  Date: 2017-08-22
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="clear: both;text-align: center">
    <c:forEach items="${sysProp.miniAppQrcodeList}" var="p" varStatus="i">
        <div class="guanggao-main">
            <div class="guanggao">
                <img src="${p}">
            </div>
            <span>${sysProp.miniAppNameList[i.index]}</span>
        </div>
    </c:forEach>
</div>
