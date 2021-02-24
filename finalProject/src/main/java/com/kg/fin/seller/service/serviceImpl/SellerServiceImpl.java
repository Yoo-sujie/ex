package com.kg.fin.seller.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.fin.koreasd.vo.KoreasdVO;
import com.kg.fin.koreasgg.vo.KoreaSggVO;
import com.kg.fin.sellCategory.vo.SellCategoryVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.sellImg.vo.SellImgVO;
import com.kg.fin.sellPlace.vo.SellPlaceVO;
import com.kg.fin.seller.dao.SellerDAO;
import com.kg.fin.seller.service.SellerService;
import com.kg.fin.seller.vo.SellerVO;

@Service("sellerService")
public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerDAO dao;
	
	@Override
	public HashMap<String, Object> memberSelect(String member_id) {
		return dao.memberSelect(member_id);
	}

	@Override
	public ArrayList<SellerVO> sellAllSelect(String sell_seller_id) {
		return dao.sellAllSelect(sell_seller_id);
	}

	@Override
	public ArrayList<SellerVO> sellOneSelect(int sell_no) {
		return dao.sellOneSelect(sell_no);
	}

	@Override
	public int sellInsert(SellerVO vo) {
		return dao.sellInsert(vo);
	}

	@Override
	public int sellImgInsert(SellImgVO vo) {
		return dao.sellImgInsert(vo);
	}
	
	@Override
	public int sellNoSelect() {
		return dao.sellNoSelect();
	}
	
	/*@Override
	public int sellTsNoSelect() {
		return dao.sellTsNoSelect();
	}*/

	@Override
	public ArrayList<KoreasdVO> sellPlaceSelectSi() {
		return dao.sellPlaceSelectSi();
	}

	@Override
	public ArrayList<KoreaSggVO> sellPlaceSelectGu(String sido_code) {
		return dao.sellPlaceSelectGu(sido_code);
	}
	
	@Override
	public ArrayList<SellPlaceVO> sellPlaceSelectStation(String sgg_code) {
		return dao.sellPlaceSelectStation(sgg_code);
	}

	@Override
	public int sellPlaceOneSelect(String sellPlaceName) {
		return dao.sellPlaceOneSelect(sellPlaceName);
	}

	@Override
	public ArrayList<SellCategoryVO> sellCategorySelectLarge() {
		return dao.sellCategorySelectLarge();
	}

	@Override
	public ArrayList<SellCategoryVO> sellCategorySelect(String sell_category_no) {
		return dao.sellCategorySelect(sell_category_no);
	}
	
	@Override
	public int sellNoUpdate() {
		return dao.sellNoUpdate();
	}
	
	@Override
	public int sellUpdate(SellerVO vo) {
		return dao.sellUpdate(vo);
	}
	
	@Override
	public int sellImgUpdate(SellImgVO vo) {
		return dao.sellImgUpdate(vo);
	}

	@Override
	public int sellDelete(int sell_no) {
		return dao.sellDelete(sell_no);
	}
	
	@Override
	public int sellImgDelete(int sell_no) {
		return dao.sellImgDelete(sell_no);
	}

	/*@Override
	public int sellTransientStorage(HashMap<Object, Object> paramMap) {
		return dao.sellTransientStorage(paramMap);
	}

	@Override
	public int sellTsNoOneSelect(HashMap<Object, Object> paramMap) {
		return dao.sellTsNoOneSelect(paramMap);
	}

	@Override
	public int sellTsUpdate(HashMap<Object, Object> paramMap) {
		return dao.sellTsUpdate(paramMap);
	}

	@Override
	public int sellTsMemberSelect(String sell_ts_seller_id) {
		return dao.sellTsMemberSelect(sell_ts_seller_id);
	}*/

	@Override
	public int pageCnt(int parseInt) {
		return dao.pageCnt(parseInt);
	}

	@Override
	public ArrayList<HashMap<String, Object>> sellPlaceItemCnt(HashMap<String, Object> map) {
		return dao.sellPlaceItemCnt(map);
	}

	@Override
	public int sellCntSelect(int sell_no) {
		return dao.sellCntSelect(sell_no);
	}

	@Override
	public void sellCntUpdate(SellerVO vo) {
		dao.sellCntUpdate(vo);
	}

	@Override
	public ArrayList<HashMap<String, Object>> mainSellListView() {
		// TODO Auto-generated method stub
		return dao.mainSellListView();
	}

	@Override
	public ArrayList<HashMap<String, Object>> mainSellReviewListView() {
		// TODO Auto-generated method stub
		return dao.mainSellReviewListView();
	}



}
