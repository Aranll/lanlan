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
<body>
<!--会员充值提示框-->

<div class="container">
    <div class="img-particulars">
        <img src="" alt="">
        <a class="preserve" href="">保存原图</a>
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
    $('.preserve').on('click', function () {
        $('#overlay').css("display", "block");
        return false;
    })
</script>
</html>