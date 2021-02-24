$(document).ready(function() {
	ajaxUtils=ajaxUtil();
	var param = {
			currPage : 1,
			startIndex : (1 - 1) * 10 + 1,
			lastIndex : (1 - 1) * 10 + 10
	}
	ajaxUtils.doPost("member/myPageList.do",param,myPageListView);
	

	
	
});


function myPageListView(dataList){
	console.log(dataList);
	
	var pageInfo = {
			pageNo : dataList.currPage,
			totalCount : dataList.totalCnt,
			offset : 10,
			pages : 10
		};
	
		paging(pageInfo, 'PageWrap');
	
	
}



function page(page){

	
	var param = {
			currPage : page,
			startIndex : (page - 1) * 10 + 1,
			lastIndex : (page - 1) * 10 + 10
	}
	ajaxUtils.doPost("member/myPageList.do",param,myPageListPageView);
}
function myPageListPageView(dataList){
	
	$('#purchaseTd').html('');
	
	var keyList = [ 'SELL_NO','SELL_SELLER_ID','SELL_IMAGE','SELL_CATEGORY_NAME','SELL_TITLE','SELL_PRICE','SELL_PLACE_NAME','SELL_DATE'];
	
	var key = '';
	console.log(dataList.list);
	if (dataList.list) {
		// if(true){
		if (dataList.list.length > 0) {
			// if(true){
			for (var i = 0; i < dataList.list.length; i++) {
				var tr = $("<tr></tr>");

				for (var j = 0; j < keyList.length; j++) {
					var td = $("<td></td>");

					key = keyList[j];
					if(j == 2){
						td.append('<img src="' + dataList.list[i][key] + '" alt="image1" width="100" height="100">');
					}else{
						td.append(dataList.list[i][key]);
					}
					
					td.appendTo(tr);
				}
				tr.appendTo('#purchaseTd');
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
	


};