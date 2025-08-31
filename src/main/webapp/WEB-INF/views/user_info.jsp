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
    <title>LifeIsEgg</title>

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
              <a href="./index.html"
                ><div
                  class="logo"
                  style="
                    display: flex;
                    flex-direction: row;
                    align-items: center;
                  "
                >
                  <img
                    src="<c:url value='resources/img/boiled-egg.png'/>"
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
        <div class="humberger__open">
          <i class="fa fa-bars"></i>
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
                <li><a href="#">내 일기 목록</a></li>
                <li><a href="#">추천</a></li>
                <li><a href="#">알림</a></li>
              </ul>
            </div>
           </div>
          <div class="col-lg-9">
            <div class="hero__search">
              <div style="display: flex">
                <button class="site-btn" onclick="logout()" style="margin-left: auto">로그아웃</button>
                <script type="text/javascript">
                	function logout(){
                 		const form = document.createElement("form");
                	    form.method = "GET";
                	    form.action = "http://localhost:8090/lifeEgg/logout";
                	    document.body.appendChild(form);
                	    form.submit(); 
					}
				</script>
              </div>
            </div>
            <div class="product__details__text">
              <div class="col" style="position:relative; left:30px; width:75%">
                <br>
                <h4>내 정보</h4>
                <br>
                <p>내 정보는 다른 사용자에게 노출되지 않습니다.</p>
                <div class="row" style="position:relative; left:20px;">
                  <label for="name">이름</label>
                  <input type="text" id="name" placeholder="${user.name}" value="${user.name}"
                  maxlength="15" size="17" style="margin-left:auto;">
                </div>
                
                <br>
				<div class="row" style="position:relative; left:20px;">
                  <label for="age">나이</label>
                  <input type="number" id="age" placeholder="${user.age}" value="${user.age}"
                  min=0 max=99 size="2" style="margin-left:auto;">
                </div>
                <br>
                <div class="row" style="position:relative; left:20px;">
                  <label>연결된 이메일</label>
                  <p style="margin-left:auto;">${user.email}</p>
                </div>
                <br>
                <div class="row" style="position:relative; left:20px;">
                  <button class="last-btn" style="margin-left: auto">저장</button>
                </div>
                
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