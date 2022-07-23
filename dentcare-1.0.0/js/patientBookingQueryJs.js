$(function () {
  sessionStorage.setItem("account", "TGA001");
  sessionStorage.setItem("memName", "吳小儒");
  let memIdLogin = sessionStorage.getItem("account");
  let memName = sessionStorage.getItem("memName");
  //如何拿到已登入者的ID~~~ sessionStorage.getItem("account")
  // let memIdLogin = "TGA001"
  let package = {};
  //==取消enter==
  $(document).keydown(function (event) {
    switch (event.keyCode) {
      case 13:
        return false;
    }
  });
  //查詢預約
  // console.log($("button.bookingQuery"));
  $("button.bookingQuery").on("click", function () {
    //先清空
    $("div.card-body div.cancelDiv").remove();
    $.ajax({
      url: "http://localhost:8080/Proj_Yokult/api/0.01/booking/bookingQuery", // 資料請求的網址
      type: "GET", // GET | POST | PUT | DELETE | PATCH
      data: {
        memID: memIdLogin,
      }, // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        console.log(data);
        if (data.msg == "you have no unchecked booking data") {
          $("div.overlay article")
            .find("article")
            .html(
              `<h1>您尚未預約掛號</h1>
              <button type="button" class="btn_modal_close cancelfalse">確定</button> 
                `
            );
        }
        if (data.msg == "bookingQuery sucess") {
          $.each(data.list, function (i, item) {
            package.patientIdcard = item.patientIdcard;
            $("div.card-body").append(`<div class="cancelDiv input-group mb-3">
          <div class="input-group-prepend">
            <button type="button" class="cancelBtn btn btn-danger">
              取消預約
            </button>
          </div>
        
          <input type="text" class="form-control" readonly value="${item.bookingDate}">
          <input type="text" class="form-control" readonly value="${item.amPm}">
          <input type="text" class="form-control" readonly value="${item.doctorName}">
          <input type="text" class="form-control" readonly value="${item.bookingNumber}">
        </div>`);
          });
        }
      },
    });
  });

  //=====確認改用彈窗========
  $("div.card-body").on("click", "button.cancelBtn", function () {
    $(this).closest("div.cancelDiv").addClass("clicked");
    package.bookingDate = $(this)
      .closest("div.cancelDiv")
      .find("input")
      .eq(0)
      .val();
    console.log(package.bookingDate);
    $("div.overlay").fadeIn();
    $("div.overlay article").html(
      ` <h1>確定取消預約?</h1>
        <button type="button" class="btn_modal_close canceltrue">
          確定取消
        </button>
        <button type="button" class="btn_modal_close cancelfalse">保留</button>`
    );
  });

  $(document).on("click", "button.btn_modal_close.canceltrue", function () {
    // $("div.overlay > article").on("click", function (e) {
    //   e.stopPropagation();
    // });
    // console.log(package.patientIdcard);
    // console.log(package.bookingDate);

    $.ajax({
      url: "http://localhost:8080/Proj_Yokult/api/0.01/booking/cancelBooking", // 資料請求的網址
      type: "DELETE", // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify({
        patientIdcard: package.patientIdcard,
        bookingDate: package.bookingDate,
      }), // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        console.log(data.msg);
        // if (data.msg == "cancel success") {
        $("div.overlay").fadeOut();
        // }
        $("button.cancelBtn")
          .closest("div.clicked")
          .animate({ opacity: 0 }, 1000, "swing", function () {
            // console.log(this);
            $(this).remove();
          });
      },
    });
  });

  $(document).on("click", "button.btn_modal_close.cancelfalse", function () {
    $("div.overlay").fadeOut();
  });

  //=====改變日期輸入內容==利用改變type的方式=

  // function changePlaceHolder() {
  //   var inputDate = document.querySelector("#inputDate");
  //   var changeTypetoDate = function () {
  //     this.type = "date";
  //   };
  //   var changeTypetoText = function () {
  //     this.type = "text";
  //   };
  //   inputDate.addEventListener("focus", changeTypetoDate);
  //   inputDate.addEventListener("blur", changeTypetoText);
  // }
  // changePlaceHolder();
});
