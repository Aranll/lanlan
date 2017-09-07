<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>医览网-系统</title>
    <%@include file="../../common/head.jsp" %>
    <%@include file="../../common/datepicker.jsp" %>
</head>
<body>
<div class="app app-header-fixed app-aside-fixed">
    <%@include file="../../common/header.jsp" %>
    <%@include file="../system_nav.jsp" %>
    <div class="app-content ">
        <div class="app-content-body">
            <div class="bg-light lter b-b wrapper-md ">
                <h1 class="m-n font-thin h3">系统-系统管理-系统日志</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="searchForm">
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">账号：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamic[operatorName]" type="text" class="form-control" value="${operationLog.dynamic['operatorName']}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">时间：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamic[startTime]" type="text" class="form-control datepicker"
                                   value="${operationLog.dynamic['startTime']}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">至：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamic[endTime]" type="text" class="form-control datepicker" onchange="updateEndTime()"
                                   value="${operationLog.dynamic['endTime']}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">用户类型：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <select name="operatorType" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${operationLog.operatorType eq 0}">selected="selected"</c:if>>员工</option>
                                <option value="1"<c:if test="${operationLog.operatorType eq 1}">selected="selected"</c:if> >用户</option>
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
                            <th>编号</th>
                            <th>账号</th>
                            <th>用户类型</th>
                            <th>登陆IP</th>
                            <th>操作路径</th>
                            <th>操作说明</th>
                            <th>创建时间</th>
                            <%--<th>操作</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${operationLogs.size() eq 0}">
                            <tr>
                                <td colspan="7">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${operationLogs}" var="operationLog">
                            <tr>
                                <td>${operationLog.id}</td>
                                <td>${operationLog.operatorName}</td>
                                <td>${operationLog.operatorTypeDesc}</td>
                                <td>${operationLog.ip}</td>
                                <td>${operationLog.url}</td>
                                <td>${operationLog.operatorTypeDesc}</td>
                                <td>${operationLog.createTime}</td>
                                    <%--<td>--%>
                                    <%--<sec:authorize access="hasAnyRole(${sessionScope.sec_op.website_website_get})">--%>
                                    <%--<button class="btn btn-warning btn-xs" onclick="detailsWebsite(${website.id})">--%>
                                    <%--详情--%>
                                    <%--</button>--%>
                                    <%--</sec:authorize>--%>
                                    <%--<sec:authorize access="hasAnyRole(${sessionScope.sec_op.website_website_update})">--%>
                                    <%--<button class="btn btn-info btn-xs" onclick="updateWebsite(${website.id})">--%>
                                    <%--编辑--%>
                                    <%--</button>--%>
                                    <%--</sec:authorize>--%>
                                    <%--<sec:authorize access="hasAnyRole(${sessionScope.sec_op.website_website_remove})">--%>
                                    <%--<button class="btn btn-danger btn-xs" onclick="remove(${website.id},'<%=request.getContextPath()%>/admin/website/website/remove')">--%>
                                    <%--删除--%>
                                    <%--</button>--%>
                                    <%--</sec:authorize>--%>
                                    <%--</td>--%>
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
<script type="text/javascript">
    $searchForm = $("#searchForm");

    function updateEndTime() {
        var $endTime = $("inpute[name='dynamic[endTime]']");
        var endTimeStr;
        if($endTime.val()!=null){
            endTimeStr = $endTime.val();
            endTimeStr += " 00:00:00";
            $endTime.val(endTimeStr);
            endTimeStr = null;
        }
    }
</script>

</body>
</html>
