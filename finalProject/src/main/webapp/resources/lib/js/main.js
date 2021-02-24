$(document).ready(function() {

	console.log("main.js");
	ajaxUtils = ajaxUtil();

	$(document).on('click', '#loginPage', loginPage); // 로그인 페이지 이동
	
	$(document).on('click', '#logout', logout); // 로그아웃

	$(document).on('keyup', '#id', checkIdAjax); // 회원 가입 아이디 체크
	
	$(document).on('keyup', '#nickName', checkNickAjax); // 회원 가입 닉네임 체크
	
	$(document).on('keyup', '#pw2', checkPwAjax);// 회원 가입 재입력 비밀번호 체크
	
	$(document).on('keyup', '#phone', checkPhoneAjax); //회원 가입 핸드폰 중복 체크 
	
	$(document).on('keyup', '#email01', checkEmailAjax); //회원 가입 이메일 중복 체크 - email01
	
	$(document).on('keyup', '#email02', checkEmailAjax); //회원 가입 이메일 중복 체크 - email02, keyup
	
	$(document).on('change', '#choose-email', checkEmailAjax); //회원 가입 이메일 중복 체크 - change
	
	$(document).on('click', '#purchaseList tr', List); // 구매 내역 리스트 클릭 시 구매글로 이동
	
	$(document).on('click', '#salesList tr', List); // 판매 내역 리스트 클릭 시 판매글로 이동
	
	$(document).on('click', '#reportList tr', List); // 신고 내역 리스트 클릭 시 판매글로 이동
	
	$('#phone').keydown(function(event) { // 회원 가입 핸드폰 번호 작성시 -(하이픈) 자동 등록
		var key = event.charCode || event.keyCode || 0;
	    $text = $(this);
	    if (key != 8 && key != 9) {
	        if ($text.val().length === 3) {
	            $text.val($text.val() + '-');
	        }
	        if ($text.val().length === 8) {
	            $text.val($text.val() + '-');
	        }
	    }
	 
	    return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
	});
	
	$('#choose-email').change(function(){ //회원가입, 비밀번호 찾기 이메일 입력
		$("#choose-email option:selected").each(function() { 
			if($(this).val()=='1'){ //직접입력
				$("#email02").val(''); //값 초기화 
			}else{ //직접입력이 아닐경우 
				$("#email02").val($(this).text()); //선택값 입력
				} 
			}); 
		});
	
	$('#selectEmail').change(function(){ //아이디 찾기 이메일 입력
		$("#selectEmail option:selected").each(function() { 
			if($(this).val()=='1'){ //직접입력
				$("#writeEmail02").val(''); //값 초기화 
			}else{ //직접입력이 아닐경우 
				$("#writeEmail02").val($(this).text()); //선택값 입력 
				} 
			}); 
		});

	
	
	
	
	ajaxUtils.doPost("/seller/mainSellListView.do",'',mainSellListView); // 메인페이지 최근 올라온 상품 목록
	ajaxUtils.doPost("/seller/mainSellReviewListView.do",'',mainSellReviewListView); // 메인페이지 최근 올라온 상품 목록
	
});


function mainSellReviewListView(dataList){
	console.log(dataList);


	
	$('#search_div_2').html('');
	

	console.log(dataList.list);

	if (dataList.list) {
		if (dataList.list.length > 0) {
			
			for (var i = 0; i < dataList.list.length; i++) {
				
				
				
			
				
				
				var image
				if(dataList.list[i]["REVIEWS_IMAGE"] == null){
					
					image = '/resources/lib/images/main-test(1).jpg';
					console.log(image);
				}else{
					console.log(dataList.list[i]["REVIEWS_IMAGE"]);
					
					image = dataList.list[i]["REVIEWS_IMAGE"];
				}
				
				
				
				
				var div = $('<div id="sellSearchTable2" class="search_div2">');
				
				var a = $('<a href="/buyer/buyerContentView.do?sell_no=' + dataList.list[i]["SELL_NO"] + '"></a>');
				var div_card = $('<div class="card"></div>');
				
				var div_card_header = $('<div class="card-header"></div>');
				var div_header = $('<div class = "card-header-is_closed" ></div>');
				var div_header_text = $('<div class = "card-header-text" >' + dataList.list[i]["CARD_HEADER"] + '</div>');
				var img = $('<img src="' + image + '" class="img-thumbnail" alt="Cinque Terre" width="322" height="250">');
				
				var div_card_body = $('<div class="card-body"></div>');
				
				var div_body = $('<div class="card-body-header"></div>');
				
				var h1 = $('<h1>[리뷰]' + dataList.list[i]["SELL_TITLE_STR"] + '</h1>');
				
				var p = $('<p class="card-body-hashtag">#' + dataList.list[i]["SELL_PLACE_NAME"] + ' #' + dataList.list[i]["SELL_CATEGORY_NAME"] + ' #' + dataList.list[i]["SELL_PRICE"] + '</p>');
				
				var div_card_footer = $('<div class="card-body-footer"></div>');
				var hr = $('<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">');
				var i_icon = $('<i class="icon icon-view_count"></i>');
				
				var i_reg = $('<i class="reg_date">' +  dataList.list[i]["REVIEW_WRITE_DATE"] + ' </i>');

				
				if(dataList.list[i]["CARD_HEADER"] != 'NOT'){
					div_header.appendTo(div_card_header);
					div_header_text.appendTo(div_header);
					
				}
				img.appendTo(div_card_header);
				
				h1.appendTo(div_body);
				p.appendTo(div_body);
				
				hr.appendTo(div_card_footer);
				i_icon.appendTo(div_card_footer);
				
				i_reg.appendTo(div_card_footer);
				
				div_body.appendTo(div_card_body);
				div_card_footer.appendTo(div_card_body);
				
				div_card_header.appendTo(div_card);
				div_card_body.appendTo(div_card);
				
				div_card.appendTo(a);
				
				div
				a.appendTo(div);
				
				div.appendTo('#search_div_2');
				
			}
			
		}

	}
	
	
	
}


function mainSellListView(dataList){
	console.log(dataList);


	
	$('#search_div').html('');
	

	console.log(dataList.list);

	if (dataList.list) {
		if (dataList.list.length > 0) {
			
			for (var i = 0; i < dataList.list.length; i++) {
				
				
				
			
				
				
				var image
				if(dataList.list[i]["SELL_IMAGE"] == null){
					
					image = '/resources/lib/images/main-test(1).jpg';
					console.log(image);
				}else{
					console.log(dataList.list[i]["SELL_IMAGE"]);
					
					image = dataList.list[i]["SELL_IMAGE"];
				}
				
				
				
				
				var div = $('<div id="sellSearchTable2" class="search_div2">');
				
				var a = $('<a href="/buyer/buyerContentView.do?sell_no=' + dataList.list[i]["SELL_NO"] + '"></a>');
				var div_card = $('<div class="card"></div>');
				
				var div_card_header = $('<div class="card-header"></div>');
				var div_header = $('<div class = "card-header-is_closed" ></div>');
				var div_header_text = $('<div class = "card-header-text" >' + dataList.list[i]["CARD_HEADER"] + '</div>');
				var img = $('<img src="' + image + '" class="img-thumbnail" alt="Cinque Terre" width="322" height="250">');
				
				var div_card_body = $('<div class="card-body"></div>');
				
				var div_body = $('<div class="card-body-header"></div>');
				
				var h1 = $('<h1>' + dataList.list[i]["SELL_TITLE_STR"] + '</h1>');
				
				var p = $('<p class="card-body-hashtag">#' + dataList.list[i]["SELL_PLACE_NAME"] + ' #' + dataList.list[i]["SELL_CATEGORY_NAME"] + ' #' + dataList.list[i]["SELL_PRICE"] + '</p>');
				
				var div_card_footer = $('<div class="card-body-footer"></div>');
				var hr = $('<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">');
				var i_icon = $('<i class="icon icon-view_count"></i>');
				var label = $('<label>조회' + dataList.list[i]["SELL_CNT"] + '회</label>');
				var i_reg = $('<i class="reg_date">' +  dataList.list[i]["SELL_WRITE_DATE"] + ' </i>');

				
				if(dataList.list[i]["CARD_HEADER"] != 'NOT'){
					div_header.appendTo(div_card_header);
					div_header_text.appendTo(div_header);
					
				}
				img.appendTo(div_card_header);
				
				h1.appendTo(div_body);
				p.appendTo(div_body);
				
				hr.appendTo(div_card_footer);
				i_icon.appendTo(div_card_footer);
				label.appendTo(div_card_footer);
				i_reg.appendTo(div_card_footer);
				
				div_body.appendTo(div_card_body);
				div_card_footer.appendTo(div_card_body);
				
				div_card_header.appendTo(div_card);
				div_card_body.appendTo(div_card);
				
				div_card.appendTo(a);
				
				div
				a.appendTo(div);
				
				div.appendTo('#search_div');
				
			}
			
		}

	}
	
	
	
}

function loginPage() {
	console.log("로그인 페이지 이동");
	location.href = "/loginPage.do";
}

function logout() {
	console.log("로그아웃");
	location.href = "/logout.do";
}

// 아이디 중복체크 에이젝스 함수
function checkId(data) {
	var idRule = /^[A-Za-z0-9]{6,12}$/;
	var element = document.getElementById("id");
	console.log(data)

	if (data.list == 1) {// 중복
		$("#checkId").text("중복 아이디입니다");
		$("#checkId").css("color", "red");
		$("#checkId").css("font-size", "15px");
		$("#checkId").css("font-family", "sans-serif");
		$("#btn_join").attr("disabled", true);
	} else {// 중복x
		if (!idRule.test($('#id').val())) {
			$("#checkId").text("아이디는 영어 대소문자와 숫자만 사용 가능, 6-12글자로만 작성해주세요");
			$("#checkId").css("color", "red");
			$("#checkId").css("font-size", "15px");
			$("#checkId").css("font-family", "sans-serif");
			$("#btn_join").attr("disabled", true);
		} else {
			$('#checkId').text('사용 가능한 아이디입니다');
			$("#checkId").css("color", "blue");
			$("#checkId").css("font-size", "15px");
			$("#checkId").css("font-family", "sans-serif");
			$("#btn_join").attr("disabled", false);
		}
	}
}

function checkIdAjax() {
	var id = $('#id').val();
	console.log(id);

	var param = {
		id : id
	}

	$.ajax({
		url : "checkId.do",
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(data) {
			checkId(data);
		},
		error : function(status) {
			console.log(status);
		}
	})
}

// 닉네임 중복체크 에이젝스 함수
function checkNick(data) {
	var element = document.getElementById("nickName");
	console.log(element)

	if (data.list == 1) {
		// 1 : 닉네임이 중복
		$("#checkNick").text("중복 닉네임입니다");
		$("#checkNick").css("font-size", "15px");
		$("#checkNick").css("font-family", "sans-serif");
		$("#checkNick").css("color", "red");
		$("#btn_join").attr("disabled", true);
	} else {
		$('#checkNick').text('사용 가능한 닉네임입니다');
		$("#checkNick").css("color", "blue");
		$("#checkNick").css("font-size", "15px");
		$("#checkNick").css("font-family", "sans-serif");
		$("#btn_join").attr("disabled", false);
	}
}

function checkNickAjax() {
	var nick = $('#nickName').val();
	console.log(nick);

	var param = {
		nick : nick
	}

	$.ajax({
		url : "checkNick.do",
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(data) {
			checkNick(data);
		},
		error : function(status) {
			console.log(status);
		}
	});
};

// 비밀번호1, 비밀번호2 대조 함수
function checkPwAjax() {
	var pw1 = $("#pw").val();
	var pw2 = $("#pw2").val();
	console.log(pw1);
	console.log(pw2);

	if (pw1 !== pw2) {
		// 비밀번호1, 비밀번호2 같지 않을 때
		$("#checkPw").text("비밀번호 불일치");
		$("#checkPw").css("color", "red");
		$("#checkPw").css("font-size", "15px");
		$("#checkPw").css("font-family", "sans-serif");
		$("#btn_join").attr("disabled", true);
	} else if (pw1 == pw2) {
		$('#checkPw').text('비밀번호 일치');
		$("#checkPw").css("color", "blue");
		$("#checkPw").css("font-size", "15px");
		$("#checkPw").css("font-family", "sans-serif");
		$("#btn_join").attr("disabled", false);
	}
}

//핸드폰 중복체크 에이젝스 함수
function checkPhone(data) {
	console.log(data)

	if (data.list == 1) {// 중복
		$("#checkPhone").text("사용중이신 번호입니다");
		$("#checkPhone").css("color", "red");
		$("#checkPhone").css("font-size", "15px");
		$("#checkPhone").css("font-family", "sans-serif");
		$("#btn_join").attr("disabled", true);
	} else {// 중복x
			$('#checkPhone').text('사용 가능한 번호입니다');
			$("#checkPhone").css("color", "blue");
			$("#checkPhone").css("font-size", "15px");
			$("#checkPhone").css("font-family", "sans-serif");
			$("#btn_join").attr("disabled", false);
	}
}

function checkPhoneAjax() {
	var phone = $('#phone').val();
	console.log(phone);

	var param = {
		phone : phone
	}

	$.ajax({
		url : "checkPhone.do",
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(data) {
			checkPhone(data);
		},
		error : function(status) {
			console.log(status);
		}
	})
}

//이메일 중복체크 에이젝스 함수
function checkEmail(data) {
	console.log(data)

	if (data.list == 1) {// 중복
		$("#checkEmail").text("사용중이신 이메일입니다");
		$("#checkEmail").css("color", "red");
		$("#checkEmail").css("font-size", "15px");
		$("#checkEmail").css("font-family", "sans-serif");
		$("#btn_join").attr("disabled", true);
	} else {// 중복x
			$('#checkEmail').text('사용 가능한 이메일입니다');
			$("#checkEmail").css("color", "blue");
			$("#checkEmail").css("font-size", "15px");
			$("#checkEmail").css("font-family", "sans-serif");
			$("#btn_join").attr("disabled", false);
	}
}

function checkEmailAjax() {
	var email1 = $('#email01').val();
	var email2 = $('#email02').val();
	var atSign = '@';
	console.log(email1);
	console.log(email2);

	var param = {
			email1 : email1,
			atSign : atSign,
			email2 : email2
	}

	$.ajax({
		url : "checkEmail.do",
		type : 'post',
		data : param,
		dataType : 'json',
		success : function(data) {
			checkEmail(data);
		},
		error : function(status) {
			console.log(status);
		}
	})
}

function List() {
	console.log(this);
	var tr = $(this);
	var td = tr.children();
	var sellNo = td.eq(0).text();

	if (sellNo != 'NO') {
		console.log(sellNo);

		location.href = "/buyer/buyerContentView.do?sell_no="+ sellNo;
	}

}

