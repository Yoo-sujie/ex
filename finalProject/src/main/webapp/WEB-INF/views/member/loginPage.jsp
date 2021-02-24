<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp" />
<script type="text/javascript" src="/resources/lib/js/main.js"></script>
<link rel="stylesheet" href="/resources/lib/css/login.css" type="text/css">


<div class="formBox" id="form-box">
	<img src="/resources/lib/images/logo.png" alt="로고" />
	<h3>로그인</h3>
	<form action="/loginCheck.do" method="post">
		<input type="text" name="id" id="id" required autocomplete="off" placeholder="ID *">   
		<input type="password" name="pw" id="pw" required autocomplete="off" placeholder="Password *">      
		<button type="submit" class="actionBtn">로그인</button>   
	</form>
	<ul class="btnList">
		<li><button class="moveBtn" onclick="location.href='joinPage.do'">리:박스의 회원이 아니신가요?</button></li>
		<li>
			<button class="moveBtn" id="findId" name="findId" onclick="location.href='findIdForm.do'">아이디</button>
			<span>·</span>
			<button class="moveBtn" id="findPw" name="findPw" onclick="location.href='findPwForm.do'">비밀번호 찾기</button>
		</li>
	</ul>
</div>

<jsp:include page="/footer.jsp" />