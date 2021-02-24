$(document).ready(function() {
	console.log("editProfile.js");
	
	$(document).on('keyup', '#nick', myPageNickAjax); // 마이페이지 닉네임 중복 체크
	
	$(document).on('click', '#nick', myPageNickAjax); // 마이페이지 닉네임 중복 체크
	
	$(document).on('keyup', '#confirm-pw', myPagePwAjax);// 마이페이지 재입력 비밀번호 체크
	
	$(document).on('keyup', '#phone', checkPhoneAjax); // 마이페이지 핸드폰 중복 체크
	
	$(document).on('click', '#phone', checkPhoneAjax); // 마이페이지 핸드폰 중복 체크
	
	$('#phone').keydown(function(event) { //핸드폰 번호 작성시 -(하이픈) 자동 등록
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
	
	$(document).on('keyup', '#email01', checkEmailAjax); // 마이페이지 이메일 중복 체크 - email01
	
	$(document).on('keyup', '#email02', checkEmailAjax); // 마이페이지 이메일 중복 체크 - email02, keyup
	
	//추가
	$(document).on('click', '#email01', checkEmailAjax); // 마이페이지 이메일 중복 체크 - email01
	
	$(document).on('click', '#email02', checkEmailAjax); // 마이페이지 이메일 중복 체크 - email02, keyup
	
	$(document).on('change', '#choose-email', checkEmailAjax); // 마이페이지 이메일 중복 체크 - change
})

// 비밀번호 비교
function myPagePwAjax() {
	var pw1 = $("#pw").val();
	var pw2 = $("#confirm-pw").val();
	console.log(pw1);
	console.log(pw2);

	if (pw1 !== pw2) {
		// 비밀번호1, 비밀번호2 같지 않을 때
		$("#checkPW").text("비밀번호 불일치");
		$("#checkPW").css("color", "red");
		$("#checkPW").css("font-size", "15px");
		$("#checkPW").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", true);
	} else if (pw1 == pw2) {
		$('#checkPW').text('비밀번호 일치');
		$("#checkPW").css("color", "blue");
		$("#checkPW").css("font-size", "15px");
		$("#checkPW").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
	}
}

// 닉네임 중복 체크
function checkNick(data) {
	var element = document.getElementById("nick");
	console.log(element)
	if (data.list == 1) {
		// 1 : 닉네임이 중복
		$("#checkNIC").text("중복 닉네임입니다");
		$("#checkNIC").css("font-size", "15px");
		$("#checkNIC").css("font-family", "sans-serif");
		$("#checkNIC").css("color", "red");
		$("#btn-update").attr("disabled", true);
	} else if(data.list == 2) {
		$('#checkNIC').text('사용중이신 닉네임입니다');
		$("#checkNIC").css("color", "blue");
		$("#checkNIC").css("font-size", "15px");
		$("#checkNIC").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
	} else {
		$('#checkNIC').text('사용 가능한 닉네임입니다');
		$("#checkNIC").css("color", "blue");
		$("#checkNIC").css("font-size", "15px");
		$("#checkNIC").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
	}
}

function myPageNickAjax() {
	var nick = $('#nick').val();
	console.log(nick);

	var param = {
		nick : nick
	}

	$.ajax({
		url : "myPageNick.do",
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

//핸드폰 중복체크 에이젝스 함수
function checkPhone(data) {
	var element = document.getElementById("phone");
	console.log(element)
	if (data.list == 1) {
		// 1 : 닉네임이 중복
		$("#checkPhone").text("사용중인 번호입니다");
		$("#checkPhone").css("font-size", "15px");
		$("#checkPhone").css("font-family", "sans-serif");
		$("#checkPhone").css("color", "red");
		$("#btn-update").attr("disabled", true);
	} else if(data.list == 2) {
		$('#checkPhone').text('사용중이신 번호입니다');
		$("#checkPhone").css("color", "blue");
		$("#checkPhone").css("font-size", "15px");
		$("#checkPhone").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
	} else {
		$('#checkPhone').text('사용 가능한 번호입니다');
		$("#checkPhone").css("color", "blue");
		$("#checkPhone").css("font-size", "15px");
		$("#checkPhone").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
	}
	
}

function checkPhoneAjax() {
	var phone = $('#phone').val();
	console.log(phone);

	var param = {
		phone : phone
	}

	$.ajax({
		url : "myPagePhone.do",
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
	if (data.list == 1) {
		// 1 : 닉네임이 중복
		$("#checkEmail").text("사용중인 이메일입니다");
		$("#checkEmail").css("font-size", "15px");
		$("#checkEmail").css("font-family", "sans-serif");
		$("#checkEmail").css("color", "red");
		$("#btn-update").attr("disabled", true);
	} else if(data.list == 2) {
		$('#checkEmail').text('사용중이신 이메일입니다');
		$("#checkEmail").css("color", "blue");
		$("#checkEmail").css("font-size", "15px");
		$("#checkEmail").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
	} else {
		$('#checkEmail').text('사용 가능한 이메일입니다');
		$("#checkEmail").css("color", "blue");
		$("#checkEmail").css("font-size", "15px");
		$("#checkEmail").css("font-family", "sans-serif");
		$("#btn-update").attr("disabled", false);
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
		url : "myPageEmail.do",
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