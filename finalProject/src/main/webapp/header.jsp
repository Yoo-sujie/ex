<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RE:BOX</title>
	<jsp:include page="/script.jsp"/>
	
	<link rel="stylesheet" href="/resources/lib/css/layout.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="/resources/lib/css/header.css" type="text/css">
</head>
<body>

	<header>
		<nav class="navBox navigation">
			<div class="logoBox"> <!-- 로고박스 -->
				<a href="/main.jsp">
					<img src="/resources/lib/images/logo.png" alt="로고" />
				</a>
			</div>
			<ul id="main-menu">
				<li class="main">
					<a href="#">판매</a>
					<ul id="sub-menu">
						<!-- <li class="sub"><a href="/sellerView.do">TEST</a></li>
						<li class="sub"><a href="/buyer.do">BUYER</a></li> -->
						<li class="sub"><a href="/search/searchPage.do">SEARCH</a></li>
					</ul>
				</li>
				<li class="main">
					<a href="/mapView.do">자판기위치</a>
				</li>
				<li class="main">
					<a href="#">고객센터</a>
					<ul id="sub-menu">
						<li class="sub"><a href="/serviceCenter/noticePageMove.do">NOTICE</a></li>
						<li class="sub"><a href="/serviceCenter/qnaPageMove.do">QNA</a></li>
					</ul>
				</li>
				<c:if test="${sessionScope.loginId != null && sessionScope.loginStatus != 9}"> 
					<!-- 로그인 되어 있을때 -->
					<li class="main"><a href="/myPage.do" class="header_a">마이페이지</a></li>
				</c:if>
				<c:if test="${sessionScope.loginStatus eq 9 }">
					<!-- 관리자 페이지 -->
					<li class="main"><a href="/memberStopped.do" class="header_a" aria-label="subemnu" id="manageBtn">회원관리</a></li>
					<li class="main"><a href="/approvalList.do" class="header_a" aria-label="subemnu">승인관리</a></li>
         			<li class="main"><a href="/manager/insertSellPlacePage.do" class="header_a" aria-label="subemnu" id="manageBtn">자판기관리</a></li>
				</c:if>
			</ul>
		</nav>
		<ul class="statusBox">
			<c:if test="${sessionScope.loginId eq null }"> <!-- 로그인이 안되어 있을때 -->
				<li class="offline"><a class="stsBtn" href="/joinPage.do">회원가입</a></li>
				<li class="offline"><a class="stsBtn stsBtn2" href="#" id="loginPage">로그인</a></li>
			</c:if>
			<c:if test="${sessionScope.loginId != null }"> <!-- 로그인 되어 있을때 -->
				<li class="online">
					<a href="#" id="logout" class="stsBtn">로그아웃</a>
				</li>
				<li class="online">
					<span><b>${sessionScope.loginId }</b>님 환영합니다!</span>
				</li>
			</c:if>
		</ul>
	</header>
</body>
</html>