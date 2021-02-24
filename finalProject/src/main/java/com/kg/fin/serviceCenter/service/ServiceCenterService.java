package com.kg.fin.serviceCenter.service;

import java.util.HashMap;
import java.util.List;

public interface ServiceCenterService {
	
	//--공지사항
	List<HashMap<String, Object>> noticeListView(HashMap paramMap); //공지사항 글 리스트 불러오기
	int noticeListCnt(HashMap paramMap); //공지사항 전체 게시글 수
	
	int noticeWriteContents(HashMap paramMap); //공지사항글 작성하기
	
	HashMap<String, Object> noticeDetailView(HashMap paramMap); //공지사항글 불러오기
	void noticeAddCnt(HashMap paramMap); //게시글 조회수 카운트
	
	int noticeDelete(HashMap paramMap); //게시글 삭제하기
	
	//--묻고답하기
	List<HashMap<String, Object>> qnaListView(HashMap paramMap); //묻고답하기 글 리스트 불러오기
	int qnaListCnt(HashMap paramMap); //묻고답하기 전체 게시글 수
	
	HashMap<String, Object> qnaDetailView(HashMap paramMap); //문의글 불러오기
	HashMap<String, Object> qnaCmtList(HashMap paramMap); //댓글 리스트 불러오기
	
	int addCmt(HashMap paramMap); //댓글 작성하기
	HashMap<String, Object> getCmt(HashMap paramMap); //작성한 댓글 불러오기
	//String statusChange(String number); //답변 등록으로 글 상태 변경
	void statusChange(HashMap paramMap);
	int qnaWriteContents(HashMap paramMap); //문의글 작성하기
	
	int qnaDelete(HashMap paramMap); //문의글 삭제하기
	int qnaCmtDelete(HashMap paramMap); //문의글 삭제 시 해당 게시글의 댓글도 함께 삭제하기
	
	
	
}
