<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body class="background">
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
        	amount : '${sessionScope.price}',
        	buyer_email : '${param.MEMBER_EMAIL}',
        	buyer_name : '${param.MEMBER_NAME}',
        	buyer_tel : '${param.MEMBER_PHONE}',
        	buyer_addr : '서울특별시 은평구 응암동',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
        	if ( rsp.success ) {
        		alert("결제 되었습니다!");
        		opener.document.location.reload();
        		location.href="/buyer/buyerUpdate.do";
        		
        	} else {
        		alert("결제에 실패하였습니다.");
        		opener.document.location.reload();
        		window.close();
        	}
        });
        
    });
</script>

</body>
</html>