

$(document).ready(function(){
	ajaxUtils = ajaxUtil();
	$("#main-comment").click(function(){
		$("#main-comment-output").toggle();
		$("#review").toggle();
	});
	/*$("#comment_re_a").click(function comment_re_a(e) {
		$("#comment_re_list_"+e).toggle();
	})*/
	
	
	
	
	
	if($('#totalCnt').val() !=0){
		var pageInfo = {
	            pageNo : 1,
	            totalCount : $('#totalCnt').val(),
	            offset : 10,
	            pages : 10
	         };

	    paging(pageInfo, 'PageWrap');

	}
	
	
});

//3초마다 자동 새로고침
/*var myTimer = setTimeout(function() {
	  location.reload(true);
}, 3000);*/

function review_popup(e1, e2) {
	console.log(e1);
	console.log(e2);
	jQuery('#layer_3').attr('style','display:inline');
    jQuery('#layer_3').height(jQuery(document).height());
	//location.href="/review/insertReviewPage.do?sellNo="+e1+"&seller_id="+e2;
}

function insert_review(e) {
	console.log('insertReview');
	
	var insertReview = $("form[name=insertReview]").serialize();
	$.ajax({
        url: "/review/insertReview.do",
        type: "POST",
        data: insertReview,
        dataType : "json",
        success: function(data){
        	location.reload(true);
        },
        error: function(){
        }
	 });
}

//참고
/*
	function addressChange2(e) {
	console.log(e.value);
	if(e.value=='동네를 선택하세요.') {
		selectNo2();
	} else {
		 $.ajax({
	            url: "/seller/sellPlaceSelectStation.do",
	            type: "POST",
	            data: { address_two : $("#address2").val() },
	            dataType : "json",
	            success: function(data){
	                addSelect2(data);
	            },
	            error: function(){
	                alert("error");
	            }
	       });
	}
	
}
	
function addSelect2(data){
	console.log($(data).attr('stationList'));
	var dataList = $(data).attr('stationList');
	$('#address3').empty();
    var option2 = $("<option>역을 선택하세요.</option>");
    $('#address3').append(option2);
	for(var count = 0; count < dataList.length; count++){
        var option = $("<option value="+dataList[count].sell_place_no+">"+dataList[count].sell_place_name+"</option>");
        $('#address3').append(option);
    }
	
	$('#sellPlaceItemId').html(''); //비워줌
	
	var sellPlaceItemId = $('#sellPlaceItemId');
	
	sellPlaceItemId.append('<h3>보관함 선택</h3>'
						+ '* 지역을 선택하세요.<br>');
}
*/

function comment_re_a(sell_comment_originno) {
	
	console.log('comment_re_a');
	
	console.log(sell_comment_originno);
	
	var sell_no = $('#sell_no').val();

	console.log(sell_no);
	
	//$(".comment_re_list_"+sell_comment_originno).toggle();
	
	$.ajax({
        url: "/buyer/sellReCommentSelect.do",
        type: "POST",
        data: { "sell_no" : sell_no
      	  	  , "sell_comment_originno" : sell_comment_originno},
        dataType : "json",
        success: function(data){
        	console.log('성공');
        	comment_re_add(data);
        },
        error: function(){
        	console.log('에러');
        }
	 });
}

function comment_re_add(data) {
	//comment_re_form_"+comment_no
	console.log('comment_re_add');
	console.log(data.result.length);

	var comment2 = $('#comment');
	
	var sell_seller_id = $('#sell_seller_id').val();
	var session_id = $('#id').val();
	var comment_re = $('.comment_re_list_'+data.result[0].SELL_COMMENT_ORIGINNO);
	
	var comment_re_id = document.getElementById("comment_re_list_L_" + data.result[0].SELL_COMMENT_ORIGINNO);
	comment_re.html('');
	if(comment_re_id.style.display == 'block'){
		comment_re_id.style.display = 'none';
	}else{
		comment_re_id.style.display = 'block';
	
	
		for(var i=0; i<data.result.length; i++) {
	
			console.log( i + '번째 태그입니다.');
			
			var div = $('<div class="container" id="comment_update_re_' + data.result[i].SELL_COMMENT_NO + '"></div>');
			var table = $('<table class="table table-bordered" style="background-color: #F6F6F6;"></table>');
			var thead = $('<thead></thead>');
			var tr_2 = $('<tr></tr>');
			var th = $('<th style="padding-left: 20px;"></th>');
			var button = $('<button class="comment_btn_writer_re" disabled="disabled"></button>');
			var a = '';
			if(sell_seller_id == data.result[i].SELL_COMMENT_WRITER) {
				a = $('<a class="seller_comment">ㄴ<img src="/resources/lib/images/buyer-profile(2).png" alt="profile(1)" class="profile_img_logo" /> '+data.result[i].SELL_COMMENT_WRITER_NIC+' <div class="main-comment2">º 판매자</div></a>');
			} else {
				a = $('<div>ㄴ<img src="/resources/lib/images/buyer-profile(2).png" alt="profile(1)" class="profile_img_logo" /> ' + data.result[i].SELL_COMMENT_WRITER_NIC + '</div>');
			}
			var input_del = '';
			var input_up = '';
			if(session_id == data.result[i].SELL_COMMENT_WRITER) {
				input_del = $('<input type="button" class="comment_btn_a_2" id="comment_btn_u_'
						+data.result[i].SELL_COMMENT_NO+'" onclick="comment_delete('+data.result[i].SELL_COMMENT_NO+');" value="삭제">');
				input_up = $('<input type="button" class="comment_btn_a_2" id="comment_btn_d_'
						+data.result[i].SELL_COMMENT_NO+'" onclick="comment_re_update('+data.result[i].SELL_COMMENT_NO+', '
						+data.result[i].SELL_COMMENT_ORIGINNO+');" value="수정">');
			}
			
			var tbody = $('<tbody></tbody>');
			var tr = $('<tr></tr>');
			var td = $('<td style="padding-left: 40px;"></td>');
			var label = $('<br><label id="comment_re_contents_'+data.result[i].SELL_COMMENT_NO+'">'+data.result[i].SELL_COMMENT_CONTENTS+'</label>');
			var small = $('<small id="comment_re_date_'+data.result[i].SELL_COMMENT_NO+'">');
			var label_2 = $('<br><label id="comment_date_la_re_'+data.result[i].SELL_COMMENT_NO+'">'+data.voList[i].sell_comment_write_date_str+'</label><br><br>');
			var input_2 = $('');
			if(session_id != null) {
				input_2 = $('<input type="button" id="comment_re" value="&nbsp;답글&nbsp;" onclick="comment_re_click('
							+ data.result[i].SELL_COMMENT_NO + ',' + data.result[i].SELL_COMMENT_ORIGINNO + ');" class="comment_btn"/>');
			}
			
			
			comment_re.append(div);
			div.append(table);
			table.append(thead);
			table.append(tbody);
			
			th.append(button);
			tr.append(td);
			
			button.append(a);
			td.append(label);
			
			th.append(input_del);
			th.append(input_up);
			label.append(small);
			small.append(label_2);
			small.append(input_2);
			
			tbody.append(tr);
			tr_2.append(th);
			thead.append(tr_2);
			
			//comment2.append(comment_re);
			
			//form.append(tbody);
			//form.append(thead);
			
			//table.append(form);
			
			//------------------------------------------------------------------------------
			
			console.log('답글 :: ' + data.result[i].SELL_COMMENT_ORIGINNO);
			
			//답글
			//$('#comment').html(''); 
			   
			   
			
			  var div6 = $('<div id="comment_re_list_L_'+ data.result[0].SELL_COMMENT_ORIGINNO+'" class="comment_re_list_'
					  + data.result[i].SELL_COMMENT_ORIGINNO + '"></div>');
			  comment_re.append(div6);
			  
		      var div3 = $('<div class="comment_re_form" id="comment_re_form_' + data.result[i].SELL_COMMENT_NO + '"></div>');
		      var div4 = $('<div class="container" id="comment_re_list"></div>');
		      var table3 = $('<table class="table table-bordered" style="background-color: #F6F6F6;"></table>');
		      var thead3 = $('<thead></thead>');
		      var tr_2 = $('<tr></tr>');
		      var th3 = $('<th>ㄴ<img src="/resources/lib/images/buyer-profile(2).png" alt="profile(1)" class="profile_img_logo" />' + data.member_nic + '</th>');
		      var tbody3 = $('<tbody></tbody>');
		      //var form3 = $('<form name="comment_re_form" method="post"></form>');
		      
		      var no3 = $('<input type="hidden" name="sell_no" value="' + data.result[i].SELL_NO + '">');
		      var groupord3 = $('<input type="hidden" name="sell_comment_groupord" value="1">');
		      var originno3 = $('<input type="hidden" name="sell_comment_originno" id = "sell_comment_originno" value="' + data.result[i].SELL_COMMENT_ORIGINNO + '">');
		      var grouplayer3 = $('<input type="hidden" name="sell_comment_grouplayer" value="1">');
		      
		      var tr3 = $('<tr></tr>');
		      var td3 = $('<td align="center"></td>');
		      var textarea3 = $('<textarea cols="160" rows="5"  name="sell_comment_contents2" placeholder="' + data.result[i].SELL_COMMENT_WRITER_NIC + '님에게 답글 쓰기" required id="sell_comment_contents_' + data.result[i].SELL_COMMENT_NO + '" class="comment_textarea"></textarea>');
		      
		      var tr4 = $('<tr></tr>');
		      var td4 = $('<td></td>');
		      var div5 = $('<div class="comment_btn_group"></div>');
		      //var reset3 = $('<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;" class="comment_reset_btn"/>');
		      var reset3 = $('<input type="button" value="&nbsp;&nbsp;초기화&nbsp;" class="comment_reset_btn" onclick="comment_reset2('+data.result[i].SELL_COMMENT_NO+')"/>');
		      var button3 = $('<input type="button" value="&nbsp;등록 &nbsp;" onclick="re_comment_insert(' + data.result[i].SELL_COMMENT_NO + ', ' + data.result[i].SELL_COMMENT_ORIGINNO + ')" class="comment_insert_btn"/>');
		      var checkbox3 = $('<input type="checkbox" name="sell_comment_secret_r" value="1" checked="checked" disabled="disabled"> &nbsp; 비밀댓글 </input>');
		      
		      
		      
		      textarea3.appendTo(td3);
		      td3.appendTo(tr3);   
		      
		      reset3.appendTo(div5);
		      button3.appendTo(div5);
		      div5.appendTo(td4);
		      checkbox3.appendTo(td4);
		      td4.appendTo(tr4);
		      
		      no3.appendTo(tbody3);
		      groupord3.appendTo(tbody3);
		      originno3.appendTo(tbody3);
		      grouplayer3.appendTo(tbody3);
		      
		      tr3.appendTo(tbody3);
		      tr4.appendTo(tbody3);
		      //form3.appendTo(tbody3);
		      tr_2.appendTo(thead3);
		      th3.appendTo(tr_2);
		      
		      thead3.appendTo(table3);
		      tbody3.appendTo(table3);
		      table3.appendTo(div4);
		      div4.appendTo(div3);
		      div3.appendTo(comment_re);
		   
		      //$("#comment_re_form_"+data.result[0].SELL_COMMENT_NO).toggle();
		      //$("#comment_re_form_"+data.result[0].SELL_COMMENT_NO).hide();
		}
	}
	
	//$(".comment_re_list_"+data.result[0].SELL_COMMENT_ORIGINNO).toggle();
	
	
}

function delete_btn_click() {
	console.log('delete');
	var sell_no = $('input[name = sell_no]').val();
	if(confirm('삭제 하시겠습니까?') == true) {
		location.href="/seller/sellDelete.do?sell_no=" + sell_no;
	} else {
		return;
	}
}

function check() {
	console.log('submit check');
	return true;
}

function comment_insert() {
	console.log('comment insert');
	
	var sell_comment_contents = $('#sell_comment_contents').val();
	console.log('(1)댓글 내용 : ' + sell_comment_contents);
	
	if(sell_comment_contents != "") {
		var comment_form = $("form[name=comment_form]").serialize();
		$.ajax({
            url: "/buyer/commentInsert.do",
            type: "POST",
            data: comment_form,
            dataType : "json",
            success: function(data){
            	location.reload();
            	/*$('#sell_comment_contents').html('');
            	var hiddenId = $('#pageHidden').val();
            	javascript:page(hiddenId);*/
            },
            error: function(){
            }
       });
	} else {
		alert('댓글 내용을 입력해주세요.');
		return;
	}
}

//수정할 댓글의 정보 불러오기
function comment_update(e) {
	console.log('comment update');

	var content = $('#comment_contents_'+e).html();
	var div = $('#comment_list').clone();
	console.log(div);
	
	$('#comment_update_'+e+' table').hide();
	$('#comment_update_'+e).append(div);
	
	var content_up = $('#comment_update_'+e+' div table tbody tr td textarea');
	
	content_up.html('');
	content_up.append(content);
	
	var div_btn = $('#comment_update_'+e+' div table tbody tr td div');
	var reset_btn = $('<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;" class="comment_reset_btn"' 
			+ ' onclick="comment_reset('+ e + ')"/>');
	var up_btn = $('<input type="button" value="&nbsp;수정 &nbsp;" '
			+'onclick="comment_update_click('+ e +')" class="comment_insert_btn"/>');
	div_btn.html('');
	div_btn.append(reset_btn);
	div_btn.append(up_btn);

}

//수정할 답글의 정보 불러오기
function comment_re_update(comment_no, comment_originno) {
	console.log('comment re update');
	
	var content = $('#comment_re_contents_'+comment_no).html();
	var form = $('#comment_re_form_'+comment_no).clone();
	console.log(content);
	
	$(".comment_re_list_"+comment_no).show();
	
	$('#comment_update_re_'+comment_no+' table').hide();
	$('#comment_update_re_'+comment_no).append(form.html());
	
	var content_up = $('#comment_update_re_'+comment_no+' div table tbody tr td textarea');
	
	content_up.html('');
	content_up.append(content);
	
	var div_btn = $('#comment_update_re_'+comment_no+' div table tbody tr td div');
	var reset_btn = $('<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;" class="comment_reset_btn"' 
			+ ' onclick="comment_re_reset('+ comment_no + ')"/>');
	var up_btn = $('<input type="button" value="&nbsp;수정 &nbsp;" '
			+'onclick="comment_re_update_click('+ comment_no + ', ' + comment_originno +')" class="comment_insert_btn"/>');
	div_btn.html('');
	div_btn.append(reset_btn);
	div_btn.append(up_btn);
	
}

function comment_reset(e) {
	console.log('comment_reset');
	var content_up = $('#comment_update_'+e+' div table tbody tr td textarea');	
	content_up.html('');
}

function comment_reset2(e) {
	console.log('comment_reset');
	
	$('#sell_comment_contents_'+e).val('');	
}

function comment_re_reset(e) {
	console.log('comment_re_reset');
	var content_up = $('#comment_update_re_'+e+' div table tbody tr td textarea');
	content_up.html('');
}

function comment_update_click(e) {
	console.log('comment_update_click');
	
	var sell_no = $('#sell_no').val();
	var content_up = $('#comment_update_'+e+' div table tbody tr td textarea').val();
	
	console.log(content_up);
	
	if(content_up == '') {
		alert('댓글 내용을 입력해주세요.');
		return;
	} else {
		$.ajax({
            url: "/buyer/commentUpdate.do",
            type: "POST",
            data: {   "sell_comment_contents" : content_up
            		, "sell_no" : sell_no
            		, "sell_comment_no" : e},
            dataType : "json",
            success: function(data){
            	sell_reload(sell_no, e, content_up);
            },
            error: function(){
            }
       });
	}
}

function comment_re_update_click(comment_no, comment_originno) {
	console.log('comment_re_update_click');
	
	var sell_no = $('#sell_no').val();
	var content_up = $('#comment_update_re_'+comment_no+' div table tbody tr td textarea').val();
	
	console.log(content_up);
	
	if(content_up == '') {
		alert('댓글 내용을 입력해주세요.');
		return;
	} else {
		$.ajax({
			url: "/buyer/commentUpdate.do",
			type: "POST",
			data: {   "sell_comment_contents" : content_up
				, "sell_no" : sell_no
				, "sell_comment_no" : comment_no},
				dataType : "json",
				success: function(data){
					comment_re_a(comment_originno);
	            	comment_re_a(comment_originno);
					//sell_re_reload(sell_no, e, content_up);
				},
				error: function(){
				}
		});
	}
}

function sell_reload(sell_no, sell_comment_no, sell_comment_contents) {
	var content_up = $('#comment_update_'+sell_comment_no+' table tbody tr td label');
	var comment_up = $('#comment_update_'+sell_comment_no+' table');
	var comment_up_d = $('#comment_update_'+sell_comment_no+' div');
	var comment_date_la = $('#comment_date_la_'+sell_comment_no);
	$.ajax({
        url: "/buyer/commentOneSelect.do",
        type: "POST",
        data: { "sell_no" : sell_no
        	  , "sell_comment_no" : sell_comment_no
        	  , "sell_comment_contents" : sell_comment_contents
        	  },
        dataType : "json",
        success: function(data){
        	console.log(data.date);
        	comment_up_d.remove();
        	content_up.html('');
        	content_up.append(sell_comment_contents);
        	comment_date_la.html('');
        	comment_date_la.append(data.date);
        	comment_up.show();
        	
        },
        error: function(){
        }
   });
	console.log('다녀옴');
}

function sell_re_reload(sell_no, sell_comment_no, sell_comment_contents) {
	var content_up = $('#comment_update_re_'+sell_comment_no+' table tbody tr td label');
	var comment_up = $('#comment_update_re_'+sell_comment_no+' table');
	var comment_up_d = $('#comment_update_re_'+sell_comment_no+' div');
	var comment_date_la = $('#comment_date_la_re_'+sell_comment_no);
	var commet_small = $('#comment_re_date_'+sell_comment_no);
	var comment_label = $('#comment_date_la_re_'+sell_comment_no);
	var input = $('#comment_re');

	$.ajax({
		url: "/buyer/commentOneSelect.do",
		type: "POST",
		data: { "sell_no" : sell_no
			, "sell_comment_no" : sell_comment_no
			, "sell_comment_contents" : sell_comment_contents
		},
		dataType : "json",
		success: function(data){
			console.log(data.date);
			comment_up_d.remove();
			content_up.html('');
			content_up.append(sell_comment_contents);
			content_up.append(commet_small);
			commet_small.append(comment_label);
			commet_small.append(input_2);
			comment_date_la.html('');
			comment_date_la.append(data.date);
			comment_up.show();
			
		},
		error: function(){
		}
	});
	console.log('다녀옴');
}

function sell_up_re_reload(sell_no, comment_no, sell_comment_contents) {
	
	var comment_no_plus = comment_no+1;
	var content_up = $('#comment_update_re_'+comment_no_plus+' table tbody tr td label');
	var comment_up_d = $('#comment_re_form_'+comment_no+' #comment_re_list');
	
	var comment_up = $('#comment_re_list_L #comment_update_re_'+comment_no_plus+' table');
	var comment_date_la = $('#comment_date_la_re_'+comment_no_plus);

	console.log(comment_no);
	console.log('comment_up_d :: ' + comment_up_d.html());
	
	$.ajax({
		url: "/buyer/commentOneSelect.do",
		type: "POST",
		data: { "sell_no" : sell_no
			, "sell_comment_no" : comment_no
			, "sell_comment_contents" : sell_comment_contents
		},
		dataType : "json",
		success: function(data){
			//comment_up_d.remove();
			comment_re_click(comment_no, '1');
			content_up.html('');
			content_up.append(sell_comment_contents);
			comment_date_la.html('');
			comment_date_la.append(data.date);
			comment_up.show();
			
		},
		error: function(){
		}
	});
	console.log('다녀옴');
}

function comment_delete(e) {
	console.log('comment delete');
	console.log(e);
	
	var sell_no = $('#sell_no').val();
	console.log(sell_no);
	
	if(e != null) {
		$.ajax({
            url: "/buyer/commentDelete.do",
            type: "POST",
            data: {"sell_comment_no" : e, "sell_no" : sell_no},
            dataType : "json",
            success: function(data){
            	location.reload(true);
            },
            error: function(){
            }
       });
	} else {
		alert('삭제된 댓글입니다.');
		return;
	}
}

function popupClose() {
	jQuery('#layer').attr('style','display:none');
	jQuery('#layer_2').attr('style','display:none');
	jQuery('#layer_3').attr('style','display:none');
}

function addCommentVo(data) {
	
	var commentVo = $(data).attr('sellCommentVo');
	
	console.log(commentVo.sell_comment_no);
       
    // 팝업창을 연다.
    jQuery('#layer').attr('style','display:inline');

    // 페이지를 가리기위한 레이어 영역의 높이를 페이지 전체의 높이와 같게 한다.
    jQuery('#layer').height(jQuery(document).height());
 
    var comment_up_form = $("form[name=comment_up_form");

    var comment_no_input = document.createElement("input");
    comment_no_input.type = "hidden";
    comment_no_input.name = "sell_comment_no";
    comment_no_input.id = "sell_comment_no";
    comment_no_input.value = commentVo.sell_comment_no;
    
	$('#sell_comment_contents_up').html('');
	$('#sell_comment_contents_up').append(commentVo.sell_comment_contents);
	comment_up_form.append(comment_no_input);
	
}

function comment_re_cnt(data){
	var cnt = $(data).attr('comment_re_cnt');
	console.log(cnt);
	if(cnt != 0) {
		//답글이 있다면
		$('#comment_list').append('삭제된 댓글입니다.');
	}
}

function comment_delete_check(data){
	var ck = $(data).attr('comment_delete_check');
	console.log(ck);
	if(ck == 1) {
		alert('삭제 되었습니다.');
	} else {
		alert('삭제된 댓글입니다.');
	}
}

function re_comment_insert(comment_no, comment_originno) {
	console.log('re comment insert');
	console.log(comment_no);
	console.log(comment_originno);
	$('#sell_comment_originno').val(comment_originno);
	var sell_comment_contents2 = $('#sell_comment_contents_'+comment_no).val();
	console.log('(2)답글 내용 : ' + sell_comment_contents2);
	
	if(sell_comment_contents2 != "") {
		//var comment_form2 = $("form[name=comment_re_form").serialize();
		
		var sell_no = $('#sell_no').val();
		var sell_comment_groupord = $('#sell_comment_groupord').val();
		var sell_comment_originno = $('#sell_comment_originno').val();
		var sell_comment_grouplayer = 1;
		
		console.log(sell_no);
		console.log(sell_comment_groupord);
		console.log(sell_comment_originno);
		console.log(sell_comment_grouplayer);
		
		$.ajax({
            url: "/buyer/commentInsert.do?sell_comment_contents="+sell_comment_contents2,
            type: "POST",
            data: {
            	"sell_no" : sell_no,
            	"sell_comment_groupord" : sell_comment_groupord,
            	"sell_comment_originno" : sell_comment_originno,
            	"sell_comment_grouplayer" : sell_comment_grouplayer
            },
            dataType : "json",
            success: function(data){
            	sell_up_re_reload(data.sellCommentVO.sell_no, 
            					comment_no, 
            				   data.sellCommentVO.sell_comment_contents);
            	comment_re_a(comment_originno);
            	comment_re_a(comment_originno);
            	$('#comment_re_a_'+comment_originno).html('');
            	$('#comment_re_a_'+comment_originno).html('답글('+data.comment_re_cnt+'개)');
            },
            error: function(){
            }
       });
	} else {
		alert('댓글 내용을 입력해주세요.');
		return;
	}
}

function comment_re_click(comment_no, comment_originno) {
	console.log('comment re');
	console.log(comment_no);		
	console.log(comment_originno);		
 	$('.comment_textarea').val('');
	//$(".comment_re_form").hide();
	$("#comment_re_form_"+comment_no).toggle();
	
	
}


function paging(pageInfo, tagName) {
   var info = pageInfo;
   $("#" + tagName).html('');

   var currPage = info.pageNo;
   var offset = info.offset;

   var totalPage = Math.ceil(info.totalCount / offset);
   var pageOffset = info.pages;

   var firstPage = Math.floor((currPage - 1) / pageOffset) * pageOffset + 1;
   var lastPage = Math.floor((currPage - 1) / pageOffset) * pageOffset
         + pageOffset;
   var currPageCnt = Math.ceil(currPage / pageOffset);
   var lastPageCnt = Math.ceil((totalPage) / pageOffset);
   var nextPage = lastPage + 1;
   var prevPage = firstPage - 1;

   if (lastPage > totalPage)
      lastPage = totalPage;

   var first = $("<li></li>")
   if (currPage == 1) { // 현재페이지가 첫페이지면 << 버튼 클릭 비활성화
      first.addClass('disabled');
   }
   var a = $("<a href='javascript:page(1);' aria-label='Previous'><span aria-hidden='true'>«</span></a>");
   a.appendTo(first);
   first.appendTo('#' + tagName);

   if (currPage > pageOffset) { // 현재 페이지가 첫 페이지 리스트일 때 < 비활성화
      var prev = $("<li></li>")

      // prev.addClass('disabled');

      var a = $("<a href='javascript:page("
            + prevPage
            + ");' aria-label='Previous'><span aria-hidden='true'>＜</span></a>");
      a.appendTo(prev);
      prev.appendTo('#' + tagName);
      
   }
   for (var i = firstPage; i <= lastPage; i++) { // 현재 페이지갯수 만큼 리스트 표출
      var li = $("<li></li>");
      if (currPage == i)
         li.addClass("active");
      var a = $("<a href='javascript:page(" + i + ");'></a>");
      a.append(i);
      a.appendTo(li);
      li.appendTo("#" + tagName);
      
   }
   
   if (currPageCnt < lastPageCnt) { // 현재 페이지 리스트가 마지막 페이지 리스트보다 작을 때

      var next = $("<li></li>")
      // next.addClass('disabled');
      var a = $("<a href='javascript:page("
            + nextPage
            + ");' aria-label='Previous'><span aria-hidden='true'>＞</span></a>");
      a.appendTo(next);
      next.appendTo('#' + tagName);
   }

   var last = $("<li></li>")
   if (currPage == totalPage) {
      last.addClass('disabled');
   }
   var a = $("<a href='javascript:page(" + totalPage
         + ");' aria-label='Previous'><span aria-hidden='true'>»</span></a>");
   a.appendTo(last);
   last.appendTo('#' + tagName);
   
   var hidden = $("<input type='hidden'  id = 'code' value= " + pageInfo.code + ">");
   hidden.appendTo('#' + tagName);

}

function buyer_popup() {	
	
    jQuery('#layer_2').attr('style','display:inline');
    jQuery('#layer_2').height(jQuery(document).height());

}

function buyerPayment(sell_no, price) {
	console.log(sell_no);
	console.log(price);
	
	jQuery('#layer_2').attr('style','display:none');
	
	window.open("/buyer/buyerPayment.do?price="+price+"&sell_no="+sell_no,
	"a", "width=600, height=600, left=600, top=200");
}

function review_select(sell_no) {
	console.log(sell_no);
	location.href="/review/review.do?sell_no="+sell_no;
}


//--------------------- 태 페이징 부분

function paging(pageInfo, tagName) {
	   var info = pageInfo;
	   $("#" + tagName).html('');

	   var currPage = info.pageNo;
	   var offset = info.offset;

	   var totalPage = Math.ceil(info.totalCount / offset);
	   var pageOffset = info.pages;

	   var firstPage = Math.floor((currPage - 1) / pageOffset) * pageOffset + 1;
	   var lastPage = Math.floor((currPage - 1) / pageOffset) * pageOffset
	         + pageOffset;
	   var currPageCnt = Math.ceil(currPage / pageOffset);
	   var lastPageCnt = Math.ceil((totalPage) / pageOffset);
	   var nextPage = lastPage + 1;
	   var prevPage = firstPage - 1;

	   if (lastPage > totalPage)
	      lastPage = totalPage;

	   var first = $("<li></li>")
	   if (currPage == 1) { // 현재페이지가 첫페이지면 << 버튼 클릭 비활성화
	      first.addClass('disabled');
	   }
	   var a = $("<a href='javascript:page(1);' aria-label='Previous'><span aria-hidden='true'>«</span></a>");
	   a.appendTo(first);
	   first.appendTo('#' + tagName);

	   if (currPage > pageOffset) { // 현재 페이지가 첫 페이지 리스트일 때 < 비활성화
	      var prev = $("<li></li>")

	      // prev.addClass('disabled');

	      var a = $("<a href='javascript:page("
	            + prevPage
	            + ");' aria-label='Previous'><span aria-hidden='true'>＜</span></a>");
	      a.appendTo(prev);
	      prev.appendTo('#' + tagName);
	   }
	   for (var i = firstPage; i <= lastPage; i++) { // 현재 페이지갯수 만큼 리스트 표출
	      var li = $("<li></li>");
	      if (currPage == i)
	         li.addClass("active");
	      var a = $("<a href='javascript:page(" + i + ");'></a>");
	      a.append(i);
	      a.appendTo(li);
	      li.appendTo("#" + tagName);
	   }
	   if (currPageCnt < lastPageCnt) { // 현재 페이지 리스트가 마지막 페이지 리스트보다 작을 때

	      var next = $("<li></li>")
	      // next.addClass('disabled');
	      var a = $("<a href='javascript:page("
	            + nextPage
	            + ");' aria-label='Previous'><span aria-hidden='true'>＞</span></a>");
	      a.appendTo(next);
	      next.appendTo('#' + tagName);
	   }

	   var last = $("<li></li>")
	   if (currPage == totalPage) {
	      last.addClass('disabled');
	   }
	   var a = $("<a href='javascript:page(" + totalPage
	         + ");' aria-label='Previous'><span aria-hidden='true'>»</span></a>");
	   a.appendTo(last);
	   last.appendTo('#' + tagName);
	   
	   var hidden = $("<input type='hidden'  id = 'code' value= " + pageInfo.code + ">");
	   hidden.appendTo('#' + tagName);

	}
	
	function review_select(sell_no) {
		console.log(sell_no);
		location.href="/review/review.do?sell_no="+sell_no;
	}











	function paging(pageInfo, tagName) {
	   var info = pageInfo;
	   $("#" + tagName).html('');

	   var currPage = info.pageNo;
	   var offset = info.offset;

	   var totalPage = Math.ceil(info.totalCount / offset);
	   var pageOffset = info.pages;

	   var firstPage = Math.floor((currPage - 1) / pageOffset) * pageOffset + 1;
	   var lastPage = Math.floor((currPage - 1) / pageOffset) * pageOffset
	         + pageOffset;
	   var currPageCnt = Math.ceil(currPage / pageOffset);
	   var lastPageCnt = Math.ceil((totalPage) / pageOffset);
	   var nextPage = lastPage + 1;
	   var prevPage = firstPage - 1;

	   if (lastPage > totalPage)
	      lastPage = totalPage;

	   var first = $("<li></li>")
	   if (currPage == 1) { // 현재페이지가 첫페이지면 << 버튼 클릭 비활성화
	      first.addClass('disabled');
	   }
	   var a = $("<a href='javascript:page(1);' aria-label='Previous'><span aria-hidden='true'>«</span></a>");
	   a.appendTo(first);
	   first.appendTo('#' + tagName);

	   if (currPage > pageOffset) { // 현재 페이지가 첫 페이지 리스트일 때 < 비활성화
	      var prev = $("<li></li>")

	      // prev.addClass('disabled');

	      var a = $("<a href='javascript:page("
	            + prevPage
	            + ");' aria-label='Previous'><span aria-hidden='true'>＜</span></a>");
	      a.appendTo(prev);
	      prev.appendTo('#' + tagName);
	   }
	   for (var i = firstPage; i <= lastPage; i++) { // 현재 페이지갯수 만큼 리스트 표출
	      var li = $("<li></li>");
	      if (currPage == i)
	         li.addClass("active");
	      var a = $("<a href='javascript:page(" + i + ");'></a>");
	      a.append(i);
	      a.appendTo(li);
	      li.appendTo("#" + tagName);
	   }
	   if (currPageCnt < lastPageCnt) { // 현재 페이지 리스트가 마지막 페이지 리스트보다 작을 때

	      var next = $("<li></li>")
	      // next.addClass('disabled');
	      var a = $("<a href='javascript:page("
	            + nextPage
	            + ");' aria-label='Previous'><span aria-hidden='true'>＞</span></a>");
	      a.appendTo(next);
	      next.appendTo('#' + tagName);
	   }

	   var last = $("<li></li>")
	   if (currPage == totalPage) {
	      last.addClass('disabled');
	   }
	   var a = $("<a href='javascript:page(" + totalPage
	         + ");' aria-label='Previous'><span aria-hidden='true'>»</span></a>");
	   a.appendTo(last);
	   last.appendTo('#' + tagName);

	}









	function buyerComment(dataList) {

	   console.log(dataList);

	    $('#comment').html(''); 
	   
	   var comment = $('#comment');
	   
	   $('#sell_comment_originno').val();
	   
	   
	   
	   
	   

	  


	   if (dataList.commentList) {
	      for(var i = 0; i<dataList.commentList.length;i++){
			//console.log(dataList.commentList[i]["sell_comment_secret"]);
			
			var sessionId = $('#sessionId').val();
			var sellerId = $('#sellerId').val();
			
			console.log(sessionId);
			console.log(sellerId);
			
			//비밀댓글
			if(dataList.commentList[i]["sell_comment_secret"] == 1 
					&& dataList.commentList[i]["sell_comment_writer"] != sessionId
					&& sessionId != sellerId){
			//console.log('sss');
				var div = $('<div class="container"></div>');
				var table = $('<table class="table table-bordered"></table>');
				var form = $('<form></form>');
				var tbody = $('<tbody></tbody>');
				var tr = $('<tr></tr>');
				var td = $('<td></td>');
				var img = $('<br><br><img src="/resources/lib/images/secret.png" width="15px"> 비밀 댓글입니다. 작성자와 판매자만 볼 수 있습니다. <br><br>');
				var small = $('<small>' + dataList.commentList[i]["sell_comment_write_date_str"] + '</small>');
				var a = $('<a id="comment_re_a_'+dataList.commentList[i]["sell_comment_originno"]
						+ '" onclick="comment_re_a(' + dataList.commentList[i]["sell_comment_originno"] + ')">'); 
				
				
				
				img.appendTo(td);
				small.appendTo(td);
				a.appendTo(td);
				td.appendTo(tr);
				tr.appendTo(tbody);
				//tbody.appendTo(form);
				tbody.appendTo(table);
				table.appendTo(div);
				div.appendTo(comment);
				
				
				
			//일반댓글
			} else {
				
				//console.log('일반 댓글');
				
				
				var div = $('<div class="container" id="comment_update_' + dataList.commentList[i]["sell_comment_no"] + '"></div>');
				var table = $('<table class="table table-bordered"></table>');
				var form = $('<form action="" method="post" encType="multiplart/form-data"></form>');
				
				var thead = $('<thead></thead>');
				var tr_2 = $('<tr></tr>');
				var th = $('<th></th>');
				var button = $('<button class="comment_btn_writer" disabled="disabled"></button>');
				
				var delete1 = $('<input type="button" class="comment_btn_a_1" onclick="comment_delete(' + dataList.commentList[i]["sell_comment_no"] + ');" value="삭제">');				
				var update = $('<input type="button" class="comment_btn_a_1" onclick="comment_update(' + dataList.commentList[i]["sell_comment_no"] + ');" value="수정">');

				var a = $('');
				if(dataList.commentList[i]["sell_comment_writer"] == sellerId) {
					a = $('<a class="seller_comment"><img src="/resources/lib/images/buyer-profile(1).png" alt="profile(1)" class="profile_img_logo" />' 
							+ dataList.commentList[i]["sell_comment_writer_nic"] + '&nbsp;<div class="main-comment2">º 판매자</div></a>');
				} else {
					a = $('<label><img src="/resources/lib/images/buyer-profile(1).png" alt="profile(1)" class="profile_img_logo" />'
							+dataList.commentList[i]["sell_comment_writer_nic"]+'</label>');
				}
				
				
				
				var tbody = $('<tbody></tbody>');
				var tr = $('<tr></tr>');
				var td = $('<td></td>');
				var br = $('<br><label id="comment_contents_'+ dataList.commentList[i]["sell_comment_no"] +'">' + dataList.commentList[i]["sell_comment_contents"] + '</label>');
				var small = $('<small></small>');
				var label = $('<br>' 
						+ '<label id="comment_date_la_'+ dataList.commentList[i]["sell_comment_no"] +'">'
						+ dataList.commentList[i]["sell_comment_write_date_str"] + '</label><br><br>');
				var button2 = button2 = $('<input type="button" id="comment_re" value="&nbsp;답글&nbsp;" '
	                	+ 'onclick="comment_re_click(' +dataList.commentList[i]["sell_comment_no"]
	                	+ ' , ' + dataList.commentList[i]["sell_comment_originno"] + ');" class="comment_btn"/>');
				
				var a2 = $('<label>&nbsp;&nbsp;&nbsp;&nbsp;</label><a id="comment_re_a_'+dataList.commentList[i]["sell_comment_originno"]
				+'" onclick="comment_re_a(' + dataList.commentList[i]["sell_comment_originno"] + ')">답글(' + dataList.commentList[i]["sell_comment_re_total"] + '개)</a>');
				
				
				

				
				
				a.appendTo(th);
				button.appendTo(th);
				if(dataList.commentList[i]["sell_comment_writer"] == sessionId){
					delete1.appendTo(th);		
					update.appendTo(th);
				}
				tr_2.appendTo(thead);
				th.appendTo(tr_2);
				
				label.appendTo(small);
				if(sessionId != null) {	
					button2.appendTo(small);					
				}
				a2.appendTo(small);
				br.appendTo(td);
				small.appendTo(td);
				td.appendTo(tr);
				tr.appendTo(tbody);
								
				thead.appendTo(table);
				tbody.appendTo(table);
				
				//form.appendTo(table);
				table.appendTo(div);
				div.appendTo(comment);
				
				
			
			}      
	      
	      
	      
			//-- 답글 쓰기
			
			//답글
			  var div6 = $('<div id="comment_re_list_L_' + dataList.commentList[i]["sell_comment_originno"] + '" class="comment_re_list_'
					  + dataList.commentList[i]["sell_comment_originno"] + '"></div>');

		      var div3 = $('<div class="comment_re_form" id="comment_re_form_' + dataList.commentList[i]["sell_comment_no"] + '"></div>');
		      var div4 = $('<div class="container" id="comment_re_list"></div>');
		      var table3 = $('<table class="table table-bordered" style="background-color: #F6F6F6;"></table>');
		      var thead3 = $('<thead></thead>');
		      var tr_2 = $('<tr></tr>');
		      var th3 = $('<th>ㄴ<img src="/resources/lib/images/buyer-profile(2).png" alt="profile(1)" class="profile_img_logo" />' + dataList.member_nic + '</th>');
		      var tbody3 = $('<tbody></tbody>');
		      //var form3 = $('<form name="comment_re_form" method="post"></form>');
		      
		      var no3 = $('<input type="hidden" name="sell_no" value="' + dataList.commentList[i]["sell_no"] + '">');
		      var groupord3 = $('<input type="hidden" name="sell_comment_groupord" value="1">');
		      var originno3 = $('<input type="hidden" name="sell_comment_originno" id = "sell_comment_originno" value="' + dataList.commentList[i]["sell_comment_originno"] + '">');
		      var grouplayer3 = $('<input type="hidden" name="sell_comment_grouplayer" value="1">');
		      
		      var tr3 = $('<tr></tr>');
		      var td3 = $('<td align="center"></td>');
		      var textarea3 = $('<textarea cols="160" rows="5"  name="sell_comment_contents2" placeholder="' + dataList.commentList[i]["sell_comment_writer_nic"] + '님에게 답글 쓰기" required id="sell_comment_contents_' + dataList.commentList[i]["sell_comment_no"] + '" class="comment_textarea"></textarea>');
		      
		      var tr4 = $('<tr></tr>');
		      var td4 = $('<td></td>');
		      var div5 = $('<div class="comment_btn_group"></div>');
		      var reset3 = $('<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;" class="comment_reset_btn"/>');
		      var button3 = $('<input type="button" value="&nbsp;등록 &nbsp;" onclick="re_comment_insert(' + dataList.commentList[i]["sell_comment_no"] + ', ' + dataList.commentList[i]["sell_comment_originno"] + ')" class="comment_insert_btn"/>');
		      var checkbox3 = $('<input type="checkbox" name="sell_comment_secret_r" value="1" checked="checked" disabled="disabled"> &nbsp; 비밀댓글 </input>');
		      
		      
		      
		      textarea3.appendTo(td3);
		      td3.appendTo(tr3);   
		      
		      reset3.appendTo(div5);
		      button3.appendTo(div5);
		      div5.appendTo(td4);
		      checkbox3.appendTo(td4);
		      td4.appendTo(tr4);
		      
		      no3.appendTo(tbody3);
		      groupord3.appendTo(tbody3);
		      originno3.appendTo(tbody3);
		      grouplayer3.appendTo(tbody3);
		      
		      tr3.appendTo(tbody3);
		      tr4.appendTo(tbody3);
		      //form3.appendTo(tbody3);
		      tr_2.appendTo(thead3);
		      th3.appendTo(tr_2);
		      
		      thead3.appendTo(table3);
		      tbody3.appendTo(table3);
		      table3.appendTo(div4);
		      div4.appendTo(div3);
		      div3.appendTo(comment);
			
		      
		      comment.append(div6);
			
			//-- 답글 쓰기 end
	      
	      
	      
	      
	      
		  //답글
		  //console.log('답글');
		  
	      //var div = $('<div class="container" id="comment_list"></div>');
	      //var table = $('<table class="table table-bordered"></table>');
	      //var thead = $('<thead></thead>');
	      //var th = $('<th>&nbsp;${member_nic}</th>');
	      
	      //var no = $('<input type="hidden" name="sell_no" value="${sellerVO.sell_no}">');
	      //var groupord = $('<input type="hidden" name="sell_comment_groupord" value="0">');
	      //var grouplayer = $('<input type="hidden" name="sell_comment_grouplayer" value="0">');
	      
	      //var tbody = $('<tbody></tbody>');
	      //var form = $('<form name="comment_form" method="post"></form>');
	      
	      //var tr = $('<tr></tr>');
	      //var td = $('<td align="center"></td>');
	      //var textarea = $('<textarea cols="160" rows="5"  name="sell_comment_contents" placeholder="댓글을 작성하세요.(악플과 욕설은 회원 정지 대상입니다.)&#13;&#10;" required id="sell_comment_contents" class="comment_textarea"></textarea>');
	      //var tr2 = $('<tr></tr>');
	      //var td2 = $('<td></td>');
	      //var div2 = $('<div class="comment_btn_group"></div>');
	      //var reset = $('<input type="reset" value="&nbsp;&nbsp;초기화&nbsp;" class="comment_reset_btn"/>');
	      //var button = $('<input type="button" value="&nbsp;등록 &nbsp;" onclick="comment_insert()" class="comment_insert_btn"/>');
	      //var check = $('<input type="checkbox" name="sell_comment_secret" value="1" checked="checked" disabled="disabled"> &nbsp; 비밀댓글');
	      
	      
	      //check.appendTo(td2);
	      //reset.appendTo(div2);
	      //button.appendTo(div2);
	      //td2.appendTo(tr2);
	      
	      //textarea.appendTo(td);
	      //td.appendTo(tr);
	      //tr.appendTo(form);
	      //tr2.appendTo(form);
	      
	      //no.appendTo(form);
	      //groupord.appendTo(form);
	      //grouplayer.appendTo(form);
	      
	      //form.appendTo(tbody);
	      //th.appendTo(thead);
	      //form.appendTo(tbody);
	      
	      //tbody.appendTo(table);
	      //thead.appendTo(table);
	      //table.appendTo(div);
	      
	      //div.appendTo(comment);
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      }
	      
	         
	         
	         
	         
	         
	         
	         
	         
	         //양수경 - 현재 페이지 구하기
	         $('#pageHidden').val(dataList.sellCommentCnt);
	         
	         
	         var pageInfo = {
	            pageNo : dataList.currPage,
	            totalCount : dataList.sellCommentCnt,
	            offset : 10,
	            pages : 10
	         }

	         paging(pageInfo, 'PageWrap');
	      }

	 

	}






	function page(page) {

	   var param = {
	   	  sell_no : $('#sell_no').val(),
	      currPage : page,
	      startIndex : (page - 1) * 10 + 1,
	      lastIndex : (page - 1) * 10 + 10
	      
	   }
	   ajaxUtils.doPost("/buyer/buyerComment.do", param, buyerComment);
	}

