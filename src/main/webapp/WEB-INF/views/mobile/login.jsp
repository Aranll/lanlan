<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-登录</title>
    <%@include file="./common/head.jsp"%>
    <%@include file="./common/validate.jsp"%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/login.css">
</head>
<body>
<div class="container">
    <div class="logo">
        <img src="<%=request.getContextPath()%>/assets/mobile/img/logo.png" alt="" >
    </div>
    <div class="form-group">
        <form name="loginForm">
            <div id="phone-number" class="validate">
                <img  class="input-icon" id="img-phone" src="<%=request.getContextPath()%>/assets/mobile/img/phone.png" alt="">
                <input class="input-style" type="tel" name="mobile" placeholder="请输入您的手机号码">
            </div>
            <div id="password" class="validate">
                <img class="input-icon" id="img-password" src="<%=request.getContextPath()%>/assets/mobile/img/key.png" alt="">
                <input class="input-style" type="password" name="password" placeholder="请输入您的密码">
            </div>
            <a class="text-left" href="<%=request.getContextPath()%>/mobile/register">注册账号</a>
            <a class="text-right" href="<%=request.getContextPath()%>/mobile/forgetPwd">忘记密码?</a>
            <input type="submit" value="登录"/>
        </form>
    </div>
</div>
<script>
    $("form[name='loginForm']").validate({
        rules: {
            mobile:{
                required: true
            },
            password:{
                required:true
            }
        },
        messages: {
            mobile:{
                required: "请填写手机号"
            },
            password:{
                required:"请填写密码"
            }
        },
        submitHandler: function (form) {
            doPost("<%=request.getContextPath()%>/mobile/login", $(form).serialize(),
                function (data) {
                    if (data.status) {
                        window.location.href = "<%=request.getContextPath()%>/mobile/index"
                    } else {
                        alert(data.msg);
                    }
                }, function (XMLHttpRequest, textStatus) {
                    alert("请求失败：" + textStatus + "\n错误码：" + XMLHttpRequest.status);

                });
        }
    });
</script>
</body>
</html>