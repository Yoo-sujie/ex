package movie.bookingDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dBConn.DBConn;
import movie.bookingVO.MovieBookingVO;

public class MovieBookingDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MovieBookingDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public void movieBookingDelete(String id) {
		String sql = "DELETE FROM MIBOOKING WHERE CLIENT_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void movieBookingInsert(MovieBookingVO vo, String sDate, int point){
		String sql = "INSERT INTO MIBOOKING VALUES (?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getClientId());
			pstmt.setString(2, vo.getMovieName());
			int year = Integer.parseInt(sDate.substring(0,4))-1900;
	        int month=Integer.parseInt(sDate.substring(4,6))-1;
	        int day = Integer.parseInt(sDate.substring(6,8));
			Date d = new Date(year,month,day);
			pstmt.setDate(3, d);
			pstmt.setString(4, vo.getMovieTime());
			pstmt.setString(5, vo.getSeat());
			pstmt.setInt(6, vo.getMovieAllPrice()-point);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public MovieBookingVO movieBookingSelect(String clientId, String movieName) throws SQLException {
		String sql = "SELECT * FROM MIBOOKING WHERE CLIENT_ID=? AND MOVIE_NAME=?";
		MovieBookingVO vo = null;
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, clientId);
		pstmt.setString(2, movieName);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			vo = new MovieBookingVO(
					rs.getString(1),
					rs.getString(2),
					rs.getDate(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(9)
				);
		}
		return vo;
	}
	
	public ArrayList<String> movieBookingSeatSelect(String movieTime, String movieDate) throws SQLException {
		String sql = "SELECT SEAT FROM MIBOOKING WHERE MOVIE_TIME=? AND MOVIE_DATE=?";
		ArrayList<String> seat = new ArrayList<>();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, movieTime);
		int year = Integer.parseInt(movieDate.substring(0,4))-1900;
        int month=Integer.parseInt(movieDate.substring(4,6))-1;
        int day = Integer.parseInt(movieDate.substring(6,8));
		Date d = new Date(year,month,day);
		pstmt.setDate(2, d);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String s = rs.getString("SEAT");
			seat.add(s);
		}
		return seat;
	}
	
}
