<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/lib/css/errorPage.css?after" type="text/css">
</head>
<body>
	<div>
		<img src="/resources/lib/images/404error.png" id="close"/>
		<button onclick="javascript:location.href='/main.jsp'">메인페이지 이동</button>
	</div>
</body>
</html>