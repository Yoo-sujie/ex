<%@page import="movie.bookingDAO.MovieBookingDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<style>
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

.seat {
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	border-top-right-radius: 5px; 
	border-bottom-right-radius: 5px;
	background-color:#6799FF;
	color: #FFFFFF;
	border: 0;
	width: 25px;
	height: 25px;
	align-content: center;
}
.screen {
	background-color: #EAEAEA;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	color: #6799FF;
	width: 100%;
	height: 50px;
	text-align: center;
	font-size: 30px;
}
.abc {
	color: #6799FF;
}
select { 
	border: 0;
	border-radius:0; /* 아이폰 사파리 보더 없애기 */ 
	-webkit-appearance:none; /* 화살표 없애기 for chrome*/ 
	-moz-appearance:none; /* 화살표 없애기 for firefox*/ 
	appearance:none; /* 화살표 없애기 공통*/ 
	width: 30px;
	height: 30px;
	text-align: center;
}
.pm_btn {
	width: 30px; height: 30px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	border-top-right-radius: 5px; 
	border-bottom-right-radius: 5px;
	background-color:#6799FF;
	color: #FFFFFF; border: 0px;
	align-content: center;
}
</style>
<link rel="stylesheet" href="css/style.css?after" type="text/css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
sessionStorage.setItem("sa", 0);
sessionStorage.setItem("sa2", 0);
sessionStorage.setItem("sa3", 0);
	var count = 0;
	function sessionSave(e, num) {
		console.log("e : " + $(e).val());
		console.log("num : " + num);
		var button1 = document.getElementById($(e).val());
		count ++;
		button1.style.backgroundColor = "#252331";
		JSON.stringify(sessionStorage.setItem("seat"+count, $(e).val()));
		/* if(button1.style.backgroundColor == "#252331") {
			alert("취소");
			button1.style.backgroundColor = "#6799FF";
			JSON.stringify(sessionStorage.setItem("seat"+count, null));
			count--;
		} else {
			count ++;
			button1.style.backgroundColor = "#252331";
			JSON.stringify(sessionStorage.setItem("seat"+count, $(e).val()));
		} */
		button1.disabled = true;
		console.log("button1 : " + button1);
		
		//console.log(JSON.stringify(sessionStorage.getItem("seat")));
		
	}
	function sessionSave2(e) {
		JSON.stringify(sessionStorage.setItem("sa", $(e).val()));
		console.log(JSON.stringify(sessionStorage.getItem("sa")));
	}
	function sessionSave3(e) {
		JSON.stringify(sessionStorage.setItem("sa2", $(e).val()));
		console.log(JSON.stringify(sessionStorage.getItem("sa2")));
	}
	function sessionSave4(e) {
		JSON.stringify(sessionStorage.setItem("sa3", $(e).val()));
		console.log(JSON.stringify(sessionStorage.getItem("sa3")));
	}
	function seatSave() {
		var	s1 = sessionStorage.getItem("seat1");		
		var	s2 = sessionStorage.getItem("seat2");		
		var	s3 = sessionStorage.getItem("seat3");		
		var	s4 = sessionStorage.getItem("seat4");		
		var	s5 = sessionStorage.getItem("seat5");		
		var	s6 = sessionStorage.getItem("seat6");		
		var	s7 = sessionStorage.getItem("seat7");		
		//var	s12 = sessionStorage.getItem("seat2");		
		var a2 = sessionStorage.getItem("sa");
		var a3 = sessionStorage.getItem("sa2");
		var a4 = sessionStorage.getItem("sa3");
		if(a2 == null) a2 = 0;
		if(a3 == null) a3 = 0;
		if(a4 == null) a4 = 0;
		if(Number(a2)+Number(a3)+Number(a4) >= 8) {
			alert("최대 인원수는 7명입니다!");
		} else if(count != Number(a2)+Number(a3)+Number(a4)) {
			alert("인원수랑 좌석이 맞지 않습니다!");
		} else if(s1 == "" && s2 == "" && s3 == "" && s4 == "" && s5 == "" && s6 == "" && s7 == "") {
			alert("좌석을 선택하세요!");
		} else if(a2+a3+a4 == 0) {
			alert("인원이 0명입니다. 다시 선택하세요!");
		} else {
		/* 	location.href="movie/movieInfo.jsp?seat1=" + s1 + "&seat2=" + s2 
					+ "&seat3=" + s3 + "&seat4=" + s4 + "&seat5=" + s5 + "&seat6=" + s6 + "&seat7=" + s7 
					+ "&sa=" + a2 + "&sa2=" + a3 + "&sa3=" + a4; */
			location.href="movieBooking.client?seat1=" + s1 + "&seat2=" + s2 
			+ "&seat3=" + s3 + "&seat4=" + s4 + "&seat5=" + s5 + "&seat6=" + s6 + "&seat7=" + s7 
			+ "&sa=" + a2 + "&sa2=" + a3 + "&sa3=" + a4;
		}

	}
	function cancel() {
		console.log("예매 취소");
		sessionStorage.invalidIteratorState;
		alert("예매가 취소되었습니다!");
		location.href="cancel.client";
	}
	
	function allreset() {
		console.log("초기화");
		count = 0;
		for(var i=0; i<=431; i++) {
			sessionStorage.setItem("sa", 0);
			sessionStorage.setItem("sa2", 0);
			sessionStorage.setItem("sa3", 0);
			var button3 = document.getElementsByName("seat")[i];
			button3.disabled = false;
			if(button3.style.backgroundColor == "gray") {
			} else {
				button3.style.backgroundColor = "#6799FF";
			}
		}
	} 
	var mywin
	function pop_open() {
		if(document.cookie.indexOf("popup=no")== -1)
				// window.open(팝업.html,별명(alias),필요인자들)메소드
			mywin = window.open("movie/movieAge.jsp","movieAge",
					"width=500, height=400, top=300, left=600")
					//옛날에는 광고 띄울때 fullscreen=yes 써서 닫기버튼 없앴음 / 지금은 사용안됨
					//★ url을 안보이게 하고싶다면 2개의 파일을 사용하는 것이 아니라 하나의 파일에 팝업창을 띄워줘야 한다
	} 
</script>
</head>
<%-- <c:choose>
<c:when test="${sessionScope.movieAge >= 15}">
	<body onload="pop_open()">
</c:when>
<c:otherwise>
	<body>
</c:otherwise>
</c:choose> --%>
<body>
	<c:if test="${sessionScope.movieName2 eq null || sessionScope.day eq null || sessionScope.time eq null}">
		<script>
			alert("값을 모두 선택하세요!");
			history.back();
		</script>
	</c:if>
	
	<jsp:include page="..\include\header.jsp"/>

	<div align="center" class="movie-title" style="padding: 20px 400px;">
	<br>
	<form action="movieSelectView.client">
	<p style="font-size: 30px;" align="left">좌석 선택하기</p>
	<hr>
	<div align="left">
		<table>
		<tr><td width="300px;">영화 제목 : ${sessionScope.movieName2}</td>
			<td rowspan="3" style="padding-bottom: 20px;"><input id="submit" type="reset" value="초기화" onclick="allreset()">
			 <input id="submit" type="submit" value="뒤로가기">
			</td>
		</tr>
		<tr><td>상영 날짜 : ${sessionScope.day}일</td></tr>
		<tr><td>영화 시간 : ${sessionScope.time}</td></tr>
		</table>
		<hr>
	</div>
	
	<p style="font-size: 20px;" align="left">관람인원선택</p>
	<p style="font-size: 15px; background-color: #EAEAEA; height: 50px; line-height: 50px;" align="left">
		성인 
		<select onclick="sessionSave2(this)">
			<option>0</option>
			<%
			  for(int i=1; i<=7; i++) {
			%>
			<option><%=i %></option>
			<%} %>
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;
		청소년 
		<select onclick="sessionSave3(this)">
			<option>0</option>
			<%
			  for(int i=1; i<=7; i++) {
			%>
			<option><%=i %></option>
			<%} %>
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;
		우대 
		<select onclick="sessionSave4(this)">
			<option>0</option>
			<%
			  for(int i=1; i<=7; i++) {
			%>
			<option><%=i %></option>
			<%} %>
		</select>
	</p>
	<div class="screen" style="line-height: 50px;"><strong>SCREEN</strong></div><br>
	<table border="0" height="520px;">
	<%	
		
		int a2 = 65; 
		char a = (char)a2;
		ArrayList<String> seatArr = (ArrayList<String>)session.getAttribute("seatArr");
	%>
	<% 
		if(seatArr.size() != 0) {
		for(int i=0; i<=15; i++) {
		%>
		<tr>
			<td class="abc"><strong><%=a%></strong></td>
			<%	int num;
				w: for(num=1; num<=6; num++) {
					for(String j : seatArr) {
					if(num==7) break w;
					String n = Character.toString(a)+ num;
					if(j.equals(n)) {%>
					<td><button id="<%=a%><%=num%>" name="seat" value="<%=a%><%=num %>" class="seat" type="button" disabled style="background-color: gray;"><%=num%></button></td>
					<%
					num++;}
					}if(num==7) break w;%>
					<td><button id="<%=a%><%=num%>" name="seat" value="<%=a%><%=num %>" class="seat" type="button" onclick="sessionSave(this,<%=num%>)"><%=num%></button></td>
				<%} %>
			<td width="20px;"></td>
			<%	int num2;
				w: for(num2=7; num2<=21; num2++) {
					for(String j : seatArr) {
					if(num2==22) break w;
					String n = Character.toString(a)+ num2;
					if(j.equals(n)) {%>
					<td><button id="<%=a%><%=num2%>" name="seat" value="<%=a%><%=num2 %>" class="seat" type="button" disabled style="background-color: gray;"><%=num2%></button></td>
					<%
					num2++;}
					}if(num2==22) break w;
					%>
					<td><button id="<%=a%><%=num2%>" name="seat" value="<%=a%><%=num2 %>" class="seat" type="button" onclick="sessionSave(this,<%=num2%>)"><%=num2%></button></td>
				<%} %>
			<td width="20px;"></td>
			<%	int num3;
				w: for(num3=22; num3<=27; num3++) {
					for(String j : seatArr) {
					if(num3==28) break w;
					String n = Character.toString(a)+ num3;
					if(j.equals(n)) {%>
					<td><button id="<%=a%><%=num3%>" name="seat" value="<%=a%><%=num3 %>" class="seat" type="button" disabled style="background-color: gray;"><%=num3%></button></td>
				<%num3++;}
					}if(num3==28) break w;%>
					<td><button id="<%=a%><%=num3%>" name="seat" value="<%=a%><%=num3 %>" class="seat" type="button" onclick="sessionSave(this,<%=num3%>)"><%=num3%></button></td>
				<%} %>
		</tr>
			
			<%
			a++;
			} %>
	<%		
		} else {
			for(int i=0; i<=15; i++) {%>
			<tr>
				<td class="abc"><strong><%=a%></strong></td>
				<%	int num;
					for(num=1; num<=6; num++) {%>
						<td><button id="<%=a%><%=num%>" name="seat" value="<%=a%><%=num %>" class="seat" type="button" onclick="sessionSave(this,<%=num%>)"><%=num%></button></td>
				<%}%>
				<td width="20px;"></td>
				<%	int num2;
					for(num2=7; num2<=21; num2++) {%>
						<td><button id="<%=a%><%=num2%>" name="seat" value="<%=a%><%=num2 %>" class="seat" type="button" onclick="sessionSave(this,<%=num2%>)"><%=num2%></button></td>
				<%}%>
				<td width="20px;"></td>
				<%	int num3;
					for(num3=22; num3<=27; num3++) {%>
						<td><button id="<%=a%><%=num3%>" name="seat" value="<%=a%><%=num3 %>" class="seat" type="button" onclick="sessionSave(this,<%=num3%>)"><%=num3%></button></td>
				<%}%>
			</tr>
		<%		a++;
			} 
		}%>
	</table>
		<input type="button" id="submit" value="예매하기" onclick="seatSave()">
		<input type="button" id="submit" value="예매취소" onclick="cancel()">
	</form>
	</div>
	<p style="height: 150px;"></p>
	<jsp:include page="..\include\footer.jsp"/>
	
</body>
</html>