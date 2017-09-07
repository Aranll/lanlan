<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <title>医览网-医学网站</title>
</head>
<body>

<div class="container">
    <%@include file="../common/search-bar.jsp" %>

    <nav>
        <c:forEach items="${websiteCategory}" var="category" varStatus="status">
            <c:if test="${status.index eq 0}">
                <a  href="javascript:void(0)" class="active">${category.name}</a>
            </c:if>
            <c:if test="${status.index ne 0}">
                <a href="javascript:void(0)">${category.name}</a>
            </c:if>
        </c:forEach>
    </nav>

    <div>
        <c:forEach items="${websiteCategory}" var="category" varStatus="status">
            <div id="category_${status.index}" class="category" style="display: none">
                <c:forEach items="${category.children}" var="child" varStatus="childSatus">
                    <div class="category-box clearfix">
                        <a class="leader">${child.name}</a>
                        <div class="hide-box">
                            <c:if test="${fn:length(child.resources) le 3}">
                                <div class="hide-box-1 clearfix">
                                    <c:forEach items="${child.resources}" var="r">
                                        <div class="down-style"><a href="${r.url}">${r.name}</a></div>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${fn:length(child.resources) gt 3}">
                                <div class="hide-box-1 clearfix">
                                    <c:forEach items="${child.resources}" begin="0" end="2" var="r">
                                        <div class="down-style"><a href="${r.url}">${r.name}</a></div>
                                    </c:forEach>
                                </div>
                                <div class="hide-box-2 clearfix" data-tag="${status.index}_${childSatus.index}">
                                    <c:if test="${fn:length(child.resources) ge 9}">
                                        <c:forEach items="${child.resources}" begin="3" end="7" var="r">
                                            <div class="down-style"><a href="${r.url}">${r.name}</a></div>
                                        </c:forEach>
                                        <div class="down-style"><a href="<%=request.getContextPath()%>/mobile/website/category/${child.id}">更多</a></div>
                                    </c:if>
                                    <c:if test="${fn:length(child.resources) lt 9}">
                                        <c:forEach items="${child.resources}" begin="3" var="r">
                                            <div class="down-style"><a href="${r.url}">${r.name}</a></div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                        <div class="down-box">
                            <img style="height: 8px;" src="<%=request.getContextPath()%>/assets/mobile/img/down.png">
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</div>
<script>

    $(function () {
        $('#category_0').show();
        $('nav a').on('click', function (event) {
            $("nav a.active").removeClass("active");
            $(event.target).addClass("active");
            $("#category_" + $(event.target).index()).show().siblings().hide();
        });

        //点击向下按钮展开分类
        var downBox = $('.down-box img');
        var $hideBox2 = $('.hide-box-2');
        $(downBox).on('click', function (e) {
            var target = $(e.target);
            var a = target.parent().parent().find('.hide-box-2');
            if (a.is(":hidden")) {
                a.slideDown();
                target.css({
                    "transform": "rotate(180deg)",
                    "transition": "0.5s"
                });
                var tag = a.data("tag");
                $hideBox2.each(function () {
                    var $this = $(this);
                    if (tag != $this.data("tag") && $this.is(':visible')){
                        $this.slideUp();
                        $this.parent().next().children('img').css({"transform": "rotate(0deg)", "transition": "0.5s"});
                        return false;
                    }
                });
            }
            else {
                a.slideUp();
                target.css({
                    "transform": "rotate(0deg)",
                    "transition": "0.5s"
                })
            }
        })
    });
</script>
</body>
</html>