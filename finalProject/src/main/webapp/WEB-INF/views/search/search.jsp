<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/lib/css/main.css" type="text/css">

<title>판매 검색</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<script type="text/javascript" src="/resources/lib/js/search.js"></script>
	<link
		href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
		rel="stylesheet">
	<script
		src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<input type="hidden" id="totalCnt" value=${totalCnt }>
	<table>
		<tr>
			<td><select id="sellPlace">
					<option value="all">전체</option>
					<c:forEach var="list" items="${sellPlace}" varStatus="status">
						<option value="${list.SELL_PLACE_NO}">${list.SELL_PLACE_NAME}</option>
					</c:forEach>
			</select></td>
			<td><select id="categoryList1">
					<option value="all">대분류</option>
					<c:forEach var="list" items="${sellCategory}" varStatus="status">
						<option value="${list.SELL_CATEGORY_NO}">${list.SELL_CATEGORY_NAME}</option>
					</c:forEach>
			</select></td>
			<td><select id="categoryList2">
					<option value="all">중분류</option>
			</select></td>
			<td><select id="categoryList3">
					<option value="all">소분류</option>
			</select></td>
			<td><select id="searchItem">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">제목+내용</option>
			</select></td>
			<td><input type="text" id="searchTxt"></td>
			<td><input type="button" id="search" value="검색"></td>
			<td><label>가격 : </label><input type="number" id="minPrice"
				min="0" step="1000" value="0"> <label>원 ~ </label><input
				type="number" id="maxPrice" min="0" step="1000" value="0"><label>원</label></td>
			<td><select id="sellStatus">
					<option value="all">전체</option>
					<option value="0">판매중</option>
					<option value="1">구매중</option>
					<option value="2">판매완료</option>
			</select></td>
			<td><input type="date" id="startDate"><input type="date"
				id="endDate"></td>
			<td><input type="button" id="seller" value="글작성""></td>
			<!-- <td><input type="button" id="tableToggle" value="토글""></td> -->
			<td><input type="checkbox" id="tableToggle" checked
				data-toggle="toggle" data-on="LIST" data-off="CARD"
				data-onstyle="success" data-offstyle="info"></td>
		</tr>

	</table>
	<c:if test="${searchSellPlace eq null}">
		<input type="hidden" id="searchSellPlace" value="all">
	</c:if>
	<c:if test="${searchSellPlace ne null}">
		<input type="hidden" id="searchSellPlace" value="${searchSellPlace}">
	</c:if>
	<c:if test="${searchSellCategory eq null}">
		<input type="hidden" id="searchCategory" value="______">
	</c:if>
	<c:if test="${searchSellCategory ne null}">
		<input type="hidden" id="searchCategory" value="${searchSellCategory}">
	</c:if>
	<input type="hidden" id="searchSearchItem">
	<input type="hidden" id="searchSearchTxt">
	<input type="hidden" id="searchMinPrice">
	<input type="hidden" id="searchMaxPrice">
	<input type="hidden" id="searchSellStatus" value="all">
	<input type="hidden" id="searchStartDate">
	<input type="hidden" id="searchEndDate">






	<div id="detailTable" style="display: block;">
		<table class="table table-bordered table-hover text-center"
			id="sellSearchTable">
			<thead id="ssth" name="ssth2">
				<tr>
					<th scope="col" class='text-center' width='5%'>#</th>
					<th scope="col" class='text-center' width='10%'>판매장소</th>
					<th scope="col" class='text-center' width='12%'>카테고리</th>
					<th scope="col" class='text-center' width='8%'>상품</th>
					<th scope="col" class='text-center' width='25%'>제목</th>
					<th scope="col" class='text-center' width='8%'>가격</th>
					<th scope="col" class='text-center' width='8%'>판매자</th>
					<th scope="col" class='text-center' width='10%'>판매상태</th>
					<th scope="col" class='text-center' width='8%'>조회수</th>
					<th scope="col" class='text-center' width='15%'>날짜</th>

				</tr>
			</thead>

			<tbody id="sstd" name="sstd">
				<c:if test="${sellList == null}">
					<tr><td colspan="10">데이터가 없습니다.</td></tr>
				</c:if>
				<c:forEach var="list" items="${sellList}" varStatus="status">
					<tr>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_NO}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_PLACE_NAME}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_CATEGORY_NAME}</td>
						<th scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle"><c:if
								test="${list.SELL_IMAGE ne null}">
								<img src="${list.SELL_IMAGE}" class="img-thumbnail"
									alt="Cinque Terre" style=" width : 85; height: 60 !important;">
							</c:if> <c:if test="${list.SELL_IMAGE eq null}">
								<img src="/resources/lib/images/main-test(1).jpg"
									class="img-thumbnail" alt="Cinque Terre" style=" width : 85; height: 60 !important;" >
							</c:if></th>
						<td scope="col" class='text-center'style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_TITLE}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_PRICE}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.MEMBER_NIC}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_STATUS}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_CNT}</td>
						<td scope="col" class='text-center' style=" height: 80px !important;" 
							style="vertical-align: middle">${list.SELL_WRITE_DATE}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div id="cardTable" class="search_div" style="display: none;">
		<!-- 최근 올라온 제품 : 가장 최근에 올라온 제품 이미지랑 제목, 순서대로 뽑아서 카드모양으로! -->
		<c:forEach var="list" items="${sellList}" varStatus="status">
			<div id="sellSearchTable2" class="search_div2">
				<a href="/buyer/buyerContentView.do?sell_no=${list.SELL_NO}"> <!-- 클릭 시 링크 설정 -->
					<div class="card">
						<!-- 카드 헤더 -->
						<div class="card-header">
							<c:if test="${list.CARD_HEADER eq 'NEW'}">
								<div class="card-header-is_closed">
									<div class="card-header-text">NEW</div>
								</div>
							</c:if>
							<c:if test="${list.CARD_HEADER eq 'HOT'}">
								<div class="card-header-is_closed">
									<div class="card-header-text">HOT</div>
								</div>
							</c:if>
							<c:if test="${list.SELL_IMAGE ne null}">
								<img src="${list.SELL_IMAGE}" class="img-thumbnail"
									alt="Cinque Terre" width="322" height="250">
							</c:if>
							<c:if test="${list.SELL_IMAGE eq null}">
								<img src="/resources/lib/images/main-test(1).jpg"
									class="img-thumbnail" alt="Cinque Terre" width="322"
									height="250">
							</c:if>
						</div>

						<!--  카드 바디 -->
						<div class="card-body">

							<!--  카드 바디 헤더 -->
							<div class="card-body-header">
								<h1>${list.SELL_TITLE_STR}</h1>
								<p class="card-body-hashtag">#${list.SELL_PLACE_NAME}
									#${list.SELL_CATEGORY_NAME} #${list.SELL_PRICE}</p>
							</div>

							<!-- 카드 바디 본문 -->
							<!-- <p class="card-body-description">
											작성자: 닉네임
										</p> -->

							<!--  카드 바디 푸터 -->
							<div class="card-body-footer">
								<hr
									style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">
								<i class="icon icon-view_count"></i><label>조회
									${list.SELL_CNT}회</label>
								<!-- <i class="icon icon-comments_count"></i>댓글 --개 -->
								<i class="reg_date"> ${list.SELL_WRITE_DATE} </i>
							</div>
						</div>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>

	<br>
	<style>
.cent {
	display: table;
	margin-left: auto;
	margin-right: auto;
}

.page {
	border-bottom: 0px;
	border-top: 0px;
}
</style>
	<div class="cent">
		<nav aria-label="Page navigation" class="page">
			<ul class="pagination" id="PageWrap">


			</ul>
		</nav>
	</div>


	<jsp:include page="/footer.jsp" />
</body>
</html>
