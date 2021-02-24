<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/lib/js/main.js"></script>
<script type="text/javascript">
	function setValues() {
		var selectEmail = document.getElementById("selectEmail");
		var writeEmail02 = document.getElementById("writeEmail02");
		writeEmail02.value = selectEmail.options[selectEmail.selectedIndex].text;
	}

	function search() {
		if ((pwsearch.writeId.value != null)
				&& (pwsearch.writeEmail01.value != null)
				&& (pwsearch.writeEmail02.value != null)) {
			pwsearch.submit();
		} else if (pwsearch.writeId.value == null) {
			alert("아이디를 입력하세요.")
		} else if (pwsearch.writeEmail01.value == null
				&& pwsearch.writeEmail02.value == null) {
			alert("이메일주소를 입력하세요.")
		}

	}
</script>

<jsp:include page="/header.jsp" />

<link rel="stylesheet" href="/resources/lib/css/login.css" type="text/css">

<div class="findWrap formBox">
	<form action="sendPw.do" method="post" name="pwsearch" id="pwsearch">
		<h3>비밀번호 찾기</h3>
		<span class="sub">가입 시 등록한 아이디와 이메일을 써주세요.</span>
		<div class="field-wrap">
			<input type="text" id="writeId" name="writeId" class="writeId" placeholder="ID"><br> 
		</div>
		<div class="field-wrap">
			<input type="text" id="writeEmail01" name="writeEmail01" class="writeEmail01" placeholder="E-mail" required> 
			<span>@</span>
			<input type="text" name="writeEmail02" id="writeEmail02" value="직접 입력" onFocus="this.value=''; return true;" required> 
			<select name="selectEmail" id="selectEmail" class="selectEmail" onchange="setValues();">
				<option value="1" selected>직접입력</option>
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="nate.com">nate.com</option>
				<option value="hotmail.com">hotmail.com</option>
			</select>
		</div>
		<button type="button" class="actionBtn" id="btnFindId" name="btnFindPw" onclick="search()">찾기</button>
	</form>
</div>
<jsp:include page="/footer.jsp" />