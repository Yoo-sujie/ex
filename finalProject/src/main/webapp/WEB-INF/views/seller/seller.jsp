<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/resources/lib/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/resources/lib/js/ajaxUtil.js"></script>
<!-- <script type="text/javascript">
$(document).ready(function() {
	console.log('sell');
	
	ajaxUtils = ajaxUtil();

	$(document).on('click', '#sellDetailView', sellDetailView);// 재입력 비밀번호 체크
	
	
	
	
});

function sellDetailView(){
	param = {
			id : 1
	}
	ajaxUtils.doPost('sell/sellDetail.do',param,sellDetail);
	
	
}

function sellDetail(data){
	console.log(data);
}

</script> -->
<body>
	<form action="/seller/sellPlaceSelectSi.do">
		<c:forEach var="vo" items="${voList}">
			<a href="/buyer/buyerContentView.do?sell_no=${vo.sell_no}">${vo.sell_title}</a><br>
		</c:forEach>
	<input type="submit" value="글작성">
	</form>
	<!-- 
	<input type = "button" id = "sellDetailView"value = "버튼" >	 -->
</body>
</html>