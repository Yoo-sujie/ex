<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>

</head>
<body>
<%
        session.invalidate(); // 모든세션정보 삭제
        
    %>
		<script>
        	alert("로그아웃 되었습니다.")
        	location.href="/MovieIrum/index.jsp";
        	</script>


</body>
</html>