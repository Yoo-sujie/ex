<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<style>

li {
	list-style: none;
}

input, select {
	cursor:pointer;
	height: 33px;
}


label {
	line-height: 30px;
	
}

button {
	font-size: 15px;
	cursor: pointer;
	color: white;
	display: block;
	background-color: #252331;
	border: 0px solid #378;
}
#bt_tel{padding: 13px 20px; float:right;}
.sub{padding:15px 155px;}
label{ color:white; font-size:15px; font-weight:bold;}
.IdCheck{padding:8px 8px;
	color: white;
	background-color: #252331;
	border: 0px solid #378;}
</style>
<script type="text/javascript">

function check() {
	var form = document.JoinForm;
	
	if(!form.new_id.value.replace(/(\s*)/gi, "")){
		alert("아이디를 입력해주세요");
		return false;
	}
	for (i = 0; i < form.new_id.value.length; i++) {
        ch = form.new_id.value.charAt(i)
        if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
            alert("아이디는 영대소문자,숫자만 입력가능합니다.")
            form.new_id.focus()
            form.new_id.select()
            return false;
        }
    }
	if(!form.new_pw.value){
		alert("패스워드를 입력해주세요");
		return false;
	}
	if(form.new_pw.value != form.new_pw2.value){
		alert("패스워드가 동일하지 않습니다");
		return false;
	}
	if(!form.new_pw.value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~,-])|([!,@,#,$,%,^,&,*,?,_,~,-].*[a-zA-Z0-9])/)){
		alert("비밀번호는 영문(대소문자구분),숫자,특수문자를 혼용하여 8~16자를 입력해주세요.");
	    return false;
	  }
	
	
	if (form.new_pw.value == form.new_id.value) {
        alert("아이디와 비밀번호가 같습니다.")
        form.new_pw.focus()
        return false;
    }
   
    if (form.new_pw.value.length<8 || form.new_pw.value.length>20) {
        alert("비밀번호를 8~20자까지 입력해주세요.")
        form.new_pw.focus()
        form.new_pw.select()
        return false;
    }
	if(!form.new_name.value){
		alert("이름을 입력해주세요");
		return false;
	}
	if(!form.new_birth.value){
		alert("생년월일을 입력해주세요");
		return false;
	}
	if(isNaN(form.new_birth.value)){
		alert("생년월일은 숫자만 입력 가능 합니다");
		return false;
	}
	if (form.new_birth.value.length<8||form.new_birth.value.length>8) {
        alert("생년월일 8자리를 정확히 입력해주세요")
        form.new_birth.focus()
        form.new_birth.select()
        return false;
    }
	if(!form.new_email.value){
		alert("이메일을 입력해주세요");
		return false;
	}
	if(isNaN(form.new_tel.value)){
		alert("핸드폰 번호는 숫자만 입력 가능 합니다");
		return false;
	}
	if (form.new_tel.value.length<11||form.new_tel.value.length>11) {
        alert("휴대번호 11자리를 정확히 입력해 주세요.")
        form.new_tel.focus()
        form.new_tel.select()
        return false;
    }
	if(!form.IdUncheck.value || form.IdUncheck.value != form.new_id.value) {
		alert("아이디 중복확인을 해주세요.");
		return false;
	}
}
function openIdCheck() {
	var form = document.JoinForm;
	if(document.JoinForm.new_id.value.replace(/(\s*)/gi, "") == ""){
		alert("아이디를 입력하세요");
		return;
	}
	for (i = 0; i < form.new_id.value.length; i++) {
        ch = form.new_id.value.charAt(i)
        if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
            alert("아이디는 대소문자, 숫자만 입력가능합니다.")
            form.new_id.focus()
            form.new_id.select()
            return false;
        }
    }
	 if (form.new_id.value.indexOf(" ") >= 0) {
         alert("아이디에 공백을 사용할 수 없습니다.")
         form.new_id.focus()
         form.new_id.select()
         return false;
     }
    
     if (form.new_id.value.length<8 || form.new_id.value.length>20) {
         alert("아이디를 8~20자까지 입력해주세요.")
         form.new_id.focus()
         form.new_id.select()
         return false;
     }
     
 url = "IdCheckForm.jsp?id="+document.JoinForm.new_id.value;
 open(url,"post","toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200,left=800, top=270");
}



</script>
</head>
<body class="background">
<jsp:include page="../include/header.jsp"/>
	<div style="width: 430px;margin: 20px auto;margin-bottom: 100px;margin-top: 50px;">
		<form action="../JoinForm.do" method="post" name="JoinForm" onsubmit="return check()">
			<ul>
				<li style="margin: 20px 0;"><label>아이디<br> <input type="text" id="new_id" name="new_id"
						size="35" placeholder="  영어와 숫자를 이용해  8자 이상 작성" >
						<input type="button" value="중복체크" id="new_check" onclick="openIdCheck(this.form)" class="IdCheck"
						>
						<input type="hidden" name="IdUncheck" id="IdUncheck" >
				</label></li>
				<li style="margin: 20px 0;"><label>비밀번호<br> <input type="password" onkeyup="pwcheck()" name="new_pw"
						id="new_pw" size="50" placeholder="  영어,숫자,특수문자를 혼합해 8글자이상 작성" 
						>
				</label></li>
				<li style="margin: 20px 0;"><label>비밀번호 확인<br> <input type="password" onkeyup="pwcheck()" name="new_pw2"
						id="new_pw2" size="50" placeholder="  영어,숫자,특수문자를 혼합해 8글자이상 작성">
				</label></li>
				<li style="margin: 20px 0;"><label>이름<br> <input type="text" id="new_name" name="new_name"
						size="40" >
				</label></li>
				<li style="margin: 20px 0;"><label>생년월일<br> <input type="text" id="new_birth" name="new_birth"
						size="15" placeholder="  ex) 19940517" ></label></li>
				<li style="margin: 20px 0;"><label>이메일<br> <input type="email" id="new_email" name="new_email"
						size="50" placeholder="ex) dnwn0517@nate.com" >
				</label></li>
				<li ><label>휴대전화<br> <select id="new_tel2" name="new_tel2">
							<option value="korea">대한민국 +82</option>
					</select><br></li>
				<li><input type="tel" id="new_tel" name="new_tel" size="40" placeholder="  ex) 01090618225">
					
				<li style="margin: 20px 0;"><button type="submit" class="sub">가입하기</button></li>
				</label>
			</ul>
		</form>
	</div>
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>