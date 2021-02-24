<%@page import="client.clientVO.ClientVO"%>
<%@page import="client.clientDAO.ClientDAO"%>
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
<script>
 Sting sessionId = null;
 if (session.getAttribute("sessionID") != null){
	 sessionId = (String) session.getAttribute("sessionID");
 }
 if( sessionId== null){
	 session.getAttribute("messageType", "오류 메시지");
	 session.getAttribute("messageContent","현재 로그인이 되어있지 않습니다");
	 response.sendRedirect("LoginForm.jsp")
 }
 ClientVO client = up ClientDAO().getClinet(clientid);
 function check() {
		var form = document.MyUpdateForm;

		if(!form.up_name.value){
			alert("이름을 입력해주세요");
			return false;
		}
		if(!form.up_birth.value){
			alert("생년월일을 입력해주세요");
			return false;
		}
		if(isNaN(form.up_birth.value)){
			alert("생년월일은 숫자만 입력 가능 합니다");
			return false;
		}
		if (form.up_birth.value.length<8||form.up_birth.value.length>8) {
	        alert("생년월일 8자리를 정확히 입력해주세요")
	        form.up_birth.focus()
	        form.up_birth.select()
	        return false;
	    }
		if(!form.up_email.value){
			alert("이메일을 입력해주세요");
			return false;
		}
		if(isNaN(form.up_tel.value)){
			alert("핸드폰 번호는 숫자만 입력 가능 합니다");
			return false;
		}
		if (form.up_tel.value.length<11||form.up_tel.value.length>11) {
	        alert("휴대번호 11자리를 정확히 입력해 주세요.")
	        form.up_tel.focus()
	        form.up_tel.select()
	        return false;
	    }
		
	}
	     
</script>
<style>



li {
	list-style: none;
	
}

input, select {
	cursor:pointer;
	height: 33px;
}



#bt_tel{padding: 13px 20px; float:right;}
.sub{padding:10px 36px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	background-color: #252331;
	border: 0px solid #378;	}
.home{padding:10px 44px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	background-color: #252331;
	border: 0px solid #378;}
.up_pw{padding:10px 90px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	display: block;
	background-color: #252331;
	border: 0px solid #378;	}
label{font-size:15px; font-weight:bold;
		margin-bottom: 30px;}
.IdCheck{padding:8px 8px;
	color: white;
	background-color: #252331;
	border: 0px solid #378;}
</style>
<script type="text/javascript">



</script>
</head>
<body class="background">
<jsp:include page="../include/header.jsp"/>
	<div style="width: 430px;margin: 20px auto;margin-bottom: 100px;margin-top: 100px;">
		<form action="MyUpdateForm.do" method="post" name="MyUpdateForm" onsubmit="return check()">
			<ul>
				<label>아이디<br> <input type="text" id="up_id" name="up_id" disabled value="${vo1.clientId}"
						size="40" >
						
						<input type="hidden" name="IdUncheck" id="IdUncheck" >
				</label>
				<li style="margin: 30px 0;"><label>이름<br> <input type="text" id="up_name" name="up_name" value="${vo1.clientName}"
						size="40" >
				</label></li>
				<li ><label>생년월일<br> <input type="text" id="up_birth" name="up_birth"
						size="40" value="${vo1.clientBirth}" disabled></label></li>
				<li style="margin: 30px 0;"><label>이메일<br> <input type="email" id="up_email" name="up_email"
						size="40" value="${vo1.clientEmail}" >
				</label></li>
				<li><label>휴대전화</label><br>
				<input type="tel" id="up_tel" name="up_tel" size="40"  
				value="${vo1.clientTel}"></li>
				<li style="margin: 30px 0;"><label>나의 포인트<br> <input type="text" id="up_point" name="up_point" value="${vo1.clientPoint}"
						size="40" disabled>
				</label></li>
				<li style="margin: 30px 0;"><input type="submit" class="sub" value="수정하기"> 
				<input type="button" class="home" value="홈으로" onclick="location.href='/MovieIrum/index.jsp'"></li> 
				<li style="margin: 30px 0;"><input type="button" class="up_pw" value="비밀번호변경" onclick="location.href='Client/MyUpdatePwForm.jsp'"></li>
			</ul>
		</form>
	</div>
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>