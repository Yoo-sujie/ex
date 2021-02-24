package com.kg.fin.search.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.fin.search.dao.SearchDAO;
import com.kg.fin.search.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchDAO dao;

	@Override
	public ArrayList<HashMap<String,Object>> sellPlace() {
		// TODO Auto-generated method stub
		return dao.sellPlace();
	}

	@Override
	public ArrayList<HashMap<String,Object>> sellCategory() {
		// TODO Auto-generated method stub
		return dao.sellCategory();
	}

	@Override
	public ArrayList<HashMap<String, Object>> sellList(HashMap<String,Object> paramMap) {
		// TODO Auto-generated method stub
		return dao.sellList(paramMap);
	}

	@Override
	public int sellListTotalCnt(HashMap paramMap) {
		// TODO Auto-generated method stub
		return dao.sellListTotalCnt(paramMap);
	}


	
	
	
	


}
