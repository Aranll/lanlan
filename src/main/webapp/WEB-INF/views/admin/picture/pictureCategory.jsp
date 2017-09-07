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
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./picture_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">图片分类</h1>
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
                            <input type="text" name="parent.name" onclick="showMenu(this,2)" class="form-control"
                                   value="${parent.name}">
                            <input name="parent.id" id="inputParentId" type="hidden">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">状态：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select name="status" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${status eq 0}">selected="selected"</c:if>>关闭</option>
                                <option value="1" <c:if test="${status eq 1}">selected="selected"</c:if>>开通</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">热门：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select name="hot" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${hot eq 0}">selected="selected"</c:if>>不热门</option>
                                <option value="1" <c:if test="${hot eq 1}">selected="selected"</c:if>>热门</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_save})">
                                <button class="btn btn-success" type="button" data-toggle="modal"
                                        data-target="#saveCategory">
                                    新增
                                </button>
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
                            <th>热门</th>
                            <th>是否开通</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${pictures.size() eq 0}">
                            <tr>
                                <td colspan="6">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${pictures}" var="picture">
                            <tr>
                                <td>${picture.id}</td>
                                <td>${picture.name}</td>
                                <td>${picture.parent.name}</td>
                                <td>
                                    <c:if test="${picture.hot==1}">
                                        是
                                    </c:if>
                                    <c:if test="${picture.hot==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>${picture.statusDesc}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_get})">
                                        <button class="btn btn-warning btn-xs" onclick="detailsPicture(${picture.id})">
                                            详情
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_update})">
                                        <button class="btn btn-info btn-xs" onclick="updatePicture(${picture.id})">
                                            编辑
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_save})">
                                        <button class="btn btn-danger btn-xs"
                                                onclick="remove(${picture.id},'<%=request.getContextPath()%>/admin/picture/pictureCategory/remove')">
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
    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_save})">
        <%--新增分类--%>
    <div class="modal fade" id="saveCategory" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">新增图片分类</h4>
                </div>
                <div class="modal-body">
                    <form name="saveForm" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">名称：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="name" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">顺序：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="seq" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">所属：</label>
                            </div>
                            <div class="col-xs-9">
                                <input type="text" onclick="showMenu(this,2)" class="form-control">
                                <input name="parent.id" type="hidden"></div>
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
                                <label class="control-label">状态：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="status" class="form-control">
                                    <option value="0">关闭</option>
                                    <option value="1" selected="selected">启用</option>
                                </select>
                            </div>
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
        <%--新增分类结束--%>
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
                seq: {
                    required: true,
                    digits: true
                },
                'parent.id': {
                    required: true,
                    digits: true
                },
                status: {
                    required: true
                },
                hot: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                seq: {
                    required: "顺序不能为空",
                    digits: "顺序必须是整数"
                },
                'parent.id': {
                    required: "所属不能为空",
//                digits: "所属是个编号"
                },
                hot: "请选择是否热门",
                status: "请选择是否开通"
            }
        });

        $('#saveCategory').on('hidden.bs.modal', function (e) {
            saveValidate.resetForm();
            $saveForm[0].reset();
        });
        //使用ajax提交表单，保存图片分类
        function submitSave() {
            doPost("<%=request.getContextPath()%>/admin/picture/pictureCategory/save", $saveForm.serialize(), function (data) {
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
    </script>
        <%--新增分类结束--%>
    </sec:authorize>
    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_get})">
        <%--图片详情--%>
    <div class="modal fade" id="detailsPicture" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">图片详情</h4>
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
                                <label class=" font-bold">所属：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="parent.name"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="font-bold">顺序：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="seq"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">热门：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="hotDesc"></span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class=" font-bold">状态：</label>
                            </div>
                            <div class="col-xs-9">
                                <span name="statusDesc"></span>
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
        <%--图片详情结束--%>
        <%--图片详情--%>
    <script type="text/javascript">
        var $detailsForm = $("form[name='detailsForm']");
        var detailsPictureId;
        function detailsPicture(id) {
            detailsPictureId = id;
            //获取图片信息
            doPost("<%=request.getContextPath()%>/admin/picture/pictureCategory/get", {id: detailsPictureId}, function (data) {
                console.log(data);
                var _data = data.data;
                console.log(_data);
                if (data.status) {
                    $detailsForm.find("span[name='id']").html(_data.id);
                    $detailsForm.find("span[name='name']").html(_data.name);
                    $detailsForm.find("span[name='parent.name']").html(_data.parent.name);
                    $detailsForm.find("span[name='statusDesc']").html(_data.statusDesc);
                    $detailsForm.find("span[name='hotDesc']").html(_data.hotDesc);
                    $detailsForm.find("span[name='seq']").html(_data.seq);
                    $detailsForm.find("span[name='createTime']").html(_data.createTime);
                    $detailsForm.find("span[name='updateTime']").html(_data.updateTime);
                } else {
                    if (data.msg != null && data.msg.length != 0) {
                        $("#info").html(data.msg);
                    }
                    $("#info_fail").modal('show');
                }
            });

            $("#detailsPicture").modal("show");
        }
        function submitDetails() {
            $("#detailsPicture").modal("hide");
        }
    </script>
        <%--图片详情结束--%>
    </sec:authorize>
    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.picture_category_update})">
        <%--编辑图片开始--%>
    <div class="modal fade" id="updatePicture" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">编辑图片</h4>
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
                                <label class="control-label">顺序：</label>
                            </div>
                            <div class="col-xs-9">
                                <input name="seq" type="text" class="form-control" value="">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">所属：</label>
                            </div>
                            <div class="col-xs-9">
                                <input type="text" onclick="showMenu(this,1)" name="parent.name" class="form-control">
                                <input name="parent.id" type="hidden">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">状态：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="status" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">热门：</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="hot" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-danger" onclick="submitUpdate()">确定</button>
                </div>
            </div>
        </div>
    </div>
        <%--编辑图片结束--%>
        <%--编辑图片分类--%>
    <script type="text/javascript">
        var $updateForm = $("form[name='updateForm']");
        var updatePictureId;
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
                seq: {
                    required: true,
                    digits: true,
                    notEmpty: true
                },
                'parent.id': {
                    required: true,
                    digits: true
                },
                developer: {
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
                'parent.id': {
                    required: "所属不能为空",
                    digits: "所属是个编号"
                },
                status: "请选择是否开通"
            }
        });

        function updatePicture(id) {
            updatePictureId = id;

            updateValidate.resetForm();
            $updateForm[0].reset();
            //获取图片信息
            doPost("<%=request.getContextPath()%>/admin/picture/pictureCategory/get", {id: updatePictureId}, function (data) {
                console.log(data);
                var _data = data.data;
                console.log(_data);
                if (data.status) {
                    $updateForm.find("input[name='id']").val(_data.id);
                    $updateForm.find("input[name='name']").val(_data.name);
                    $updateForm.find("input[name='seq']").val(_data.seq);
                    $updateForm.find("input[name='parent.name']").val(_data.parent.name);
                    $updateForm.find("input[name='parent.id']").val(_data.parent.id);
                    if (_data.hot == 0) {
                        $updateForm.find("select[name='hot']").val(0);
                    } else {
                        $updateForm.find("select[name='hot']").val(1);
                    }
                    if (_data.status == 0) {
                        $updateForm.find("select[name='status']").val(0);
                    } else {
                        $updateForm.find("select[name='status']").val(1);
                    }
                } else {
                    if (data.msg != null && data.msg.length != 0) {
                        $("#info").html(data.msg);
                    }
                    $("#info_fail").modal('show');
                }
            });

            $("#updatePicture").modal("show");
        }


        //使用ajax提交表单，更新图片分类
        function submitUpdate() {
            doPost("<%=request.getContextPath()%>/admin/picture/pictureCategory/update", $updateForm.serialize(), function (data) {
                if (data.status) {
                    $("#info_success").modal("show");
                    setTimeout(function(){
                        window.location.reload(true);
                    },800);
                } else {
                    $("#info").html(data.msg);
                    $("#info_fail").modal("show");
                }
            });
        }
    </script>
        <%--编辑图片分类结束--%>
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
            return "<%=request.getContextPath()%>/admin/picture/picture/tree";
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
    <jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
</body>
</html>


