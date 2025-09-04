<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="
	com.lifeEgg.dto.PostDTO,
	
	java.util.List,
	java.util.ArrayList"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html lang="zxx">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="Ogani Template" />
    <meta name="keywords" content="Ogani, unica, creative, html" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>feed</title>

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
      <div class="headertop">
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
              <a href="./home">
                <div
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
                <li><a href="./feed">추천</a></li>
                <li><a href="./alarms">알림</a></li>
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
                  <c:forEach items="${postList}" var="post">

                  <tr>
                    <td class="shoping__cart__item">
                      <h5>Vegetable’s Package</h5>
                      <br />
                      <p>${post.created_at}</p>
                      <p>${post.content}</p>
                    </td>
                  </tr>
                  
                  </c:forEach>
                </tbody>
              </table>
            </div>


            <!-- 페이징 영역 시작 -->
            <div class="product__pagination">
              <c:choose>
        	    <c:when test="${postPages.hasPrev}">
        		  <c:set var="url" value="${postPages.getUrl('./feed', postPages.page - 1)}" />
        	    </c:when>
        	    <c:otherwise><c:set var="url" value="./feed" /></c:otherwise>
    		  </c:choose>
    		
    		  <a href="${url}"<c:if test="${!postPages.hasPrev}"> class="disabled"</c:if>>
        	    <span class="sr-only">Previous</span>
        	    <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
           	  	  <path fill-rule="evenodd" d="M12.79 5.23a.75.75 0 01-.02 1.06L8.832 10l3.938 3.71a.75.75 0 11-1.04 1.08l-4.5-4.25a.75.75 0 010-1.08l4.5-4.25a.75.75 0 011.06.02z" clip-rule="evenodd" />
        	    </svg>
    		  </a>
    		
    		  <c:forEach var="i" begin="${postPages.firstPage}" end="${postPages.lastPage}" step="1">
    		  <a href="${postPages.getUrl('./feed', i)}"
    		    <c:if test="${postPages.page eq i}">
    		  	  aria-current="page" class="active"
    		    </c:if>>${i}</a>
    		  </c:forEach>
    		
    		  <c:choose>
        	    <c:when test="${postPages.hasNext}">
        		  <c:set var="url" value="${postPages.getUrl('./feed', pageInfo.page + 1)}" />
        	    </c:when>
        	    <c:otherwise><c:set var="url" value="#" /></c:otherwise>
    		  </c:choose>
    		
    		  <a href="${url}"<c:if test="${!postPages.hasNext}"> class="disabled"</c:if>>
        	    <span class="sr-only">Next</span>
        	    <svg class="h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
              	  <path fill-rule="evenodd" d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z" clip-rule="evenodd" />
        	    </svg>
    		  </a>
    		</div>

<!--  
            
-->            
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