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
    <title>Ogani | Template</title>

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
              <a href="./index.html"><img src="img/logo.png" alt="" /></a>
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
            <div class="section-title product__discount__title">
              <h2>추천</h2>
            </div>

            <div class="shoping__cart__table">
              <table>
                <tbody>
                  <tr>
                    <td class="shoping__cart__item">
                      <h5>Vegetable’s Package</h5>
                      <br />
                      <p>날짜</p>
                      <p>일기 내용</p>
                    </td>
                  </tr>

                  <tr>
                    <td class="shoping__cart__item">
                      <h5>Vegetable’s Package</h5>
                      <br />
                      <p>날짜</p>
                      <p>일기 내용</p>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <div class="product__pagination">
              <a href="#">1</a>
              <a href="#">2</a>
              <a href="#">3</a>
              <a href="#"><i class="fa fa-long-arrow-right"></i></a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Hero Section End -->

    <!-- Js Plugins -->

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