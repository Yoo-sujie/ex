package com.kg.fin.buyer.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kg.fin.buyer.vo.BuyerVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.seller.vo.SellerVO;

public interface BuyerDAO {
	
	//BuyerVO buyerMainOutput(BuyerVO buyerVO);
	
	public int buyerUpdate (HashMap<String, Object> param); //구매 결제 시 업데이트
	
	public int buyerPaySelect (SellerVO sellerVo); //해당 글의 구매자일 경우 구매후기 작성 버튼 보이기
	
	public ArrayList<SellCommentVO> commentGroupSelect (int sell_no); //작성된 댓글만 검색하기 (답글x)

	public SellCommentVO sellCommentOneSelect (SellCommentVO sellCommentVo); //작성된 댓글 하나 검색하기 (수정을 위한)
	
	public ArrayList<SellCommentVO> sellCommentSelect(HashMap param); //판매글 댓글 전체 조회
	
	public int commentGroupMaxSelect(SellCommentVO sellCommentVo); //댓글의 답글 순서 검색 : 가장 큰 순서 

	public ArrayList<HashMap<String, Object>> sellReCommentSelect(HashMap<String, Object> param); //댓글의 답글 리스트 조회

	public int sellCommentMaxNo(); //판매글 댓글 번호 (가장 큰 번호) 검색

	public int sellCommentCntSelect(int sell_no); //판매글 댓글 개수 조회
	
	public int sellCommentInsert(SellCommentVO vo); //판매글 댓글 등록

	public int sellCommentUpdate (SellCommentVO sellCommentVo); //댓글 업데이트(수정)
	
	//public int sellReCommentCntSelect(SellCommentVO vo); //판매글 댓글의 답글 개수 검색

	public int commentGroupTotalSelect(SellCommentVO vo); //판매글 댓글의 답글 개수 검색
	
	/*public ArrayList<SellCommentVO> sellDeleteComment(SellCommentVO vo); //판매글 삭제된 댓글 검색(답글있는 원댓글)*/

	public int sellCommentDelete(SellCommentVO vo); //판매글 댓글 삭제

	//public ArrayList<SellCommentVO> sellOneReComment(int sell_comment_originno); //하나의 댓글의 답글리스트 검색
	
	public HashMap<String, Object> selectReview(int num); //리뷰 검색
	
	public ArrayList<HashMap<String, Object>> selectReviwImages(int num); //리뷰 이미지검색
	
	public void insertReview(HashMap<String, Object> param); //리뷰 등록
	
	public void insertReviewImg(HashMap<String, Object> param2); //리뷰 이미지 등록
	
}
