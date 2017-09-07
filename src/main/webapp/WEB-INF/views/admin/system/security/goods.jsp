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
    <title>医览网-管理-会员管理</title>
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
                <h1 class="m-n font-thin h3">会员套餐</h1>
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
                            <label class="control-label">状态：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select name="status" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${status eq 0}">selected="selected"</c:if>>下架</option>
                                <option value="1" <c:if test="${status eq 1}">selected="selected"</c:if>>上架</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_save})">
                            <button class="btn btn-success" type="button" data-toggle="modal"
                                    data-target="#saveGoods">
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
                            <th>期限</th>
                            <th>价格</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${goods.size() eq 0}">
                            <tr>
                                <td colspan="7">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${goods}" var="goods">
                            <tr>
                                <td>${goods.id}</td>
                                <td>${goods.name}</td>
                                <td>${goods.duration}</td>
                                <td>${goods.price}</td>
                                <td>${goods.statusDesc}</td>
                                <td>${goods.createTime}</td>
                                <td>
                                        <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_get})">
                                    <button class="btn btn-warning btn-xs" onclick="detailsGoods(${goods.id})">
                                        详情
                                    </button>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_update})">
                                    <button class="btn btn-info btn-xs" onclick="updateGoods(${goods.id})">
                                        编辑
                                    </button>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_remove})">
                                    <button class="btn btn-danger btn-xs"
                                            onclick="remove(${goods.id},'<%=request.getContextPath()%>/admin/system/security/goods/remove')">
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

<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_save})">

<%--新增分类--%>
<div class="modal fade" id="saveGoods" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增会员</h4>
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
                            <label class="control-label">价格：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="price" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">时长：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="duration" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">描述：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="desc" type="text" class="form-control">
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
                            <label class="control-label">状态：</label>
                        </div>
                        <div class="col-xs-9">
                            <select name="status" class="form-control">
                                <option value="0">下架</option>
                                <option value="1" selected="selected">上架</option>
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
            price: {
                required: true,
                notEmpty: true
            },
            duration: {
                required: true,
                digits: true,
                notEmpty: true
            },
            status: {
                required: true,
                notEmpty: true
            },
            desc: {
                required: true,
                notEmpty: true
            },
            seq: {
                required: true,
                digits: true,
                notEmpty: true
            }
        },
        messages: {
            name: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            price: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            duration: {
                required: "名称不能为空",
                notEmpty: "名称不能为空",
                digits: "时长是个数字"
            },
            status: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            desc: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            seq: {
                required: "名称不能为空",
                notEmpty: "名称不能为空",
                digits: "顺序是个数字"
            }
        }
    });

    $('#saveGoods').on('hidden.bs.modal', function (e) {
        saveValidate.resetForm();
        $saveForm[0].reset();
    });
    //使用ajax提交表单，保存会员分类
    function submitSave() {
        console.log($saveForm.serialize());
        doPost("<%=request.getContextPath()%>/admin/system/security/goods/save", $saveForm.serialize(), function (data) {
            if (data.status) {
                $("#info_success").modal("show");
//                setTimeout(function(){
//                    window.location.reload(true);
//                },800);
            } else {
                $("#info").html(data.msg);
                $("#info_fail").modal("show");
            }
        });
    }
</script>
<%--新增分类结束--%>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_get})">
<%--会员详情--%>
<div class="modal fade" id="detailsGoods" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">会员详情</h4>
            </div>
            <div class="modal-body">
                <form name="detailsForm" class="form-horizontal">
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="font-bold">名称：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="name"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="font-bold">价格：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="price"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="font-bold">时长：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="duration"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="font-bold">描述：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="desc"></span>
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
                            <label class="font-bold">状态：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="statusDesc"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="font-bold">创建时间：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="createTime"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="font-bold">更新时间：</label>
                        </div>
                        <div class="col-xs-9">
                            <span name="updateTime"></span>
                        </div>
                    </div>
                </form>
                <div class="clearfix"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" onclick="submitDetails()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--会员详情结束--%>
<%--会员详情--%>
<script type="text/javascript">
    var $detailsForm = $("form[name='detailsForm']");
    var detailsGoodsId;

    function detailsGoods(id) {
        detailsGoodsId = id;
        //获取会员信息
        doPost("<%=request.getContextPath()%>/admin/system/security/goods/get", {id: detailsGoodsId}, function (data) {
            console.log(data);
            var _data = data.data;
            console.log(_data);
            if (data.status) {
                $detailsForm.find("span[name='id']").html(_data.id);
                $detailsForm.find("span[name='name']").html(_data.name);
                $detailsForm.find("span[name='price']").html(_data.price);
                $detailsForm.find("span[name='statusDesc']").html(_data.statusDesc);
                $detailsForm.find("span[name='desc']").html(_data.desc);
                $detailsForm.find("span[name='seq']").html(_data.seq);
                $detailsForm.find("span[name='duration']").html(_data.duration);
                $detailsForm.find("span[name='createTime']").html(_data.createTime);
                $detailsForm.find("span[name='updateTime']").html(_data.updateTime);
            } else {
                if (data.msg != null && data.msg.length != 0) {
                    $("#info").html(data.msg);
                }
                $("#info_fail").modal('show');
            }
        });

        $("#detailsGoods").modal("show");
    }
    function submitDetails() {
        $("#detailsGoods").modal("hide");
    }
</script>
<%--会员详情结束--%>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_goods_update})">
<%--编辑会员开始--%>
<div class="modal fade" id="updateGoods" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑会员</h4>
            </div>
            <div class="modal-body">
                <form name="updateForm" class="form-horizontal">
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
                            <label class="control-label">价格：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="price" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">时长：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="duration" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-3 text-right">
                            <label class="control-label">描述：</label>
                        </div>
                        <div class="col-xs-9">
                            <input name="desc" type="text" class="form-control">
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
                            <label class="control-label">状态：</label>
                        </div>
                        <div class="col-xs-9">
                            <select name="recommend" class="form-control">
                                <option value="0">下架</option>
                                <option value="1" selected="selected">上架</option>
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
<%--编辑会员结束--%>
<%--编辑会员分类--%>
<script type="text/javascript">
    var $updateForm = $("form[name='updateForm']");
    var updateGoodsId;
    var updateValidate = $updateForm.validate({
        errorClass: 'text-danger',
        rules: {
            name: {
                required: true,
                notEmpty: true
            },
            price: {
                required: true,
                notEmpty: true
            },
            duration: {
                required: true,
                digits: true,
                notEmpty: true
            },
            status: {
                required: true,
                notEmpty: true
            },
            desc: {
                required: true,
                notEmpty: true
            },
            seq: {
                required: true,
                digits: true,
                notEmpty: true
            }
        },
        messages: {
            name: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            price: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            duration: {
                required: "名称不能为空",
                notEmpty: "名称不能为空",
                digits: "时长是个数字"
            },
            status: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            desc: {
                required: "名称不能为空",
                notEmpty: "名称不能为空"
            },
            seq: {
                required: "名称不能为空",
                notEmpty: "名称不能为空",
                digits: "顺序是个数字"
            }
        }
    });

    function updateGoods(id) {
        updateGoodsId = id;
        updateValidate.resetForm();
        $updateForm[0].reset();
        //获取会员信息
        doPost("<%=request.getContextPath()%>/admin/system/security/goods/get", {id: updateGoodsId}, function (data) {
            var _data = data.data
            if (data.status) {
                $updateForm.find("input[name='id']").val(_data.id);
                $updateForm.find("input[name='name']").val(_data.name);
                $updateForm.find("input[name='price']").val(_data.price);
                $updateForm.find("input[name='statusDesc']").val(_data.statusDesc);
                $updateForm.find("input[name='desc']").val(_data.desc);
                $updateForm.find("input[name='seq']").val(_data.seq);
                $updateForm.find("input[name='duration']").val(_data.duration);
                if (!_data.status) {
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
        $("#updateGoods").modal("show");
    }


    //使用ajax提交表单，更新会员分类
    function submitUpdate() {
        doPost("<%=request.getContextPath()%>/admin/system/security/goods/update", $updateForm.serialize(), function (data) {
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
<%--编辑会员分类结束--%>
</sec:authorize>
<%--操作提示--%>
<jsp:include page="../../common/operationTip.jsp" flush="false"></jsp:include>
<%--操作提示结束--%>
</body>
</html>
