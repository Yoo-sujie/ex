$(document).ready(function() {
	
	  console.log("ddddd");
	  ajaxUtils = ajaxUtil();
	  $(document).on('click','#sellSearchButton', listSearch);
	  $(document).delegate('#approvalList tr','click', detailForm);
	
	  /* $(document).on('click', '#btn_show', checkReport);*/
	  /*$(document).delegate('#memberList tr','click', memberStopped);*/
	  
	  var param;
	  
	  
	  
	  ajaxUtils.doPost('manager/showPage.do',param,showPage);
	  
});






function showPage(dataList){
	
	 var pageInfo = {
             pageNo : 1,
             totalCount : dataList.totalCnt,
             offset : 10,
             pages : 10
          };
       
          paging(pageInfo, 'PageWrap');
	
	
}

function listSearch(){
	console.log('아아아아아');
	
	//var sellAcknowledgment = $('#sellAcknowledgment option:selected').val();
	
	console.log(sellAcknowledgment); //승인대기여부
	
	var param = {
			//sellAcknowledgment : sellAcknowledgment
			sellAcknowledgment : $('#sellAcknowledgment option:selected').val(), //key : value
			sellSearch : $('#sellSearch option:selected').val(),
			sellSearchText : $('#sellSearchText').val(),
			firstSearchDate : $('#firstSearchDate').val(),
			lastSearchDate : $('#lastSearchDate').val(),
			currPage : 1,
	        startIndex : (1 - 1) * 10 + 1,
	        lastIndex : (1 - 1) * 10 + 10
			
	}
	console.log(param);
	ajaxUtils.doPost('manager/listSearch.do',param,searchResult); //src > main > webapp > resources > lib > js > ajaxUtil.js 안의 것('url', param, callback)을 실행
	
}

function searchResult(dataList){ //위의 callback함수가 제대로 실행됐을 경우 실행되는 결과값
	console.log(dataList);
	
	$('#approvalListTd').html(''); //검색버튼 누를 때 마다 한번씩 싹 비워주기 역할
	
	var keyList = ['sellNo','sellTitle','memberNic','sellAcknowledgment','sellWriteDate']; //td에 들어갈 key값 미리 써주기(값은 majnager.xml에서의 as~값과 같음)
	
	
	if(dataList.alist){
		for(var i=0; i<dataList.alist.length;i++){
			var tr = $("<tr></tr>"); //tr생성
			
			for(var j=0;j < keyList.length;j++){
				td = $("<td></td>"); //tr이 생성될 때 td생성
				
				key = keyList[j];
				td.append(dataList.alist[i][key]); //td에 위에서 미리 정한 key값 순서대로 넣어주기
				console.log('확인' + dataList.alist[i][key]);
				td.appendTo(tr);//tr에 td 넣기
			}
			tr.appendTo('#approvalListTd'); //tbody에 td가 append된 tr 넣기
		}
		
		var pageInfo = {
	               pageNo : dataList.currPage,
	               totalCount : dataList.totalCnt,
	               offset : 10,
	               pages : 10
	            };
	         
	            paging(pageInfo, 'PageWrap');
	}
	
	
	
}


function detail(data) {
		console.log(data);
		

	}


function detailForm(){
	var approvalList = $('#approvalList tr').val();
	
	var tr = $(this);
	var td = tr.children();
	var sellNo = td.eq(0).text();

	
	if (sellNo != '글번호') {
		console.log(sellNo);

		location.href = "/approvalDetail.do?num="+ sellNo;
	}

	
	var param = {
			sellNo : sellNo
	}
	
	
}


/*function checkCount(){
	
	var check = $("#checkCount");
	check.click(function(){
		$("p").toggle();
		$('#reportBar tr').hide();
		
		
	});
	$("p").toggle();
	
	var param = {
			memberId : $('#memberList.member_id').val(), 
			sellNo : $('#memberList.sell_no').val()
				
	}
	console.log(param);
	ajaxUtils.doPost('manager/checkReport.do',param,memberStopped); 
	
	
}
*/
function page(page){
	   
	var param = {
			//sellAcknowledgment : sellAcknowledgment
			sellAcknowledgment : $('#sellAcknowledgment option:selected').val(), //key : value
			sellSearch : $('#sellSearch option:selected').val(),
			sellSearchText : $('#sellSearchText').val(),
			firstSearchDate : $('#firstSearchDate').val(),
			lastSearchDate : $('#lastSearchDate').val(),
			currPage : page,
	        startIndex : (page - 1) * 10 + 1,
	        lastIndex : (page - 1) * 10 + 10
			
	}
console.log($('#firstSearchDate').val());
	console.log($('#lastSearchDate').val());
	ajaxUtils.doPost('manager/listSearch.do',param,searchResult); //src > main > webapp > resources > lib > js > ajaxUtil.js 안의 것('url', param, callback)을 실행
	
	
	

	}

function listSearchPage(dataList){
	
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



