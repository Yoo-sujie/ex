<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/header.jsp" />

<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script> -->
<script type="text/javascript" src="/resources/lib/js/memberListPaging.js"></script>
<link rel="stylesheet" href="/resources/lib/css/manager.css" type="text/css">

<h3>회원 관리</h3>

<table class="listBoard" id="memberList">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>닉네임</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입날짜</th>
			<th>신고누적</th>				
			<th>관리</th>
		</tr>
	</thead>
	<tbody id="memberListTd">
		<c:if test="${MemberList != null}">
			<c:forEach items="${MemberList}" var="memberList">
				<c:if test="${memberList.member_nic != '관리자'}">
					<tr>
						<td>${memberList.member_id}</td>
						<td>${memberList.member_name}</td>
						<td>${memberList.member_nic}</td>
						<td>${memberList.member_phone}</td>
						<td>${memberList.member_email}</td>
						<td>${memberList.member_join_date}</td>
						<td>${memberList.sell_no}</td>
						<c:if test="${memberList.member_status eq 1 }">
							<td>
								<input type="button" id='manageBtn' class="actionBtn" value="회원정지" 
								onclick='location.href="stopMember.do?id=${memberList.member_id}"'>
							</td>
						</c:if>
						<c:if test="${memberList.member_status eq 2 }">
							<td>
								<input type="button" id='manageBtn2' class="actionBtn" value="정지해제"
								onclick='location.href="undoStopMember.do?id=${memberList.member_id}"'>
							</td>
						</c:if>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</tbody>
</table>

<input type="hidden" id="totalCnt" value="${totalCnt }">

<nav aria-label="Page navigation" class="page">
	<ul class="pagination justify-content-center" id="PageWrap">
	</ul>
</nav>

<jsp:include page="/footer.jsp" />
