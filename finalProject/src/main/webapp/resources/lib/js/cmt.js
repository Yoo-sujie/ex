
$(document).ready(function() {
	ajaxUtils = ajaxUtil();
	$(document).on('click','#commentBtn', clickCmtBtn);
}); 

function clickCmtBtn(){
	
	var date = new Date(); 
	var year = date.getFullYear().toString().slice(-2); //연 두자리
	var month = date.getMonth()+1; //월
	var day = date.getDate(); //일
	if ((day+"").length < 2) { //일이 한자리 수인 경우 앞에 0
		day = "0" + day;
	}
    var	sysdate = year+"/"+month+"/"+day; 
	
	var param = {
			no : $("#boardNumber").val(),
			writer : $("#commentWriter").val(),
			content : $("#commentCnt").val(),
			sysdate : sysdate
		}
	ajaxUtils.doPost("/serviceCenter/addCmt.do", param, addCmt);
}

function addCmt(data){ 
	//console.log(data.result);
	//console.log(data.comment);
	$('#cmtForm').html('');
	$('#cmtTr').html('');
	var keyList = ['SC_QNA_COMMENT_WRITER','SC_QNA_COMMENT_CONTENTS','SC_QNA_COMMENT_WRITE_DATE'];
	var key = '';
	//console.log(data.result);
	if (data.comment) {
		for (var j = 0; j < keyList.length; j++) {
			var td = $("<td></td>");
			key = keyList[j];
			td.append(data.comment[key]);
			//console.log(td);
			td.appendTo('#cmtTr');
		}
	}
	$('#status').html(data.map['SC_QNA_STATUS']);
}