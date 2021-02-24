package com.kg.fin.reviews.vo;

import java.util.Date;

public class ReviewsVO {

	/*
	 	--구매후기 테이블
		drop table reviews;
		create table reviews(
		sell_no           number(8)                                                                   ,
		buyer_id          varchar2(30)  not null                                                      ,
		review_contents   varchar2(500) not null                                                      ,
		review_rating     number(1)     not null                                                      ,
		review_write_date date default sysdate                                                        ,
		constraint reviews_pk primary key (sell_no)                                                   , 
		constraint reviews_sell_no_fk foreign key(sell_no) references geo.sell(sell_no)               ,
		constraint reviews_buyer_id_fk foreign key(buyer_id) references geo.members(member_id)
		
		);
	*/
	
	private int sell_no; //판매 번호
	private String buyer_id; //구매자 아이디
	private String review_contents; //구매후기 내용
	private int review_rating; //구매후기 별점
	private Date review_write_date; //구매후기 작성일자
	
	public ReviewsVO() {}

	public ReviewsVO(int sell_no, String buyer_id, String review_contents, int review_rating, Date review_write_date) {
		super();
		this.sell_no = sell_no;
		this.buyer_id = buyer_id;
		this.review_contents = review_contents;
		this.review_rating = review_rating;
		this.review_write_date = review_write_date;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getReview_contents() {
		return review_contents;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public int getReview_rating() {
		return review_rating;
	}

	public void setReview_rating(int review_rating) {
		this.review_rating = review_rating;
	}

	public Date getReview_write_date() {
		return review_write_date;
	}

	public void setReview_write_date(Date review_write_date) {
		this.review_write_date = review_write_date;
	}

	
	
	
}
