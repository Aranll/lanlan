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
<div class="yilan-center" style="height: 525px">
    <div class="logo-top">
        <img src="<%=request.getContextPath()%>/assets/web/img/logo.png" alt="" height="41" width="86">
    </div>
    <h3>用户登录</h3>
    <form id="loginForm">
        <div>
            <div class="input-box">
                <label for="telephone-number">手机号码</label>
                <input id="telephone-number" name="mobile" type="text" placeholder="手机号码">
            </div>
        </div>
        <div>
            <div class="input-box">
                <label for="telephone-number">密码</label>
                <input id="password" name="password" type="password" placeholder="帐号密码">
            </div>
        </div>
        <div class="login-prompt">
            <p>
                <span style="position: relative;left: 30px">
                    <a href="">忘记密码?</a>
                </span>
            </p>
        </div>
        <div class="button-box">
            <input type="button" class="button-style" href="<%=request.getContextPath()%>/register/page" value="注册">
            <input type="button" class="button-style" onclick="sumbitForm()" value="登录">
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


    function reForm() {
        $("#registerForm")[0].reset();
    }

    $.validator.addMethod("checkMobile",function(value,element,params){
        var checkPwd = /^[1][0-9]{10}$/;
        return this.optional(element)||(checkPwd.test(value));
    },"请输入正确的手机号码");

    $('#loginForm').validate({
        rules: {
            mobile: {
                required: true,
                notEmpty: true,
                checkMobile:true
            },
            password: {
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
            password: {
                required: "请输入密码",
                notEmpty: "请输入密码"
            }
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.appendTo(element.parent().parent()); //这里的element是录入数据的对象
        },

        submitHandler: function (form) {
            doPost("<%=request.getContextPath()%>/user/login",
                $('#loginForm').serialize(), function (data) {
                    if (data.status) {
                        window.location.href="<%=request.getContextPath()%>";
                    } else {
                        $('#loginMsg p').html(data.msg);
                        $('#loginMsg').css("display","block");
                    }
                });
        }
    })

    function sumbitForm() {
        $('#loginForm').submit();
    }

    function hideLoginMsg() {
        $('#loginMsg').css("display","none");
    }


</script>

<div id="loginMsg" style="display:none;width: 350px;height: 200px;background: #FFFFFF;text-align: center;position:fixed;top: 35%;left:50%;
    transform: translate(-50%,-50%);border-radius: 30px;
    box-shadow: 0 0 2px 2px #33AC79">
    <div style="margin-top: 60px">
        <p style="display: block;margin-bottom:40px;font-size: 22px !important;color:#00A963;"></p>
        <a style="margin: 20px auto;border-radius: 10px;display:block;color: #FFFFFF;background-color:#00A963;text-decoration: none;cursor: pointer;width: 200px;
        height: 50px;line-height: 50px;font-size: 21px" onclick="hideLoginMsg()">确定</a>
    </div>
</div>

</body>

</body>
</html>



