<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
<script>
	$(function() {
		
		var address_selected1 = '${address_selected1}'
		var address_selected2 = '${address_selected2}'
		var address_selected3 = '${address_selected3}'
	
		var category_selected1 = '${category_selected1}'
		var category_selected2 = '${category_selected2}'
		var category_selected3 = '${category_selected3}'
		
		var sell_bargain = '${sellerVO.sell_bargain}'
	
		$("#address").val(address_selected1).prop("selected", true);
		$("#address2").val(address_selected2).prop("selected", true);
		$("#address3").val(address_selected3).prop("selected", true);
		
		$("#category").val(category_selected1).prop("selected", true);
		$("#category2").val(category_selected2).prop("selected", true);
		$("#category3").val(category_selected3).prop("selected", true);
	
		$("input:radio[name='sell_bargain']:radio[value="+sell_bargain+"]").prop('checked', true); 
		
		var result = document.createElement("input");
		result.type = "hidden";
		result.id = "sell_place_item_id";
		result.name = "sell_place_item_no";
		result.value = "${sellerVO.sell_place_item_no}";
		$('#sellPlaceItemId').append(result);
		
	});
	
	function fileChange1() {
		console.log('클릭')
		jQuery("#file_upload1").click();
	}
	function fileChange2() {
		console.log('클릭')
		jQuery("#file_upload2").click();
	}
	function fileChange3() {
		console.log('클릭')
		jQuery("#file_upload3").click();
	}
	
	
	function check() {
		console.log('submit check');
		var contents = $('#content').val();
		var check1_contents = contents.replaceAll("<p>", "");
		var check2_contents = check1_contents.replaceAll("</p>", "");
		var check3_contents = check2_contents.replaceAll("<br>", "");
		
		var check_address = $('#address3').val();
		var check_category = $('#category3').val();
		var pw_length = $('#sell_pw').val().length;
		
		//비밀번호 영문 조합 확인
		var pw_check_num = /[0-9]/; //숫자 
	   	var pw_check_eng = /[a-zA-Z]/; //영문 
		var pw_check_spc = /[~!@#$%^&*()_+|<>?:{}]/; //특수문자
		var pw = $('#sell_pw').val();
		
		if(check_address == "역을 선택하세요.") {
			alert('지역을 선택해 주세요.')
			return false;
		}else if(check_category == "카테고리를 선택하세요.") {
			alert('카테고리를 선택해 주세요.')
			return false;
		}else if(check3_contents == "") {
			alert('내용을 입력해 주세요.')
			return false;
		}else if(sell_place_item_id == undefined) {
			alert('보관함을 선택해 주세요.')
			return false;
		}else {
			if(pw_length >= 6 && pw_check_num.test(pw) && pw_check_eng.test(pw) && !(pw_check_spc.test(pw))) {
				if(confirm('수정하시겠습니까?') == true) {
					return true;
				} else {
					return false;
				}
			} else if(pw_check_spc.test(pw)) {
				alert('비밀번호에 특수문자는 사용할 수 없습니다.');
				return false;
			} else {
				alert('비밀번호를 영문/숫자 조합 6자리 이상 입력하세요.')
				return false;
			}
		}
	}
	
	function cancel_click() {
		if(confirm('수정을 취소하시겠습니까?') == true) {
			location.href="/main.jsp";
		} else {
			return;
		}
	}
</script>

</head>

<c:if test="${sessionScope.loginId eq null}">
	<script>
		alert("회원만 이용 가능 합니다.");
		location.href="/loginPage.do";
	</script>
</c:if>

<body>
	<jsp:include page="/header.jsp" />
	
	<form name="sellForm" id="sellForm" onsubmit="return check()" action="/seller/sellUpdate.do"  method="post" enctype="multipart/form-data">

		<div class="seller_page">
			<div class="seller_head">
					<h1>
						▶ 글 수정 페이지 <small>* &nbsp;모든 항목을 입력해 주세요.</small>
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
						<img id="file_img1" src="${sellerList[0].sell_image}" style="width:280px;height:200px;">
						</td>
						<td>
						<img id="file_img2" src="${sellerList[1].sell_image}" style="width:280px;height:200px;">
						</td>
						<td>
						<img id="file_img3" src="${sellerList[2].sell_image}" style="width:280px;height:200px;">
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" onclick="fileChange1();" value="파일 업로드">&nbsp;&nbsp;
							<input type="file" id="file_upload1" name="file_upload1" accept="jpg,.jpeg,.png,.gif,.bmp" 
								style="display:none;" onchange="file_upload1_click(this);">
						</td>
						<td align="center">
							<input type="button" onclick="fileChange2();" value="파일 업로드">&nbsp;&nbsp;
							<input type="file" id="file_upload2" name="file_upload2" accept="jpg,.jpeg,.png,.gif,.bmp" 
								style="display:none;" onchange="file_upload2_click(this);">
						</td>
						<td align="center">
							<input type="button" onclick="fileChange3();" value="파일 업로드">&nbsp;&nbsp;
							<input type="file" id="file_upload3" name="file_upload3" accept="jpg,.jpeg,.png,.gif,.bmp" 
								style="display:none;" onchange="file_upload3_click(this);">
						</td>
					</tr>
				</table>
				<br><hr><br>
			
				<p class="profile-left-icon">
					<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
					작성자 : ${sessionScope.loginId}
				</p>
				<br><hr><br>
				
				<div class="sell_bargain">
					<h3>가격 &nbsp;&nbsp;<input type="number" name="sell_price" id="sell_price" value="${sellerVO.sell_price}" required placeholder="금액을 입력하세요."></h3>
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
								<c:forEach var="address" items="${sggList}">
									<option value="${address.sgg_code}">${address.sig_kor_nm}</option>
								</c:forEach>
							</select>&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<select class="selectbox" id="address3" name="sell_place_no" onchange="addSelect3(this)">
									<option>역을 선택하세요.</option>
								<c:forEach var="address" items="${stationList}">
									<option value="${address.sell_place_no}">${address.sell_place_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<br><br><br>
				
				<div>
					<h3>보관함 선택</h3>
					<p class="profile-left-icon2">
						<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />
						  보관함을 선택하세요.
					</p>
					<table class="img_table" id="sellPlaceItemId">
						<tr>
							<td>
								<c:forEach var="i" begin="0" end="${fn:length(sellPlaceItemList)}">
									<c:choose>
										<c:when test="${sellPlaceItemList[i].SELL_PLACE_ITEM_NO eq sellerVO.sell_place_item_no}">
											<button type="button" class="item_class" id="item_${sellPlaceItemList[i].SELL_PLACE_ITEM_NO}"
											onclick="sellPlaceItemClick(${sellPlaceItemList[i].SELL_PLACE_ITEM_NO});"
											style="background-color: #343a40; color: #FFFFFF;">
												${sellPlaceItemList[i].SELL_PLACE_ITEM_NO}
											</button>	
										</c:when>
										<c:when test="${sellPlaceItemList[i].YN eq 'Y'}">
											<button type="button" class="item_class"  id="item_${sellPlaceItemList[i].SELL_PLACE_ITEM_NO}"
											onclick="sellPlaceItemClick(${sellPlaceItemList[i].SELL_PLACE_ITEM_NO});">
												${sellPlaceItemList[i].SELL_PLACE_ITEM_NO}
											</button>			
										</c:when>
										<c:when test="${sellPlaceItemList[i].YN eq 'N'}">
											<button type="button" class="item_class_d"  id="item_${sellPlaceItemList[i].SELL_PLACE_ITEM_NO}"
											onclick="sellPlaceItemClick('${sellPlaceItemList[i].SELL_PLACE_ITEM_NO});"
											disabled="disabled">
												${sellPlaceItemList[i].SELL_PLACE_ITEM_NO}
											</button>
										</c:when>
									</c:choose>
								</c:forEach>
							</td>
						</tr>
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
							</select>&nbsp;&nbsp;&nbsp;
						</td>
						<td align="center">
							<select class="selectbox" id="category2" name="category2" onchange="categoryChange2(this)">
								<option>카테고리를 선택하세요.</option>
								<c:forEach var="category" items="${categoryList_M}">
									<option value="${category.sell_category_no}">${category.sell_category_name}</option>
								</c:forEach>	
							</select>&nbsp;&nbsp;&nbsp;
						</td>
						<td align="center">
							<select class="selectbox" id="category3" name="sell_category_no" onchange="categorySelect3(this)">
								<option>카테고리를 선택하세요.</option>
								<c:forEach var="category" items="${categoryList_S}">
									<option value="${category.sell_category_no}">${category.sell_category_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				
				<br><br><hr><br>
				<br><br>
			
				<h3><input type="text" name="sell_title" id="sell_title" value="${sellerVO.sell_title}" required placeholder="제목을 입력하세요." size="70%"></h3>
				<br>
				
				<div class="seller_content">
					<textarea name="sell_contents" id="content">${sellerVO.sell_contents}</textarea>
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
					<input type="hidden" name="sell_no" value="${ sellerVO.sell_no }">
					<input type="hidden" name="transient_no" value="${ transient_count }">
					<!-- <input type="hidden" name="img1" value=""> -->
					<!-- <input type="submit" class="sellInsertSubmit" value="확인" class="w3-button w3-white w3-round-small"> &nbsp; -->
				</div>
			
				<div class="sell_button">
					<button type="submit" class="sell_click_btn">수정</button>&nbsp;&nbsp;
					<button type="button" class="sell_click_btn2" onclick="cancel_click();">취소</button>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:include page="/footer.jsp" />
</body>
</html>