<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/17
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-系统-系统管理-系统设置</title>
    <%@include file="../../common/head.jsp" %>
    <%@include file="../../common/validate.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../../common/header.jsp" %>
    <%@include file="../system_nav.jsp" %>

    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3 inline">
                    系统-系统管理-系统设置
                </h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="form">
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">网站名称：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="webName" value="${property.webName}" type="text" class="form-control"
                                       maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">邮箱：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="email" value="${property.email}" type="text" class="form-control"
                                       maxlength="200">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">地址：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="address" value="${property.address}" type="text" class="form-control"
                                       maxlength="200">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">电话：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="phone" value="${property.phone}" type="text"
                                       class="form-control datepicker" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">QQ：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="qq" value="${property.qq}" type="text" class="form-control datepicker"
                                       maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">版权信息：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="copyright" value="${property.copyright}" type="text"
                                       class="form-control datepicker" maxlength="50">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">备案证编号：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="recordNumber" value="${property.recordNumber}" type="text"
                                       class="form-control datepicker" maxlength="50">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">许可证信息：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="license" value="${property.license}" type="text"
                                       class="form-control datepicker" maxlength="50">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">友情合作商Ⅰ：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="partnerList[0]" value="${property.partnerList[0]}" type="text"
                                       class="form-control datepicker" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">链接：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="partnerUrlList[0]" type="text" value="${property.partnerUrlList[0]}"
                                       class="form-control datepicker" maxlength="200">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">友情合作商Ⅱ：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="partnerList[1]" type="text" value="${property.partnerList[1]}"
                                       class="form-control datepicker" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">链接：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="partnerUrlList[1]" type="text" value="${property.partnerUrlList[1]}"
                                       class="form-control datepicker" maxlength="200">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">友情合作商Ⅲ：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="partnerList[2]" type="text" value="${property.partnerList[2]}"
                                       class="form-control datepicker" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">链接：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="partnerUrlList[2]" type="text" value="${property.partnerUrlList[2]}"
                                       class="form-control datepicker" maxlength="200">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">关于我们（链接）：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="aboutUs" value="${property.aboutUs}" type="text"
                                       class="form-control datepicker" maxlength="200">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">微信二维码：</label>
                            </div>
                            <div id="qrCodePreviewDiv" class="img-preview col-xs-8 col-md-9">
                            </div>
                            <input id="uploadQrCode" class="hidden" type="file" onchange="uploadQrCode1(this)"
                                   accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                            <input id="qrCode" type="hidden" name="qrCode" value="${property.qrCode}"/>
                        </div>
                    </div>
                    <div class="col-xs-24 col-md-12 b  line-dashed wrapper-sm padder">
                        <div class="row" id="miniAppImagesDiv">
                            <div class="col-xs-2 col-md-1 no-padder text-right">
                                <label style="margin-top: 8px" class="control-label required">小程序：</label>
                            </div>
                            <div id="previewDiv0" class="img-preview col-xs-4 col-md-2">
                                <label style="display: block;margin:8px auto ;">
                                    <c:if test="${property.miniAppQrcodeList[0] eq null}">
                                        <a onclick="uploadInput(0)" id="add0" class="add">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${property.miniAppQrcodeList[0] ne null}">
                                        <img src="${property.miniAppQrcodeList[0]}">
                                        <a onclick="deleteImage(0)" class="delete">删除</a>
                                    </c:if>
                                </label>
                                <input id="uploadInput0" class="hidden" type="file" onchange="uploadPreview(this,0)"
                                       accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                                <input id="image0" type="hidden" name="miniAppQrcodeList[0]"
                                       value="${property.miniAppQrcodeList[0]}"/>
                                <input  style="margin: 10px auto ; display: block;text-align:center" type="text" name="miniAppNameList[0]" value="${property.miniAppNameList[0]}">
                            </div>
                            <div id="previewDiv1" class="img-preview col-xs-4 col-md-2">
                                <label style="display: block;margin:8px auto;">
                                    <c:if test="${property.miniAppQrcodeList[1] eq null}">
                                        <a onclick="uploadInput(1)" id="add1" class="add">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${property.miniAppQrcodeList[1] ne null}">
                                        <img src="${property.miniAppQrcodeList[1]}">
                                        <a onclick="deleteImage(1)" class="delete">删除</a>
                                    </c:if>
                                </label>
                                <input id="uploadInput1" class="hidden" type="file" onchange="uploadPreview(this,1)"
                                       accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                                <input id="image1" type="hidden" name="miniAppQrcodeList[1]"
                                       value="${property.miniAppQrcodeList[1]}"/>
                                <input style="margin: 10px auto ; display: block;text-align:center" class="" type="text" name="miniAppNameList[1]" value="${property.miniAppNameList[1]}">
                            </div>
                            <div id="previewDiv2" class="img-preview col-xs-4 col-md-2">
                                <label style="display: block;margin:8px auto;">
                                    <c:if test="${property.miniAppQrcodeList[2] eq null}">
                                        <a onclick="uploadInput(2)" id="add2" class="add">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${property.miniAppQrcodeList[2] ne null}">
                                        <img src="${property.miniAppQrcodeList[2]}">
                                        <a onclick="deleteImage(2)" class="delete">删除</a>
                                    </c:if>
                                </label>
                                <input id="uploadInput2" class="hidden" type="file" onchange="uploadPreview(this,2)"
                                       accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                                <input id="image2" type="hidden" name="miniAppQrcodeList[2]"
                                       value="${property.miniAppQrcodeList[2]}"/>
                                <input style="margin: 10px auto ; display: block;text-align:center" class="" type="text" name="miniAppNameList[2]" value="${property.miniAppNameList[2]}">
                            </div>
                            <div id="previewDiv3" class="img-preview col-xs-4 col-md-2">
                                <label style="display: block;margin:8px auto;">
                                    <c:if test="${property.miniAppQrcodeList[3] eq null}">
                                        <a onclick="uploadInput(3)" id="add3" class="add">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${property.miniAppQrcodeList[3] ne null}">
                                        <img src="${property.miniAppQrcodeList[3]}">
                                        <a onclick="deleteImage(3)" class="delete">删除</a>
                                    </c:if>
                                </label>
                                <input id="uploadInput3" class="hidden" type="file" onchange="uploadPreview(this,3)"
                                       accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                                <input id="image3" type="hidden" name="miniAppQrcodeList[3]"
                                       value="${property.miniAppQrcodeList[3]}"/>
                                <input style="margin: 10px auto ; display: block;text-align:center" class="" type="text" name="miniAppNameList[3]" value="${property.miniAppNameList[3]}">
                            </div>
                            <div id="previewDiv4" class="img-preview col-xs-4 col-md-2">
                                <label style="display: block;margin:8px auto;">
                                    <c:if test="${property.miniAppQrcodeList[4] eq null}">
                                        <a onclick="uploadInput(4)" id="add4" class="add">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${property.miniAppQrcodeList[4] ne null}">
                                        <img src="${property.miniAppQrcodeList[4]}">
                                        <a onclick="deleteImage(4)" class="delete">删除</a>
                                    </c:if>
                                </label>
                                <input id="uploadInput4" class="hidden" type="file" onchange="uploadPreview(this,4)"
                                       accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                                <input id="image4" type="hidden" name="miniAppQrcodeList[4]"
                                       value="${property.miniAppQrcodeList[4]}"/>
                                <input style="display: block;margin: 10px auto;text-align:center;" type="text" name="miniAppNameList[4]" value="${property.miniAppNameList[4]}">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-24 col-md-12 b  line-dashed wrapper-sm padder ">
                        <div class="form-group">
                            <input type="button" class="btn  btn-default pull-right" value="重置"
                                   onclick="resetSearch('form')">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_property_save})">
                                <input type="button" class="btn  btn-success pull-right m-r-sm " value="修改"
                                       onclick="submitForm()">
                            </sec:authorize>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_property_save})">
<%--表单验证表单提交--%>
<script type="text/javascript">
    var $form = $("form[id='form']");

    $form.validate({
        errorClass: 'text-danger',
        rules: {
            webName: {
                required: true,
                notEmpty: true
            },
            email: {
                required: true,
                email: true,
                notEmpty: true
            },
            address: {
                required: true,
                notEmpty: true
            },
            phone: {
                mobile: true,
                required: true,
                notEmpty: true
            },
            qq: {
                required: true,
                number: true
            },
            copyright: {
                required: true
            },
            recordNumber: {
                required: true
            },
            license: {
                required: true
            },
            'partnerList[0]': {},
            'partnerList[1]': {},
            'partnerList[2]': {},
            'partnerUrlList[0]': {
                required: true,
                url: true
            },
            'partnerUrlList[1]': {
                required: true,
                url: true
            },
            'partnerUrlList[2]': {
                required: true,
                url: true
            },
            aboutUs: {
                required: true,
                url: true
            },
            'miniAppQrcodeList[0]': {
                url: true
            },
            'miniAppQrcodeList[1]': {
                url: true
            },
            'miniAppQrcodeList[2]': {
                url: true
            },
            'miniAppQrcodeList[3]': {
                url: true
            },
            'miniAppQrcodeList[4]': {
                url: true
            }
        },
        messages: {
            webName: {
                required: "请输入网站名称",
                notEmpty: "网站名称不能为空"
            },
            email: {
                required: "请输入邮箱",
                email: "请输入正确的邮箱",
                notEmpty: "邮箱不能为空"
            },
            address: {
                required: "请输入地址",
                notEmpty: "地址不能为空"
            },
            phone: {
                mobile: "请输入正确的手机号码",
                required: "请输入手机号",
                notEmpty: "手机号不能为空"
            },
            qq: {
                required: "请输入qq",
                number: "qq号码是数字"
            },
            copyright: {
                required: "请输入版权信息",
                notEmpty: "版权信息不能为空"
            },
            recordNumber: {
                required: "请输入备案号",
                notEmpty: "备案号不能为空"
            },
            license: {
                required: "请输入许可证",
                notEmpty: "许可证不能为空"
            },
            'partnerList[0]': {},
            'partnerList[1]': {},
            'partnerList[2]': {},
            'partnerUrlList[0]': {
                url: "请输入正确的url",
            },
            'partnerUrlList[1]': {
                url: "请输入正确的url",
            },
            'partnerUrlList[2]': {
                url: "请输入正确的url",
            },
            aboutUs: {
                url: "请输入正确的url",
                required: "请输入正确的链接"
            },
            'miniAppQrcodeList[0]': {
                url: "请输入正确的url",
            },
            'miniAppQrcodeList[1]': {
                url: "请输入正确的url",
            },
            'miniAppQrcodeList[2]': {
                url: "请输入正确的url",
            },
            'miniAppQrcodeList[3]': {
                url: "请输入正确的url",
            },
            'miniAppQrcodeList[4]': {
                url: "请输入正确的url",
            }
        },
        submitHandler: function (form) {
            console.log($(form).serialize());
            doPost("<%=request.getContextPath()%>/admin/system/security/property/save", $(form).serialize(), function (data) {
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
    function submitUpdate() {
        $form.submit();
    }
    function submitForm() {
        $("#updateOperation").modal("show");
    }
</script>
<%--表单提交结束--%>
<%--图片预览--%>
<script type="text/javascript">
    var client = new OSS.Wrapper({
        accessKeyId: "${token.accessKeyId}",
        accessKeySecret: "${token.accessKeySecret}",
        stsToken: "${token.securityToken}",
        endpoint: "oss-cn-shenzhen.aliyuncs.com",
        bucket: "shuttle-yilan"
    });
    var imageIndex = null;
    var images = [];//用来存放二维码
    for (var i = 0; i <= 4; i++) {
        images[i] = $("#image" + i).val();
    }
    function uploadInput(index) {
        imageIndex = index;
        $("#uploadInput" + imageIndex).click();
    }
    function uploadPreview(file, index) {
        imageIndex = index;
        if (file.files && file.files[0]) {
            $("#add" + imageIndex).html("<i class='fa fa-spin fa-spinner'></i>");
            client.multipartUpload("miniApp/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                progress: function*(p) {
                    console.log('Progress: ' + p);
                }
            }).then(function (result) {
                console.log(result);
                images[imageIndex] = result.url + "/watermark";
                $("#image" + imageIndex).val(result.url + "/watermark");
                updatePreviewDiv(imageIndex);
            }).catch(function (err) {
                console.log(err);
            });
        }
        $('#uploadInput' + imageIndex).val("");
    }
    function updatePreviewDiv(index) {
        var html = "";
        imageIndex = index;
        if (images[imageIndex] != null && images[imageIndex].length != 0) {
            html +=
                "<img src='" + images[imageIndex] + "'/>" +
                "<a class='delete' onclick='deleteImage(" + imageIndex + ")'>删除</a>";
        } else {
            html += "<a id='add" + imageIndex + "' class='add' onclick='uploadInput(" + imageIndex + ")'>" +
                "<i class='fa fa-plus'></i>" +
                "</a>";
        }
        $("#previewDiv" + imageIndex + " label:eq(0)").html(html);
    }
    function deleteImage(index) {
        imageIndex = index;
        $("#image" + imageIndex).val(null);
        images[index] = null;
        updatePreviewDiv(imageIndex);
    }

</script>
<%--图片预览结束--%>
<%--修改操作--%>
<div class="modal fade" id="updateOperation" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger">确定修改？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" onclick="submitUpdate()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--修改操作结束--%>
<%--微信二维码--%>
<script type="text/javascript">
    var qrCodes = [];
    var $qrCodePreviewDiv = $("#qrCodePreviewDiv");

    $(function () {
        if($("#qrCode").val()!=null&&$("#qrCode").val().length!=0){
            qrCodes.push($("#qrCode").val());
        }
        updateQrCodeDiv();
    })

    function updateQrCodeDiv() {
        var html = "";
        $.each(qrCodes, function (index, qrCode) {
            html += "<label>" +
                "<img src='" + qrCode + "'/>" +
                "<a class='delete' href='javascript:deleteUpload(" + index + ")'>删除</a>" +
                "</label>";
        });
        if (qrCodes.length === 0) {
            html += "<label>" +
                "<a id='addQrCode' class='add' href=\"" + "javascript:$('#uploadQrCode').click()" + "\">" +
                "<i class='fa fa-plus'></i>" +
                "</a>" +
                "</label>";
            $("#qrCode").val(null);
        }
        $qrCodePreviewDiv.html(html);
    }

    function uploadQrCode1(file) {
        if (file.files && file.files[0]) {
            $("#add").html("<i class='fa fa-spin fa-spinner'></i>");
//            这是需要修改图片路径，模块
            client.multipartUpload("weChat/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                progress: function*(p) {
                    console.log('Progress: ' + p);
                }
            }).then(function (result) {
                console.log(result);
                qrCodes.push(result.url+ "/watermark");
                $("#qrCode").val(result.url+ "/watermark");
                updateQrCodeDiv();
            }).catch(function (err) {
                console.log(err);
            });
        }
        $('#uploadQrCode').val("");
    }

    function deleteUpload(index) {
        qrCodes.splice(index, 1);
        updateQrCodeDiv();
    }
</script>
<%--微信二维码结束--%>
<jsp:include page="../../common/operationTip.jsp"/>
</sec:authorize>
</body>
</html>

