<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no"/>
    <title>医览网-图书详情</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/cg-style.css">
    <script src="<%=request.getContextPath()%>/assets/mobile/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/mobile/js/share.js"></script>
</head>
<body>
<div class="container">
    <div class="book-particulars-box">
        <div class="book-particulars-img">
            <img src="" alt="">
        </div>
        <div class="book-name">
            <p><span class="title">书名<i></i></span>：<span class="content">进化论</span></p>
            <p><span class="title">作者<i></i></span>：<span class="content">达尔文</span></p>
            <p><span class="title">出版社<i></i></span>：<span class="content">人民日报日人民</span></p>
            <p><span class="title">摘要<i></i></span>：<span class="content">讲述了动物的基本身体构造以本身体构造以及以及进化过程身体构造的变化</span></p>
            <p><span class="title">出版时间<i></i></span>：<span class="content">20012年12月21日</span></p>
        </div>
        <div class="clearfix"></div>
        <a class="btn-read clearfix">阅读</a>
    </div>
</div>

<!--会员充值提示框-->
<div id="overlay">
    <div id="book-alert" class="text-center">
        <span>该资源仅对会员开放</span>
        <span><a href="" onclick="history.go(-1);">返回</a></span>
        <span><a href="">购买会员</a></span>
    </div>
</div>
</body>
<script>
    $('.btn-read').on('click', function () {
        $('#overlay').css("display", "block");
        return false;
    })
</script>
</html>