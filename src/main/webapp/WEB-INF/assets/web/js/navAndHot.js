/**
 * Created by 阿展 on 2017-08-18.
 */
//
// function pictureDisplay(e) {
//     var a = $(e).next();
//     if(a.hasClass('picture-nav-hidden')) {
//         a.removeClass('picture-nav-hidden');
//         $('body').on("mouseup",function () {
//             a.addClass('picture-nav-hidden');
//         })
//     }
//     else
//         a.addClass('picture-nav-hidden');
// }

function scrollDiv(id){
    $("html,body").animate({scrollTop:$("#"+id).offset().top-30},1000);
}

$(function () {

    $('#pictureNav').bind('mouseover',function () {
        $('#pictureDiv').removeClass('picture-nav-hidden');
    });
    $('#pictureNav').bind('mouseleave',function(){
        $('#pictureDiv').addClass('picture-nav-hidden');
    });

    $('.nav-li').bind('mouseover',function () {
        $(this).children('div').eq(0).removeClass('nav-navbar-hidden');
    });
    $('.nav-li').bind('mouseleave',function(){
        $(this).children('div').eq(0).addClass('nav-navbar-hidden');
    });
    $('.soft-hot').bind('mouseover',function () {
        $(this).children('div').eq(0).removeClass('soft-hot-hidden');
    });
    $('.soft-hot').bind('mouseleave',function () {
        $(this).children('div').eq(0).addClass('soft-hot-hidden');
    });
    $('.soft-codesmall').bind('mouseover',function () {
        $(this).children('.soft-detail').eq(0).removeClass('soft-detail-hidden');
    });
    $('.soft-codesmall').bind('mouseleave',function () {
        $(this).children('.soft-detail').eq(0).addClass('soft-detail-hidden');
    });
    $('.search-soft-codesmall').bind('mouseover',function () {
        $(this).children('.search-soft-detail').eq(0).removeClass('search-soft-detail-hidden');
    });
    $('.search-soft-codesmall').bind('mouseleave',function () {
        $(this).children('.search-soft-detail').eq(0).addClass('search-soft-detail-hidden');
    });

    //Nav
    var c_href = window.location.href;
    c_href = c_href.replace(/^http:\/\/[^/]+/, "");
    var tNav = $("#a_nav > li > a");
    tNav.each(function () {
        var a_href = $(this).attr("href");
        if (a_href !== "" && c_href.indexOf(a_href) === 0) {
            var nexStr = c_href.substr(a_href.length,1);
            if (nexStr === "" || nexStr === "?" || nexStr === "#" || nexStr === "/")
                $(this).addClass("active");
        }
    });


    if($("body").height()<$(window).height()){
        $("body").height($(window).height());
        $('footer').addClass('change');
    }

    $('.ff-item').each(function () {
        $(this).on("mouseover",function () {
            $(this).children('div').eq(0).removeClass('hidden');
        })
        $(this).on("mouseleave",function () {
            $(this).children('div').eq(0).addClass('hidden');
        })
    })


//    会员充值弹出框
    $('.grid>.grid-item>a>img').on('click', function () {
        $('#member-alert').css("display", "block");
        return false;
    })

    //用户名更改弹出框
    $('a.title').on('click', function () {
        $('#user-name-hide-box').css('display', 'block');

        $(document).on('click', function () {
            $('#user-name-hide-box').css('display', 'none');
        })
        return false;
    })

})