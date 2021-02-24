
var htmlTagUtil = function () {

    var MESSAGE_NO_DATA = 'No Data';

    function buildListTable(parentTagName ,dataList, codeKeyName) {
    	console.log(parentTagName);
    	console.log(dataList);
    	console.log(codeKeyName);
        $('#'+parentTagName).html('');

        var ul = $("<ul></ul>");
        ul.addClass("detail_sub");

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++){
                    var dataObj = dataList[i];
                    var li = $("<li></li>");

                    var btnPin = $("<a class=\"btnPin btn-gray\"><i class=\"material-icons\">pin_drop</i></a>");
                    btnPin.attr('fid', dataObj.id);
                    btnPin.appendTo(li);
                    var infoTitle = $("<span class=\"infoTitle\"></span>");
                    infoTitle.append(dataObj.BLD_FRM_NM);
                    infoTitle.appendTo(li);
                    var infoAddr = $("<span></span>");
                    infoAddr.append(dataObj.BLD_ADDR);
                    infoAddr.appendTo(li);
                    var btnInfo = $("<a class=\"btnInfo btn-gray\"><i class=\"material-icons\">info_outline</i></a>");
                    btnInfo.attr('code', dataObj.PART_CD);
                    btnInfo.attr('no', dataObj.CST_NO);
                    btnInfo.appendTo(li);
                    li.appendTo(ul);
                }
            }
            else {
                /*var tr = $("<tr></tr>");
                var td = $("<td></td>");
                td.append(MESSAGE_NO_DATA);
                td.appendTo(tr);*/
            }
        }
        ul.appendTo('#'+parentTagName);
    };

    function listSelectOptions(tagName, dataList) {
        $('#'+tagName).html('');

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++) {
                    var dataObj = dataList[i];
                    var options = $("<option></option>");
                    options.append(dataObj.name);
                    options.val(dataObj.code);
                    options.appendTo('#'+tagName);
                }
            }
        }
    }


    function listSelectYears(tagName, dataList, index) {
        $('#'+tagName).html('');

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++) {
                    var dataObj = dataList[i];
                    var options = $("<option></option>");
                    if(i == index)
                        options.attr("selected", "selected");
                    options.append(dataObj.YEAR+"년");
                    options.val(dataObj.YEAR);
                    options.appendTo('#'+tagName);
                }
            }
        }
    }

    function listSelectOptionByKey(tagName, dataList, keyObj) {
        $('#'+tagName).html('');

        if(dataList){
            if(dataList.length > 0){
                var tempOptions = $("<option></option>");
                tempOptions.append('선택');
                tempOptions.val('');
                tempOptions.appendTo('#'+tagName);

                for (var i = 0; i < dataList.length; i++) {
                    var dataObj = dataList[i];
                    var options = $("<option></option>");
                    options.append(dataObj[keyObj.name]);
                    options.val(dataObj[keyObj.value]);
                    options.appendTo('#'+tagName);
                }
            }
        }
    }

    function listSelectBoundLevel(tagName, dataList) {
        $('#'+tagName).html('');

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++) {
                    var dataObj = dataList[i];
                    var options = $("<option></option>");
                    options.append(dataObj.CODE_NM);
                    options.val(dataObj.LAYER_NM);
                    options.appendTo('#'+tagName);
                }
            }
        }
    }

    function listArchResultList(tagName ,dataList) {
        $('#'+tagName).html('');
        var keyList = ['rnum', 'platPlc', 'platGbCd', 'bldNm', 'hstpGbCdNm', 'crtnDay'];

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++){
                    var tr = $("<tr></tr>");

                    for(var j = 0; j < keyList.length; j++){
                        var td = $("<td></td>");
                        td.append(dataList[i][keyList[j]]);
                        td.appendTo(tr);
                    }

                    tr.appendTo('#'+tagName);
                }
            }else{
           	 var tr = $("<tr></tr>");
           	 for(var j = 0; j < keyList.length; j++){
                    var td = $("<td></td>");
                    td.append(dataList[keyList[j]]);
                    td.appendTo(tr);
                }
           	 tr.appendTo('#'+tagName);
            }
        }
    }


    function listGeoResultList(tagName ,dataList) {
        $('#'+tagName).html('');
        var keyList = ['G_NO', 'PART_NAME', 'BLD_FRM_NM', 'BLD_ADDR', 'OLD_JUSO', 'GEO_FLAG', 'LNG', 'LAT', 'UPDT_DATE'];

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++){
                    var tr = $("<tr></tr>");

                    var tdCk = $("<td></td>");
                    var input = $("<input type=\"checkbox\" name=\"geo[1][]\">");
                    input.attr('part', dataList[i]['PART_CD']);
                    input.attr('cst', dataList[i]['CST_NO']);
                    input.addClass('geoChk');
                    input.appendTo(tdCk);
                    tdCk.appendTo(tr);

                    for(var j = 0; j < keyList.length; j++){
                        var td = $("<td></td>");
                        td.append(dataList[i][keyList[j]]);
                        td.appendTo(tr);
                    }
                    tr.appendTo('#'+tagName);
                }

            }
        }
    }

    function listApiResultList(tagName ,dataList) {
        $('#'+tagName).html('');
        var keyList = ['API_NO', 'API_NAME', 'API_KEY', 'API_DESC', 'API_ISSUE_DATE', 'API_PERIOD', 'API_RENUE_DATE', 'API_REGI_DATE'];

        if(dataList){
            if(dataList.length > 0){
                for (var i = 0; i < dataList.length; i++){
                    var tr = $("<tr></tr>");

                    var tdCk = $("<td></td>");
                    var input = $("<input type=\"checkbox\" name=\"api[1][]\">");
                    input.val(dataList[i]['API_SEQ']);
                    input.addClass('apiChk');
                    input.appendTo(tdCk);
                    tdCk.appendTo(tr);

                    for(var j = 0; j < keyList.length; j++){
                        var td = $("<td></td>");
                        td.append(dataList[i][keyList[j]]);
                        td.appendTo(tr);
                    }
                    tr.appendTo('#'+tagName);
                }

            }
        }
    }

    function viewGeoResult(dataObj) {
        var tagNames = [
            {key: 'PART_CD', tag: '#geoPartCd', type: 'value'},
            {key: 'CST_NO', tag: '#geoCstNo', type: 'value'},
            {key: 'PART_NAME', tag: '#geoPart', type: 'text'},
            {key: 'BLD_FRM_NM', tag: '#geoGogak', type: 'text'},
            {key: 'BLD_ADDR', tag: '#geoAddr1', type: 'text'},
            {key: 'OLD_JUSO', tag: '#geoAddr2', type: 'text'},
            {key: 'GEO_FLAG', tag: '#geoFlag', type: 'text'},
            {key: 'LNG', tag: '#geoLng', type: 'value'},
            {key: 'LAT', tag: '#geoLat', type: 'value'},
            {key: 'UPDT_DATE', tag: '#geoDate', type: 'text'}
        ];

        tagNames.forEach(function (tags) {
            if(tags.type == 'text')
                $(tags.tag).text(dataObj[tags.key]);
            else
                $(tags.tag).val(dataObj[tags.key]);
        });

    }

    function viewApiResult(dataObj) {
        var tagNames = [
            {key: 'API_SEQ', tag: '#eapiSeq', type: 'text'},
            {key: 'API_NAME', tag: '#eapiName', type: 'text'},
            {key: 'API_DESC', tag: '#eapiDesc', type: 'text'},
            {key: 'API_PERIOD', tag: '#eapiPeriod', type: 'text'},
            {key: 'API_KEY', tag: '#eapiKey', type: 'text'},
            {key: 'API_ISSUE_DATE', tag: '#eapiSdate', type: 'date'},
            {key: 'API_RENUE_DATE', tag: '#eapiEdate', type: 'date'},
        ];

        tagNames.forEach(function (tags) {
            if(tags.type == 'text')
                $(tags.tag).val(dataObj[tags.key]);
            else{
                var dt = new Date(dataObj[tags.key]);
                $(tags.tag).val(dt.toISOString().slice(0,10));
            }
        });

    }

    function paging(pageInfo, tagName) {
        var info = pageInfo;
        $("#"+tagName).html('');


        var currPage = info.pageNo;
        var offset = info.offset;
        var totalPage = Math.ceil(info.totalCount / offset);
        var pageOffset = info.pages;

        var firstPage = Math.floor(( currPage - 1 ) / pageOffset) * pageOffset + 1;
        var lastPage = Math.floor(( currPage - 1 ) / pageOffset) * pageOffset + pageOffset;
        var currPageCnt = Math.ceil( currPage / pageOffset);
        var lastPageCnt = Math.ceil(( totalPage - 1 ) / pageOffset);
        var nextPage = lastPage + 1;
        var prevPage = firstPage - 1;
        if(lastPage > totalPage)
            lastPage = totalPage;

        
            var first = $("<span></span>");
            first.addClass('btn_group');
            if(currPage > 1){
            	var a = $("<a href='javascript:page(1);'><i class='material-icons'>first_page</i></a>");
            }else{
            	var a = $("<a><i class='material-icons'>first_page</i></a>");
            }
            a.appendTo(first);
            first.appendTo('#'+tagName);
        

            var prev = $("<span></span>");
            prev.addClass('btn_group');
            if(currPage > pageOffset){
            	var a = $("<a href='javascript:page("+prevPage+");'><i class='material-icons'>chevron_left</i></a>");
            }else{
            	var a = $("<a><i class='material-icons'>chevron_left</i></a>");
            }
            a.appendTo(prev);
            prev.appendTo('#'+tagName);

        var ul = $("<ul></ul>")
        for (var i = firstPage; i <= lastPage; i++) {
            var li = $("<li></li>");
            if(currPage == i)
                li.addClass("active");
            var a = $("<a href='javascript:page("+i+");'></a>");
            a.append(i);
            a.appendTo(li);
            li.appendTo(ul);
        }
        ul.appendTo("#"+tagName);

       
            var next = $("<span></span>");
            next.addClass('btn_group');
            if(lastPage < totalPage) {
            var a = $("<a href='javascript:page("+nextPage+");'><i class='material-icons'>chevron_right</i></a>");
            }else{
                var a = $("<a><i class='material-icons'>chevron_right</i></a>");
                }
            a.appendTo(next);
            next.appendTo('#'+tagName);
        


        
            var last = $("<span></span>");
            last.addClass('btn_group');
            if(currPage < totalPage) {
                var a = $("<a href='javascript:page("+totalPage+");'><i class='material-icons'>last_page</i></a>");
                }else{
                var a = $("<a><i class='material-icons'>last_page</i></a>");
                }
            var a = $("<a href='javascript:page("+totalPage+");'><i class='material-icons'>last_page</i></a>");
            a.appendTo(last);
            last.appendTo('#'+tagName);
        


    /*    #if($startPage > $indexSize)
            <span class="btn_group"><a href="javascript:${jsFunction}(1);"><i class="material-icons">first_page</i></a> </span>

	#end

	#if($page > 10)
            #set($prevIdx = $page - 10)
        <span class="btn_group"> <a href="javascript:${jsFunction}(${prevIdx});"><i
    class="material-icons">chevron_left</i></a></span>
	#end
        <ul class="${style_list}">
	#foreach( $idx in [$startPage..$endPage] )
		#if($idx != $page)
            <li><a type="button" href="javascript:${jsFunction}(${idx});">$idx</a></li>
		#else
    <li class="active"><a href="javascript:${jsFunction}(${idx});">$idx</a></li>
		#end
	#end


        </ul>
	#if($page < $totalPage)
            #if($page /10 != ($totalPage - 1) /10)
            #set($nextIdx = ((($page - 1) / 10) * 10 + 11))

        <span class="btn_group"> <a href="javascript:${jsFunction}(${nextIdx});"><i
    class="material-icons">chevron_right</i></a></span>

		#end
	#end
	#if($currentPageCount < $lastPageCount)
            <span class="btn_group"> <a href="javascript:${jsFunction}(${totalPage});"><i class="material-icons">last_page</i></a></span>
	#end*/



    };

    return {
        displayResultList : buildListTable,
        listSelectOptions : listSelectOptions,
        listSelectOptionByKey : listSelectOptionByKey,
        listArchResultList : listArchResultList,
        listSelectYears: listSelectYears,
        listSelectBoundLevel: listSelectBoundLevel,
        listGeoResultList: listGeoResultList,
        listApiResultList: listApiResultList,
        viewGeoResult :viewGeoResult,
        viewApiResult : viewApiResult,
        paging : paging
    };
};

