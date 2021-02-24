package com.mi.service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.serviceDAO.ClientServiceDAO;
import client.serviceVO.ClientCategoryVO;
import client.serviceVO.ClientServiceVO;

/**
 * Servlet implementation class ClientServiceController
 */
@WebServlet("*.service")
public class ClientServiceController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientServiceController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();

		uri = uri.substring(conPath.length() + 1, uri.lastIndexOf("."));

		// String t = (String)request.getParameter("svcTitle");
		// System.out.println("svcTitle = "+t);

		String str = null;
		System.out.println(uri);

		ClientServiceDAO csdao = null;
		switch (uri) {

		// 세션에 id가 담겨 있으므로 고객센터 글 목록을 가져와야 한다
		case "serviceCenter":
			System.out.println("serviceCenter으로 넘어옴");

			try {

				csdao = new ClientServiceDAO();
				ArrayList<ClientServiceVO> vo = new ArrayList<ClientServiceVO>();
				vo = csdao.getServiceList();
				System.out.println(vo.size());
				request.setAttribute("list", vo);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			str = "/serviceCenter/clientListForm.jsp";
			break;

		// 고객센터 글쓰기 버튼 클릭 시 가져올 카테고리 조회
		case "searchCategory_write":
			System.out.println("searchCategory_write로 넘어옴");

			try {
				csdao = new ClientServiceDAO();
				ArrayList<ClientCategoryVO> cgArray = new ArrayList<ClientCategoryVO>();
				cgArray = csdao.searchCategory();
				request.setAttribute("cgArray", cgArray);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("client 글 작성 페이지 이동 요청");
			str = "/serviceCenter/clientWriteForm.jsp";
			break;

		case "getServiceList":
			System.out.println("getServiceList.service로 넘어옴");
			// ClientServiceDAO csdao1 = null;
			ArrayList<ClientServiceVO> clist = null;
			try {
				csdao = new ClientServiceDAO();

				clist = csdao.getServiceList();
				request.setAttribute("clist", clist);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println(clist);
			str = "clientListForm.jsp";
			break;

		case "clientWrittenBoard":
			System.out.println("확인");

			int param = Integer.parseInt(request.getParameter("num"));
			System.out.println(param);

			try {

				csdao = new ClientServiceDAO();

				HashMap<String, Object> map = new HashMap<String, Object>();

				map = csdao.getServiceForm(param);
				System.out.println(map.size());

				HttpSession session = request.getSession();
				session.getAttribute("sessionID");
				map.get("client_id");
				if (session.getAttribute("sessionID").equals(map.get("client_id"))) {
					session.setAttribute("map", map);
					str = "serviceCenter/clientWrittenBoard.jsp";

				} else {
					session.setAttribute("claimNo", param);
					str = "serviceCenter/clientBoardPassword.jsp";
				}
				request.setAttribute("map", map);/////////////////////////////////////////
			} catch (Exception e) {
				// TODO: handle exception
			}

			break;

		// 고객센터 글 클릭 시 password 입력하는 폼으로 넘어감
		// 애초에 로그인 아이디와 작성 아이디가 다르면 password입력 폼으로 넘어가고, 아이디가 다르면 패스워드 입력해도 못 봄
		case "searchPassword":
			System.out.println("searchPassword 넘어옴");
			Map<String, Object> param3 = getParameterMap2(request);
			System.out.println(param3);

			try {
				csdao = new ClientServiceDAO();
				HashMap<String, String> map = new HashMap<String, String>();
				Map<String, Object> map2 = csdao.getClaimpw(param3);
				System.out.println(map2);

				if ((map2.get("claim_pw").equals(param3.get("pw")))) {
					System.out.println("비번 맞음");
					// 고객센터 리스트 창으로 넘어가면서 얼럿창 뜨게하기
					str = "/serviceCenter/consoleAlert.jsp";
					HashMap<String, Object> map9 = new HashMap<String, Object>();
					map9 = csdao.getServiceForm((int) param3.get("num"));
					request.setAttribute("map", map9);
				} else {
					System.out.println("비번 틀림");
					str = "serviceCenter.service";
				}

				request.setAttribute("map", map);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;

		case "insertClaim":
			System.out.println("insertClaim으로 넘어옴");
			Map<String, Object> map1 = new HashMap<>();
			try {
				csdao = new ClientServiceDAO();
				map1 = getParameterMap2(request);

				HttpSession session = request.getSession();
				map1.put("id", session.getAttribute("sessionID"));
				System.out.println(map1);
				System.out.println(map1.get("id"));
				System.out.println(map1.get("category"));
				System.out.println(map1.get("title"));
				System.out.println(map1.get("content"));

				csdao.insertClaim(map1);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			str = "/serviceCenter/success.jsp";
			break;

		// 글 클릭해서 삭제 버튼 클릭 시 삭제 된다.
		case "deleteInfo":
			System.out.println("deleteInfo 넘어옴");
			int claim_num2 = Integer.parseInt(request.getParameter("claim_num"));
			System.out.println(claim_num2);

			Map<String, String> map7 = new HashMap<String, String>();
			int delete = 0;
			int update = 0;

			try {
				delete = new ClientServiceDAO().getRemoveClaim(claim_num2);
				if (delete > 0) {
					System.out.println("delete 성공");
					update = new ClientServiceDAO().getUpdateClaimNum(claim_num2);
					if (update > 0) {
						System.out.println("update 성공");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			str = "serviceCenter.service";
			break;

		case "updateGetInfo":
			System.out.println("updateGetInfo로 넘어옴");
			int claim_num = Integer.parseInt(request.getParameter("claim_num"));
			System.out.println(claim_num);

			try {
				csdao = new ClientServiceDAO();

				ArrayList<ClientCategoryVO> cgArray = new ArrayList<ClientCategoryVO>();
				cgArray = csdao.searchCategory();
				request.setAttribute("cgArray", cgArray);

				Map<String, Object> map2 = new HashMap<>();
				map2 = getParameterMap2(request);

				HashMap<String, Object> map = new HashMap<String, Object>();

				map = csdao.getServiceForm(claim_num);
				System.out.println(map.size());

				HttpSession session = request.getSession();
//				session.getAttribute("sessionID");
				map.put("client_id", (String) session.getAttribute("sessionID"));
				map.put("claim_num", claim_num);
				// request.setAttribute("map", map);
				System.out.println(map.get("client_id"));

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			str = "/serviceCenter/clientUpdateForm.jsp";
			break;

		case "updateSetInfo":
			System.out.println("updateSetInfo로 넘어옴");

			// int claim_num2 = Integer.parseInt(request.getParameter("claim_num"));
			String category = request.getParameter("category");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String pw = request.getParameter("pw");
			System.out.println(category);
			System.out.println(title);
			System.out.println(content);
			System.out.println(pw);

			Map<String, Object> map3 = new HashMap<>();
			Map<String, Object> map4 = new HashMap<>();
			try {
				csdao = new ClientServiceDAO();
				map3 = getParameterMap2(request);
				csdao.updateClaim(map3);
				map4 = csdao.getServiceForm(Integer.parseInt((String) map3.get("num")));
				request.setAttribute("map", map4);
				HttpSession session = request.getSession();
				System.out.println(map4);
				// System.out.println(map3.get("id"));

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			str = "/serviceCenter/clientWrittenBoard.jsp";
			break;

		}

		RequestDispatcher rd = request.getRequestDispatcher(str);
		rd.forward(request, response);

	}

	protected Map<String, Object> getParameterMap2(HttpServletRequest req) {
		Map<String, Object> map = new HashMap();
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
