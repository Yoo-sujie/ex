package com.mi.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieBookingController
 */
@WebServlet("/MovieBookingFailController")
public class MovieBookingFailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieBookingFailController() {
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
                    "alert(\"이미 예매된 좌석입니다. 다시 예매해주세요!\")\r\n" +
                    "location.href=\"/MovieIrum/index.jsp\";\r\n" + 
                    "</script>";
        out.println(htmlCode);
        
        out.flush(); //버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다.
        out.close(); //객체 반납.
     

	}

}
