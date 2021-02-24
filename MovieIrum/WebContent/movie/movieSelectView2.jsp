<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>

   <link rel="stylesheet" href="css/style.css?after"/>
   <link rel="stylesheet" href="css/style-main.css?after"/>
   <link rel="stylesheet" href="css/reset-main.css?after"/>
   <link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
   <link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
 

</head>
<body class="background">

   <jsp:include page="..\include\header.jsp"/>
   
   <p style="height: 84px;"></p>
   
   <section id="movie">
      <div class="container">
         <div class="row">
            <div class="movie">
               <div class="movie_chart" style="width: 1500px;">
               
                  <!-- 최신 영화 -->
                  <div class="swiper-container2">
                     <div class="swiper-wrapper">
                  		<c:forEach var="vo" items="${list}">

	                        <div class="swiper-slide" style="float: left; margin: 20px;">
	                           <div class="poster">
	                              <figure>
	                                 <img src="${vo.moviePoster}" alt="movie" />
	                              </figure>
	                           </div>
	                           <div class="infor">
	                              <h3><strong>${vo.movieName}</strong></h3>
	                              <div class="infor_btn">
	                              		<a href="movieContentView.client?movieName=${vo.movieName}" class="white"
	                              			onclick="window.open(this.href, '_blanck', 'width=750, height=480, left=550, top=270, status=no'); return false">상세 보기</a>
	                              		<a href="movieSelectView.client" class="black">예매하기</a>
	                              </div>
	                           </div>
	                        </div>

                    	</c:forEach>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </section>
   
   <jsp:include page="..\include\footer.jsp"/>
   
</body>
</html>