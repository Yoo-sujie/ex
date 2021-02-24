$(document).ready(function() {

	ajaxUtils = ajaxUtil();

	$(document).on('change', '#categoryList1', categoryList2);

	$(document).on('change', '#categoryList2', categoryList3);

	$(document).on('click', '#search', search);
	
	$(document).on('click', '#seller', seller);
	
	$(document).on('change', '#tableToggle', tableToggle);
	
	
	$(document).delegate('#sellSearchTable tr', 'click', clickedSellSearchTable);

	if($("#totalCnt").val()>1){
		var pageInfo = {
				pageNo : 1,
				totalCount : $("#totalCnt").val(),
				offset : 10,
				pages : 10
			};

			paging(pageInfo, 'PageWrap');
	}
	

});


function tableToggle(){
	
	$("#detailTable").toggle();
	$("#cardTable").toggle();
	
}


function clickedSellSearchTable(){

	
	
	var tr = $(this)
	var td = tr.children();
	console.log(td.eq(0).html());
	var check = td.eq(0).html();
	
	if(check != '#'){
		 location.href="/buyer/buyerContentView.do?sell_no=" + check;
	}
}


function categoryList2() {
	$('#categoryList2').val('all');
	$('#categoryList3').val('all');

	$('#categoryList3').html('');

	var th1 = $("<option value='all'>소분류</option>");
	th1.appendTo('#categoryList3');
	if ($('#categoryList1 option:selected').val() == 'all') {

		$('#categoryList2').html('');

		var th1 = $("<option value='all'>중분류</option>");
		th1.appendTo('#categoryList2');
	} else {

		$('#categoryList2').html('');

		var th1 = $("<option value='all'>중분류</option>");
		th1.appendTo('#categoryList2');

		param = {
			no : $('#categoryList1 option:selected').val()
		};
		console.log(param);
		ajaxUtils.doPost('/map/middleCategoryList.do', param, category2);
	}

}

function category2(dataList) {
	console.log(dataList.list);

	if (dataList.list) {
		// if(true){
		if (dataList.list.length > 0) {
			// if(true){
			for (var i = 0; i < dataList.list.length; i++) {
				var th1 = $("<option value='"
						+ dataList.list[i].SELL_CATEGORY_NO + "'>"
						+ dataList.list[i].SELL_CATEGORY_NAME + "</option>");
				th1.appendTo('#categoryList2');

			}

		}

	}

}
function categoryList3() {
	$('#categoryList3').val('all');

	if ($('#categoryList2 option:selected').val() == 'all') {
		$('#categoryList3').html('');
		var th1 = $("<option value='all'>소분류</option>");
		th1.appendTo('#categoryList3');
	} else {
		$('#categoryList3').html('');
		var th1 = $("<option value='all'>소분류</option>");
		th1.appendTo('#categoryList3');
		$('#categoryList3').val('all');
		param = {
			no : $('#categoryList2 option:selected').val()
		};
		console.log(param);
		ajaxUtils.doPost('/map/middleCategoryList.do', param, category3);
	}


}

function category3(dataList) {
	console.log(dataList.list);

	if (dataList.list) {
		// if(true){
		if (dataList.list.length > 0) {
			// if(true){
			for (var i = 0; i < dataList.list.length; i++) {
				var th1 = $("<option value='"
						+ dataList.list[i].SELL_CATEGORY_NO + "'>"
						+ dataList.list[i].SELL_CATEGORY_NAME + "</option>");
				th1.appendTo('#categoryList3');

			}

		}

	}

}

function search() {
	var sellPlace = $('#sellPlace option:selected').val();
	
	var categoryList1 = $('#categoryList1 option:selected').val();
	var categoryList2 = $('#categoryList2 option:selected').val();
	var categoryList3 = $('#categoryList3 option:selected').val();

	var category = '';
	if(categoryList1 == 'all'){			//대분류가 전체일 경우
		category = '______';
	}else if(categoryList2 == 'all'){		//대분류가 전체가 아니고 중분류가 전체일 때
		category = categoryList1  +'____';
	}else if(categoryList3 == 'all'){		//대분류가 전체가 아니고 중분류가 전체가 아니고 소분류가 전체일 때
		category = categoryList2 + '__';
	}else{						//모두 all이 아닐 때
		category = categoryList3;
	}
	
	var searchItem = $('#searchItem option:selected').val();
	var searchTxt = $("#searchTxt").val();
	var minPrice = $("#minPrice").val();
	var maxPrice = $("#maxPrice").val();
	var sellStatus = $('#sellStatus option:selected').val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	/*location.href = "/search/searchPage.do?sellPlace=" + sellPlace + "&category = " + category + "&searchItem = " + searchItem  
					+ "&searchTxt = " + searchTxt + "&minPrice = " + minPrice + "&maxPrice = " + maxPrice
					+  "&sellStatus = " + sellStatus + "&startDate = " + startDate + "&endDate = " + endDate;*/
	
	
	param = {
			currPage : 1,
			startIndex : 1,
			lastIndex : 10,
			sellPlace : sellPlace,
			category : category,
			searchItem : searchItem,
			searchTxt : searchTxt,
			minPrice : minPrice,
			maxPrice : maxPrice,
			sellStatus : sellStatus,
			startDate : startDate,
			endDate : endDate
			
	}
	$("#searchSellPlace").val(sellPlace);
	$("#searchCategory").val(category);
	$("#searchSearchItem").val(searchItem);
	$("#searchSearchTxt").val(searchTxt);
	$("#searchMinPrice").val(minPrice);
	$("#searchMaxPrice").val(maxPrice);
	$("#searchSellStatus").val(sellStatus);
	$("#searchStartDate").val(startDate);
	$("#searchEndDate").val(endDate);
	
	ajaxUtils.doPost('/search/sellListView.do', param, sellListView);
	
}

function sellListView(dataList) {

	console.log(dataList);

	$('#sstd').html('');
	
	$('#cardTable').html('');
	$("#PageWrap").html('');
	
	

	var keyList = [ 'SELL_NO', 'SELL_PLACE_NAME', 'SELL_CATEGORY_NAME', 'SELL_IMAGE',
			'SELL_TITLE', 'SELL_PRICE', 'MEMBER_NIC', 'SELL_STATUS',
			'SELL_CNT', 'SELL_WRITE_DATE' ];

	var key = '';
	console.log(dataList.list);
	console.log(dataList.list.length);
	if (dataList.list) {
		// if(true){
		if (dataList.list.length > 0) {
			// if(true){
			
			for (var i = 0; i < dataList.list.length; i++) {
				//리스트형-----------------------
				var thead = $("<><>");
				var tr = $("<tr></tr>");

				for (var j = 0; j < keyList.length; j++) {
					var td = $("<td scope='col' class='text-center' height='80' style='vertical-align : middle; height: 80 !important;'></td>");
			
					key = keyList[j];
					
					if(key == 'SELL_IMAGE'){
						if(dataList.list[i][key] == null){
							td.append('<img src="/resources/lib/images/main-test(1).jpg" class = "img-thumbnail" alt="Cinque Terre" style=" width : 85; height: 60 !important;">');
						}else{
							td.append('<img src="' + dataList.list[i][key] + '" class = "img-thumbnail" alt="Cinque Terre"  style=" width : 85; height: 60 !important;">');
						}
						
					}else{
						td.append(dataList.list[i][key]);
					}
					

					td.appendTo(tr);
				}
				tr.appendTo('#sstd');
				
				
				//카드형-----------
				
				
				var image
				if(dataList.list[i]["SELL_IMAGE"] == null){
					
					image = '/resources/lib/images/main-test(1).jpg';
					console.log(image);
				}else{
					console.log(dataList.list[i]["SELL_IMAGE"]);
					
					image = dataList.list[i]["SELL_IMAGE"];
				}
				
				var div = $('<div id="sellSearchTable2" class="search_div2">');
				
				var a = $('<a href="/buyer/buyerContentView.do?sell_no=' + dataList.list[i]["SELL_NO"] + '"></a>');
				var div_card = $('<div class="card"></div>');
				
				var div_card_header = $('<div class="card-header"></div>');
				var div_header = $('<div class = "card-header-is_closed" ></div>');
				var div_header_text = $('<div class = "card-header-text" >' + dataList.list[i]["CARD_HEADER"] + '</div>');
				var img = $('<img src="' + image + '" class="img-thumbnail" alt="Cinque Terre" width="322" height="250">');
				
				var div_card_body = $('<div class="card-body"></div>');
				
				var div_body = $('<div class="card-body-header"></div>');
				
				var h1 = $('<h1>' + dataList.list[i]["SELL_TITLE_STR"] + '</h1>');
				
				var p = $('<p class="card-body-hashtag">#' + dataList.list[i]["SELL_PLACE_NAME"] + ' #' + dataList.list[i]["SELL_CATEGORY_NAME"] + ' #' + dataList.list[i]["SELL_PRICE"] + '</p>');
				
				var div_card_footer = $('<div class="card-body-footer"></div>');
				var hr = $('<hr style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">');
				var i_icon = $('<i class="icon icon-view_count"></i>');
				var label = $('<label>조회' + dataList.list[i]["SELL_CNT"] + '회</label>');
				var i_reg = $('<i class="reg_date">' +  dataList.list[i]["SELL_WRITE_DATE"] + ' </i>');
				
				
				if(dataList.list[i]["CARD_HEADER"] != 'NOT'){
					div_header.appendTo(div_card_header);
					div_header_text.appendTo(div_header);
					
				}
				img.appendTo(div_card_header);
				
				h1.appendTo(div_body);
				p.appendTo(div_body);
				
				hr.appendTo(div_card_footer);
				i_icon.appendTo(div_card_footer);
				i_reg.appendTo(div_card_footer);
				label.appendTo(div_card_footer);
				
				div_body.appendTo(div_card_body);
				div_card_footer.appendTo(div_card_body);
				
				div_card_header.appendTo(div_card);
				div_card_body.appendTo(div_card);
				
				div_card.appendTo(a);
				
				div
				a.appendTo(div);
				
				div.appendTo('#cardTable');
				
			}
			var pageInfo = {
				pageNo : dataList.currPage,
				totalCount : dataList.totalCnt,
				offset : 10,
				pages : 10
			};

			paging(pageInfo, 'PageWrap');
		}else{
			console.log('1');
			var tr2 = $('<tr><td colspan="10">데이터가 없습니다.</td></tr>'); 
			tr2.appendTo('#sstd');
		}

	}

}

function page(page) {
	
	
	
	
	
	
	
	
	
	
	var param = {
		currPage : page,
		startIndex : (page - 1) * 10 + 1,
		lastIndex : (page - 1) * 10 + 10,
		sellPlace : $("#searchSellPlace").val(),
		category : $("#searchCategory").val(),
		searchItem : $("#searchSearchItem").val(),
		searchTxt : $("#searchSearchTxt").val(),
		minPrice : $("#searchMinPrice").val(),
		maxPrice : $("#searchMaxPrice").val(),
		sellStatus : $("#searchSellStatus").val(),
		startDate : $("#searchStartDate").val(),
		endDate : $("#searchEndDate").val()
	}
	ajaxUtils.doPost("/search/sellListView.do", param, sellListView);
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



function seller() {
	location.href="/seller/sellPlaceSelectSi.do";
}