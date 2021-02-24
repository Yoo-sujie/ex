<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	if(session.getAttribute("sessionID")==null) {
%>
	<script>
		alert("회원만 이용 가능합니다!");
		location.href="\MovieIrum\Client\LoginForm.jsp";
	</script>
<%} %>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Movie Irum</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script>
	/* function sessionSave(e) {
		const seatBtn = sessionStorage.getItem("seat");
		const seat = document.querySelector(".seat");
		const button = document.createElement("button");
		button.append(seatBtn);
		seat.append(button);
		console.log(seatBtn);
	} */
	function payment() {
		var point = Number($('#point').val());
		var point2 = Number($('#point2').val());
		var price = Number($('#price').val());
		var money = Number($('#money').val());
		
		if(point2 < point) {
			alert("남은 포인트를 확인해주세요!");
		} else if(money < point) {
			alert("결제금액보다 사용 포인트가 많습니다!");
		} else if(money == 0) {
			alert("예매 되었습니다!");
			location.href="update.client";
		} else {
			/* location.href="movieBooking.client?hidden='hidden'&mbVo=${mbVo}&point="+point; */
			location.href="movieBooking.client?hidden='hidden'&movieName=${mbVo.movieName}&date=${date}&movieTime=${mbVo.movieTime}&seat=${mbVo.seat}&point="+point
					+"&price="+price+"&point2="+point2;
		}
	}
	
	$(document).ready(function () {
	    $('#point').keyup(function() {
	    	var point = Number($('#point').val());
	    	if(point==null) $('#point').val(0);
	    	var money = Number($('#money').val());
	    	if($('#money').val()=="NaN") {
	    		alert("숫자만 입력 가능합니다!");
	    		$('#money').val(${param.sa*8000 + param.sa2*7000});
	    	} else {
	    		if(money>0) {
		        	if(point==0 || $('#money').val()=="NaN") $('#money').val(${param.sa*8000 + param.sa2*7000});
		        	else $('#money').val(${param.sa*8000 + param.sa2*7000} - point);
		        }
	    	}
	    });
	});
	
	function cancel() {
		console.log("예매 취소");
		alert("예매가 취소되었습니다!");
		location.href="cancel.client";
	}

</script>
<style type="text/css"> 

 input, button{ 
     border-radius: 6px; 
     border: 1px solid #dedede; 
     padding: 10px; 
     margin-top: 3px;
 } 
 
 #submit{ 
     width: 127px; 
     height: 48px; 
     text-align: center; 
     border: none; 
     margin-top: 20px; 
     cursor: pointer; 
 } 
 #submit:hover{ 
     color: #fff; 
     background-color: #252331; 
     opacity: 0.9; 
 } 
 #cancel { 
     width: 127px; height: 48px; 
     text-align: center; 
     border: none; 
     margin-top: 20px; 
     cursor: pointer; 
 } 
 #cancel:hover{ 
     color: #fff; 
     background-color: #252331; 
     opacity: 0.9; 
 }
 #font, #point2, #point,#money,#submit {
 	 font-size: 15px;
 }

</style>
<link rel="stylesheet" href="\MovieIrum\css\style.css?after" type="text/css">
<link rel="stylesheet" href="\MovieIrum\css/style2.css?after" type="text/css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
</head>
<body>

<jsp:include page="../include/header.jsp"/>
	<p style="height: 30px;"></p>
	<form class="movie-title">예매 확인
	<hr>
	<div class="ss" align="center">
	<table border="2">
		<tr>
			<td rowspan="7"><img src="${poster}" alt="${poster}" width="200px;" height="270px;"></td>
			<td colspan="2" id="font">${mbVo.movieName}</td>
		</tr>
		<tr>
			<td colspan="2" id="font">${mbVo.movieTime}</td>
		</tr>
		<tr>
			<td colspan="2" id="font">${date}</td>
		</tr>
		<tr>
			<td id="font">좌석</td>
			<td id="font">
			${mbVo.seat}
			</td>
		</tr>
		<tr>
			<td colspan="3" id="font">남은 포인트 : <input type="text" id="point2" value="${sessionScope.point}" disabled></td>
		</tr>
		<tr>
			<td colspan="3" id="font">포인트 사용 : <input type="text" id="point"></td>
		</tr>
		<tr>
			<td colspan="3" id="font">총 금액 : <input type="text" id="money" value="${param.sa*8000 + param.sa2*7000}" readonly="readonly"></td>
		</tr>
	</table>
		<input type="hidden" name="price" id="price" value="${param.sa*8000 + param.sa2*7000}">
		<input type="hidden" name="hidden" value="hidden">
		<input type="button" id="submit" value="결제하기" onclick="payment()">
		<input type="button" id="submit" value="예매취소" onclick="cancel()">
	</div>
	</form>
	<p style="height: 30px;"></p>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>