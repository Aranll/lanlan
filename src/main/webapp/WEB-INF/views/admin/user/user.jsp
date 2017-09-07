<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gustinlau
  Date: 04/08/2017
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-用户-用户管理</title>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../common/header.jsp" %>
    <%@include file="./user_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">用户管理</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="searchForm">
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">编号：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamicId" type="text" class="form-control"  value="${dynamic.id}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">手机号码：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamicMobile" type="text" class="form-control" value="${dynamic.mobile}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">状态：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select id="userStatus" name="status" class="form-control" >
                                <option value="">全部</option>
                                <option value="1">正常</option>
                                <option value="0">冻结</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">级别：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select id="userVipLevel" name="vipLevel" class="form-control">
                                <option value="">全部</option>
                                <option value="0">普通</option>
                                <option value="1">会员</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <input class="btn btn-default pull-right" value="重置" type="button" onclick="resetSearch('searchForm')">
                            <input class="btn btn-info pull-right m-r-sm" value="搜索" type="submit">
                        </div>
                    </div>
                </form>
                <div class="panel panel-default m-b-none">
                    <table class="table text-center table-bordered table-striped m-b-none">
                        <thead>
                        <tr>
                            <th>${initParam}</th>
                            <th>编号</th>
                            <th>客户账号</th>
                            <th>客户邮箱</th>
                            <th>创建时间</th>
                            <th>登陆时间</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${users.size() eq 0}">
                            <tr>
                                <td colspan="6">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td></td>
                                <td>${user.id}</td>
                                <td>${user.mobile}</td>
                                <td>${user.email}</td>
                                <td>${user.createTime}</td>
                                <td>${user.lastLoginTime}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.user_user_update})">
                                        <c:if test="${user.status==1}">
                                            <button class="btn btn-success btn-xs" onclick="updateStatus('${user.id}',0)">正常
                                            </button>
                                        </c:if>
                                        <c:if test="${user.status==0}">
                                            <button class="btn btn-danger btn-xs" onclick="updateStatus('${user.id}',1)">冻结
                                            </button>
                                        </c:if>
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
<%--重置--%>


<sec:authorize access="hasAnyRole(${sessionScope.sec_op.user_user_update})">
<%--改变用户状态--%>
<div class="modal fade" id="changeId" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger" id="statusInfo"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" onclick="submitChange()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--信息提示操作成功--%>
<div class="modal fade" id="info_update" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger" align="" id="updateInfo">操作成功</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="updateSuccess()">确定</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function updateSuccess() {
        $("#info_update").modal('hide');
        window.location.reload(true);
    }
</script>
<%--信息提示操作成功--%>
<script>
    var userId;
    var userStatus;
    function updateStatus(id,status) {
        userId = id;
        userStatus = status;
        if(status==0){
            $("#statusInfo").html("确定冻结？");
        }else{
            $("#statusInfo").html("确定取消冻结?");
        }
        $("#changeId").modal('show');
    }
    function submitChange() {
        doPost("<%=request.getContextPath()%>/admin/user/user/update", {id: userId,status:userStatus}, function (data) {
            if (data.status) {
                $("#updateInfo").html("操作成功");
                $("#changeId").modal('hide');
                $("#info_update").modal('show');
            }else {
                $("#updateInfo").html("操作失败");
                $("#changeId").modal('hide');
                $("#info_update").modal('show');
            }
        });
    }
</script>
</sec:authorize>
</body>
</html>
