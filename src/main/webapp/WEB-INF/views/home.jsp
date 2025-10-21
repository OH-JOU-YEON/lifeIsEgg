<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="오늘 하루의 삶은 달걀" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Life is Egg | Home</title>

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
              <a href="/lifeEgg/home">
                <div
                  class="logo"
                  style="
                    display: flex;
                    flex-direction: row;
                    align-items: center;
                  "
                >
                  <img
                    src="<c:url value='./resources/img/boiled-egg.png'/>"
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
              </ul>
            </div>
          </div>
          <div class="col-lg-9">
            <div class="hero__search">
              <div style="display: flex">
                <button class="site-btn" onclick="login()">회원가입/로그인</button>
                <script type="text/javascript">
                	function login(){
                		const form = document.createElement("form");
                	    form.method = "POST";
                	    form.action = "http://localhost:8090/lifeEgg/oauth2/google";
                	    document.body.appendChild(form);
                	    form.submit();
					}
				</script>   
              </div>
            </div>
            <div class="product__details__text" style="display: flex">
              <div class="col" style="position:relative; left:20px;">
                <br>
                <h3>오늘 하루의 마무리, 삶은 달걀</h3>
                <br>
                <p style="display:inline">오늘 있었던 일을 일기로 기록할 수 있어요.</p>
                <br>
                <br>
                <p style="display:inline">누군가의 일기에 공감하고, 응원하는 말을 남길 수 있어요.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Hero Section End -->

    <!-- Js Plugins -->
   
  </body>
</html>