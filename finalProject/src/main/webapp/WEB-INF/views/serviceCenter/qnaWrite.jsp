<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Header -->
<jsp:include page="/header.jsp" />

<script type="text/javascript" src="/resources/lib/js/qna.js"></script>
<link rel="stylesheet" href="/resources/lib/css/serviceCenter.css" />

<h3>묻고답하기</h3>

<!-- ChatBot -->
<%-- <jsp:include page="/WEB-INF/views/serviceCenter/chatBot.jsp" /> --%>

<!-- Contents -->
<form class="writeForm" action="qnaWrite.do" >
    <table class="writeBoard">
        <colgroup>
            <col width="20%">
            <col width="*">
        </colgroup>
        <tbody>
        	<tr>
        		<td>제목</td>
        		<td><input type="text" name="title" placeholder="제목을 입력하세요."/></td>
        	</tr>
        	<tr>
        		<td>작성자</td>
        		<td><input type="hidden" name="writer" value="${sessionScope.loginId}"/>${sessionScope.loginId}</td>
        	</tr>
        	<tr>
        		<td colspan="2">
        			 <textarea name="contents" cols="60" rows="40" placeholder="내용을 입력하세요."></textarea>
        		</td>
        	</tr>
        </tbody>
    </table>
    <input class="actionBtn" type="submit" value="작성완료"/>	
</form>

<!-- Footer -->
<jsp:include page="/footer.jsp" />