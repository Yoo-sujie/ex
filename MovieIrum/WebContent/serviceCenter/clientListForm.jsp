<%@page import="java.sql.Timestamp"%>
<%@page import="client.clientDAO.ClientDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<title>Movie Irum</title>
<script src="/MovieIrum/js/jquery-3.3.1.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(document).delegate('#claimList tr', 'click', claimList);
		
	});

	function claimList() {
		console.log(this);
		var tr = $(this);
		var td = tr.children();
		var claimNo = td.eq(1).text();
		var user_id = td.eq(3).text();
		// var sessionId = ${sessionScope.sessionID};

		if (claimNo != '번호') {
			console.log(claimNo);
			console.log(user_id);

			location.href = "/MovieIrum/clientWrittenBoard.service?num="+ claimNo;
		}

	}
	
</script>
<style>
.faq {
	height: auto;
	padding: 25px 0;
	margin: auto;
	color: #ffffff;
	text-align: center;
}

.big {
	color: #33FF00;
	cursor: pointer;
}

.big:hover {
	color: rgb(254, 6, 145);
}

.style {
	padding: 25px 0;
	margin: auto;
	width: 840px;
	text-align: center;
}

table {
	width: 840px;
	padding: 10px 0;
	border-collapse: collapse;
}

th {
	background-color: #000000;
	color: white;
}

.tbody {
	background-color: #ffffff;
}

button {
	width: 100px;
	height: 40px;
	padding: 5px 12px;
	margin: 5px 0;
	border: none;
	background-color: #000000;
	color: white;
	cursor: pointer;
}

#button {
	width: 100px;
	height: 40px;
	padding: 5px 12px;
	margin: 5px 0;
	border: none;
	background-color: #000000;
	color: white;
	cursor: pointer;
}

a {
	text-decoration: none;
	color: black;
}
</style>

</head>



<body class="background">
	
	<script>
		$(function() { //toggle

			var soo = [ 1, 2, 3, 4 ];
			//0,1,2.. //1,2,3,4...   
			$.each(soo, function(index, value) { // =  for(var imsi in...) 
				//index는 자동으로 0,1,2.. 가져온다.
				//value는 soo에 1,2,3,4가 준비되어있기 때문에 이 값 들을 가져온다.
				$('#title' + value).click(function() {
					if ($('#stitle' + value).is(':hidden')) //hide 상태냐??
						$('#stitle' + value).show(); //보여줘
					else
						//show 상태면??
						$('#stitle' + value).hide(); //숨겨줘
				}); //click-end
			}); //each-end
		}); //$function-end
	</script>
	
	<jsp:include page="../include/header.jsp" />
	<div class="faq">
		<h1>FAQ</h1>

		<div class="big" id="title1">
			<h4>● 운영시간 임시 조정 안내</h4>
		</div>

		<div id="stitle1" style="display: none">
			코로나19 확산 예방에 동참하고자 운영시간이 임시 조정되오니<br> 홈페이지나 어플 상영시간표를 참고하시어 영화관
			이용 부탁드립니다.<br>
			<br> - 운영시간 조정 적용일 : 2020년 4월 1일(수) ~<br> (매표, 매점 운영시간 :
			13시 30분 ~ 20시 30분)
		</div>

		<div class="big" id="title2">
			<h4>● 영화예매 홈페이지상 영화예매 가능 시간은 언제까지 인가요?</h4>
		</div>

		<div id="stitle2" style="display: none">
			홈페이지상 영화예매는 상영시간 20분전 까지 가능하며,<br> 시간 안에 결제까지 모두 마쳐주셔야 합니다.
		</div>

		<div class="big" id="title3">
			<h4>● 제휴 예매처 예미 시 포인트 적립/본영화 등록이 가능한가요?</h4>
		</div>

		<div id="stitle3" style="display: none">
			제휴 예매처[펀무비, 인투더무비, happy30, STT VIP]에서 예매하신 경우<br> 예매자 정보와 무비이룸
			회원 정보가 일치 시 자동 적립 됩니다.<br> 본영화 등록은 극장에서 발권하신 티켓 하단의 온라인 예매번호
			12자리를 입력시 등록되며,<br> 관람이력에 포함되어 포토무비 제작이 가능합니다.
		</div>
	</div>
	<div class="style">

		<%-- ${sessionScope.SessionId} --%>

		<table id="claimList">
			<tr>
				<th width="100px">카테고리</th>
				<th width="40px">번호</th>
				<th width="150px">제목</th>
				<th width="100px">작성자</th>
				<th width="150px">날짜</th>
			</tr>

			<!-- boardList객체 가져와서 목록 만듦 -->

			<c:forEach var="article" items="${list}">
				<tbody class="tbody">
					<tr>
						<td>${article.categoryName}</td>
						<td>${article.claimNum}</td>
						<td>${article.clientTitle}</td>
						<td>${article.clientId}</td>
						<td>${article.clientClaimDate}</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<p>
			<!-- 글쓰기 버튼을 눌렀을 떄 .service로 넘겨줘야 할 값들
         카테고리, 유저 아이디, 유저 번호(관리자와 비교되는), 유저 이름, 시퀀스는 글 등록을 마칠 때 넘겨주나? 아니면 여기서 넘겨주나..? -->
			<a href="searchCategory_write.service"><button class="button">글쓰기</button></a>
			<a href="/MovieIrum/index.jsp"><button class="button">HOME</button></a>
		</p>
		<form></form>
	</div>

	<jsp:include page="../include/footer.jsp" />
</body>
</html>