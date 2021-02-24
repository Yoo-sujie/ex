<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/header.jsp" />

<link rel="stylesheet" href="/resources/lib/css/manager.css" type="text/css">
<script type="text/javascript" src="/resources/lib/js/manager.js"></script>

<h3>승인 관리</h3>

<div class="optionArea">
	<select id='sellAcknowledgment'>
		<option value='0'>전체</option>
		<option value='1'>승인완료</option>
		<option value='2'>승인대기</option>
	</select>
	<select id='sellSearch'>
		<option value='0'>제목</option>
		<option value='1'>제목+내용</option>
		<option value='2'>내용</option>
		<option value='3'>판매자</option>
	</select> 
	<input type='text' id='sellSearchText'> 
	<input type ='date' id='firstSearchDate'>
		<p>~</p>
	<input type ='date' id='lastSearchDate'>
	<input type="button" id='sellSearchButton' value="검색">
</div>

<table class="listBoard" id="approvalList">
	<colgroup>
		<col width="10%">
		<col width="30%">
		<col width="20%">
		<col width="10%">
		<col width="20%">
	</colgroup>
	<thead>
		<tr>
			<th>글번호</th><!-- SELL_NO -->
			<th>제목</th><!-- SELL_TITLE -->
			<th>판매자</th><!-- MEMBER_NIC -->
			<th>승인여부</th><!-- SELL_ACKNOWLOGEDMENT -->
			<th>작성시간</th><!-- SELL_WRITE_DATE -->
		</tr>
	</thead>
	<tbody id="approvalListTd">
		<!-- Controller 에서 작업클래스(impl)로 부터 받아온 request를 가지고, view페이지(jsp)로 이동시킴
			 => view 페이지에서 request 안의 db정보를 출력
		 -->
		<c:if test="${alist eq null}">
			<tr>
				<td colspan="5">
					<h3 style="text-align: center;">승인 대기 중인 글이 없습니다</h3>
				</td>
			</tr>
		</c:if>
		<c:if test="${alist != null}">
			<c:forEach items="${alist}" var="approvalList">
				<tr>
					<td>${approvalList.sellNo}</td>
					<td class="left">${approvalList.sellTitle}</td>
					<td>${approvalList.memberNic}</td>
					<td>${approvalList.sellAcknowledgment}</td>
					<td>${approvalList.sellWriteDate}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>

<nav aria-label="Page navigation" class="page">
   <ul class="pagination justify-content-center" id="PageWrap">
   </ul>
</nav>

<jsp:include page="/footer.jsp" />