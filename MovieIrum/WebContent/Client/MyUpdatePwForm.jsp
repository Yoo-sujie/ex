<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<script>
function check() {
	var form = document.MyUpdatePwForm;
	if(!form.pw.value){
		alert("기존 비밀번호를 입력해주세요");
		return false;
	}
	if(!form.new_pw.value){
		alert("새로운 비밀번호를 입력해주세요");
		return false;
	}
	if(form.new_pw.value != form.new_pw2.value){
		alert("패스워드가 동일하지 않습니다");
		return false;
	}
	if(!form.new_pw.value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~,-])|([!,@,#,$,%,^,&,*,?,_,~,-].*[a-zA-Z0-9])/)){
		alert("새로운 비밀번호는 영문(대소문자구분),숫자,특수문자를 혼용하여 8~16자를 입력해주세요.");
	    return false;
	  }
   
    if (form.new_pw.value.length<8 || form.new_pw.value.length>20) {
        alert("새로운 비밀번호를 8~20자까지 입력해주세요.")
        form.new_pw.focus()
        form.new_pw.select()
        return false;
    }
	
}
<% 
String msg=request.getParameter("msg");

if(msg!=null && msg.equals("0")) 
{%>
	alert("기존비밀번호를 올바르게 입력해주세요");<%
}

%>
</script>
<style>


li {
	list-style: none;
	margin: 30px 0;
}

input, select {
	cursor:pointer;
	height: 33px;
}



button {
	
	}
#bt_tel{padding: 13px 20px; float:right;}
.sub{padding:10px 50px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	background-color: #252331;
	border: 0px solid #378;	}
.back{padding:10px 50px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	background-color: #252331;
	border: 0px solid #378;	}
label{font-size:15px; font-weight:bold;
		margin-bottom: 30px;}
.IdCheck{padding:8px 8px;
	color: white;
	background-color: #252331;
	border: 0px solid #378;}
</style>
<link rel="stylesheet" href="/MovieIrum/css/style.css?after" />
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
</head>
<body class="background">
<jsp:include page="../include/header.jsp"/>
	<div style="width: 430px;margin: 20px auto;margin-bottom: 80px;margin-top: 100px;">
		<form action="/MovieIrum/MyUpdatePwForm.do" method="post" name="MyUpdatePwForm" onsubmit="return check()">
			<ul>
				<li><label>기존 비밀번호<br> <input type="password" name="pw"
						id="pw" size="50" placeholder="">
				</label></li>
				<li><label>새 비밀번호<br> <input type="password"  name="new_pw"
						id="new_pw" size="50" placeholder="  영어,숫자,특수문자를 혼합해 8글자이상 작성" >
				</label></li>
				<li><label>새 비밀번호 확인<br> <input type="password"  name="new_pw2"
						id="new_pw2" size="50" placeholder="  영어,숫자,특수문자를 혼합해 8글자이상 작성">
				</label></li>
				
				<li><input type="submit" class="sub" value="변경하기"> <input type="button" class="back" value="홈으로" onclick="location.href='/MovieIrum/index.jsp'"></li>
			</ul>
		</form>
	</div>
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>