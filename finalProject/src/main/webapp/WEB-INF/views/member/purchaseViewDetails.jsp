<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>상세 보기 | Board Content</title>
<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

.board_title {
	font-weight : 700;
	font-size : 22pt;
	margin : 10pt;
}

.board_info_box {
	color : #6B6B6B;
	margin : 10pt;
}

.board_sell_no, .board_seller_id {
	font-size : 10pt;
	margin-right : 10pt;
}

.board_date {
	font-size : 10pt;
}

.board_content {
	color : #444343;
	font-size : 12pt;
	margin : 10pt;
}

.board_tag {
	font-size : 11pt;
	margin : 10pt;
	padding-bottom : 10pt;
}
</style>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2>상세 보기 | Board Content</h2>
			<div class="bg-white rounded shadow-sm">
				<div class="board_title">
					<c:out value="${PurchaseDetailView.sell_title}" />
				</div>

				<div class="board_info_box">
					<span class="board_sell_no"><c:out value="${PurchaseDetailView.sell_no}" />,</span>
					<span class="board_seller_id"><c:out value="${PurchaseDetailView.sell_seller_id}" />,</span>
					<span class="board_date"><c:out value="${PurchaseDetailView.sell_date}" /></span> <br>
					<span class="board_sell_price"><c:out value="${PurchaseDetailView.sell_price}" />원</span>
				</div>

				<div class="board_content">${PurchaseDetailView.sell_contents}</div>
				<div class="board_tag">
					CATEGORY : <c:out value="${PurchaseDetailView.sell_category_name}" />
				</div>
			</div>

			<div style="margin-top: 20px">
				<button type="button" class="btn btn-sm btn-primary" id="btnPurchaseList">목록</button>
			</div>
		</div>
	</article>
</body>
</html>