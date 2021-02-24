<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<script type="text/javascript" src="/resources/lib/js/review.js"></script>

	<table id='reviewTable'>
		<tr>
			<td>구매자</td>
			<td>${map.MEMBER_NIC }</td>
		</tr>
		<tr>
			<td>구매날짜</td>
			<td>${map.REVIEW_WRITE_DATE }</td>
		</tr>

		<tr>
			<td>평점</td>
			<td><img src="/resources/lib/images/review/star/star${map.REVIEW_RATING }.png" style="width: 50%;"></td>
		</tr>
		<tr>
			<td colspan="1">
				<div style="width: 300px; margin: 100px;">
					<!-- carousel를 사용하기 위해서는 class에 carousel와 slide 설정한다. -->
					<!-- carousel는 특이하게 id를 설정해야 한다.-->
					<div id="carousel-example-generic" class="carousel slide">
						<!-- carousel의 지시자 -->
						<!-- 지시자라고는 하는데 ol태그의 class에 carousel-indicators를 넣는다. -->
						<ol class="carousel-indicators">
							<!-- li는 이미지 개수만큼 추가하고 data-target은 carousel id를 가르킨다. -->
							<!-- data-slide-to는 순서대로 0부터 올라가고 0은 active를 설정한다. -->
							<!-- 딱히 이 부분은 옵션별로 설정하게 없다. -->
							<c:forEach var="list" items="${alist}" varStatus="status">
								<c:if test="${status.count eq 1}">
									<li data-target="#carousel-example-generic" data-slide-to="0"
										class="active"></li>
								</c:if>

								<c:if test="${status.count ne 1}">
									<li data-target="#carousel-example-generic" data-slide-to="1"></li>
								</c:if>
							</c:forEach>


						</ol>
						<!-- 실제 이미지 아이템 -->
						<!-- class는 carousel-inner로 설정하고 role은 listbox에서 설정한다. -->
						<div class="carousel-inner" role="listbox">
							<!-- 이미지의 개수만큼 item을 만든다. 중요한 포인트는 carousel-indicators의 li 태그 개수와 item의 개수는 일치해야 한다. -->

							<c:forEach var="list" items="${alist}" varStatus="status">
								<c:if test="${status.count eq 1}">
									<div class="item active">
										<img src="${list.REVIEWS_IMAGE }" style="width: 100%">
									</div>
								</c:if>

								<c:if test="${status.count ne 1}">
									<div class="item">
										<img src="${list.REVIEWS_IMAGE }" style="width: 100%">
									</div>
								</c:if>
							</c:forEach>

							<!-- <div class="item active">
					아미지 설정-
					<img
						src="https://tistory2.daumcdn.net/tistory/1041549/skin/images/nowonbuntistory.png"
						style="width: 100%">
					캡션 설정 (생략 가능)
				

				</div>
				<div class="item">
					<img src="https://www.nowonbun.com/img/nowonbuntistory1.png"
						style="width: 100%">
				</div> -->



						</div>
						<!-- 왼쪽 화살표 버튼 -->
						<!-- href는 carousel의 id를 가르킨다. -->
						<a class="left carousel-control" href="#carousel-example-generic"
							role="button" data-slide="prev"> <!-- 왼쪽 화살표 --> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						</a>
						<!-- 오른쪽 화살표 버튼 -->
						<!-- href는 carousel의 id를 가르킨다. -->
						<a class="right carousel-control" href="#carousel-example-generic"
							role="button" data-slide="next"> <!-- 오른쪽 화살표 --> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						</a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${map.REVIEW_CONTENTS }</td>
		</tr>
	</table>




	<jsp:include page="/footer.jsp" />


</body>
</html>