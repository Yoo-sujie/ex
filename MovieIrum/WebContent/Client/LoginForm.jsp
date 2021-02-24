<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script>
function check() {
	var form = document.LoginForm;
	if(!form.id.value.replace(/(\s*)/gi, "")){
		alert("아이디를 입력해주세요");
		return false;
	}
	if(!form.pw.value){
		alert("패스워드를 입력해주세요");
		return false;
	}
}
<% 
// 아이디, 비밀번호가 틀릴경우 화면에 메시지 표시
//컨트롤러에서 로그인 처리 결과에 따른 메시지를 보낸다.
String msg=request.getParameter("msg");

if(msg!=null && msg.equals("0")) 
{%> 
	alert("아이디와 비밀번호를 확인해주세요");
	<%}%>    
 
</script>
<style>
body {
	margin-top: 150px;
	background-color: #F5F5F5;
}

* {
	margin: 0;
	padding: 0;
}


li {
	list-style: none;
	margin: 30px 0;
}

label {
	
}

.login {
	height: 43px;
	width: 424px;
	font-size: 13px;
	cursor: pointer;
	color: white;
	display: block;
	background-color: #252331;
	border: 0px solid #378;
}

.id{
	cursor: pointer;
	height: 43px;
	width: 420px;
}
.pw{
	cursor: pointer;
	height: 43px;
	width: 420px;
	margin-top: 1px;
}

.lab_g {
	float: left;
}

.txt_find2 {
	float: right;
	margin-top: 10px;
}
.btn_Join{
margin-top: 3px;
height: 43px;
	width: 420px;
}
.inp_radio{font: 7px;
}
label {
	font-size: 12px;
	
}

</style>


</head>
<body>

	<div style="width: 430px;margin: 20px auto;margin-bottom: 100px;margin-top: 50px;">
	<table border="0" class="logo">
  	<tr><td align="center" class="image blinking">
  		<a href="/MovieIrum/index.jsp"><img alt="header_logo" src="\MovieIrum\img\mi_logo_header2.png" align="middle" width="150px;"></a>
  	</td></tr>
  	<tr><td align="center">
		<form action="/MovieIrum/LoginForm.do" method="post" name="LoginForm"
			onsubmit="return check()">
			<ul>
				<input type="hidden" name="redirectUrl" value="">

				<input type="text" id="id" name="id" class="id" placeholder="ID">
				<br>
				<input type="password" id="pw" name="pw" class="pw"
					placeholder="Password">
				<li></li>
				<input type="submit" class="login" value="로그인"></input>
				
				<div></div>
				
				</label> <span class="txt_find2"> <a href="/MovieIrum/Client/Find_idForm.jsp"
						class="/MovieIrum/Client/link_find2" style="text-decoration:none">아이디찾기</a>/ 
						<a href="/MovieIrum/Client/Find_pwForm.jsp" class="link_find2" style="text-decoration:none">비밀번호찾기</a>/
						<a href="/MovieIrum/Client/JoinForm.jsp" class="link_find2" style="text-decoration:none">회원가입</a>
				</span>
	</div>
	</ul>
	</form>
</body>
</html>