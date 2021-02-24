package com.kg.fin.manager.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.fin.manager.dao.ManagerDAO;
import com.kg.fin.manager.service.ManagerService;
import com.kg.fin.manager.vo.ApprovalVO;
import com.kg.fin.manager.vo.ManagerDetailVO;
import com.kg.fin.manager.vo.MemberStoppedVO;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService{

	@Autowired
	private ManagerDAO dao;

	/*@Override
	public HashMap<String, String> approvalBoard(HashMap<String, String> paramMap) {
		return dao.selectApprovalList(paramMap);
	}*/
	@Override
	public ArrayList<ApprovalVO> selectApprovalList() {
		return dao.selectApprovalList();
	}

	@Override
	public ManagerDetailVO selectDetail(int param) {
		// TODO Auto-generated method stub
		return dao.selectDetail(param);
	}

	@Override
	public ArrayList<ApprovalVO> selectApprovalSearchList(HashMap param) {
		// TODO Auto-generated method stub
		return dao.selectApprovalSearchList(param);
	}

	@Override
	public int approvalUpdate(int param) {
		// TODO Auto-generated method stub
		return dao.approvalUpdate(param);
	}
	/*
	@Override
	public int approvalUpdate(HashMap param) {
		// TODO Auto-generated method stub
		return dao.approvalUpdate(param);
	}
	 */

	@Override
	public ArrayList<MemberStoppedVO> selectMemberlList() {
		// TODO Auto-generated method stub
		return dao.selectMemberList();
	}

	@Override
	public int totalCnt(HashMap map) {
		// TODO Auto-generated method stub
		return dao.totalCnt(map);
	}

	@Override
	public int stopAccount(String id) {
		// TODO Auto-generated method stub
		return dao.stopAccount(id);
	}

	@Override
	public int undoStopAccount(String id) {
		// TODO Auto-generated method stub
		return dao.undoStopAccount(id);
	}

	@Override
	public ArrayList<MemberStoppedVO> getMemberListPaging(HashMap paramMap) {
		return dao.getMemberListPaging(paramMap);
	}

	
	@Override
	public void insertSellPlace(HashMap paramMap) {
		// TODO Auto-generated method stub
		dao.insertSellPlace(paramMap);
		
	}

	@Override
	public void insertSellPlaceItem(HashMap paramMap) {
		// TODO Auto-generated method stub
		dao.insertSellPlaceItem(paramMap);
	}
	
	
	
	

	
	
	
	


}
