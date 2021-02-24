package movie.timeVO;

import java.util.Date;

public class MovieTimeVO {
	
	private String movieName; //영화 이름
	private int movieRoom; //영화 관 (1관, 2관)
	private String movieStartTime; //영화 시작 시간
	private Date movieShowing; //영화 끝나는 날짜

	public MovieTimeVO() {}

	public MovieTimeVO(String movieName, int movieRoom, String movieStartTime, Date movieShowing) {
		super();
		this.movieName = movieName;
		this.movieRoom = movieRoom;
		this.movieStartTime = movieStartTime;
		this.movieShowing = movieShowing;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieRoom() {
		return movieRoom;
	}

	public void setMovieRoom(int movieRoom) {
		this.movieRoom = movieRoom;
	}

	public String getMovieStartTime() {
		return movieStartTime;
	}

	public void setMovieStartTime(String movieStartTime) {
		this.movieStartTime = movieStartTime;
	}

	public Date getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(Date movieShowing) {
		this.movieShowing = movieShowing;
	}

	
	
	
}
