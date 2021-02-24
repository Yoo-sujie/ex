<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/lib/css/style5.css?after" 
type="text/css">

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/resources/lib/js/ajaxUtil.js"></script>
<script type="text/javascript" src="/resources/lib/js/seller.js"></script>
</head>

<c:if test="${sessionScope.loginId eq null}">
	<script>
		alert("회원만 이용 가능 합니다.");
		location.href="/loginPage.do";
	</script>
</c:if>

<body>
	<jsp:include page="/header.jsp" />
	
	<div id="popup">
   		<div class="popup_close">
   			<a href="javascript:" onClick="popupClose()">
   				<img src="/resources/lib/images/close.png" alt="close" />
   			</a>
   		</div>
		<p class="profile-left-icon">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
			지역을 먼저 선택해 주세요. 선택하신 지역의 보관함 목록을 보여줍니다.<br><br>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<small>* 맡길 보관함으로 선택해주세요.
			<br>&nbsp;&nbsp;&nbsp;&nbsp;
			* 리박스는 항상 보관함의 청결을 유지합니다. 감사합니다.^^</small>
		</p>
	</div>
	
	<form name="sellForm" id="sellForm" onsubmit="return check()" action="/seller/sellerInsert.do"  method="post" enctype="multipart/form-data">

		<div class="seller_page">
			<div class="seller_head">
				<h1>
					▶ 글 작성 페이지 <small>* &nbsp;모든 항목을 입력해 주세요.</small>
				</h1>
			</div>
		
			<div class="seller_head_S">
				<h3>메인 이미지 선택</h3>
				<p class="profile-left-icon">
					<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
					 메인 이미지 3개를 순서대로 넣어주세요.
				</p>
				
				<table class="img_table">
					<tr>
						<td align="center">
							<img id="file_img1" style="width:280px;height:200px;">
						</td>
						<td>
							<img id="file_img2" style="width:280px;height:200px;">
						</td>
						<td>
							<img id="file_img3" style="width:280px;height:200px;">
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="file" id="file_upload1" name="file_upload1"required accept="jpg,.jpeg,.png,.gif,.bmp" onchange="file_upload1_click(this);">
						</td>
						<td align="center">
							<input type="file" id="file_upload2" name="file_upload2" required accept="jpg,.jpeg,.png,.gif,.bmp" onchange="file_upload2_click(this);">
						</td>
						<td align="center">
							<input type="file" id="file_upload3" name="file_upload3" required accept="jpg,.jpeg,.png,.gif,.bmp" onchange="file_upload3_click(this);">
						</td>
					</tr>
				</table>
				<br><hr><br>
			
				<p class="profile-left-icon">
					<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
					작성자 : ${member_nic}
				</p>
				<br><hr><br>
				
				<div class="sell_bargain">
					<h3>가격 &nbsp;&nbsp;<input type="number" name="sell_price" id="sell_price" required placeholder="금액을 입력하세요."></h3>
					<input type="radio" id="r1" name="sell_bargain" required value="Y" />
    				<label for="r1"><span></span></label>흥정 가능 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="r2" name="sell_bargain" required value="N" />
    				<label for="r2"><span></span></label>흥정 불가능
				</div><br><hr><br>
			
			
				<h3>지역 선택</h3>
				<table class="img_table">
					<tr>
						<td align="center">
							<select class="selectbox" id="address" name="address" onchange="addressChange(this)">
									<option>지역을 선택하세요.</option>
								<c:forEach var="address" items="${sidoList}">
									<option value="${address.sido_code}">${address.ctp_kor_nm}</option>
								</c:forEach>
							</select>&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<select class="selectbox" id="address2" name="address2" onchange="addressChange2(this)">
								<option>동네를 선택하세요.</option>
							</select>&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<select class="selectbox" id="address3" name="address3" onchange="addSelect3(this)">
								<option>역을 선택하세요.</option>
							</select>
						</td>
					</tr>
				</table>
				<br><br><br>
			
				<div>
					<h3>보관함 선택</h3>
					<p class="profile-left-icon2">
						<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
						  지역을 선택하세요.
					</p>
					<table class="img_table" id="sellPlaceItemId">
					</table>
				</div>
				
				<br><br><hr><br>
			
				<h3>카테고리 선택</h3>
				<table class="img_table" id="sellPlaceItemId">
					<tr>
						<td align="center">
							<select class="selectbox" id="category" name="category" onchange="categoryChange(this)">
								<option>카테고리를 선택하세요.</option>
								<c:forEach var="category" items="${categoryList_L}">
									<option value="${category.sell_category_no}">${category.sell_category_name}</option>
								</c:forEach>
							</select>
						</td>
						<td align="center">
							<select class="selectbox" id="category2" name="category2" onchange="categoryChange2(this)">
								<option>카테고리를 선택하세요.</option>
							</select>
						</td>
						<td align="center">
							<select class="selectbox" id="category3" name="category3" onchange="categorySelect3(this)">
								<option>카테고리를 선택하세요.</option>
							</select>
						</td>
					</tr>
				</table>
				
				<br><br><hr><br>
				<br><br>
			
				<h2><input type="text" name="sell_title" id="sell_title" required placeholder="제목을 입력하세요." size="70%"></h2>
				<br>
				
				<div class="seller_content">
					<textarea name="sell_contents" id="content"></textarea>
				</div>
			
				<div class="sell_pw">
					<h3><input type="password" name="sell_pw" id="sell_pw" required placeholder="비밀번호를 입력하세요"></h3>
					<p class="profile-left-icon">
						<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
						구매자가 해당 상품을 수령할 때, 반드시 필요한 비밀번호입니다.<br>입금 확인 후 구매자에게 공유해주세요. 
					</p>
				</div><br>
				<div align="center">
					<input type="hidden" name="section" value="${ param.section }">
					<input type="hidden" name="id" value="${ id }">
					<input type="hidden" name="transient_no" value="${ transient_count }">
					<!-- <input type="hidden" name="img1" value=""> -->
					<!-- <input type="submit" class="sellInsertSubmit" value="확인" class="w3-button w3-white w3-round-small"> &nbsp; -->
				</div>
				
				<div class="sell_button">
					<button type="submit" class="sell_click_btn">등록</button>&nbsp;&nbsp;
					<button type="button" class="sell_click_btn2" onclick="cancel_click();">취소</button>
				</div>
			</div>
		</div>
	</form>
	
	
	<jsp:include page="/footer.jsp" />
</body>
</html>