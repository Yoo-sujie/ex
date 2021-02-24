package com.kg.fin.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kg.fin.member.board.vo.BoardVO;
import com.kg.fin.member.board.vo.MemberVO;
import com.kg.fin.member.board.vo.ReportVO;
import com.kg.fin.reviews.vo.ReviewsVO;
import com.kg.fin.sellImg.vo.SellImgVO;

public interface MemberDAO {
	// 로그인
	int selectLoginCheck(HashMap<String, String> paramMap);
	
	String getId(HashMap<String, String> paramMap);
	
	String checkUser(HashMap<String, String> paramMap);
	
	HashMap<String, Object> loginGetInfo(String id);
	
	// 회원 가입
	int checkId(String id);

	int checkNick(String nick);
	
	int checkPhone(String phone);

	int checkEmail(String email);
	
	void signUp(HashMap<Object, Object> paramMap);
	
	// 마이페이지
	MemberVO getUserInfo(String id);
	
	int getMyPageReport(String id);
	
	ArrayList<BoardVO> getSellList(String id);
	
	ArrayList<BoardVO> getBuyList(String id);
	
	ArrayList<Integer> getReviewRating(String id);
	
	String myPageMyNick(String id);

	int myPageNick(String nick);

	String myPageMyPhone(String id);
	
	int myPagePhone(String phone);

	String myPageMyEmail(String id);
	
	int myPageEmail(String email);
	
	int myPageUpdate(HashMap<String, String> param);
	
	ArrayList<BoardVO> getReportSell(String id);
	
	ArrayList<HashMap<String, Object>> getReport(ArrayList<BoardVO> reportSell);

	// 구매 내역
	ArrayList<HashMap<String, Object>> getPurchaseList(HashMap paramMap);

	//List<BoardVO> buyList(String id); 
	
	int getTotalCnt(String id);
	
	// 판매 내역
	ArrayList<HashMap<String, Object>> getSalesList(HashMap paramMap);

	int salesTotalCnt(String id);




}
