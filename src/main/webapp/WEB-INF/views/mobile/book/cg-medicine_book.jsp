<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no"/>
    <title>医览网-医学图书</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/cg-style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/cg-medicine_book.css">
    <script src="<%=request.getContextPath()%>/assets/mobile/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/mobile/js/share.js"></script>
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
                <img class="search-icon" src="<%=request.getContextPath()%>/assets/mobile/img/search.png">
                <input class="search" type="search" placeholder="搜索您所需要的内容">
            </form>
        </div>
        <div class="header-right">
            <a class="user"></a>
        </div>
        <div class="clearfix"></div>
    </header>

    <div class="catagory">
        <div class="content">
            <a class="leader">分类</a>
            <div class="classify-active">
                <a>所有</a>
            </div>
            <a>人类学</a>
            <a>药物学</a>
            <a class="dowm-box"></a>
        </div>
        <div class="content">
            <a class="leader"></a>
            <a>人类学</a>
            <a>药物学</a>
            <a>药物学</a>
        </div>
        <div class="content">
            <a class="leader"></a>
            <a>人类学</a>
            <a>药物学</a>
            <a>药物学</a>
        </div>
    </div>

    <div class="catagory">
        <div class="content">
            <a class="leader">筛选</a>
            <div class="classify-active">
                <a>所有</a>
            </div>
            <a>人类学</a>
            <a>药物学</a>
            <a class="dowm-box"></a>
        </div>
        <div class="content">
            <a class="leader"></a>
            <a>人类学</a>
            <a>人类学</a>
            <a>药物学</a>
        </div>
        <div class="content">
            <a class="leader"></a>
            <a>人类学</a>
            <a>人类学</a>
            <a>药物学</a>
        </div>
        <div class="content">
            <a class="leader"></a>
            <a>人类学</a>
            <a>人类学</a>
            <a>药物学</a>
        </div>
    </div>
    <div class="book-box">
        <div class="book text-left">
            <div class="book-img">
                <img>
            </div>
            <p>动物学</p>
        </div>
        <div class="book text-left">
            <div class="book-img">
                <img>
            </div>
            <p>动物学</p>
        </div>

        <div class="book text-left">
            <div class="book-img">
                <img>
            </div>
            <p>动物学</p>
        </div>
        <div class="book text-left">
            <div class="book-img">
                <img>
            </div>
            <p>动物学</p>
        </div>
        <div class="book text-left">
            <div class="book-img">
                <img>
            </div>
            <p>动物学</p>
        </div>
        <div class="book text-left">
            <div class="book-img">
                <img>
            </div>
            <p>动物学</p>
        </div>

    </div>
</div>
<script>
    //    var lis = $("li");
    //    for (var i = 0; i < lis.length; i++) {
    //        var fun = function (i) {
    //            $(lis[i]).on("click", function () {
    //                console.log(i);
    //            });
    //        };
    //        fun(i);
    //    }

</script>
</body>
</html>