/**
 * Created by GustinLau on 2017-04-05.
 */

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