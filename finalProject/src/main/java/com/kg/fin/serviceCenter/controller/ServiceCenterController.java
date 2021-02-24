package com.kg.fin.serviceCenter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.serviceCenter.service.ServiceCenterService;

@Controller
public class ServiceCenterController {

	@Autowired
	private ServiceCenterService service;
	
	//공지사항 이동
	@RequestMapping(value="/serviceCenter/noticePageMove.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView noticePageMove(Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/serviceCenter/notice");
		return mv;
	}

	//공지사항 전체 게시글 목록
	@RequestMapping(value="/serviceCenter/noticeList.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView noticeList(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = service.noticeListView(paramMap); //리스트
		int currPage = Integer.parseInt((String)paramMap.get("currPage")); //현재 페이지
		int totalCnt = service.noticeListCnt(paramMap); //총 리스트 갯수
		//System.out.println("paramMap : " + paramMap);
		//System.out.println("request : " + request);
		ModelAndView mv = new ModelAndView("jsonView");
		//System.out.println("mv : " + mv);
		model.addAttribute("list", list);
		model.addAttribute("currPage", currPage);
		model.addAttribute("totalCnt", totalCnt);
		return mv;
	}
	
	//공지사항 글작성페이지 이동
	@RequestMapping(value="/serviceCenter/noticeWritePageMove.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView noticeWritePageMove(Model model) {
		ModelAndView mv = new ModelAndView("/serviceCenter/noticeWrite");
		return mv;
	}
	
	//공지사항 글 작성
	@RequestMapping(value="/serviceCenter/noticeWrite.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView noticeWrite(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		//System.out.println(paramMap);
		int result = 0;
		result = service.noticeWriteContents(paramMap);
		ModelAndView mv = new ModelAndView("/serviceCenter/notice");
		return mv;
	}
	
	//공지사항 해당 게시글 보기
	@RequestMapping(value="/serviceCenter/noticeView.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView noticeView(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		//System.out.println(paramMap);
		HashMap<String,Object> map = new HashMap<String,Object>();
		service.noticeAddCnt(paramMap);
		map = service.noticeDetailView(paramMap);
		request.setAttribute("map", map);
		ModelAndView mv = new ModelAndView("/serviceCenter/noticeDetail");
		return mv;
	}
	
	//공지사항 글 삭제 
	@RequestMapping(value="/serviceCenter/noticeDelete.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView noticeDelete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HashMap paramMap = getParameterMap(request);
		int result = 0;
		result = service.noticeDelete(paramMap);

		//java spring controller에서 javascript로 alert창 띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (result != 0) {
			out.println("<script> alert('삭제가 완료되었습니다.'); </script>");
		}
		out.flush();
		
		ModelAndView mv = new ModelAndView("/serviceCenter/notice");
		return mv;
	}
	
	//묻고답하기 이동
	@RequestMapping(value="/serviceCenter/qnaPageMove.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView qnaPageMove(Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/serviceCenter/qna");
		return mv;
	}
	
	//묻고답하기 전체 게시글 목록
	@RequestMapping(value="/serviceCenter/qnaList.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView qnaList(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = service.qnaListView(paramMap); //위에서 생성한 리스트에 메소드 결과 데이터 담기
		int currPage = Integer.parseInt((String)paramMap.get("currPage")); //현재 페이지
		int totalCnt = service.qnaListCnt(paramMap); //총 리스트 갯수
		//System.out.println("paramMap : " + paramMap);
		//System.out.println("request : " + request);
		ModelAndView mv = new ModelAndView("jsonView");
		//System.out.println("mv : " + mv);
		model.addAttribute("list", list);
		model.addAttribute("currPage", currPage);
		model.addAttribute("totalCnt", totalCnt);
		return mv;
	}
	
	//묻고답하기 해당 게시글 보기 & 댓글 불러오기
	@RequestMapping(value="/serviceCenter/qnaView.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView qnaView(Model model, HttpServletRequest request) {
		
		//게시글
		HashMap paramMap = getParameterMap(request);
		HashMap<String,Object> map = new HashMap<String,Object>(); 
		map = service.qnaDetailView(paramMap);
		request.setAttribute("map", map);		
		
		//댓글
		HashMap<String, Object> comment = new HashMap<String,Object>();
		comment = service.qnaCmtList(paramMap);
		request.setAttribute("comment", comment);
		
		ModelAndView mv = new ModelAndView("/serviceCenter/qnaDetail");
		return mv;
	}
	
	//댓글작성
	@RequestMapping(value="/serviceCenter/addCmt.do", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView addCmt(Model model, HttpServletRequest request) {
		
		HashMap paramMap = getParameterMap(request);
		
		int result = 0;
		result = service.addCmt(paramMap); //결과가 1이면 제대로 DB에 insert
		service.statusChange(paramMap);
		HashMap<String,Object> map = new HashMap<String,Object>(); 
		map = service.qnaDetailView(paramMap);
		HashMap<String, Object> comment = new HashMap<String, Object>(); 
		comment = service.getCmt(paramMap); //넣은 데이터를 다시 select
		
		//답변이 등록되었으므로 해당 문의글의 번호를 받아와 상태를 0->1로 변경
		//System.out.println("paramMap"+paramMap);
		//String number = paramMap.get("no").toString();
		
		
		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("result", result);
		model.addAttribute("comment", comment);
		model.addAttribute("map", map);
		return mv;
	}
	
	//묻고답하기 글작성페이지 이동
	@RequestMapping(value="/serviceCenter/qnaWritePageMove.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView qnaWritePageMove(Model model) {
		ModelAndView mv = new ModelAndView("/serviceCenter/qnaWrite");
		return mv;
	}
	
	//묻고답하기 글 작성
	@RequestMapping(value="/serviceCenter/qnaWrite.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView qnaWrite(Model model, HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		System.out.println(paramMap);
		//System.out.println(paramMap);
		int result = 0;
		result = service.qnaWriteContents(paramMap);
		ModelAndView mv = new ModelAndView("/serviceCenter/qna");
		return mv;
	}
	
	//문의하기 글 삭제 
	@RequestMapping(value="/serviceCenter/qnaDelete.do", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView qnaDelete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HashMap paramMap = getParameterMap(request);
		
		//해당 글의 답변 함께 삭제
		int comment = 0;
		comment = service.qnaCmtDelete(paramMap);
		
		//해당 글 삭제
		int result = 0;
		result = service.qnaDelete(paramMap);

		//java spring controller에서 javascript로 alert창 띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (comment != 0 || result != 0) {
			out.println("<script> alert('삭제가 완료되었습니다.'); </script>");
		}
		out.flush();
		
		ModelAndView mv = new ModelAndView("/serviceCenter/qna");
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
