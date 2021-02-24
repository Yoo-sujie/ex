<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/lib/js/main.js"></script>
<jsp:include page="/header.jsp" />
<link rel="stylesheet" href="/resources/lib/css/login.css" type="text/css">
<h3>비밀번호 찾기 결과</h3>
<span class="result">회원님의 비밀번호는 ${findId}입니다.</span>
<button class="resultBtn" onclick="location.href='loginPage.do'">확인</button>
<jsp:include page="/footer.jsp" />