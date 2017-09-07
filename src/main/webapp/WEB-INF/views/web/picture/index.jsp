<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>医览网</title>
    <%@include file="../common/head.jsp" %>
    <script src="<%=request.getContextPath()%>/assets/web/plugins/masonry&&imagesLoaded/masonry.pkgd.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/web/plugins/masonry&&imagesLoaded/imagesloaded.pkgd.min.js"></script>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="yilan-center clearfix">
    <form id="searchForm">
        <div id="main-search">
            <img src="<%=request.getContextPath()%>/assets/web/img/logo.png">
            <input id="main-search-input" type="text" name="dynamic[name]" placeholder="请输入名字或者关键字"
                   <c:if test="${not empty picture.dynamic.name}">value="${picture.dynamic.name}" </c:if>>
            <input type="hidden" name="category.id" value="${category.id}">
            <input id="main-search-btn" type="button" onclick="$('#searchForm').submit()" value="搜索">
        </div>
    </form>
    <!--导航路径-->
    <div class="location">
        <p>当前位置：</p>
        <span>全部</span>
        <c:forEach items="${picturePath}" var="p" varStatus="i">
            <div class="location-next">
                <span>></span>
                <span>${p.name}<a
                        href="<%=request.getContextPath()%>/picture<c:if test="${i.index ne 0}">?category.id=${picturePath[i.index-1].id}</c:if>">x</a></span>
            </div>
        </c:forEach>
        <div class="statistics ">
            <p>共<span>${total}</span>个结果</p>
        </div>
    </div>

    <!--横线-->
    <div class="horizontal-line"></div>

    <!--分类-->
    <div class="classify-box">
        <p>分类：</p>
        <c:if test="${not empty pictureCategory}">
            <c:forEach items="${pictureCategory}" var="p">
                <div class="classify">
                    <a class="active" href="<%=request.getContextPath()%>/picture?category.id=${p.id}">${p.name}</a>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty pictureCategory && empty picturePath}">
            <c:forEach items="${pictureCategories}" var="p">
                <div class="classify">
                    <a class="active" href="<%=request.getContextPath()%>/picture?category.id=${p.id}">${p.name}</a>
                </div>
            </c:forEach>
        </c:if>
    </div>

    <!--瀑布流-->
    <div id="images" class="grid clearfix">
        <c:forEach items="${pictures}" var="p">
            <div class="grid-item" >
                <img class="" src="${p.url}" onclick="showPic(${p.id})">
            </div>
        </c:forEach>
    </div>
    <div style="width: 100%;text-align: center;margin-top: 40px;height: 50px">
        <button id="readmoreBtn" type="button" style="height:50px;width:150px;text-align:center;
        background-color:#33AC79;display:block;margin: 0 auto;
        outline:none;font-size: 18px;color:#FFFFFF;border: 0" onclick="readmore()">更多
        </button>
    </div>

</div>
<%--瀑布流--%>
<script type="text/javascript">
    var count = 2;
    var categoryId<c:if test="${not empty picture.category.id}">=${picture.category.id}</c:if>;
    if(categoryId===undefined){
        categoryId='';
    }
    var name = $('#main-search-input').val();
    var $waterImages = $('.grid');
    var $waterImagesContainer = $('.grid .grid-item');
    var headDiv = '<div class="grid-item"><img src=';
    var footDiv1 = ' onclick="showPic(';
    var footDiv2 = ')"> </div>';
    $(function () {
        $waterImagesContainer.imagesLoaded(function () {
            $waterImagesContainer.fadeIn(300);
            $waterImages.masonry({
                itemSelector: '.grid-item',
                fitWidth: true
            });
        });
    })

    function readmore() {
        var $html;
        var myData = "page="+count+"&category.id="+ categoryId+"&dynamic[name]="+name;
        $.ajax({
            type: 'POST',
            url: "<%=request.getContextPath()%>/picture/list",
            data: myData,
            dataType: 'json',
            success: function (data) {
                var _data = data.data.list;
                var imagesHtml = '';
                if (!data.status) {
                    alert(data.msg);
                } else {
                    if (_data == null || _data.length <= 0) {
                        $("#readmoreBtn").html("没有更多");
                        $("#readmoreBtn").removeAttr("onclick");
                        return;
                    }
                    for (var i = 0; i < _data.length; i++) {
                        imagesHtml += headDiv + '"' + _data[i].url + '"' + footDiv1+_data[i].id+footDiv2;
                    }
                    var $imagesHtml = $(imagesHtml);
                    $waterImages.append($imagesHtml);
                    $waterImages.imagesLoaded(function () {
                        $waterImages.masonry('appended', $imagesHtml);
                        $waterImages.masonry('layout');
                        if (_data != null && _data.length < 15) {
                            $("#readmoreBtn").html("没有更多");
                            $("#readmoreBtn").removeAttr("onclick");
                        }
                    });
                    count++;
                }
            }
        });

    }
</script>


<div class="modal fade" id="pictureDetial" data-backdrop="static" role="dialog">
    <div class="modal-dialog" role="document" style="width: 800px;text-align: center">
        <div class="modal-content">
            <div class="modal-header" style="border: 0">
                <button type="button" style="color: #00A963;" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="border: 0;">
                <img>
            </div>
            <div class="modal-footer" style="border: 0;clear: both;margin-top: 15px;text-align: center">

            </div>
        </div>
    </div>
</div>

<script>
    function showPic(id) {
        $.ajax({
            url:"<%=request.getContextPath()%>/picture/check",
            type:"post",
            data:"id="+id,
            dataType:"json",
            success: function (data) {
                if (!data.status) {
                    if(data.code == 700) {
                        $('#login-msg').modal('show');
                    }
                    if(data.code == 701) {
                        $('#vip-msg').modal('show');
                    }
                }else {
                    $('#pictureDetial img').attr("src","<%=request.getContextPath()%>/picture/get?id="+id);
                    $('#pictureDetial').modal('show');
                }
            },
        });
    }

</script>
<%@include file="../common/guanggao.jsp" %>
<%@include file="../common/footer.jsp" %>
<%@include file="../common/msgPage.jsp"%>
</body>
</html>
