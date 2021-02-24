
$(document).ready(function() {
	ajaxUtils = ajaxUtil();

	
	$(document).on('click','#insertSellPlaceBtn',ClickInsertSellPlaceBtn);

});


function ClickInsertSellPlaceBtn(){
	
	var name = $("#sellPlaceName").val();
	var addr = $("#roadAddrPart1").val();
	var lat = $("#geoLat").val();
	var lon = $("#geoLon").val();
	var item = $("#sellPlaceItem option:selected").val();
	
	console.log(name);
	console.log(addr);
	console.log(lat);
	console.log(lon);
	console.log(item);
	
	
	
	if(name == ''){
		alert('장소 명을 입력해주세요.');
	}else if(addr == ''){
		alert('주소를 입력해주세요');
	}else if(lat == ''){
		alert('좌표 변환을 해주세요');
	}else if(lon == ''){
		alert('좌표 변환을 해주세요');
	}else if(item == 'null'){
		alert('보관함 갯수를 선택해주세요');
	}else{
		var param = {
				name : name,
				addr : addr,
				lat : lat,
				lon : lon,
				item : item	
		}
		location.href = "/manager/insertSellPlace.do?name=" + name + "&addr=" + addr + "&lat=" + lat + "&lon=" + lon + "&item=" + item ;
	}
	
	
}


function geoIncoder(){
	
	var juso = $("#roadAddrPart1").val();
	var geocoder = new kakao.maps.services.Geocoder();
	
	geocoder.addressSearch(juso,function(result,status){
		if(status === kakao.maps.services.Status.OK){
			var coords = new kakao.maps.LatLng(result[0].y,result[0].x);
			console.log(coords);
			$("#geoLat").val(coords.Ha);
			$("#geoLon").val(coords.Ga);
		}
	});
	
}




//opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup() {
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/jusoPopup.jsp", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");

	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}
/** API 서비스 제공항목 확대 (2017.02) **/
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail,
		roadAddrPart2, engAddr, jibunAddr, zipNo, admCd, rnMgtSn,
		bdMgtSn, detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm,
		rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.

	console.log(roadAddrPart1);
	$("#roadAddrPart1").val(roadAddrPart1);
	$("#roadAddrPart2").val(roadAddrPart2);
	$("#addrDetail").val(addrDetail);
	$("#zipNo").val(zipNo);
	/* document.form.roadAddrPart1.value = roadAddrPart1;
	document.form.roadAddrPart2.value = roadAddrPart2;
	document.form.addrDetail.value = addrDetail;
	document.form.zipNo.value = zipNo; */
	geoIncoder();
}