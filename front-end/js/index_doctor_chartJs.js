$(function () {

    // $("textarea.textareaChart").val("");
    // 文字框隨內容自動增減
    $("textarea.textareaChart").css("resize", "none");
    $("textarea.textareaChart").on("keyup", function () {

        let textarea_content = $(this).val();
        let content_arr = textarea_content.split("\n");
        let count = content_arr.length;
        let padding_add_border = $(this).outerHeight() - $(this).height();
        $(this).css("height", (16 * (count + 3) + padding_add_border) + "px");

    });


    
    setInterval(function () {
        // 現在日期時間
        var now = new Date();
        var dateString = `叫號更新時間 : ${now.getFullYear()}/${(now.getMonth() + 1)}/${now.getDate()}  ${now.getHours()}: ${now.getMinutes()}: ${now.getSeconds()}`;
        $("#time").html(dateString);
    }, 1000);



});