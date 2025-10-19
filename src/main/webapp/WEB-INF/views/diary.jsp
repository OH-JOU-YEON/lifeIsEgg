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
    <title>Life is Egg | Diary</title>

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
    <!-- Header Section End --

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
           <div class="section-title product__discount__title">
              <h2>'<c:out value="${post.created_at}"/>'</h2>
            </div>
            <div id="viewer">
            <c:out value="${post.content}"/>

            </div>
            <br />
            <div style="display: flex">
              <button id = "deleteOrCheer" class="last-btn" style="margin-left: auto" onclick = 'deleteOrCheer()'>
                응원하기
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
   
    
    let cheerable = <c:out value="${cheerable}"/>;
    
    //응원을 보낼 때 응원을 보내는 일기의 uuid를 파라미터로 받음 
    //cheer의 uuid는 별개임!!! 
    let diaryUuid = <c:out value="${uuid}"/>;
    let publicParam = document.querySelector('#deleteOrCheer'); 
    userId = await ${user.id}; 
	
	
    
    


	</script>
    <script src="<c:url value="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"/>"/></script>
    <script src="<c:url value="/resources/js/cheer.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery.nice-select.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery-ui.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/jquery.slicknav.js"/>"/></script>
    <script src="<c:url value="/resources/js/mixitup.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/owl.carousel.min.js"/>"/></script>
    <script src="<c:url value="/resources/js/main.js"/>"/></script>
  
  </body>
</html>