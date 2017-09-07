<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="yilan-head">
    <div class="yilan-nav">
        <ul id="a_nav" class="nav navbar-nav">
            <li>
                <a href="<%=request.getContextPath()%>/index">首页</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/app">医学软件</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/periodical">医学期刊</a>
            </li>
            <li id="pictureNav" style="position: relative">
                <a id="pictureA" href="<%=request.getContextPath()%>/picture">医学图片<img style="margin-left:4px;margin-top: -6px" src="<%=request.getContextPath()%>/assets/web/img/down.png"></a>
                <div id="pictureDiv" class="picture-nav picture-nav-hidden">
                    <div class="ff-nav">
                        <c:forEach items="${pictureCategories}" var="p">
                            <div class="ff-item" style="position: relative">
                                <a href="<%=request.getContextPath()%>/picture?category.id=${p.id}">${p.name}</a>
                                <div class="fs-item hidden">
                                    <c:forEach items="${p.children}" var="c">
                                        <div class="fsf-item">
                                            <div class="left-item">
                                                <a href="<%=request.getContextPath()%>/picture?category.id=${c.id}">${c.name}</a>
                                            </div>
                                            <div class="right-item">
                                                <c:forEach items="${c.children}" var="cc">
                                                    <a href="<%=request.getContextPath()%>/picture?category.id=${cc.id}">${cc.name}</a>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/book">医学图书</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/video">医学讲坛</a>
            </li>
        </ul>
        <c:if test="${empty user}">
            <a class="pull-right" href="<%=request.getContextPath()%>/user/register/page">注册</a>
            <a class="pull-right" onclick="onc()">登录</a>
        </c:if>
        <c:if test="${not empty user}">
            <a class="user-name pull-right" style="width: 100px" href="<%=request.getContextPath()%>/user/center">个人中心</a>
            <div class="user-name pull-right" style="position: relative;">
                <a class="title">
                    <span style="width: 80%;font-size: 18px">${fn:substring(user.mobile, 0, 3)}***${fn:substring(user.mobile,7,11)}</span>
                    <span class="glyphicon glyphicon-menu-down" style="vertical-align: top"></span>
                </a>
                <div id="user-name-hide-box" style="display: none">
                    <a onclick="logout()">退出登录</a>
                </div>
            </div>
        </c:if>
    </div>
</div>
<div class="modal fade" id="signUp" data-backdrop="static" role="dialog">
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
                <form id="loginForm">
                    <div style="position: relative;margin-bottom: 20px">
                        <img src="<%=request.getContextPath()%>/assets/web/img/user.png"
                             style="position: absolute;left: 22px;top:10px">
                        <input type="text" name="mobile" style="padding-left: 42px;width: 440px;height: 50px;"
                               placeholder="请输入手机号码">
                    </div>
                    <div style="position: relative;margin-bottom: 20px">
                        <img src="<%=request.getContextPath()%>/assets/web/img/key.png"
                             style="position: absolute;left: 22px;top:10px">
                        <input type="password" name="password" style="padding-left: 42px;width: 440px;height: 50px;"
                               placeholder="请输入密码">
                    </div>
                    <div style="text-align: right">
                        <span class="pull-right" style="margin: -10px 15px -15px 0"><a
                                href="<%=request.getContextPath()%>/user/forgetPasswordPage"
                                style="color: #33AC79;cursor: pointer;text-decoration: none">忘记密码?</a></span>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="border: 0;clear: both;margin-top: -10px;text-align: center">
                <button type="button" class="btn"
                        style="width: 440px;height: 50px;background-color: #33AC79;color: #FFFFFF"
                        onclick="login()">登录
                </button>
            </div>
            </form>
        </div>
    </div>
</div>

<script>

    function onc() {
        $('#loginForm')[0].reset();
        $('#signUp').modal("show");
    }

    var $saveForm = $("form[id='loginForm']");
    var saveValidate = $saveForm.validate({
        errorClass: 'text-danger',
        rules: {
            mobile: {
                required: true,
                notEmpty: true
            },
            password: {
                required: true,
                notEmpty: true
            }
        },
        messages: {
            mobile: {
                required: "请输入手机号码",
                notEmpty: "请输入手机号码"
            },
            password: {
                required: "请输入密码",
                notEmpty: "请输入密码"
            }
        },        //使用ajax提交表单，保存网站分类
        submitHandler: function (saveForm) {
            doPost("<%=request.getContextPath()%>/user/login", $(saveForm).serialize(), function (data) {
                $('#password-error').remove();
                if (data.status) {
                    window.location.reload();
                } else {
                    var msg = $('<label id="password-error" class="text-danger" for="password">' + data.msg + '</label>');
                    $('#signUp .modal-header').append(msg);
                }
            });
        }
    });
    function login() {
        $saveForm.submit();
    }
    function logout() {
        $.ajax({
            url:"<%=request.getContextPath()%>/user/logout",
            type: 'POST',
            dataType: 'json',
            success:function(date){
                if(date.status){
                    window.location.href="<%=request.getContextPath()%>";
                }
            }

        })
    }

</script>