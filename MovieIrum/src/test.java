import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import client.serviceDAO.ClientServiceDAO;
import movie.bookingDAO.MovieBookingDAO;
import movie.bookingVO.MovieBookingVO;
import movie.movieDAO.MovieDAO;
import movie.movieVO.MovieVO;
import movie.timeDAO.MovieTimeDAO;
import movie.timeVO.MovieTimeVO;

public class test {
	public static void main(String[] args) throws Exception {
		
		MovieBookingDAO mbDao = new MovieBookingDAO();
		/*
		MovieBookingVO mbVo = new MovieBookingVO();
		mbVo.setClientId("testID");
		mbVo.setMovieName("싸이코");
		mbVo.setMovieDate(new Date());
		mbVo.setMovieTime("11:30");
		mbVo.setSeat("A1");
		mbVo.setAdult(1);
		mbVo.setYouth(0);
		mbVo.setPass(0);
		mbVo.setMovieAllPrice(7000);
		*/
		/*mbDao.movieBookingInsert("testID","싸이코","11:30","A1", 7000 , "20200711");*/
		
		/*Date d = new Date();
		System.out.println(d.getYear()+1900);
		int month = d.getMonth()+1;
		if(month < 10) {
			System.out.println("0" + month);
		}*/
		
		/*MovieBookingDAO dao = new MovieBookingDAO();
		MovieBookingVO vo = dao.movieBookingSelect("test123", "싸이코");
		System.out.println(vo.getMovieName());
		
		ArrayList<String> seat = dao.movieBookingSeatSelect("13:30<1관>", "20200714");

		ArrayList<String> seatArr = new ArrayList<>();
		for(int i=0; i<seat.size(); i++) {
			if(seat.get(i).length()>=3) {
				String[] s2 = seat.get(i).split(", ");
				for(int j=0; j<s2.length; j++) seatArr.add(s2[j]);
			} else {
				seatArr.add(seat.get(i));
			}
		}*/
			
		
		String str = "D:\\Sue\\eclipse\\3차 프로젝트(mi)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\MovieIrum\\img\\2020.04.22_김??님_커미션_프로필_미방2.jpg";
		String str2 = str.substring(str.indexOf("M"), str.length());
		System.out.println("\\"+ str2);
	}
	
}
