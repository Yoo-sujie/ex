
<%@page import="client.clientDAO.ClientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script>



</script>
<style>
body {
	margin-top: 250px;
	background-color: #F5F5F5;
}

* {
	margin: 0;
	padding: 0;
}

div {
	width: 430px;
	margin: 20px auto;
}

li {
	list-style: none;
	margin: 30px 0;
}

label {
	
}

.Find {
	height: 43px;
	width: 420px;
	font-size: 13px;
	cursor: pointer;
	color: white;
	display: block;
	background-color: #252331;
	border: 0px solid #378;
}

.id {
	cursor: pointer;
	height: 43px;
	width: 420px;
}

.name {
	cursor: pointer;
	height: 43px;
	width: 420px;
}

.email {
	cursor: pointer;
	height: 43px;
	width: 420px;
	margin-top: 1px;
}

.lab_g {
	float: left;
}

.txt_find2 {
	float: right;
}

.inp_radio {
	font: 7px;
}

label {
	font-size: 12px;
}
</style>
</head>

<body>
	<div>
		<form action="LoginForm.jsp" method="post" name="Find_idOKForm">
			<ul>
				<h2>　　　　　　아이디 찾기</h2>
				<li></li>
				<h5>　　　　　　회원가입 시 사용하신 아이디는<%=request.getAttribute("findid") %>입니다</h5>
				
				<li></li>
				<br>
				<input type="hidden" name="redirectUrl" value="">

				<li></li>
				<input type="submit" class="Find" value="로그인 화면으로 돌아가기"></input>
	</div>
	</ul>
	</form>
</body>
</html>