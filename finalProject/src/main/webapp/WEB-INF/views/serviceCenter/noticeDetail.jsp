<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Header -->
<jsp:include page="/header.jsp" />

<script type="text/javascript" src="/resources/lib/js/notice.js"></script>
<link rel="stylesheet" href="/resources/lib/css/serviceCenter.css" />	

<script type="text/javascript">
function deleteConfirm(){
	
	if(confirm("정말 삭제하시겠습니까?")) { 
	    document.location.href='/serviceCenter/noticeDelete.do?no=' + ${map.NOTICE_NO};
	} else {
	    alert("취소되었습니다.");
	    document.location.href='/serviceCenter/noticePageMove.do';
	}
}
</script>
<h3>공지사항</h3>

<!-- ChatBot -->
<%-- <jsp:include page="/WEB-INF/views/serviceCenter/chatBot.jsp" /> --%>

<!-- Contents -->
<div class="optionArea">
	<a class="actionBtn fleft" onclick="history.back(-1);">뒤로가기</a>
<c:if test="${loginStatus eq 9}">
<a class="actionBtn fright" onclick="deleteConfirm()">삭제하기</a>
</c:if>
</div>

   <table class="viewBoard">
       <colgroup>
        <col width="15%">
        <col width="35%">
        <col width="15%">
        <col width="*">
    </colgroup>
    <tbody>
        <tr>
            <th>제목</th>
            <td>${map.NOTICE_TITLE}</td>
            <th>조회수</th>
            <td>${map.NOTICE_CNT}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${map.NOTICE_WRITER}</td>
            <th>작성시간</th>
            <td>${map.NOTICE_WRITE_DATE}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td colspan="3">
                ${map.NOTICE_CONTENTS}
            </td>
        </tr>
    </tbody>
</table>

<!-- Footer -->
<jsp:include page="/footer.jsp" />