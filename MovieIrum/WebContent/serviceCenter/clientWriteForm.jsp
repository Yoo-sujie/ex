<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<title>Movie Irum</title>
	<script src="/MovieIrum/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(document).on('click', '#insertClaim', insertClaim);
		});
		
		function insertClaim() {
			var category = $("#category option:selected").val();
			var title = $("#title").val();
			var content = $("#content").val();
			var pw = $("#pw").val();
			var id = $("#id").val();
			
			console.log(title);
			console.log(content);
			console.log(pw);
			console.log(category);
			//location.href="/MovieIrum/insertClaim.service";
			location.href = "/MovieIrum/insertClaim.service?category="
					+ category + "&title=" + title + "&content="
					+ content + "&pw=" + pw;

		}
	</script>
<style>

/* html, body {
	padding: 0;
	margin: 0;
}
 */
* {
	margin: 4px 0;
}

.style {
	padding: 25px 0;
	margin: auto;
	width: 800px;
}

#svctitle {
	text-align: center;
	background-color: #000000;
	width: 800px;
	height: 20px;
	padding: 12px 0;
	color: white;
}

#table {
	width: 800px;
	margin: 25px 0;
	padding: 20px;
	border-collapse: collapse;
	color: white;
}

.cursor {
	
}

#category {
	width: 100px;
	height: 30px;
}

#title {
	width: 652px;
	height: 24px;
}

textarea {
	width: 794px;
	height: 400px;
}

#pw {
	width: 100px;
}

.btn {
	width: 130px;
	height: 40px;
	padding: 5px 12px;
	border: none;
	background-color: #000000;
	color: white;
	float: right;
	cursor: pointer;
	margin: 5px;
}

textarea {
	resize: none;
}
</style>
</head>
<body class="background">

	<jsp:include page="../include/header.jsp" />


	<div class="style">
		<div id="svctitle">
			<b>게시글 작성</b>
		</div>
		<div>
			<a href="serviceCenter.service"><button class="btn">게시판</button></a> <a
				href="/MovieIrum/index.jsp"><button class="btn">홈으로</button></a>
		</div>

		<form action="clientListForm.jsp" method="post">
			<table id="table">
				<tr>
					<td width="100px">카테고리</td>
					<td><select name="svcCategory" id="category">
							<c:forEach var="cg1" items="${cgArray}">
								<option value="${cg1.category}"><a
										href="searchCategory?category=${cg1.category}">${cg1.category}</a></option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="svcTitle" id="title" required />
					</td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="12" cols="50"
							name="svcContent" id="content" required></textarea></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="svcPW" id="pw" required /></td>
				</tr>
				<tr>
					<td align="center"><a href="#"><input type="button"
							value="작성" class="btn" id="insertClaim"></a></td>
					<td align="center"><input type="reset" value="다시 작성하기"
						class="btn"></td>
				</tr>
			</table>
		</form>

	</div>


	<jsp:include page="../include/footer.jsp" />

</body>
</html>