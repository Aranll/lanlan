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
    <title>医览网-讲坛-讲坛管理-添加讲坛</title>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <%@include file="../common/ztree.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./video_nav.jsp" %>

    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3 inline">
                    讲坛管理 > 新增视频
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
                <form class="form-horizontal" id="form">
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">名称：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="name" type="text" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">作者：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="author" type="text" class="form-control" maxlength="20">
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">热门：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <select name="hot" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">首页推荐：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <select name="recommend" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">所属：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input name="category.id" type="hidden">
                                <input name="category.name" type="text" onclick="showMenu(this,0,2)" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">VIP资源：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <select id="vipSelect" name="accessVipLevel" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">来源：</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <select id="origin" name="origin" class="form-control" onchange="originChange()">
                                    <option value="0">本站</option>
                                    <option value="1">外站</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div id="url" class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required ">:</label>
                            </div>
                            <div class="col-xs-8 col-md-9">
                                <input  type="text" class="form-control datepicker" maxlength="20">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">封面：</label>
                            </div>
                            <div id="previewDiv" class="img-preview col-xs-8 col-md-9">
                            </div>
                            <input id="uploadInput" class="hidden" type="file" onchange="uploadPreview(this)"
                                   accept="image/png,image/jpg,image/jpeg,image/bmp,image/gif"/>
                            <input id="covers" type="hidden" name="cover" value=""/>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6 b  line-dashed wrapper-sm padder">
                        <div class="row">
                            <div class="col-xs-4 col-md-3 no-padder text-right">
                                <label class="control-label required">视频：</label>
                            </div>
                            <div id="videoPreview" class="img-preview col-xs-8 col-md-9">
                            </div>
                            <input id="uploadVideoInput" alt="" class="hidden" type="file" onchange="uploadVideoPreview(this)"
                                   accept="	audio/mp4, video/mp4"/>
                            <input id="video" type="hidden" name="url" multiple="multiple"/>
                        </div>
                    </div>
                    <div class="col-xs-12 b line-dashed wrapper-sm padder">
                        <div class="row m-b-sm">
                            <div class="col-xs-12">
                                <label class="control-label">简介：</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <textarea name="profile" style="width:100%;height: 200px"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var $form = $("form[id='form']");

    function originChange() {
        var val = $('#origin option:selected').val();
        if(val == 0){
            $("#ossUrl").css("display",'block');
            $('#url').css("display",'none');
        }else {
            $("#url").css("display",'block');
            $('#ossUrl').css("display",'none');
        }
    }
    $form.validate({
        errorClass: 'text-danger',
        rules: {
            name: {
                required: true,
                notEmpty: true
            },
            author: {
                required: true,
                notEmpty: true
            },
            publisher: {
                required: true,
                notEmpty: true
            },
            publishDate: {
                required: true,
                notEmpty: true
            },
            hot: {
                required: true
            },
            recommend: {
                required: true
            },
            "category.name":{
                required:true
            },
            accessVipLevel:{
                required:true
            },
            cover:{
                required:true
            },
            url:{
                required:true
            }
        },
        messages: {
            name: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            author: {
                required: "作者不能为空",
                notEmpty: "作者不能为空"
            },
            publisher: {
                required: "出版社不能为空",
                notEmpty: "出版社不能为空"
            },
            publishDate: {
                required: "出版时间不能为空",
                notEmpty: "出版时间不能为空"
            },
            hot: {
                required: "是否热门"
            },
            recommend: {
                required: "是否首页推荐"
            },
            "category.name": {
                required: "请选择所属分类"
            },
            accessVipLevel: {
                required: "是否VIP资源"
            },
            cover:{
                required:"请上传封面"
            },
            video:{
                required:"请上传视频"
            }
        },
        submitHandler: function (form) {
            doPost("<%=request.getContextPath()%>/admin/video/video/save", $(form).serialize(), function (data) {
                if (data.status) {
                    $("#info_success").modal("show");
                    <%--setTimeout(function(){--%>
                        <%--window.location.href = "${redirectUrl}";--%>
                    <%--},800);--%>
                } else {
                    alert(data.msg);
                }
            });
        }
    });
    function submitForm(){
        $form.submit();
    }
</script>
<%--新增文件上传--%>
<script type="text/javascript">
    var client = new OSS.Wrapper({
        accessKeyId: "${token.accessKeyId}",
        accessKeySecret: "${token.accessKeySecret}",
        stsToken: "${token.securityToken}",
        endpoint: "oss-cn-shenzhen.aliyuncs.com",
        bucket: "shuttle-yilan"
    });
</script>
<%--封面上传--%>
<script type="text/javascript">
    var covers = [];
    var $previewDiv = $("#previewDiv");


    $(function () {
        updatePreviewDiv();
    })

    function updatePreviewDiv() {
        var html = "";
        $.each(covers, function (index, cover) {
            html += "<label>" +
                "<img src='" + cover + "'/>" +
                "<a class='delete' href='javascript:deleteUpload(" + index + ")'>删除</a>" +
                "</label>";
        });
        if (covers.length === 0) {
            html += "<label>" +
                "<a id='addCover' class='add' href=\"" + "javascript:$('#uploadInput').click()" + "\">" +
                "<i class='fa fa-plus'></i>" +
                "</a>" +
                "</label>";
        }
        $previewDiv.html(html);
    }

    function uploadPreview(file) {
        if (file.files && file.files[0]) {
            $("#addCover").html("<i class='fa fa-spin fa-spinner'></i>");
//            这是需要修改图片路径，模块
            client.multipartUpload("book/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                progress: function*(p) {
                    console.log('Progress: ' + p);
                }
            }).then(function (result) {
                covers.push(result.url+ "/watermark");
                $("#covers").val(result.url+"/watermark");
                updatePreviewDiv();
            }).catch(function (err) {
                console.log(err);
            });
        }
        $('#uploadInput').val("");
    }

    function deleteUpload(index) {
        covers.splice(index, 1);
        updatePreviewDiv();
    }
</script>
<%--封面上传结束--%>
<%--pdf上传--%>
<script type="text/javascript">
    var videos = [];
    var $videoPreview = $("#videoPreview");
    $(function () {
        updateVideoPreviewDiv();
    });

    function updateVideoPreviewDiv() {
        var html = "";
        $.each(videos, function (index) {
            html += "<label>" +
                "<img src='" + "<%=request.getContextPath()%>/assets/admin/logo.png" + "'/>" +
                "<a class='delete' href='javascript:deleteVideoUpload(" + index + ")'>删除</a>" +
                "</label>";
        });
        if (videos.length === 0) {
            html += "<label>" +
                "<a id='addVideo' class='add' href=\"" + "javascript:$('#uploadVideoInput').click()" + "\">" +
                "<i class='fa fa-plus'></i>" +
                "</a>" +
                "</label>";
        }
        $videoPreview.html(html);
    }

    function uploadVideoPreview(file) {
        if (file.files && file.files[0]) {
            $("#addVideo").html("<i class='fa fa-spin fa-spinner'></i>");
            client.multipartUpload("video/" + Date.parse(new Date()) + "_" + file.files[0].name, file.files[0], {
                progress: function*(p) {
                    console.log('Progress: ' + p);
                }
            }).then(function (result) {
                console.log(result);
                videos.push("http://shuttle-yilan.oss-cn-shenzhen.aliyuncs.com/" + result.name);
                $("#video").val("http://shuttle-yilan.oss-cn-shenzhen.aliyuncs.com/" + result.name);
                updateVideoPreviewDiv();
            }).catch(function (err) {
                console.log(err);
            });
        }
        $('#uploadVideoInput').val("");
    }

    function deleteVideoUpload(index) {
        videos.splice(index, 1);
        updateVideoPreviewDiv();
    }
</script>
<%--pdf上传结束--%>
<%--新增文件上传结束--%>

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
        return "<%=request.getContextPath()%>/admin/video/video/tree";
    }
    function onAsyncSuccess() {

        if (type == 1) {
            var allNodes = zTreeObj.getNodes();

            for (var i = 0; i < allNodes.length; i++) {
                zTreeObj.removeChildNodes(allNodes[i]);
            }
        }else if(type == 2){
            var allNodes = zTreeObj.getNodes();
            for (var i = 0; i < allNodes.length; i++) {
                var childNodes = allNodes[i].children;
                for(var j = 0;j<childNodes[j].length;j++){
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
            $(targetElement).prev().attr("value", (sNodes[0].id));
            $(targetElement).val(sNodes[0].name);
        }
        $("#selectCategory").modal('hide');
    }
</script>
<%--选择分类管理结束--%>

</body>
</html>
