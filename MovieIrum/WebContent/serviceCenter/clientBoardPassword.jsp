<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script src="/MovieIrum/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).on('click', '#pwbtn', clickedPWBtn);
	});

	function clickedPWBtn() {
		console.log(this);
		var claimNo = $('#claimNo').val();
		var inputPw = $('#inputPw').val();
		console.log(claimNo);
		console.log(inputPw);
		location.href = "/MovieIrum/searchPassword.service?num=" + claimNo + "&pw=" + inputPw;
	}
</script>

<style>
.box {
	width: 400px;
	height: 150px;
	margin: auto;
	margin-top: 100px;
	margin-bottom: 250px;
}

ul {
	list-style: none;
	margin-left: 38px;
	margin-top: 23px;
}

.btn {
	width: 100px;
	height: 40px;
	padding: 5px 12px;
	border: none;
	background-color: #000000;
	color: white;
	cursor: pointer;
	margin: 10px;
	position: auto;
}
</style>
</head>
<body class="background">
	<jsp:include page="/include/header.jsp" />

	<form action="searchPassword.service">
		<input type="hidden" id="claimNo" value="${param.num }">
		<fieldset class="box">
			<legend align="center">
				<font color=#ffffff>비밀번호를 입력하세요! </font>
			</legend>
			<ul>
				<li><label for="password"><font color=#ffffff>비밀번호</font>
						<input type="text" name="pw" autofocus required
						placeholder="비밀번호 입력" id="inputPw" /> </label></li>
				<li><input type="button" value="확인" class="btn" id="pwbtn" />
					<a href="/MovieIrum/serviceCenter.service"><input type="button" value="취소"
						class="btn" /></a></li>
			</ul>
		</fieldset>
	</form>

	<jsp:include page="/include/footer.jsp" />
</body>
</html>