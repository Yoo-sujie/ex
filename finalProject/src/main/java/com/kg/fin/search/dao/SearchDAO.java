package com.kg.fin.search.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchDAO {

	ArrayList<HashMap<String,Object>> sellPlace();

	ArrayList<HashMap<String,Object>> sellCategory();

	ArrayList<HashMap<String, Object>> sellList(HashMap<String, Object> paramMap);

	int sellListTotalCnt(HashMap paramMap);

	
	
	
}
