package com.kg.fin.member.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.fin.member.board.vo.BoardVO;
import com.kg.fin.member.board.vo.MemberVO;
import com.kg.fin.member.board.vo.ReportVO;
import com.kg.fin.member.dao.MemberDAO;
import com.kg.fin.member.service.MemberService;
import com.kg.fin.reviews.vo.ReviewsVO;
import com.kg.fin.sellImg.vo.SellImgVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao;

	// 로그인
	
	@Override //로그인 시 아이디, 비번 체크
	public int loginCheck(HashMap<String, String> paramMap) {
		return dao.selectLoginCheck(paramMap);
	}

	@Override // 아이디 찾기
	public String getId(HashMap<String, String> paramMap) {
		return dao.getId(paramMap);
	}
	
	@Override // 비밀번호 찾을 때 해당 아이디와 이메일이 존재하는 지 검색
	public String checkUser(HashMap<String, String> paramMap) {
		return dao.checkUser(paramMap);
	}
	
	@Override //로그인 한 유저 정보 가져옴 (일반 유저, 관리자 판별)
	public HashMap<String, Object> loginGetInfo(String id) {
		return dao.loginGetInfo(id);
	}
	
	// 회원 가입
	
	@Override //아이디 중복 체크
	public int checkId(String id) {
		return dao.checkId(id);
	}

	@Override //닉네임 중복 체크
	public int checkNick(String nick) {
		return dao.checkNick(nick);
	}
	
	@Override //핸드폰 중복 체크
	public int checkPhone(String phone) {
		return dao.checkPhone(phone);
	}
	
	@Override //이메일 중복 체크
	public int checkEmail(String email) {
		return dao.checkEmail(email);
	}
	
	@Override //회원 가입
	public void signUp(HashMap<Object, Object> paramMap) {
		dao.signUp(paramMap);
	}

	// 마이페이지
	
	@Override // 마이페이지용 로그인 유저 정보
	public MemberVO getUserInfo(String id) {
		return dao.getUserInfo(id);
	}
	
	@Override // 마이페이지 신고 카운트 가져오기
	public int getMyPageReport(String id) {
		return dao.getMyPageReport(id);
	}
	
	@Override // 판매 게시글 가져옴
	public ArrayList<BoardVO> getSellList(String id) {
		return dao.getSellList(id);
	}
	
	@Override // 구매 게시글 가져옴
	public ArrayList<BoardVO> getBuyList(String id) {
		return dao.getBuyList(id);
	}
	
	@Override // 리뷰 테이블 별점 가져옴
	public ArrayList<Integer> getReviewRating(String id) {
		return dao.getReviewRating(id);
	}
	
	@Override // 마이페이지 닉네임 중복 체크 - 내가 사용중인 닉네임일 경우
	public String myPageMyNick(String id) {
		return dao.myPageMyNick(id);
	}
	
	@Override // 마이페이지 닉네임 중복 체크
	public int myPageNick(String nick) { 
		return dao.myPageNick(nick);
	}
	
	@Override // 마이페이지 핸드폰 중복 체크 - 내가 사용중인 핸드폰일 경우
	public String myPageMyPhone(String id) {
		return dao.myPageMyPhone(id);
	}
	
	@Override // 마이페이지 핸드폰 중복 체크
	public int myPagePhone(String phone) {
		return dao.myPagePhone(phone);
	}
	
	@Override // 마이페이지 이메일 중복 체크 - 내가 사용중인 이메일일 경우
	public String myPageMyEmail(String id) {
		return dao.myPageMyEmail(id);
	}
	
	@Override // 마이페이지 이메일 중복 체크
	public int myPageEmail(String email) {
		return dao.myPageEmail(email);
	}
	
	@Override  // 마이페이지 업데이트
	public int myPageUpdate(HashMap<String, String> param) {
		return dao.myPageUpdate(param);
	}
	
	@Override // 신고된 게시글 번호가져오기
	public ArrayList<BoardVO> getReportSell(String id) {
		return dao.getReportSell(id);
	}

	@Override // 신고 게시글 내역 가져오기
	public ArrayList<HashMap<String, Object>> getReport(ArrayList<BoardVO> reportSell) {
		return dao.getReport(reportSell);
	}
	
	// 구매 내역
	
	@Override // 구매 내역 가져옴(로그인 한 유저 아이디 담아감)
	public ArrayList<HashMap<String, Object>> getPurchaseList(HashMap paramMap) {
		return dao.getPurchaseList(paramMap);
	}
	
	@Override // 구매내역 totalCnt 가져옴
	public int getTotalCnt(String id) {
		return dao.getTotalCnt(id);
	}
	
	// 판매 내역
	
	@Override // 판매 내역 가져옴(로그인 한 유저 아이디 담아감)
	public ArrayList<HashMap<String, Object>> getSalesList(HashMap paramMap) {
		return dao.getSalesList(paramMap);
	}

	@Override // 판매 내역 total count 가져옴
	public int salesTotalCnt(String id) {
		return dao.salesTotalCnt(id);
	}






}
