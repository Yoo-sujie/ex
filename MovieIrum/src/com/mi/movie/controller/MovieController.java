package com.mi.movie.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.websocket.Session;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import client.clientDAO.ClientDAO;
import movie.bookingDAO.MovieBookingDAO;
import movie.bookingVO.MovieBookingVO;
import movie.movieDAO.MovieDAO;
import movie.movieVO.MovieVO;
import movie.timeDAO.MovieTimeDAO;
import movie.timeVO.MovieTimeVO;

/**
 * Servlet implementation class MovieController
 */
@WebServlet("*.client")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length()+1,uri.lastIndexOf("."));
		System.out.println(uri);
		String str = null;
		HttpSession session = request.getSession();
		switch(uri) {
		
		case "movieSelectView":
			MovieDAO dao = null;
			try {
				dao = new MovieDAO();
				session = request.getSession();
				System.out.println(session.getAttribute("sessionID"));
				ArrayList<MovieVO> list = dao.selectMovie();
				String movieName = request.getParameter("movieName");
				String movieAge = request.getParameter("movieAge");
				MovieVO vo = dao.oneSelectMovie(movieName);
				MovieTimeDAO timeDao = new MovieTimeDAO();
				
				Date d = new Date();
				Date d2 = dao.selectShowingDate(movieName);
				int intd = d.getDate();
				int intd2 = d2.getDate();
				ArrayList dayList = new ArrayList<>();
				if(d2.getYear()>=d.getYear()) {
					if(d2.getMonth()>d.getMonth()) {
						Date d3 = new Date(d.getYear(), d.getMonth() + 1, 0);
						for(int i=intd; i<=d3.getDate(); i++) {
							dayList.add(i);
						}
					} else {
						for(int i=intd; i<=intd2; i++) {
							dayList.add(i);
						}
					}
				}
				
				ArrayList<String> time1 = timeDao.selectMovieRoom(movieName, 1);
				ArrayList<String> time2 = timeDao.selectMovieRoom(movieName, 2);
				request.setAttribute("time1", time1);
				request.setAttribute("time2", time2);
				request.setAttribute("mvVo", vo);
				request.setAttribute("list", list);
				request.setAttribute("month", d.getMonth()+1);
				request.setAttribute("movie-year", d.getYear()+1900);
				request.setAttribute("dayList", dayList);
				session.setAttribute("movieName2", movieName);
				session.setAttribute("movieAge", movieAge);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "movie/movieSelectView.jsp";
			break;
			
		case "movieSelectView2":
			MovieDAO dao9 = null;
			try {
				dao9 = new MovieDAO();
				session = request.getSession();
				System.out.println(session.getAttribute("sessionID"));
				ArrayList<MovieVO> list = dao9.selectMovie();
				String movieName = request.getParameter("movieName");
				String movieAge = request.getParameter("movieAge");
				MovieVO vo = dao9.oneSelectMovie(movieName);
				MovieTimeDAO timeDao = new MovieTimeDAO();
				
				Date d = new Date();
				Date d2 = dao9.selectShowingDate(movieName);
				int intd = d.getDate();
				int intd2 = d2.getDate();
				ArrayList dayList = new ArrayList<>();
				if(d2.getYear()>=d.getYear()) {
					if(d2.getMonth()>d.getMonth()) {
						Date d3 = new Date(d.getYear(), d.getMonth() + 1, 0);
						for(int i=intd; i<=d3.getDate(); i++) {
							dayList.add(i);
						}
					} else {
						for(int i=intd; i<=intd2; i++) {
							dayList.add(i);
						}
					}
				}
				
				ArrayList<String> time1 = timeDao.selectMovieRoom(movieName, 1);
				ArrayList<String> time2 = timeDao.selectMovieRoom(movieName, 2);
				
				request.setAttribute("time1", time1);
				request.setAttribute("time2", time2);
				request.setAttribute("mvVo", vo);
				request.setAttribute("list", list);
				request.setAttribute("month", d.getMonth()+1);
				request.setAttribute("movie-year", d.getYear()+1900);
				request.setAttribute("dayList", dayList);
				session.setAttribute("movieName2", movieName);
				session.setAttribute("movieAge", movieAge);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "movie/movieSelectView2.jsp";
			break;
			
		case "movieBooking":
			MovieBookingDAO mbDao;
			MovieBookingVO mbVo = null;
			MovieDAO mdao = null;
			ClientDAO cdao = null;
			try {
				session = request.getSession();
				mbDao = new MovieBookingDAO();
				mbVo = new MovieBookingVO();
				if(request.getParameter("hidden") != null) {
					int point = Integer.parseInt(request.getParameter("point"));
					if(request.getParameter("point")==null) point=0;
					Date d = new Date();
					int month = d.getMonth()+1;
					String sMonth = "";
					if(month < 10) {
						sMonth = "0" + month;
					}
					String sDate2 = d.getYear()+1900 + sMonth + (String)session.getAttribute("day");
					mbVo = new MovieBookingVO(
							(String)session.getAttribute("sessionID"),
							request.getParameter("movieName"),
							new Date(),
							request.getParameter("movieTime"),
							request.getParameter("seat"),
							Integer.parseInt(request.getParameter("price"))
					);
					System.out.println(request.getParameter("movieName"));
					cdao = new ClientDAO();
					int point3 = Integer.parseInt(request.getParameter("point2"))-point;
					System.out.println(point);
					ClientDAO cdao2 = new ClientDAO();
					System.out.println(mbVo.getClientId());
					//---------------------------------------------------
					ArrayList<String> seat = mbDao.movieBookingSeatSelect(request.getParameter("movieTime"), sDate2);
					ArrayList<String> seatArr = new ArrayList<>();
					if(seat.size()!=0) {
						for(int i=0; i<seat.size(); i++) {
							if(seat.get(i).length()>=3) {
								String[] s2 = seat.get(i).split(", ");
								for(int j=0; j<s2.length; j++) seatArr.add(s2[j]);
							} else {
								seatArr.add(seat.get(i));
							}
						}
						if(seatArr != null) {
							session.setAttribute("seatArr", seatArr);
						}
					} else {
						session.setAttribute("seatArr", seat);
					}
					String seat2 = request.getParameter("seat");
					seatArr = (ArrayList<String>) session.getAttribute("seatArr");
					String[] s2 = {};
					if(seat2.length()>=3) {
						s2 = seat2.split(", ");
					}
					String result = "�꽦怨�";
					for(String s : seatArr) {
						if(s2 != null) {
							for(String ss : s2) {
								if(s.equals(ss)) {
									result = "�떎�뙣";
								}
							}
						}
						if(s.equals(seat2)) {
							result = "�떎�뙣";
						}
					}
					System.out.println("seat2" + seat2);
					System.out.println(result);
					if(result.equals("�꽦怨�")) {
						int price2 = (int) ((Integer.parseInt(request.getParameter("price"))-point)*0.1);
						session.setAttribute("price", Integer.parseInt(request.getParameter("price"))-point);
						session.setAttribute("price2", price2);
						session.setAttribute("mbVo", mbVo);
						session.setAttribute("date", request.getParameter("date"));
						session.setAttribute("point", point);
						str = "movie/kakao.jsp";
					} else {
						str = "MovieBookingFailController";
					}
					//---------------------------------------------------
					//cdao2.updatePoint(point, mbVo.getClientId());
				} else {
					System.out.println("�뿬湲�2");
					int sa = Integer.parseInt(request.getParameter("sa"));
					int sa2 = Integer.parseInt(request.getParameter("sa2"));
					int sa3 = Integer.parseInt(request.getParameter("sa3"));
					int total = sa + sa2 + sa3;
					int price = sa * 8000 + sa2 * 7000;
					String ss = "";
					for(int i=1; i<=total; i++) {
						if(total == i) {
							ss+=request.getParameter("seat"+i);
						} else {
							ss+=request.getParameter("seat"+i)+", ";
						}
					}
					mbVo = new MovieBookingVO(
							(String)session.getAttribute("sessionID"),
							(String)session.getAttribute("movieName2"),
							new Date(),
							(String)session.getAttribute("time"),
							ss, price
					);
					Date d = new Date();
					int month = d.getMonth()+1;
					String sMonth = "";
					if(month < 10) {
						sMonth = "0" + month;
					}
					String sDate = d.getYear()+1900 + sMonth + (String)session.getAttribute("day");
					MovieVO mvo = new MovieVO();
					cdao = new ClientDAO();
					mdao = new MovieDAO();
					mvo = mdao.oneSelectMovie((String)session.getAttribute("movieName2"));
					request.setAttribute("poster", mvo.getMoviePoster());
					request.setAttribute("mbVo", mbVo);
					session.setAttribute("mbVo", mbVo);
					session.setAttribute("point", cdao.selectPoint((String)session.getAttribute("sessionID")));
					request.setAttribute("date", sDate);
					str = "movie/movieInfo.jsp";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
			
		case "movieBookingSelect":
			MovieBookingDAO mbDao2 = null;
			try {
				mbDao2 = new MovieBookingDAO();
				session = request.getSession();
				MovieBookingVO mbVo2 =
				mbDao2.movieBookingSelect((String)session.getAttribute("sessionID"), 
						(String)session.getAttribute("movieName2"));
				session.setAttribute("mbVo", mbVo2);
				System.out.println(mbVo2.getMovieName());
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			str = "movie/movieInfo.jsp";
			break;
		
		case "movieContentView":
			MovieDAO dao5 = null;
			try {
				dao5 = new MovieDAO();
				String movieName = request.getParameter("movieName");
				System.out.println("movieName : " + movieName);
				MovieVO vo = dao5.oneSelectMovie(movieName);
				request.setAttribute("vo", vo);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			str = "movie/movieContentView.jsp";
			break;
		
		case "movieInfo":
			
			MovieDAO dao6 = null;
			session = request.getSession();
			String movieName = (String) session.getAttribute("movieName2");
			String movieAge = (String) session.getAttribute("movieAge");
			session.setAttribute("time", request.getParameter("time1"));
			session.setAttribute("day", request.getParameter("day"));
			
			if(request.getParameter("time1") != null && request.getParameter("day") != null ) {
				MovieBookingDAO mbdao;
				try {
					mbdao = new MovieBookingDAO();
					Date d = new Date();
					int month = d.getMonth()+1;
					String sMonth = "";
					if(month < 10) {
						sMonth = "0" + month;
					}
					String sDate = d.getYear()+1900 + sMonth + request.getParameter("day");
					ArrayList<String> seat = mbdao.movieBookingSeatSelect(request.getParameter("time1"), sDate);
					if(seat.size()!=0) {
						ArrayList<String> seatArr = new ArrayList<>();
						for(int i=0; i<seat.size(); i++) {
							if(seat.get(i).length()>=3) {
								String[] s2 = seat.get(i).split(", ");
								for(int j=0; j<s2.length; j++) seatArr.add(s2[j]);
							} else {
								seatArr.add(seat.get(i));
							}
						}
						if(seatArr != null) {
							session.setAttribute("seatArr", seatArr);
						}
					} else {
						session.setAttribute("seatArr", seat);
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}

			str = "movie/movieSeatView.jsp";
			break;
			
		case "cancel":
			//System.out.println(session2.getAttribute("movieName2"));
			str = "index.jsp";
			break;
		
		case "update":
			try {
				cdao = new ClientDAO();
				int point = (int)session.getAttribute("point");
				int price2 = (int)session.getAttribute("price2");
				String date = (String)session.getAttribute("date");
				MovieBookingVO mbVo3 = (MovieBookingVO)session.getAttribute("mbVo");
				MovieBookingDAO mbDao3 = new MovieBookingDAO();
				System.out.println(point + " " + price2);
				cdao.updatePoint("-", point, mbVo3.getClientId());
				cdao.updatePoint("+", price2, mbVo3.getClientId());
				mbDao3.movieBookingInsert(mbVo3, date,point);
				str = "index.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "movieInsert":
			MovieDAO dao33;
			MovieTimeDAO mtdao33;
			MovieTimeVO mtvo33;
			try {
				dao33 = new MovieDAO();
				mtdao33 = new MovieTimeDAO();
				
				String movieName2 = request.getParameter("movieName");
				String movieInfo = request.getParameter("movieInfo");
				String movieStar = request.getParameter("movieStar");
				String movieOpening = request.getParameter("movieOpening");
				String movieShowing = request.getParameter("movieShowing");
				String movieAge2 = request.getParameter("movieAge");
				String movieRoom = request.getParameter("movieRoom");
				String movieStartTime = request.getParameter("movieStartTime");
				
				boolean flag =dao33.insertMovie(movieName2, movieInfo, (String)session.getAttribute("str3"), Integer.parseInt(movieStar), Integer.parseInt(movieAge2), movieOpening, movieShowing);
				System.out.println(flag);
				if(flag == true) {
					mtvo33 = new MovieTimeVO(
							movieName2,
							Integer.parseInt(movieRoom),
							movieStartTime,
							new Date()
					);
					mtdao33.insertMovieTime(mtvo33, movieShowing);
					String movieRoom2 = request.getParameter("movieRoom2");
					String movieStartTime2 = request.getParameter("movieStartTime2");
					mtvo33 = new MovieTimeVO(
							movieName2,
							Integer.parseInt(movieRoom2),
							movieStartTime2,
							new Date()
					);
					mtdao33.insertMovieTime(mtvo33, movieShowing);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "MovieInsertController";
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(str);
	 	rd.forward(request, response);
	 	session.removeAttribute("str3");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected Map<String,Object> getParameterMap2(HttpServletRequest req) {
	      Map<String,Object> map = new HashMap();

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
	   
	   @SuppressWarnings({ "unchecked" })
	   public static JSONObject convertMapToJson(Map<String, Object> map) {

	      JSONObject json = new JSONObject();

	      for (Map.Entry<String, Object> entry : map.entrySet()) {

	         String key = entry.getKey();

	         Object value = entry.getValue();

	         // json.addProperty(key, value);

	         json.put(key, value);

	      }

	      return json;

	   }
}
