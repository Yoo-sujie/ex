<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/header.jsp" />
<style>
h3 {
	text-align: center;
	line-height: 80px;
	font-family: 'Jua', sans-serif;
}

table.viewBoard {
	border-collapse: collapse;
	width: 100%;
	max-width: 70%;
	margin: 0 auto;
	text-align: left;
}

table.viewBoard > tbody > tr > th, 
table.viewBoard > tbody > tr > td {
	padding: 15px;
	border-bottom: 1px solid #ddd;
	box-sizing: border-box;
}

table.viewBoard > tbody > tr > th {	background-color:#f5f5f5; }

table.viewBoard > tbody > tr:last-child > th,
table.viewBoard > tbody > tr:last-child > td {
	border-bottom: 0;
}

input.actionBtn {
    width: 80px;
    height: 30px;
    text-align: center;
    display: block;
    line-height: 30px;
    background-color: #f5f5f5;
    color: #333;
    border-radius: 0.5rem;
    border: 1px solid #ddd;
    box-shadow: none;
    text-decoration: none;
    cursor: pointer;
    transition: all 0.4s;
    font-size: 12px;
}

</style>

	<h3>승인대기요청</h3>
	<table class="viewBoard">
		<tr>
			<th id="title">글 번호</th>
			<td>${svo.sell_no}</td>
		</tr>
		<tr>	
			<th id="title">판매자</th>
			<td>${svo.member_nic}</td>
		</tr>
		<tr>	
			<th id="title">카테고리</th>
			<td>${svo.sell_category_name}</td>
		</tr>
		<tr>	
			<th id="title">제목 </th>
			<td>${svo.sell_title}</td>
		</tr>
		<tr>	
			<th id="title">내용</th>
			<td> ${svo.sell_contents}</td>
		</tr>
		<tr>
			<th id="title">판매 가격</th>
			<td>${svo.sell_price}</td>
		</tr>
		<tr>
			<th id="title">흥정 여부</th>
			<td>${svo.sell_bargain}</td>
		</tr>
		<tr>
			<th id="title">작성일</th>
			<td>${svo.sell_write_date}</td>
		</tr>
		<tr>
			<td id="approvalBtn">
				<a href="approvalFin.do?num=${svo.sell_no}">
					<c:if test = "${svo.sell_acknowledgment eq 'N'}">
						<input type="button" class="actionBtn" id='approvalButton' value="승인완료">
					</c:if>
				</a>
			</td>
		</tr>
	</table>
	<jsp:include page="/footer.jsp" />