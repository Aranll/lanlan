<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <title>医览网-忘记密码</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/register.css">
</head>
<body>
<div class="container">
    <form class="form-group" name="updateForm">
        <div class="position-div validate">
            <p>手机号码</p>
            <input class="input-style" type="tel" name="mobile" placeholder="请输入手机号码">
        </div>
        <div class="position-div validate">
            <p>验证码</p>
            <input class="input-style" type="text" placeholder="请输入验证码" name = "code" >
            <img id="securityCode" src="<%=request.getContextPath()%>/captcha.png" onclick="refreshCode()" alt="securityCode">
        </div>
        <div class="position-div validate">
            <p>短信验证码</p>
            <input class="input-style" type="text" name="content" placeholder="请输入短信验证码">
            <input id="get_verification" name="captcha.content" type="button" value="获取验证码" onclick="getCaptcha()">
        </div>

        <div class="register-group">
            <a href="<%=request.getContextPath()%>/mobile/login">
                <input class="reset-button" type="button" value="取消">
            </a>
            <input class="register-button" type="submit" value="下一步" >
            <div class="clearfix"></div>
        </div>
    </form>
</div>
<script type="text/javascript">
    var mobile;
    var content;
    $updateForm = $("form[name='updateForm']");
    $updateForm.validate({
            errorClass: 'text-danger',
            rules: {
                mobile: {
                    mobile: true,
                    required: true
                },
                securityCode: {
                    required: true
                },
                'captcha.content': {
                    required: true
                }
            },
            messages: {
                mobile: {
                    mobile: "手机格式不符",
                    required: "请填写手机号码"
                },
                securityCode: {
                    required: "请填写图形验证码"
                },
                'captcha.content':{
                    required: "请填写手机验证码"
                }
            },
            submitHandler:function(){
                mobile=$("input[name='mobile']").val();
                content = $("input[name='content']").val();

                doPost("<%=request.getContextPath()%>/mobile/forgetPwd/verifyCaptcha","mobile="+mobile+"&captcha.content="+content,
                    function(data){
                        if(!data.status){
                            alert(data.msg);
                        }else{
                            window.location.href =
                                "<%=request.getContextPath()%>/mobile/forgetPwd/resetPwdPage?mobile="+mobile+"&captcha.content="+content;
                        }
                    }
                );
            }
        }
    );
    //更新图形验证码
    function refreshCode() {
        $("#securityCode").attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
    }

    //获取短信验证码
    function getCaptcha(){
        var code = $("input[name='code']").val();
        var mobile = $("input[name='mobile']").val();

        if (!mobile.match(/^1[34578]\d{9}$/)) {
            alert("请填写手机号码");
            return;
        }
        if (code == "") {
            alert("请先填写验证码");
            return;
        }
        doPost("<%=request.getContextPath()%>/mobile/forgetPwd/captcha",{mobile:mobile,code:code},function(data){
            if (data.status) {
                countDown();
                toastSuccess("手机验证码发送成功");
            } else {
                if (data.code == 1001) {
                    refreshCode();
                }
                alert(data.msg);
            }
        });
    }
    var second=60;
    var $getVerification=$("#get_verification");
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