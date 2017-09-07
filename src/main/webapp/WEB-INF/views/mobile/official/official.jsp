<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: gustinlau
  Date: 01/09/2017
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <title>医览网-公众号</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/official.css">
</head>
<body>
<div class="container">
    <%@include file="../common/search-bar.jsp" %>

    <nav>
        <c:forEach items="${officialCategory}" var="category" varStatus="status">
            <c:if test="${status.index eq 0}">
                <a href="javascript:void(0)" class="active" data-tag="0">${category.name}</a>
            </c:if>
            <c:if test="${status.index ne 0}">
                <a href="javascript:void(0)" data-tag="${status.index}">${category.name}</a>
            </c:if>
        </c:forEach>
    </nav>

    <div>
        <c:forEach items="${officialCategory}" var="category" varStatus="status">
            <div id="category_${status.index}" class="category" style="display: none">
                <div class="category-box clearfix">
                    <a class="leader">筛选</a>
                    <div class="hide-box">

                        <c:if test="${fn:length(category.children) le 3}">
                            <div class="hide-box-1 clearfix">
                                <c:forEach items="${category.children}" var="child" varStatus="childStatus">
                                    <div  class="down-style ${childStatus.index==0?"active":""}">
                                        <a href="javascript:void(0)" data-tag="${childStatus.index}">${child.name}</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>

                        <c:if test="${fn:length(category.children) gt 3}">
                            <div class="hide-box-1 clearfix">
                                <c:forEach items="${category.children}" begin="0" end="2" var="child"
                                           varStatus="childStatus">
                                    <div class="down-style ${childStatus.index==0?"active":""}">
                                        <a href="javascript:void(0)" data-tag="${childStatus.index}">${child.name}</a>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="hide-box-2 clearfix">
                                <c:forEach items="${category.children}" begin="3" var="child" varStatus="childStatus">
                                    <div class="down-style" data-tag="${childStatus.index}">
                                        <a href="javascript:void(0)" data-tag="${childStatus.index}">${child.name}</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${fn:length(category.children) gt 3}">
                        <div class="down-box">
                            <img style="height: 8px;" src="<%=request.getContextPath()%>/assets/mobile/img/down.png">
                        </div>
                    </c:if>
                </div>

                <c:forEach items="${category.children}" var="child" varStatus="qrStatus">
                    <div class="official-box" data-tag="${status.index}_${qrStatus.index}"
                         style="display: ${qrStatus.index==0?"block":"none"}">
                        <c:forEach items="${child.resources}" var="r">
                            <div class="official">
                                <div class="QRcode">
                                    <img src="${r.qrCode}" alt="">
                                </div>
                                <div class="company">
                                    <h4>${r.name}</h4>
                                    <h5>${r.developer}</h5>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    $('#category_0').show();
    $('nav a').on('click', function (event) {
        $("nav a.active").removeClass("active");
        $(event.target).addClass("active");
        $("#category_" + $(event.target).index()).show().siblings().hide();
    });

    //点击向下按钮展开分类
    var downBox = $('.down-box img');
    $(downBox).on('click', function (e) {
        var target = $(e.target);
        var a = target.parent().parent().find('.hide-box-2');
        if (a.is(":hidden")) {
            a.slideDown();
            target.css({
                "transform": "rotate(180deg)",
                "transition": "0.5s"
            });
        } else {
            a.slideUp();
            target.css({
                "transform": "rotate(0deg)",
                "transition": "0.5s"
            })
        }
    });

    $(".down-style a").on("click", function (e) {
        var tag1 = $("nav a.active").data("tag");
        var target = $(e.target);
        var tag2 = target.data("tag");
        var currentBox = $("#category_" + tag1).find(".official-box:visible");
        if (currentBox.data("tag") != (tag1 + "_" + tag2)) {
            currentBox.hide();
            $(".official-box[data-tag=" + tag1 + "_" + tag2 + "]").show();
            target.parent().addClass("active").siblings().removeClass("active");
        }
    });

</script>
</body>
</html>
