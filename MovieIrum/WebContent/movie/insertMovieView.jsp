<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="stylesheet" href="\MovieIrum\css\style.css?after" type="text/css">
<link rel="stylesheet" href="\MovieIrum\css/style2.css?after" type="text/css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<style type="text/css"> 
 p {
 	color: #ffffff;
 	font-size: 20px;
 }
 input, button, textarea{ 
     border-radius: 6px; 
     border: 1px solid #dedede; 
     padding: 10px; 
     margin-top: 10px;
     color: black;
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
 #font, #point2, #point,#money,#submit {
 	 font-size: 15px;
 }

</style>
<script type="text/javascript" src="/MovieIrum/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	function movieSave() {
		var movieName = $('.movieName').val();
		var movieInfo = $('.movieInfo').val();
		var movieStar = $('.movieStar').val();
		var movieOpening = Number($('.movieOpening').val());
		var movieShowing = Number($('.movieShowing').val());
		var movieAge = Number($('.movieAge').val());
		var movieRoom = Number($('.movieRoom').val());
		var movieRoom2 = Number($('.movieRoom2').val());
		var movieStartTime = $('.movieStartTime').val();
		var movieStartTime2 = $('.movieStartTime2').val();
		if($('.movieName').val() == "" || $('.movieInfo').val() == "" || $('.movieStar').val() == ""
				|| $('.movieOpening').val() == "" || $('.movieShowing').val() == "" || $('.movieAge').val() == ""
				|| $('.movieRoom').val() == "" || $('.movieStartTime').val() == "" || $('.movieStartTime2').val() == ""
				||$('.movieRoom2').val() == "") {
			alert("값을 모두 채워주세요!");
		} else {
			if(sessionStorage.getItem("img").valueOf() == null) {
				alert("이미지를 등록해주세요!");
				location.href="/MovieIrum/movie/insertMovieView.jsp";
			} else {
				location.href="/MovieIrum/movieInsert.client?movieName="+ movieName +"&movieInfo="+movieInfo+"&movieStar="+movieStar
					+"&movieOpening="+movieOpening+"&movieShowing="+movieShowing+"&movieAge="+movieAge+"&movieRoom="+movieRoom
					+"&movieStartTime="+movieStartTime+"&movieRoom2="+movieRoom2+"&movieStartTime2="+movieStartTime2;	
			}
		}
	}
</script>
</head>
<body class="background">

<jsp:include page="../include/header.jsp"/>
<p style="height: 30px;"></p>
<form method="post"  enctype="multipart/form-data" action="imgup.jsp"  class="movie-title"> 
	<p>영화 등록</p>
	<hr>
<div class="ss" align="center">
	<input type="file" name="filename1" size="40">
	<input type="submit" value="업로드"><br>
	</form> 
	
	<form>
	<input type="text" name="movieName" class="movieName" placeholder="영화 이름" size="45"><br> 
	<textarea rows="10" cols="45" name="movieInfo" class="movieInfo" placeholder="영화 소개"></textarea><br> 
	<input type="text" name="movieStar" class="movieStar" placeholder="영화  평점" size="45"><br> 
	<input type="text" name="movieOpening" class="movieOpening"placeholder="영화 상영일" size="45"><br> 
	<input type="text" name="movieShowing" class="movieShowing" placeholder="영화 종영일" size="45" value=""><br> 
	<input type="text" name="movieAge" class="movieAge" placeholder="연령 등급" size="45"><br>
	<hr>
	<input type="text" name="movieRoom" class="movieRoom" placeholder="1관/2관 - 숫자만 입력" size="45"><br>
	<input type="text" name="movieStartTime" class="movieStartTime" placeholder="영화 시작 시간" size="45"><br>
	<hr>
	<input type="text" name="movieRoom2" class="movieRoom2" placeholder="1관/2관 - 숫자만 입력" size="45"><br>
	<input type="text" name="movieStartTime2" class="movieStartTime2" placeholder="영화 시작 시간" size="45"><br>
	<input type="button" value="등록하기" onclick="movieSave()">
</div>
</form>
<p style="height: 30px;"></p>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>