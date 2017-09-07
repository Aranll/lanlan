//点击向下图标展开


//侧边栏
$(function () {
    $('.side_nav').click(function () {
        $('#side-nav-box').removeClass('nav-hidden');
        $(document).bind('mouseup', function () {
            navHidden();
        });
        return;
    });
    //下拉按钮导航
    $(".catagory").find(".dowm-box").on("click", function (e) {
        var target = $(e.target);
        var catagory = target.parent().parent();
        if (catagory.hasClass("active")) {
            catagory.removeClass("active");
        } else {
            $(".catagory.active").removeClass("active");
            catagory.addClass("active");
        }
        ;
    });
});

function navHidden() {
    $('#side-nav-box').addClass('nav-hidden');
    $(document).unbind('mouseup');
}


