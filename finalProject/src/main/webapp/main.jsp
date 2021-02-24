<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/resources/lib/css/main.css" type="text/css">



<!-- 오늘하루 보지않음 쿠키 셋-->
<script type="text/javascript">
	//쿠키 셋팅
	function setCookie(name, value, expiredays) {
	    var todayDate = new Date();
	    todayDate.setDate(todayDate.getDate() + expiredays);
	    document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	    console.log('쿠키셋팅');
	}
	
	//쿠키 생성후 팝업 히든
	function popupClose() {
		setCookie("maindiv", "done", 1);
		document.all['popup'].style.visibility = "hidden";
	 	console.log('쿠키생성');
	}
</script>
<jsp:include page="/header.jsp" />

<div class="mainPage">

	<div class="mainContent mainContent1">
	
		<!-- 메인 팝업 -->
		<div id="popup">
			<a href="/serviceCenter/qnaPageMove.do">
	    		<img src="/resources/lib/images/main-popup(1).jpg" id="main_popup" class="main_popup"/>
	    		<br>
	    		<div class="popup_close">
	    			<a href="javascript:" onClick="popupClose()">
	    				오늘 하루 닫기
	    			</a>
	    		</div>
	    	</a>
    	</div>
    	
    	<!-- 메인 슬라이드 -->
    	<div>
	    	<div class="slide">
	    		<input type="radio" name="pos" id="pos1" checked>
		        <input type="radio" name="pos" id="pos2">
		        <input type="radio" name="pos" id="pos3">
		        <input type="radio" name="pos" id="pos4">
		        <ul>
		        	<li>
		        		<a href="/joinPage.do" class="main_slide">
		        			<img alt="main_img_0" src="/resources/lib/images/main-img(1).jpg" class="main_slide">
		        		</a>
		        	</li>
		            <li>
		            	<a href="/serviceCenter/noticePageMove.do" class="main_slide">
		            		<img alt="main_img_1" src="/resources/lib/images/main-img(2).jpg" class="main_slide">
		            	</a>
		            </li>
		            <li>
		            	<a href="/joinPage.do" class="main_slide">
		        			<img alt="main_img_0" src="/resources/lib/images/main-img(1).jpg" class="main_slide">
		        		</a>
		            </li>
		            <li>
		            	<a href="/serviceCenter/noticePageMove.do" class="main_slide">
		            		<img alt="main_img_1" src="/resources/lib/images/main-img(2).jpg" class="main_slide">
		            	</a>
		            </li>
		        </ul>
		        <p class="bullet">
		            <label for="pos1">1</label>
		            <label for="pos2">2</label>
		            <label for="pos3">3</label>
		            <label for="pos4">4</label>
		        </p>
	    	</div>
    	</div>
    	
    	<!-- 메인 페이지 -->
    	<div class="mainBack">
    		<img src="/resources/lib/images/main-img(3).jpg" class="main_img1"/>
    		<br>
    		
    		<a class="search_a" href="/search/searchPage.do">
    			<img src="/resources/lib/images/main-btn(1).jpg" class="main_img2 main_a"/>
    		</a>
    		<a class="map_a" href="/mapView.do">
    			<img src="/resources/lib/images/main-btn(2).jpg" class="main_img2 main_a"/>
    		</a>
    		<a class="service_a" href="/serviceCenter/qnaPageMove.do">
    			<img src="/resources/lib/images/main-btn(3).jpg" class="main_img2 main_a"/>
    		</a>
    		<a class="notice_a" href="/serviceCenter/noticePageMove.do">
    			<img src="/resources/lib/images/main-btn(4).jpg" class="main_img2 main_a"/>
    		</a>
    		
    		<br><br><br><br>
    		<br><br><br><br>
    		
    		<div>
    			<h1>다양한 물품을 만나보세요.</h1>
				<h3>가장 최근에 올라온 상품입니다.</h3>
    		</div>
    		
    		<a href="/search/searchPage.do" class="category_a">> 더보기 <</a>
			<div id = "search_div" class="search_div">
				<!-- 최근 올라온 제품 : 가장 최근에 올라온 제품 이미지랑 제목, 순서대로 뽑아서 카드모양으로! -->
				<c:forEach begin="1" end="8">
					<div class="search_div2">
						<a href=""> <!-- 클릭 시 링크 설정 -->
							<div class="card">
								<!-- 카드 헤더 -->
								<div class="card-header">
									<div class = "card-header-is_closed" >
					                	<div class = "card-header-text" >NEW</div>
					            	</div>
					            	<img src="/resources/lib/images/main-test(1).jpg"/>
								</div>
								
						
								<!--  카드 바디 -->
								<div class="card-body">
								
									<!--  카드 바디 헤더 -->
									<div class="card-body-header">
										<h1>판매 제목</h1>
										<p class="card-body-hashtag">
											#지역 #카테고리 #가격
										</p>
									</div>
									
									<!-- 카드 바디 본문 -->
									<!-- <p class="card-body-description">
										작성자: 닉네임
									</p> -->
									
									<!--  카드 바디 푸터 -->
									<div class="card-body-footer">
										<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">
										<i class="icon icon-view_count"></i>조회 --회
										<i class="icon icon-comments_count"></i>댓글 --개
										<i class="reg_date"> 2018/04/12 </i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			
		</div>
		
	</div>
	
	<br><br><br><br>
   	<br><br><br><br>
	
	<div class="mainContent2">
		<div>
			<img src="/resources/lib/images/main-img(4).jpg" class="main_img3"/>
		</div>
	</div>
	
	<br><br><br><br>
   	<br><br><br><br>
   	
   	<!-- 최근 리뷰 -->
   	<div class="mainContent mainContent1">
   		<div class="mainBack">
			<h1>리박스 구매 리뷰</h1>
			<div class="search_div_2" id="search_div_2">
				<!-- 최근 올라온 제품 : 가장 최근에 올라온 제품 이미지랑 제목, 순서대로 뽑아서 카드모양으로! -->
				<c:forEach begin="1" end="3">
					<div class="search_div3">
						<a href=""> <!-- 클릭 시 링크 설정 -->
							<div class="card2">
								<!-- 카드 헤더 -->
								<div class="card-header2">
					            	<img src="/resources/lib/images/main-test(1).jpg"/>
								</div>
								<!--  카드 바디 -->
								<div class="card-body">
									<img alt="star" src="/resources/lib/images/review/star/star5.png">
									<!--  카드 바디 헤더 -->
									<div class="card-body-header2">
										<h1>판매 제목</h1>
										<p class="card-body-hashtag">
											#지역 #카테고리 #가격
										</p>
									</div>
									
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
   	</div>
   	
   	<br><br><br><br>
   	<br><br><br><br>
	
	
	
</div>

<jsp:include page="/footer.jsp" />

<!-- 오늘하루 이창을 열지 않음 2-->
<script language="Javascript">
    cookiedata = document.cookie;
    if (cookiedata.indexOf("maindiv=done") < 0) {
        document.all['popup'].style.visibility = "visible";
    } else {
        document.all['popup'].style.visibility = "hidden";
    }

</script>