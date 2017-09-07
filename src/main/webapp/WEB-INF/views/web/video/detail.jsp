<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>医览网</title>
    <%@include file="../common/head.jsp" %>
    <link href="<%=request.getContextPath()%>/assets/web/plugins/video/video-js.css" rel="stylesheet">
</head>
<%@include file="../common/header.jsp" %>

<div class="yilan-center clearfix">
    <%@include file="../common/search.jsp" %>

    <span style="color: #999999;font-size: 14px">当前位置：<a href="<%=request.getContextPath()%>/video/all">医学讲坛</a>
        <a href="<%=request.getContextPath()%>/video/all?id=${video.category.id}"> >${video.name} </a>
    </span>
    <hr class="position-hr">
    <div style="width: 1200px">
        <div class="v-detail-div">
            <span class="span-b">${video.name}</span>
            <div style="margin-top: 10px">
                <span class="span-inblock-left">讲师:</span>
                <span class="span-inblock-right"><c:choose>
                    <c:when test="${not empty video.author}">${video.author}</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose></span>
            </div>
            <div style="margin-top: 10px">
                <span class="span-inblock-left">来源:</span>
                <span class="span-inblock-right"><c:choose>
                    <c:when test="${not empty video.originDesc}">${video.originDesc}</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose></span>
            </div>
            <div style="margin-top: 10px">
                <span class="span-inblock-left">内容:</span>
                <span class="span-inblock-right-h"><c:choose>
                    <c:when test="${not empty video.profile}">${video.profile}</c:when>
                    <c:otherwise>暂无</c:otherwise>
                </c:choose></span>
            </div>
        </div>
        <div style="width: 890px;height: 511px;display: inline-block;vertical-align: top">
            <video id="my-video" style="background-color: #666666" class="video-js vjs-big-play-centered"
                   controls preload="auto" width="890" height="500"
                   poster="MY_VIDEO_POSTER.jpg" data-setup="{}">
                <source src="<c:if test="${video.accessVipLevel eq 0 || user.vipLevel eq 1}">${video.url}</c:if>" type="video/mp4">
                <source src="<c:if test="${video.accessVipLevel eq 0 || user.vipLevel eq 1}">${video.url}</c:if>" type="video/webm">
                <source src="<c:if test="${video.accessVipLevel eq 0 || user.vipLevel eq 1}">${video.url}</c:if>" type="video/ogg">
                <p class="vjs-no-js">
                    To view this video please enable JavaScript, and consider upgrading to a web browser that
                    <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
                </p>
            </video>
            <script src="<%=request.getContextPath()%>/assets/web/plugins/video/video.min.js"></script>
            <script type="text/javascript">
                var myPlayer;
                $(function () {
                    <c:if test="${video.accessVipLevel eq 1}">
                        <c:if test="${empty user || user.mobile eq ''}">
                            $('#login-msg').modal("show");
                        </c:if>
                        <c:if test="${user.vipLevel eq 0}">
                            $('#vip-msg').modal("show");
                        </c:if>
                    </c:if>
                    myPlayer = videojs('my-video');
                    videojs("my-video").ready(function(){
                        var myPlayer = this;
                    });
                })

            </script>
        </div>

    </div>

</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>
<%@include file="../common/msgPage.jsp"%>

</body>


</html>
