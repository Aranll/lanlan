<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <title>医览网-重置密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/reset-password.css">
</head>
<body>
<div class="container">
    <form class="form-group" action="" id="resetForm">
        <div class="position-div">
            <p>密码</p>
            <input class="input-style" id="password" name="password1" type="password" placeholder="请输入密码">
        </div>
        <div class="position-div">
            <p>确认密码</p>
            <input class="input-style" name="password" type="password" placeholder="请确认密码">
        </div>
        <div class="register-group">
            <a href="<%=request.getContextPath()%>/mobile/login">
                <input class="reset-button" type="button" value="取消">
            </a>
            <input class="register-button" type="submit" value="完成">
        </div>
    </form>
</div>
<script type="text/javascript">
    var mobile;
    var content;
    var $resetForm = $("#resetForm");
    $resetForm.validate({
        errorClass: 'text-danger',
        rules: {
            password1: {
                required: true,
                notEmpty: true,
                min: 6
            },
            password: {
                required: true,
                notEmpty: true,
                equalTo: "#password",
                maxlength: 20,
                minlength: 6
            }
        },
        messages: {
            password: {
                required: "密码不能为空",
                notEmpty: "密码不能为空",
                equalTo: "两次输入密码需要相同",
                maxlength: "密码最多20位",
                minlength: "密码最少6位"
            }
        },
        submitHandler: function () {
            var password = $("input[name='password']").val();
            mobile = ${mobile};
            content = ${content};
            doPost("<%=request.getContextPath()%>/mobile/forgetPwd/resetPwd",
                "password="+password+"&"+"mobile="+mobile+"&"+"captcha.content="+content,
                function (data) {
                    if (data.status) {
                        toastSuccess("更新密码成功");
                        setTimeout(function(){
                            window.location.href = "<%=request.getContextPath()%>/yilan/mobile/login";
                        },300);
                    } else {
                        alert(data.msg);
                    }
                 });
            }
         }
    );
</script>
</body>
</html>