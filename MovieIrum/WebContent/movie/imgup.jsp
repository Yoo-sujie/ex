 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="movie.movieDAO.MovieDAO"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %>
<%@ page import="java.sql.*" %>

<%
 request.setCharacterEncoding("utf-8");
 String realFolder = "";
 String filename1 = "";
 int maxSize = 1024*1024*5;
 String encType = "utf-8";
 String savefile = "img";
 ServletContext scontext = getServletContext();
 realFolder = scontext.getRealPath(savefile);
 
 try{
  MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

  Enumeration<?> files = multi.getFileNames();
     String file1 = (String)files.nextElement();
     filename1 = multi.getFilesystemName(file1);
 } catch(Exception e) {
  e.printStackTrace();
 }
 
 String fullpath = realFolder + "\\" + filename1;
%>

<title>Movie Irum</title>
</head>
<body>
<%if(filename1 != null) {
String str2 = fullpath.substring(fullpath.indexOf("M"), fullpath.length());
String str3 = "\\"+ str2;
session.setAttribute("str3", str3);%>
	<script>
		sessionStorage.setItem("img", "img");
		alert("등록되었습니다!");
		history.back();
	</script>
<%
} else {%>
	<script>
		alert("이미지가 없습니다!");
		history.back();
	</script>
<%} %>

</body>
</html>

