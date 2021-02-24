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
        if(session.getAttribute("sessionID") == null) // 로그인이 안되었을 때
        { 
            // 로그인 화면으로 이동
            response.sendRedirect("Client/LoginForm.jsp");
        }
        else // 로그인 했을 경우
        { %>
        	<script>
        	alert("로그인 되었습니다.")
        	</script>
        	<% response.sendRedirect("/MovieIrum/index.jsp");%>
    
    
    
    
    <%} %>    



</body>
</html>