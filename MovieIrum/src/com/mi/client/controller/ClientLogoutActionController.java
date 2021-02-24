package com.mi.client.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientLogoutActionController
 */
@WebServlet("/ClientLogoutActionController")
public class ClientLogoutActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientLogoutActionController() {
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
		// �ڹ� Ŭ�������� HTML�̳� JS�� ���� ��� - PrintWriter out ��ü�� ���.
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
		String htmlCode = null;
		
         htmlCode = "<script>\r\n" +
                     "alert(\"�����Ǿ����ϴ�.\")\r\n" +
                     "location.href=\"/MovieIrum/Mypage.do\";\r\n" + 
                     "</script>";
		  
         
         out.println(htmlCode);
         
         out.flush(); //���ۿ� ����Ǿ� �ִ� ������ Ŭ���̾�Ʈ�� �����ϰ� ���۸� ����.
         out.close(); //��ü �ݳ�.

}

}
