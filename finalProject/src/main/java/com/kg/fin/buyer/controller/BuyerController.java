package com.kg.fin.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.buyer.service.BuyerService;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.seller.service.SellerService;
import com.kg.fin.seller.vo.SellerVO;



@Controller
public class BuyerController {
	
	@Resource(name = "buyerService")
	private BuyerService buyerService;
	
	@Resource(name = "sellerService")
	private SellerService sellerService;

	/*@requestmapping(value = "/buyer.do")
	public string buyer(model model) throws exception {
		
		arraylist<buyervo> alist = buyerservice.buyermainoutput();
		model.addattribute("alist", alist);
		//return "buyer";
		return "buyer";
		
	}*/
	
	@RequestMapping(value = "/buyer/buyerContentView.do")
	public String buyerContentView(Model model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session, SellerVO sellerVo) throws Exception {
		
		int sell_no = Integer.parseInt(request.getParameter("sell_no"));
		
		int sell_cnt = sellerService.sellCntSelect(sell_no);
		sellerVo.setSell_cnt(sell_cnt+1);
		sellerService.sellCntUpdate(sellerVo);
	
		ArrayList<SellerVO> voList = sellerService.sellOneSelect(sell_no);
		System.out.println(voList.get(0).getSell_image()); //이미지1
		System.out.println(voList.get(1).getSell_image()); //이미지2
		System.out.println(voList.get(2).getSell_image()); //이미지3
		
		//String fileP1 = request.getRealPath("/");
		//String begin = fileP1.substring(0, fileP1.indexOf("\\.metadata"));
		//String filePath = "/resources/lib/images/sell_images/";
		//System.out.println("filePath : " + filePath);
		
		//메인이미지 3개를 모두 가져와야 하기 때문에 ArrayList로 받는다.
		for(int i=0; i<voList.size(); i++) {
			model.addAttribute("main_img_"+i, voList.get(i).getSell_image());
		}
		
		String member_id = (String) session.getAttribute("loginId");
		if(member_id != null) {
			HashMap<String, Object> param = sellerService.memberSelect(member_id);
			HashMap<String, Object> param1 = sellerService.memberSelect(member_id);
			model.addAttribute("member_nic",(String)param.get("MEMBER_NIC")); //로그인한 회원 닉네임
			model.addAttribute("member_status", param1.get("MEMBER_STATUS")); //로그인한 회원 상태(관리자확인용)
			
			sellerVo.setSell_buyer_id(member_id);
			int buyerPaySelect = buyerService.buyerPaySelect(sellerVo);
			session.setAttribute("buyerPaySelect", buyerPaySelect);
			
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
		String sell_write_date = sdf2.format(voList.get(0).getSell_write_date());
		
		//int cnt = sellerService.pageCnt(Integer.parseInt(request.getParameter("sell_no")));
		
		
		model.addAttribute("sell_write_date", sell_write_date);
		model.addAttribute("voList", voList);
		model.addAttribute("sellerVO", voList.get(0));
		HashMap param = new HashMap();
		param.put("sell_no",voList.get(0).getSell_no());
		param.put("startIndex",1);
		param.put("lastIndex",10);
		
		if(buyerService.sellCommentSelect(param) != null) {
			ArrayList<SellCommentVO> commentList = buyerService.sellCommentSelect(param);
			for(SellCommentVO vo : commentList) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
				String date = sdf.format(vo.getSell_comment_write_date());
				vo.setSell_comment_write_date_str(date);
				HashMap<String, Object> param2 = sellerService.memberSelect(vo.getSell_comment_writer());
				vo.setSell_comment_writer_nic((String)param2.get("MEMBER_NIC")); //댓글 작성자 닉네임
				vo.setSell_comment_re_total(buyerService.commentGroupTotalSelect(vo));
				
			}
			
			ArrayList<SellCommentVO> commentGroupList = buyerService.commentGroupSelect(voList.get(0).getSell_no());
			
			/*
			int startIndex = 1;
			int lastIndex = 10;
			ArrayList<SellCommentVO> jin_commentList = new ArrayList<SellCommentVO>();
			for (int i=1;i<=commentList.size();i++) {
				if(i>=startIndex && i<=lastIndex) {
					jin_commentList.add(commentList.get(i));
				}
			}
			
			
			model.addAttribute("commentList", jin_commentList);
			model.addAttribute("currPage", 1);
			model.addAttribute("totalCount", commentList.size());*/
			
			model.addAttribute("commentGroupList", commentGroupList);
			model.addAttribute("commentList", commentList);
			model.addAttribute("sellCommentCnt", buyerService.sellCommentCntSelect(voList.get(0).getSell_no()));
		}
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		if(buyerService.selectReview(sell_no) != null) {
			map = buyerService.selectReview(sell_no);
			model.addAttribute("review_map", map);
		}
		
		
		HashMap paramMap2 = getParameterMap(request);
		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		HashMap<String,Object> map2 = new HashMap<String,Object>();
		
		
		
		
		map2 = buyerService.selectReview(sell_no);
		
		ArrayList<HashMap<String,Object>> alist = new ArrayList<HashMap<String,Object>>();
		
		alist = buyerService.selectReviwImages(sell_no);
		
		//list = service.storeList();

		//HashMap<String, String> map = null;

		//HashMap<String, String> map2 = null;

		request.setAttribute("map", map2);
		request.setAttribute("alist", alist);
		
		
		
		
		
		return "buyer/buyer";
		
	}
	
	@RequestMapping(value = "/buyer/sellReCommentSelect.do", method = RequestMethod.POST)
	public ModelAndView sellReCommentSelect(HttpServletRequest req, Model model, HttpSession session) {
		System.out.println("sellReCommentSelect 답글 리스트");
		HashMap<String, Object> map = getParameterMap(req);
		System.out.println("답글 map ::: " + map);
	
		int sell_no = Integer.parseInt(req.getParameter("sell_no"));
		
		ArrayList<HashMap<String, Object>> result = buyerService.sellReCommentSelect(map);
		
		SellCommentVO vo = new SellCommentVO();
		ArrayList<SellCommentVO> voList = new ArrayList<SellCommentVO>();
		
		for(int i=0; i<result.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
			String date = sdf.format((Date)result.get(i).get("SELL_COMMENT_WRITE_DATE"));
			System.out.println(date);
			vo.setSell_comment_write_date_str(date);
			voList.add(vo);
		}
		model.addAttribute("voList", voList);
		
		HashMap<String, Object> member = sellerService.memberSelect((String)session.getAttribute("loginId"));
		model.addAttribute("member_nic", (String)member.get("MEMBER_NIC"));

		ModelAndView mv = new ModelAndView("jsonView");
		
		result = buyerService.sellReCommentSelect(map);
		model.addAttribute("result", result);
		System.out.println("답글 result ::: " + result);
		return mv;
	}
	
	
	//buyer_popup.jsp 페이지 이동
	/*@RequestMapping(value = "/buyer/buyerPopup.do")
	public String buyerPopup(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{

		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		String member_id = (String) session.getAttribute("loginId");
		
		if(member_id == null) {
			out.println("<script>");
			out.println("alert('회원만 구매 가능 합니다.')");
			out.println("window.close();");
			out.println("</script>");
			out.flush();
			return "";
			
		} else {
			session.setAttribute("price", request.getParameter("price"));
			session.setAttribute("sell_no", request.getParameter("sell_no"));
			return "buyer/buyerPopup";
			
		}
		
	}*/
	
	///buyerPayment.jsp 페이지 이동
	@RequestMapping(value = "/buyer/buyerPayment.do")
	public String buyerPayment(HttpSession session, HttpServletRequest request){
		
		String buyer_id = (String)session.getAttribute("loginId");
		HashMap<String, Object> param = sellerService.memberSelect(buyer_id);
		
		session.setAttribute("param", param);
		session.setAttribute("price", request.getParameter("price"));
		session.setAttribute("sell_no", request.getParameter("sell_no"));
		
		return "buyer/buyerPayment";
	}
	
	///buyerUpdate - sell테이블에 구매자 업데이트
	@RequestMapping(value = "/buyer/buyerUpdate.do")
	public String buyerUpdate(HttpSession session, HttpServletResponse response) throws IOException{
		System.out.println("buyerUpdate");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		String buyer_id = (String)session.getAttribute("loginId");
		
		if(buyer_id == null) {
			
			out.println("<script>");
			out.println("alert('회원만 구매 가능 합니다.')");
			out.println("</script>");
			
		} else {
			int sell_no = Integer.parseInt((String)session.getAttribute("sell_no"));
			
			HashMap<String,Object> param = new HashMap<String,Object>();
			param.put("sell_buyer_id", buyer_id);
			param.put("sell_no", sell_no);
			
			int buyerUpdateCheck = buyerService.buyerUpdate(param);
			if(buyerUpdateCheck == 1) {
				System.out.println("업데이트 성공");
			} else {
				System.out.println("업데이트 실패");
			}
			
			out.println("<script>");
			out.println("window.close();");
			out.println("</script>");
		}
		
		out.flush();
		
		return "buyer/buyerPayment";
	}
	
	//commentOneSelect - 댓글 하나 검색하기
	@RequestMapping(value = "/buyer/commentOneSelect.do")
	public ModelAndView commentOneSelect(SellCommentVO vo, HttpServletRequest req, Model model){
		SellCommentVO sellCommentVo = buyerService.sellCommentOneSelect(vo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
		String date = sdf.format(sellCommentVo.getSell_comment_write_date());
		
		model.addAttribute("date", date);
		model.addAttribute("sellCommentVo", sellCommentVo);
		ModelAndView mv = new ModelAndView("jsonView");
		return mv;
	}
	
	//review 검색
	@RequestMapping(value = "/review/review.do", method = RequestMethod.GET)
	public String review(HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		int sell_no = Integer.parseInt(request.getParameter("sell_no"));
		
		map = buyerService.selectReview(sell_no);
		
		ArrayList<HashMap<String,Object>> alist = new ArrayList<HashMap<String,Object>>();
		
		alist = buyerService.selectReviwImages(sell_no);
		
		//list = service.storeList();

		//HashMap<String, String> map = null;

		//HashMap<String, String> map2 = null;

		request.setAttribute("map", map);
		request.setAttribute("alist", alist);
		
		
		String result = "buyer/review";

		//model.addAttribute("map", paramMap);
		return result;
		//return "buyer/review";
	}
	
	//review 등록 페이지 이동하기
	@RequestMapping(value = "/review/insertReviewPage.do", method = RequestMethod.GET)
	public String insertReviewPage(HttpServletRequest request) {
		HashMap paramMap = getParameterMap(request);
		//List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String result = "buyer/insertReview";
		return result;
	}
	
	//review 등록하기
	@RequestMapping(value = "/review/insertReview.do", method = RequestMethod.POST)
	public String insertReview(HttpServletRequest request,
							   MultipartHttpServletRequest mtfRequest,
							   @RequestParam("sellNo") int sellNo,
							   @RequestParam("seller_id") String seller_id,
							   @RequestParam("contents") String contents,
							   @RequestParam("id") String id,
							   @RequestParam("star") String star) {
		HashMap paramMap = getParameterMap(request);
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file1");

		System.out.println(paramMap);
		
		System.out.println(contents);
		
		
		
		ServletContext context = request.getSession().getServletContext();

        String appPath = context.getRealPath("resources/lib/images/review/img/");

        System.out.println(appPath);
		
      
    
        int cnt = 0;
        
        HashMap<String,Object> param = new HashMap<String,Object>();
        HashMap<String,Object> param2 = new HashMap<String,Object>();
        
        param.put("sellNo", sellNo);
        param.put("seller_id", seller_id);
        param.put("memberId", id);
        param.put("reviewContents", contents);
        param.put("reviewRating", star);
        
        param2.put("sellNo", sellNo);
        
        buyerService.insertReview(param);
        
        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);
            String crrtm = "" + System.currentTimeMillis();
            String image = "/resources/lib/images/review/img/" + crrtm + originFileName;
            String safeFile = appPath + crrtm + originFileName;
            try {
                mf.transferTo(new File(safeFile));
                
                param2.put("reviewsImage", image);
                cnt++;
                param2.put("reviewsImageNo", cnt);
                System.out.println(param2);
                buyerService.insertReviewImg(param2);
                
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map = buyerService.selectReview(sellNo);
		
		ArrayList<HashMap<String,Object>> alist = new ArrayList<HashMap<String,Object>>();
		
		alist = buyerService.selectReviwImages(sellNo);
		
		//list = service.storeList();

		//HashMap<String, String> map = null;

		//HashMap<String, String> map2 = null;

		request.setAttribute("map", map);
		request.setAttribute("alist", alist);
		
		
		String result = "buyer/review";



		return result;
	}
	
	//판매글 댓글+답글 등록
	@RequestMapping(value = "/buyer/commentInsert.do", method = RequestMethod.POST)
	public ModelAndView sellCommentInsert(SellCommentVO commentVO, HttpSession session
									, HttpServletRequest req, Model model){
		String sell_comment_str = Integer.toString(buyerService.sellCommentMaxNo());
		System.out.println("commentInsert");
		
		int sell_comment_no = 0;
		if(sell_comment_str != null) {
			sell_comment_no = buyerService.sellCommentMaxNo()+1;
		}
		
		String sell_comment_writer = (String)session.getAttribute("loginId");
		commentVO.setSell_comment_no(sell_comment_no);
		commentVO.setSell_comment_writer(sell_comment_writer);
		commentVO.setSell_comment_secret(1);
		
		if(commentVO.getSell_comment_grouplayer() == 0) {
			//댓글
			commentVO.setSell_comment_originno(sell_comment_no);
			buyerService.sellCommentInsert(commentVO);			
		} else {
			//답글
			int sell_comment_groupord = buyerService.commentGroupMaxSelect(commentVO)+1; //답글 순서
			commentVO.setSell_comment_groupord(sell_comment_groupord);
			
			buyerService.sellCommentInsert(commentVO);
			
			HashMap<String, Object> map = getParameterMap(req);
			ArrayList<HashMap<String, Object>> result = buyerService.sellReCommentSelect(map);
			model.addAttribute("comment_re_cnt", result.size()); //전체 답글 수 + 1
		}

		ModelAndView mv = new ModelAndView("jsonView");
		return mv;
	}
	
	//판매글 댓글+답글 수정
	@RequestMapping(value = "/buyer/commentUpdate.do", method = RequestMethod.POST)
	public ModelAndView sellCommentUpdate(SellCommentVO vo, Model model) throws IOException{
		System.out.println("sellCommentUpdate");
		buyerService.sellCommentUpdate(vo);
		ModelAndView mv = new ModelAndView("jsonView");
		return mv;
	}
		
	//판매글 댓글+답글 삭제
	@RequestMapping(value = "/buyer/commentDelete.do", method = RequestMethod.POST)
	public ModelAndView sellCommentDelete(SellCommentVO vo, Model model) throws IOException{
		System.out.println("sellCommentDelete");
		int comment_delete_check = buyerService.sellCommentDelete(vo);
		model.addAttribute("comment_delete_check", comment_delete_check);
		ModelAndView mv = new ModelAndView("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "/buyer/buyerComment.do")
	public ModelAndView buyerComment(Model model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session, SellerVO sellerVo) throws Exception {
	
	
		
		HashMap map = getParameterMap(request);
		System.out.println(map);
		int sellNo=Integer.parseInt((String)map.get("sell_no"));
		
			//댓글 검색
			ArrayList<SellCommentVO> commentList = buyerService.sellCommentSelect(map);
			for(SellCommentVO vo : commentList) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
				String date = sdf.format(vo.getSell_comment_write_date());
				vo.setSell_comment_write_date_str(date);
				HashMap<String, Object> param2 = sellerService.memberSelect(vo.getSell_comment_writer());
				vo.setSell_comment_writer_nic((String)param2.get("MEMBER_NIC")); //댓글 작성자 닉네임
				vo.setSell_comment_re_total(buyerService.commentGroupTotalSelect(vo));
				
			}
			//댓글 + 답글 검색
			ArrayList<SellCommentVO> commentGroupList = buyerService.commentGroupSelect(sellNo);
			
			/*
			int startIndex = 1;
			int lastIndex = 10;
			ArrayList<SellCommentVO> jin_commentList = new ArrayList<SellCommentVO>();
			for (int i=1;i<=commentList.size();i++) {
				if(i>=startIndex && i<=lastIndex) {
					jin_commentList.add(commentList.get(i));
				}
			}
			
			
			model.addAttribute("commentList", jin_commentList);
			model.addAttribute("currPage", 1);
			model.addAttribute("totalCount", commentList.size());*/
			System.out.println( map.get("totalCnt"));
			model.addAttribute("currPage", map.get("currPage"));
			model.addAttribute("totalCnt", map.get("totalCnt"));
			
			
			model.addAttribute("commentGroupList", commentGroupList);
			model.addAttribute("commentList", commentList);
			model.addAttribute("sellCommentCnt", buyerService.sellCommentCntSelect(sellNo));
			
			
			HashMap<String, Object> member = sellerService.memberSelect((String)session.getAttribute("loginId"));
			model.addAttribute("member_nic", (String)member.get("MEMBER_NIC"));
	
		System.out.println(buyerService.sellCommentCntSelect(sellNo));
		
		ModelAndView mv=new ModelAndView("jsonView");
		
		
		
		return mv;
		
	}
	
	
	//HashMap에 담긴 내용 보여줌
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
