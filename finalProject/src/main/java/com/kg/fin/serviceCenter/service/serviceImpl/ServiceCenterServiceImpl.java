package com.kg.fin.serviceCenter.service.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.fin.serviceCenter.dao.ServiceCenterDAO;
import com.kg.fin.serviceCenter.service.ServiceCenterService;

@Service("serviceCenterService")
public class ServiceCenterServiceImpl implements ServiceCenterService{

	@Autowired
	private ServiceCenterDAO dao;
	
	
	//--공지사항
	@Override //전체 게시글 불러오기
	public List<HashMap<String, Object>> noticeListView(HashMap paramMap) {
		return dao.noticeListView(paramMap);
	}
	@Override //전체 게시글 수 
	public int noticeListCnt(HashMap paramMap) {
		return dao.noticeListCnt(paramMap);
	}
	@Override //공지사항 작성하기
	public int noticeWriteContents(HashMap paramMap) {
		return dao.noticeWriteContents(paramMap);
	}
	@Override //공지사항 글 불러오기
	public HashMap<String, Object> noticeDetailView(HashMap paramMap) {
		return dao.noticeDetailView(paramMap);
	}
	@Override //게시글 조회수 카운트
	public void noticeAddCnt(HashMap paramMap) {
		dao.noticeAddCnt(paramMap);
	}
	@Override //공지사항 게시글 삭제하기
	public int noticeDelete(HashMap paramMap) {
		return dao.noticeDelete(paramMap);
	}
	
	
	//--묻고답하기
	@Override //전체 게시글 불러오기
	public List<HashMap<String, Object>> qnaListView(HashMap paramMap) {
		return dao.qnaListView(paramMap);
	}
	@Override //전체 게시글 수 
	public int qnaListCnt(HashMap paramMap) {
		return dao.qnaListCnt(paramMap);
	}
	@Override //문의글 불러오기
	public HashMap<String, Object> qnaDetailView(HashMap paramMap) {
		return dao.qnaDetailView(paramMap);
	}
	@Override //댓글 작성하기
	public int addCmt(HashMap paramMap) { 
		return dao.addCmt(paramMap);
	}
	@Override //작성한 댓글 가져오기
	public HashMap<String, Object> getCmt(HashMap paramMap) { 
		return dao.getCmt(paramMap);
	}
	@Override //댓글 리스트 불러오기
	public HashMap<String, Object> qnaCmtList(HashMap paramMap) {
		return dao.qnaCmtList(paramMap);
	}
	/*@Override //답변 등록으로 글 상태 변경
	public String statusChange(String number) {
		return dao.statusChange(number);
	}*/
	@Override //문의글 답변 작성 시 문의 상태 변경
	public void statusChange(HashMap paramMap) {
		// TODO Auto-generated method stub
		dao.statusChange(paramMap);
	}
	@Override //문의글 작성하기
	public int qnaWriteContents(HashMap paramMap) {
		return dao.qnaWriteContents(paramMap);
	}
	@Override //문의글 삭제하기
	public int qnaDelete(HashMap paramMap) {
		return dao.qnaDelete(paramMap);
	}
	@Override //문의글 삭제 시 해당 게시글의 댓글도 함께 삭제하기
	public int qnaCmtDelete(HashMap paramMap) {
		return dao.qnaCmtDelete(paramMap);
	}
	
	
		
}
