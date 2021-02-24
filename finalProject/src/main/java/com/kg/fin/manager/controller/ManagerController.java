package com.kg.fin.manager.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.manager.service.ManagerService;
import com.kg.fin.manager.vo.ApprovalVO;
import com.kg.fin.manager.vo.ManagerDetailVO;
import com.kg.fin.manager.vo.MemberStoppedVO;





@Controller //컨트롤러 지정
public class ManagerController {

	@Autowired
	private ManagerService service;
	
	
	
	//승인대기리스트
	@RequestMapping(value="/approvalList.do", method = RequestMethod.GET)
								//사용자의 요청 받기
	public String approvalBoard(HttpServletRequest request) throws Exception {
		System.out.println("승인대기리스트");
		
		ArrayList<ApprovalVO> alist = new ArrayList<ApprovalVO>();
		alist = service.selectApprovalList();
		
		System.out.println(alist.size());
		
		request.setAttribute("alist", alist); //값이 alist 인 객체를 "alist"라는 이름으로 jsp로 넘겨주기(approvalBoard.jsp)
		
		//Controller 에서 작업클래스(impl)로 부터 받아온 request를 가지고, view페이지(jsp)로 이동시킴
		return "manager/approvalBoard";
		
	}
	
	
	//승인대기리스트에서 검색기능
	@RequestMapping(value="manager/listSearch.do", method = RequestMethod.POST)
	public ModelAndView listSearch(Model model, HttpServletRequest request) throws Exception { 
		System.out.println("승인 서치 리스트");
		
		System.out.println(request.getParameter("sellAcknowledgment"));
		
		/*String param = (String)request.getParameter("sellAcknowledgment");*/		
		HashMap map = getParameterMap(request);

		System.out.println(map);
		
		ArrayList<ApprovalVO> alist = new ArrayList<ApprovalVO>();
		alist = service.selectApprovalSearchList(map);
		
		System.out.println(alist.size());
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		int totalCnt = service.totalCnt(map);
		
		model.addAttribute("currPage", map.get("currPage"));
		model.addAttribute("totalCnt", totalCnt);
		
		model.addAttribute("alist", alist);
		
		return mv;
		
	}
	
	
	//승인대기글 상세페이지
	@RequestMapping(value="/approvalDetail.do", method = RequestMethod.GET)
	public String getWrittenBoard(HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("승인대기글 상세보기");
		
		
		int param = Integer.parseInt((String)request.getParameter("num"));
		System.out.println(param);
		
		ManagerDetailVO svo = new ManagerDetailVO();
		svo = service.selectDetail(param);
		
		System.out.println(svo);
		
		request.setAttribute("svo", svo);
		
		return "manager/approvalDetail";
	}
	
	
	
	//update -> 승인대기를 [승인완료]로 허가 후 승인대기리스트페이지로 넘기기
	@RequestMapping(value="/approvalFin.do", method = RequestMethod.GET)
	public String approvalCompleted(HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("승인완료");
		int param = Integer.parseInt((String)request.getParameter("num"));
		
		////////mapper에서 parameterType을 int가 아닌 HashMap으로 할 때. ////아래 주석처리 돼 있는걸로 같이 하면됨.
		//HashMap map = getParameterMap(request);
		System.out.println(param);
		
		//update는 결과처리를 int로
		int alist = service.approvalUpdate(param);
		//int alist = service.approvalUpdate(map);
		request.setAttribute("alist", service.selectApprovalList()); //승인대기리스트를 다시 불러와야하니까
		
		System.out.println(alist);
		
		
		return "manager/approvalBoard";
	}
	
	
	
	
	
	//회원리스트
	@RequestMapping(value="/memberStopped.do", method = RequestMethod.GET)
	public String memberList(Model model, HttpServletRequest request) throws Exception {
		System.out.println("회원리스트");

		ArrayList<MemberStoppedVO> mlist = new ArrayList<MemberStoppedVO>();
		mlist = service.selectMemberlList();
		ArrayList<MemberStoppedVO> MemberList = new ArrayList<MemberStoppedVO>();
		System.out.println(mlist.size());
		
		request.setAttribute("mlist", mlist);

		HashMap paramMap = new HashMap();
		paramMap.put("startIndex", 1);
		paramMap.put("lastIndex", 10);
		MemberList=service.getMemberListPaging(paramMap);
		request.setAttribute("MemberList", MemberList);
		request.setAttribute("totalCnt", mlist.size());

		System.out.println(mlist);
		//Controller 에서 작업클래스(impl)로 부터 받아온 request를 가지고, view페이지(jsp)로 이동시킴
		return "manager/memberList";

	}
	
	//회원리스트 페이징
		@RequestMapping(value="/member/memberStopped.do", method = RequestMethod.POST)
		public ModelAndView memberListPage(Model model, HttpServletRequest request) throws Exception {
			System.out.println("회원리스트");

			ArrayList<MemberStoppedVO> mlist = new ArrayList<MemberStoppedVO>();
			mlist = service.selectMemberlList();
			ArrayList<MemberStoppedVO> MemberList = new ArrayList<MemberStoppedVO>();
			System.out.println(mlist.size());
			
			request.setAttribute("mlist", mlist);
			HashMap map = getParameterMap(request);
			MemberList=service.getMemberListPaging(map);
			request.setAttribute("MemberList", MemberList);
			request.setAttribute("totalCnt", mlist.size());
			
			model.addAttribute("MemberList", MemberList);
			model.addAttribute("currPage", map.get("currPage"));
			model.addAttribute("totalCnt", mlist.size());
			
			
			ModelAndView mv = new ModelAndView("jsonView");
			System.out.println(mlist);
			//Controller 에서 작업클래스(impl)로 부터 받아온 request를 가지고, view페이지(jsp)로 이동시킴
			return mv;

		}
	
	//회원정지
	@RequestMapping(value="/stopMember.do", method = RequestMethod.GET)
	public String stopMember(HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("회원정지");
		
		String id = (String)request.getParameter("id");
		System.out.println(id);
		int maccount = service.stopAccount(id);
		
		if(maccount == 1) { // 1행 추가 되는지 확인
			System.out.println("회원 정지 성공");
		} else {
			System.out.println("회원 정지 실패");
		}
		
		return "redirect:memberStopped.do";
	}
	
	//회원정지풀기
	@RequestMapping(value="/undoStopMember.do", method = RequestMethod.GET)
	public String undoStopMember(HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("회원정지풀기");
		
		String id = (String)request.getParameter("id");
		System.out.println(id);
		int uaccount = service.undoStopAccount(id);
		
		if(uaccount == 1) {
			System.out.println("회원 정지 풀기 성공");
		} else {
			System.out.println("회원 정지 풀기 실패");
		}
		
		return "redirect:memberStopped.do";
	}
	
	
	
	
	//승인리스트 첫 페이지 토탈 갯수확인
	@RequestMapping(value="/manager/showPage.do", method = RequestMethod.POST)
	public ModelAndView showPage(Model model,HttpServletRequest request) throws Exception {
		System.out.println("첫 페이지 확인");

		HashMap map = getParameterMap(request);
		int totalCnt = service.totalCnt(map);
		
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		
		model.addAttribute("totalCnt", totalCnt);
		
		return mv;

	}
	
	 @RequestMapping(value = "manager/insertSellPlacePage.do", method = RequestMethod.GET)
	 	public String insertSellPlacePage(HttpServletRequest request) {
	 		HashMap paramMap = getParameterMap(request);
	 		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	 		System.out.println("판매장소 등록 페이지 이동");

	 		
	 		
	 		String result = "manager/insertSellPlace";



	 		return result;
	 	}
		 
		 
		 @RequestMapping(value = "manager/insertSellPlace.do", method = RequestMethod.GET)
		 	public String insertSellPlace(HttpServletRequest request) {
		 		HashMap paramMap = getParameterMap(request);
		 		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		 		System.out.println("판매장소 등록");
		 		
		 		
		 		System.out.println(paramMap);
		 		
		 		
		 		String sido = paramMap.get("addr").toString().split(" ")[0];
		 		String sgg = paramMap.get("addr").toString().split(" ")[1];
		 		System.out.println(sido + "," + sgg);
		 		paramMap.put("sido", paramMap.get("addr").toString().split(" ")[0]);
		 		paramMap.put("sgg", paramMap.get("addr").toString().split(" ")[1]);
		 		try {
		 			service.insertSellPlace(paramMap);
				} catch (Exception e) {
					paramMap.put("sgg", paramMap.get("addr").toString().split(" ")[1] + paramMap.get("addr").toString().split(" ")[2]);
					service.insertSellPlace(paramMap);
				}
		 		
		 		int item = Integer.parseInt((String)paramMap.get("item"));
		 		for(int i = 1; i<=item; i++){
		 			paramMap.put("no", i);
		 			service.insertSellPlaceItem(paramMap);
		 		}
		 		
		 		String result = "manager/insertSellPlace";



		 		return result;
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
