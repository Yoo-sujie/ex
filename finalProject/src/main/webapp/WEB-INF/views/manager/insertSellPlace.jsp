<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">


<title>판매장소 등록</title>
</head>


<body>

	<jsp:include page="/header.jsp" />
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=035f32a51e456b3fd8051c848ccf989d&libraries=services"></script>
	<script type="text/javascript" src="/resources/lib/js/sellPlace.js"></script>
	<link rel="stylesheet" href="/resources/lib/css/buyer.css?after"
		type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<div class="profile3">
		<div class="profile-left">
			<div class="profile-left-nic">
				<!-- ******* 클릭 시 판매자 판매 기록 / 후기 / 별점 ******* -->
				<img src="/resources/lib/images/buyer-profile(1).png"
					alt="profile(1)" /> <br> <a href="#"> 판매장소 등록</a>
				<table class="profile3-table" border="0">

					<tr>
						<td><input type="text" id="sellPlaceName"
							class="form-control form-control-lg" placeholder="판매장소 명"></td>
					</tr>
					<tr >
						<td><div class="input-group mb-3" style="height: 20;">
								<input type="text" id="roadAddrPart1" class="form-control form-control-lg"
									readonly="readonly" placeholder="주소">
								<div class="input-group-append">
									<input type="hidden" id="confmKey" name="confmKey" value="">
									<button class="btn btn-primary" type="button"
										onclick="goPopup();">주소검색</button>
								</div>
							</div></td>
					</tr>
					
					<tr>

						<td><input type="text" id="geoLat" readonly="readonly" class="form-control form-control-lg"
							placeholder="위도"></td>
					</tr>
					<tr>

						<td><input type="text" id="geoLon" readonly="readonly" class="form-control form-control-lg"
							placeholder="경도"></td>
					</tr>
					<tr>
						<td><select class="form-control" name="sellPlaceItem" 
							id="sellPlaceItem">
								<option value="null">보관함 수</option>
								<c:forEach begin="1" end="20" step="1" varStatus="status">
									<option value="${status.index }">${status.index }</option>
								</c:forEach>
						</select></td>
					</tr>

				</table>
			</div>
			<div id="insertSellPlaceBtn" class="profile-left-div2">
				<label >등록</label>
			</div>

			<br>
			<div class="profile-left-icon">
				<p class="profile-left-icon">
					<img src="/resources/lib/images/buyer-icon(1).png" alt="icon(1)" />
					<b>보관함 설치 후 작성해주세요.</b>
				</p>
				<p class="profile-left-p2"></p>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>





	<jsp:include page="/footer.jsp" />
</body>
</html>