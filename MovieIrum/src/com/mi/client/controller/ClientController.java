package com.mi.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.tomcat.util.descriptor.web.LoginConfig;


import client.clientDAO.ClientDAO;
import client.clientVO.ClientVO;
import client.serviceDAO.ClientServiceDAO;
import movie.bookingDAO.MovieBookingDAO;

/**
 * Servlet implementation class ClientController
 */
@WebServlet("*.do")
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length()+1,uri.lastIndexOf("."));
		String str = null;
		System.out.println(uri);
		switch(uri) {

		case "JoinForm"://�쉶�썝媛��엯
			ClientDAO dao1 = null;
			String id = request.getParameter("new_id");
			String pw = request.getParameter("new_pw");
			String pw2 = request.getParameter("new_pw2");
			String name = request.getParameter("new_name");
			String birth = request.getParameter("new_birth");
			String email = request.getParameter("new_email");
			String tel = request.getParameter("new_tel");

			try {
				dao1 = new ClientDAO();
				dao1.memberJoin(id, pw, name, birth, email, tel);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			str = "ClientJoinActionController";

			break;

		case "LoginForm"://濡쒓렇�씤
			ClientDAO dao11 = null;
			String id1= request.getParameter("id");
			String pw1 = request.getParameter("pw");

			try {
				dao11 = new ClientDAO();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// DB�뿉�꽌 �븘�씠�뵒, 鍮꾨�踰덊샇 �솗�씤
			int x = 1;
			String pw3 = dao11.login(id1); //留욌뒗媛�

			// URL 諛� 濡쒓렇�씤愿��젴 �쟾�떖 硫붿떆吏�
			String msg = "";

			if(pw3.equals(pw1))    // 濡쒓렇�씤 �꽦怨�
			{ 
				// �꽭�뀡�뿉 �쁽�옱 �븘�씠�뵒 �꽭�똿
				HttpSession session = request.getSession();
				session.setAttribute("sessionID", id1);
				str = "Client/LoginAction.jsp";
			}
			else //  ��由닿꼍�슦
			{
				str = "Client/LoginForm.jsp?msg=0";
			}

			// sendRedirect(String URL) : �빐�떦 URL濡� �씠�룞
			// URL�뮘�뿉 get諛⑹떇 泥섎읆 �뜲�씠�꽣瑜� �쟾�떖媛��뒫
			// response.sendRedirect(msg);
			// str = "main.jsp";
			break;

		case "Client/Find_idForm"://�븘�씠�뵒李얘린
			ClientDAO dao12 = null;
			String name12= request.getParameter("name");
			String email12 = request.getParameter("email");
			try {
				dao12 = new ClientDAO();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String id12 = dao12.idSearch(name12, email12);

			request.setAttribute("findid", id12);
			if(id12!=null) {

				str = "Find_idOKForm.jsp";
			}
			else
			{
				str = "Find_idForm.jsp?msg=0";
			}
			break;

		case "Client/Find_pwForm"://鍮꾨�踰덊샇李얘린
			ClientDAO dao13 = null;
			String id13=request.getParameter("id");
			String name13= request.getParameter("name");
			String email13 = request.getParameter("email");
			try {
				dao13 = new ClientDAO();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pw13 = dao13.pwSearch(id13, name13, email13);
			request.setAttribute("findpw", pw13);
			if(pw13!=null) {
				System.out.println(pw13);
				str = "Find_pwOKForm.jsp";
			}
			else
			{
				str = "Find_pwForm.jsp?msg=0";
			}
			break;

		case "Mypage":

			HttpSession session = request.getSession();
			if(session.getAttribute("sessionID")==null) {
				str = "Client/LoginForm.jsp";
				break;
			}else {
				try {
					ClientDAO dao14 = new ClientDAO();
					ClientVO vo1=dao14.getclient((String)session.getAttribute("sessionID"));
					request.setAttribute("vo1", vo1);


				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				str = "Client/MyUpdateForm.jsp";
			}
			break;

		case "MyUpdateForm":
			System.out.println("�꽆�뼱�샂");
			String name15 = request.getParameter("up_name");
			String email15 = request.getParameter("up_email");
			String tel15 = request.getParameter("up_tel");

			System.out.println("zz" + name15);
			try {
				HttpSession session1 = request.getSession();
				ClientDAO dao15 = new ClientDAO();
				int update = dao15.Update(name15,email15,tel15,(String)session1.getAttribute("sessionID"));
				if(update == -1) {


				}else{

				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			str = "ClientUpdateActionController";
			break;

		case "MyUpdatePwForm":

			try {
				HttpSession session2 = request.getSession();
				ClientDAO dao16 = new ClientDAO();

				String old_pw16 = dao16.pwSel((String)session2.getAttribute("sessionID"));
				String old2_pw16 = request.getParameter("pw");
				String new_pw16 = request.getParameter("new_pw");
				String id16 = (String)session2.getAttribute("sessionID");

				if(old_pw16.equals(old2_pw16)){
					int result = dao16.pwChange(old_pw16, new_pw16, id16); //留욌뒗媛�
					request.setAttribute("result", result);
					str = "ClientPwUpdateActionController";
				}
				else
				{
					str = "Client/MyUpdatePwForm.jsp?msg=0";
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			break;

		case "MyDeleteForm":
			try {
				HttpSession session3 = request.getSession();
				ClientDAO dao18 = new ClientDAO();
				MovieBookingDAO mbDao = new MovieBookingDAO();
				ClientServiceDAO csDao = new ClientServiceDAO();
				String pw111 = dao18.pwSel((String)session3.getAttribute("sessionID"));
				String pw222 = request.getParameter("pw");
				String id17 = (String)session3.getAttribute("sessionID");
				if(pw111.equals(pw222)) {
					mbDao.movieBookingDelete(id17);
					csDao.deleteService(id17);
					dao18.ClientDelete(id17);
					session3.invalidate();
					str = "ClientDeleteActionController";
					
				}else {
					str = "Client/MyUpdatePwForm.jsp?msg=0";
				}
				
			
			
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(str);
		rd.forward(request, response);
	}
}
