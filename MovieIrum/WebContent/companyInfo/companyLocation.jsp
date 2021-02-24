<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Movie Irum</title>
<style>
body {
   font-family : "맑은 고딕";
   color : #000000;
}

.clear {
   clear: both;
}

#infoBar {
   border-bottom: solid 1px #dddddd;
    padding-bottom: 10px;
    padding-top: 10px;
    background-color: #ffffff;
}

.title {
   width : 1320px;
   margin : 0 auto;
   color : #4a4239;
}

#content-box {
   padding-top : 10px;
}

.jido {
   width : 50%;
   float: left;
    
}

.jido > h3 {
   margin-top : 30px;
   margin-left : 200px;
}

#jido1 {
   margin-left : 100px;
   margin-bottom : 60px;
}

.info {
   width : 40%;
   float : left;
}

.info2 {
   margin-top : 250px;
   margin-bottom : 100px;
}

</style>
<link rel="stylesheet" href="/MovieIrum/css/style.css">
<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=79bba6f5d745e534494bd21b517ec92e"></script>
<script>
   window.onload = function() {
         var joongang1 = new daum.maps.LatLng(37.570985, 126.992504);
         var mapDesign1 = {
               zoom:15,
               center : joongang1,
               mapTypeId : daum.maps.MapTypeId.ROADMAP
         }
         var map = new daum.maps.Map(document.getElementById("jido1"), mapDesign1);
      	// 마커를 생성합니다
 		var marker = new daum.maps.Marker({
 			position: joongang1
 		});
 		
 		// 마커가 지도 위에 표시되도록 설정합니다
 		marker.setMap(map);
   }
</script>
</head>
<body>
<jsp:include page="/include/header.jsp"/>
   <nav id = "infoBar">
      <div class = "title">
         <h2>회사 위치 안내</h2>
      </div>
   </nav>
   
   <section id = "contentBox">
      <div class = "clear"></div>
         <div class="jido">
            <h3>회사 위치 : 서울특별시 종로구 묘동 56</h3>
            <div id="jido1" style = "width : 600px; height : 600px;"></div>
         </div>
         
         <div class = "info">
            <div class = "info2">
               <h1>주변 교통 안내</h1>
               <h3>
                  1호선- 종로3가역 10번 출구에서 장갑성애플치과의원 방면<br><br>
                  3호선- 종로3가역 9번 출구, 2-1번 출구 맞은편<br><br>
                  버스-  12번 버스 - 묘동 정류장에서 하차<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        04, 101, 103, 140번 버스 - 와룡동 정류장에서 하차<br>
               </h3>
            </div>
         </div>
         <div class="clear"></div>
   </section>



<jsp:include page="/include/footer.jsp"/>
</body>
</html>