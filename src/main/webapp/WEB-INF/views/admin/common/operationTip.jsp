<%--
  Created by IntelliJ IDEA.
  User: Aranl_lin
  Date: 2017/8/10
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--删除操作--%>
<div class="modal fade" id="deleteOperation" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger">确定删除？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" onclick="submitRemove()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--删除操作结束--%>
<%--信息提示操作成功--%>
<div class="modal fade" id="info_success" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger" id="infoOfSuccess">操作成功</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="infoUpdate()">确定</button>
            </div>
        </div>
    </div>
</div>
<%--信息提示操作成功--%>
<%--信息提示失败--%>
<div class="modal fade" id="info_fail" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <h4 class="text-danger" align="" id="info">操作失败</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="infoClose('info_fail')">确定</button>
            </div>
        </div>
    </div>
</div>
<%--信息提示失败--%>

