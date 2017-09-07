<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <title>医览网-会员充值</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/cg-recharge.css">
    <script src="<%=request.getContextPath()%>/assets/mobile/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="container">
    <h4>套餐选择</h4>
    <div class="choose-date">
        <span class="active">30天</span>
        <span>60天</span>
        <span>90天</span>
        <span>120天</span>
    </div>
    <h4>支付方式</h4>
    <div class="choose-bank">
        <span class="active">微信</span>
        <span>支付宝</span>
        <span>网银</span>
    </div>
    <h4>金额:
        <span>￥19</span>
    </h4>
    <input type="button" value="付款">
</div>
</body>
<script>
    $('.choose-date').find('span').on('click',function (e) {
        var spana=$(e.target);
        if (spana.hasClass('active')){
            spana.removeClass('active');
        }else {
            $('.choose-date span.active').removeClass('active');
            spana.addClass('active');
        }
    })

    $('.choose-bank').find('span').on('click',function (e) {
        var spanb=$(e.target);
        if (spanb.hasClass('active')){
            spanb.removeClass('active');
        }else {
            $('.choose-bank span.active').removeClass('active');
            spanb.addClass('active');
        }
    })
</script>
</html>