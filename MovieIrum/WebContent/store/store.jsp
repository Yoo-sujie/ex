<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Movie Irum</title>
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script src="/MovieIrum/js/jquery-3.3.1.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(document).on('click', '#popcorn', popcorn);
		$(document).on('click', '#store', store);
		
	});

	function popcorn() {
		location.href = "/MovieIrum/store/popcorn.jsp";
	}
	
	function store() {
		location.href = "/MovieIrum/store/store.jsp";
	}
	
	
	
	
</script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font-family: "맑은 고딕";
	color: #444444;
}

ul {
	list-style: none;
}

.clear {
	clear: both;
}

.box {
	width: 1320px;
	margin: 0 auto;
}



#store-menu {
	/* border-top: solid 1px #dddddd; */
	border-bottom: solid 1px #dddddd;
	font-size: 13px;
	padding-bottom: 48px;
	padding-top: 20px;
	background-color: #ffffff;
}


#store-menu li {
	display: inline;
	font-size: 20px;
	margin-left: 50px;
	float: left;
}

#store {
	cursor:pointer;
}
.title {
	padding-top: 8px;
	margin-left: 30px;
}

#content-box h3 {
	
	text-align:center;
}

#content-box {
	padding-top: 25px;
	padding-bottom: 85px;
}

#content-box h3 {
	margin-top: 10px;
}

.items {
	width: 232px; /* 사진 가로 크기 */
	float: left;
	margin: 40px 90px 30px 70px;
	margin-left: 100px;
}

.items .a {
	font-weight: bold;
	color: black;
	margin-top: 15px;
}

.items .b {
	color: black;
	margin-top: 8px;
}

.btn {
	width: 40px;
	color: #252331;
	font-weight: bold;
	font-size: 16px;
	cursor: pointer;
}
.items .c {
	font-weight: bold;
	color: black;
	margin-top: 22px;
}
.items .d {
	font-weight: bold;
	color: black;
	margin-top: 47px;
}
.items .e {
	font-weight: bold;
	color: black;
	margin-top: 1px;
}


#store-menu ul li:nth-child(n+2)  {
	margin-top: 6px;
}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp" />

	<nav id="store-menu">
		<div class="box">
			<ul>
				<li><h2 id="store">스토어</h2></li>
				<li class="btn" id="popcorn">팝콘</li>
				<li class="btn" id="drink">음료</li>
				<li class="btn" id="snack">스낵</li>
			</ul>
		</div>
	</nav>

	<section id="content-box">
		<div class="box">
			<h3>BEST</h3>
			<div class="clear"></div>
			<ul class="items">
				<li><img src="/MovieIrum/store/img/combo.jpg" width="380px"
					alt="combo 11,000" onerror="noImg(this)"></li>
				<li class="c">콤보</li>
				<li class="b"><span> 11,000 </span> <em>Won</em></li>
			</ul>
			
			
			<ul class="items">
				<li><img src="/MovieIrum/store/img/ticket.jpg" width="350px"
					alt="ticket 11,000" onerror="noImg(this)"></li>
				<li class="d">주중 관람권</li>
				<li class="b"><span> 11,000 </span> <em>Won</em></li>
			</ul>

			<ul class="items">
				<li><img src="/MovieIrum/store/img/cheese.jpg" width="350px"
					alt="cheese L-6,000 & M-5,000" onerror="noImg(this)"></li>
				<li class="a">치즈맛 팝콘</li>
				<li class="b"><span> L - 6,000 & M - 5,000 </span> <em>Won</em></li>
			</ul>

			<ul class="items">
				<li><img src="/MovieIrum/store/img/caramel.jpg" width="350px"
					alt="caramelPopcorn L-6,000 & M-5,000" onerror="noImg(this)"></li>
				<li class="a">카라멜맛 팝콘</li>
				<li class="b"><span> L - 6,000 & M - 5,000 </span> <em>Won</em></li>
			</ul>


			<ul class="items">
				<li><img src="/MovieIrum/store/img/sweet.jpg" width="350px"
					alt="sweetPopcorn L-6,000 & M-5,000" onerror="noImg(this)"></li>
				<li class="a">달콤한맛 팝콘</li>
				<li class="b"><span> L - 6,000 & M - 5,000 </span> <em>Won</em></li>
			</ul>
			<ul class="items">
				<li><img src="/MovieIrum/store/img/coke.jpg" width="400px"
					alt="originalPopcorn L-6,000 & M-5,000" onerror="noImg(this)"></li>
				<li class="e">콜라</li>
				<li class="b"><span> L - 4,000 & M - 2,500 </span> <em>Won</em></li>
			</ul>

			
		</div>
		<div class="clear"></div>
	</section>

	
	<jsp:include page="../include/footer.jsp" />
</body>
</html>