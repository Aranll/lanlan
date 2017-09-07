<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/28
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <div class="header-left">
        <a id="side_btn" class="side_nav"></a>
    </div>
    <div class="header-center">
        <form>
            <img class="search-icon" src="<%=request.getContextPath()%>/assets/mobile/img/search.png">
            <input class="search" type="search" placeholder="搜索您所需要的内容">
        </form>
    </div>
    <div class="header-right">
        <a class="user" href="<%=request.getContextPath()%>/mobile/user/center"></a>
    </div>
    <div class="clearfix"></div>
</header>


<div id="side_nav_overlay" class="side-nav-overlay"></div>

<div id="side_nav_box" class="side-nav-box">
    <a href="<%=request.getContextPath()%>/mobile/website">医学网站</a>
    <a href="<%=request.getContextPath()%>/mobile/official">医学公众号</a>
    <a href="javascript:void(0);">医学软件</a>
    <a href="javascript:void(0);">医学小程序</a>
    <a href="javascript:void(0);">医学期刊</a>
    <a href="javascript:void(0);">医学图片</a>
    <a href="javascript:void(0);">医学图书</a>
    <a href="javascript:void(0);">医学影院</a>
</div>
<script>
    var $sideBtn = $("#side_btn");
    var $sideNavOverlay = $("#side_nav_overlay");
    var $sideNavBox = $("#side_nav_box");

    var c_href = window.location.href;
    var sideNavBox = $("#side_nav_box");
    $(function () {
        $sideBtn.on("click", function () {
            $sideNavOverlay.show();
            $sideNavBox.addClass("show");
        });

        $sideNavOverlay.on("click", function () {
            $sideNavBox.removeClass("show");
            $sideNavOverlay.hide();
        });

        sideNavBox.find("a").each(function () {
            var $a = $(this)[0];
            var a_href = $a.href.trim();
            if (a_href !== "" && a_href !== $a.origin + "/" && c_href.indexOf(a_href) === 0) {
                var nexStr = c_href.substr(a_href.length, 1);
                if (nexStr === "" || nexStr === "?" || nexStr === "#" || nexStr === "/")
                    $(this).addClass("active");
            }
        });
    });
</script>
