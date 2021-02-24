package com.kg.fin.search.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.search.service.SearchService;



@Controller
public class SearchController {

	@Autowired
	private SearchService service;
	
	
	
	
	 @RequestMapping(value = "search/searchPage.do", method = RequestMethod.GET)
	 	public String searchPage(HttpServletRequest request) {
	 		HashMap paramMap = getParameterMap(request);
	 		paramMap.put("startIndex", 1);
	 		paramMap.put("lastIndex", 10);
	 		
	 		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	 		System.out.println("판매리스트 페이지 이동");

	 		
	 		System.out.println(paramMap);
	 		ArrayList<HashMap<String,Object>> sellPlace = new ArrayList<HashMap<String,Object>>();
	 		ArrayList<HashMap<String,Object>> sellCategory = new ArrayList<HashMap<String,Object>>();
	 		
	 		
	 		ArrayList<HashMap<String,Object>> sellList = new ArrayList<HashMap<String,Object>>();
	 		
	 		sellPlace = service.sellPlace();
	 		sellCategory = service.sellCategory();
	 		sellList = service.sellList(paramMap);
	 		
	 		int totalCnt = service.sellListTotalCnt(paramMap);
	 		request.setAttribute("searchSellPlace", paramMap.get("sellPlace"));
	 		request.setAttribute("searchSellCategory", paramMap.get("category"));
	 		request.setAttribute("sellPlace", sellPlace);
	 		request.setAttribute("sellCategory", sellCategory);
	 		request.setAttribute("sellList", sellList);
	 		request.setAttribute("totalCnt", totalCnt);
	 		
	 		String result = "search/search";



	 		return result;
	 	}
	
	 @RequestMapping(value = "search/sellListView.do", method = RequestMethod.POST)
	 	public ModelAndView sellListView(HttpServletRequest request,Model model) {
	 		HashMap paramMap = getParameterMap(request);
	 		System.out.println(paramMap);
	 		
	 		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	 		System.out.println("판매리스트 페이지 이동");

	 		ArrayList<HashMap<String,Object>> sellPlace = new ArrayList<HashMap<String,Object>>();
	 		ArrayList<HashMap<String,Object>> sellCategory = new ArrayList<HashMap<String,Object>>();
	 		
	 		
	 		ArrayList<HashMap<String,Object>> sellList = new ArrayList<HashMap<String,Object>>();
	 		
	 		sellPlace = service.sellPlace();
	 		sellCategory = service.sellCategory();
	 		sellList = service.sellList(paramMap);
	 		
	 		int totalCnt = service.sellListTotalCnt(paramMap);
	 		

	 		




	 		ModelAndView mv = new ModelAndView("jsonView");
			System.out.println("mv = " + mv);

			model.addAttribute("sellPlace", sellPlace);
			model.addAttribute("sellCategory", sellCategory);
			model.addAttribute("list", sellList);
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("currPage", paramMap.get("currPage"));
			//model.addAttribute("map", paramMap);

			return mv;
			
	 	}
	 
	 @RequestMapping(value = "search/sellSearch.do", method = RequestMethod.POST)
	 	public ModelAndView sellSearch(HttpServletRequest request,Model model) {
		 HashMap paramMap = getParameterMap(request);
	 		
	 		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	 		System.out.println("판매리스트 페이지 이동");

	 		
	 		System.out.println(paramMap);
	 		ArrayList<HashMap<String,Object>> sellPlace = new ArrayList<HashMap<String,Object>>();
	 		ArrayList<HashMap<String,Object>> sellCategory = new ArrayList<HashMap<String,Object>>();
	 		
	 		
	 		ArrayList<HashMap<String,Object>> sellList = new ArrayList<HashMap<String,Object>>();
	 		
	 		sellPlace = service.sellPlace();
	 		sellCategory = service.sellCategory();
	 		sellList = service.sellList(paramMap);
	 		
	 		int totalCnt = service.sellListTotalCnt(paramMap);
	 		
	 		request.setAttribute("sellPlace", sellPlace);
	 		request.setAttribute("sellCategory", sellCategory);
	 		request.setAttribute("sellList", sellList);
	 		request.setAttribute("totalCnt", totalCnt);
	 		




	 		ModelAndView mv = new ModelAndView("jsonView");
			System.out.println("mv = " + mv);

			model.addAttribute("sellPlace", sellPlace);
			model.addAttribute("sellCategory", sellCategory);
			model.addAttribute("list", sellList);
			model.addAttribute("totalCnt", totalCnt);
			model.addAttribute("currPage", paramMap.get("currPage"));
			//model.addAttribute("map", paramMap);

			return mv;
			
	 	}
	 
	 
	 
	protected HashMap getParameterMap(HttpServletRequest req) {
		HashMap map = new HashMap();

		Enumeration enm = req.getParameterNames();

		String name = null;
		String value = null;
		String[] arr = null;

		while (enm.hasMoreElements()) {
			name = (String) enm.nextElement();
			arr = req.getParameterValues(name);

			if (name.startsWith("arr")) {
				map.put(name, arr);
			} else {
				if (arr != null && arr.length > 0) {
					value = arr[0];
				} else {
					value = req.getParameter(name);
				}

				map.put(name, value);
			}
		}

		return map;
	}
	
	
}
