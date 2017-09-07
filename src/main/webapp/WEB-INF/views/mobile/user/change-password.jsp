<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <title>医览网-修改密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/reset-password.css">
</head>
<body>
<div class="container">
    <form class="form-group" action="" name="changeForm">
        <div class="position-div validate">
            <p>旧密码</p>
            <input class="input-style form-control" name="oldPassword" type="password" placeholder="请输入旧密码">
        </div>
        <div class="position-div validate">
            <p>新密码</p>
            <input class="input-style form-control" name="newPassword" id="password" type="password"
                   placeholder="请输入新密码">
        </div>
        <div class="position-div validate">
            <p>确认密码</p>
            <input class="input-style form-control" type="password" name="password" placeholder="请确认新密码">
        </div>
        <div class="register-group">
            <input class="reset-button" type="button" value="取消" onclick="returnCenter()">
            <input class="register-button" type="submit" value="完成">
        </div>
    </form>
</div>
<script type="text/javascript">
    var $changeForm = $("form[name='changeForm']");
    $changeForm.validate({
        errorClass: 'text-danger',
        rules: {
            oldPassword: {
                required: true,
                notEmpty: true,
                maxlength: 20,
                minlength: 6
            },
            newPassword: {
                required: true,
                notEmpty: true,
                maxlength: 20,
                minlength: 6
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
            oldPassword: {
                required: "密码不能为空",
                notEmpty: "密码不能为空",
                maxlength: "密码长度不得大于20位数",
                minlength: "密码长度不得小于6位数"
            },
            newPassword: {
                required: "密码不能为空",
                notEmpty: "密码不能为空",
                maxlength: "密码长度不得大于20位数",
                minlength: "密码长度不得小于6为数"
            },
            password: {
                required: "密码不能为空",
                notEmpty: "密码不能为空",
                equalTo: "两次输入密码需要相同",
                maxlength: "密码长度不得大于20位数",
                minlength: "密码长度不得小于6为数"
            }
        },
        submitHandler: function () {
            doPost("<%=request.getContextPath()%>/mobile/user/changePassword/update",$changeForm.serialize(), function (data) {
                if (data.status) {
                    alert("更新密码成功");
                    window.location.href = "<%=request.getContextPath()%>/mobile/user/center";
                } else {
                    alert(data.msg);
                }
            });
        }
    });
    function returnCenter() {
        window.location.href = "<%=request.getContextPath()%>/mobile/user/center";
    }
</script>
</body>
</html>