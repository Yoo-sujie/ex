package com.kg.fin.map.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



public interface MapDAO {


	
	
	public int loginCheck(HashMap<String, String> paramMap);


	public List<HashMap<String, String>> storeList();


	public List<HashMap<String, Object>> sellListView(HashMap paramMap);


	public int sellListCnt(HashMap paramMap);


	public List<HashMap<String, Object>> bigCategoryList();


	public List<HashMap<String, Object>> middleCategoryList(String no);



}
