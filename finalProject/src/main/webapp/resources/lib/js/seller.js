
function popupClose() {
	document.all['popup'].style.visibility = "hidden";
}


//3분마다 글 임시저장 하기
/*transientStorage = setInterval(function() {
	
	var sell_form = $("form[name=sellForm]").serialize();

	$.ajax({
        url: "/seller/sellTransientStorage.do",
        type: "POST",
        data: sell_form,
        dataType : "json",
        success: function(data){
            addSelect(data);
        },
        error: function(){
           
        }
   });
}, 10000);*/

function addressChange(e) {
	console.log(e.value);
	if(e.value=='지역을 선택하세요.') {
		selectNo();
	} else {
		 $.ajax({
	            url: "/seller/sellPlaceSelectGu.do",
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
    
    $('#sellPlaceItemId').html(''); //비워줌
	$('.profile-left-icon2').html(''); //비워줌    
	
    var sellPlaceItemId = $('#sellPlaceItemId');
	var profileLeftIcon = $('.profile-left-icon2');
	
	sellPlaceItemId.append('<p class="profile-left-icon2">');
	profileLeftIcon.append('<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />'
			+ ' 지역을 선택하세요.</p>');
}

function selectNo2() {
	$('#address3').empty();
    var option3 = $("<option>역을 선택하세요.</option>");
    $('#address3').append(option3);
}

function addSelect(data){
	console.log($(data).attr('sggList'));
	var dataList = $(data).attr('sggList');
	//$('#address2').empty();
    //var option2 = $("<option>동네를 선택하세요.</option>");
    //$('#address2').append(option2);
	for(var count = 0; count < dataList.length; count++){
        var option = $("<option value="+dataList[count].sgg_code+">"+dataList[count].sig_kor_nm+"</option>");
        $('#address2').append(option);
    }
	
	$('#sellPlaceItemId').html(''); //비워줌
	$('.profile-left-icon2').html(''); //비워줌
	
	var sellPlaceItemId = $('#sellPlaceItemId');
	var profileLeftIcon = $('.profile-left-icon2');
	
	sellPlaceItemId.append('<p class="profile-left-icon2">');
	profileLeftIcon.append('<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />'
			+ ' 지역을 선택하세요.</p>');
}

function addressChange2(e) {
	console.log(e.value);
	if(e.value=='동네를 선택하세요.') {
		selectNo2();
	} else {
		 $.ajax({
	            url: "/seller/sellPlaceSelectStation.do",
	            type: "POST",
	            data: { address_two : $("#address2").val() },
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
	
	$('#sellPlaceItemId').html(''); //비워줌
	$('.profile-left-icon2').html(''); //비워줌
	
	var sellPlaceItemId = $('#sellPlaceItemId');
	var profileLeftIcon = $('.profile-left-icon2');
	
	sellPlaceItemId.append('<p class="profile-left-icon2">');
	profileLeftIcon.append('<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />'
			+ ' 지역을 선택하세요.</p>');
}

function addSelect3(data) {
	console.log(data.value);

	var station = document.createElement("input");
	$('#sell_place_no').remove();
	station.type = "hidden";
	station.id = "sell_place_no";
	station.name = "sell_place_no";
	station.value = data.value;
	$('#sellForm').append(station);
	
	$.ajax({
        url: "/seller/sellPlaceItem.do",
        type: "POST",
        data: { item : data.value },
        dataType : "json",
        success: function(data){
            addItem(data);
        },
        error: function(){
        	$('#sellPlaceItemId').html(''); //비워줌
        	$('.profile-left-icon2').html(''); //비워줌
        	
        	var sellPlaceItemId = $('#sellPlaceItemId');
        	var profileLeftIcon = $('.profile-left-icon2');
        	
        	sellPlaceItemId.append('<p class="profile-left-icon2">');
        	profileLeftIcon.append('<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />'
        			+ ' 지역을 선택하세요.</p>');
        }
   });
	
}


function addItem(data) {
	$('#sellPlaceItemId').html(''); //비워줌
	$('.profile-left-icon2').html(''); //비워줌
	
	var sellPlaceItemId = $('#sellPlaceItemId');
	var profileLeftIcon = $('.profile-left-icon2');
	
	sellPlaceItemId.append('<p class="profile-left-icon2">');
	profileLeftIcon.append('<img src="/resources/lib/images/buyer-icon(2).png" alt="icon(2)" />'
			+ ' 보관함을 선택하세요.</p>');
	
	for(var i=0; i<data.result.length; i++) {
		
		var tr = '';
		
		if(data.result[i].YN == 'Y') 
			tr = $('<button type="button" class="item_class" id="item_'+data.result[i].SELL_PLACE_ITEM_NO+'"'
					+ ' onclick="sellPlaceItemClick('+ data.result[i].SELL_PLACE_ITEM_NO +');"' 
					+ ' value= ' + data.result[i].SELL_PLACE_ITEM_NO + '>' 
					+ data.result[i].SELL_PLACE_ITEM_NO + '</button>');
		
		else if(data.result[i].YN == 'N')
			tr = $('<button type="button" class="item_class_d" id="item_'+data.result[i].SELL_PLACE_ITEM_NO+'"'
					+' onclick="sellPlaceItemClick('+ data.result[i].SELL_PLACE_ITEM_NO +');"' 
					+' disabled="disabled" value= ' + data.result[i].SELL_PLACE_ITEM_NO + '>' 
					+ data.result[i].SELL_PLACE_ITEM_NO + '</button>');
		
		tr.appendTo(sellPlaceItemId);
		
	}
}

function sellPlaceItemClick(e) {
	console.log(e);
	$('.item_class').css('background-color','#FFFFFF');
	$('.item_class').css('color','#343a40');
	$('#item_'+e).css('background-color','#343a40');
	$('#item_'+e).css('color','#FFFFFF');
	var sellPlaceItemId = $('#sellPlaceItemId');
	
	var result = document.createElement("input");
	result.type = "hidden";
	result.id = "sell_place_item_id";
	result.name = "sell_place_item_no";
	result.value = e;
	$('#sell_place_item_id').remove();
	$('#sellPlaceItemId').append(result);
	
}
//----------------------------------------------------------------

function categoryChange(e){
	console.log(e.value);
	if(e.value=='카테고리를 선택하세요.') {
		categorySelectNo();
	} else {
		$.ajax({
            url: "/seller/sellCategorySelectMedium.do",
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
            url: "/seller/sellCategorySelectSmall.do",
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
	$('#sell_category_no').remove();
	category.type = "hidden";
	category.id = "sell_category_no";
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

function file_upload1_click(value) {
	if(value.files && value.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			if(!e.target.result.match("image*")) {
	            alert("이미지만 등록해주세요.");
	            $('#file_img1').attr('src','');
				$('#file_upload1').val('');
	            return;
	        }
			$('img[id=file_img1]').attr('src', e.target.result);
		}
		reader.readAsDataURL(value.files[0]);
	}
}

function file_upload2_click(value) {
	if(value.files && value.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			if(!e.target.result.match("image*")) {
	            alert("이미지만 등록해주세요.");
	            $('#file_img2').attr('src','');
				$('#file_upload2').val('');
	            return;
	        }
			$('#file_img2').attr('src', e.target.result);
		}
		reader.readAsDataURL(value.files[0]);
	}
}

function file_upload3_click(value) {
	if(value.files && value.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			if(!e.target.result.match("image*")) {
	            alert("이미지만 등록해주세요.");
	            $('#file_img3').attr('src','');
				$('#file_upload3').val('');
	            return;
	        }
			$('#file_img3').attr('src', e.target.result);
		}
		reader.readAsDataURL(value.files[0]);
	}
}


/*var count = 0;
function transient_storage() {//임시저장
	count++;
	console.log('transient storage');
	$("#ts_count").html(count);
}*/

function check() {
	console.log('submit check');
	var contents = $('#content').val();
	var check1_contents = contents.replaceAll("<p>", "");
	var check2_contents = check1_contents.replaceAll("</p>", "");
	var check3_contents = check2_contents.replaceAll("<br>", "");
	
	var sell_place_item_id = $('#sell_place_item_id').val();
	
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
			if(confirm('입력하시겠습니까?') == true) {
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
	if(confirm('입력을 취소하시겠습니까?') == true) {
		location.href="/main.jsp";
	} else {
		return;
	}
}

//-----------------------------------------------------

// form의 textarea에 summernote 적용
$(document).ready(function() {
	$('#content').summernote({
		height: 700,
		fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
		fontNamesIgnoreCheck : [ '맑은고딕' ],
		focus: true
	});
});