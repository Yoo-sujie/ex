package com.kg.fin.buyer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kg.fin.buyer.vo.BuyerVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.seller.vo.SellerVO;

public interface BuyerService {

	//ArrayList<BuyerVO> buyerMainOutput(int sell_no);

	int buyerUpdate (HashMap<String, Object> param);
	
	int buyerPaySelect (SellerVO sellerVo);
	
	ArrayList<SellCommentVO> commentGroupSelect (int sell_no);
	
	SellCommentVO sellCommentOneSelect (SellCommentVO sellCommentVo);
	
	ArrayList<SellCommentVO> sellCommentSelect(HashMap param);
	
	int commentGroupMaxSelect(SellCommentVO sellCommentVo);

	ArrayList<HashMap<String, Object>> sellReCommentSelect(HashMap<String, Object> param);

	int sellCommentMaxNo();

	int sellCommentCntSelect(int sell_no);
	
	int sellCommentInsert(SellCommentVO vo);
	
	int sellCommentUpdate (SellCommentVO sellCommentVo);
	
	/*int sellReCommentCntSelect(SellCommentVO vo);*/
	
	int commentGroupTotalSelect(SellCommentVO vo);
	
	/*ArrayList<SellCommentVO> sellDeleteComment(SellCommentVO vo);*/
	
	int sellCommentDelete(SellCommentVO vo);
	
	//ArrayList<SellCommentVO> sellOneReComment(int sell_comment_originno);

	HashMap<String, Object> selectReview(int num);

	ArrayList<HashMap<String, Object>> selectReviwImages(int num);

	void insertReview(HashMap<String, Object> param);

	void insertReviewImg(HashMap<String, Object> param2);


}
