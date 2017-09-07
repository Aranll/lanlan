/**
 * Created by GustinLau on 2017-04-05.
 */


//region Ajax

function doPost(url, data, success, error) {
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        dataType: 'json',
        success: success,
        error: error
    })
}

function doPost(url, data, success) {
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        dataType: 'json',
        success: success,
        error: function (XMLHttpRequest, textStatus) {
            alert("请求失败：" + textStatus + "\n错误码：" + XMLHttpRequest.status);
        }
    })
}

function uploadFile(url, formData, success, error) {
    $.ajax({
        type: 'POST',
        url: url,
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        dataType: 'json',
    }).done(success).fail(error);
}

function uploadFile(url, formData, success) {
    $.ajax({
        type: 'POST',
        url: url,
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        dataType: 'json',
    }).done(success).fail(function (res) {
        alert("请求失败：" + res.statusText + "\n错误码：" + res.status);
    });
}

function doGet(url, data, success, error) {
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        dataType: 'json',
        success: success,
        error: function (XMLHttpRequest, textStatus) {
        alert("请求失败：" + textStatus + "\n错误码：" + XMLHttpRequest.status);
    }
    })
}

//endregion

//region Toastr

toastr.options = {
    "closeButton": false,
    "debug": false,
    "positionClass": "toast-top-center",
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "10000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

function toastSuccess(text) {
    toastr.remove();
    toastr.success(text);
}

//endregion

