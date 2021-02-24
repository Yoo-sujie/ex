<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Header -->
<jsp:include page="/header.jsp" />

<script type="text/javascript" src="/resources/lib/js/qna.js"></script>
<script type="text/javascript" src="/resources/lib/js/cmt.js"></script>
<link rel="stylesheet" href="/resources/lib/css/serviceCenter.css" />

<script type="text/javascript"> 
	//해당 게시글의 작성자의 아이디와 로그인한 회원의 이름이 같은 경우와 
	//회원의 상태가 관리자일 경우에만 페이지 이동, 그 외에는 메세지창 출력
	var loginId = '${sessionScope.loginId}';
	var writer = '${map.SC_QNA_WRITER}';
	var status = '${loginStatus}';
	if (status != 9) { //관리자가 아닐 경우
		if (loginId != writer || loginId == "") { //로그인하지않았거나 작성자가 아닌경우
			alert("해당 게시물에 대한 권한이 없습니다.");
			document.location.href = '/serviceCenter/qnaPageMove.do'; 
		}
	}
</script>

<h3>묻고답하기</h3>

<!-- ChatBot -->
<%-- <jsp:include page="/WEB-INF/views/serviceCenter/chatBot.jsp" /> --%>

<!-- Contents -->
<div class="optionArea">
	<a class="actionBtn fleft" onclick="history.back(-1);">뒤로가기</a>
<c:if test="${loginStatus eq 9}">
	<a class="actionBtn fright" href="qnaDelete.do?no=${map.SC_QNA_NO}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제하기</a>
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
           <td><input type="hidden" name="no" id="boardNumber" value="${map.SC_QNA_NO}"/>${map.SC_QNA_TITLE}</td>
           <th>작성자</th>
           <td>${map.SC_QNA_WRITER}</td>
       </tr>
       <tr>
           <th>작성일</th>
           <td>${map.SC_QNA_WRITE_DATE}</td>
           <th>처리상태</th>
           <td id = "status">${map.SC_QNA_STATUS}</td>
       </tr>
       <tr>
           <th>내용</th>
           <td colspan="3">
               ${map.SC_QNA_CONTENTS}
            </td>
        </tr>
    </tbody>
</table>
<c:if test="${comment == null}">
	<c:if test="${loginStatus == 9}">
		<table class="cmtWriteBoard">
			<colgroup>
		        <col width="15%">
		        <col width="*">
		        <col width="15%">
			</colgroup>
			<tbody>
				<tr id="cmtForm">
					<th><input name="writer" id="commentWriter" type="hidden" value="${sessionScope.loginId}"/>관리자답변</th>
					<td><textarea name="content" id="commentCnt" cols="50" rows="5" placeholder="답변을 입력하세요."></textarea></td>
					<td><input class="actionBtn" id="commentBtn" type="button" value="작성완료"/></td>
				</tr>
			</tbody>
		</table>
	</c:if>
</c:if>
<table class="cmtBoard">
	<colgroup>
	    <col width="15%">
	    <col width="*">
	    <col width="15%">
	</colgroup>
	<tbody>
	<tr>
		<th colspan="3" class="alignLeft">답변</th>
	</tr>
	<tr id="cmtTr">
		<c:if test="${comment != null}">
			<!-- 답변이있을때 -->
			<td>${comment.SC_QNA_COMMENT_WRITER}</td>
			<td>${comment.SC_QNA_COMMENT_CONTENTS}</td>
			<td>${comment.SC_QNA_COMMENT_WRITE_DATE}</td>
		</c:if>
		<c:if test="${comment eq null}">
			<!-- 답변이없을때 -->
			<td colspan="3">작성된 답변이 없습니다.</td>
		</c:if>
	</tr>
 	</tbody> 
</table>
<!-- Footer -->
<jsp:include page="/footer.jsp" />