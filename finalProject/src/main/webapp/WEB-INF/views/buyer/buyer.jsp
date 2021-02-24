<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/lib/css/buyer.css?after"
	type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/resources/lib/js/ajaxUtil.js"></script>
<script type="text/javascript" src="/resources/lib/js/buyer.js"></script>
<!-- <script type="text/javascript" src="/resources/lib/js/review.js"></script> -->

</head>
<body>


	<jsp:include page="/header.jsp" />
<script type="text/javascript" src="/resources/lib/js/review.js"></script>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div class="buyer_body">

		<input type="hidden" id="re_comment_list" value="${re_comment_list}">

		<!-- ******* 구매 하기 팝업창 ******* -->
		<%-- <div class="container" id="layer_2" style="display:none">
			<div id="popup">
				<a href="javascript:" onClick="popupClose()">
		    		<img src="/resources/lib/images/close.png" id="close"/>
		    	</a>
				<form action="/buyer/buyerPayment.do">
					<h1>잠깐 !!</h1>
					판매자와 먼저 대화 해보셨나요 ?<br>
					댓글과 채팅 기능을 통해 판매자랑 연락 후<br>
					구매 진행해주세요.<br>
					가격 : ${sellerVO.sell_price}원 <br>
					<input type="button" value="구매" onclick="buyerPayment(${sellerVO.sell_no}, ${sellerVO.sell_price});">
				</form>
			</div>
		</div> --%>
		<!-- ******* 구매 하기 팝업창 end ******* -->

		<!-- ******* 구매 후기 작성 팝업창 ******* -->
      <div class="review2" id="layer_3" style="height:0;">
         <div id="popup">
            <a href="javascript:" onClick="popupClose()">
                <img src="/resources/lib/images/close.png" id="close"/>
             </a>
             <div class="profile-left-nic profile-review">
               <!-- ******* 클릭 시 판매자 판매 기록 / 후기 / 별점 ******* -->
               <img src="/resources/lib/images/buyer-profile(1).png" alt="profile(1)"/>
               <br>
               <h3>판매자 : ${sellerVO.member_nic}</h3>
            </div> 
             <form name="insertReview" method="post" action="/review/insertReview.do" enctype="multipart/form-data">
               <input type="hidden" id="sell_seller_id" name="seller_id" value="${sellerVO.sell_seller_id}">
               <input type="hidden" id="id" name = "id" value = "${sessionScope.loginId }" readonly="readonly">
               <input type="hidden" name="sellNo" value="${sellerVO.sell_no}">
               <div class="profile-left-div">
                  ${sessionScope.loginId }
               </div>
               <div class="profile-left-div5">
               <table>
                  <tr>
                     <td>내용</td>
                     <td><input type="text" id="contents" name = "contents" value = ""></td>
                  </tr>
                  <tr>
                     <td>별점</td>
                     <td>
                        <select id = "star" name = "star">
                           <option value="1"  selected="selected" background-image="/resources/lib/images/review/star/star1.png">★☆☆☆☆</option>
                           <option value="2"  background-image="/resources/lib/images/review/star/star2.png">★★☆☆☆</option>
                           <option value="3"  background-image="/resources/lib/images/review/star/star3.png">★★★☆☆</option>
                           <option value="4"  background-image="/resources/lib/images/review/star/star4.png">★★★★☆</option>
                           <option value="5"  background-image="/resources/lib/images/review/star/star5.png">★★★★★</option>
                         </select>
                     </td>
                  </tr>
                  <tr>
                     <td>파일</td>
                     <td><input multiple="multiple" type="file" name="file1"></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <input type="submit" class="sell_click_btn" value="등록">
                     </td>
                  </tr>
               </table>
               </div>
               <!-- <input type="button" value="등록" onclick="insert_review();">-->
            </form>
         </div>
      </div>
      <!-- ******* 구매 후기 작성 팝업창 end ******* -->


		<!-- ******* 구매 후기 팝업창 ******* -->
		<%-- <div class="container" id="layer_3" style="display:none">
			<div id="popup">
		<table id='reviewTable'>
			<tr>
				<td>구매자</td>
				<td>${map.MEMBER_NIC }</td>
			</tr>
			<tr>
				<td>구매날짜</td>
				<td>${map.REVIEW_WRITE_DATE }</td>
			</tr>
	
			<tr>
				<td>평점</td>
				<td><img src="/resources/lib/images/review/star/star${map.REVIEW_RATING }.png" style="width: 50%;"></td>
			</tr>
			<tr>
				<td colspan="1">
					<div style="width: 300px; margin: 100px;">
						<!-- carousel를 사용하기 위해서는 class에 carousel와 slide 설정한다. -->
						<!-- carousel는 특이하게 id를 설정해야 한다.-->
						<div id="carousel-example-generic" class="carousel slide">
							<!-- carousel의 지시자 -->
							<!-- 지시자라고는 하는데 ol태그의 class에 carousel-indicators를 넣는다. -->
							<ol class="carousel-indicators">
								<!-- li는 이미지 개수만큼 추가하고 data-target은 carousel id를 가르킨다. -->
								<!-- data-slide-to는 순서대로 0부터 올라가고 0은 active를 설정한다. -->
								<!-- 딱히 이 부분은 옵션별로 설정하게 없다. -->
								<c:forEach var="list" items="${alist}" varStatus="status">
									<c:if test="${status.count eq 1}">
										<li data-target="#carousel-example-generic" data-slide-to="0"
											class="active"></li>
									</c:if>
	
									<c:if test="${status.count ne 1}">
										<li data-target="#carousel-example-generic" data-slide-to="1"></li>
									</c:if>
								</c:forEach>
	
	
							</ol>
							<!-- 실제 이미지 아이템 -->
							<!-- class는 carousel-inner로 설정하고 role은 listbox에서 설정한다. -->
							<div class="carousel-inner" role="listbox">
								<!-- 이미지의 개수만큼 item을 만든다. 중요한 포인트는 carousel-indicators의 li 태그 개수와 item의 개수는 일치해야 한다. -->
	
								<c:forEach var="list" items="${alist}" varStatus="status">
									<c:if test="${status.count eq 1}">
										<div class="item active">
											<img src="${list.REVIEWS_IMAGE }" style="width: 100%">
										</div>
									</c:if>
	
									<c:if test="${status.count ne 1}">
										<div class="item">
											<img src="${list.REVIEWS_IMAGE }" style="width: 100%">
										</div>
									</c:if>
								</c:forEach>
	
								<!-- <div class="item active">
						아미지 설정-
						<img
							src="https://tistory2.daumcdn.net/tistory/1041549/skin/images/nowonbuntistory.png"
							style="width: 100%">
						캡션 설정 (생략 가능)
					
	
					</div>
					<div class="item">
						<img src="https://www.nowonbun.com/img/nowonbuntistory1.png"
							style="width: 100%">
					</div> -->
	
	
	
							</div>
							<!-- 왼쪽 화살표 버튼 -->
							<!-- href는 carousel의 id를 가르킨다. -->
							<a class="left carousel-control" href="#carousel-example-generic"
								role="button" data-slide="prev"> <!-- 왼쪽 화살표 --> <span
								class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							</a>
							<!-- 오른쪽 화살표 버튼 -->
							<!-- href는 carousel의 id를 가르킨다. -->
							<a class="right carousel-control" href="#carousel-example-generic"
								role="button" data-slide="next"> <!-- 오른쪽 화살표 --> <span
								class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${map.REVIEW_CONTENTS }</td>
			</tr>
		</table>
		</div>
		</div> --%>
		<!-- ******* 구매 후기 팝업창 end ******* -->



		<!-- ******* 전체 div ******* -->
		<div class="container">

			<form action="/seller/sellUpdateView.do" method="post"
				onsubmit="return check()">

				<div class="profile2">
					<div class="profile-left">
						<div class="profile-left-nic">
							<!-- ******* 클릭 시 판매자 판매 기록 / 후기 / 별점 ******* -->
							<img src="/resources/lib/images/buyer-profile(1).png"
								alt="profile(1)" /> <br> <a href="#">${sellerVO.member_nic}
								판매자</a>
							<p class="profile-left-p">구매 전 판매자와 대화하세요.</p>
						</div>

						<div class="profile-left-div">
							<c:choose>
								<c:when test="${sellerVO.sell_status eq 0}">
									판매중
								</c:when>
								<c:when test="${sellerVO.sell_status eq 1}">
									구매중			
								</c:when>
								<c:when test="${sellerVO.sell_status eq 2}">
									구매완료	
								</c:when>
							</c:choose>
						</div>

						<br>
						<div class="profile-left-icon">
							<p class="profile-left-icon">
								<img src="/resources/lib/images/buyer-icon(1).png" alt="icon(1)" />
								<b>구매 하기 전에</b>
							</p>
							<p class="profile-left-p2">
								판매자와 연락하지 않으면 <br> 구매가 거절될 수 있습니다.
							</p>
						</div>
					</div>
				</div>

				<div class="profile2 profile2-1">
					<div class="profile-left-menu">상세정보</div>
					<br>
					<br>
					<div class="profile-left-p2">● 판매 장소 :
						${sellerVO.sell_place_name}</div>
					<div class="profile-left-p2">
						<c:choose>
							<c:when test="${sellerVO.sell_bargain eq 'N'}">
								● 흥정 불가능
							</c:when>
							<c:when test="${sellerVO.sell_acknowledgment eq 'Y'}">
								● 	흥정 가능
							</c:when>
						</c:choose>
					</div>
					<br>
					<br>
					<div class="profile-right">
						<div class="profile-left-pr">결제 금액 : ${sellerVO.sell_price}원
						</div>
						<br>
						<div class="profile-left-pr2">
							<c:if test="${sessionScope.loginId != null}">
								<c:choose>
									<c:when test="${sellerVO.sell_status eq 0}">
										<button type="button" class="profile-left-buy"
											onclick="buyerPayment(${sellerVO.sell_no}, ${sellerVO.sell_price});">
											구매하기</button>
									</c:when>
									<c:when
										test="${sellerVO.sell_status eq 2 && review_map != null}">
										<button type="button" class="profile-left-buy"
											onclick="review_select(${sellerVO.sell_no});">구매후기
											보기</button>
									</c:when>
									<c:when test="${buyerPaySelect eq 1}">
										<p class="profile-left-p">보관함 :
											${sellerVO.sell_place_item_no}번 / 비밀번호 : ${sellerVO.sell_pw}</p>
										<br>
										<button type="button" class="profile-left-buy"
											onclick="review_popup(${sellerVO.sell_no},'${sellerVO.sell_seller_id}');">
											구매후기 작성</button>
									</c:when>
								</c:choose>
							</c:if>
						</div>
					</div>
				</div>

				<c:if test="${sessionScope.loginId eq sellerVO.sell_seller_id}">
					<input type="button" value="삭제" class="sell_click_btn"
						onclick="delete_btn_click();">
					<input type="submit" value="수정" class="sell_click_btn">
					<c:choose>
						<c:when test="${sellerVO.sell_acknowledgment eq 'N'}">
							 ● 판매 승인 상태 : 미승인 
						</c:when>
						<c:when test="${sellerVO.sell_acknowledgment eq 'Y'}">
							 ● 판매 승인 상태 : 승인
						</c:when>
					</c:choose>
				</c:if>
				<input type="hidden" name="sell_no" id="sell_no"
					value="${sellerVO.sell_no}">
			</form>

			<hr>

			<div class="main">
				<div class="main-title">
					<!-- 판매 제목 -->
					<h3>${sellerVO.sell_title}</h3>
				</div>
				<div class="profile-left-p">
					${sellerVO.sell_category_name} &nbsp;●&nbsp;
					<!-- 판매 작성일 -->
					${sell_write_date}
				</div>
				<br>

				<!-- ******* 이미지 크기 width : 675px; height : 500px; ******* -->
				<div class="slide">
					<input type="radio" name="pos" id="pos1" checked> <input
						type="radio" name="pos" id="pos2"> <input type="radio"
						name="pos" id="pos3">
					<ul>
						<li><img alt="main_img_0" src="${main_img_0}"></li>
						<li><img alt="main_img_1" src="${main_img_1}"></li>
						<li><img alt="main_img_2" src="${main_img_2}"></li>
					</ul>
					<p class="bullet">
						<label for="pos1">1</label> <label for="pos2">2</label> <label
							for="pos3">3</label>
					</p>
				</div>

				<p class="slide_img">
					<label for="pos1"> <img alt="main_img_0"
						src="${main_img_0}" width="50px" height="50px">
					</label> <label for="pos2"> <img alt="main_img_1"
						src="${main_img_1}" width="50px" height="50px">
					</label> <label for="pos3"> <img alt="main_img_2"
						src="${main_img_2}" width="50px" height="50px">
					</label>
				</p>

				<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
				<br>

				<div class="main-content">
					<hr>

					<!-- 판매 내용 -->
					${sellerVO.sell_contents}

				</div>
				<div class="profile-left-p3">
					<div id="main-comment" class="main-comment">
						<!-- 댓글 -->
						댓글(${sellCommentCnt})/리뷰
					</div>
					<div class="main-check">
						<!-- 조회수 -->
						&nbsp;·&nbsp; 조회(${sellerVO.sell_cnt})
					</div>
				</div>
				<input type="hidden" id="totalCnt" value="${sellCommentCnt}" />
				<!-- **** 댓글 클릭 시 toggle **** -->
				<div id="main-comment-output" class="main-comment-output">
					<div id="comment">
						<!-- *** 댓글+답글 전체 리스트 *** -->
						<c:forEach var="comment" items="${commentList}">

							<!-- **** 댓글 리스트 **** -->
							<c:choose>
								<%-- <c:when test="${comment.sell_comment_status eq 1}">
									<c:if test="${comment.sell_comment_re_cnt != 0}">
										<div class="container">
										 	<table class="table table-bordered">
											 	<form>
												    <tbody>
											            <tr>
											            	<td>
											            		<br>
												       			삭제된 댓글입니다.
												       			<br><br>
												       			<a id="comment_re_a"
											                	onclick="comment_re_a(${comment.sell_comment_originno})">
											                	답글(${comment.sell_comment_re_total}개)
											                	</a>
												       			<br>
												       		</td>
												       	</tr>
												    </tbody>
												 </form>
											 </table>
									 	</div>
									</c:if>
								</c:when> --%>

								<c:when
									test="${comment.sell_comment_secret == 1 
									 	&& sessionScope.loginId != comment.sell_comment_writer
									 	&& sessionScope.loginId != sellerVO.sell_seller_id}">
									<c:if test="${comment.sell_comment_grouplayer == 0}">
										<div class="container">
											<table class="table table-bordered">
												<tbody>
													<tr>
														<td><br>
														<br> <img src="/resources/lib/images/secret.png"
															width="15px"> &nbsp; 비밀 댓글입니다. 작성자와 판매자만 볼 수 있습니다.
															<br>
														<br> &nbsp;&nbsp;&nbsp;&nbsp; <small>${comment.sell_comment_write_date_str}</small>
															<%-- 
												                <a id="comment_re_a" class="comment_re_a_${comment.sell_comment_no}"
											                	onclick="comment_re_a(${comment.sell_comment_originno})">
											                	답글(${comment.sell_comment_re_total}개)
											                	</a> --%> <br>
														<br></td>
													</tr>
												</tbody>
											</table>
										</div>
									</c:if>
								</c:when>

								<c:when
									test="${comment.sell_comment_grouplayer == 0 && comment.sell_comment_status != 1 }">
									<div class="container"
										id="comment_update_${comment.sell_comment_no}">
										<table class="table table-bordered">
											<thead>
												<th>
													<button class="comment_btn_writer" disabled="disabled">
														<c:choose>
															<c:when
																test="${sellerVO.sell_seller_id eq comment.sell_comment_writer}">
																<a class="seller_comment"> <img
																	src="/resources/lib/images/buyer-profile(1).png"
																	alt="profile(1)" class="profile_img_logo" />
																	${comment.sell_comment_writer_nic}
																	<div class="main-comment2">º 판매자</div>
																</a>
															</c:when>
															<c:otherwise>
																<img src="/resources/lib/images/buyer-profile(1).png"
																	alt="profile(1)" class="profile_img_logo" />
										       					${comment.sell_comment_writer_nic}
										       				</c:otherwise>
														</c:choose>
													</button> <c:if
														test="${sessionScope.loginId eq comment.sell_comment_writer}">
														<input type="button" class="comment_btn_a_1"
															id="comment_btn_u_${comment.sell_comment_no}"
															onclick="comment_delete(${comment.sell_comment_no});"
															value="삭제">
														<input type="button" class="comment_btn_a_1"
															id="comment_btn_d_${comment.sell_comment_no}"
															onclick="comment_update(${comment.sell_comment_no});"
															value="수정">
													</c:if>
												</th>
											</thead>
											<tbody>
												<tr>
													<td><br> <!-- (태) 수정 필요 --> <label
														id="comment_contents_${comment.sell_comment_no}">${comment.sell_comment_contents}</label>
														<small id="comment_date_${comment.sell_comment_no}">
															<br> <label
															id="comment_date_la_${comment.sell_comment_no}">${comment.sell_comment_write_date_str}</label>
															<br>
														<br> <c:if test="${sessionScope.loginId != null}">
																<input type="button" id="comment_re"
																	value="&nbsp;답글&nbsp;"
																	onclick="comment_re_click(${comment.sell_comment_no} , '${comment.sell_comment_originno}');"
																	class="comment_btn" />
															</c:if> &nbsp;&nbsp; <a
															id="comment_re_a_${comment.sell_comment_no}"
															onclick="comment_re_a(${comment.sell_comment_originno})">
																답글(${comment.sell_comment_re_total}개) </a>
													</small></td>
												</tr>
											</tbody>
										</table>
									</div>
								</c:when>
							</c:choose>
							<!-- **** 댓글 리스트 end **** -->

							<!-- **** 답글 작성 **** -->
							<div class="comment_re_form"
								id="comment_re_form_${comment.sell_comment_no}">
								<div class="container" id="comment_re_list">
									<table class="table table-bordered"
										style="background-color: #F6F6F6;">
										<thead>
											<th>ㄴ<img
												src="/resources/lib/images/buyer-profile(2).png"
												alt="profile(1)" class="profile_img_logo" /> ${member_nic}
											</th>
										</thead>
										<tbody>
											<form name="comment_re_form" method="post">
												<input type="hidden" name="sell_no"
													value="${sellerVO.sell_no}"> <input type="hidden"
													id="sell_comment_groupord" name="sell_comment_groupord"
													value="1"> <input type="hidden"
													id="sell_comment_originno" name="sell_comment_originno"
													value="${comment.sell_comment_originno}"> <input
													type="hidden" id="sell_comment_grouplayer"
													name="sell_comment_grouplayer" value="1">

												<tr>
													<td align="center"><textarea cols="160" rows="5"
															name="sell_comment_contents2"
															placeholder="${comment.sell_comment_writer_nic}님에게 답글 쓰기"
															required
															id="sell_comment_contents_${comment.sell_comment_no}"
															class="comment_textarea"></textarea></td>
												</tr>
												<tr>
													<td>
														<div class="comment_btn_group">
															<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;"
																class="comment_reset_btn" /> <input type="button"
																value="&nbsp;등록 &nbsp;"
																onclick="re_comment_insert(${comment.sell_comment_no}, ${comment.sell_comment_originno})"
																class="comment_insert_btn" />
														</div> &nbsp; <input type="checkbox"
														name="sell_comment_secret_r" value="1" checked="checked"
														disabled="disabled"> &nbsp; 비밀댓글
													</td>
												</tr>
											</form>
										</tbody>
									</table>
								</div>
							</div>
							<!-- **** 답글 작성 end **** -->

							<!-- **** 답글 리스트 **** -->
							<div id="comment_re_list_L_${comment.sell_comment_originno}"
								class="comment_re_list_${comment.sell_comment_originno}">
								<!-- 답글이라면 -->

							</div>
							<!-- **** 답글 리스트 end **** -->

						</c:forEach>
					</div>
					<!-- *** 댓글+답글 전체 리스트 end *** -->

					<input type="hidden" id="sessionId" value="${sessionScope.loginId}">
					<input type="hidden" id="sellerId"
						value="${sellerVO.sell_seller_id}"> <input type="hidden"
						id="pageHidden" value="">

					<!-- 페이징 -->
					<div class="cent">
						<nav aria-label="Page navigation" class="page">
							<ul class="pagination" id="PageWrap">

							</ul>
						</nav>
					</div>
					<!-- 페이징 end -->

					<!-- **** 댓글 작성 ****  -->
					<c:choose>
						<c:when test="${sessionScope.loginId == null}">
							<div class="container">
								<table class="table table-bordered">
									<thead>
									<tbody>
										<form action="write_ok.jsp" method="post"
											encType="multiplart/form-data">
											<tr>
												<td><br> <a href="/loginPage.do"> 댓글을 작성하려면 <strong><u>로그인</u></strong>
														해주세요.
												</a> <br> <br></td>
											</tr>
											<tr></tr>
										</form>
									</tbody>
									</thead>
								</table>
							</div>
						</c:when>
						<c:otherwise>
							<div class="container" id="comment_list">
								<form name="comment_form">
									<table class="table table-bordered">
										<thead>
											<th>&nbsp; <img
												src="/resources/lib/images/buyer-profile(1).png"
												alt="profile(1)" class="profile_img_logo" /> ${member_nic}
											</th>
										</thead>
										<tbody>
											<input type="hidden" name="sell_no"
												value="${sellerVO.sell_no}">
											<input type="hidden" name="sell_comment_groupord" value="0">
											<input type="hidden" name="sell_comment_grouplayer" value="0">
											<tr>
												<td align="center"><textarea cols="160" rows="5"
														name="sell_comment_contents"
														placeholder="댓글을 작성하세요.(악플과 욕설은 회원 정지 대상입니다.)&#13;&#10;"
														required id="sell_comment_contents"
														class="comment_textarea"></textarea></td>
											</tr>
											<tr>
												<td>
													<div class="comment_btn_group">
														<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;"
															class="comment_reset_btn" /> <input type="button"
															value="&nbsp;등록 &nbsp;" onclick="comment_insert()"
															class="comment_insert_btn" />
													</div> &nbsp; <input type="checkbox" name="sell_comment_secret"
													value="1" checked="checked" disabled="disabled">
													&nbsp; 비밀댓글
												</td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- **** 댓글 작성 end ****  -->

				</div>
				
				<div class="review" id= "review"
					style="padding: 10px; height: 60%; min-height: 100px;display: none;">
					<c:if test="${map ne null }">
						<div class="container">
						<div class="main">
							<div class="main-title">
								<!-- 판매 제목 -->
								<h3>[리뷰] : ${map.SELL_TITLE }</h3>
							</div>
							<div class="profile-left-p">
								구매자 : ${map.MEMBER_NIC } &nbsp;●&nbsp;
								<!-- 판매 작성일 -->
								${map.REVIEW_WRITE_DATE }
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								평점 : <img
									src="/resources/lib/images/review/star/star${map.REVIEW_RATING }.png"
									style="width: 5%;">
							</div>
							<br>
							<div style="width : 300px ; height: 200px !important; overflow: auto;">
								<!-- carousel를 사용하기 위해서는 class에 carousel와 slide 설정한다. -->
								<!-- carousel는 특이하게 id를 설정해야 한다.-->
								<div id="carousel-example-generic" class="carousel slide" style="width : 300px ; height: 200px !important; overflow: auto;">
									<!-- carousel의 지시자 -->
									<!-- 지시자라고는 하는데 ol태그의 class에 carousel-indicators를 넣는다. -->
									<ol class="carousel-indicators">
										<!-- li는 이미지 개수만큼 추가하고 data-target은 carousel id를 가르킨다. -->
										<!-- data-slide-to는 순서대로 0부터 올라가고 0은 active를 설정한다. -->
										<!-- 딱히 이 부분은 옵션별로 설정하게 없다. -->
										<c:forEach var="list" items="${alist}" varStatus="status">
											<c:if test="${status.count eq 1}">
												<li data-target="#carousel-example-generic"
													data-slide-to="0" class="active"></li>
											</c:if>

											<c:if test="${status.count ne 1}">
												<li data-target="#carousel-example-generic"
													data-slide-to="1"></li>
											</c:if>
										</c:forEach>
									</ol>
									<!-- 실제 이미지 아이템 -->
									<!-- class는 carousel-inner로 설정하고 role은 listbox에서 설정한다. -->

									<div class="carousel-inner" role="listbox">
										<!-- 이미지의 개수만큼 item을 만든다. 중요한 포인트는 carousel-indicators의 li 태그 개수와 item의 개수는 일치해야 한다. -->

										<c:forEach var="list" items="${alist}" varStatus="status">
											<c:if test="${status.count eq 1}">
												<div class="item active">
													<img src="${list.REVIEWS_IMAGE }"
														style="width : 300px ; height: 200px !important;">
												</div>
											</c:if>

											<c:if test="${status.count ne 1}">
												<div class="item">
													<img src="${list.REVIEWS_IMAGE }"
														style="width : 300px ; height: 200px !important;">
												</div>
											</c:if>
										</c:forEach>

										<!-- <div class="item active">
					아미지 설정-
					<img
						src="https://tistory2.daumcdn.net/tistory/1041549/skin/images/nowonbuntistory.png"
						style="width: 100%">
					캡션 설정 (생략 가능)
				

				</div>
				<div class="item">
					<img src="https://www.nowonbun.com/img/nowonbuntistory1.png"
						style="width: 100%">
				</div> -->



									</div>
									<!-- 왼쪽 화살표 버튼 -->
									<!-- href는 carousel의 id를 가르킨다. -->
									<a class="left carousel-control"
										href="#carousel-example-generic" role="button"
										data-slide="prev"> <!-- 왼쪽 화살표 --> <span
										class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									</a>
									<!-- 오른쪽 화살표 버튼 -->
									<!-- href는 carousel의 id를 가르킨다. -->
									<a class="right carousel-control"
										href="#carousel-example-generic" role="button"
										data-slide="next"> <!-- 오른쪽 화살표 --> <span
										class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									</a>
								</div>
							</div>


							<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
							<br>

							<div class="main-content">
								<hr>
								<!-- 판매 내용 -->
								${map.REVIEW_CONTENTS }
							</div>

						</div>







					</div>
					</c:if>
					
				</div>
				<!-- **** 댓글 클릭 시 toggle - end **** -->

				<div class="mainContent2">
					<div>
						<img src="/resources/lib/images/main-img(4).jpg" class="main_img3" />
					</div>
				</div>

				<div class="category">
					<div class="category-title">
						<!-- <h3>최근 등록된 상품 보기</h3> -->
						<a href="/search/searchPage.do" class="category_a">> 목록으로 <</a>
					</div>
					<%-- <div class="search_div">
						<c:forEach begin="1" end="3">
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
											<h3>판매 제목</h3>
										</div>
									</div>
								</a>
							</div>
						</c:forEach>
					</div> --%>
				</div>

			</div>
			<!-- ******* 전체 div end ******* -->
		</div>
	</div>


	<jsp:include page="/footer.jsp" />

</body>
</html>