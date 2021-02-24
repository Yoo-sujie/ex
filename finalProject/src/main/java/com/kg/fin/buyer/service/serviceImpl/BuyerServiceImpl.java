package com.kg.fin.buyer.service.serviceImpl;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.fin.buyer.dao.BuyerDAO;
import com.kg.fin.buyer.service.BuyerService;
import com.kg.fin.buyer.vo.BuyerVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.seller.vo.SellerVO;

@Service("buyerService")
public class BuyerServiceImpl implements BuyerService{

	@Autowired
	private BuyerDAO dao;

	@Override
	public int buyerUpdate(HashMap<String, Object> param) {
		return dao.buyerUpdate(param);
	}

	@Override
	public int buyerPaySelect(SellerVO sellerVo) {
		return dao.buyerPaySelect(sellerVo);
	}

	@Override
	public ArrayList<SellCommentVO> commentGroupSelect (int sell_no) {
		return dao.commentGroupSelect(sell_no);
	}

	@Override
	public SellCommentVO sellCommentOneSelect(SellCommentVO sellCommentVo) {
		return dao.sellCommentOneSelect(sellCommentVo);
	}
	
	@Override
	public ArrayList<SellCommentVO> sellCommentSelect(HashMap param) {
		return dao.sellCommentSelect(param);
	}
	
	@Override
	public int commentGroupMaxSelect(SellCommentVO sellCommentVo) {
		return dao.commentGroupMaxSelect(sellCommentVo);
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> sellReCommentSelect(HashMap<String, Object> param) {
		return dao.sellReCommentSelect(param);
	}
	
	@Override
	public int sellCommentMaxNo() {
		return dao.sellCommentMaxNo();
	}
	
	@Override
	public int sellCommentCntSelect(int sell_no) {
		return dao.sellCommentCntSelect(sell_no);
	}
	
	@Override
	public int sellCommentInsert(SellCommentVO vo) {
		return dao.sellCommentInsert(vo);
	}

	@Override
	public int sellCommentUpdate(SellCommentVO sellCommentVo) {
		return dao.sellCommentUpdate(sellCommentVo);
	}
	
	/*@Override
	public int sellReCommentCntSelect(SellCommentVO vo) {
		return dao.sellReCommentCntSelect(vo);
	}*/
	
	@Override
	public int commentGroupTotalSelect(SellCommentVO vo) {
		return dao.commentGroupTotalSelect(vo);
	}
	
	/*@Override
	public ArrayList<SellCommentVO> sellDeleteComment(SellCommentVO vo) {
		return dao.sellDeleteComment(vo);
	}*/

	@Override
	public int sellCommentDelete(SellCommentVO vo) {
		return dao.sellCommentDelete(vo);
	}
	
	/*@Override
	public ArrayList<SellCommentVO> sellOneReComment(int sell_comment_originno) {
		return dao.sellOneReComment(sell_comment_originno);
	}*/

	@Override
	public HashMap<String, Object> selectReview(int num) {
		return dao.selectReview(num);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectReviwImages(int num) {
		return dao.selectReviwImages(num);
	}

	@Override
	public void insertReview(HashMap<String, Object> param) {
		dao.insertReview(param);
	}

	@Override
	public void insertReviewImg(HashMap<String, Object> param2) {
		dao.insertReviewImg(param2);
	}

	/*@Override
	@Transactional
	public ArrayList<BuyerVO> buyerMainOutput(int sell_no) {
		// TODO Auto-generated method stub
		return dao.buyerMainOutput(sell_no);
	}*/

	

	

	
	
	
	
	


}
