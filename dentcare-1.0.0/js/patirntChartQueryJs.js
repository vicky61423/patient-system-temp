$(function () {
  sessionStorage.setItem("account", "TGA001");
  sessionStorage.setItem("memName", "吳小儒");
  let memIdLogin = sessionStorage.getItem("account");
  let memName = sessionStorage.getItem("memName");

  $("textarea.textareaChart").css(
    "height",
    $("textarea.textareaChart").prop("scrollHeight")
  );

  function init() {
    $.ajax({
      url: "http://localhost:8080/Proj_Yokult/api/0.01/booking/chartQuery", // 資料請求的網址
      type: "GET", // GET | POST | PUT | DELETE | PATCH
      data: {
        memID: memIdLogin,
      },
      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        console.log(data);
        if (data.msg == "return date success") {
          $.each(data.list, function (i, item) {
            $("select.chart").html("");
            $("select.chart").append(`<option>${item}</option>`);
          });
        } else if (data.msg == "you don't see doctor yet") {
          $("select.chart").html("");
          $("select.chart").append(`<option>查無就診紀錄</option>`);
          $("button.chartQuery").prop("disabled", true);
        }
      },
    });
  }
  init();
  $("button.chartQuery").on("click", function () {
    if ($("select.dr :selected").text() == "請選擇看診日期") {
      return;
    }
    $.ajax({
      url: "http://localhost:8080/Proj_Yokult/api/0.01/booking/chartQuery", // 資料請求的網址
      type: "POST", // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify({
        memID: memIdLogin,
        bookingDate: $("select.chart :selected").text(),
      }),
      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        console.log(data);
        if (data.msg == "return chart success") {
          $("input.drName").val(`看診醫師：${data.map.doctorName} 醫師`);
          $("textarea.textareaChart").html("");
          $("textarea.textareaChart").val(
            data.map.chart.replace(/\n/g, "\r\n")
          );
        } else if (data.msg == "you don't see doctor yet") {
          $("input.drName").val("");
          $("textarea.textareaChart").html("");
          $("textarea.textareaChart").val("醫師尚未填寫，查無病歷紀錄");
        }
      },
    });
  });
});
