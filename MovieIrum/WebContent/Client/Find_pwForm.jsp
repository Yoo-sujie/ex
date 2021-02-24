<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script type="text/javascript">

function check() {
	var form = document.joinform;
	if(!form.id.value){
		alert("아이디를 입력해주세요");
		return false;
	}
	if(!form.name.value){
		alert("이름을 입력해주세요");
		return false;
	}
	if(!form.email.value){
		alert("이메일을 입력해주세요");
		return false;
	}
}
<% 
String msg=request.getParameter("msg");

if(msg!=null && msg.equals("0")) 
{%>
	alert("내용을 올바르게 입력해주세요");<%
}

%>
</script>
<style>
body {
	margin-top: 200px;
	background-color: #F5F5F5;
}

* {
	margin: 0;
	padding: 0;
}

div {
	width: 430px;
	margin: 20px auto;
}

li {
	list-style: none;
	margin: 30px 0;
}

label {
	
}

.Find {
	height: 43px;
	width: 420px;
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
.name{
	cursor: pointer;
	height: 43px;
	width: 420px;
}
.email{
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
}
.inp_radio{font: 7px;
}
label {
	font-size: 12px;
	
}

</style>
</head>

<body>
<div>
		<form action="Find_pwForm.do" method="post" name="Find_pwForm"
			onsubmit="return check()">
			<ul>
			<h2>　　　　　　비밀번호 찾기</h2>
			<li></li>
			<br>
				<input type="hidden" name="redirectUrl" value="">
			<input type="text" id="id" name="id" class="id" placeholder="아이디">
			<li></li>
				<input type="text" id="name" name="name" class="name" placeholder="이름">
				<li></li>
				<input type="email" id="email" name="email" class="email"
					placeholder="이메일">
				<li></li>
				<input type="submit" class="Find" value="찾기"></input>
				
			
	</div>
	</ul>
	</form>   
</body>
</html>