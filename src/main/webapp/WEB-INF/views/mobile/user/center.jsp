<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>

    <title>医览网-个人中心</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/mobile/css/center.css">
</head>
<body>
<div class="container">
    <form class="form-group">
        <div class="position-div">
            <p>手机号码</p>
            <input class="input-style" disabled value="${user.mobile}">
        </div>
        <div class="position-div">
            <p>邮箱</p>
            <input class="input-style" value="${user.email}" disabled>
        </div>
        <div class="position-div">
            <p>密码</p>
            <input class="input-style"  disabled value="******">
            <a href="<%=request.getContextPath()%>/mobile/user/changePassword">修改</a>
        </div>
        <div class="position-div">
            <p>会员到期</p>
            <input class="input-style" value="${user.vipExpire}" disabled>
            <a href="<%=request.getContextPath()%>/mobile/user/recharge">会员充值</a>
        </div>
    </form>
</div>

</body>
</html>