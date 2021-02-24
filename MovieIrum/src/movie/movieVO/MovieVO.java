package movie.movieVO;

import java.util.Date;

public class MovieVO {

	private String movieName; //��ȭ �̸�
	private String movieInfo; //��ȭ �Ұ�
	private String moviePoster; //��ȭ ������(�̹���)
	private int movieStar; //��ȭ ����
	private Date movieOpening; //��ȭ ������
	private Date movieShowing; //��ȭ ������ ����
	private int movieAge; //��ȭ ��������
	private int moviePrice; //��ȭ �ݾ�
	
	public MovieVO() {}
	
	public MovieVO(String movieName, String movieInfo, String moviePoster, int movieStar, Date movieOpening,
			Date movieShowing, int movieAge, int moviePrice) {
		super();
		this.movieName = movieName;
		this.movieInfo = movieInfo;
		this.moviePoster = moviePoster;
		this.movieStar = movieStar;
		this.movieOpening = movieOpening;
		this.movieShowing = movieShowing;
		this.movieAge = movieAge;
		this.moviePrice = moviePrice;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
	}

	public String getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

	public int getMovieStar() {
		return movieStar;
	}

	public void setMovieStar(int movieStar) {
		this.movieStar = movieStar;
	}

	public Date getMovieOpening() {
		return movieOpening;
	}

	public void setMovieOpening(Date movieOpening) {
		this.movieOpening = movieOpening;
	}

	public Date getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(Date movieShowing) {
		this.movieShowing = movieShowing;
	}

	public int getMovieAge() {
		return movieAge;
	}

	public void setMovieAge(int movieAge) {
		this.movieAge = movieAge;
	}

	public int getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(int moviePrice) {
		this.moviePrice = moviePrice;
	}
	
	
	
}
