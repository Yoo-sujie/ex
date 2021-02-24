package com.kg.fin.seller.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kg.fin.buyer.service.BuyerService;
import com.kg.fin.koreasd.vo.KoreasdVO;
import com.kg.fin.koreasgg.vo.KoreaSggVO;
import com.kg.fin.member.service.MemberService;
import com.kg.fin.sellCategory.vo.SellCategoryVO;
import com.kg.fin.sellComment.vo.SellCommentVO;
import com.kg.fin.sellImg.vo.SellImgVO;
import com.kg.fin.sellPlace.vo.SellPlaceVO;
import com.kg.fin.seller.service.SellerService;
import com.kg.fin.seller.vo.SellerVO;

import sun.print.resources.serviceui;


@Controller
public class SellerController {

	@Resource(name = "sellerService")
	private SellerService sellerService;

	@Resource(name = "buyerService")
	private BuyerService buyerService;

	
	/*//seller.jsp 페이지 이동
	@RequestMapping(value = "/seller/sellerView.do", method = RequestMethod.GET)
	public String sellerView(HttpServletRequest req, Model model, HttpSession session) {
		System.out.println("sellerView");
		ArrayList<SellerVO> voList = sellerService.sellAllSelect((String)session.getAttribute("loginId"));
		model.addAttribute("voList", voList);
		return "seller/seller";
	}*/
	//search.jsp 페이지 이동
		@RequestMapping(value = "/seller/sellerView.do", method = RequestMethod.GET)
		public String sellerView(HttpServletRequest req, Model model, HttpSession session) {
			System.out.println("sellerView");
			return "redirect:/search/searchPage.do";
		}
	
	//판매글 입력
	@RequestMapping(value = "/seller/sellerInsert.do", method = RequestMethod.POST)
	public void sellerInsert(SellerVO vo, HttpServletResponse res, HttpServletRequest req, 
			MultipartHttpServletRequest mReq, HttpSession session) throws IOException{
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html; charset=UTF-8");
		
		String sell_contents = req.getParameter("sell_contents");
		String check1_contents = sell_contents.replace("<p>", "");
		String check2_contents = check1_contents.replace("</p>", "");
		String check3_contents = check2_contents.replace("<br>", "");
		
		try {
			//게시글 내용 등록
			System.out.println("sellerInsert(1)");
			int sell_no = sellerService.sellNoSelect()+1;
			System.out.println("글 등록 sell_no : " + sell_no);
			vo.setSell_no(sell_no); //sellNo 자동 증가
			vo.setSell_seller_id((String)session.getAttribute("loginId"));
		
			int c = sellerService.sellInsert(vo);
			
			if(c==1) { //게시글이 입력되었다면
				
				//메인 이미지 등록
				System.out.println("sellerInsert(2) - img");
				
				String file_img1 = mReq.getFile("file_upload1").getOriginalFilename();
				String file_img2 = mReq.getFile("file_upload2").getOriginalFilename();
				String file_img3 = mReq.getFile("file_upload3").getOriginalFilename();
				
				System.out.println(file_img1 + file_img2 + file_img3);
				
				if(file_img1.equals("") || file_img2.equals("") || file_img3.equals("")) {
					//만약 메인 이미지 3개중 하나라도 빠졌다면
					//입력된 판매글 삭제 후 뒤로 이동
					sellerService.sellDelete(sell_no);
					out.println("<script>");
					out.println("alert('이미지를 모두 넣어주세요.')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					//메인 이미지 3개가 넘어왔다면
					MultipartFile file1 = mReq.getFile("file_upload1");
					MultipartFile file2 = mReq.getFile("file_upload2");
					MultipartFile file3 = mReq.getFile("file_upload3");
					
					System.out.println("파일 이름 : " + file1.getOriginalFilename());
			        System.out.println("파일 크기 : " + file1.getSize());
	
			        System.out.println("파일 이름 : " + file2.getOriginalFilename());
			        System.out.println("파일 크기 : " + file2.getSize());
			        
			        System.out.println("파일 이름 : " + file3.getOriginalFilename());
			        System.out.println("파일 크기 : " + file3.getSize());
			        
			        SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
			        Date date = new Date();
			        
			        String fileName1 = sell_no + "_main01_" + simDf.format(date) + "_" + file1.getOriginalFilename();
	        		String fileName2 = sell_no + "_main02_" + simDf.format(date) + "_" + file2.getOriginalFilename();
	        		String fileName3 = sell_no + "_main03_" + simDf.format(date) + "_" + file3.getOriginalFilename();
	        		
	        		String filePath = req.getRealPath("resources/lib/images/sell_images/");
	        		System.out.println(filePath);
	        		//String begin = fileP1.substring(0, fileP1.indexOf("\\.metadata"));
	        		//String filePath = begin + "\\finalProject\\src\\main\\webapp\\resources\\lib\\images\\sell_images\\";
	        		System.out.println("filePath : " + filePath);
	        		
			        try (
			        		FileOutputStream fos1 = new FileOutputStream(filePath + fileName1);
			        		FileOutputStream fos2 = new FileOutputStream(filePath + fileName2);
			        		FileOutputStream fos3 = new FileOutputStream(filePath + fileName3);
			                //파일 저장할 경로 + 저장할 파일명
			        		//을 fileOutputStream 객체로 생성
			                InputStream is1 = file1.getInputStream();
			        		InputStream is2 = file2.getInputStream();
			        		InputStream is3 = file3.getInputStream();) {
			                //file1로 부터 inputStream을 가져온다.
			            
			            int readCount = 0;
			            byte[] buffer = new byte[1024];
			            //파일을 읽을 크기 만큼의 buffer를 생성하고
			            //( 보통 1024, 2048, 4096, 8192 와 같이 배수 형식으로 버퍼의 크기를 잡는 것이 일반적이다.)
			            
			            while ((readCount = is1.read(buffer)) != -1) {
			                //파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
			                fos1.write(buffer, 0, readCount);
			                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
			            }
			            while ((readCount = is2.read(buffer)) != -1) {
			            	fos2.write(buffer, 0, readCount);
			            }
			            while ((readCount = is3.read(buffer)) != -1) {
			            	fos3.write(buffer, 0, readCount);
			            }
			        } catch (Exception ex) {
			            throw new RuntimeException("file Save Error");
			        }
			        
			        SellImgVO imgVo = new SellImgVO();
			        
			        imgVo.setSell_image_no(1); imgVo.setSell_no(sell_no);
			        imgVo.setSell_image("\\resources\\lib\\images\\sell_images\\" + fileName1);
			        sellerService.sellImgInsert(imgVo);
	
			        imgVo.setSell_image_no(2); imgVo.setSell_no(sell_no);
			        imgVo.setSell_image("\\resources\\lib\\images\\sell_images\\" + fileName2);
			        sellerService.sellImgInsert(imgVo);
			        
			        imgVo.setSell_image_no(3); imgVo.setSell_no(sell_no);
			        imgVo.setSell_image("\\resources\\lib\\images\\sell_images\\" + fileName3);
			        sellerService.sellImgInsert(imgVo);
			        
			        //---------------------------------------------------------------
					
					out.println("<script>alert(\"입력 되었습니다.\") \n"
							+ "location.href = \"/main.jsp\";</script>");
					
				}
			} else {
				out.println("<script>");
				out.println("alert('정확한 값을 입력해주세요.')");
				out.println("location.href = \"/main.jsp\"");
				out.println("</script>");
			}
		} catch (Exception e) {
			System.out.println(e);
			out.println("<script>");
			out.println("alert('정확한 값을 입력해주세요.')");
			out.println("location.href = \"/main.jsp\"");
			out.println("</script>");
		}
			
		out.flush();
		
		//----------------------------------------------------------------------

		
	}
	
	//판매글 수정 View 이동하기
	@RequestMapping(value = "/seller/sellUpdateView.do", method = RequestMethod.POST)
	public String sellUpdateView(HttpServletRequest req, Model model, HttpSession session) {
		
		System.out.println("sellUpdateView 글 번호 : " + req.getParameter("sell_no"));
		
		ArrayList<SellerVO> sellerList = sellerService.sellOneSelect(Integer.parseInt(req.getParameter("sell_no")));
		SellerVO vo = sellerList.get(0);
		model.addAttribute("sellerList", sellerList);
		model.addAttribute("sellerVO", vo);
		
		ArrayList<KoreasdVO> sidoList = sellerService.sellPlaceSelectSi(); //11 서울특별시
    	req.setAttribute("sidoList",sidoList);
		ArrayList<KoreaSggVO> sggList = sellerService.sellPlaceSelectGu(vo.getSgg_code().substring(0,2)); //11710	송파구
		model.addAttribute("sggList",sggList);
		ArrayList<SellPlaceVO> stationList = sellerService.sellPlaceSelectStation(vo.getSgg_code());
		model.addAttribute("stationList",stationList);
	
		model.addAttribute("address_selected1",vo.getSgg_code().substring(0,2));
		model.addAttribute("address_selected2",vo.getSgg_code());
		model.addAttribute("address_selected3",vo.getSell_place_no());
		
		ArrayList<SellCategoryVO> categoryList_L = sellerService.sellCategorySelectLarge(); //00 패션
    	req.setAttribute("categoryList_L", categoryList_L);
		ArrayList<SellCategoryVO> categoryList_M = sellerService.sellCategorySelect(vo.getSell_category_no().substring(0,2));
		model.addAttribute("categoryList_M", categoryList_M);
		ArrayList<SellCategoryVO> categoryList_S = sellerService.sellCategorySelect(vo.getSell_category_no().substring(0,4));
		model.addAttribute("categoryList_S", categoryList_S);
		
		model.addAttribute("category_selected1",vo.getSell_category_no().substring(0,2));
		model.addAttribute("category_selected2",vo.getSell_category_no().substring(0,4));
		model.addAttribute("category_selected3",vo.getSell_category_no());
		
		HashMap<String, Object> map = getParameterMap(req);
		map.put("item", vo.getSell_place_no());
		System.out.println("map :::::: " + map.get("item"));
		ArrayList<HashMap<String, Object>> sellPlaceItemList = sellerService.sellPlaceItemCnt(map);
		model.addAttribute("sellPlaceItemList", sellPlaceItemList);

		return "seller/sellUpdate";
	}
	
	//판매 장소 조회(시) + 판매 카테고리 조회(대분류)
	@RequestMapping(value = "/seller/sellPlaceSelectSi.do", method = RequestMethod.GET)
	public String sellPlaceSelectSi(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("sellPlaceSelectSi\n판매 장소 조회(시)");
		ArrayList<KoreasdVO> sidoList = sellerService.sellPlaceSelectSi(); //11 서울특별시
    	req.setAttribute("sidoList",sidoList);
    	//------------------------------------------------------------
    	System.out.println("판매 카테고리 조회(대분류)");
    	ArrayList<SellCategoryVO> categoryList_L = sellerService.sellCategorySelectLarge(); //00 패션
    	req.setAttribute("categoryList_L", categoryList_L);
    	//------------------------------------------------------------
    	//req.setAttribute("transient_count", sellerService.sellTsNoSelect()+1); //임시저장글 번호 자동 증가
    	String sell_ts_seller_id = (String)session.getAttribute("loginId");
    	if(sell_ts_seller_id != null) {
    		String member_id = (String) session.getAttribute("loginId");
    		HashMap<String, Object> param = sellerService.memberSelect(member_id);
    		req.setAttribute("member_nic",(String)param.get("MEMBER_NIC"));
    		//req.setAttribute("transient_write_count", sellerService.sellTsMemberSelect(sell_ts_seller_id));    		
    	} else {
    		PrintWriter out = res.getWriter();
    		res.setContentType("text/html; charset=UTF-8");
    		out.println("<script>");
			out.println("alert(\"회원만 이용 가능 합니다.\")");
			out.println("location.href = \"/loginPage.do\"");
			out.println("</script>");
			out.flush();
    	}
		return "seller/sellInsert";
	}
	
	//판매 장소 조회(구)
	@RequestMapping(value = "/seller/sellPlaceSelectGu.do", method = RequestMethod.POST)
	public ModelAndView sellPlaceSelectGu(HttpServletRequest req, Model model) {
		System.out.println("sellPlaceSelectGu");
		ArrayList<KoreaSggVO> sggList = sellerService.sellPlaceSelectGu(req.getParameter("address")); //11710	송파구
		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("sggList",sggList);
		return mv;
	}

	//판매 장소 조회(역)
	@RequestMapping(value = "/seller/sellPlaceSelectStation.do", method = RequestMethod.POST)
	public ModelAndView sellPlaceSelectStation(HttpServletRequest req, Model model) {
		System.out.println("sellPlaceSelectStation");
		System.out.println(req.getParameter("address_two"));
		ArrayList<SellPlaceVO> stationList = sellerService.sellPlaceSelectStation(req.getParameter("address_two"));
		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("stationList",stationList);
		return mv;
	}
	
	//판매 카테고리 조회(중분류)
	@RequestMapping(value = "/seller/sellCategorySelectMedium.do", method = RequestMethod.POST)
	public ModelAndView sellCategorySelectMedium(HttpServletRequest req, Model model) {
		System.out.println("sellCategorySelectMedium\n"
				+ "판매 카테고리 조회(중분류)");
		System.out.println(req.getParameter("category"));
		ArrayList<SellCategoryVO> categoryList_M = sellerService.sellCategorySelect(req.getParameter("category"));
		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("categoryList_M", categoryList_M);
		return mv;
	}
	
	//판매 카테고리 조회(소분류)
	@RequestMapping(value = "/seller/sellCategorySelectSmall.do", method = RequestMethod.POST)
	public ModelAndView sellCategorySelectSmall(HttpServletRequest req, Model model) {
		System.out.println("sellCategorySelectSmall\n"
				+ "판매 카테고리 조회(소분류)");
		System.out.println(req.getParameter("category"));
		ArrayList<SellCategoryVO> categoryList_S = sellerService.sellCategorySelect(req.getParameter("category"));
		ModelAndView mv = new ModelAndView("jsonView");
		model.addAttribute("categoryList_S", categoryList_S);
		return mv;
	}
	
	
	//판매글 수정하기
		@RequestMapping(value = "/seller/sellUpdate.do", method = RequestMethod.POST)
		public void sellUpdate(SellerVO vo, HttpServletRequest req, MultipartHttpServletRequest mReq
				, HttpServletResponse res) throws IOException {

			System.out.println("sellUpdate 글 번호 : " + vo.getSell_no());
			System.out.println("=========================== : " + vo.getSell_place_item_no());
			
			PrintWriter out = res.getWriter();
			res.setContentType("text/html; charset=UTF-8");

			String fileP1 = req.getRealPath("resources/lib/images/sell_images/");
			
			System.out.println(fileP1);
			//System.out.println(fileP1.indexOf(5));
			//String begin = "C:\\eGovFrameDev-3.7.0-64bit\\workspace\\finalProject\\src\\main\\webapp";
			
			
			String filePath = fileP1;
		    
			SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
		    Date date = new Date();
		    
		    if(!mReq.getFile("file_upload1").getOriginalFilename().equals("")) {
				
				MultipartFile file1 = mReq.getFile("file_upload1");

		        String fileName1 = vo.getSell_no() + "_main01_" + simDf.format(date) + "_" + file1.getOriginalFilename();
		
		        ArrayList<SellerVO> sellerList = sellerService.sellOneSelect(vo.getSell_no());
	    		File deleteFile = new File(sellerList.get(0).getSell_image());
	 	        System.out.println(sellerList.get(0).getSell_image());
	    		
	 	       if(deleteFile.exists()) {  	   
	 	            // 파일을 삭제합니다.
	 	            deleteFile.delete(); 
	 	            
	 	            System.out.println("파일을 삭제하였습니다.");
	 	            
	 	        } else {
	 	            System.out.println("파일이 존재하지 않습니다.");
	 	        }
	 	        
	 	        System.out.println(filePath + fileName1);
	    		
		        try (
		        		FileOutputStream fos1 = new FileOutputStream(filePath + fileName1);
		                InputStream is1 = file1.getInputStream();) {
		            
			            int readCount = 0;
			            byte[] buffer = new byte[1024];
			            
			            while ((readCount = is1.read(buffer)) != -1) {
			                fos1.write(buffer, 0, readCount);
			            }
		         
		        } catch (Exception e) {
		            throw new RuntimeException("file Save Error");
		        }
		        
		        SellImgVO imgVo = new SellImgVO();
		        
		        imgVo.setSell_image_no(1); imgVo.setSell_no(vo.getSell_no());
		        imgVo.setSell_image("\\resources\\lib\\images\\sell_images\\" + fileName1);
		        sellerService.sellImgUpdate(imgVo);
			}
			
			if(!mReq.getFile("file_upload2").getOriginalFilename().equals("")) {
				
				MultipartFile file2 = mReq.getFile("file_upload2");

		        String fileName2 = vo.getSell_no() + "_main02_" + simDf.format(date) + "_" + file2.getOriginalFilename();
		
		        ArrayList<SellerVO> sellerList = sellerService.sellOneSelect(vo.getSell_no());
	    		File deleteFile = new File(sellerList.get(1).getSell_image());
	 	        System.out.println(sellerList.get(1).getSell_image());
	    		
	 	       if(deleteFile.exists()) {
	 	            deleteFile.delete(); 
	 	            System.out.println("파일을 삭제하였습니다.");
	 	            
	 	        } else {
	 	            System.out.println("파일이 존재하지 않습니다.");
	 	        }
	 	        
	 	        System.out.println(filePath + fileName2);
	    		
		        try (
		        		FileOutputStream fos1 = new FileOutputStream(filePath + fileName2);
		                InputStream is1 = file2.getInputStream();) {
		            
			            int readCount = 0;
			            byte[] buffer = new byte[1024];
			            
			            while ((readCount = is1.read(buffer)) != -1) {
			                fos1.write(buffer, 0, readCount);
			            }
		         
		        } catch (Exception e) {
		            throw new RuntimeException("file Save Error");
		        }
		        
		        SellImgVO imgVo = new SellImgVO();
		        
		        imgVo.setSell_image_no(2); imgVo.setSell_no(vo.getSell_no());
		        imgVo.setSell_image("\\resources\\lib\\images\\sell_images\\" + fileName2);
		        sellerService.sellImgUpdate(imgVo);
			}

			if(!mReq.getFile("file_upload3").getOriginalFilename().equals("")) {
				
				MultipartFile file3 = mReq.getFile("file_upload3");
			
			    String fileName3 = vo.getSell_no() + "_main03_" + simDf.format(date) + "_" + file3.getOriginalFilename();
			
			    ArrayList<SellerVO> sellerList = sellerService.sellOneSelect(vo.getSell_no());
				File deleteFile = new File(sellerList.get(2).getSell_image());
			     System.out.println(sellerList.get(2).getSell_image());
				
			    if(deleteFile.exists()) {  	   
			         // 파일을 삭제합니다.
			         deleteFile.delete(); 
			         
			         System.out.println("파일을 삭제하였습니다.");
			         
			     } else {
			         System.out.println("파일이 존재하지 않습니다.");
			     }
			     
			     System.out.println(filePath + fileName3);
				
			    try (
			    		FileOutputStream fos1 = new FileOutputStream(filePath + fileName3);
			            InputStream is1 = file3.getInputStream();) {
			        
			            int readCount = 0;
			            byte[] buffer = new byte[1024];
			            
			            while ((readCount = is1.read(buffer)) != -1) {
			                fos1.write(buffer, 0, readCount);
			            }
			     
			    } catch (Exception e) {
			        throw new RuntimeException("file Save Error");
			    }
			    
			    SellImgVO imgVo = new SellImgVO();
			    
			    imgVo.setSell_image_no(3); imgVo.setSell_no(vo.getSell_no());
			    imgVo.setSell_image("\\resources\\lib\\images\\sell_images\\" + fileName3);
			    sellerService.sellImgUpdate(imgVo);
			}
			
			/*int sell_place_item_no = Integer.parseInt(req.getParameter("sell_place_item_no"));
			if(sell_place_item_no != 0) {
				vo.setSell_place_item_no(sell_place_item_no);			
			}*/
			
			vo.setSell_category_no(req.getParameter("sell_category_no"));
		    int sellUpdateCheck = sellerService.sellUpdate(vo);
		    
		    if(sellUpdateCheck == 1) {
		    	out.println("<script>");
				out.println("alert(\"수정 되었습니다.\")");
				out.println("location.href = \"/buyer/buyerContentView.do?sell_no=" + vo.getSell_no() +"\"");
				//out.println("location.href = \"/seller/sellerView.do\"");
				out.println("</script>");
		    } else {
		    	out.println("<script>");
				out.println("alert(\"실패 했습니다.\")");
				out.println("location.href = \"/main.jsp\"");
				out.println("</script>");
		    	
		    }
	     
		    out.flush();
		}
	
	//판매글 삭제
	@RequestMapping(value = "/seller/sellDelete.do", method = RequestMethod.GET)
	public void sellDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html; charset=UTF-8");
		int sell_no = Integer.parseInt(req.getParameter("sell_no"));
		
		/*ArrayList<SellerVO> sellerList = sellerService.sellOneSelect(sell_no);

		String fileP1 = req.getRealPath("/");
		String begin = fileP1.substring(0, fileP1.indexOf("\\.metadata"));
		
		for(int i=0; i<sellerList.size(); i++) {
			File deleteFile = new File(begin + "\\finalProject\\src\\main\\webapp" + sellerList.get(i).getSell_image());
			System.out.println(begin + "\\finalProject\\src\\main\\webapp" + sellerList.get(i).getSell_image());					
			
			if(deleteFile.exists()) {
 	            deleteFile.delete(); 
 	            System.out.println("파일을 삭제하였습니다.");
 	            
 	        } else {
 	            System.out.println("파일이 존재하지 않습니다.");
 	        }
		}*/
		
		//int sellImgDeleteCheck = sellerService.sellImgDelete(sell_no);
		int sellDeleteCheck = sellerService.sellDelete(sell_no);
		
		if(sellDeleteCheck == 1) {
			out.println("<script>");
			out.println("alert(\"삭제 되었습니다.\")");
			out.println("location.href = \"/seller/sellerView.do\"");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert(\"실패 했습니다.\")");
			out.println("location.href = \"/main.jsp\"");
			out.println("</script>");
		}
		out.flush();
	}
	
	
	//판매 보관함 검색
	@RequestMapping(value = "/seller/sellPlaceItem.do", method = RequestMethod.POST)
	public ModelAndView sellPlaceItem(HttpServletRequest req, Model model) {
		System.out.println("sellPlaceItem 보관함");
		HashMap<String, Object> map = getParameterMap(req);
		System.out.println(map);
	
		ArrayList<HashMap<String, Object>> result = sellerService.sellPlaceItemCnt(map);
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		model.addAttribute("result", result);
		return mv;
	}
	
	
	//메인 페이지 최근 올라온 판매목록 
		@RequestMapping(value = "/seller/mainSellListView.do", method = RequestMethod.POST)
		public ModelAndView mainSellListView(HttpServletRequest req, Model model) {
			System.out.println("메인 페이지 최근 올라온 판매목록 ");
			HashMap<String, Object> map = getParameterMap(req);
			System.out.println(map);
		
			ArrayList<HashMap<String, Object>> list = sellerService.mainSellListView();
			
			ModelAndView mv = new ModelAndView("jsonView");
			
			model.addAttribute("list", list);
			return mv;
		}
		
		//메인 페이지 최근 올라온 리뷰목록 
		@RequestMapping(value = "/seller/mainSellReviewListView.do", method = RequestMethod.POST)
		public ModelAndView mainSellReviewListView(HttpServletRequest req, Model model) {
			System.out.println("메인 페이지 최근 올라온 판매목록 ");
			HashMap<String, Object> map = getParameterMap(req);
			System.out.println(map);
		
			ArrayList<HashMap<String, Object>> list = sellerService.mainSellReviewListView();
			
			ModelAndView mv = new ModelAndView("jsonView");
			
			model.addAttribute("list", list);
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
