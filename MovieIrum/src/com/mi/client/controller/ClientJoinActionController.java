package com.mi.client.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientJoinActionController
 */
@WebServlet("/ClientJoinActionController")
public class ClientJoinActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientJoinActionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	 	String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		// 占쌘뱄옙 클占쏙옙占쏙옙占쏙옙占쏙옙 HTML占싱놂옙 JS占쏙옙 占쏙옙占쏙옙 占쏙옙占� - PrintWriter out 占쏙옙체占쏙옙 占쏙옙占�.
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
		String htmlCode = null;
		
         htmlCode = "<script>\r\n" +
                     "alert(\"회원가입이 완료되었습니다!\")\r\n" +
                     "location.href=\"/MovieIrum/Client/LoginForm.jsp\";\r\n" + 
                     "</script>";
		  
         
         out.println(htmlCode);
         
         out.flush(); //占쏙옙占쌜울옙 占쏙옙占쏙옙퓸占� 占쌍댐옙 占쏙옙占쏙옙占쏙옙 클占쏙옙占싱억옙트占쏙옙 占쏙옙占쏙옙占싹곤옙 占쏙옙占쌜몌옙 占쏙옙占쏙옙.
         out.close(); //占쏙옙체 占쌥놂옙.

}

}
