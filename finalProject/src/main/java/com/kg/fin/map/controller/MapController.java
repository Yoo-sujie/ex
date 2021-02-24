package com.kg.fin.map.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.map.service.MapService;



@Controller
public class MapController {

	@Autowired
	private MapService service;
	
	
	
	
	
	@RequestMapping(value = "/storeList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView login2(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		System.out.println("paramMap = " + paramMap);
		System.out.println("request = " + request);
		System.out.println("2번루트");
		
		
		
		list = service.storeList();

		HashMap<String, String> map = null;

		HashMap<String, String> map2 = null;






		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);

		model.addAttribute("list", list);
		//model.addAttribute("map", paramMap);

		return mv;
	}
	
	
	@RequestMapping(value = "/map/sellListView.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView sellListView(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		//리스트
		list = service.sellListView(paramMap);
		
		
		// 현재 페이지
		int currPage = Integer.parseInt((String)paramMap.get("currPage"));
		System.out.println(currPage);
		
		//총 리스트 갯수
		int totalCnt = service.sellListCnt(paramMap); 
		
		System.out.println("paramMap = " + paramMap);
		System.out.println("request = " + request);

		
		
		//list = service.storeList();

		HashMap<String, String> map = null;

		HashMap<String, String> map2 = null;

		




		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);

		model.addAttribute("list", list);
		model.addAttribute("currPage", currPage);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("paramMap", paramMap);
		
		
		//model.addAttribute("map", paramMap);

		return mv;
	}
	
	@RequestMapping(value = "/map/bigCategoryList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView bigCategoryList(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		
		list = service.bigCategoryList();




		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);

		System.out.println("list = " + list);
		
		model.addAttribute("list", list);
		
		
		//model.addAttribute("map", paramMap);

		return mv;
	}
	
	@RequestMapping(value = "/map/middleCategoryList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView middleCategoryList(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		System.out.println((String)paramMap.get("no"));
		list = service.middleCategoryList((String)paramMap.get("no"));




		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);

		System.out.println("list = " + list);
		
		model.addAttribute("list", list);
		
		
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
