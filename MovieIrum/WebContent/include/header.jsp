
<%@page import="client.clientDAO.ClientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/MovieIrum/js/jquery-3.3.1.js"></script>
<title>Movie Irum header</title>
<meta charset="UTF-8">
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {

			$(document).on('click', '#serviceCenter', serviceCenter);
			$(document).on('click', '#store', store);

		});

		function serviceCenter() {
			//서버 path이름 가져오는 거 나중에 참고
			location.href = "/MovieIrum/serviceCenter.service";
		}
		function store() {
			location.href = "/MovieIrum/store/store.jsp";
		}
		/* serviceCenter.client */
	</script>
	<!-- clientId hidden으로 보낼 때 id로 설정 -->
	<input type="hidden" id="sessionId" value="${sessionScope.id}">

 	<table border="0" class="logo">
  	<tr><td align="center" class="image blinking">
  		<a href="/MovieIrum/index.jsp"><img alt="header_logo" src="\MovieIrum\img\mi_logo_header.png" align="middle" width="150px;"></a>
  	</td></tr>
  	<tr><td align="center">
  		 <nav>
      <ul>
        <li class="dropdown">
          <div class="dropdown-menu">소개</div>
          <div class="dropdown-content">
            <a href="/MovieIrum/companyInfo/companyInfo.jsp">회사소개</a>
            <a href="/MovieIrum/companyInfo/companyLocation.jsp">위치안내</a>
          </div>
        </li>
        <li class="dropdown">
          <div class="dropdown-menu">예매</div>
          <div class="dropdown-content">
            <a href="/MovieIrum/movieSelectView2.client">영화소개</a>
            <a href="/MovieIrum/movieSelectView.client">좌석예매</a>
          </div>
        </li>
        <li class="dropdown">
          <div class="dropdown-menu" id="store">스토어</div>
        </li>
        <li class="dropdown">
          <div class="dropdown-menu" id="serviceCenter">고객센터</div>
        </li>
       <%  if(session.getAttribute("sessionID") != null) { %>
          <li class="login"><a href="/MovieIrum/Client/LogoutAction.jsp">로그아웃</a></li>
       <%} else {%>
        <li class="login"><a href="/MovieIrum/Client/LoginForm.jsp">로그인</a></li>
       <%} %>
        <li class="dropdown">
        <% ClientDAO dao = new ClientDAO();
           int level = dao.selectLevel((String)session.getAttribute("sessionID"));
           if(level == 0) {%>
           	 <div class="dropdown-menu">시스템 관리</div>
           	 <div class="dropdown-content">
           	 	<a href="/MovieIrum/movie/insertMovieView.jsp">영화등록</a>
           	 </div>
          <%} else {%>
          <%  if(session.getAttribute("sessionID") != null) { %>
          		<div class="dropdown-menu">마이페이지</div>
          		<div class="dropdown-content">
            	<a href="/MovieIrum/Mypage.do">나의 정보</a>
             	<a href="/MovieIrum/Client/MyDeleteForm.jsp">회원 탈퇴</a>
          		</div>
	     
	      <%} else {%>
	       <%} %>
	        <%} %>
        </li>
      </ul>
    </nav>
 	</td></tr>
 	</table>
</body>
</html>