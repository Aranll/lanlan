<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>医览网</title>
    <%@include file="../common/head.jsp" %>
    <script>
        function getBook(){
            $.ajax({
                type: 'POST',
                url: "<%=request.getContextPath()%>/book/get",
                data: '{ "id":${book.id}}',
                contentType:"application/json",
                dataType:"json",
                success: function (data) {
                    if (!data.status) {
                        if(data.code == 700) {
                            $('#login-msg').modal('show');
                        }
                        if(data.code == 701) {
                            $('#vip-msg').modal('show');
                        }
                    }else {
                        window.location.href="<%=request.getContextPath()%>/assets/web/plugins/generic/web/viewer.html?file="+data.data;
                    }
                },
                error:function (data) {
                    alert("请求出错");
                }

            })
        }
    </script>
</head>
<%@include file="../common/header.jsp" %>

<div class="yilan-center clearfix">
    <%@include file="../common/search.jsp" %>
    <span style="color: #999999;font-size: 14px">当前位置：<a href="<%=request.getContextPath()%>/book/all">医学图书</a>
        <a href="<%=request.getContextPath()%>/book/all?id=${book.category.id}"> >${book.name} </a>
    </span>
    <hr class = "position-hr">
    <div>
        <img style="width: 250px;height: 325px;display: inline-block" src="${book.cover}">
        <div class="b-detail-div">
            <span class="span-block">${book.name}</span>
            <div style="margin-top: 10px">
                <span class="span-inline-block-left">作者:</span>
                <span class="span-inline-block-right"><c:choose>
                    <c:when test="${not empty book.author}">${book.author}</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose></span>
            </div>
            <div style="margin-top: 10px">
                <span class="span-inline-block-left">出版:</span>
                <span class="span-inline-block-right"><c:choose>
                    <c:when test="${not empty book.publisher}">${book.publisher}</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose></span>
            </div>
            <div style="margin-top: 10px">
                <span class="span-inline-block-left">简介:</span>
                <span class="span-inline-block-right-h"><c:choose>
                    <c:when test="${not empty book.profile}">${book.profile}</c:when>
                    <c:otherwise>暂无</c:otherwise>
                </c:choose></span>
            </div>
            <div style="margin-top: 10px">
                <span class="span-inline-block-left">出版时间:</span>
                <span class="span-inline-block-right"><c:choose>
                    <c:when test="${not empty book.publishDate}">${book.publishDate}</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose></span>
            </div>
            <div style="margin-top: 10px;position: absolute;bottom: 0">
                <button class="span-button" onclick="getBook()">阅读</button>
            </div>
        </div>
    </div>
</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>
<%@include file="../common/msgPage.jsp" %>

</body>


</html>
