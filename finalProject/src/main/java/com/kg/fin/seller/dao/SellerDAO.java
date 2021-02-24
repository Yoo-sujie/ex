package com.kg.fin.seller.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.kg.fin.koreasd.vo.KoreasdVO;
import com.kg.fin.koreasgg.vo.KoreaSggVO;
import com.kg.fin.sellCategory.vo.SellCategoryVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.sellImg.vo.SellImgVO;
import com.kg.fin.sellPlace.vo.SellPlaceVO;
import com.kg.fin.seller.vo.SellerVO;

public interface SellerDAO {
	
	public HashMap<String, Object> memberSelect(String member_id); //회원 닉네임 조회
	
	public ArrayList<SellerVO> sellAllSelect(String sell_seller_id); //판매글 전체 조회
	
	public ArrayList<SellerVO> sellOneSelect(int sell_no); //판매글 하나 조회 + 이미지 조회를 위한 arrayList

	public int sellInsert(SellerVO vo); //판매글 입력
	
	public int sellImgInsert(SellImgVO vo); //판매글 등록 이미지 입력
	
	public int sellNoSelect(); //판매글 번호 개수 조회 (자동 +1을 위한)

	//public int sellTsNoSelect(); //임시 판매글 번호 개수 조회 (자동 +1을 위한)

	public ArrayList<KoreasdVO> sellPlaceSelectSi(); //판매장소(시) 조회 (ex:서울특별시)

	public ArrayList<KoreaSggVO> sellPlaceSelectGu(String sido_code); //판매장소(구) 조회 (ex:은평구)

	public ArrayList<SellPlaceVO> sellPlaceSelectStation(String sgg_code); //판매장소(역) 조회 (ex:응암역)
	
	public int sellPlaceOneSelect(String sellPlaceName); //판매장소 하나 조회 (장소번호)
	
	public ArrayList<SellCategoryVO> sellCategorySelectLarge(); //카테고리 대분류 조회

	public ArrayList<SellCategoryVO> sellCategorySelect(String sell_category_no); //카테고리 중/소분류 조회

	public int sellNoUpdate(); //판매글 번호 업데이트

	public int sellUpdate(SellerVO vo); //판매글 업데이트

	public int sellImgUpdate(SellImgVO vo); //판매글 이미지 업데이트
	
	public int sellDelete(int sell_no); //판매글 삭제

	public int sellImgDelete(int sell_no); //판매글 이미지 삭제
	
	//public int sellTransientStorage(HashMap<Object, Object> paramMap); //임시 판매글 저장
	
	//public int sellTsNoOneSelect(HashMap<Object, Object> paramMap); //임시 판매글 번호 검색 (업데이트를 위한)

	//public int sellTsUpdate(HashMap<Object, Object> paramMap); //임시 판매글 업데이트

	//public int sellTsMemberSelect(String sell_ts_seller_id); //저장된 임시 판매글 조회 (아이디별) 

	public int pageCnt(int parseInt);

	public ArrayList<HashMap<String, Object>> sellPlaceItemCnt(HashMap<String, Object> map);
	
	public int sellCntSelect (int sell_no); //판매글 조회수 검색
	
	public void sellCntUpdate (SellerVO vo); //판매글 조회수 업데이트

	public ArrayList<HashMap<String, Object>> mainSellListView();

	public ArrayList<HashMap<String, Object>> mainSellReviewListView();
	
}
