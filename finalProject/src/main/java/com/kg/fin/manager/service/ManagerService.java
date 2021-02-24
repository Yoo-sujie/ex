package com.kg.fin.manager.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kg.fin.manager.vo.ApprovalVO;
import com.kg.fin.manager.vo.ManagerDetailVO;
import com.kg.fin.manager.vo.MemberStoppedVO;

public interface ManagerService {

	//HashMap<String, String> approvalBoard(HashMap<String, String> paramMap);
	ArrayList<ApprovalVO> selectApprovalList() throws Exception;

	ManagerDetailVO selectDetail(int param);

	ArrayList<ApprovalVO> selectApprovalSearchList(HashMap param);

	int approvalUpdate(int param);
	//int approvalUpdate(HashMap param);

	ArrayList<MemberStoppedVO> selectMemberlList();

	int totalCnt(HashMap map);

	int stopAccount(String id);

	int undoStopAccount(String id);

	ArrayList<MemberStoppedVO> getMemberListPaging(HashMap paramMap);
	
	void insertSellPlace(HashMap paramMap);

	void insertSellPlaceItem(HashMap paramMap);

}