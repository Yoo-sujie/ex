<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="/resources/lib/js/PurchaseHistoryPaging.js"></script>
<script type="text/javascript" src="/resources/lib/js/main.js"></script>

<link rel="stylesheet" href="/resources/lib/css/myPage.css" type="text/css">

<script>
	$(document).ready(function() { 
		$('ul.myNav li a.PurchaseHistory').siblings().removeClass('on');
		$('ul.myNav li a.PurchaseHistory').addClass('on');
	});
</script>

<div class="optionArea">
	<input type="button" id="ok" name="ok" class="ok actionBtn fleft" onclick="location.href='myPage.do'" value="뒤로가기">
</div>
<table class="listBoard" id="purchaseList" name="purchaseList">
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
	<tbody id="purchaseTd">
		<c:choose>
			<c:when test="${empty PurchaseList}">
				<tr>
					<td colspan="8" align="center">데이터가 없습니다.</td>
				</tr>
			</c:when>

			<c:when test="${!empty PurchaseList}">
				<c:forEach var="list" items="${PurchaseList}">
					<tr>
						<td><c:out value="${list.SELL_NO}" /></td>
						<td><c:out value="${list.SELL_SELLER_ID}" /></td>
						<td><img src="${list.SELL_IMAGE}" alt="image1" width="100" height="100"></td>
						<td><c:out value="${list.SELL_CATEGORY_NAME}" /></td>
						<td><c:out value="${list.SELL_TITLE}" /></td>
						<td><c:out value="${list.SELL_PRICE}" /></td>
						<td><c:out value="${list.SELL_PLACE_NAME}" /></td>
						<c:set var="buy_date" value="${list.SELL_DATE}" />
							<c:choose>
								<c:when test="${buy_date eq null }">
      									<td>미판매</td>
  									</c:when>
								<c:when test="${!empty buy_date}">
     										<td>${list.SELL_DATE}</td>
  									</c:when>
							</c:choose>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
</table>

<nav aria-label="Page navigation" class="page">
	<ul class="pagination justify-content-center" id="PageWrap">
	</ul>
</nav>