<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<title>Movie Irum</title>
<script src="/MovieIrum/js/jquery-3.3.1.js"></script>
<script>
	/* 이렇게 열고 */
	function kaja() {
		location.href ="serviceCenter.service";
	}
</script>
<style>
body {
	background: repeating-linear-gradient(45deg, #3B3942, #3B3942 2px, #38363F 0, #38363F 20px);
}
h1 {
	text-align: center;
	margin-top : 80px;
	color : #ffffff;
}

h2 {
	text-align: center;
	margin-bottom: 200px;
	color : #ffffff;
}
</style>
</head>
<body onload="setTimeout('kaja()', 2000)">
<jsp:include page="/include/header.jsp"/>
	<h1> 다른 이용자의 글입니다. </h1>
	<h2> 고객센터 목록으로 이동합니다.</h2>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>