<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-管理-网站管理-批量上传</title>
    <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../../assets/admin/css/appAranl.css">

</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./website_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">网站管理>文件批量上传</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="form">
                    <div class="col-xs-12 col-md-6 col-center-block">
                        <div class="form-group row">
                            <div class="col-xs-3 col-md-2 text-right">
                                <label class="control-label required">文件选择：</label>
                            </div>
                            <div class="col-xs-9 col-md-6">
                                <input class="form-control" type="file" name="file" id="file"
                                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-12 col-md-8">
                                <input type="button" class="btn btn-info pull-right " value="文件上传" onclick="templateUpload()">
                                <a class="btn btn-success pull-right m-r-sm" href="<%=request.getContextPath()%>/admin/website/template/download">模板下载</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%--信息提示操作成功--%>
<div class="modal fade" id="upload_success" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger" id="info-success">上传成功，是否确定提交？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-success" onclick="submitForm()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--信息提示操作成功--%>
<script type="text/javascript">
    var $form = $("#form");
    function submitForm() {
        $form.submit();
        window.location.href = "${redirectUrl}";
    }
function templateUpload(){
    var files = $("#file").prop('files');
    if (files && files[0]) {
        var formData = new FormData();
        formData.append('file', files[0]);
        console.log(files[0]);
        uploadFile('<%=request.getContextPath()%>/admin/website/template/upload', formData, function (data) {
            if(!data.status) {
                $("#info").html(data.msg);
                $("#info_fail").modal("show");
            }else{
                $("#upload_success").modal("show");
            }
        });
    }
}

</script>
<%--操作提示及删除--%>
<jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
<%--操作提示结束--%>
</body>
</html>
