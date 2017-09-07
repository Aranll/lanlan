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
    <title>医览网-系统管理-文章管理</title>
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
                <h1 class="m-n font-thin h3">文章管理</h1>
            </div>
            <div class="wrapper-md">
                <form class="form-horizontal" id="searchForm">
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">文章标题：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamic.title" type="text" class="form-control" value="${dynamic.title}">
                        </div>

                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">发布开始时间：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamicStartTime" type="text" class="form-control datepicker"
                                   value="${dynamicStartTime}" readonly>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">发布结束时间：</label>
                        </div>
                        <div class="col-xs-8 col-md-4 col-lg-3 m-b-md">
                            <input name="dynamicEndTime" type="text" class="form-control datepicker"
                                   value="${dynamicEndTime}" readonly>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_create})">
                                <a class="btn btn-success"
                                   href="<%=request.getContextPath()%>/admin/system/article/create?redirectUrl=${redirectUrl}">新增
                                </a>
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
                            <th>文章标题</th>
                            <th>发布时间</th>
                            <th>url</th>
                            <th>发布人</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${articles.size() eq 0}">
                            <tr>
                                <td colspan="6">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${articles}" var="article">
                            <tr>
                                <td>${article.id}</td>
                                <td>${article.title}</td>
                                <td>${article.createTime}</td>
                                <td>${article.url}</td>
                                <td>${article.publisher}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_get})">
                                    <a class="btn btn-default btn-xs"
                                       href="<%=request.getContextPath()%>/admin/system/article/get?redirectUrl=${redirectUrl}&id=${article.id}">预览</a>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_update})">
                                        <a class="btn btn-info btn-xs"
                                           href="<%=request.getContextPath()%>/admin/system/article/update?redirectUrl=${redirectUrl}&id=${article.id}">编辑</a>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_delete})">
                                        <button class="btn btn-danger btn-xs" onclick="del(${article.id})">删除</button>
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

    function resetSearch() {
        $searchForm.find("input[type='text']").each(function () {
            $(this).val("");
        });
    }
</script>

<sec:authorize access="hasAnyRole(${sessionScope.sec_op.system_article_delete})">
<%--删除文章--%>
<div class="modal fade" id="del" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">删除文章</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger"> 确定删除此文章？</h4>
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
        doPost("<%=request.getContextPath()%>/admin/system/article/delete", {id: deleteId}, function (data) {
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
</sec:authorize>
<%--操作提示及删除--%>
<jsp:include page="../../common/operationTip.jsp" flush="false"></jsp:include>
<%--操作提示结束--%>
</body>
</html>
