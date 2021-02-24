<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/header.jsp" />


	<form method="post" action="/review/insertReview.do" enctype="multipart/form-data">
		<input type="hidden" name="seller_id" value="${param.seller_id}">
		<input type="hidden" name="sellNo" value="${param.sellNo}">
		구매자 : <input type="text" id="id" name = "id" value = "${sessionScope.loginId }" readonly="readonly">
		내용 : <input type="text" id="contents" name = "contents" value = "">
		별점 : <select id = "star" name = "star" style="width:150px">
				<option value="1"  selected="selected" background-image="/resources/lib/images/review/star/star1.png">★☆☆☆☆</option>
				<option value="2"  background-image="/resources/lib/images/review/star/star2.png">★★☆☆☆</option>
				<option value="3"  background-image="/resources/lib/images/review/star/star3.png">★★★☆☆</option>
				<option value="4"  background-image="/resources/lib/images/review/star/star4.png">★★★★☆</option>
				<option value="5"  background-image="/resources/lib/images/review/star/star5.png">★★★★★</option>


			 </select>
		<label>파일:</label> <input multiple="multiple" type="file"
			name="file1"> <input type="submit" value="upload">

	</form>


	<jsp:include page="/footer.jsp" />




</body>
</html>