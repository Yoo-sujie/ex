var gisUtil, ajaxUtils;
var searchParam;




$(document).ready(function() {
	console.log('1');
		

	  gisUtil = gis();
	  ajaxUtils = ajaxUtil();
	  //$(document).delegate('#categoryList1','click',category2);
	  
	  
	  
	  ajaxUtils.doPost('map/bigCategoryList.do','',category1);
	  
	  $(document).on('change','#categoryList1',categoryList2);
	  
	  function categoryList2(){
		  $('#categoryList2').val('all');
		  $('#categoryList3').val('all');
		  
		  
		  
		  $('#categoryList3').html('');
		  
		  var th1 = $("<option value='all'>소분류</option>");
		  th1.appendTo('#categoryList3');
		  if($('#categoryList1 option:selected').val() == 'all'){
			  
			  $('#categoryList2').html('');
			  
			  var th1 = $("<option value='all'>중분류</option>");
			  th1.appendTo('#categoryList2');
		  }else{
		  
			  $('#categoryList2').html('');
			  
			  var th1 = $("<option value='all'>중분류</option>");
			  th1.appendTo('#categoryList2');
		  
		  
			  param  = {
					  no : $('#categoryList1 option:selected').val()
			  };
			  console.log(param);
			  ajaxUtils.doPost('map/middleCategoryList.do',param,category2);
		  }
		  
		  layerName = $('#mapList option:selected').val();
		  gisUtil.setVisibleSafeLayer(layerName);
		}
	  
	  function category2(dataList){
			console.log(dataList.list);
			
			if (dataList.list) {
				// if(true){
				if (dataList.list.length > 0) {
					// if(true){
					for (var i = 0; i < dataList.list.length; i++) {
						var th1 = $("<option value='" + dataList.list[i].SELL_CATEGORY_NO + "'>" + dataList.list[i].SELL_CATEGORY_NAME +"</option>");
						th1.appendTo('#categoryList2');
						
					}
					
					
				}
				
			}
			
			
		}
	  
	  
	  $(document).on('change','#categoryList2',categoryList3);
	  
	  function categoryList3(){
		  $('#categoryList3').val('all');
		  
		  
		  
		  if($('#categoryList2 option:selected').val() == 'all'){
			  $('#categoryList3').html('');
			  var th1 = $("<option value='all'>소분류</option>");
			  th1.appendTo('#categoryList3');
		  }else{
		  
			  	$('#categoryList3').html('');
				var th1 = $("<option value='all'>소분류</option>");
				th1.appendTo('#categoryList3');
				$('#categoryList3').val('all');
			  
			  $('#categoryList3').val('all');
		  	  param  = {
				  	no : $('#categoryList2 option:selected').val()
		  			 };
		  	  console.log(param);
		  	  ajaxUtils.doPost('map/middleCategoryList.do',param,category3);
		  }
		  
		  layerName = $('#mapList option:selected').val();
		  gisUtil.setVisibleSafeLayer(layerName);
		}
	  
	  function category3(dataList){
			console.log(dataList.list);
			
			if (dataList.list) {
				// if(true){
				if (dataList.list.length > 0) {
					// if(true){
					for (var i = 0; i < dataList.list.length; i++) {
						var th1 = $("<option value='" + dataList.list[i].SELL_CATEGORY_NO + "'>" + dataList.list[i].SELL_CATEGORY_NAME +"</option>");
						th1.appendTo('#categoryList3');
						
					}
					
					
				}
				
			}
			
			
		}
	  
	  
	  
	  $(document).on('change','#categoryList3',categoryList4);
	  
	  function categoryList4(){
		 
		  
		  layerName = $('#mapList option:selected').val();
		  gisUtil.setVisibleSafeLayer(layerName);
	  }
	  
	  gisUtil.init();
	  
	  
	  
	  //$(document).on('click','#mapChange',gisUtil.mapChange);
	  
	  $(document).on('change','#mapList',gisUtil.mapChange);
	  //$(document).on('change','#categoryList1',gisUtil.mapChange2);
	  
	  
	  
	  
	  $(document).delegate('#mapTable tr', 'click', clickedMapTable);
	  
	  
	  
	 
	  
	  $(document).delegate('#mapTable2 tr', 'click', clickedMapTable2);
	  
	  $(document).on('click', '#sellPlaceView', sellPlaceView);
	  
});



function category1(dataList){
	console.log(dataList.list);
	
	
	
	
	if (dataList.list) {
		// if(true){
		if (dataList.list.length > 0) {
			// if(true){
			for (var i = 0; i < dataList.list.length; i++) {
				var th1 = $("<option value='" + dataList.list[i].SELL_CATEGORY_NO + "'>" + dataList.list[i].SELL_CATEGORY_NAME +"</option>");
				th1.appendTo('#categoryList1');
				
			}
			
			
		}
		
	}
	
	
	
	
	
}


function category2(){
	
}

function clickedMapTable(){
	var menu = $('#mapList option:selected').val();

	
	var tr = $(this)
	var td = tr.children();
	console.log(td.eq(0).html());
	var check = td.eq(0).html();
	if(check != '지역' ){
		
		var param = {
				menu : menu,
				code : td.eq(3).val(),
				currPage : 1,		//현재 페이지
				startIndex : (1 - 1) * 10 + 1,	// 처음
				lastIndex : (1 - 1) * 10 + 10	// 마지막
		}
		ajaxUtils.doPost("map/sellListView.do",param,sellListView);
		
	}
	

	

	

}
function clickedMapTable2(){

	
	var tr = $(this)
	var td = tr.children();
	console.log(td.eq(3).val());
	var check = td.eq(0).html();
	if(check != '지역' ){
		
		var bg = $('#categoryList1 option:selected').val();
    	var md = $('#categoryList2 option:selected').val();
    	var sm = $('#categoryList3 option:selected').val();
    	console.log(bg);
    	console.log(md);
    	console.log(sm);
    	var category = '';
    	if(bg == 'all'){			//대분류가 전체일 경우
    		category = '______';
    	}else if(md == 'all'){		//대분류가 전체가 아니고 중분류가 전체일 때
    		category = bg  +'____';
    	}else if(sm == 'all'){		//대분류가 전체가 아니고 중분류가 전체가 아니고 소분류가 전체일 때
    		category = md + '__';
    	}else{						//모두 all이 아닐 때
    		category = sm;
    	}
		location.href="/search/searchPage.do?sellPlace=" + td.eq(3).val() + "&category=" + category;
		//ajaxUtils.doPost("map/sellListView.do",param,sellListView);
		
	}
	

	

	

}


function sellPlaceView(){

	var sellPlaceViewParam = $('#sellPlaceViewParam').val();
	console.log(sellPlaceViewParam);
	
		
		var bg = $('#categoryList1 option:selected').val();
    	var md = $('#categoryList2 option:selected').val();
    	var sm = $('#categoryList3 option:selected').val();
    	console.log(bg);
    	console.log(md);
    	console.log(sm);
    	var category = '';
    	if(bg == 'all'){			//대분류가 전체일 경우
    		category = '______';
    	}else if(md == 'all'){		//대분류가 전체가 아니고 중분류가 전체일 때
    		category = bg  +'____';
    	}else if(sm == 'all'){		//대분류가 전체가 아니고 중분류가 전체가 아니고 소분류가 전체일 때
    		category = md + '__';
    	}else{						//모두 all이 아닐 때
    		category = sm;
    	}
		location.href="/search/searchPage.do?sellPlace=" + sellPlaceViewParam + "&category=" + category;
		//ajaxUtils.doPost("map/sellListView.do",param,sellListView);
		
	
	

	

	

}


function sellListView(dataList){
	
	console.log(dataList);
	
	
	
	
	
	
	
	$('#mth2').html('');
	$('#mtd2').html('');
	$("#PageWrap").html('');
	
	var thtr = $("<tr></tr>");
	var th1 = $("<th scope='col' class='text-center'></th>");
	th1.append('#');
	th1.appendTo(thtr);
	var th2 = $("<th scope='col' class='text-center'></th>");
	th2.append('판매장소');
	th2.appendTo(thtr);
	var th3 = $("<th scope='col'class='text-center'></th>");
	th3.append('판매물품 갯수');
	th3.appendTo(thtr);
	thtr.appendTo('#mth2');
	
	
	
	var keyList = [ '#', 'SELL_PLACE_NAME','CNT'];
	
	var key = '';
	console.log(dataList.list);
	
	if (dataList.list) {
		// if(true){
		if (dataList.list.length > 0) {
			// if(true){
			for (var i = 0; i < dataList.list.length; i++) {
				var thead = $("<><>");
				var tr = $("<tr></tr>");

				for (var j = 0; j < keyList.length; j++) {
					var td = $("<td></td>");

					if (j == 0) {
						//td.append(dataList.list[i]["RNUM"]);
						td.append(dataList.list[i]["RNUM"]);
					} else {
						key = keyList[j];				
						td.append(dataList.list[i][key]);
						
					}
					
					td.appendTo(tr);
				}
				var td4 = $('<input type="hidden" id = sellList' + dataList.list[i]["RNUM"] +' value = ' + dataList.list[i]['SELL_PLACE_NO'] + '>');
	        	
	        	td4.appendTo(tr);
				tr.appendTo('#mtd2');
			}
			var pageInfo = {
					pageNo : dataList.currPage,
					totalCount : dataList.totalCnt,
					code : dataList.paramMap['code'],
					offset : 10,
					pages : 10
				};
			
				paging(pageInfo, 'PageWrap');
		}
		
	}
	
	
}

function page(page){
	var menu = $('#mapList option:selected').val();
	var code = $('#code').val();
	
	var param = {
			menu : menu,
			code : code,
			currPage : page,
			startIndex : (page - 1) * 10 + 1,
			lastIndex : (page - 1) * 10 + 10
	}
	ajaxUtils.doPost("map/sellListView.do",param,sellListView);
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


