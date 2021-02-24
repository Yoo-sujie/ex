package movie.timeVO;

import java.util.Date;

public class MovieTimeVO {
	
	private String movieName; //��ȭ �̸�
	private int movieRoom; //��ȭ �� (1��, 2��)
	private String movieStartTime; //��ȭ ���� �ð�
	private Date movieShowing; //��ȭ ������ ��¥

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
