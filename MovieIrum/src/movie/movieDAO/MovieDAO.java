package movie.movieDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dBConn.DBConn;
import movie.movieVO.MovieVO;

public class MovieDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MovieDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public ArrayList<MovieVO> indexMovie(String cName) {
		String sql = "SELECT * FROM MOVIE WHERE ROWNUM<9 ORDER BY " + cName;
		ArrayList<MovieVO> arr = new ArrayList<MovieVO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieVO vo = new MovieVO(
						rs.getString("MOVIE_NAME"),
						rs.getString("MOVIE_INFO"),
						rs.getString("MOVIE_POSTER"),
						rs.getInt("MOVIE_STAR"),
						rs.getDate("MOVIE_OPENING"),
						rs.getDate("MOVIE_SHOWING"),
						rs.getInt("MOVIE_AGE"),
						rs.getInt("MOVIE_PRICE")
				);
				arr.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public boolean insertMovie (String movieName, String movieInfo, String moviePoster, int movieStar , int movieAge, String openingDate, String showingDate) {
		String sql = "INSERT INTO MOVIE VALUES (?,?,?,?,?,?,?,?)";
		boolean flag = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieName);
			pstmt.setString(2, movieInfo);
			pstmt.setString(3, moviePoster);
			pstmt.setInt(4, movieStar);
			int openingDateyear = Integer.parseInt(openingDate.substring(0,4))-1900;
	        int openingDatemonth=Integer.parseInt(openingDate.substring(4,6))-1;
	        int openingDateday = Integer.parseInt(openingDate.substring(6,8));
			@SuppressWarnings("deprecation")
			java.sql.Date openingDate2 = new java.sql.Date(openingDateyear,openingDatemonth,openingDateday);
			pstmt.setDate(5, openingDate2);
			int showingDateyear = Integer.parseInt(showingDate.substring(0,4))-1900;
	        int showingDatemonth=Integer.parseInt(showingDate.substring(4,6))-1;
	        int showingDateday = Integer.parseInt(showingDate.substring(6,8));
			java.sql.Date showingDate2 = new java.sql.Date(showingDateyear,showingDatemonth,showingDateday);
			pstmt.setDate(6, showingDate2);
			pstmt.setInt(7, movieAge);
			pstmt.setInt(8, 7000);
			int num = pstmt.executeUpdate();
			if(num == 1) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public ArrayList<MovieVO> selectMovie() {
		
		ArrayList<MovieVO> nameList = new ArrayList<>();
		
		String sql = "SELECT * FROM MOVIE ORDER BY MOVIE_OPENING";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieVO vo = new MovieVO(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getDate(5),
					rs.getDate(6),
					rs.getInt(7),
					rs.getInt(8)
				);
				nameList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameList;
	}
	
	public Date selectShowingDate(String movieName) {
		
		String sql = "SELECT MOVIE_SHOWING FROM MOVIE WHERE MOVIE_NAME = ? AND MOVIE_SHOWING >= SYSDATE";
		Date d = new Date();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				d = rs.getDate("MOVIE_SHOWING");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public MovieVO oneSelectMovie(String movieName) {
		
		String sql = "SELECT * FROM MOVIE WHERE MOVIE_NAME = ?";
		MovieVO vo = new MovieVO();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MovieVO(
						rs.getString("MOVIE_NAME"),
						rs.getString("MOVIE_INFO"),
						rs.getString("MOVIE_POSTER"),
						rs.getInt("MOVIE_STAR"),
						rs.getDate("MOVIE_OPENING"),
						rs.getDate("MOVIE_SHOWING"),
						rs.getInt("MOVIE_AGE"),
						rs.getInt("MOVIE_PRICE")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
}
