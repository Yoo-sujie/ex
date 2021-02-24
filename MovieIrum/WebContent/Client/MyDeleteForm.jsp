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
<% 
String msg=request.getParameter("msg");

if(msg!=null && msg.equals("0")) 
{%>
	alert("비밀번호를 올바르게 입력해주세요");<%
}

%>
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
.sub{padding:10px 50px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	background-color: #252331;
	border: 0px solid #378;	}
.home{padding:10px 58px;
	font-size: 15px;
	cursor: pointer;
	color: white;
	background-color: #252331;
	border: 0px solid #378;}

label{font-size:15px; font-weight:bold;
		margin-bottom: 30px;}

</style>
<script type="text/javascript">



</script>

</head>
<body class="background">
<jsp:include page="../include/header.jsp"/>
<div style="width: 430px;margin: 20px auto;margin-bottom: 300px;margin-top: 200px;">
	
		<form action="../MyDeleteForm.do" method="post" name="MyUpdateForm" onsubmit="return check()">
			<ul>
				<li style="margin: 30px 0;"><label>비밀번호 입력<br> <input type="password" name="pw"
						id="pw" size="50" 
						>
				</label></li>
				<li><input type="submit" class="sub" value="탈퇴하기"> 
				<input type="button" class="home" value="홈으로" onclick="location.href='http://localhost:8081/MovieIrum/index.jsp'"></li> 
				
			</ul>
		</form>
	</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>