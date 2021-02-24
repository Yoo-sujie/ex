
$(document).ready(function() {
	
	ajaxUtils = ajaxUtil();	 
	var param = {
			currPage : 1,
			startIndex : (1 - 1) * 10 + 1,
			lastIndex : (1 - 1) * 10 + 10
	}
	
	ajaxUtils.doPost("/serviceCenter/noticeList.do",param,noticeAllListView);
	$(document).delegate('#noticeTable tr', 'click', clickedNoticeTable);
	
});

function clickedNoticeTable(){
	var tr = $(this);
	var td = tr.children();
	//console.log(td.eq(0).html());
	//console.log(this);
	//console.log(td.eq(0).html())
	var check = td.eq(0).html();
	if(check != '번호' ){
		location.href = "/serviceCenter/noticeView.do?no=" + check  ;
	}
}

function noticeAllListView(dataList){
	//console.log(dataList);
	$('#noticeTd1').html('');
	var keyList = [ 'NOTICE_NO','NOTICE_TITLE','NOTICE_WRITER','NOTICE_CNT','NOTICE_WRITE_DATE'];
	var key = '';
	//console.log(dataList.list);
	if (dataList.list) {
		if (dataList.list.length > 0) {
			for (var i = 0; i < dataList.list.length; i++) {
				var tr = $("<tr></tr>");
				for (var j = 0; j < keyList.length; j++) {
					var td = $("<td></td>");
					key = keyList[j];
					td.append(dataList.list[i][key]);
					td.appendTo(tr);
				}
				tr.appendTo('#noticeTd1');
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

function page(page){
	var param = {
		currPage : page,
		startIndex : (page - 1) * 10 + 1,
		lastIndex : (page - 1) * 10 + 10
	}
	
	ajaxUtils.doPost("/serviceCenter/noticeList.do",param,noticeAllListView);
}

function paging(pageInfo, tagName) {
	
	var info = pageInfo;
	$("#" + tagName).html('');

	var currPage = info.pageNo;
	var offset = info.offset;

	var totalPage = Math.ceil(info.totalCount / offset);
	var pageOffset = info.pages;

	var firstPage = Math.floor((currPage - 1) / pageOffset) * pageOffset + 1;
	var lastPage = Math.floor((currPage - 1) / pageOffset) * pageOffset	+ pageOffset;
	var currPageCnt = Math.ceil(currPage / pageOffset);
	var lastPageCnt = Math.ceil((totalPage) / pageOffset);
	var nextPage = lastPage + 1;
	var prevPage = firstPage - 1;

	if (lastPage > totalPage){
		lastPage = totalPage;
	}
	
	var first = $("<li class='page-item'></li>")
	
	if (currPage == 1) { //현재페이지가 첫페이지면 << 버튼 클릭 비활성화
		first.addClass('disabled');
	}
	
	var a = $("<a class='page-link' href='javascript:page(1);' aria-label='Previous'><span aria-hidden='true'>«</span></a>");
	a.appendTo(first);
	first.appendTo('#' + tagName);

	if (currPage > pageOffset) { //현재 페이지가 첫 페이지 리스트일 때 < 비활성화
		
		var prev = $("<li class='page-item'></li>")
		// prev.addClass('disabled');
		var a = $("<a class='page-link' href='javascript:page("
				+ prevPage
				+ ");' aria-label='Previous'><span aria-hidden='true'>＜</span></a>");
		a.appendTo(prev);
		prev.appendTo('#' + tagName);
	}
	
	for (var i = firstPage; i <= lastPage; i++) { //현재 페이지갯수 만큼 리스트 표출
		var li = $("<li class='page-item'></li>");
		if (currPage == i)
			li.addClass("active");
		var a = $("<a class='page-link' href='javascript:page(" + i + ");'></a>");
		a.append(i);
		a.appendTo(li);
		li.appendTo("#" + tagName);
	}
	
	if (currPageCnt < lastPageCnt) { //현재 페이지 리스트가 마지막 페이지 리스트보다 작을 때

		var next = $("<li class='page-item'></li>")
		//next.addClass('disabled');
		var a = $("<a class='page-link' href='javascript:page("
				+ nextPage
				+ ");' aria-label='Previous'><span aria-hidden='true'>＞</span></a>");
		a.appendTo(next);
		next.appendTo('#' + tagName);
	}

	var last = $("<li class='page-item'></li>")
	
	if (currPage == totalPage) {
		last.addClass('disabled');
	}
	
	var a = $("<a class='page-link' href='javascript:page(" + totalPage
			+ ");' aria-label='Previous'><span aria-hidden='true'>»</span></a>");
	a.appendTo(last);
	last.appendTo('#' + tagName);
	
	//var hidden = $("<input type='hidden'  id = 'code' value= " + pageInfo.code + ">");
	//hidden.appendTo('#' + tagName);

};
