<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Header -->
<jsp:include page="/header.jsp" />

<script type="text/javascript" src="/resources/lib/js/notice.js"></script>
<link rel="stylesheet" href="/resources/lib/css/serviceCenter.css" />

<h3>공지사항</h3>

<!-- ChatBot -->
<%-- <jsp:include page="/WEB-INF/views/serviceCenter/chatBot.jsp" /> --%>

<!-- Contents -->
<div class="optionArea">
	<c:if test="${loginStatus eq 9}">
		<a class="actionBtn fright" href="noticeWritePageMove.do">공지작성</a>
	</c:if>
</div>
<table class="listBoard" id="noticeTable">
	<colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="20%"/>
    </colgroup>
    <thead id="noticeTh1">
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
    </thead>
	<tbody id="noticeTd1">
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