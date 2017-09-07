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
    <title>医览网-讲坛-讲坛管理</title>
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
                <h1 class="m-n font-thin h3">讲坛管理</h1>
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
                            <input type="text" onclick="showMenu(this,0,2)" name="category.name" class="form-control"
                                   value="${category.name}">
                            <input name="category.id" type="hidden" value="${category.id}">
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">首页推荐：</label>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-3 m-b-md">
                            <select name="recommend" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${recommend==0}">selected="selected"</c:if>>否</option>
                                <option value="1" <c:if test="${recommend==1}">selected="selected"</c:if>>是</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">热门：</label>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-3 m-b-md">
                            <select name="hot" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${hot==0}">selected="selected"</c:if>>否</option>
                                <option value="1" <c:if test="${hot==1}">selected="selected"</c:if>>是</option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">VIP资源：</label>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-3 m-b-md">
                            <select name="accessVipLevel" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${accessVipLevel==0}">selected="selected"</c:if>>否
                                </option>
                                <option value="1" <c:if test="${accessVipLevel==1}">selected="selected"</c:if>>是
                                </option>
                            </select>
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-1 no-padder m-b-md text-right">
                            <label class="control-label">来源：</label>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-3 m-b-md"><select name="orgin" class="form-control">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${accessVipLevel==0}">selected="selected"</c:if>>本站
                            </option>
                            <option value="1" <c:if test="${accessVipLevel==1}">selected="selected"</c:if>>外站
                            </option>
                        </select>
                        </div>
                    </div>
                    <div class="form-group m-t-n-md">
                        <div class="col-xs-12">
                            <a href="<%=request.getContextPath()%>/admin/video/video/new?redirectUrl=${redirectUrl}">
                                <sec:authorize access="hasAnyRole(${sessionScope.sec_op.video_video_save})">
                                <button class="btn btn-success" type="button" data-toggle="modal"
                                        data-target="#savevideo">
                                    新增
                                </button>
                                </sec:authorize>
                            </a>
                            <sec:authorize access="hasAnyRole(${sessionScope.sec_op.video_video_batchUpload})">
                            <a href="<%=request.getContextPath()%>/admin/video/video/videoBatchUpload" target="_blank">
                                <button class="btn btn-success" type="button">
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
                            <th>首页热门</th>
                            <th>热门</th>
                            <th>VIP资源</th>
                            <th>来源</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${videos.size() eq 0}">
                            <tr>
                                <td colspan="9">无数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${videos}" var="video">
                            <tr>
                                <td>${video.id}</td>
                                <td>${video.name}</td>
                                <td>${video.categories[0].name}&nbsp;&nbsp;|&nbsp;&nbsp;${video.categories[1].name}</td>
                                <td>
                                    <c:if test="${video.recommend==1}">
                                        是
                                    </c:if>
                                    <c:if test="${video.recommend==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${video.hot==1}">
                                        是
                                    </c:if>
                                    <c:if test="${video.hot==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${video.accessVipLevel==1}">
                                        是
                                    </c:if>
                                    <c:if test="${video.accessVipLevel==0}">
                                        否
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${video.origin==1}">
                                        外站
                                    </c:if>
                                    <c:if test="${video.origin==0}">
                                        本站
                                    </c:if>
                                </td>
                                <td>${video.createTime}</td>
                                <td>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.video_video_get})">
                                    <a href="<%=request.getContextPath()%>/admin/video/video/get?id=${video.id}&redirectUrl=${redirectUrl}">
                                        <button class="btn btn-warning btn-xs">
                                            详情
                                        </button>
                                    </a>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.video_video_update})">
                                    <a href="<%=request.getContextPath()%>/admin/video/video/edit?id=${video.id}&redirectUrl=${redirectUrl}">
                                        <button class="btn btn-info btn-xs">
                                            编辑
                                        </button>
                                    </a>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole(${sessionScope.sec_op.video_video_remove})">
                                    <button class="btn btn-danger btn-xs"
                                            onclick="remove(${video.id},'<%=request.getContextPath()%>/admin/video/video/remove')">
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
                    $(targetElement).next().attr("value", (sNodes[0].id));
                    $(targetElement).val(sNodes[0].name);
                }
                $("#selectCategory").modal('hide');
            }
        </script>
        <%--选择分类管理结束--%>


    <%--操作提示及删除--%>
    <jsp:include page="../common/operationTip.jsp" flush="false"></jsp:include>
    <%--操作提示结束--%>


</body>
</html>
