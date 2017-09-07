<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-管理-图片管理-批量上传</title>
    <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../../assets/admin/css/appAranl.css">
</head>
<body>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_picture_batchUpload})">
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./picture_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">图片管理>文件批量上传</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="form">
                    <div class="col-xs-24 col-md-12  col-center ">
                        <div class="form-group row ">
                            <div class="col-xs-6 col-md-3 text-right">
                            </div>
                            <div class="col-xs-8 col-md-4">
                                <input class="form-control" type="file" name="file" id="file"
                                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                            </div>
                            <div class="col-xs-10 col-md-5">
                                <input type="button" class="btn btn-success m-r-sm" value="模板下载" onclick="templateForm()">
                                <input type="button" class="btn btn-info" value="文件提交" onclick="templateUpload()">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="templateForm"
                      action="<%=request.getContextPath()%>/admin/picture/template/download" method="post">
                    <div class="col-xs-24 col-md-12 b  line-dashed wrapper-sm padder">
                        <div class="form-group row">
                            <div class="col-xs-6 col-md-3 no-padder text-right">
                                <label class="control-label required">模板下载（点击加号上传二维码）</label>
                                <label class="control-label required" style="color: red">(超过100kb的图片不能上传)&nbsp;</label>
                                <label class="control-label required" style="color: red">(图片名称不能超过20个字符)&nbsp;</label>
                            </div>
                            <div id="previewDiv" class="img-preview col-xs-14 col-md-7">
                            </div>
                            <input id="uploadInput" class="hidden" type="file" onchange="uploadPreview(this)"
                                   accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif" multiple="multiple"/>
                            <input id="pictures" type="hidden" name="pictures"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--操作提示及删除--%>
<jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
<%--操作提示结束--%>
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
    var $templateForm = $("#templateForm");
    function submitForm() {
        $form.submit();
        window.location.href = "${redirectUrl}";
    }
    function templateForm() {
        $templateForm.submit();
    }


    function templateUpload() {
        var files = $("#file").prop('files');
        if (files && files[0]) {
            var formData = new FormData();
            formData.append('file', files[0]);
            uploadFile('<%=request.getContextPath()%>/admin/picture/template/upload', formData, function (data) {
                if (data.status == 0) {
                    $("#info").html(data.msg);
                    $("#info_fail").modal("show");
                }else{
                    $("#upload_success").modal("show");
                }
            });
        }
    }

    <%--图片批量上传--%>
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
    });

    function updatePreviewDiv() {
        var html = "";
        $.each(images, function (index, image) {
            html += "<label>" +
                "<img src='" + image + "'/>" +
                "<a class='delete' href='javascript:deleteUpload(" + index + ")'>删除</a>" +
                "</label>";
        });
        html += "<label>" +
            "<a id='add' class='add' href=\"" + "javascript:$('#uploadInput').click()" + "\">" +
            "<i class='fa fa-plus'></i>" +
            "</a>" +
            "</label>";
        $previewDiv.html(html);
    }

    function uploadPreview(file) {
        if (file.files) {
            $("#add").html("<i class='fa fa-spin fa-spinner'></i>");
            for (var i = 0; i < file.files.length; ++i) {
                client.multipartUpload("picture/" + Date.parse(new Date()) + "_" + file.files[i].name, file.files[i], {
                    progress: function*(p) {
                        console.log('Progress: ' + p);
                    }
                }).then(function (result) {
                    images.push(result.url + "/watermark");
                    genImageInputValue();
                    updatePreviewDiv();
                }).catch(function (err) {
                    console.log(err);
                });
            }
        }
        $('#uploadInput').val("");
    }

    function deleteUpload(index) {
        images.splice(index, 1);
        genImageInputValue();
        updatePreviewDiv();
    }

    function genImageInputValue() {
        var imagesStr = "";
        for (var j = 0; j < images.length; ++j) {
            if (j == images.length - 1) imagesStr += images[j];
            else imagesStr += images[j] + ",";
        }
        $("#pictures").val(imagesStr);
    }
    <%--图片批量上传--%>
</script>
</sec:authorize>
</body>
</html>
