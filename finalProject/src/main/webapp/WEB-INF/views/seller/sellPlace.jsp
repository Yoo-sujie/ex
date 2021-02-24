<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/resources/lib/js/ajaxUtil.js"></script>
<script>
	function addressChange(e) {
		console.log(e.value);
		if(e.value=='지역을 선택하세요.') {
			selectNo();
		} else {
			 $.ajax({
		            url: "sellPlaceSelectGu.do",
		            type: "POST",
		            data: { address : $("#address").val() },
		            dataType : "json",
		            success: function(data){
		                //$('#address').text(data);
		                addSelect(data);
		            },
		            error: function(){
		                alert("error");
		            }
		       });
		}
		
	}
	
	function selectNo() {
		$('#address2').empty();
		$('#address3').empty();
        var option2 = $("<option>동네를 선택하세요.</option>");
        var option3 = $("<option>역을 선택하세요.</option>");
        $('#address2').append(option2);
        $('#address3').append(option3);
	}
	
	function selectNo2() {
		$('#address3').empty();
        var option3 = $("<option>역을 선택하세요.</option>");
        $('#address3').append(option3);
	}

	function addSelect(data){
		console.log($(data).attr('sggList'));
		var dataList = $(data).attr('sggList');
		$('#address2').empty();
        var option2 = $("<option>동네를 선택하세요.</option>");
        $('#address2').append(option2);
		for(var count = 0; count < dataList.length; count++){
            var option = $("<option value="+dataList[count].sgg_code+">"+dataList[count].sig_kor_nm+"</option>");
            $('#address2').append(option);
        }
	}
	
	function addressChange2(e) {
		console.log(e.value);
		if(e.value=='동네를 선택하세요.') {
			selectNo2();
		} else {
			 $.ajax({
		            url: "sellPlaceSelectStation.do",
		            type: "POST",
		            data: { address : $("#address2").val() },
		            dataType : "json",
		            success: function(data){
		                addSelect2(data);
		            },
		            error: function(){
		                alert("error");
		            }
		       });
		}
		
	}
		
	function addSelect2(data){
		console.log($(data).attr('stationList'));
		var dataList = $(data).attr('stationList');
		$('#address3').empty();
        var option2 = $("<option>역을 선택하세요.</option>");
        $('#address3').append(option2);
		for(var count = 0; count < dataList.length; count++){
            var option = $("<option value="+dataList[count].sell_place_no+">"+dataList[count].sell_place_name+"</option>");
            $('#address3').append(option);
        }
	}
	
	function addSelect3(data) {
		console.log(data.value);
		var station = document.createElement("input");
		station.type = "hidden";
		station.name = "sell_place_no";
		station.value = data.value;
		$('#sellForm').append(station);
	}
	
	function cancelClick() {
		console.log('ddd23');
	}
</script>

<script>
	function categoryChange(e){
		console.log(e.value);
		if(e.value=='카테고리를 선택하세요.') {
			categorySelectNo();
		} else {
			$.ajax({
	            url: "sellCategorySelectMedium.do",
	            type: "POST",
	            data: { category : $('#category').val() },
	            dataType : "json",
	            success: function(data){
	            	addCategorySelect(data);
	            },
	            error: function(){
	                alert("error");
	            }
	       });
		}
	} 
	
	function categoryChange2(e){
		console.log(e.value);
		if(e.value=='카테고리를 선택하세요.') {
			categorySelectNo2();
		} else {
			$.ajax({
	            url: "sellCategorySelectSmall.do",
	            type: "POST",
	            data: { category : $('#category2').val() },
	            dataType : "json",
	            success: function(data){
	            	addCategorySelect2(data);
	            },
	            error: function(){
	                alert("error");
	            }
	       });
		}
	} 
	
	function addCategorySelect(data) {
		console.log($(data).attr('categoryList_M'));
		$('#category2').empty();
		$('#category3').empty();
		var option = $('<option>카테고리를 선택하세요.</option>');
		var option2 = $('<option>카테고리를 선택하세요.</option>');
		$('#category2').append(option);
		$('#category3').append(option2);
		
		var dataList = $(data).attr('categoryList_M');
		for(var count = 0; count < dataList.length; count++) {
			var option = '<option value='+ dataList[count].sell_category_no  +'>'
			+ dataList[count].sell_category_name + '</option>';
			$('#category2').append(option);
		}
	}

	function addCategorySelect2(data) {
		console.log($(data).attr('categoryList_S'));
		$('#category3').empty();
		var option = $('<option>카테고리를 선택하세요.</option>');
		$('#category3').append(option);
		
		var dataList = $(data).attr('categoryList_S');
		for(var count = 0; count < dataList.length; count++) {
			var option = '<option value='+ dataList[count].sell_category_no  +'>'
			+ dataList[count].sell_category_name + '</option>';
			$('#category3').append(option);
		}
	}
	
	function categorySelect3(data) {
		console.log(data.value);
		var category = document.createElement("input");
		category.type = "hidden";
		category.name = "sell_category_no";
		category.value = data.value;
		$('#sellForm').append(category);
	}
	
	function categorySelectNo() {
		$('#category2').empty();
		$('#category3').empty();
		var option = $('<option>카테고리를 선택하세요.</option>');
		var option2 = $('<option>카테고리를 선택하세요.</option>');
		$('#category2').append(option);
		$('#category3').append(option2);
	}

	function categorySelectNo2() {
		$('#category3').empty();
		var option = $('<option>카테고리를 선택하세요.</option>');
		$('#category3').append(option);
	}
	
	
</script>


<script>
	<%-- form의 textarea에 summernote 적용 --%>
	$(document).ready(function() {
		$('#content').summernote({
			height: 700,
			fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
			fontNamesIgnoreCheck : [ '맑은고딕' ],
			focus: true
		});
	});
</script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div>
		
	
	<form id="sellForm" action="sellInsert.do">

	<!-- <table>
	<tr>
		<td rowspan="2" width="100" height="100"><img alt="" src=""></td>
		<td><button>이미지업로드</button></td>
	</tr>
	<tr><td><input type="text" name="sell_price" placeholder="금액을 입력하세요."></td></tr>
	</table> -->
	
	<div class="sell_bargain">
		<input type="text" name="sell_price" placeholder="금액을 입력하세요."><br>
		<input type="radio" name="sell_bargain" value="Y">흥정 가능&nbsp;&nbsp;&nbsp;
		<input type="radio" name="sell_bargain" value="N">흥정 불가능
	</div><br>
	
	
	
	<input type="text" name="sell_title" placeholder="제목을 입력하세요." size="75"><br>

	작성자 : 테스트 <br><br>

	<select id="address" name="address" onchange="addressChange(this)">
			<option>지역을 선택하세요.</option>
		<c:forEach var="address" items="${sidoList}">
			<option value="${address.sido_code}">${address.ctp_kor_nm}</option>
		</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;
	<select id="address2" name="address2" onchange="addressChange2(this)">
			<option>동네를 선택하세요.</option>
	</select>&nbsp;&nbsp;&nbsp;
	<select id="address3" name="address3" onchange="addSelect3(this)">
			<option>역을 선택하세요.</option>
	</select>
	<br><br>
	
	<select id="category" name="category" onchange="categoryChange(this)">
		<option>카테고리를 선택하세요.</option>
		<c:forEach var="category" items="${categoryList_L}">
			<option value="${category.sell_category_no}">${category.sell_category_name}</option>
		</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;
	<select id="category2" name="category2" onchange="categoryChange2(this)">
			<option>카테고리를 선택하세요.</option>
	</select>&nbsp;&nbsp;&nbsp;
	<select id="category3" name="category3" onchange="categorySelect3(this)">
			<option>카테고리를 선택하세요.</option>
	</select>
	<br><br>
	
	<br><br>
	
	<div>
		<textarea name="board_content" id="content"></textarea>
	</div>
	
	<input type="text" name="sell_pw" placeholder="비밀번호를 입력하세요"><br>
	
	<div align="center">
		<input type="hidden" name="section" value="${ param.section }">
		<input type="hidden" name="id" value="${ id }">
		<!-- <input type="submit" class="sellInsertSubmit" value="확인" class="w3-button w3-white w3-round-small"> &nbsp; -->
		<button class="sellInsertSubmit">확인</button>&nbsp;&nbsp;
		<button type="button" onclick="cancelClick();">취소</button>
	</div>
	</form>
	</div>
	<jsp:include page="/footer.jsp" />
</body>
</html>