<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-管理-图书管理-批量上传</title>
    <%@include file="../common/head.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../../assets/admin/css/appAranl.css">
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./book_nav.jsp" %>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.book_book_batchUpload})">
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">图书管理>文件批量上传</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="form"
                      action="<%=request.getContextPath()%>/admin/book/template/upload" method="post">
                    <div class="col-xs-24 col-md-12  col-center ">
                        <div class="form-group row ">
                            <div class="col-xs-6 col-md-3 text-right">
                            </div>
                            <div class="col-xs-8 col-md-4">
                                <input class="form-control" type="file" name="file" id="file"
                                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                            </div>
                            <div class="col-xs-10 col-md-5">
                                <input type="button" class="btn btn-success m-r-sm" value="模板下载"
                                       onclick="templateForm()">
                                <input type="button" class="btn btn-info" value="文件上传" onclick="templateUpload()">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="templateForm"
                      action="<%=request.getContextPath()%>/admin/book/template/download" method="post">
                    <div class="col-xs-24 col-md-12 b  line-dashed wrapper-sm padder">
                        <div class="form-group row">
                            <div class="col-xs-6 col-md-3 no-padder text-right">
                                <label class="control-label required">模板下载（点击加号上传图书封面）</label>
                                <label class="control-label required"
                                       style="color: red">(超过100kb的图书封面能上传)&nbsp;</label>
                            </div>
                            <div id="coverPreviewDiv" class="img-preview col-xs-14 col-md-7">
                            </div>
                            <input id="coverUploadInput" class="hidden" type="file" onchange="uploadCoverPreview(this)"
                                   accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif" multiple="multiple"/>
                            <input id="covers" type="hidden" name="covers"/>
                        </div>
                        <div class="col-xs-24 col-md-12 b line-dashed wrapper-sm padder">
                            <div class="form-group row">
                                <div class="col-xs-4 col-md-3 no-padder text-right">
                                    <label class="control-label required">模板下载（点击加号上传图书）</label>
                                    <label class="control-label required" style="color: red">(图书名称不能超过20个字符，且必须与封面一致)&nbsp;</label>
                                </div>
                                <div id="bookPreviewDiv" class="img-preview col-xs-8 col-md-9">
                                </div>
                                <input id="bookUploadInput" class="hidden" type="file"
                                       onchange="uploadBookPreview(this)"
                                       accept="application/pdf"
                                       multiple="multiple"/>
                                <input id="books" type="hidden" name="books"/>
                            </div>
                        </div>
                    </div>
                </form>
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
                    uploadFile('<%=request.getContextPath()%>/admin/book/template/upload', formData, function (data) {
                        if (!data.status) {
                            $("#info").html(data.msg);
                            $("#info_fail").modal("show");
                        } else {
                            $("#upload_success").modal("show");
                        }
                    });
                }
            }

            <%--图书批量上传--%>
            var client = new OSS.Wrapper({
                accessKeyId: "${token.accessKeyId}",
                accessKeySecret: "${token.accessKeySecret}",
                stsToken: "${token.securityToken}",
                endpoint: "oss-cn-shenzhen.aliyuncs.com",
                bucket: "shuttle-yilan"
            });

            var covers = [];
            var books = [];
            var $coverPreviewDiv = $("#coverPreviewDiv");
            var $bookPreviewDiv = $("#bookPreviewDiv");

            $(function () {
                updateCoverPreviewDiv();
                updateBookPreviewDiv();
            });

            function updateCoverPreviewDiv() {
                var html = "";
                $.each(covers, function (index, image) {
                    html += "<label>" +
                        "<img src='" + image + "'/>" +
                        "<a class='delete' href='javascript:deleteCoverUpload(" + index + ")'>删除</a>" +
                        "</label>";
                });
                html += "<label>" +
                    "<a id='addCover' class='add' href=\"" + "javascript:$('#coverUploadInput').click()" + "\">" +
                    "<i class='fa fa-plus'></i>" +
                    "</a>" +
                    "</label>";
                $coverPreviewDiv.html(html);
            }

            function uploadCoverPreview(file) {
                if (file.files) {
                    $("#addCover").html("<i class='fa fa-spin fa-spinner'></i>");
                    for (var i = 0; i < file.files.length; ++i) {
                        client.multipartUpload("book/" + Date.parse(new Date()) + "_" + file.files[i].name, file.files[i], {
                            progress: function*(p) {
                                console.log('Progress: ' + p);
                            }
                        }).then(function (result) {
                            covers.push("http://shuttle-yilan.oss-cn-shenzhen.aliyuncs.com/" + result.name + "/watermark");
                            genCoverInputValue();
                            updateCoverPreviewDiv();
                        }).catch(function (err) {
                            console.log(err);
                        });
                    }
                }
                $('#coverUploadInput').val("");
            }

            function deleteCoverUpload(index) {
                covers.splice(index, 1);
                genCoverInputValue();
                updateCoverPreviewDiv();
            }

            function genCoverInputValue() {
                var coversStr = "";
                for (var j = 0; j < covers.length; ++j) {
                    if (j == covers.length - 1) coversStr += covers[j];
                    else coversStr += covers[j] + ",";
                }
                $("#covers").val(coversStr);
            }

            function updateBookPreviewDiv() {
                var html = "";
                $.each(books, function (index, image) {
                    html += "<label>" +
                        "<img src='" + "<%=request.getContextPath()%>/assets/admin/logo.png" + "'/>" +
                        "<a class='delete' href='javascript:deleteBookUpload(" + index + ")'>删除</a>" +
                        "</label>";
                });
                html += "<label>" +
                    "<a id='addBook' class='add' href=\"" + "javascript:$('#bookUploadInput').click()" + "\">" +
                    "<i class='fa fa-plus'></i>" +
                    "</a>" +
                    "</label>";
                $bookPreviewDiv.html(html);
            }

            function uploadBookPreview(file) {
                if (file.files) {
                    $("#addBook").html("<i class='fa fa-spin fa-spinner'></i>");
                    for (var i = 0; i < file.files.length; ++i) {
                        client.multipartUpload("book/" + Date.parse(new Date()) + "_" + file.files[i].name, file.files[i], {
                            progress: function*(p) {
                                console.log('Progress: ' + p);
                            }
                        }).then(function (result) {
                            console.log(result);
                            books.push("http://shuttle-yilan.oss-cn-shenzhen.aliyuncs.com/" + result.name);
                            genBookInputValue();
                            updateBookPreviewDiv();
                        }).catch(function (err) {
                            console.log(err);
                        });
                    }
                }
                $('#bookUploadInput').val("");
            }

            function deleteBookUpload(index) {
                books.splice(index, 1);
                genBookInputValue();
                updateBookPreviewDiv();
            }

            function genBookInputValue() {
                var booksStr = "";
                for (var j = 0; j < books.length; ++j) {
                    if (j == books.length - 1) booksStr += books[j];
                    else booksStr += books[j] + ",";
                }
                $("#books").val(booksStr);
            }
            <%--图书批量上传--%>
        </script>
        <%--操作提示及删除--%>
        <jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
        <%--操作提示结束--%>
        </sec:authorize>
</body>
</html>
