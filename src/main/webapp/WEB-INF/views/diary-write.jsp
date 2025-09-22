<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Ogani Template" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Life is Egg... | Write</title>

    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
      rel="stylesheet"
    />

    <!-- Css Styles -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/elegant-icons.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/nice-select.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/owl.carousel.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/slicknav.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
    <link rel="stylesheet" href="<c:url value="https://uicdn.toast.com/editor/latest/toastui-editor.min.css"/>" />
  </head>

  <body>
    <!-- Page Preloder -->
    <div id="preloder">
      <div class="loader"></div>
    </div>

    <!-- Header Section Begin -->
    <header class="header">
      <div class="header__top">
        <div class="container">
          <div class="row">
            <div class="col-lg-6 col-md-6"></div>
            <div class="col-lg-6 col-md-6"></div>
          </div>
        </div>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="header__logo">
             <a href="/lifeEgg/home"
                ><div
                  class="logo"
                  style="
                    display: flex;
                    flex-direction: row;
                    align-items: center;
                  "
                >
                  <img
                    src="<c:url value='/resources/img/boiled-egg.png'/>"
                    style="width: 50px; height: 50px; margin-right: 8px"
                    alt=""
                  />
                  <h2>Life is Egg...</h2>
                </div>
              </a>
            </div>
          </div>
          <div class="col-lg-6"></div>
          <div class="col-lg-3"></div>
        </div>
      </div>
    </header>
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero">
      <div class="container">
        <div class="row">
          <div class="col-lg-3">
            <div class="hero__categories">
              <div class="hero__categories__all">
                <i class="fa fa-bars"></i>
                <span></span>
              </div>
              <ul>
                <li><a href="/lifeEgg/diaries">내 일기 목록</a></li>
                <li><a href="/lifeEgg/feed">추천</a></li>
                <li><a href="/lifeEgg/alarms">알림</a></li>
                <li><a href="/lifeEgg/user">내 정보</a></li>
                <li><a href="/lifeEgg/logout">로그아웃</a></li>
              </ul>
            </div>
          </div>
         <div class="col-lg-9">
            <div class="hero__search">
              <div class="hero__search__form">
                <button class="site-btn" id = "publicOrNot" onclick = publicOrNot()>일기 공개하기</button>
              </div>
            </div>
            <input type="date" id = "created_at" value = "${post.created_at}"/>
            <div id="content" data-name="main-content"></div>
            <br />
            <div style="display: flex">
              <button class="last-btn" style="margin-left: auto" onclick = createOrUpdate()>
                작성하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Hero Section End -->

    <!-- Js Plugins -->
    <script>
 // javascript에서 jstl 단일값 사용
    const userAge = '<c:out value="${user.age}"/>';
    const userId = '<c:out value="${user.id}"/>';


</script>

    <script src="<c:url value="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery.nice-select.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery-ui.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery.slicknav.js"/>"/></script>
    <script src="<c:url value="/resources/js/mixitup.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/owl.carousel.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/diary.js"/>"/></script>
    <script src="<c:url value="/resources/js/main.js"/>"/></script>
    <script>
    const editor = new toastui.Editor({
    	  el: document.querySelector("#content"),
    	  height: "500px",
    	  initialEditType: "wysiwyg",
    	  initialValue: "<c:out value='${post.content}' default='내용을 입력해 주세요.'/>",
    	  previewStyle: "vertical",
    	  hideModeSwitch: true,
    	  hooks: {
    	    async addImageBlobHook(blob, callback) {
    	      try {
    	        callback("", "");
    	      } catch (error) {
    	        console.error("업로드 실패 : ", error);
    	      }
    	    },
    	  },
    	});

    	editor.removeToolbarItem("image");
</script>
  </body>
</html>