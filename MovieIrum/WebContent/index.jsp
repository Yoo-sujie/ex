<%@page import="movie.movieVO.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="movie.movieDAO.MovieDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		MovieDAO mdao = new MovieDAO();
		ArrayList<MovieVO> arr1 = mdao.indexMovie("MOVIE_OPENING DESC");
		ArrayList<MovieVO> arr2 = mdao.indexMovie("MOVIE_STAR");
		ArrayList<MovieVO> arr3 = mdao.indexMovie("MOVIE_AGE");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Irum</title>

	<link rel="stylesheet" href="css/style.css?after" />
	<link rel="stylesheet" href="css/style-main.css?after" />
	<link rel="stylesheet" href="css/swiper.css?after" />
	<link rel="stylesheet" href="css/reset-main.css?after" />
	
	<!-- 파비콘 -->
	<link rel="shortcut icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/MovieIrum/img/favicon.ico" type="image/x-icon">
	<!-- <link rel="shortcut icon" href="#"> --> <!-- 기본 아이콘 -->

</head>
<body class="background">
	
	<jsp:include page="include/header.jsp"/>

	<p style="height: 130px;"></p>
	
	<!-- 배너 슬라이드 -->
	<section id="banner">
		<div class="slider">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide ss1">
						<div class="container">
							<div class="row">
								<h2>에이리언 : 침략<em>Alien : aggression</em></h2>
								<p>종말이 다가온다</p>
							</div>
						</div>
					</div>
					<div class="swiper-slide ss2">
						<div class="container">
							<div class="row">
								<h2>추억<em>Memory</em></h2>
								<p>그 시절 우리의 추억 속으로</p>
							</div>
						</div>
					</div>
					<div class="swiper-slide ss3">
						<div class="container">
							<div class="row">
								<h2>폭격<em>Bombing</em></h2>
								<p>미사일 폭격으로 폐허가 된 도시</p>
							</div>
						</div>
					</div>
				</div>
				<div class="swiper-pagination"></div>
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>
			</div>
		</div>
	</section>

	<section id="movie">
		<div class="container">
			<div class="row">
				<div class="movie">
					<h2 class="ir_so">영화 메뉴</h2>
					<div class="movie_title">
						<ul>
							<li class="active"><a href="#">최신 영화</a></li>
							<li><a href="#">추천 영화</a></li>
							<li><a href="#">인기 영화</a></li>
							<li><a href="#">추천 먹거리</a></li>
						</ul>
					</div>
					<div class="movie_chart">
					
						<!-- 최신 영화 -->
						<div class="swiper-container2">
							<div class="chart_cont1 swiper-wrapper">
							<%for(MovieVO vo : arr1) {%>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="<%=vo.getMoviePoster()%>" alt="movie"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong><%=vo.getMovieName()%></strong></h3>
										<%
											request.setAttribute("movieName", vo.getMovieName());
										%>
										<div class="infor_btn">
                          					<a href="movieContentView.client?movieName=<%=vo.getMovieName()%>" class="white"
	                              			onclick="window.open(this.href, '_blanck', 'width=750, height=480, left=550, top=270, status=no'); return false">상세 보기</a>
                           					<a href="movieSelectView.client" class="black">예매하기</a>
										</div>
									</div>
								</div>
							<%} %>
							</div>
						</div>
						
						<!-- 추천 영화 -->
						<div class="swiper-container2">
							<div class="chart_cont1 swiper-wrapper">
							<%for(MovieVO vo : arr2) {%>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="<%=vo.getMoviePoster()%>" alt="movie"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong><%=vo.getMovieName()%></strong></h3>
										<%
											request.setAttribute("movieName", vo.getMovieName());
										%>
										<div class="infor_btn">
                          					<a href="movieContentView.client?movieName=<%=vo.getMovieName()%>" class="white"
	                              			onclick="window.open(this.href, '_blanck', 'width=750, height=480, left=550, top=270, status=no'); return false">상세 보기</a>
                           					<a href="movieSelectView.client" class="black">예매하기</a>
										</div>
									</div>
								</div>
							<%} %>
							</div>
						</div>
						
						
						<!-- 상영 예정 -->
						<div class="swiper-container2">
							<div class="chart_cont1 swiper-wrapper">
							<%for(MovieVO vo : arr3) {%>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="<%=vo.getMoviePoster()%>" alt="movie"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong><%=vo.getMovieName()%></strong></h3>
										<%
											request.setAttribute("movieName", vo.getMovieName());
										%>
										<div class="infor_btn">
                          					<a href="movieContentView.client?movieName=<%=vo.getMovieName()%>" class="white"
	                              			onclick="window.open(this.href, '_blanck', 'width=750, height=480, left=550, top=270, status=no'); return false">상세 보기</a>
                           					<a href="movieSelectView.client" class="black">예매하기</a>
										</div>
									</div>
								</div>
							<%} %>
							</div>
						</div>
						
						
						<!-- 추천 먹거리 -->
						<div class="swiper-container2">
							<div class="chart_cont2 swiper-wrapper">
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="/MovieIrum/store/img/combo.jpg" alt="food"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong>콤보</strong></h3>
										<div class="infor_food">
											<span> 11,000 </span> <em>Won</em>
										</div>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="/MovieIrum/store/img/cheese.jpg" alt="food"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong>치즈맛 팝콘</strong></h3>
										<div class="infor_food">
											<span> L - 6,000 & M - 5,000 </span> <em>Won</em>
										</div>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="/MovieIrum/store/img/caramel.jpg" alt="food"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong>카라멜맛 팝콘</strong></h3>
										<div class="infor_food">
											<span> L - 6,000 & M - 5,000 </span> <em>Won</em>
										</div>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="/MovieIrum/store/img/sweet.jpg" alt="food"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong>달콤한맛 팝콘</strong></h3>
										<div class="infor_food">
											<span> L - 6,000 & M - 5,000 </span> <em>Won</em>
										</div>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="/MovieIrum/store/img/original.jpg" alt="food"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong>갈릭맛 팝콘</strong></h3>
										<div class="infor_food">
											<span> L - 6,000 & M - 5,000 </span> <em>Won</em>
										</div>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="poster">
										<figure>
											<img src="/MovieIrum/store/img/coke.jpg" alt="food"/>
										</figure>
									</div>
									<div class="infor">
										<h3><strong>콜라</strong></h3>
										<div class="infor_food">
											<span> L - 4,000 & M - 2,500 </span> <em>Won</em>
										</div>
									</div>
								</div>
								
							</div>
						</div>
								
						<!-- movie_chart-end -->
						
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<p style="height: 55px;"></p>
	
	<!-- 이벤트 -->
	<section id="event">
        <div class="container">
            <div class="row">
                <div class="event">
                    <h2>진행 중인 이벤트</h2>
                    <div class="event_left">
                        <div class="event_slider">
                            <img src="img/event01.jpg" alt="이벤트1">
                        </div>
                        <div class="event_box1">
                            <img src="img/event02.jpg" alt="이벤트2">
                        </div>
                        <div class="event_box2">
                            <img src="img/event03.jpg" alt="이벤트3">
                        </div>
                    </div>
                    <div class="event_right">
                        <img src="img/event04.gif" alt="이벤트4">
                    </div>
                </div>
            </div>
        </div>
    </section>

	<!-- 동영상 -->    
    <section id="video">
        <div class="container">
            <div class="row">
                <div class="video">
                    <h2>이달의 기대작</h2>
                    <div class="video_left">
                    	<video id="vid" controls autoplay loop muted>
                    		<!-- muted : 음소거(크롬은 muted를 사용해야 autoplay가 실행됨) -->
							<source src="video/Film.mp4" type="video/mp4">
						</video>
                    </div>
                    <div class="video_right">
                   		<h3 class="video_title"><img src="img/19.jpg" alt="age" />장마</h3>
                        <span class="video_screening">2020년 8월 3일 개봉</span>
                        <br><br>
                    	<div class="review">
                    		<div class="video_header">공포 / 대한민국</div>
                    		<div class="video_body">53분 / 19세 이상 관람가</div><br>
                    		<div class="video_footer">
                    			평범한 중학생 김병만은 비가 오는 날이면 발작을 하는 옆집 아저씨가 무서워 비가 그칠 때까지 집에 들어가는 것을 피했다.
                    			화창한 날에 만나는 옆집 아저씨 최정식은 친절하고 자상한 성격이지만, 비가 내리는 날이면 항상 최정식의 알 수 없는 괴음이
                    			김병만의 방 안까지 울려 퍼진다. 그러던 어느 날 장마가 시작되는데...
	         				</div>
               			 </div>
               			 <div class="btn">
                            <a href="#" class="white">상세 보기</a>
                            <a href="#" class="black">사전 예약</a>
                        </div>
           			 </div>
       			 </div>
       		 </div>
       	 </div>
    </section>
	
	<script src="js/jquery-3.3.1.js"></script>
    <script src="js/swiper.min.js"></script>
	<script>
		//영화차트 탭 메뉴
	    var movBtn = $(".movie_title > ul > li");
	    var movCont = $(".movie_chart > div");
	    
	    movCont.hide().eq(0).show();
	    
	    movBtn.click(function(e){
	        e.preventDefault();
	        var target = $(this);
	        var index = target.index();
	        movBtn.removeClass("active");
	        target.addClass("active");
	        movCont.css("display","none");
	        movCont.eq(index).css("display","block");
	    });
        
     	//배너 이미지 슬라이드
        var mySwiper = new Swiper('.swiper-container', {
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            pagination: {
                el: '.swiper-pagination',
                type: 'bullets',
            },
            autoplay: {
                delay: 5000,
            }
        })
		
        
     	//영화 차트 이미지 슬라이드
        var mySwiper = new Swiper('.swiper-container2', {
            slidesPerView: 4,
            spaceBetween: 24,
            /* mousewheel: {
                invert: true,
            }, */
            keyboard: {
                enabled: true,
                onlyInViewport: false,
            },
            autoplay: {
                delay: 5000,
            }
            /* pagination : { // 페이징
        		el : '.swiper-pagination',
        		clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
        	}, */
        });
    </script>
	
	<jsp:include page="include/footer.jsp"/>
	
</body>
</html>