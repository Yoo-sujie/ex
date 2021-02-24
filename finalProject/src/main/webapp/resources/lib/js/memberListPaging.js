$(document).ready(function() {
   
	var pageInfo = {
            pageNo : 1,
            totalCount : $('#totalCnt').val(),
            offset : 10,
            pages : 10
         };
   //ajaxUtils.doPost("member/memberStopped.do",param,myPageListView);
	console.log('ddddd');
   paging(pageInfo, 'PageWrap');

   
   //$(document).on('click','#manageBtn', clickedBtn);
});


/*function clickedBtn() {
	
}*/

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
   ajaxUtils.doPost("member/memberStopped.do",param,myPageListPageView);
}
function myPageListPageView(dataList){
   
   $('#memberListTd').html('');
   
   var keyList = [ 'member_id','member_name','member_nic','member_phone','member_email','member_join_date','sell_no','member_status'];
   
   var key = '';
   console.log(dataList.MemberList);
   if (dataList.MemberList) {
      // if(true){
      if (dataList.MemberList.length > 0) {
         // if(true){
         for (var i = 0; i < dataList.MemberList.length; i++) {
            var tr = $("<tr></tr>");

            for (var j = 0; j < keyList.length; j++) {
               var td = $("<td></td>");

               
               key = keyList[j];
               
               if(key=='member_status') {
            	   
            	   if(dataList.MemberList[i][key] == 1){
            		   var link = 'StopMember.do?id=';
            		   
            		   td.append('<input type="button" id="manageBtn" value="회원정지" style="padding:3px " onclick="location.href="' + link + dataList.MemberList[i]["member_id"] +'>');
            		   
            	   }else if(dataList.MemberList[i][key] ==2) {
            		   var link = 'undoStopMember.do?id=';
            		   td.append('<input type="button" id="manageBtn2" value="정지해제" style="padding:3px " onclick="location.href="' + link + dataList.MemberList[i]["member_id"] +'>');
            		   
            		   
            	   }
               }else {
            	   td.append(dataList.MemberList[i][key]);
               }
               
               
               td.appendTo(tr);
            }
            tr.appendTo('#memberListTd');
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