package com.kg.fin.map.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MapService {

	List<HashMap<String, String>> storeList();

	List<HashMap<String, Object>> sellListView(HashMap paramMap);

	int sellListCnt(HashMap paramMap);

	List<HashMap<String, Object>> bigCategoryList();

	List<HashMap<String, Object>> middleCategoryList(String no);







}
