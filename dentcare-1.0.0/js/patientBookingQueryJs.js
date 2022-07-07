$(function () {
  //=====確認改用彈窗========
  $("div.card-body").on("click", "button#cancelBtn", function () {
    $(this).closest("div#cancelDiv").addClass("clicked");
    $("div.overlay").fadeIn();
  });

  $("button.btn_modal_close.canceltrue").on("click", function () {
    $("div.overlay").fadeOut();
    // $("div.overlay > article").on("click", function (e) {
    //   e.stopPropagation();
    // });
    $("button#cancelBtn")
      .closest("div.clicked")
      .animate({ opacity: 0 }, 1000, "swing", function () {
        // console.log(this);
        $(this).remove();
      });
  });

  $("button.btn_modal_close.cancelfalse").on("click", function () {
    $("div.overlay").fadeOut();
  });

  //=====改變日期輸入內容==利用改變type的方式=

  function changePlaceHolder() {
    var inputDate = document.querySelector("#inputDate");
    var changeTypetoDate = function () {
      this.type = "date";
    };
    var changeTypetoText = function () {
      this.type = "text";
    };
    inputDate.addEventListener("focus", changeTypetoDate);
    inputDate.addEventListener("blur", changeTypetoText);
  }
  changePlaceHolder();
});
