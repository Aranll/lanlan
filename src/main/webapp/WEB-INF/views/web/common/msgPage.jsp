<%--
  Created by IntelliJ IDEA.
  User: 阿展
  Date: 2017-08-31
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="login-msg" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document" style="margin-top: 160px;width: 350px;text-align: center">
        <div class="modal-content">
            <div class="modal-header" style="border: 0">
                <button type="button" style="color: #00A963;" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="border: 0;margin-bottom: 20px">
                <h4 class="modal-title" style="text-align: center;
                    font-size: 24px;color: #333333">请先登录</h4>
            </div>
            <div class="modal-footer" style="border: 0;clear: both;margin-top: -10px;text-align: center">
                <button type="button" class="btn"
                        style="width: 300px;height: 50px;background-color: #33AC79;color: #FFFFFF"
                        onclick="$('#login-msg').modal('hide');onc();">登录
                </button>
            </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="vip-msg" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document" style="margin-top: 160px;width: 350px;text-align: center">
        <div class="modal-content">
            <div class="modal-header" style="border: 0">
                <button type="button" style="color: #00A963;" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="border: 0;;margin-bottom: 20px">
                <h4 class="modal-title" style="text-align: center;
                    font-size: 24px;color: #333333">该资源仅对会员开放</h4>
            </div>
            <div class="modal-footer" style="border: 0;clear: both;margin-top: -10px;text-align: center">
                <button type="button" class="btn"
                        style="width: 300px;height: 50px;background-color: #33AC79;color: #FFFFFF"
                        onclick="login()">升级会员
                </button>
            </div>
            </form>
        </div>
    </div>
</div>

