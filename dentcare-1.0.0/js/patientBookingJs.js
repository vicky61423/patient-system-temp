$(function () {
  //====彈跳視窗=====
  // 開啟 Modal 彈跳視窗
  $("button.btn_modal").on("click", function () {
    $("div.overlay").fadeIn();
  });

  // 關閉 Modal
  $("button.btn_modal_close").on("click", function (e) {
    $("div.overlay").fadeOut();
  });

  $("div.overlay > article").on("click", function (e) {
    e.stopPropagation();
  });

  //==========初診複診選單==============
  $("input#double").on("click", function () {
    if ($("input#double").prop("checked")) {
      $("input#yourname").css("display", "none");
      $("input#IDcard").css("display", "none");
    } else {
      $("input#yourname").css("display", "");
      $("input#IDcard").css("display", "");
    }
  });
  $("input#single").on("click", function () {
    if ($("input#single").prop("checked")) {
      $("input#yourname").css("display", "");
      $("input#IDcard").css("display", "");
    }
  });

  // ===============下一周下拉選單==========
  function weekDate(date) {
    let year = date.getFullYear();
    let one_day = 86400000;
    //回傳星期幾
    let day = date.getDay();
    // 設時間為0:0:0
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);
    //回傳自1970/01/01至今的毫秒數
    let todayMillis = date.getTime();
    let sixDayMillis = 6 * 24 * 60 * 60 * 1000;
    let sevenDayMillis = 7 * 24 * 60 * 60 * 1000;

    let this_week_end = new Date(todayMillis + sixDayMillis);
    let next_week_start = new Date(todayMillis + sevenDayMillis);
    let next_week_end = new Date(next_week_start.getTime() + sixDayMillis);
    let next_2week_start = new Date(next_week_start.getTime() + sevenDayMillis);
    let next_2week_end = new Date(next_2week_start.getTime() + sixDayMillis);

    let next_3week_start = new Date(
      next_2week_start.getTime() + sevenDayMillis
    );
    let next_3week_end = new Date(next_3week_start.getTime() + sixDayMillis);

    let thisWeek = `${date.getFullYear()}/${
      date.getMonth() + 1
    }/${date.getDate()} - ${this_week_end.getFullYear()}/${
      this_week_end.getMonth() + 1
    }/${this_week_end.getDate()}`;

    let nextWeek = `${next_week_start.getFullYear()}/${
      next_week_start.getMonth() + 1
    }/${next_week_start.getDate()} - ${next_week_end.getFullYear()}/${
      next_week_end.getMonth() + 1
    }/${next_week_end.getDate()}`;

    let next2Week = `${next_2week_start.getFullYear()}/${
      next_2week_start.getMonth() + 1
    }/${next_2week_start.getDate()} - ${next_2week_end.getFullYear()}/${
      next_2week_end.getMonth() + 1
    }/${next_2week_end.getDate()}`;

    let next3Week = `${next_3week_start.getFullYear()}/${
      next_3week_start.getMonth() + 1
    }/${next_3week_start.getDate()} - ${next_3week_end.getFullYear()}/${
      next_3week_end.getMonth() + 1
    }/${next_3week_end.getDate()}`;

    var map = new Map();
    map["1"] = thisWeek;
    map["2"] = nextWeek;
    map["3"] = next2Week;
    map["4"] = next3Week;

    return map;
  }

  //===下拉選單方法執行填入===
  const now = new Date();
  let weekMap = weekDate(now);
  $("select.selectDate")
    .children("option")
    .each(function (index, item) {
      $(item).text(weekMap[index + 1]);
    });

  //=============顯示每天日期=========

  //====預設填入本周日期======
  trWriteinit(now);

  //====依據下拉事選單 填入對應日期===
  $("select.selectDate").on("change", function () {
    let dateObjectBaseline;
    let sevenDayMillis = 7 * 24 * 60 * 60 * 1000;
    if ($("select.selectDate option.week1").prop("selected")) {
      dateObjectBaseline = now;
    } else if ($("select.selectDate option.week2").prop("selected")) {
      dateObjectBaseline = new Date(now.getTime() + sevenDayMillis);
    } else if ($("select.selectDate option.week3").prop("selected")) {
      dateObjectBaseline = new Date(now.getTime() + 2 * sevenDayMillis);
    } else if ($("select.selectDate option.week4").prop("selected")) {
      dateObjectBaseline = new Date(now.getTime() + 3 * sevenDayMillis);
    }
    trWrite(dateObjectBaseline);
  });

  //===========自動填入星期相關方法==============
  //=====預設載入頁面值===
  function trWriteinit(now) {
    trWrite(now);
  }

  //寫入日期標籤
  function trWrite(dateObjectBaseline) {
    let thisWeekDateTextMap = getThisWeekDate(dateObjectBaseline);
    let thisWeekDateObjectMap =
      getThisWeekEverydayDateObject(dateObjectBaseline);
    let thisWeekDateChineseMap = getChineseWeekDay(thisWeekDateObjectMap);

    $("div.week")
      .children("div.th")
      .each(function (index, item) {
        let text = thisWeekDateTextMap[index + 1];
        let text2 = thisWeekDateChineseMap[index + 1];
        $(item).html(`<span>${text}<br>(${text2})</span>`);
      });

    // $("tr.chineseWeekDay")
    //   .children("th")
    //   .each(function (index, item) {
    //     let text2 = thisWeekDateChineseMap[index + 1];
    //     $(item).html(text2);
    //   });
  }
  // 拿到每天日期字串;
  function getThisWeekDate(date) {
    let thisWeekDateObjectMap = getThisWeekEverydayDateObject(date);
    let thisWeekDateTextMap = getThisWeekEverydayDateText(
      thisWeekDateObjectMap
    );
    return thisWeekDateTextMap;
  }

  //拿到每天日期字串
  function getThisWeekEverydayDateText(thisWeekDateObjectMap) {
    let thisWeekDateTextMap = new Map();
    for (let i = 1; i < 7; i++) {
      let str = `${
        thisWeekDateObjectMap[i].getMonth() + 1
      } / ${thisWeekDateObjectMap[i].getDate()}`;
      thisWeekDateTextMap[i] = str;
      //   console.log(str);
    }
    return thisWeekDateTextMap;
  }

  //拿到本周每日Date物件
  function getThisWeekEverydayDateObject(date) {
    let todayMillis = date.getTime();
    let oneDayMillis = 1 * 24 * 60 * 60 * 1000;
    let thisWeekDateObjectMap = new Map();
    let thisMillis = todayMillis;
    for (let i = 1; i < 7; i++) {
      if (i == 1) {
        thisWeekDateObjectMap[i] = date;
      } else {
        thisMillis += oneDayMillis;
        thisWeekDateObjectMap[i] = new Date(thisMillis);
      }
      //   console.log(thisWeekDateObjectMap[i]);
    }
    return thisWeekDateObjectMap;
  }

  //拿到每天星期字串
  function getChineseWeekDay(thisWeekDateObjectMap) {
    let thisWeekDateChineseMap = new Map();
    for (let i = 1; i < 7; i++) {
      console.log(thisWeekDateObjectMap[i]);
      let n = thisWeekDateObjectMap[i].getDay();
      switch (n) {
        case 1:
          thisWeekDateChineseMap[i] = "一";
          break;
        case 2:
          thisWeekDateChineseMap[i] = "二";
          break;
        case 3:
          thisWeekDateChineseMap[i] = "三";
          break;
        case 4:
          thisWeekDateChineseMap[i] = "四";
          break;
        case 5:
          thisWeekDateChineseMap[i] = "五";
          break;
        case 6:
          thisWeekDateChineseMap[i] = "六";
          break;
        case 0:
          thisWeekDateChineseMap[i] = "日";
          break;
      }
    }
    return thisWeekDateChineseMap;
  }
});
