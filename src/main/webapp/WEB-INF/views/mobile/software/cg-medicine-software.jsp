<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no"/>
    <title>医览网-医学软件</title>
    <link rel="stylesheet" href="../../assets/mobile/css/cg-style.css">
    <link rel="stylesheet" href="../../assets/mobile/css/cg-medicine-software.css">
    <script src="../../assets/mobile/js/jquery-3.2.1.min.js"></script>
    <script src="../../assets/mobile/js/share.js"></script>
    <script src="../../assets/mobile/js/app.js"></script>
</head>
<body>

<div class="container">
    <div id="side-nav-box" class="nav-hidden">
        <a href="javascript:void(0);">医学网站</a>
        <a href="javascript:void(0);">医学公众号</a>
        <a href="javascript:void(0);">医学软件</a>
        <a href="javascript:void(0);">医学小程序</a>
        <a href="javascript:void(0);">医学期刊</a>
        <a href="javascript:void(0);">医学图片</a>
        <a href="javascript:void(0);">医学图书</a>
        <a href="javascript:void(0);">医学影院</a>
    </div>

    <header>
        <div class="header-left">
            <a class="side_nav"></a>
        </div>
        <div class="header-center">
            <form action="">
                <img class="search-icon" src="../../assets/mobile/img/search.png">
                <input class="search" type="search" placeholder="搜索您所需要的内容">
            </form>
        </div>
        <div class="header-right">
            <a class="user"></a>
        </div>
        <div class="clearfix"></div>
    </header>
    <nav> <!--id="test"-->
        <c:forEach items="${softwares}" var="software">
            <a id="${software.id}" onclick="getResources(${software.id})">${software.name}</a>
        </c:forEach>
    </nav>

    <c:forEach items="${softwares}" var="software">
        <div class="catagory hideCategory showCategory${software.id}" style="display: none">
            <div class="content">
                <a class="leader">分类</a>
                <div class="classify-active">
                    <a onclick="getResources(${software.id})">所有</a>
                </div>
                <c:forEach items="${software.children}" var="children" begin="0" end="1">
                    <a id="${children.id}" onclick="getResources(${children.id})">${children.name}</a>
                </c:forEach>
                <a class="dowm-box"></a>
            </div>
            <c:forEach items="${software.children}" var="children" begin="2" step="3">
                <div class="content">
                    <a class="leader"></a>
                    <a id="${children.id}" onclick="getResources(${children.id})">${children.name}</a>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
    <div id="software-box">
        <c:forEach items="${apps}" var="app">
            <div class="software">
                <div class="QRcode">
                    <img src="${app.qrCode}" alt="">
                </div>
                <div class="company">
                    <h4>${app.name}</h4>
                    <h5>${app.developer}</h5>
                </div>
            </div>
        </c:forEach>
    </div>
    <div>
        <%--<button type="button" class="btn btn-default" onclick="readmore()">閲讀更多</button>--%>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".hideCategory:first").show();
        $("nav a:first").addClass("active");
        $("nav a").on('click', function () {
            $(".hideCategory").hide();
            $(".showCategory" + this.id).show();
        });
    });
</script>
<script type="text/javascript">
    var categoryId;
    var count=0;
    var html = '';
    function getResources(id) {
        count = 0;
        categoryId = id;
        doPost("<%=request.getContextPath()%>/mobile/software/category",'category.id='+id, function (data) {
            var _data = data.data;
            if (data.status) {
                $.each(_data, function (i, item) {
                    html += "<div class='software'>" +
                        "<div class='QRcode'>" +
                        "<img src='" + item.qrCode + "' alt=''>" +
                        "</div>" +
                        "<div class='company'>" +
                        "<h4>" + item.name + "</h4>" +
                        "<h5>" + item.developer + "</h5>" +
                        "</div>" +
                        "</div>"
                });
                $("#software-box").html("");
                $("#software-box").append(html);
                html="";
            } else {
                alert(data.msg);
            }
        })
    }
    function readmore() {
        count++;
        doPost("<%=request.getContextPath()%>/mobile/software/category",'page='+count+'&'+'category.id='+categoryId, function (data) {
            var _data = data.data;
            if (data.status) {
                $.each(_data, function (i, item) {
                    html += "<div class='software'>" +
                        "<div class='QRcode'>" +
                        "<img src='" + item.qrCode + "' alt=''>" +
                        "</div>" +
                        "<div class='company'>" +
                        "<h4>" + item.name + "</h4>" +
                        "<h5>" + item.developer + "</h5>" +
                        "</div>" +
                        "</div>"
                });
                $("#software-box").append(html);
                html="";
            } else {
                alert(data.msg);
            }
        })
    }
</script>


<script>
    $('nav a').on('click', function (event) {
        console.log(event);
        $("a.active").removeClass("active");
        $(event.target).addClass("active");
    })
</script>
</body>
</html>