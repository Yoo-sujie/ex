<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp" />

<link rel="stylesheet" href="/resources/lib/css/login.css" type="text/css">

<script type="text/javascript" src="/resources/lib/js/main.js"></script>
<script type="text/javascript">
function setValues() {
	 var chooseEmail = document.getElementById("choose-email");
	 var email02 = document.getElementById("email02");
	email02.value = chooseEmail.options[chooseEmail.selectedIndex].text;
}
</script>

<div class="formBox join" id="form-box">
	<!-- href="/loginPage.do" -->
	<img src="/resources/lib/images/logo.png" alt="로고" />
	<h3>회원가입</h3>
	<form action="/signUp.do" method="post">
		<div class="field-wrap diff">
			<input type="text" name="id" id="id" required autocomplete="off"
				placeholder="ID *"> 
			<label class="checkId" id="checkId"></label>
			<!-- 아이디 중복 검사 -->
		</div>
		<div class="field-wrap">
			<input type="password" name="pw" id="pw" required
				autocomplete="off" placeholder="Password *">
		</div>
		<div class="field-wrap">
			<input type="password" name="pw2" id="pw2" required
				autocomplete="off" placeholder="Confirm:Password *"> 
			<label class="checkPw" id="checkPw"></label>
		</div>
		<div class="field-wrap">
			<input type="text" name="nickName" id="nickName" required
				autocomplete="off" placeholder="Nickname *"> 
			<label class="checkNick" id="checkNick"></label>
		</div>
		<div class="field-wrap">
			<input type="text" name="name" id="name" required
				autocomplete="off" placeholder="Name *">
		</div>
		<div class="field-wrap">
			<input type="text" name="phone" id="phone" required
				autocomplete="off" maxlength="13" placeholder="Phone *">
			<label class="checkPhone" id="checkPhone"></label>
		</div>
		<div class="field-wrap diff">
			<input type="text" name="email01" id="email01" required
				autocomplete="off" placeholder="Email *"> 
			<span>@</span> 
			<input type="text" name="email02" id="email02" value="직접 입력" 
				onFocus="this.value=''; return true;">
			<select name="choose-email" id="choose-email" class="choose-email" onchange="setValues();">
				<option value="1" selected>직접 입력</option>
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="gmail.com">gmail.com</option>
				<option value="hanmail.net">hanmail.net</option> 
				<option value="nate.com">nate.com</option>
				<option value="hotmail.com">hotmail.com</option>
			</select>
			<label class="checkEmail" id="checkEmail"></label>
		</div>
		<button type="submit" class="actionBtn" id="btn_join">
			가입하기
		</button>
	</form>
	<!-- 
	Welcome Back! 
	<div id="login">
		<h1>Welcome Back!</h1>

		<form action="/" method="post">


			<div class="field-wrap">
				<label for="email2"> Email Address <span class="req">*</span>
				</label> <input type="text" id="email2" required autocomplete="off">
			</div>

			<div class="field-wrap">
				<label for="pwd2"> Password <span class="req">*</span>
				</label> <input type="text" id="pwd2" required autocomplete="off">
			</div>

			<p class="forgot">
				<a href="#">Forgot Password?</a>
			</p>

			<button type="submit" class="button button-block" id="btn_join"
				name="btn_join">Log In</button>

		</form>
	-->
</div>
<jsp:include page="/footer.jsp" />