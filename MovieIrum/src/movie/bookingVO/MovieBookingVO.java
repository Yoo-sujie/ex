package movie.bookingVO;

import java.util.Date;

public class MovieBookingVO {
	
	private String clientId; //�삁留ㅽ븳 �궗�엺 �븘�씠�뵒
	private String movieName; //�삁留ㅽ븳 �쁺�솕 �씠由�
	private Date movieDate;
	private String movieTime; //�쁺�솕 �떆媛�
	private String seat; //�옄由�
	private int movieAllPrice; //媛�寃�
	
	public MovieBookingVO() {}

	public MovieBookingVO(String clientId, String movieName, Date movieDate, String movieTime, String seat, int movieAllPrice) {
		super();
		this.clientId = clientId;
		this.movieName = movieName;
		this.movieDate = movieDate;
		this.movieTime = movieTime;
		this.seat = seat;
		this.movieAllPrice = movieAllPrice;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getMovieDate() {
		return movieDate;
	}

	public void setMovieDate(Date movieDate) {
		this.movieDate = movieDate;
	}

	public String getMovieTime() {
		return movieTime;
	}

	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public int getMovieAllPrice() {
		return movieAllPrice;
	}

	public void setMovieAllPrice(int movieAllPrice) {
		this.movieAllPrice = movieAllPrice;
	}

	
}
