<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp" />
<script type="text/javascript" src="/resources/lib/js/main.js"></script>
<link rel="stylesheet" href="/resources/lib/css/myPage.css?after" type="text/css">
<script type="text/javascript" src="/resources/lib/js/jquery-3.2.1.min.js"></script> 

<h3>마이페이지</h3>

<ul class="myNav">
	<li><a class="myPage" href="myPage.do">내 정보</a></li>
	<li><a class="PurchaseHistory" href="PurchaseHistory.do">구매 내역</a></li>
	<li><a class="SalesHistory" href="SalesHistory.do">판매 내역</a></li>
	<li><a class="myReportList" href="myReportList.do">신고 내역</a></li>
</ul>

<jsp:include page="${contentPage}" />

<jsp:include page="/footer.jsp" />