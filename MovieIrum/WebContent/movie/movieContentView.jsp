<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>

	<link rel="stylesheet" href="css/style.css?after"/>
	<link rel="stylesheet" href="css/style-main.css?after"/>
   	<link rel="stylesheet" href="css/reset-main.css?after"/>
   
  	<!-- 파비콘 -->
   	<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
   	<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
   	
</head>
<body class="background">

   	<section id="movie">
       	   	<div class="movie_info">
        		<div class="movie_info1">
					<img alt="${vo.moviePoster}" src="${vo.moviePoster}">
        		</div>
        		<div class="movie_info2">
		         	<div class="movie_info2_a">
		         		<h2>${vo.movieName}</h2>
		         	</div>
		         	<div class="movie_info2_b">
			         	<div class="b_info">
			         		${vo.movieInfo}
			         	</div>
			         	<div class="b_star">
			         		평점 : ${vo.movieStar} / 5점
			         	</div>
			         	<div class="b_show">
			         		상영날짜 :  ${vo.movieOpening} ~ ${vo.movieShowing}
			         	</div>
			         	<div class="b_age">
			         		연령 제한 : ${vo.movieAge}
			         	</div>
		         	</div>
       			 </div>
         	 </div>
   	</section>

</body>
</html>