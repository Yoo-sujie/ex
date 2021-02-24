<%@page import="movie.movieVO.MovieVO"%>
<%@page import="movie.timeDAO.MovieTimeDAO"%>
<%@page import="movie.movieDAO.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="index.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

<%
	if(session.getAttribute("sessionID")==null) {
%>
	<script>
		alert("회원만 이용 가능합니다!");
		location.href="/MovieIrum/Client/LoginForm.jsp";
	</script>
<%} %>

<style type="text/css"> 
     h1{ 
         font-family: 'Oswald', sans-serif; 
         font-size: 30px; 
         color: #3C3C50; 
     }
     h2{ 
         font-family: 'Oswald', sans-serif; 
         font-size: 25px; 
         color: #999;
     }
     p {
     	font-family: 'Oswald', sans-serif; 
        font-size: 15px; 
        color: #999;
     }
     .movieDate {
     	text-align: center;
     	font-size: 16px;
     }
     .movieName, .movieDate, .movieTime{
         border: none; 
         height: 30px;
         cursor: pointer; 
         font-family: 'Oswald', sans-serif;
         color: #000000; 
     } 
     .movieName:hover, .movieDate:hover, .movieTime:hover{ 
         color: #fff; 
         background-color: #252331;
         opacity: 0.9; 
     }
     .movieContent1, strong {
     	 text-align: center;
     	 padding: 5px;
     	 font-size: 14px;
         font-family: 'Oswald', sans-serif;
     }
     .movieContent2 {
     	padding: 5px;
     	font-size: 13px;
        font-family: 'Oswald', sans-serif;
     }
     .popup { 
     	 display: block; 
     	 text-align: center; 
         margin: 0 auto; 
         width: 459px;
         color: #999; 
         font-size: 16px;
     } 
     input, button{ 
         width: 200px; 
         height: 27px; 
         background-color: #efefef; 
         border-radius: 6px; 
         border: 1px solid #dedede; 
         padding: 10px; 
         margin-top: 3px; 
         font-size: 15px;
         color: #3a3a3a;
     } 
     input:focus { 
         border: 1px solid #97d6eb; 
     } 
    
     #submit{ 
         width: 127px; 
         height: 48px;
         text-align: center; 
         border: none; 
         margin-top: 20px; 
         cursor: pointer; 
     } 
     #submit:hover{ 
         color: #fff; 
         background-color: #252331; 
         opacity: 0.9; 
     } 
     #cancel { 
         width: 127px; height: 48px; 
         text-align: center; 
         border: none; 
         margin-top: 20px; 
         cursor: pointer; 
     } 
     #cancel:hover{ 
         color: #fff; 
         background-color: #252331; 
         opacity: 0.9; 
     }

    .modal, .modal2 { 
         position: fixed; 
         left: 0; 
         top: 0; 
         width: 100%; 
         height: 100%; 
         background-color: rgba(0, 0, 0, 0.5); 
         opacity: 0; 
         visibility: hidden; 
         transform: scale(1.1); 
         transition: visibility 0s linear 0.25s, opacity 0.25s 0s, transform 0.25s; 
     } 
     .modal-content, .modal-content2 { 
         position: absolute; 
         top: 50%; 
         left: 50%; 
         transform: translate(-50%, -50%); 
         background-color: white; 
         padding: 1rem 1.5rem; 
         width: 500px; 
         height: 350px; 
         border-radius: 0.5rem; 
     } 
     .close-button { 
         float: right; 
         width: 1.5rem; 
         line-height: 1.5rem; 
         text-align: center; 
         cursor: pointer; 
         border-radius: 0.25rem; 
         background-color: lightgray; 
     } 
     .close-button:hover { 
         background-color: darkgray; 
     } 
     .show-modal, .show-modal2 { 
         opacity: 1; 
         visibility: visible; 
         transform: scale(1.0); 
         transition: visibility 0s linear 0s, opacity 0.25s 0s, transform 0.25s; 
     } 
    .reserve-content {
    	width: 264px;
    }
</style>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<script src="js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="\MovieIrum\css/style.css?after" type="text/css">
<link rel="stylesheet" href="\MovieIrum\css/style2.css?after" type="text/css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	function allreset() {
		console.log("초기화");
		$( '.reserve-content').remove();
		$( '#movieDate').remove();
		$( '#timeId').remove();
	}
</script>
</head>
<body>
    <jsp:include page="..\include\header.jsp"/>
    
    <br><br>
    <form action="movieInfo.client?movieName2=${mvVo.movieName}">
    <div class="movie-title">빠른 예매<hr>
    <!-- <input class="guide" id="submit" type="button" value="예매 가이드"> -->
    <input type="reset" id="submit" value="초기화" onclick="allreset()">
    <c:choose>
    <c:when test="${sessionScope.movieAge >= 15}">
    	<input type="button" id="submit" value="좌석 선택하기" class="seat_btn">
    </c:when>
    <c:otherwise>
    	<input type="submit" id="submit" value="좌석 선택하기">
    </c:otherwise>
    </c:choose>
    </div>
    <!-- 팝업 될 레이어 --> 
     <div class="modal"> 
         <div class="modal-content"> 
             <span class="close-button">&times;</span> 
             <h1 class="title"></h1> 
             <div class="popup"> 
               <h1>관람등급 안내</h1>
				<hr>
				<h2>${sessionScope.movieAge}세 이상 관람가</h2>
				 <p>
				 본 영화는 만 ${sessionScope.movieAge}세 이상 관람 가능한 영화입니다.<br>
				 만 ${sessionScope.movieAge}세 미만 고객은 보호자 동반 시 관람이 가능합니다.<br>
				 연령 확인 불가 시 입장이 제한 될 수 있습니다.<br>
				 </p>
               <input type="submit" id="submit" value="확인"> 
             </div> 
         </div> 
     </div>
   
    <script type="text/javascript"> 
         var modal = document.querySelector(".modal"); 
         var seat_btn = document.querySelector(".seat_btn");
         var closeButton = document.querySelector(".close-button");  
         var cancelButton = document.querySelector("#cancel");

        //console.log(modal);

        function toggleModal() { 
             modal.classList.toggle("show-modal"); 
         }

        function windowOnClick(event) { 
             if (event.target === modal) { 
                 toggleModal(); 
             }
         }
       
         seat_btn.addEventListener("click", toggleModal);
         closeButton.addEventListener("click", toggleModal); 
         window.addEventListener("click", windowOnClick);
     </script>
     
    <div class="reserve-container">
        <div class="movie-part">
            <div class="reserve-title">영화</div>
            <div class="sort-wrapper">
                <div class="sort-rate sort-selected">마감일순</div>
            </div>
            <div class="movie-list">
            <select size="40" name="movieName" id = "movieId" onchange="window.open(value,'_self');">
				<c:forEach var="vo" items="${list}">
					<option class="movieName" value="movieSelectView.client?movieName=${vo.movieName}&movieAge=${vo.movieAge}">${vo.movieName}
				</c:forEach>
			</select>
            </div>
        </div>
        <div class="theater-part">
            <div class="reserve-title">영화정보</div>
            <c:if test="${mvVo.movieName ne null}">
            <table class="reserve-content">
            	<tr><td class="movieContent1" colspan="2"><img src="${mvVo.moviePoster}" alt="${mvVo.moviePoster}" width="200px;" height="286px;"></td></tr>
            	<tr><td class="movieContent1" colspan="2"><strong>${mvVo.movieName}</strong></td></tr>
            	<tr><td class="movieContent1" colspan="2">${mvVo.movieInfo}</td></tr>
            	<tr><td colspan="2"><hr></td></tr>
            	<tr><td><strong>평점</strong></td><td class="movieContent2">${mvVo.movieStar}</td></tr>
            	<tr><td><strong>기간</strong></td><td class="movieContent2">${mvVo.movieOpening} ~ ${mvVo.movieShowing}</td></tr>
            	<c:choose>
            	<c:when test="${mvVo.movieAge eq 0}">
            		<tr><td><strong>나이</strong></td><td class="movieContent2">ALL 모든 연령 관람 가능</td></tr>
            	</c:when>
            	<c:otherwise>
            		<tr><td><strong>나이</strong></td><td class="movieContent2">${mvVo.movieAge}세 이상</td></tr>
            	</c:otherwise>
            	</c:choose>
            </table>
            </c:if>
            <div></div>
        </div>
        <div class="day-part">
            <div class="reserve-title">날짜</div>
            <div class="month">${month}월</div>
            <select size="40" name="day" class="reserve-date" id = "movieDate">
            <c:if test="${mvVo.movieName ne null}"> 
            	<c:forEach var="day" items="${dayList}">
            		<option class="movieDate" value="${day}">${day}
            	</c:forEach>
            </c:if>
            </select>
        </div>
        <div class="time-part">
            <div class="reserve-title">시간</div>
            <div class="reserve-time" id="timeId"> 
            <c:if test="${mvVo.movieName ne null}"> 
            	<select size="40" name="time1" class="reserve-date">
            	<c:forEach var="time1" items="${time1}">
            		<option class="movieTime" value="${time1}<1관>">${time1}<1관>
            	</c:forEach>
            	<br>
            	<c:forEach var="time2" items="${time2}">
            		<option value="${time2}<2관>">${time2}<2관>
            	</c:forEach>
            	</select>
            </c:if>
            </div>
        </div>
    </div>
    </form>
    <jsp:include page="..\include\footer.jsp"/>
</body>

</html>