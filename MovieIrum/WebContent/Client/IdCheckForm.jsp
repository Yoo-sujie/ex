<%@page import="client.clientDAO.ClientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/MovieIrum/css/style.css?after" />
<style type="text/css">
* {
	color: white;
}
</style>
</head>
<body class="background">
<%	

String id = request.getParameter("id");
ClientDAO dao1 = new ClientDAO();
boolean result = dao1.IdCheck(id);

if(result){%>
<center>
<br><br><br>
<h4>이미 사용중인 아이디 입니다.</h4>
</center>
<%}else{ %>
<center>
<br><br><br>
<h4>입력하신<%=id %> 는 사용하실수 있습니다.</h4>
<script>
	opener.document.getElementById("IdUncheck").value = '<%=id %>';
</script>
</center>
<%}%>
</body>
</html>