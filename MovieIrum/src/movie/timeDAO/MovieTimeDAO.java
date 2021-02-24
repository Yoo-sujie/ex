package movie.timeDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dBConn.DBConn;
import movie.timeVO.MovieTimeVO;

public class MovieTimeDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MovieTimeDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public void insertMovieTime(MovieTimeVO vo, String showingDate) {
		String sql = "INSERT INTO MOVIE_TIME VALUES (?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getMovieName());
			pstmt.setInt(2, vo.getMovieRoom());
			pstmt.setString(3, vo.getMovieStartTime());
			int showingDateyear = Integer.parseInt(showingDate.substring(0,4))-1900;
	        int showingDatemonth=Integer.parseInt(showingDate.substring(4,6))-1;
	        int showingDateday = Integer.parseInt(showingDate.substring(6,8));
			java.sql.Date showingDate2 = new java.sql.Date(showingDateyear,showingDatemonth,showingDateday);
			pstmt.setDate(4, showingDate2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//영화 관별/이름별 시작시간 검색
	public ArrayList<String> selectMovieRoom(String movieName, int movieRoom) {
		
		String sql = "SELECT MOVIE_START_TIME FROM MOVIE_TIME WHERE MOVIE_NAME = ? AND MOVIE_ROOM = ?";
		
		ArrayList<String> list = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieName);
			pstmt.setInt(2, movieRoom);
			/*int year = Integer.parseInt(selectDate.substring(0,4))-1900;
	        int month=Integer.parseInt(selectDate.substring(4,6))-1;
	        int day = Integer.parseInt(selectDate.substring(6,8));
			Date d = new Date(year,month,day);
			pstmt.setDate(3, d);*/
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("MOVIE_START_TIME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
