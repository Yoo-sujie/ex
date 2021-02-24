<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false"%> --%>
<html>
<head>
<title>geo</title>
</head>






<body>

	<jsp:include page="/header.jsp" />

	<script type="text/javascript" src="/resources/lib/js/map.js"></script>

	<script type="text/javascript" src="/resources/lib/js/geo.js"></script>
	<script type="text/javascript" src="/resources/lib/js/gis.js"></script>
	<script type="text/javascript" src="/resources/lib/js/htmlUtil.js"></script>
	<script type="text/javascript" src="/resources/lib/js/openlayers/ol.js"></script>
	



	
	<!-- 대분류 -->
	<select class="form-control" name="categoryList1" id="categoryList1">
		<option value="all">대분류</option>
	</select>
	
	<!-- 중분류 -->
	<select class="form-control" name="categoryList2" id="categoryList2">
		<option value="all">중분류</option>
	</select>
	
	
	<!-- 소분류 -->
	<select class="form-control" name="categoryList3" id="categoryList3">
		<option value="all">소분류</option>
	</select>
	
	
	<select class="form-control" name="mapList" id="mapList">
		<option value="SELL_SD">시도</option>
		<option value="SELL_SGG">시군구</option>
		<option value="MARKER">지점</option>
	</select>
	<br>
	<div id="map" class="map"
		style="width: 100%; height: 50%; border-radius: 20px;"></div>

	<table class="table table-bordered table-hover text-center"
		id="mapTable">
		<thead id="mth" name="mth">
			<tr>
				<th scope="col" class='text-center' width='20%'>지역</th>
				<th scope="col" class='text-center' width='12%'>판매장소갯수</th>
				<th scope="col" class='text-center' width='12%'>판매물품 갯수</th>
			</tr>
		</thead>
		<tbody id="mtd" name="mtd">

		</tbody>
	</table>




	<br>
	<br>
	<br>


	<table class="table table-bordered table-hover text-center"
		id="mapTable2">
		<thead id="mth2" name="mth2">
			<!-- <tr>
				<th scope="col" class='text-center' width='20%'>지역</th>
				<th scope="col" class='text-center' width='12%'>판매장소갯수</th>
				<th scope="col" class='text-center' width='12%'>판매물품 갯수</th>
			</tr> -->
		</thead>
		<tbody id="mtd2" name="mtd2">

		</tbody>
	</table>

	<style>
		.cent {
			display: table;
			margin-left: auto;
			margin-right: auto;
			  }
		.page {
			border-bottom : 0px;
			border-top : 0px;
			  }
</style>
	<div class="cent">
		<nav aria-label="Page navigation" class = "page">
			<ul class="pagination" id="PageWrap">


			</ul>
		</nav>
	</div>


	<div class="container">
		<!-- The Modal -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">가게정보</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body" id="modalBody">Modal body..</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>


	</div>



	
	
	
	<jsp:include page="/footer.jsp" />

</body>
</html>
