<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/resources/lib/js/main.js"></script>
<link rel="stylesheet" href="/resources/lib/css/myPage.css" type="text/css">

<script>
	$(document).ready(function() { 
		$('ul.myNav li a.myReportList').siblings().removeClass('on');
		$('ul.myNav li a.myReportList').addClass('on');
	});
</script>

<div class="optionArea">
	<input type="button" id="ok" name="ok" class="ok actionBtn fleft" onclick="location.href='myPage.do'" value="뒤로가기">
</div>
<table class="listBoard" id="reportList" name="reportList">
	<thead>
		<tr>
			<th>글번호</th>
			<th>판매자</th>
			<th>상품</th>
			<th>카테고리</th>
			<th>글 제목</th>
			<th>구매 금액</th>
			<th>거래 장소</th>
			<th>거래 날짜</th>
		</tr>
	</thead>
	<tbody id="reportTd">
		<c:choose>
			<c:when test="${empty reportList}">
				<tr>
					<td colspan="8" align="center">데이터가 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${!empty reportList}">
				<c:forEach var="report" items="${reportList}">
					<tr>
						<td><c:out value="${report.SELL_NO}" /></td>
						<td><c:out value="${report.SELL_SELLER_ID}" /></td>
						<td><img src="${report.SELL_IMAGE}" alt="image1" width="100" height="100"></td>
						<td><c:out value="${report.SELL_CATEGORY_NAME}" /></td>
						<td><c:out value="${report.SELL_TITLE}" /></td>
						<td><c:out value="${report.SELL_PRICE}" /></td>
						<td><c:out value="${report.SELL_PLACE_NAME}" /></td>
						<c:set var="sell_date" value="${report.SELL_DATE}" />
						<c:choose>
							<c:when test="${sell_date eq null }">
     									<td>미판매</td>
 									</c:when>
							<c:when test="${!empty sell_date}">
    										<td>${report.SELL_DATE}</td>
 									</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
</table>
<nav aria-label="Page navigation" class="page">
	<ul class="pagination" id="PageWrap"></ul>
</nav>