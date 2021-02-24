
var ajaxUtil = function () {

    function doPost(url, param, callback) {
        $.ajax({
            url: url,
            type: 'post',
            data: param,
            dataType: 'json',
            success: function(data, status, jqXHR){
            	 callback(data);
            },
            error: function (jqXHR, status, error) {
                callback(jpXHR, null);
            },
            complete: function (jqXHR, status) {

            }
        });
    }

    function doHtml(url, param, tagName, callback) {
        $.ajax({
            url: url,
            type: 'post',
            data: param,
            dataType: 'html',
            success: function(data, status, jqXHR){
                $('#'+tagName).html(data);
                callback(data);
            },
            error: function (jqXHR, status, error) {
            },
            complete: function (jqXHR, status) {

            }
        });
    }

    var certKey = 'h9mKsa1LxTugB9wzXbzZbE2D3H5IUdXI%2BN4AMOOjsj9ehzIP%2FVJd1LIcG%2Fs%2FsraqRF32nzs2qNDwzy5Q0GIM9g%3D%3D';
    var apiUrl = 'http://apis.data.go.kr/1611000/ArchPmsService/getApBasisOulnInfo?sigunguCd=11680&bjdongCd=10300&bun=0012&ji=0004&ServiceKey=';


    function doRest(url) {
        $.ajax({
            url: url,
            type: 'post',
            crossOrigin: true,
            dataType: 'json',
            success: function(data, status, jqXHR){
                console.log(data);
                //callback('success', data);
            },
            error: function (jqXHR, status, error) {
                //callback('failed', null);
            },
            complete: function (jqXHR, status) {

            }
        });
    }

    function getAPIUrl() {
        return apiUrl + certKey;
    }

    return {
        doPost : doPost,
        doRest : doRest,
        doHtml : doHtml
    };
};