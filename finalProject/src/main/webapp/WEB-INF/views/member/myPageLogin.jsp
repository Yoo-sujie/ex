<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page | Login</title>

<link rel="stylesheet" href="/resources/lib/css/myPageLogin.css?after" type="text/css">
</head>
<body>
<div class="myPage">
	<img src="resources/lib/images/login.png" />
	<h1>본 페이지는 로그인을 해야 열람이 가능합니다.<br></h1>
	<form action="loginCheck.do" method="post" class="form12">
		<ul>
			<li>
				<input type="text" id="account" class="pww" placeholder="ID">
			</li>
			<li>
				<input type="password" id="pw" class="pww" placeholder="Password">
			</li>
		</ul>
		
		<ul>
			<li class="pwwco">
				<button><a>로그인</a></button>
			</li>
			<li class="myPages">
				<p>* 아이디를 분실하셨나요?<button class="mycs"><a>아이디 찾기</a></button></p>
			</li>
			<li class="myPages userLogins">
				<p>* 비밀번호를 분실하셨나요?<button class="mycs"><a>비밀번호 찾기</a></button></p>
			</li>
		</ul>
	</form>

</body>
</html>