<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/7
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-管理-软件管理</title>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <%@include file="../common/ztree.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./app_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">APP管理</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="searchForm">
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">编号：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="id" type="text" class="form-control" value="${id}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">名称：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamic[name]" type="text" class="form-control" value="${dynamic.name}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">所属：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input type="text" onclick="showMenu(this,0)" name="category.name" class="form-control">
                            <input name="category.id" type="hidden">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">首页推荐：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select name="recommend" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${recommend eq 0}">selected="selected"</c:if>>否</option>
                                <option value="1" <c:if test="${recommend eq 1}">selected="selected"</c:if>>是</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">热门：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select name="hot" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${hot==0}">selected="selected"</c:if>>否</option>
                                <option value="1" <c:if test="${hot==1}">selected="selected"</c:if>>是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_save})">
                                <button class="btn btn-success" type="button" data-toggle="modal"
                                        data-target="#saveApp">
                                    新增
                                </button>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_batchUpload})">
                            <a href="<%=request.getContextPath()%>/admin/app/app/batchUpload" target="_blank">
                                <button class="btn btn-success" type="button">
                                    批量
                                </button>
                            </a>
                            </sec:authorize>
                            <input class="btn btn-default pull-right" value="重置" type="button"
                                   onclick="resetSearch('searchForm')">
                            <input class="btn btn-info pull-right m-r-sm" value="搜索" type="submit">
                        </div>
                    </div>
                </form>
                <div class="panel panel-default m-b-none">
                    <table class="table text-center table-bordered table-striped m-b-none">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>所属</th>
                            <th>首页推荐</th>
                            <th>热门</th>
                            <th>二维码</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${apps.size() eq 0}">
                            <tr>
                                <td colspan="6">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${apps}" var="app">
                            <tr>
                                <td>${app.id}</td>
                                <td>${app.name}</td>
                                <td>${app.categories[0].name}&nbsp;&nbsp;|&nbsp;&nbsp;${app.categories[1].name}</td>
                                <td>
                                    <c:if test="${app.recommend==1}">
                                        是
                                    </c:if>
                                    <c:if test="${app.recommend==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${app.hot==1}">
                                        是
                                    </c:if>
                                    <c:if test="${app.hot==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>
                                    <a href="${app.qrCode}" target="_blank">
                                        <img src="${app.qrCode}" style="max-width: 350px;max-height: 80px"
                                             title="${app.qrCode}">
                                    </a>
                                </td>
                                <td>${app.createTime}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_get})">
                                        <button class="btn btn-warning btn-xs" onclick="detailsApp(${app.id})">
                                            详情
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_update})">
                                        <button class="btn btn-info btn-xs" onclick="updateApp(${app.id})">
                                            编辑
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_remove})">
                                        <button class="btn btn-danger btn-xs"
                                                onclick="remove(${app.id},'<%=request.getContextPath()%>/admin/app/app/remove')">
                                            删除
                                        </button>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <c:if test="${isPagination}">
                        <div class="col-xs-12">
                            <ul class="pagination pull-right">
                                    ${pagination}
                            </ul>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_save})">
<%--新增分类--%>
    <div class="modal fade" id="saveApp" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">新增APP</h4>
                </div>
                <div class="modal-body">
                    <form name="saveForm" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">名称：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="name" type="text" class="form-control" value="${name}">
                            </div>
                        </div>
                        <div class="form-group row">
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">来源：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="developer" type="text" class="form-control" value="${developer}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">所属：</label>
                            </div>
                            <div class="col-xs-9">
                                <input type="text" onclick="showMenu(this,0,2)" name="category.name"
                                       class="form-control">
                                <input name="category.id" type="hidden">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">热门：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="hot" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1" selected="selected">是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">首页推荐：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="recommend" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1" selected="selected">是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">二维码：</label>
                            </div>
                            <div class="col-xs-3">
                                <div id="previewDiv" class="img-preview col-xs-8 col-md-9">
                                </div>
                            </div>
                            <input id="uploadInput" class="hidden" type="file" onchange="uploadPreview(this)"
                                   accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                            <input id="url" type="hidden" name="qrCode"/>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-danger" onclick="submitSave()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <%--新增分类，模态框结束--%>
    <%--新增分类--%>
    <script type="text/javascript">
        var $saveForm = $("form[name='saveForm']");
        var saveValidate = $saveForm.validate({
            errorClass: 'text-danger',
            rules: {
                name: {
                    required: true,
                    notEmpty: true
                },
                qrCode: {
                    required: true,
                    url: true,
                    notEmpty: true
                },
                developer: {
                    required: true,
                    notEmpty: true
                },
                'category.name': {
                    required: true,
                }
            },
            messages: {
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                qrCode: {
                    required: "链接不能为空",
                    url: "必须输入正确格式的网址",
                    notEmpty: "链接不能为空"
                },
                developer: {
                    required: "来源不能为空",
                    notEmpty: "来源不能为空"
                },
                'category.name': {
                    required: "所属不能为空"
                },
                submitHandler:function () {
                    doPost("<%=request.getContextPath()%>/admin/app/app/save", $saveForm.serialize(), function (data) {
                        console.log(data);
                        if (data.status) {
                            $("#info_success").modal("show");
//                            setTimeout(function () {
//                                window.location.reload(true);
//                            },600);
                        } else {
                            $("#info").html(data.msg);
                            $("#info_fail").modal("show");
                        }
                    });
                 }
            }
        });
        function submitSave(){
            $saveForm.submit();
        }

        $('#saveApp').on('hidden.bs.modal', function (e) {
            saveValidate.resetForm();
            $saveForm[0].reset();
        });
        //使用ajax提交表单，保存软件分类

    </script>
    <%--新增分类结束--%>
    <%--新增文件上传--%>
    <script type="text/javascript">
        var client = new OSS.Wrapper({
            accessKeyId: "${token.accessKeyId}",
            accessKeySecret: "${token.accessKeySecret}",
            stsToken: "${token.securityToken}",
            endpoint: "oss-cn-shenzhen.aliyuncs.com",
            bucket: "shuttle-yilan"
        });

        var images = [];
        var $previewDiv = $("#previewDiv");


        $(function () {
            updatePreviewDiv();
        })

        function updatePreviewDiv() {
            var html = "";
            $.each(images, function (index, image) {
                html += "<label>" +
                    "<img src='" + image + "'/>" +
                    "<a class='delete' href='javascript:deleteUpload(" + index + ")'>删除</a>" +
                    "</label>";
            });
            if (images.length === 0) {
                html += "<label>" +
                    "<a id='add' class='add' href=\"" + "javascript:$('#uploadInput').click()" + "\">" +
                    "<i class='fa fa-plus'></i>" +
                    "</a>" +
                    "</label>";
            }
            $previewDiv.html(html);
        }

        function uploadPreview(file) {
            if (file.files && file.files[0]) {
                $("#add").html("<i class='fa fa-spin fa-spinner'></i>");
//            这是需要修改图片路径，模块
                client.multipartUpload("app/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                    progress: function*(p) {
                        console.log('Progress: ' + p);
                    }
                }).then(function (result) {
                    console.log(result);
                    images.push(result.url + "/watermark");
                    $("#url").val(result.url + "/watermark");
                    updatePreviewDiv();
                }).catch(function (err) {
                    console.log(err);
                });
            }
            $('#uploadInput').val("");
        }

        function deleteUpload(index) {
            images.splice(index, 1);
            updatePreviewDiv();
        }
    </script>
    <%--新增文件上传结束--%>
        </sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_get})">
<%--软件详情--%>
    <div class="modal fade" id="detailsApp" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">APP详情</h4>
                </div>
                <div class="modal-body">
                    <form name="detailsForm" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">编号：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="id"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">名称：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="name"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">来源：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="developer"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">分类名称：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="category.name"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">热门：</label>
                            </div>
                            <div class="col-xs-3">
                                <span name="hotDesc"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">首页推荐：</label>
                            </div>
                            <div class="col-xs-3">
                                <span name="recommendDesc"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">二维码：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="qrCode"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-danger" onclick="submitDetails()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <%--软件详情结束--%>
    <%--软件详情--%>
    <script type="text/javascript">
        var $detailsForm = $("form[name='detailsForm']");
        var detailsAppId;
        function detailsApp(id) {
            detailsAppId = id;
            //获取软件信息
            doPost("<%=request.getContextPath()%>/admin/app/app/get", {id: detailsAppId}, function (data) {
                console.log(data);
                var _data = data.data;
                console.log(_data);
                if (data.status) {
                    $detailsForm.find("span[name='id']").html(_data.id);
                    $detailsForm.find("span[name='name']").html(_data.name);
                    $detailsForm.find("span[name='developer']").html(_data.developer);
                    $detailsForm.find("span[name='category.name']").html(_data.categories[0].name + "&nbsp;&nbsp;|&nbsp;&nbsp;" + _data.categories[1].name);
                    $detailsForm.find("span[name='hotDesc']").html(_data.hotDesc);
                    $detailsForm.find("span[name='recommendDesc']").html(_data.recommendDesc);
                    $detailsForm.find("span[name='qrCode']").html("<label>" + "<img src='" + _data.qrCode + "' style='max-width:350px;max-height:80px'/>" + "</label>")
//                    $detailsForm.find("span[name='createTime']").html(_data.createTime);
//                    $detailsForm.find("span[name='updateTime']").html(_data.updateTime);
                    $("#detailsApp").modal("show");
                } else {
                    if (data.msg != null && data.msg.length != 0) {
                        $("#info").html(data.msg);
                    }
                    $("#info_fail").modal('show');
                }
            });
        }
        function submitDetails() {
            $("#detailsApp").modal("hide");
        }
    </script>
    <%--软件详情结束--%>
        </sec:authorize>

<sec:authorize access="hasAnyRole(${sessionScope.sec_op.app_app_update})">
    <%--编辑软件开始--%>
    <div class="modal fade" id="updateApp" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">编辑APP</h4>
                </div>
                <div class="modal-body">
                    <form name="updateForm" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">编号：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="id" type="text" class="form-control" value="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">名称：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="name" type="text" class="form-control" value="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">来源：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="developer" type="text" class="form-control" value="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">所属：</label>
                            </div>
                            <div class="col-xs-9">
                                <input type="text" onclick="showMenu(this,0,2)" name="category.name"
                                       class="form-control">
                                <input name="category.id" type="hidden">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">热门：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="hot" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1" selected="selected">是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">首页推荐：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="recommend" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1" selected="selected">是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">二维码：</label>
                            </div>
                            <div class="col-xs-3">
                                <div id="previewUpdateDiv" class="img-preview col-xs-8 col-md-9">
                                </div>
                            </div>
                            <input id="updateUploadInput" class="hidden" type="file"
                                   onchange="uploadUpdatePreview(this)"
                                   accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                            <input id="updateUrl" type="hidden" name="qrCode"/>
                        </div>
                    </form>
                </div>
                <div class="clearfix"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-danger" onclick="submitUpdate()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <%--编辑软件结束--%>
    <%--编辑软件分类--%>
    <script type="text/javascript">
        var $updateForm = $("form[name='updateForm']");
        var updateAppId;
        var updateValidate = $updateForm.validate({
            errorClass: 'text-danger',
            rules: {
                id: {
                    required: true,
                    notEmpty: true
                },
                name: {
                    required: true,
                    notEmpty: true
                },
                qrCode: {
                    url: true,
                    required: true,
                    notEmpty: true
                },
                'category.name': {
                    required: true
                },
                developer: {
                    required: true,
                    notEmpty: true
                },
                hot: {
                    required: true,
                    notEmpty: true
                },
                recommend: {
                    required: true,
                    notEmpty: true
                }
            },
            messages: {
                id: {
                    required: "编号不能为空",
                    notEmpty: "编号不能为空"
                },
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                seq: {
                    required: "顺序不能为空",
                    digits: "顺序必须是整数"
                },
                qrCode: {
                    required: "二维码不能为空",
                    url: "请输入正确格式的网址",
                },
                'category.name': {
                    required: "所属不能为空"
                },
                status: "请选择是否开通"
            },
            submitHandler:function () {
                doPost("<%=request.getContextPath()%>/admin/app/app/update", $updateForm.serialize(), function (data) {
                    if (data.status) {
                        $("#info_success").modal("show");
                        setTimeout(function () {
                            window.location.reload(true);
                        },600);
                    } else {
                        $("#info").html(data.msg);
                        $("#info_fail").modal("show");
                    }
                });
            }
        });
        function submitUpdate(){
            $("#updateForm").submit();
        }

        function updateApp(id) {
            updateAppId = id;
            updateValidate.resetForm();
            $updateForm[0].reset();
            //获取软件信息
            doPost("<%=request.getContextPath()%>/admin/app/app/get", {id: updateAppId}, function (data) {
                var _data = data.data
                if (data.status) {
                    $updateForm.find("input[name='id']").val(_data.id);
                    $updateForm.find("input[name='name']").val(_data.name);
                    $updateForm.find("input[name='developer']").val(_data.developer);
                    $updateForm.find("input[name='qrCode']").val(_data.qrCode);
                    $updateForm.find("input[name='category.name']").val(_data.category.name);
                    $updateForm.find("input[name='category.id']").val(_data.category.id);
                    if (_data.hot == 0) {
                        $updateForm.find("select[name='hot']").val(0);
                    } else {
                        $updateForm.find("select[name='hot']").val(1);
                    }
                    if (_data.recommend == 0) {
                        $updateForm.find("select[name='recommend']").val(0);
                    } else {
                        $updateForm.find("select[name='recommend']").val(1);
                    }
                    //初始化数组
                    updateImages.length = 0;
                    //初始化二维码
                    if (_data.qrCode != null)
                        updateImages.push(_data.qrCode);
                    updatePreviewUpdateDiv();
                    $("#updateApp").modal("show");
                } else {
                    if (data.msg != null && data.msg.length != 0) {
                        $("#info").html(data.msg);
                    }
                    $("#info_fail").modal('show');
                }
            });


        }


        //使用ajax提交表单，更新软件分类

    </script>
    <%--编辑软件分类结束--%>
    <%--编辑文件上传--%>
    <script type="text/javascript">

        var updateImages = [];
        var $previewUpdateDiv = $("#previewUpdateDiv");


        function updatePreviewUpdateDiv() {
            var html = "";
            $.each(updateImages, function (index, image) {
                html += "<label>" +
                    "<img src='" + image + "'/>" +
                    "<a class='delete' href='javascript:deleteUpload(" + index + ")'>删除</a>" +
                    "</label>";
            });
            if (updateImages.length === 0) {
                html += "<label>" +
                    "<a id='updateAdd' class='add' href=\"" + "javascript:$('#updateUploadInput').click()" + "\">" +
                    "<i class='fa fa-plus'></i>" +
                    "</a>" +
                    "</label>";
            }
            $previewUpdateDiv.html(html);
        }

        function uploadUpdatePreview(file) {
            if (file.files && file.files[0]) {
                $("#updateAdd").html("<i class='fa fa-spin fa-spinner'></i>");
//            这是需要修改图片路径，模块
                client.multipartUpload("app/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                    progress: function*(p) {
                        console.log('Progress: ' + p);
                    }
                }).then(function (result) {
                    console.log(result);
                    updateImages.push(result.url + "/watermark");
                    $("#updateUrl").val(result.url + "/watermark");
                    updatePreviewUpdateDiv();
                }).catch(function (err) {
                    console.log(err);
                });
            }
            $('#updateUploadInput').val("");
        }

        function deleteUpload(index) {
            updateImages.splice(index, 1);
            updatePreviewUpdateDiv();
        }
    </script>
    <%--编辑文件上传结束--%>
        </sec:authorize>

    <%--选择分类--%>
    <div id="selectCategory" class="modal fade" data-backdrop="static" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                </div>
                <div class="modal-body">
                    <div id="menuContent2" myStatus="hidden" class="zTreeDemoBackground ">
                        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <%--选择分类结束--%>
    <%--选择分类管理--%>
    <script>

        //type 0为展示所有节点，1为展示一级节点，2为展示一二级节点，默认为0
        var type;

        //type1 0为可点击所有节点，x为x级节点可以点击,默认为0
        var type1;

        var targetElement;
        //ztree设置
        var zTreeObj, setting = {
            check: {
                enable: false
            },
            async: {
                enable: true,
                type: "post",
                url: asyncUrl,
                dataFilter: asyncFilter
            },
            callback: {
                onClick: onClickNode,
                beforeAsync: beforeAsync,
                onAsyncSuccess: onAsyncSuccess
            }
        };
        var zTreeNodes = [];
        function asyncUrl() {
            return "<%=request.getContextPath()%>/admin/app/app/tree";
        }
        function onAsyncSuccess() {

            if (type == 1) {
                var allNodes = zTreeObj.getNodes();

                for (var i = 0; i < allNodes.length; i++) {
                    zTreeObj.removeChildNodes(allNodes[i]);
                }
            } else if (type == 2) {
                var allNodes = zTreeObj.getNodes();
                for (var i = 0; i < allNodes.length; i++) {
                    var childNodes = allNodes[i].children;
                    for (var j = 0; j < childNodes[j].length; j++) {
                        zTreeObj.removeChildNodes(allNodes[i]);
                    }
                }
            }
        }

        function beforeAsync(treeId, treeNode) {
            if (treeNode === undefined)
                return false;
        }

        function asyncFilter(treeId, parentNode, responseData) {
            if (responseData.status) {
                return responseData.data;
            } else {
                alert(responseData.msg);
                return [];
            }
        }

        $(document).ready(function () {
            zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
        });


        function ZtreeNode(id, pId, name) {//定义ztree的节点类
            this.id = id;
            this.pId = pId;
            this.name = name;
        }
        var childZNode = new ZtreeNode('0', null, '根');
        //显示菜单
        function showMenu(e, t, t1) {
            targetElement = e;
            if (t === undefined) {
                type = 0;
            } else {
                type = t;
            }
            if (t1 === undefined) {
                type1 = 0;
            } else {
                type1 = t1;
            }
            zTreeObj.reAsyncChildNodes(null, "refresh");
            zTreeObj.addNodes(null, childZNode, true);

            $("#selectCategory").modal();
            $("#menuContent2").css({left: "15px", top: "34px"}).slideDown("fast");
        }

        //节点点击事件
        function onClickNode(e, treeId, treeNode) {
            var sNodes = zTreeObj.getSelectedNodes();
            if (sNodes.length > 0) {
                if (type1 != 0 && sNodes[0].level != type1 - 1) {
                    return;
                }
                $(targetElement).next().attr("value", (sNodes[0].id));
                $(targetElement).val(sNodes[0].name);
            }
            $("#selectCategory").modal('hide');
        }
    </script>
    <%--选择分类管理结束--%>

    <%--操作提示及删除--%>
    <jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
    <%--操作提示结束--%>

</body>
</html>
