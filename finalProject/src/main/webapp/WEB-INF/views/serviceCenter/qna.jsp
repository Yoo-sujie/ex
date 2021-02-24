<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Header -->
<jsp:include page="/header.jsp" />
<script type="text/javascript" src="/resources/lib/js/qna.js"></script>

<link rel="stylesheet" href="/resources/lib/css/serviceCenter.css" />

<script type="text/javascript"> 
function access(){ //로그인이 안되어있을때, '로그인이 필요한 작업입니다'
	var loginId = '${sessionScope.loginId}';
	if (loginId == "") {
		alert("로그인이 필요한 작업입니다");
		document.location.href = '/serviceCenter/qnaPageMove.do';
	} else if(loginId != ""){
		document.location.href = '/serviceCenter/qnaWritePageMove.do';
	}
}
</script>
<h3>묻고답하기</h3>

<!-- ChatBot -->
<%-- <jsp:include page="/WEB-INF/views/serviceCenter/chatBot.jsp" /> --%>

<!-- Contents -->
<div class="optionArea">
	<a class="actionBtn fright" onclick="access()">문의하기</a>
</div>
<table class="listBoard" id="qnaTable">
	<colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="10%"/>
        <col width="20%"/>
        <col width="15%"/>
    </colgroup>
    <thead id="qnaTh1">
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
            <th scope="col">처리상태</th>
        </tr>
    </thead>
	<tbody id="qnaTd1">
		<!-- 리스트 자동 생성 -->
	</tbody>
</table>

<!-- Pagination -->
<nav aria-label="Page navigation" class="page">
	<ul class="pagination justify-content-center" id="PageWrap">
		<!-- 페이저 자동 생성  -->
	</ul>
</nav>
	
<!-- Footer -->
<jsp:include page="/footer.jsp" />