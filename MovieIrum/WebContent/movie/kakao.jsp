<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet" href="\MovieIrum\css/style.css?after" type="text/css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
</head>
<body class="background">
<jsp:include page="../include/header.jsp"/>
	<p style="height: 1000px;"></p>
    <script>
    $(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp10567562'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '결제테스트',
        	amount : ${sessionScope.price},
        	buyer_email : 'iamport@siot.do',
        	buyer_name : '구매자',
        	buyer_tel : '010-1234-5678',
        	buyer_addr : '서울특별시 강남구 삼성동',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
        	if ( rsp.success ) {
        		alert("결제 되었습니다!");
        		location.href="update.client";
        	} else {
        		alert("결제에 실패하였습니다.");
        		location.href="/MovieIrum/index.jsp";
        	}
        });
        
    });
    </script>
 <jsp:include page="../include/footer.jsp"/>
</body>
</html>