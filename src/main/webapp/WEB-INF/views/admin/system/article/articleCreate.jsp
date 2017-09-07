<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: GustinLau
  Date: 2017-05-02
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-系统管理-文章管理-新增文章</title>
    <%@include file="../../common/head.jsp" %>
    <%@include file="../../common/validate.jsp" %>
    <%@include file="../../common/ueditor.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../../common/header.jsp" %>
    <%@include file="../system_nav.jsp" %>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_create})">

    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3 inline">
                    文章管理 > 新增文章
                </h1>
                <a class="btn btn-default pull-right" style="margin-top: -3px" href="${redirectUrl}">
                    返回
                </a>
                <button class="btn btn-success pull-right" style="margin-top: -3px;margin-right: 5px"
                        onclick="submitForm()">
                    确定
                </button>
            </div>
            <div class="wrapper-md">
                <div class="panel panel-default m-b-none">
                    <div class="col-xs-12  b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <form name="form" class="form-horizontal">
                                <div class="padder text-right pull-left">
                                    <label class="control-label font-bold required">文章标题：</label>
                                </div>
                                <div class="col-xs-8">
                                    <input name="title" type="text" class="form-control">
                                </div>
                                <input type="hidden" name="content" id="content">
                                <input type="hidden" name="publisher" value="${sessionScope.session_staff.name}"/>
                            </form>
                        </div>
                    </div>
                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <label class="font-bold required">
                            内容
                        </label>
                    </div>
                    <div class="col-xs-12 b  line-dashed wrapper-sm padder">
                        <div id="container" style="height: 500px"></div>
                    </div>
                    <div class="clearfix">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var ue = UE.getEditor('container', { initialFrameWidth: null });

    var $form = $("form[name='form']");
    $form.validate({
        errorClass: 'text-danger',
        rules: {
            title: {
                required: true,
                notEmpty: true
            }
        },
        messages: {
            title: {
                required: "文章标题不能为空",
                notEmpty: "文章标题不能为空"
            }
        },
        submitHandler: function (form) {
            $("#content").val(ue.getContent());
            doPost("<%=request.getContextPath()%>/admin/system/article/create", $(form).serialize(), function (data) {
                if (data.status) {
                    $("#info_success").modal("show");
//                    setTimeout(function(){
//                        window.location.reload(true);
//                    },800);
                } else {
                    $("#info").html(data.msg);
                    $("#info_fail").modal("show");
                }
            });
        }
    });

    function submitForm() {
        $form.submit();
    }
</script>
<%--操作提示及删除--%>
<jsp:include page="../../common/operationTip.jsp" flush="false"></jsp:include>
<%--操作提示结束--%>
</sec:authorize>
</body>
</html>
