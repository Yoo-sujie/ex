package com.kg.fin.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kg.fin.member.board.vo.BoardVO;
import com.kg.fin.member.board.vo.MemberVO;
import com.kg.fin.member.board.vo.ReportVO;
import com.kg.fin.reviews.vo.ReviewsVO;
import com.kg.fin.sellImg.vo.SellImgVO;

public interface MemberService {

	// 로그인
	public int loginCheck(HashMap<String, String> paramMap); // 로그인 아이디 비번 체크
	
	public String getId(HashMap<String, String> paramMap); // 아이디 찾기
	
	public String checkUser(HashMap<String, String> paramMap); // 입력한 아이디, 이메일에 맞는 비밀번호 찾기
	
	public HashMap<String, Object> loginGetInfo(String id); // 로그인 한 유저 정보 가져오기
	
	// 회원 가입
	public int checkId(String id); // 아이디 중복 체크
	
	public int checkNick(String nick); // 닉네임 중복 체크
	
	public int checkPhone(String phone); // 핸드폰 중복 체크
	
	public int checkEmail(String email); // 이메일 중복 체크
	
	public void signUp(HashMap<Object, Object> paramMap); // 회원 가입 등록

	// 마이 페이지
	public MemberVO getUserInfo(String id); // 마이페이지 로그인 회원 정보 가져옴
	
	public int getMyPageReport(String id); // 마이페이지 신고 횟수 가져옴
	
	public ArrayList<BoardVO> getSellList(String id); // 마이페이지 판매 게시글 가져옴
	
	public ArrayList<BoardVO> getBuyList(String id); // 마이페이지 구매 게시글 가져옴
	
	public ArrayList<Integer> getReviewRating(String id); // 마이페이지 리뷰 테이블에서 별점 가져옴
	
	public String myPageMyNick(String id); // 마이페이지 닉네임 수정 중복 체크 - 내가 사용 중인 닉네임일 경우
	
	public int myPageNick(String nick); //  마이페이지 닉네임 수정 중복 체크

	public String myPageMyPhone(String id); // 마이페이지 핸드폰 수정 중복 체크 - 내가 사용 중인 핸드폰일 경우

	public int myPagePhone(String phone); // 마이페이지 핸드폰 수정 중복 체크
	
	public String myPageMyEmail(String id); // 마이페이지 이메일 수정 중복 체크 - 내가 사용 중인 이메일일 경우
	
	public int myPageEmail(String email); // 마이페이지 이메일 수정 중복 체크
	
	public int myPageUpdate(HashMap<String, String> param); // 마이페이지 수정하기

	public ArrayList<BoardVO> getReportSell(String id); // 신고된 게시글 번호 가져오기
	
	public ArrayList<HashMap<String, Object>> getReport(ArrayList<BoardVO> reportSell); // 마이페이지 신고 내역 버튼 클릭 시 내역 가져오기
	
	// 구매 내역
	//public List<BoardVO> buyList(String id); // 구매 게시글 번호
	
	public ArrayList<HashMap<String, Object>> getPurchaseList(HashMap paramMap); // 구매 내역 가져오기
	
	public int getTotalCnt(String id); // 구매 내역 페이징
	
	// 판매 내역
	public ArrayList<HashMap<String, Object>> getSalesList(HashMap paramMap); // 판매 내역  가져오기
	
	public int salesTotalCnt(String id); // 판매 내역 페이징





}
