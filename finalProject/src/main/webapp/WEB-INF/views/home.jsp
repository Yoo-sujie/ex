<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false"%> --%>
<html>
<head>
<title>geo</title>
</head>

<jsp:include page="/script.jsp" />
<script type="text/javascript" src="/resources/lib/js/map.js"></script>

<script type="text/javascript" src="/resources/lib/js/geo.js"></script>
<script type="text/javascript" src="/resources/lib/js/gis.js"></script>
<script type="text/javascript" src="/resources/lib/js/htmlUtil.js"></script>
<script type="text/javascript" src="/resources/lib/js/openlayers/ol.js"></script>



<body>

	<div id="map" class="map"
		style="width: 100%; height: 50%; border-radius: 20px;"></div>

	<table class="table table-bordered table-hover text-center"
		id="mapTable">
		<thead>
			<tr>
				<th scope="col" class='text-center' width='20%'>지역</th>
				<th scope="col" class='text-center' width='12%'>A</th>
				<th scope="col" class='text-center' width='12%'>B</th>
				<th scope="col" class='text-center' width='12%'>C</th>
				<th scope="col" class='text-center' width='12%'>D</th>
				<th scope="col" class='text-center' width='12%'>E</th>
				<th scope="col" class='text-center' width='20%'>등급</th>

			</tr>
		</thead>
		<tbody id="mt" name="mt">

		</tbody>
	</table>

	<!-- <input type="button" id=mapChange value="뱁 변경"> -->
	<select name="mapList" id="mapList">
		<option value="ES_DAY_SIDO">시도</option>
		<option value="ES_DAY_SGG">시군구</option>
		<option value="MARKER">지점</option>
	</select>




</body>
</html>

