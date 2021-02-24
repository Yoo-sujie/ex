package com.kg.fin.sellImg.vo;

public class SellImgVO {

	/*
	 	--판매사진
		drop table sell_images;
		create table sell_images(
		sell_no number(8),
		sell_image_no number(8),
		sell_image varchar2(200),
		constraint sell_images_pk primary key (sell_no,sell_image_no), 
		constraint sell_images_fk foreign key(sell_no) references geo.sell(sell_no)
		);
	*/
	
	private int sell_no; //판매 번호
	private int sell_image_no; //판매사진 번호
	private String sell_image; //판매사진 주소

	public SellImgVO() {}

	public SellImgVO(int sell_no, int sell_image_no, String sell_image) {
		super();
		this.sell_no = sell_no;
		this.sell_image_no = sell_image_no;
		this.sell_image = sell_image;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public int getSell_image_no() {
		return sell_image_no;
	}

	public void setSell_image_no(int sell_image_no) {
		this.sell_image_no = sell_image_no;
	}

	public String getSell_image() {
		return sell_image;
	}

	public void setSell_image(String sell_image) {
		this.sell_image = sell_image;
	}

	
	
	
}
