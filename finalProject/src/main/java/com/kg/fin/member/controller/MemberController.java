package com.kg.fin.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.member.board.vo.BoardVO;
import com.kg.fin.member.board.vo.MemberVO;
import com.kg.fin.member.board.vo.ReportVO;
import com.kg.fin.member.mail.Email;
import com.kg.fin.member.mail.EmailSender;
import com.kg.fin.member.service.MemberService;
import com.kg.fin.sellImg.vo.SellImgVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private Email email;
	
	@Autowired
	private JavaMailSender mailSender;

	// *****************************< 로그인 >***************************** //

	// 로그인페이지 이동
	@RequestMapping(value = "/loginPage.do", method = RequestMethod.GET)
	public String loginPage(Model model, HttpServletRequest request) {
		System.out.println("loginPage");
		String result = "member/loginPage";
		return result;
	}
	
	// 로그인 아이디 비번 체크
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("loginCheck.do");
		String result = "";

		HashMap<String, String> paramMap = getParameterMap(request);
		System.out.println(paramMap);
		int check = service.loginCheck(paramMap);
		System.out.println(service.loginCheck(paramMap));

		if (check == 1) {
			session.setAttribute("loginId", paramMap.get("id"));
			System.out.println("메인 이동");
			result = "redirect:loginGetInfo.do";
		} else if (check == 0) {
			result = "member/loginPage";
		}
		return result;
	}
	
	// 아이디 찾기 폼 이동
	@RequestMapping("/findIdForm.do")
	public String findIdForm(HttpServletRequest request) {
		return "member/find_Id_Form";
	}
	
	// 아이디 찾기
	@RequestMapping("/findId.do")
	public void findId(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("findId.do");
		HashMap<String, String> paramMap = getParameterMap(request); //작성한 이메일 가져옴
		String email = (String)paramMap.get("writeEmail01")+"@"+(String)paramMap.get("writeEmail02");
		paramMap.put("email", email);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String findId = service.getId(paramMap);
		if(findId==null) {
			out.println("<script>alert('존재 하지 않는 이메일입니다.'); location.href='findIdForm.do';</script>");
			out.flush();
		} else {
			session.setAttribute("findId", findId);
			out.println("<script>location.href='answerId.do';</script>");
			out.flush();
		}
	}
	
	// 아이디 결과 폼 이동
	@RequestMapping("/answerId.do")
	public String answerId(HttpServletRequest request, HttpSession session) {
		return "member/find_Id";
	}
	
	// 비밀번호 찾기 폼 이동
	@RequestMapping("/findPwForm.do")
	public String findPwForm(HttpServletRequest request) {
		return "member/find_Pw_Form";
	}
	
	// 비밀 번호 찾기 - 이메일로 발송
	@RequestMapping("/sendPw.do")
    public void sendPw (HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		System.out.println("sendPw.do");
		HashMap<String, String> paramMap = getParameterMap(request);
		
    	String id = (String)paramMap.get("writeId");
    	String e_mail = (String)paramMap.get("writeEmail01") + "@" + (String)paramMap.get("writeEmail02");
    	paramMap.put("e_mail", e_mail);
    	String userPw = service.checkUser(paramMap);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
    	
    	if(userPw != null) {
    		email.setContent("비밀번호는 "+userPw+" 입니다.");
    		email.setReceiver(e_mail);
    		email.setSubject(id+"님 중고 거래 사이트 비밀번호 찾기 메일입니다.");
    		emailSender.SendEmail(mailSender, email);
    		
    		out.println("<script>alert('해당 이메일로 비밀번호를 전송하였습니다'); location.href='loginPage.do';</script>");
			out.flush();
    	} else {
    		out.println("<script>alert('아이디와 이메일을 다시 확인해주세요'); location.href='findPwForm.do';</script>");
			out.flush();
    	}
        
    }
	
	//로그인 한 사람의 정보 가져옴, 권한도 같이 가져와서 권한이 1이면 일반 유저, 9면 관리자
	@RequestMapping(value="/loginGetInfo.do", method=RequestMethod.GET)
	public String loginGetInfo(HttpSession session, HttpServletRequest req) {
		String id = (String) session.getAttribute("loginId");
		HashMap<String, Object> param = service.loginGetInfo(id);
		System.out.println(param);
		
		int loginStatus = Integer.parseInt((String)param.get("MEMBER_STATUS"));
		session.setAttribute("loginStatus", loginStatus);
		session.setAttribute("paramMap", param); // 마이페이지에서 로그인 유저 정보 가져와야 함
		
		req.setAttribute("paramMap", param);
		req.setAttribute("page", "main.jsp");
		
		String result = "member/pageMove";
		return result;
	}

	// 로그아웃
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("logout");
		String result = "member/pageMove";
		session.invalidate();
		request.setAttribute("page", "main.jsp");
		return result;
	}

	// *****************************< 회원가입 >***************************** //
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/joinPage.do", method = RequestMethod.GET)
	public String joinPage() {
		System.out.println("joinPage.do");
		String result = "member/joinPage";
		return result;
	}

	// 회원 가입 시 아이디 중복 체크
	@RequestMapping(value = "/checkId.do", method = RequestMethod.POST)
	@ResponseBody // jsp에서 넘어온 데이터로 코드를 짜고 가공된 데이터를 json객체로 넘겨주기 위해서 붙이는 annotation
	public ModelAndView checkId(Model model, HttpServletRequest request) {

		System.out.println("checkId.do");
		HashMap paramMap = getParameterMap(request);
		String id = (String) paramMap.get("id");
		int list = service.checkId(id);

		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		model.addAttribute("list", list);
		return mv;
	}

	// 회원 가입 시 닉네임 중복 체크
	@RequestMapping("/checkNick.do")
	@ResponseBody
	public ModelAndView checkNick(Model model, HttpServletRequest request) {

		System.out.println("checkNick.do");
		HashMap paramMap = getParameterMap(request);
		String nick = (String) paramMap.get("nick"); //얘는 AJAX 거쳐서 오는 애라 거기 DATA에 선언된 이름으로 가져와야 혀
		int list = service.checkNick(nick);

		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		model.addAttribute("list", list);
		return mv;
	}
	
	// 회원 가입 시 핸드폰 중복 체크
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ModelAndView checkPhone(Model model, HttpServletRequest request) {
		
		System.out.println("checkPhone.do");
		HashMap paramMap = getParameterMap(request);
		String phone = (String) paramMap.get("phone");
		int list = service.checkPhone(phone);
		
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		model.addAttribute("list", list);
		return mv;
	}
	
	// 회원 가입 시 이메일 중복 체크
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ModelAndView checkEmail(Model model, HttpServletRequest request) {
		
		System.out.println("checkEmail.do");
		HashMap paramMap = getParameterMap(request);
		
		String email = (String) paramMap.get("email1") + (String) paramMap.get("atSign") + (String) paramMap.get("email2");
		int list = service.checkEmail(email);
		
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		model.addAttribute("list", list);
		return mv;
	}
	
	// 회원가입 등록
	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUp(Model model, HttpServletRequest request) throws IOException {

		System.out.println("signUp.do");
		HashMap<Object, Object> param = getParameterMap(request);
		String email=(String)param.get("email01")+"@"+(String)param.get("email02");
		param.put("email", email);
		System.out.println(param);
		System.out.println(param.size());

		if (param.get("id").equals("") || param.get("pw").equals("") || param.get("nickName").equals("")
				|| param.get("name").equals("") || param.get("phone").equals("") || param.get("email").equals("")) {
			return "redirect:joinPage.do";
		} else {
			service.signUp(param);
			return "member/loginPage";
		}

	}
	
	// *****************************< 마이페이지 >***************************** //

	// 마이 페이지 이동 및 관련 정보 로딩
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String myPageMain(HttpServletRequest request, HttpSession session, Model model) {

		System.out.println("myPage.do");

		String id = (String) session.getAttribute("loginId");
		MemberVO user = service.getUserInfo(id);
		System.out.println("갔다옴1");
		model.addAttribute("user", user);
		
		String[] getEmail = user.getMember_email().split("@");
		System.out.println("@@@@@@@@@@"+getEmail);
		String email1 = getEmail[0];
		String email2 = getEmail[1];
		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);

		//신고 게시글 가져오기
		int reportList = service.getMyPageReport(id);
		model.addAttribute("reportList", reportList);
		System.out.println("갔다옴2");

		// ----------------------------------------신고 횟수 가져오기-end

		// 로그인 아이디와 같은 이름을 가진 판매 게시글 가져와서 count 한다.
		ArrayList<BoardVO> sellList = service.getSellList(id);

		int sellListCnt = 0;
		for (BoardVO bv1 : sellList) {
				sellListCnt++;
				System.out.println(sellListCnt);
		}
		request.setAttribute("sellListCnt", sellListCnt);

		// ----------------------------------------판매 횟수 가져오기-end

		// 로그인 아이디와 같은 이름을 가진 구매 게시글 가져와서 count 한다.
		ArrayList<BoardVO> buyList = service.getBuyList(id);

		int buyListCnt = 0;
		for (BoardVO bv2 : buyList) {
				buyListCnt++;
				System.out.println(buyListCnt);
		}
		request.setAttribute("buyListCnt", buyListCnt);
		
		// ----------------------------------------구매 횟수 가져오기-end
		
		// 리뷰 테이블에서 별점을 가져온다. 
		ArrayList<Integer> reviewRating = service.getReviewRating(id);
		
		int stars = 0;
		int[] star = new int[reviewRating.size()];
		for(int i = 0; i < reviewRating.size(); i++) {
			star[i] = reviewRating.get(i);
			stars += star[i];
		}
		
		// ----------------------------------------리뷰 별점 가져오기-end
		
		int credit = reportList*(-3) + sellListCnt*4 + buyListCnt*3 + stars*2;
		request.setAttribute("credit", credit);
		
		// ----------------------------------------신용 등급 가져오기-end
		
		String result = "member/myPage";
		request.setAttribute("contentPage", "editProfile.jsp");

		return result;
	}

	// 마이페이지 닉네임 수정 중복 체크
	@RequestMapping("/myPageNick.do")
	@ResponseBody
	public ModelAndView myPageNick(Model model, HttpServletRequest request, HttpSession session) {

		System.out.println("myPageNick.do");
		String id = (String) session.getAttribute("loginId");
		HashMap paramMap = getParameterMap(request);
		
		String nick = (String) paramMap.get("nick");
		String nNick = service.myPageMyNick(id);
		int list = service.myPageNick(nick);
		
		if(nick.equals(nNick)) {
			list = 2;
			model.addAttribute("list", list);
		} else {
			model.addAttribute("list", list);
		}

		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		return mv;
	}

	// 마이페이지 핸드폰 번호 수정 중복 체크
	@RequestMapping("/myPagePhone.do")
	@ResponseBody
	public ModelAndView myPagePhone(Model model, HttpServletRequest request, HttpSession session) {

		System.out.println("myPagePhone.do");
		String id = (String) session.getAttribute("loginId");
		HashMap paramMap = getParameterMap(request);
		
		String phone = (String) paramMap.get("phone");
		String myPhone = service.myPageMyPhone(id);
		int list = service.myPagePhone(phone);

		if(phone.equals(myPhone)) {
			list = 2;
			model.addAttribute("list", list);
		} else {
			model.addAttribute("list", list);
		}
		
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		model.addAttribute("list", list);
		return mv;
	}
	
	// 마이페이지 이메일 중복 체크
	@RequestMapping("/myPageEmail.do")
	@ResponseBody
	public ModelAndView myPageEmail(Model model, HttpServletRequest request, HttpSession session) {
		
		System.out.println("myPageEmail.do");
		String id = (String) session.getAttribute("loginId");
		HashMap paramMap = getParameterMap(request);
		
		String email = (String) paramMap.get("email1") + (String) paramMap.get("atSign") + (String) paramMap.get("email2");
		String myEmail = service.myPageMyEmail(id);
		int list = service.myPageEmail(email);
		
		if(email.equals(myEmail)) {
			list = 2;
			model.addAttribute("list", list);
		} else {
			model.addAttribute("list", list);
		}
		
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println("mv = " + mv);
		model.addAttribute("list", list);
		return mv;
	}
	
	// 마이페이지 수정하기 버튼
	@RequestMapping("/myPageUpdate.do")
	public String myPageUpdate(HttpServletRequest request, HttpSession session) {
		
		System.out.println("myPageUpdate.do");
		String id = (String)session.getAttribute("loginId");
		
		HashMap<String, String> param = getParameterMap(request);
		param.put("id", id);
		
		String email=(String)param.get("email01")+"@"+(String)param.get("email02");
		param.put("email", email);
		System.out.println(param.size());
		
		int updateCnt = service.myPageUpdate(param);
		if(updateCnt==0) {
			System.out.println("update 실패");
		} else if(updateCnt==1){
			System.out.println("update 성공");
		}
		
		return "redirect:myPage.do";
	}
	
	// 신고 게시글 내역 확인 버튼
	@RequestMapping("/myReportList.do")
	public String myReportList(Model model, HttpServletRequest request, HttpSession session) {
		
		System.out.println("myReportList.do");
		String id = (String)session.getAttribute("loginId");
		
		// 로그인 유저 신고 게시글 번호 가져옴
		ArrayList<BoardVO> reportSell = service.getReportSell(id);
		System.out.println(reportSell);
		if (reportSell.size() == 0){
			reportSell = null;
		}
		// 신고 게시글 가져오기 r.sell_no
		ArrayList<HashMap<String, Object>> reportList = service.getReport(reportSell);
		System.out.println("reportList 가져옴");
		System.out.println(reportList);
		model.addAttribute("reportList", reportList);
		String result = "member/myPage";
		request.setAttribute("contentPage", "reportList.jsp");
		return result;

	}

	// ? 버튼
	@RequestMapping("/creditPopup.do")
	public String creditPopup(HttpServletRequest request) {
		return "member/creditPopup";
	}
	
	// *****************************< 구매내역 >***************************** //

	// 구매 내역 이동 및 구매 내역 가져옴
	@RequestMapping(value = "/PurchaseHistory.do", method = RequestMethod.GET)
	public String PurchaseHistory(Model model, HttpServletRequest request, HttpSession session) {
		
		System.out.println("PurchaseHistory.do");
		String id = (String) session.getAttribute("loginId");
		
		HashMap paramMap = new HashMap();
		paramMap.put("startIndex", 1);
		paramMap.put("lastIndex", 10);
		paramMap.put("id", id);
		
		ArrayList<HashMap<String, Object>> PurchaseList = service.getPurchaseList(paramMap);
		model.addAttribute("PurchaseList", PurchaseList);
		
		String result = "member/myPage";
		request.setAttribute("contentPage", "PurchaseHistory.jsp");
		return result;
		
	}

	// 구매 내역 페이징
	@RequestMapping(value = "member/myPageList.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView myPageList(Model model, HttpServletRequest request) {
		
		HashMap paramMap = getParameterMap(request);
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");

		// 현재 페이지
		int currPage = Integer.parseInt((String) paramMap.get("currPage"));
		paramMap.put("id", id);

		// 총 리스트 갯수
		int totalCnt = service.getTotalCnt(id);

		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("currPage", currPage);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", service.getPurchaseList(paramMap));
		return mv;
	}

	// *****************************< 판매내역 >***************************** //

	// 판매 내역 이동 및 판매 내역 가져옴 
	@RequestMapping(value = "/SalesHistory.do", method = RequestMethod.GET)
	public String SalesHistory(HttpServletRequest request, HttpSession session, Model model) {
		System.out.println("SalesHistory.do");
		String id = (String) session.getAttribute("loginId");
		
		HashMap paramMap = new HashMap();
		paramMap.put("startIndex", 1);
		paramMap.put("lastIndex", 10);
		paramMap.put("id", id);
		
		ArrayList<HashMap<String, Object>> SalesList = service.getSalesList(paramMap);
		model.addAttribute("SalesList", SalesList);
		
		String result = "member/myPage";
		request.setAttribute("contentPage", "SalesHistory.jsp");
		return result;
		
	}

	// 판매 내역 페이징
	@RequestMapping("member/salesListPaging.do")
	@ResponseBody
	public ModelAndView salesListPaging(Model model, HttpServletRequest request) {
		System.out.println("salesListPaging.do");
		HashMap paramMap = getParameterMap(request);
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginId");

		// 현재 페이지
		int currPage = Integer.parseInt((String) paramMap.get("currPage"));
		paramMap.put("id", id);

		// 총 리스트 갯수
		int totalCnt = service.salesTotalCnt(id);

		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("currPage", currPage);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("list", service.getSalesList(paramMap));
		return mv;
	}
	
	// *****************************< 지도 & HashMap.. >***************************** //

	// 지도보기
	@RequestMapping(value = "/mapView.do", method = RequestMethod.GET)
	public String mapViewMove(Model model, HttpServletRequest request) {
		String result = "map/mapView";
		return result;
	}

	// HashMap에 담긴 내용 보여줌
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
