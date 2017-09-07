<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no"/>
    <title>医览网-医学图片详情</title>
    <link rel="stylesheet" href="../assets/css/cg-style.css">
    <script src="../assets/js/jquery-3.2.1.min.js"></script>
    <script src="../assets/js/share.js"></script>
</head>
<body style="background: #FFFFFF !important;">
<!--会员充值提示框-->

<div class="container">
    <div id="video-box">
        <h4>广东省医学会副会长</h4>
        <div id="video-button">
            <video src=""></video>
            <a href=""><img id="video-play" src="../assets/img/play.png" alt=""></a>
        </div>
        <div class="book-name">
            <p><span class="title">发布时间<i></i></span>：<span class="content">2017年1月20日</span></p>
            <p><span class="title">讲师<i></i></span>：<span class="content">李教授</span></p>
            <p><span class="title">来源<i></i></span>：<span class="content">****网</span></p>
            <p><span class="title">摘要<i></i></span>：<span class="content">讲述了动物的基本身体构造以本身体构造以及以及进化过程身体构造的了动物的基本身体构造以本身体构造以及以及进化过程身体构造的了动物的基本身体构造以本身体构造以及以及进化过程身体构造的了动物的基本身体构造以本身体构造以及以及进化过程身体构造的了动物的基本身体构造以本身体构造以及以及进化过程身体构造的了动物的基本身体构造以本</span></p>
        </div>
    </div>
</div>
<div id="overlay">
    <div id="book-alert" class="text-center">
        <span>该资源仅对会员开放</span>
        <span><a href="" onclick="history.go(-1);">返回</a></span>
        <span><a href="">购买会员</a></span>
    </div>
</div>
</body>
<script>
    $('#video-play').on('click', function () {
        $('#overlay').css("display", "block");
        return false;
    })
</script>
</html>