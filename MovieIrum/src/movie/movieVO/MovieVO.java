package movie.movieVO;

import java.util.Date;

public class MovieVO {

	private String movieName; //영화 이름
	private String movieInfo; //영화 소개
	private String moviePoster; //영화 포스터(이미지)
	private int movieStar; //영화 평점
	private Date movieOpening; //영화 개봉일
	private Date movieShowing; //영화 마지막 상영일
	private int movieAge; //영화 나이제한
	private int moviePrice; //영화 금액
	
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
