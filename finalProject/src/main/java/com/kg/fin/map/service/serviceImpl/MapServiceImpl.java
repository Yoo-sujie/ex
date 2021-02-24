package com.kg.fin.map.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kg.fin.map.dao.MapDAO;
import com.kg.fin.map.service.MapService;

@Service("mapService")
public class MapServiceImpl implements MapService{

	@Autowired
	private MapDAO dao;

	@Override
	public List<HashMap<String, String>> storeList() {
		// TODO Auto-generated method stub
		return dao.storeList();
	}

	@Override
	public List<HashMap<String, Object>> sellListView(HashMap paramMap) {
		// TODO Auto-generated method stub
		return dao.sellListView(paramMap);
	}

	@Override
	public int sellListCnt(HashMap paramMap) {
		// TODO Auto-generated method stub
		return dao.sellListCnt(paramMap);
	}

	@Override
	public List<HashMap<String, Object>> bigCategoryList() {
		// TODO Auto-generated method stub
		return dao.bigCategoryList();
	}

	@Override
	public List<HashMap<String, Object>> middleCategoryList(String no) {
		// TODO Auto-generated method stub
		return dao.middleCategoryList(no);
	}



	
	
	
	
	


}
