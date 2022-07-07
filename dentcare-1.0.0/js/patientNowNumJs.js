$(function(){

    setInterval(function () {
        // 現在日期時間
        var now = new Date();
        var dateString = `${now.getFullYear()}/${(now.getMonth() + 1)}/${now.getDate()}<br>${now.getHours()}: ${now.getMinutes()}: ${now.getSeconds()}`;
        
        $("#time").html(dateString);
    }, 1000);

    



});

