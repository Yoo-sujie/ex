package com.kg.fin.reviewsImg.vo;

public class ReviewsImgVO {
	
	/*
	 	DROP TABLE REVIEWS_IMAGES;
		CREATE TABLE REVIEWS_IMAGES(
		SELL_NO NUMBER(8),
		REVIEWS_IMAGE_NO NUMBER(8),
		REVIEWS_IMAGE VARCHAR2(200),
		CONSTRAINT REVIEWS_IMAGES_PK PRIMARY KEY (SELL_NO,REVIEWS_IMAGE_NO), 
		CONSTRAINT REVIEWS_IMAGES_FK FOREIGN KEY(SELL_NO) REFERENCES GEO.SELL(SELL_NO)
		);
	*/
	
	private int sell_no; //판매 번호
	private int reviews_image_no; //구매후기사진 번호
	private String reviews_image; //구매후기사진 주소
	
	public ReviewsImgVO() {}

	public ReviewsImgVO(int sell_no, int reviews_image_no, String reviews_image) {
		super();
		this.sell_no = sell_no;
		this.reviews_image_no = reviews_image_no;
		this.reviews_image = reviews_image;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public int getReviews_image_no() {
		return reviews_image_no;
	}

	public void setReviews_image_no(int reviews_image_no) {
		this.reviews_image_no = reviews_image_no;
	}

	public String getReviews_image() {
		return reviews_image;
	}

	public void setReviews_image(String reviews_image) {
		this.reviews_image = reviews_image;
	}

	
	

}
