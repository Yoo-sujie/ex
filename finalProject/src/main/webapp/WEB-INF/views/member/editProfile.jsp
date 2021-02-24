<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="/resources/lib/css/myPage.css" />
<script type="text/javascript" src="/resources/lib/js/editProfile.js"></script>
<script>

	$(document).ready(function() { 
		$('ul.myNav li a.myPage').siblings().removeClass('on');
		$('ul.myNav li a.myPage').addClass('on');
	});
	
	function popup() {
		var url = "creditPopup.do";
		var name = "creditPopup";
		var option = "width = 400, height = 300, top = 200, left = 600, location = no"
		window.open(url, name, option);
	}

	function setValues() {
		var chooseEmail = document.getElementById("choose-email");
		var email02 = document.getElementById("email02");
		email02.value = chooseEmail.options[chooseEmail.selectedIndex].text;
	}
	
</script>

<c:if test="${sessionScope.loginStatus eq 1 }">
	<div class="memberInfo">
		<button id="popup" name="popup" onclick="javascript:popup()">?</button>
		<c:choose>
			<c:when test="${credit>=500}">
				<div class="point">
					<span><strong>GOLD 등급</strong><br />(누적 점수 500점 이상 회원입니다.)</span>
				</div>
			</c:when>
			<c:when test="${credit>=400}">
				<div class="point">
					<span><strong>SILVER 등급</strong><br />(누적 점수 400점 이상 회원입니다.)</span>
				</div>
			</c:when>
			<c:when test="${credit>=300}">
				<div class="point">
					<span><strong>BLACK 등급</strong><br />(누적 점수 300점 이상 회원입니다.)</span>
				</div> 
			</c:when>
			<c:when test="${credit>=150}">
				<div class="point">
					<span><strong>WHITE 등급</strong><br />(누적 점수 150점 이상 회원입니다.)</span> 
				</div>
			</c:when>
			<c:when test="${credit<150}">
				<div class="point">
					<span><strong>BASIC 등급</strong><br />(누적 점수 150점 미만 회원입니다.)</span> 
				</div>
			</c:when>
		</c:choose>
	
		<ul class="cntGroup">
			<li>
				<button class="cntResult" id="sellBtn" name="sellBtn" onClick="location.href='SalesHistory.do'">${sellListCnt}</button>
				<label class="cnt">판매 횟수</label>
			</li>
			<li>
				<button class="cntResult" id="buyBtn" name="buyBtn" onClick="location.href='PurchaseHistory.do'">${buyListCnt}</button>
				<label class="cnt">구매 횟수</label> 
			</li>
			<li>				
				<button class="cntResult" id="reportBtn" name="reportBtn" onClick="location.href='myReportList.do'">${reportList}</button>
				<label class="cnt">신고받은 횟수</label> 
			</li>
		</ul>
	</div>
</c:if>

<form action="myPageUpdate.do" method="post">
	<table class="viewBoard">
		<tbody>
           	<tr>	
				<th>이름</th>
				<td colspan="5"><label class="name">${user.member_name}</label></td>
			</tr>
			<tr>
				<th>아이디</th> 
				<td colspan="5"><label id="id" name="id" class="id">${user.member_id}</label></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td colspan="5"><input type="password" name="pw" id="pw" class="pw" value="${user.member_pw}"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="confirm-pw" id="confirm-pw" class="confirm-pw"></td>
				<td colspan="5"><label class="checkPW" id="checkPW"></label></td>
				<!-- 비밀번호 대조 -->
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type="text" name="nick"	id="nick" class="nick" value="${user.member_nic}"/></td>
				<td colspan="5"><label class="checkNIC" id="checkNIC"></label></td>
				<!-- 닉네임 중복 검사 -->
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" id="phone" class="phone" name="phone" value="${user.member_phone}"></td>
				<td colspan="5"><label class="checkPhone" id="checkPhone"></label></td>
			</tr>
			<tr>
				<th>이메일</th> 
				<td><input type="text" name="email01" id="email01" value="${email1}"> @ </td>
				<td><input type="text" name="email02" id="email02" value="${email2}"></td>
				<td>
					<select name="choose-email" id="choose-email" class="choose-email" onchange="setValues();">
						<option value="1" selected>직접 입력</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="hotmail.com">hotmail.com</option>
					</select> 
				</td>
				<td><label class="checkEmail" id="checkEmail"></label></td>
			</tr>
			<tr>
				<th>가입날짜</th>
				<td colspan="5">
					<label class="join-date">
						<fmt:formatDate value="${user.member_join_date}" pattern="yyyy-MM-dd" />
					</label>
				</td>
			</tr>
			<tr class="lastChild">
				<td colspan="6"><button type="submit" class="updateBtn" id="btn-update">수정하기</button></td>
			</tr>
		</tbody>
	</table>
	
</form>