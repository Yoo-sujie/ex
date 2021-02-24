package com.kg.fin.search.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchService {

	//판매장소 옵션
	ArrayList<HashMap<String,Object>> sellPlace();

	ArrayList<HashMap<String,Object>> sellCategory();

	ArrayList<HashMap<String, Object>> sellList(HashMap<String,Object> paramMap);

	int sellListTotalCnt(HashMap paramMap);

	



}
