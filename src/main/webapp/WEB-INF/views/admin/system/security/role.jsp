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
    <title>医览网-系统-角色管理</title>
    <%@include file="../../common/head.jsp" %>
    <%@include file="../../common/validate.jsp" %>
    <%@include file="../../common/ztree.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../../common/header.jsp" %>
    <%@include file="./../system_nav.jsp" %>

    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">角色管理</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="searchForm">
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">编号：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input class="form-control" type="number" name="id" value="${id}"/>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">名称：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input class="form-control" type="text" name="name" value="${name}"/>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">状态：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select id="searchStatus" name="status" class="form-control">
                                <option value="">全部</option>
                                <option value="0">未启用</option>
                                <option value="1">启用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_create})">
                                <button class="btn btn-success" type="button" data-toggle="modal" data-target="#create">
                                    新增
                                </button>
                            </sec:authorize>
                            <input class="btn btn-default pull-right" value="重置" type="button" onclick="resetSearch()">
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
                            <th>描述</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${roles.size() eq 0}">
                            <tr>
                                <td colspan="5">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${roles}" var="role">
                            <tr>
                                <td>${role.id}</td>
                                <td>${role.name}</td>
                                <td>${role.desc}</td>
                                <td>${role.statusDesc}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_auth})">
                                        <button class="btn btn-primary btn-xs" onclick="auth('${role.id}')">授权</button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_update})">
                                        <button class="btn btn-info btn-xs" onclick="edit('${role.id}')">编辑</button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_delete})">
                                        <button class="btn btn-danger btn-xs" onclick="del('${role.id}')">删除</button>
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
<script>
    var $searchForm = $("#searchForm");
    var $searchStatus = $("#searchStatus");

    $searchStatus.val("${status}");

    function resetSearch() {
        $searchForm.find("input[type='text']").each(function () {
            $(this).val("");
        });
        $searchForm.find("input[type='number']").each(function () {
            $(this).val("");
        });
        $searchStatus.val("");
    }

</script>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_create})">
    <%--新增角色--%>
    <div class="modal fade" id="create" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">新增角色</h4>
                </div>
                <div class="modal-body">
                    <form name="createForm" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">名称</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="name"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">描述</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="desc"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">状态</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="status" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="0">未启用</option>
                                    <option value="1">启用</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" onclick="submitCreateForm()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        var $createForm = $("form[name='createForm']");

        var createValidate = $createForm.validate({
            errorClass: 'text-danger',
            rules: {
                name: {
                    required: true,
                    notEmpty: true
                },
                status: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                status: {
                    required: "请选择状态"
                }
            },
            submitHandler: function (form) {
                doPost("<%=request.getContextPath()%>/admin/system/security/role/create", $(form).serialize(), function (data) {
                    if (data.status) {
                        $("#info_success").modal("show");
//                        setTimeout(function(){
//                            window.location.reload(true);
//                        },800);
                    } else {
                        $("#info").html(data.msg);
                        $("#info_fail").modal("show");
                    }
                });
            }
        });

        $('#create').on('hidden.bs.modal', function (e) {
            createValidate.resetForm();
            $createForm[0].reset();
        });

        function submitCreateForm() {
            $createForm.submit();
        }

    </script>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_update})">
    <%--编辑角色--%>
    <div class="modal fade" id="edit" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">编辑角色</h4>
                </div>
                <div class="modal-body">
                    <form name="editForm" class="form-horizontal">
                        <input type="hidden" name="id">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">名称</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="name"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">描述</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="desc"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">状态</label>
                            </div>
                            <div class="col-xs-9">
                                <select name="status" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="0">未启用</option>
                                    <option value="1">启用</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-info" onclick="submitEditForm()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script>

        var $editForm = $("form[name='editForm']");
        var editValidate = $editForm.validate({
            errorClass: 'text-danger',
            rules: {
                name: {
                    required: true,
                    notEmpty: true
                },
                status: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                status: {
                    required: "请选择状态"
                }
            },
            submitHandler: function (form) {
                doPost("<%=request.getContextPath()%>/admin/system/security/role/update", $(form).serialize(), function (data) {
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
        $('#edit').on('hidden.bs.modal', function (e) {
            editValidate.resetForm();
            $("#edit").find(".text-danger").removeClass("text-danger");
        });

        function edit(id) {
            doPost("<%=request.getContextPath()%>/admin/system/security/role/find", {id: id}, function (data) {
                if (data.status) {
                    var _data = data.data;
                    $editForm.find("input[name='id']").val(_data.id);
                    $editForm.find("input[name='name']").val(_data.name);
                    $editForm.find("input[name='desc']").val(_data.desc);
                    $editForm.find("select[name='status']").val(_data.status);
                    $("#edit").modal('show');
                } else {
                        $("#info").html(data.msg);
                        $("#info_fail").modal("show");
                }
            });
        }

        function submitEditForm() {
            $editForm.submit();
        }
    </script>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_delete})">
    <%--删除角色--%>
    <div class="modal fade" id="del" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">删除角色</h4>
                </div>
                <div class="modal-body">
                    <h4 class="text-danger">确认删除该角色？</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-danger" onclick="submitDelete()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        var deleteId;
        function del(id) {
            deleteId = id;
            $("#del").modal('show');
        }
        function submitDelete() {
            doPost("<%=request.getContextPath()%>/admin/system/security/role/delete", {id: deleteId}, function (data) {
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
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_role_auth})">
    <%--角色授权--%>
    <div class="modal fade" id="auth" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">角色授权</h4>
                </div>
                <div class="modal-body pos-rlt" style="height: 500px;">
                    <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" onclick="submitAuth()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        var roleId = "";
        var zTreeObj,
            setting = {
                data: {
                    key: {
                        children: "sons"
                    }
                },
                check: {
                    enable: true
                },
                async: {
                    enable: true,
                    url: asyncUrl,
                    dataFilter: asyncFilter
                },
                callback: {
                    beforeAsync: beforeAsync
                }
            },
            zTreeNodes = [];

        $(document).ready(function () {
            zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
        });

        function beforeAsync(treeId, treeNode) {
            if (treeNode === undefined)
                return false;
        }

        function asyncUrl() {
            return "<%=request.getContextPath()%>/admin/system/security/role/res/tree?id=" + roleId;
        }

        function asyncFilter(treeId, parentNode, responseData) {
            if (responseData.status) {
                return responseData.data;
            } else {
                    $("#info").html(responseData.msg);
                    $("#info_fail").modal("show");
                return [];
            }
        }

        function auth(id) {
            roleId = id;
            zTreeObj.reAsyncChildNodes(null, "refresh");
            $("#auth").modal("show");
        }

        function submitAuth() {
            var selectedNodes = zTreeObj.getCheckedNodes();
            var resources = [];
            $.each(selectedNodes, function (index, node) {
                resources.push(node.id);
            });
            doPost("<%=request.getContextPath()%>/admin/system/security/role/res/authorize", {
                id: roleId,
                resourceIds: resources + ""
            }, function (data) {
                if (data.status) {
                    $("#info_success").modal("show");
                    setTimeout(function(){
                        window.location.reload(true);
                    },800);
                } else {
                    $("#info").html(data.msg);
                    $("#info_fail").modal("show");
                }
                $('#auth').modal('hide');
            });
        }
    </script>
</sec:authorize>
<jsp:include page="../../common/operationTip.jsp" flush="false"></jsp:include>
</body>
</html>
