package com.mi.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieInsertController
 */
@WebServlet("/MovieInsertController")
public class MovieInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInsertController() {
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
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String htmlCode = "<script>\r\n" +
                    "alert(\"��� �Ǿ����ϴ�!\")\r\n" +
                    "location.href=\"/MovieIrum/index.jsp\";\r\n" + 
                    "</script>";
        out.println(htmlCode);
        
        out.flush(); //���ۿ� ����Ǿ� �ִ� ������ Ŭ���̾�Ʈ�� �����ϰ� ���۸� ����.
        out.close(); //��ü �ݳ�.
	}

}