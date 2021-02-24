package com.kg.fin.seller.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kg.fin.koreasd.vo.KoreasdVO;
import com.kg.fin.koreasgg.vo.KoreaSggVO;
import com.kg.fin.sellCategory.vo.SellCategoryVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.sellImg.vo.SellImgVO;
import com.kg.fin.sellPlace.vo.SellPlaceVO;
import com.kg.fin.seller.vo.SellerVO;

public interface SellerService {

	HashMap<String, Object> memberSelect(String member_id);
	
	ArrayList<SellerVO> sellAllSelect(String sell_seller_id);
	
	ArrayList<SellerVO> sellOneSelect(int sell_no);
	
	int sellInsert(SellerVO vo);
	
	int sellImgInsert(SellImgVO vo);
	
	int sellNoSelect();

	//int sellTsNoSelect();

	ArrayList<KoreasdVO> sellPlaceSelectSi();

	ArrayList<KoreaSggVO> sellPlaceSelectGu(String sido_code);
	
	ArrayList<SellPlaceVO> sellPlaceSelectStation(String sgg_code);
	
	int sellPlaceOneSelect(String sellPlaceName);
	
	ArrayList<SellCategoryVO> sellCategorySelectLarge();

	ArrayList<SellCategoryVO> sellCategorySelect(String sell_category_no);

	int sellNoUpdate();

	int sellUpdate(SellerVO vo);

	int sellImgUpdate(SellImgVO vo);
	
	int sellDelete(int sell_no);

	int sellImgDelete(int sell_no);
	
	//int sellTransientStorage(HashMap<Object, Object> paramMap);
	
	//int sellTsNoOneSelect(HashMap<Object, Object> paramMap);
	
	//int sellTsUpdate(HashMap<Object, Object> paramMap);
	
	//int sellTsMemberSelect(String sell_ts_seller_id);

	int pageCnt(int parseInt);

	ArrayList<HashMap<String, Object>> sellPlaceItemCnt(HashMap<String, Object> map);
	
	int sellCntSelect (int sell_no);
	
	void sellCntUpdate (SellerVO vo);

	ArrayList<HashMap<String, Object>> mainSellListView();

	ArrayList<HashMap<String, Object>> mainSellReviewListView();
	
}
