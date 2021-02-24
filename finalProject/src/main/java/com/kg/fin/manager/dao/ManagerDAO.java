package com.kg.fin.manager.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.kg.fin.manager.vo.ApprovalVO;
import com.kg.fin.manager.vo.ManagerDetailVO;
import com.kg.fin.manager.vo.MemberStoppedVO;

public interface ManagerDAO {

	//public int loginCheck(HashMap<String, String> paramMap);
	//HashMap<String, String> selectApprovalList(HashMap<String, String> paramMap);
	
	ArrayList<ApprovalVO> selectApprovalList();

	ManagerDetailVO selectDetail(int param);

	ArrayList<ApprovalVO> selectApprovalSearchList(HashMap param);

	int approvalUpdate(int param);

	ArrayList<MemberStoppedVO> selectMemberList();

	int totalCnt(HashMap map);

	int stopAccount(String id);

	int undoStopAccount(String id);

	ArrayList<MemberStoppedVO> getMemberListPaging(HashMap paramMap);


	public void insertSellPlace(HashMap paramMap);


	public void insertSellPlaceItem(HashMap paramMap);
	
}
