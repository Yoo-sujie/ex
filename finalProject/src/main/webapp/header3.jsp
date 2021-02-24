<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/script.jsp"/>

<link rel="stylesheet" href="/resources/lib/css/style1.css?after" type="text/css">

</head>
<body class="back">


<a class="navbar-brand" href="/main.jsp">
<h1>
<div class="loading">
  	<span>L</span>
  	<span>O</span>
  	<span>G</span>
  	<span>O</span>
  	<span>.</span>
  	<span>.</span>
  	<span>.</span>
  </div>
</h1>
</a>

<nav role="navigation">
  <ul id="main-menu">
    <li><a href="/mapView.do">MAP</a></li>
    <li><a href="#">판매</a>
      <ul id="sub-menu">
        <li><a href="/sellerView.do" aria-label="subemnu">테스트</a></li>
        <li><a href="#" aria-label="subemnu">submenu</a></li>
      </ul>
    </li>
    <li><a href="#">MENU3</a>
      <ul id="sub-menu">
        <li><a href="#" aria-label="subemnu">submenu</a></li>
        <li><a href="#" aria-label="subemnu">submenu</a></li>
      </ul>
    </li>
    <li><a href="#">MENU4</a>
      <ul id="sub-menu">
        <li><a href="#" aria-label="subemnu">submenu</a></li>
        <li><a href="#" aria-label="subemnu">submenu</a></li>
      </ul>
    </li>
    <c:if test="${sessionScope.loginId eq null }">
    	<li><a href="#" aria-label="subemnu" id= "loginPage">
    	LOGIN
    	</a></li>
    </c:if>
    <c:if test="${sessionScope.loginId != null }">
    	<li><a class="navbar-brand" href="#" value = "${sessionScope.loginId }">${sessionScope.loginId }님</a></li>
    	<li><input type="button" id= "logout" value="로그아웃"></li>
    	<li><a href="#">MYPAGE</a></li>
    </c:if>
  </ul>
 
</nav>

<p class="background"><br><br><br><br></p>

</body>
</html>