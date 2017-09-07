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
    <title>医览网-管理-图片管理</title>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <%@include file="../common/ztree.jsp" %>
    <link rel="stylesheet" type="text/css" href="../../assets/admin/css/appAranl.css">
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./picture_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">图片管理</h1>
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
                            <input type="text" onclick="showMenu(this,0,2)" class="form-control">
                            <input name="category.id" id="" type="hidden">
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_picture_save})">
                                <button class="btn btn-success" type="button" data-toggle="modal"
                                        data-target="#savePicture">
                                    新增
                                </button>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_picture_batchUpload})">
                                <a href="<%=request.getContextPath()%>/admin/picture/picture/batchUpload"
                                   target="_blank">
                                    <button class="btn btn-success" type="button">
                                        批量
                                    </button>
                                </a>
                            </sec:authorize>
                            <input class="btn btn-default pull-right" value="重置" type="button" onclick="resetSearch('searchForm')">
                            <input class="btn btn-info pull-right m-r-sm" value="搜索" type="submit">
                        </div>
                    </div>
                </form>
                <div class="panel-collapse">
                    <div id="images">
                        <c:forEach items="${pictures}" var="picture">
                            <div class="waterfallItems" id="${picture.id}">
                                <img src="${picture.url}" onclick="updateImage(${picture.id})">
                                <%--<a onclick="deleteImageId(${picture.id})">删除</a>--%>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div type="button" id="readmoreBtn" class="btn btn-default col-center-block" onclick="readmore()">
                    点击阅读更多
                </div>
            </div>
        </div>
    </div>
</div>

<sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_picture_save})">
<%--新图片--%>
<div class="modal fade" id="savePicture" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增图片</h4>
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
                        <div class="col-xs-3 text-right">
                            <label class="control-label">所属：</label>
                        </div>
                        <div class="col-xs-9">
                            <input type="text" onclick="showMenu(this,0,2)" class="form-control">
                            <input name="category.id" type="hidden">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">vip资源：</label>
                        </div>
                        <div class="col-xs-3">
                            <select name="accessVipLevel" class="form-control">
                                <option value="0">普通</option>
                                <option value="1" selected="selected">会员</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label required">图片：</label>
                        </div>
                        <div class="col-xs-3">
                            <div id="previewDiv" class="img-preview col-xs-8 col-md-9">
                            </div>
                        </div>
                        <input id="uploadInput" class="hidden" type="file" onchange="uploadPreview(this)"
                               accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                        <input id="url" type="hidden" name="url"/>
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
<%--新增图片，模态框结束--%>
<%--新增图片--%>
<script type="text/javascript">
    var $saveForm = $("form[name='saveForm']");
    var saveValidate = $saveForm.validate({
        errorClass: 'text-danger',
        rules: {
            name: {
                required: true,
                notEmpty: true
            },
            'category.name': {
                required: true
            },
            accessVipLevel: {
                required: true,
                notEmpty: true
            },
            url: {
                required: true,
                url: true,
                notEmpty: true
            }
        },
        messages: {
            name: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            'category.name': {
                required: "所属不能为空"
            },
            accessVipLevel: {
                required: "vip资源不能为空",
                notEmpty: "vip资源不能为空"
            },
            url: {
                required: "链接不能为空",
                url: "必须输入正确格式的网址",
                notEmpty: "链接不能为空"
            }
        },
        submitHandler:function () {
            doPost("<%=request.getContextPath()%>/admin/picture/picture/save", $saveForm.serialize(), function (data) {
                if (data.status) {
                    $("#info_success").modal("show");
//                    setTimeout(function(){
//                        window.location.reload(true);
//                    },600);
                } else {
                    $("#info").html(data.msg);
                    $("#info_fail").modal("show");
                }
            });
        }
    });

    $('#savePicture').on('hidden.bs.modal', function (e) {
        saveValidate.resetForm();
        $saveForm[0].reset();
    });
    //使用ajax提交表单，保存网站分类
    function submitSave() {
        $saveForm.submit();
    }
</script>
<%--新增图片结束--%>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_picture_update})">
<%--编辑及删除图片--%>
<div class="modal fade" id="updatePicture" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">更新图片</h4>
            </div>
            <div class="modal-body">
                <form name="updateForm" id="updateForm" class="form-horizontal">
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">名称：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="name" type="text" class="form-control">
                            <input type="hidden" name="id">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">所属：</label>
                        </div>
                        <div class="col-xs-9">
                            <input type="text" onclick="showMenu(this,0,2)" class="form-control" name="category.name">
                            <input name="category.id" type="hidden">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">vip资源：</label>
                        </div>
                        <div class="col-xs-3">
                            <select name="accessVipLevel" class="form-control">
                                <option value="0">普通</option>
                                <option value="1" selected="selected">会员</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label required">图片：</label>
                        </div>
                        <div class="col-xs-3">
                            <div id="updatePreviewDiv" class="img-preview col-xs-8 col-md-9">
                            </div>
                        </div>
                        <input id="updateUploadInput" class="hidden" type="file" onchange="updateUploadPreview(this)"
                               accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                        <input id="updateUrl" type="hidden" name="url"/>
                    </div>
                </form>
                <div class="clearfix"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="submitUpdate()">修改</button>
                <button type="button" class="btn btn-danger" onclick="submitRemove()">删除</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--获取图片信息--%>
<script type="text/javascript">
    var updatePictureId;
    var updateImages = [];
    var $updateForm = $("#updateForm");
    function updateImage(id) {
        deleteId = id;
        updatePictureId = id;
        updateValidate.resetForm();
        $updateForm[0].reset();
        //获取图片信息
        doPost("<%=request.getContextPath()%>/admin/picture/picture/get", {id: updatePictureId}, function (data) {
            var _data = data.data;
            if (data.status) {
                $updateForm.find("input[name='id']").val(_data.id);
                $updateForm.find("input[name='name']").val(_data.name);
                $updateForm.find("input[name='category.name']").val(_data.category.name);
                $updateForm.find("input[name='category.id']").val(_data.category.id);
                $updateForm.find("input[name='url']").val(_data.url);
                if (_data.accessVipLevel == 0) {
                    $updateForm.find("select[name='accessVipLevel']").val(0);
                } else {
                    $updateForm.find("select[name='accessVipLevel']").val(1);
                }
                //初始化数组
                updateImages.length = 0;
                //初始化二维码
                if (_data.url != null) {
                    updateImages.push(_data.url);
                    updateUpdatePreviewDiv();
                }
            } else {
                $("#info").html(data.msg);
                $("#info_fail").modal('show');
            }
            $("#updatePicture").modal("show");
        });
    }

</script>
<%--获取图片信息结束--%>
<script type="text/javascript">
//    var updateValidate =
$updateForm.validate({
        errorClass: 'text-danger',
        rules: {
            name: {
                required: true,
                notEmpty: true
            },
            'category.name': {
                required: true
            },
            accessVipLevel: {
                required: true,
                notEmpty: true
            },
            url: {
                required: true,
                url: true,
                notEmpty: true
            }
        },
        messages: {
            name: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            'category.name': {
                required: "所属不能为空"
            },
            accessVipLevel: {
                required: "vip资源不能为空",
                notEmpty: "vip资源不能为空"
            },
            url: {
                required: "链接不能为空",
                url: "必须输入正确格式的网址",
                notEmpty: "链接不能为空"
            }
        },
        submitHandler:function () {
            doPost("<%=request.getContextPath()%>/admin/picture/picture/update", $updateForm.serialize(), function (data) {
                if (data.status) {
                    $("#info_success").modal("show");
                } else {
                    $("#info").html(data.msg);
                    $("#info_fail").modal("show");
                }
            });
        }
    });

    //使用ajax提交表单，保存网站分类
    function submitUpdate() {
        $updateForm.submit();
    }
</script>

<%--编辑文件图片上传--%>
<script type="text/javascript">
    var $updatePreviewDiv = $("#updatePreviewDiv");
    $(function () {
        updateUpdatePreviewDiv();
    });

    function updateUpdatePreviewDiv() {
        var html = "";
        $.each(updateImages, function (index, image) {
            html += "<label>" +
                "<img src='" + image + "'/>" +
                "<a class='delete' href='javascript:updateDeleteUpload(" + index + ")'>删除" +
                "</a>"+
                "</label>";
        });
        if (updateImages.length === 0) {
            html += "<label>" +
                "<a id='addUpdate' class='add' href=\"" + "javascript:$('#updateUploadInput').click()" + "\">" +
                "<i class='fa fa-plus'></i>" +
                "</a>" +
                "</label>";
        }
        $updatePreviewDiv.html(html);
    }

    function updateUploadPreview(file) {
        if (file.files && file.files[0]) {
            $("#addUpdate").html("<i class='fa fa-spin fa-spinner'></i>");
//            这是需要修改图片路径，模块
            client.multipartUpload("picture/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                progress: function*(p) {
                    console.log('Progress: ' + p);
                }
            }).then(function (result) {
                console.log(result);
                updateImages.push(result.url + "/watermark");
                $("#updateUrl").val(result.url + "/watermark");
                updateUpdatePreviewDiv();
            }).catch(function (err) {
                console.log(err);
            });
        }
        $('#updateUploadInput').val("");
    }

    function updateDeleteUpload(index) {
        updateImages.splice(index, 1);
        updateUpdatePreviewDiv();
    }
</script>
<%--编辑文件图片上传结束--%>
<%--编辑及删除图片结束--%>
<%--文件上传--%>
<script>

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
            client.multipartUpload("picture/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                progress: function*(p) {
                    console.log('Progress: ' + p);
                }
            }).then(function (result) {
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
<%--文件上传结束--%>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_picture_remove})">
<%--删除图片--%>
<div class="modal fade" id="remove-image" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger" align="">确定删除该图片？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-success" onclick="deleteImage()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--删除图片结束--%>
<%--删除图片--%>
<script type="text/javascript">
    var deleteId;
    function submitRemove() {
        $("#remove-image").modal("show");
    }
    function deleteImage() {
        doPost("<%=request.getContextPath()%>/admin/picture/picture/remove", {id: deleteId}, function (data) {
            if (data.status) {
                $("#remove-image").modal("hide");
                $("#updatePicture").modal("hide");
                $waterImages.masonry('remove', $("#" + deleteId)).masonry('layout').delay(200);
            } else {
                $("#info").html(data.msg);
                $("#info_fail").modal("show");
            }
        });
    }
</script>
<%--删除图片结束--%>
</sec:authorize>
<%--瀑布流--%>
<script type="text/javascript">
    var count = 2;
    var $waterImages = $('#images');
    var $waterImagesContainer = $('#images .waterfallItems');

    $(function () {
        $waterImagesContainer.imagesLoaded(function () {
            $waterImagesContainer.fadeIn(300);
            $waterImages.masonry({
                itemSelector: '.waterfallItems',
                isAnimated: true,
                columnWidth: 200,
                gutter: 6
            });
        });
    });

    function readmore() {
        var $html;
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/admin/picture/picture/list",
            data: "page="+count,
            dataType: 'json',
            success: function (data) {
                var _data = data.data.list;
                var $imagesHtml = $("<div></div>");
                var imagesHtml = '';
                if (!data.status) {
                    $("#info").html(_data.msg);
                    $("#info_fail").modal("show");
                } else {
                    if(_data==null || _data.length<15){
                        $("#readmoreBtn").html("没有更多");
                        $("#readmoreBtn").removeAttr("onclick");
                        return;
                    }
                    for (var i = 0; i < _data.length; i++) {
                        $html = $("<div class='waterfallItems' id='" + _data[i].id + "'></div>");
                        $html.append("<img src='" + _data[i].url + "' onclick='updateImage("+_data[i].id+")'>");
                        $imagesHtml.append($html);
                        imagesHtml += $imagesHtml.html();
                        $imagesHtml = $("<div></div>");
                    }
                    $imagesHtml = $(imagesHtml);
                    $waterImages.append($imagesHtml);
                    $waterImages.imagesLoaded(function () {
                        $waterImages.masonry('appended', $imagesHtml);
                        $waterImages.masonry('layout');
                        if (_data != null && _data.length < 15) {
                            $("#readmoreBtn").html("没有更多");
                            $("#readmoreBtn").removeAttr("onclick");
                        }
                    });
                    count++;
                }
            }

        });

    }
</script>


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
            onAsyncSuccess:onAsyncSuccess
        }
    };
    var zTreeNodes = [];
    function asyncUrl() {
        return "<%=request.getContextPath()%>/admin/picture/picture/tree";
    }
    function onAsyncSuccess() {

        if (type == 1) {
            var allNodes = zTreeObj.getNodes();

            for (var i = 0; i < allNodes.length; i++) {
                zTreeObj.removeChildNodes(allNodes[i]);
            }
        }else if(type == 2){
            var allNodes = zTreeObj.getNodes();
            for (var i = 1; i < allNodes.length; i++) {
                var childNodes = allNodes[i].children;
                if(childNodes !== undefined) {
                    for (var j = 0; j < childNodes.length; j++) {
                        zTreeObj.removeChildNodes(childNodes[j]);
                    }
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
    function showMenu(e, t,t1) {
        targetElement = e;
        if(t ===undefined){
            type = 0;
        }else{
            type = t;
        }
        if(t1 ===undefined){
            type1 = 0;
        }else {
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
            if(type1 != 0 && sNodes[0].level != type1-1){
                return;
            }
            $(targetElement).next().attr("value", (sNodes[0].id));
            $(targetElement).val(sNodes[0].name);
        }
        $("#selectCategory").modal('hide');
    }
</script>
<%--选择分类管理结束--%>

<jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
<script type="text/javascript" src="../../assets/admin/plugins/masonry&&imagesLoaded/masonry.pkgd.min.js"></script>
<script type="text/javascript" src="../../assets/admin/plugins/masonry&&imagesLoaded/imagesloaded.pkgd.min.js"></script>
</body>
</html>


