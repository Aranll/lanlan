<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="./common/head.jsp" %>
    <%@include file="./common/validate.jsp" %>

    <title>医览网-用户注册</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/register.css">
</head>
<body>
<div class="container">
    <form name="registerForm" class="form-group">
        <div class="position-div validate">
            <p>手机号码</p>
            <input class="input-style" name="mobile" type="tel" placeholder="请输入手机号码">
        </div>
        <div class="position-div validate">
            <p>验证码</p>
            <input class="input-style" name="code" type="text" placeholder="请输入验证码">
            <img id="securityCode" src="<%=request.getContextPath()%>/captcha.png" onclick="refreshCode()">
        </div>
        <div class="position-div validate">
            <p>短信验证码</p>
            <input class="input-style" type="text" name="captcha.content" placeholder="请输入短信验证码">
            <input id="get_verification" type="button" onclick="getCaptcha()" value="获取验证码">
        </div>
        <div class="position-div validate">
            <p>邮箱</p>
            <input class="input-style" type="tel" name="email" placeholder="请输入邮箱">
        </div>
        <div class="position-div validate">
            <p>密码</p>
            <input id="password" class="input-style" type="password" name="password" placeholder="请输入密码">
        </div>
        <div class="position-div validate">
            <p>确认密码</p>
            <input class="input-style" type="password" name="confirmPassword" placeholder="请确认密码">
        </div>
        <div class="register-group">
            <input class="reset-button" type="reset" value="重置">
            <input class="register-button" type="submit" value="注册">
        </div>
    </form>
</div>
<script>
    $("form[name='registerForm']").validate({
        rules: {
            mobile: {
                required: true,
                mobile: true
            },
            password: {
                required: true,
                maxlength: 20,
                minlength: 6
            },
            confirmPassword: {
                required: true,
                equalTo: "#password"
            },
            email: {
                required: true,
                maxlength: 50,
                email: true
            }
        },
        messages: {
            mobile: {
                required: "请填写手机号",
                mobile: "手机格式不符"
            },
            password: {
                required: "请填写密码",
                maxlength: "密码最多20位",
                minlength: "密码最少6位"
            },
            confirmPassword: {
                required: "请再次填写密码",
                equalTo: "两次密码不一致"
            },
            email: {
                required: "请填写邮箱",
                maxlength: "邮箱最多50个字符",
                email: "邮箱格式不符"
            }
        },
        submitHandler: function (form) {
            doPost("<%=request.getContextPath()%>/mobile/register", $(form).serialize(),
                function (data) {
                    if (data.status) {
                        window.location.href = "<%=request.getContextPath()%>/mobile/index"
                    } else {
                        alert(data.msg);
                    }
                }, function (XMLHttpRequest, textStatus) {
                    alert("请求失败：" + textStatus + "\n错误码：" + XMLHttpRequest.status);
                }
            );
        }
    });

    function getCaptcha() {
        var code = $("input[name=code]").val();
        var mobile = $("input[name=mobile]").val();

        if (!mobile.match(/^1[34578]\d{9}$/)) {
            alert("请填写手机号码");
            return;
        }
        if (code == "") {
            alert("请先填写验证码");
            return;
        }
        doPost("<%=request.getContextPath()%>/mobile/register/captcha", {mobile: mobile, code: code}, function (data) {
            if (data.status) {
                countDown();
                toastSuccess("手机验证码发送成功");
            } else {
                if (data.code === 1001) {
                    refreshCode();
                }
                alert(data.msg);
            }
        });

    }

    function refreshCode() {
        $("#securityCode").attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
    }

    var second = 60;
    var $getVerification = $("#get_verification");
    function countDown() {
        if (second > 0) {
            second--;
            $getVerification.attr("disabled", true);
            $getVerification.val(second + "(s)后重新获取");
            setTimeout(function () {
                countDown();
            }, 1000);
        } else {
            $getVerification.attr("disabled", false);
            $getVerification.val("获取验证码");
            second = 60;
        }


    }
</script>
</body>
</html>