<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<style type="text/css">
	img {
		width: 200px;
		height: 250px;
	}
	
	table {
		width: 80%;
	}
</style>
<link rel="stylesheet" href="css/style.css?after" type="text/css">
<link rel="stylesheet" href="css/style2.css?after" type="text/css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
</head>
<body>

<jsp:include page="..\include\header.jsp"/>
	<br><br>
    <div class="movie-title">영화 소개<hr></div>
	<table border="0">
		<tr>
		<c:forEach var="vo" items="${list}">
			<td><a href="movieContentView.client?movieName=${vo.movieName}"><img alt="${vo.moviePoster}" src="${vo.moviePoster}"></a></td>
		</c:forEach>
		</tr>
		<tr>
		<c:forEach var="vo" items="${list}">
			<td><a href="movieContentView.client?movieName=${vo.movieName}">${vo.movieName}</a></td>
		</c:forEach>
		</tr>
	</table>
<jsp:include page="..\include\footer.jsp"/>
</body>
</html>