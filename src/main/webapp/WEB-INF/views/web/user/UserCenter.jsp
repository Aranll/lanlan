<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>医览网</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>

<div class="yilan-center clearfix">
    <a href="http://www.baidu.com"><img src="<%=request.getContextPath()%>/assets/web/img/logo.png"
                                        style="margin-top: 19px;margin-left: 8px"></a>
    <div style="text-align: center;font-size: 18px;color: #333333 ">
        <h3 style="margin-bottom: 30px;margin-top: 0">个人中心</h3>
        <div class="center-box" style="text-align: left;width: 640px;margin: 0 auto">
            <div class="input-box">
                <label style="width: 80px;text-align: right">手机号码</label>
                <input type="text" class="user-center-input"
                       value="${fn:substring(user.mobile, 0, 3)}***${fn:substring(user.mobile,7,11)}" readonly>
            </div>
            <div class="input-box">
                <label style="width: 80px;text-align: right">邮箱</label>
                <input type="text" class="user-center-input" value="${user.email}" readonly>
                <a onclick="showReEmail()">修改</a>
            </div>
            <div class="input-box">
                <label style="width: 80px;text-align: right">密码</label>
                <input type="text" class="user-center-input" value="******" readonly>
                <a onclick="showRePassword()">修改</a>
            </div>
            <div class="input-box">
                <label style="width: 80px;text-align: right">会员到期</label>
                <input type="text" class="user-center-input"
                <c:if test="${user.vipLevel eq 1 }">
                       value="${user.vipExpire}"
                </c:if>
                <c:if test="${user.vipLevel eq 0 }">
                       value="请升级会员"
                </c:if> readonly>
                <a>会员续费</a>
            </div>
        </div>
    </div>

</div>

<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>
<script>
    function showRePassword() {
        $("#resetPswForm")[0].reset();
        $("#resetPassword").modal('show');
    }
    function showReEmail() {
        $("#resetEForm")[0].reset();
        $("#resetEmail").modal('show');
    }
    function resetPassword() {
        $("#resetPswForm").submit();
    }
    function resetEmail() {
        $("#resetEForm").submit();
    }
</script>


<div class="modal fade" id="resetPassword" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document" style="margin-top: 160px;width: 500px;text-align: center">
        <div class="modal-content">
            <div class="modal-header" style="border: 0">
                <button type="button" style="color: #00A963;" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="text-align: center;font-size: 24px;color: #333333">重置密码</h4>
            </div>
            <div class="modal-body" style="border: 0;">
                <form id="resetPswForm">
                    <div style="position: relative;margin-bottom: 20px">
                        <label for="oldPassword" style="width: 100px;font-size: 18px">旧密码</label>
                        <input type="password" id="oldPassword" name="oldPassword" style="padding-left: 20px;width: 300px;height: 50px;">
                    </div>
                    <div style="position: relative;margin-bottom: 20px">
                        <label for="password" style="width: 100px;font-size: 18px">新密码</label>
                        <input type="password" id="password" name="password" style="padding-left: 20px;width: 300px;height: 50px;">
                    </div>
                    <div style="position: relative;margin-bottom: 20px">
                        <label for="enterPassword" style="width: 100px;font-size: 18px">确认密码</label>
                        <input type="password" id="enterPassword" name="enterPassword" style="padding-left: 20px;width: 300px;height: 50px;">
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="border: 0;clear: both;margin-top: -10px;text-align: center">
                <button type="button" class="btn"
                        style="width: 400px;height: 50px;background-color: #33AC79;color: #FFFFFF"
                        onclick="resetPassword()">确定
                </button>
            </div>
            </form>
        </div>
    </div>
</div>
<script>
    var $pswForm = $("form[id='resetPswForm']");
    var pswValidate = $pswForm.validate({
        errorClass: 'text-danger',
        rules: {
            oldPassword: {
                required: true,
                notEmpty: true
            },
            password: {
                required: true,
                notEmpty: true
            },
            enterPassword: {
                required: true,
                notEmpty: true,
                equalTo: "#password"
            }
        },
        messages: {
            oldPassword: {
                required: "请输入旧密码",
                notEmpty: "请输入旧密码"
            },
            password: {
                required: "请输入密码",
                notEmpty: "请输入密码"
            },
            enterPassword: {
                required: "请输入确认密码",
                notEmpty: "请输入确认密码",
                equalTo: "两次密码不一致"
            }
        },        //使用ajax提交表单，保存网站分类
        submitHandler: function (saveForm) {
            doPost("<%=request.getContextPath()%>/user/password/update", $('#resetPswForm').serialize(), function (data) {
                if (data.status) {
                    alert("修改成功！");
                    window.location.reload();
                } else {
                   alert(data.msg);
                }
            });
        }
    });
</script>

<div class="modal fade" id="resetEmail" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document" style="margin-top: 160px;width: 500px;text-align: center">
        <div class="modal-content">
            <div class="modal-header" style="border: 0">
                <button type="button" style="color: #00A963;" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="text-align: center;font-size: 24px;color: #333333">用户登录</h4>
            </div>
            <div class="modal-body" style="border: 0;">
                <form id="resetEForm">
                    <div style="position: relative;margin-bottom: 20px">
                        <label for="email" style="width: 100px;font-size: 18px">新邮箱</label>
                        <input type="text" id="email" name="email" style="padding-left: 20px;width: 300px;height: 50px;">
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="border: 0;clear: both;margin-top: -10px;text-align: center">
                <button type="button" class="btn"
                        style="width: 400px;height: 50px;background-color: #33AC79;color: #FFFFFF"
                        onclick="resetEmail()">确定
                </button>
            </div>
            </form>
        </div>
    </div>
</div>

<script>
    var $emailForm = $("form[id='resetEForm']");
    var emailValidate = $emailForm.validate({
        errorClass: 'text-danger',
        rules: {
            email: {
                required: true,
                notEmpty: true,
                email:true
            }
        },
        messages: {
            email: {
                required: "请输入邮箱",
                notEmpty: "请输入邮箱",
                email: "请输入正确的邮箱"
            }
        },        //使用ajax提交表单，保存网站分类
        submitHandler: function (saveForm) {
            doPost("<%=request.getContextPath()%>/user/update", $('#resetEForm').serialize(), function (data) {
                if (data.status) {
                    alert("修改成功！");
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            });
        }
    });
</script>

</body>

</html>