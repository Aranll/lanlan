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
    <title>医览网-系统-员工管理</title>
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
                <h1 class="m-n font-thin h3">员工管理</h1>
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
                            <label class="control-label">用户名：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input class="form-control" type="text" name="username" value="${username}"/>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">手机：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input class="form-control" type="text" name="mobile" value="${mobile}"/>
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
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_create})">
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
                            <th>用户名</th>
                            <th>手机</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${staffs.size() eq 0}">
                            <tr>
                                <td colspan="6">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${staffs}" var="staff">
                            <tr>
                                <td>${staff.id}</td>
                                <td>${staff.name}</td>
                                <td>${staff.username}</td>
                                <td>${staff.mobile}</td>
                                <td>${staff.statusDesc}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_auth})">
                                        <button class="btn btn-primary btn-xs" onclick="auth('${staff.id}')">授权</button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_join})">
                                        <button class="btn btn-success btn-xs" onclick="join('${staff.id}')">加入组织
                                        </button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_update})">
                                        <button class="btn btn-info btn-xs" onclick="edit('${staff.id}')">编辑</button>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_delete})">
                                        <button class="btn btn-danger btn-xs" onclick="del('${staff.id}')">删除</button>
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
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_create})">
    <%--新增员工--%>
    <div class="modal fade" id="create" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">新增员工</h4>
                </div>
                <div class="modal-body">
                    <form name="createForm" class="form-horizontal">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">用户名</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="un"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">密码</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="password" style="display: none"/>
                                <input class="form-control" type="password" name="pw" maxlength="20"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">姓名</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="name"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">手机</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="mobile"/>
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
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">email</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="email" name="email"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">QQ</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="qq"/>
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
                un: {
                    required: true,
                    notEmpty: true
                },
                pw: {
                    required: true,
                    notEmpty: true,
                    minlength: 6,
                    maxlength: 20
                },
                mobile: {
                    required: true,
                    notEmpty: true,
                    mobile: true
                },
                name: {
                    required: true,
                    notEmpty: true
                },
                status: {
                    required: true
                },
                email: {
                    email: true
                }
            },
            messages: {
                un: {
                    required: "用户名不能为空",
                    notEmpty: "用户名不能为空"
                },
                pw: {
                    required: "密码不能为空",
                    notEmpty: "密码不能为空",
                    minlength: "密码至少为6位",
                    maxlength: "密码至多位20位"
                },
                mobile: {
                    required: "手机不能为空",
                    notEmpty: "手机不能为空",
                    mobile: "手机格式不对"
                },
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                status: {
                    required: "请选择状态"
                },
                email: {
                    email: "email格式不对"
                }
            },
            submitHandler: function (form) {
                doPost("<%=request.getContextPath()%>/admin/system/security/staff/create", $(form).serialize(), function (data) {
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
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_update})">
    <%--编辑员工--%>
    <div class="modal fade" id="edit" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">编辑员工</h4>
                </div>
                <div class="modal-body">
                    <form name="editForm" class="form-horizontal">
                        <input type="hidden" name="id">
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">用户名</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="un"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">密码</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="password" style="display: none"/>
                                <input class="form-control" type="password" name="pw" maxlength="20"
                                       placeholder="留空则不修改"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">姓名</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="name"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label required">手机</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="mobile"/>
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
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">email</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="email" name="email"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-3 text-right">
                                <label class="control-label">QQ</label>
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" type="text" name="qq"/>
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
                un: {
                    required: true,
                    notEmpty: true
                },
                pw: {
                    minlength: 6,
                    maxlength: 20
                },
                mobile: {
                    required: true,
                    notEmpty: true,
                    mobile: true
                },
                name: {
                    required: true,
                    notEmpty: true
                },
                status: {
                    required: true
                },
                email: {
                    email: true
                }
            },
            messages: {
                un: {
                    required: "用户名不能为空",
                    notEmpty: "用户名不能为空"
                },
                pw: {
                    minlength: "密码至少为6位",
                    maxlength: "密码至多位20位"
                },
                mobile: {
                    required: "手机不能为空",
                    notEmpty: "手机不能为空",
                    mobile: "手机格式不对"
                },
                name: {
                    required: "名称不能为空",
                    notEmpty: "名称不能为空"
                },
                status: {
                    required: "请选择状态"
                },
                email: {
                    email: "email格式不对"
                }
            },
            submitHandler: function (form) {
                doPost("<%=request.getContextPath()%>/admin/system/security/staff/update", $(form).serialize(), function (data) {
                    if (data.status) {
                        alert("员工编辑成功");
                        window.location.reload(true);
                    } else {
                        alert(data.msg);
                    }
                });
            }
        });

        $('#edit').on('hidden.bs.modal', function (e) {
            editValidate.resetForm();
            $("#edit").find(".text-danger").removeClass("text-danger");
        });

        function edit(id) {
            doPost("<%=request.getContextPath()%>/admin/system/security/staff/find", {id: id}, function (data) {
                if (data.status) {
                    var _data = data.data;
                    $editForm.find("input[name='id']").val(_data.id);
                    $editForm.find("input[name='name']").val(_data.name);
                    $editForm.find("input[name='un']").val(_data.username);
                    $editForm.find("input[name='mobile']").val(_data.mobile);
                    $editForm.find("input[name='qq']").val(_data.qq);
                    $editForm.find("input[name='email']").val(_data.email);
                    $editForm.find("select[name='status']").val(_data.status);
                    $("#edit").modal('show');
                } else {
                    alert(data.msg);
                }
            });
        }

        function submitEditForm() {
            $editForm.submit();
        }
    </script>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_delete})">
    <%--删除员工--%>
    <div class="modal fade" id="del" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">删除员工</h4>
                </div>
                <div class="modal-body">
                    <h4 class="text-danger">确认删除该员工？</h4>
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
            doPost("<%=request.getContextPath()%>/admin/system/security/staff/delete", {id: deleteId}, function (data) {
                if (data.status) {
                    alert("员工删除成功");
                    window.location.reload(true);
                } else {
                    alert(data.msg);
                }
            });
        }
    </script>
</sec:authorize>
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_auth})">
    <%--员工授权--%>
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
                    <ul id="authTree" class="ztree" style="overflow:auto;"></ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" onclick="submitAuth()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        var authStaffId = "";
        var zAuthTreeObj,
            authSetting = {
                check: {
                    enable: true
                },
                async: {
                    enable: true,
                    url: authAsyncUrl,
                    dataFilter: authAsyncFilter
                },
                callback: {
                    beforeAsync: authBeforeAsync
                },
                view: {
                    showLine: false
                }
            },
            zAuthTreeNodes = [];

        $(document).ready(function () {
            zAuthTreeObj = $.fn.zTree.init($("#authTree"), authSetting, zAuthTreeNodes);
        });

        function authBeforeAsync(treeId, treeNode) {
            if (treeNode === undefined)
                return false;
        }

        function authAsyncUrl() {
            return "<%=request.getContextPath()%>/admin/system/security/staff/role/pair?id=" + authStaffId;
        }

        function authAsyncFilter(treeId, parentNode, responseData) {
            if (responseData.status) {
                return responseData.data;
            } else {

                    $("#info").html(responseData.msg);
                    $("#info_fail").modal("show");
                return [];
            }
        }

        function auth(id) {
            authStaffId = id;
            zAuthTreeObj.reAsyncChildNodes(null, "refresh");
            $("#auth").modal("show");
        }

        function submitAuth() {
            var selectedNodes = zAuthTreeObj.getCheckedNodes();
            var roleIds = [];
            $.each(selectedNodes, function (index, node) {
                roleIds.push(node.id);
            });
            doPost("<%=request.getContextPath()%>/admin/system/security/staff/role/authorize", {
                id: authStaffId,
                roleIds: roleIds + ""
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
<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_staff_join})">
    <%--员工加入组织--%>
    <div class="modal fade" id="org" data-backdrop="static" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">加入组织</h4>
                </div>
                <div class="modal-body pos-rlt" style="height: 500px;">
                    <ul id="orgTree" class="ztree" style="overflow:auto;"></ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" onclick="submitOrg()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        var orgStaffId = "";
        var zOrgTreeObj,
            orgSetting = {
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
                    url: orgAsyncUrl,
                    dataFilter: orgAsyncFilter
                },
                callback: {
                    beforeAsync: orgBeforeAsync
                }
            },
            zOrgTreeNodes = [];

        $(document).ready(function () {
            zOrgTreeObj = $.fn.zTree.init($("#orgTree"), orgSetting, zOrgTreeNodes);
        });

        function orgBeforeAsync(treeId, treeNode) {
            if (treeNode === undefined)
                return false;
        }

        function orgAsyncUrl() {
            return "<%=request.getContextPath()%>/admin/system/security/staff/org/tree?id=" + orgStaffId;
        }

        function orgAsyncFilter(treeId, parentNode, responseData) {
            if (responseData.status) {
                return responseData.data;
            } else {
                $("#info").html(responseData.msg);
                $("#info_fail").modal("show");
                return [];
            }
        }

        function join(id) {
            orgStaffId = id;
            zOrgTreeObj.reAsyncChildNodes(null, "refresh");
            $("#org").modal("show");
        }

        function submitOrg() {
            var selectedNodes = zOrgTreeObj.getCheckedNodes();
            var organizationIds = [];
            $.each(selectedNodes, function (index, node) {
                organizationIds.push(node.id);
            });
            doPost("<%=request.getContextPath()%>/admin/system/security/staff/org/join", {
                id: orgStaffId,
                organizationIds: organizationIds + ""
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
                $('#org').modal('hide');
            });
        }
    </script>
</sec:authorize>
<jsp:include page="../../common/operationTip.jsp"/>
</body>
</html>
