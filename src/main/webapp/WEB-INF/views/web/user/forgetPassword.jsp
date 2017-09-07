<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>医览网</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/web/css/cg-style.css">
    <script src="<%=request.getContextPath()%>/assets/web/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/web/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/assets/web/js/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/web/js/jquery.validate.extends.js"></script>
    <script src="<%=request.getContextPath()%>/assets/web/js/app.js"></script>
    <style>
        input.error {
            border: 1px solid red !important;
        }

        label.error {
            font-size: 14px !important;
            color: red !important;
            line-height: 10px;
            width: auto !important;
        }
    </style>
</head>
<body>
<div class="yilan-center" style="height: 570px">
    <div class="logo-top">
        <img src="<%=request.getContextPath()%>/assets/web/img/logo.png" alt="" height="41" width="86">
    </div>
    <h3>用户注册</h3>
    <form id="registerForm">
        <div>
            <div class="input-box">
                <label for="telephone-number">手机号码</label>
                <input id="telephone-number" name="mobile" type="tel" placeholder="手机号码">
            </div>
        </div>
        <div>
            <div class="input-box">
                <label for="verification-code">验证码</label>
                <input id="verification-code" name="textCaptcha" type="text" placeholder="图片验证码">
                <img id="verification-img" name="textCaptcha" style="cursor: pointer" onclick="reCaptcha(this)" alt="">
            </div>
        </div>
        <div>
            <div class="input-box">
                <label for="sms-verification">短信验证码</label>
                <input id="sms-verification" name="captcha.content" type="text" placeholder="短信验证码">
                <input type="button" id="getPhoneCaptcha" value="获取验证码" style="text-align: center;padding: 0">
            </div>
        </div>
        <div class="button-box">
            <input style="position: relative;left: 30px" type="button" class="button-style" onclick="reForm()" value="重置">
            <input style="position: relative;left: 40px" type="button" class="button-style" onclick="sumbitForm()" value="下一步">
        </div>
    </form>
</div>
<footer>
    <div class="footer-box">
        <div class="footer-box-left">
            <img src="<%=request.getContextPath()%>/assets/web/img/logowhite.png" alt="" width="108" height="51">
            <ul>
                <li>
                    <a href="${sysProp.aboutUs}">关于我们</a>
                </li>
                <c:forEach items="${sysProp.partnerList}" var="p" varStatus="i">
                    <li>
                        <a href="${sysProp.partnerUrlList[i.index]}">${p}</a>
                    </li>
                </c:forEach>
            </ul>
            <div id="horizontal-line"></div>
            <section style="text-align: right;margin-top: 3px">
                <span style="text-align: justify;margin-right: 10px;color: #FFFFFF;display: inline-block">${sysProp.copyright}</span>
                <span style="margin-right: 10px;color: #FFFFFF;display: inline-block"> ${sysProp.recordNumber}</span>
                <span style="color: #FFFFFF;display: inline-block">${sysProp.license}</span>
            </section>
        </div>
        <div class="footer-box-right">
            <img src="<%=request.getContextPath()%>/assets/web/img/codesmall.png" alt="" width="147" height="147">
            <ul>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/phone.png" alt="">
                        <span>${sysProp.phone}</span>
                    </div>
                </li>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/qq.png" alt="">
                        <span>${sysProp.qq}</span>
                    </div>
                </li>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/mail.png" alt="">
                        <span>${sysProp.email}</span>
                    </div>
                </li>
                <li>
                    <div>
                        <img class="address-img" src="<%=request.getContextPath()%>/assets/web/img/address.png" alt="">
                        <span>${sysProp.address}</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</footer>

<script>

    $(function () {
        $('#verification-img').attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
    })

    function reForm() {
        $("#registerForm")[0].reset();
    }

    function reCaptcha(e) {
        $(e).attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
    }

    $.validator.addMethod("checkMobile",function(value,element,params){
        var checkPwd = /^[1][0-9]{10}$/;
        return this.optional(element)||(checkPwd.test(value));
    },"请输入正确的手机号码");

    $('#registerForm').validate({
        rules: {
            mobile: {
                required: true,
                notEmpty: true,
                checkMobile:true
            },
            textCaptcha: {
                required: true,
                notEmpty: true
            },
            "captcha.content": {
                required: true,
                notEmpty: true
            }
        },
        messages: {
            mobile: {
                required: "请输入手机号码",
                notEmpty: "请输入手机号码",
                isMoblie: "请输入正确的手机号码"
            },
            textCaptcha: {
                required: "请输入验证码",
                notEmpty: "请输入验证码"
            },
            "captcha.content": {
                required: "请输入短信验证码",
                notEmpty: "请输入短信验证码"
            }
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.appendTo(element.parent().parent()); //这里的element是录入数据的对象
        },

        submitHandler: function (form) {
            doPost("<%=request.getContextPath()%>/user/forgetPassword",
                $('#registerForm').serialize(), function (data) {
                    if (data.status) {
                        window.location.href="<%=request.getContextPath()%>/user/forgetPasswordAndResetPage"
                    } else {
                        alert(data.msg);
                    }
                });
        }
    })
    $(function () {
        $('#getPhoneCaptcha').on('click', function () {
            getCapt();
        })
    })
    function sumbitForm() {
        $('#registerForm').submit();
    }

    function getCapt() {
        $('#captchaMsg').remove();
        var b = $("#telephone-number").val();
        if (b == '' || b == undefined || !b.match(/^[1][0-9]{10}$/)) {
            var msg = $('<label id="captchaMsg"  class="error" for="verification-code">请先输入正确手机号码</label>');
            $('#telephone-number').parent().parent().append(msg);
            return;
        }
        var a = $('#verification-code').val();
        if (a == '' || a === undefined) {
            var msg = $('<label id="captchaMsg"  class="error" for="verification-code">请先输入验证码</label>');
            $('#verification-code').parent().parent().append(msg);
            return;
        }
        doPost("<%=request.getContextPath()%>/user/password/captcha/get",
            $('#registerForm').serialize(), function (data) {
                $('#captchaMsg').remove();
                $('#mobileMsg').remove();
                $('#verificationMsg').remove();
                if (data.status) {
                    changeCaptchaClass();
                } else if (data.code == 777) {
                    $('#verification-img').attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
                    var msg = $('<label id="captchaMsg"  class="error">' + data.msg + '</label>');
                    $('#verification-code').parent().parent().append(msg);
                } else if(data.code == 1002){
                    $('#verification-img').attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
                    var msg = $('<label id="captchaMsg"  class="error">' + data.msg + '</label>');
                    $('#telephone-number').parent().parent().append(msg);
                } else {
                    $('#verification-img').attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
                    var msg = $('<label id="captchaMsg"  class="error">' + data.msg + '</label>');
                    $('#getPhoneCaptcha').parent().parent().append(msg);
                }
            });
    }

    function changeCaptchaClass() {
        b = $('#getPhoneCaptcha');
        b.unbind('click');
        b.css('background', "#666666");
        b.css('border', "none");
        var i = 60;
        b.val(i-- + "S后重新获取");
        var s = setInterval(function () {
            b.val(i + "S后重新获取");
            if (i <= 0) {
                b.css('background', "#33AC79");
                b.css('border', '1px solid #33AC79');
                b.val("重新获取");
                clearInterval(s);
                $('#verification-img').attr("src", "<%=request.getContextPath()%>/captcha.png?" + Math.random());
                $('#getPhoneCaptcha').on('click', function () {
                    getCapt();
                })
            }
            i--;
        }, 1000)
    }

</script>


</body>

</body>
</html>



