<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Movie Irum</title>
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">

<style>

body {
	font-family : "맑은 고딕";
	color : #000000;
}

#infoBar {
	border-bottom: solid 1px #dddddd;
    padding-bottom: 10px;
    padding-top: 10px;
    background-color: #ffffff;
}

.title {
	width : 1320px;
	margin : 0 auto;
	color : #4a4239;
}

.tit_intro {
    display: block;
    padding-top: 73px;
    font-size: 25px;
    line-height: 34px;
    color: #1e1e1e;
    margin-left : 40px;
}

ul {
	list-style : none;
}

.infoImg {
	width : 900px;
	height : 500px;
	margin-top : 15px;
}

.info1 {
	font-size : 20px;
	color : #4a4239;
}

.info1 > em {
	font-size: 24px;
	color : #004000;
}

.info2 {
	margin-bottom : 50px;
	font-size : 20px;
	color : #4a4239;
}

.info2 > em {
	font-size : 24px;
	color : #004000;
}

</style>
</head>
<body>
<jsp:include page="/include/header.jsp"/>

	<nav id = "infoBar">
		<div class = "title">
			<h2>회사 소개</h2>
		</div>
	</nav>
	
	<section id = "content-box">
		<div class = "box">
			<strong class="tit_intro">“Movie Irum” 더 새로운, 독립 영화관</strong>
			
			<ul class = "format1">
				<li><img src = "/MovieIrum/img/camera.jpg" onerror = "noImg(this)" class = "infoImg"></li>
			</ul>
			
			<ul class = "format2">
				<li class = "info1">
				<em>"Movie Irum"</em><br><br>
					기존 독립영화관 중 유동인구가 많은 서울 또한 폐관을 맞이한 곳이 많습니다.<br>
					일반 대중들에게 알려지지 못해 발길이 적고 매니아층만이 찾던 독립영화관에서 벗어나<br>
					저희 Movie Irum은 일반 대중들과도 만나기 편한 독립 영화관을 추구합니다.<br>
					<br><br>
				</li>
				
				<li class = "info2">
					<em>"Movie Irum의 모토"</em><br><br>
					작은 단막극부터 독립영화까지 상업 영화를 벗어나 예술 영화를 즐기시는 모든 분들이<br>
					접근하기 쉽고 편리하게 이용하실 수 있는 영화관을 모토로 삼으며<br>
					주요 영화제 수상작부터 영화팬들이 기다려온 해외 많은 예술 영화까지 감상 할 수 있습니다.<br>
					<br><br>
				</li>
			</ul>
		</div>	
	
	</section>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>