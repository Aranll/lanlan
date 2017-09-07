<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/13
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>医览网-管理-期刊管理</title>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/validate.jsp" %>
    <%@include file="../common/ztree.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./periodical_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">期刊管理</h1>
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
                            <input type="text" name="category.name" onclick="showMenu(this)" class="form-control"
                                   value="${category.name}">
                            <input name="category.id" type="hidden" value="${category.id}">
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
                                <option value="0" <c:if test="${hot eq 0}">selected="selected"</c:if>>否</option>
                                <option value="1" <c:if test="${hot eq 1}">selected="selected"</c:if>>是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.periodical_periodical_save})">
                                <button class="btn btn-success" type="button" data-toggle="modal"
                                        data-target="#savePeriodical">
                                    新增
                                </button>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.periodical_periodical_batchUpload})">
                                <a href="<%=request.getContextPath()%>/admin/periodical/periodical/batchUpload">
                                    <button class="btn btn-success" type="button" data-toggle="modal" data-target="">
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
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${periodicals.size() eq 0}">
                            <tr>
                                <td colspan="6">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${periodicals}" var="periodical">
                            <tr>
                                <td>${periodical.id}</td>
                                <td>${periodical.name}</td>
                                <td>${periodical.categories[0].name}&nbsp;&nbsp;|&nbsp;&nbsp;${periodical.categories[1].name}</td>
                                <td>
                                    <c:if test="${periodical.recommend==1}">
                                        是
                                    </c:if>
                                    <c:if test="${periodical.recommend==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${periodical.hot==1}">
                                        是
                                    </c:if>
                                    <c:if test="${periodical.hot==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>${periodical.createTime}</td>
                                <td>
                                    <sec:authorize
                                            access="hasAnyRole(${sessionScope.sec_op.periodical_periodical_get})">
                                        <button class="btn btn-warning btn-xs"
                                                onclick="detailsPeriodical(${periodical.id})">
                                            详情
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize
                                            access="hasAnyRole(${sessionScope.sec_op.periodical_periodical_update})">
                                        <button class="btn btn-info btn-xs"
                                                onclick="updatePeriodical(${periodical.id})">
                                            编辑
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize
                                            access="hasAnyRole(${sessionScope.sec_op.periodical_periodical_remove})">
                                        <button class="btn btn-danger btn-xs"
                                                onclick="remove(${periodical.id},'<%=request.getContextPath()%>/admin/periodical/periodical/remove')">
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
</div>


<%--新增分类--%>
<div class="modal fade" id="savePeriodical" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增期刊</h4>
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
                            <label class="control-label">链接：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="url" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">来源：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="developer" type="text" class="form-control">
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
                            <label class="control-label">热门：</label>
                        </div>
                        <div class="col-xs-9">
                            <select name="hot" class="form-control">
                                <option value="0">否</option>
                                <option value="1" selected="selected">是</option>
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
            ulr: {
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
                digits: true
            }
        },
        messages: {
            name: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            url: {
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
                doPost("<%=request.getContextPath()%>/admin/periodical/periodical/save", $saveForm.serialize(), function (data) {
                    if (data.status) {
                        $("#info_success").modal("show");
//                        setTimeout(function(){
//                            window.location.reload(true);
//                        },600);
                    } else {
                        $("#info").html(data.msg);
                        $("#info_fail").modal("show");
                    }
                });
            }
        }
    });

    $('#savePeriodical').on('hidden.bs.modal', function (e) {
        saveValidate.resetForm();
        $saveForm[0].reset();
    });
    //使用ajax提交表单，保存期刊分类
    function submitSave() {
        $saveForm.submit();
    }
</script>
<%--新增分类结束--%>

<%--期刊详情--%>
<div class="modal fade" id="detailsPeriodical" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">期刊详情</h4>
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
                            <label class=" font-bold">链接：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="url"></span>
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
                            <label class=" font-bold">创建时间：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="createTime"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class=" font-bold">更新时间：</label>
                        </div>
                        <div class="col-xs-3">
                            <span name="updateTime"></span>
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
                            <label class=" font-bold">首页推荐：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="recommendDesc"></span>
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
<%--期刊详情结束--%>
<%--期刊详情--%>
<script type="text/javascript">
    var $detailsForm = $("form[name='detailsForm']");
    var detailsPeriodicalId;
    function detailsPeriodical(id) {
        detailsPeriodicalId = id;
        //获取期刊信息
        doPost("<%=request.getContextPath()%>/admin/periodical/periodical/get", {id: detailsPeriodicalId}, function (data) {
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
                $detailsForm.find("span[name='url']").html(_data.url);
                $detailsForm.find("span[name='createTime']").html(_data.createTime);
                $detailsForm.find("span[name='updateTime']").html(_data.updateTime);
            } else {
                if (data.msg != null && data.msg.length != 0) {
                    $("#info").html(data.msg);
                }
                $("#info_fail").modal('show');
            }
        });

        $("#detailsPeriodical").modal("show");
    }
    function submitDetails() {
        $("#detailsPeriodical").modal("hide");
    }
</script>
<%--期刊详情结束--%>

<%--编辑期刊开始--%>
<div class="modal fade" id="updatePeriodical" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑期刊</h4>
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
                            <label class="control-label">链接：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="url" type="text" class="form-control" value="">
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
                            <input type="text" onclick="showMenu(this,0,2)" name="category.name" class="form-control">
                            <input name="category.id" type="hidden">
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
                            <label class="control-label">热门：</label>
                        </div>
                        <div class="col-xs-9">
                            <select name="hot" class="form-control">
                                <option value="0">否</option>
                                <option value="1" selected="selected">是</option>
                            </select>
                        </div>
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
<%--编辑期刊结束--%>
<%--编辑期刊分类--%>
<script type="text/javascript">
    var $updateForm = $("form[name='updateForm']");
    var updatePeriodicalId;
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
            url: {
                url: true,
                required: true,
                notEmpty: true
            },
            'category.name': {
                required: true,
                digits: true
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
            'category.name': {
                required: "所属不能为空",
                digits: "所属是个编号"
            },
            status: "请选择是否开通"
        },
        submitHandler:function () {
            doPost("<%=request.getContextPath()%>/admin/periodical/periodical/update", $updateForm.serialize(), function (data) {
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
    });

    function updatePeriodical(id) {
        updatePeriodicalId = id;
        updateValidate.resetForm();
        $updateForm[0].reset();
        //获取期刊信息
        doPost("<%=request.getContextPath()%>/admin/periodical/periodical/get", {id: updatePeriodicalId}, function (data) {
            var _data = data.data
            console.log(_data);
            if (data.status) {
                $updateForm.find("input[name='id']").val(_data.id);
                $updateForm.find("input[name='name']").val(_data.name);
                $updateForm.find("input[name='url']").val(_data.url);
                $updateForm.find("input[name='developer']").val(_data.developer);
                $updateForm.find("input[name='category.id']").val(_data.category.id);
                $updateForm.find("input[name='category.name']").val(_data.category.name);
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


            } else {
                if (data.msg != null && data.msg.length != 0) {
                    $("#info").html(data.msg);
                }
                $("#info_fail").modal('show');
            }
        });
        $("#updatePeriodical").modal("show");
    }


    //使用ajax提交表单，更新期刊分类
    function submitUpdate() {
        $saveForm.submit();
    }
</script>
<%--编辑期刊分类结束--%>

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
        return "<%=request.getContextPath()%>/admin/periodical/periodicalCategory/tree";
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

<%--操作提示--%>
<jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
<%--操作提示结束--%>
</body>
</html>
