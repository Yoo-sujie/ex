package com.kg.fin.member.board.vo;

import java.util.Date;

public class BoardVO {
	private int sell_no;
	private int sell_place_no;
	private String sell_seller_id;
	private String sell_buyer_id;
	private String category_no;
	private String sell_category_name;
	private String sell_title;
	private String sell_contents;
	private String sell_price;
	private String sell_place_name;
	private String sell_date;
	private int sell_status;
	private int sell_image_no;
	private String sell_image;
	
	public BoardVO() {}
	
	// 신고 내역 이미지 포함 가져오기
	public BoardVO(int sell_no, String sell_seller_id, String sell_category_name, String sell_title, String sell_price,
			String sell_place_name, String sell_date, int sell_image_no, String sell_image) {
		super();
		this.sell_no = sell_no;
		this.sell_seller_id = sell_seller_id;
		this.sell_category_name = sell_category_name;
		this.sell_title = sell_title;
		this.sell_price = sell_price;
		this.sell_place_name = sell_place_name;
		this.sell_date = sell_date;
		this.sell_image_no = sell_image_no;
		this.sell_image = sell_image;
	}

	//구매, 판매 내역 목록 가져오기
	public BoardVO(int sell_no, int sell_place_no, String sell_seller_id, String sell_buyer_id, String sell_category_name,
			String sell_title, String sell_price, String sell_place_name, String sell_date) {
		super();
		this.sell_no = sell_no;
		this.sell_seller_id = sell_seller_id;
		this.sell_buyer_id = sell_buyer_id;
		this.sell_category_name = sell_category_name;
		this.sell_title = sell_title;
		this.sell_price = sell_price;
		this.sell_place_name = sell_place_name;
		this.sell_date = sell_date;
	}
	
	public int getSell_place_no() {
		return sell_place_no;
	}

	public void setSell_place_no(int sell_place_no) {
		this.sell_place_no = sell_place_no;
	}

	public String getSell_contents() {
		return sell_contents;
	}

	public void setSell_contents(String sell_contents) {
		this.sell_contents = sell_contents;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public String getSell_seller_id() {
		return sell_seller_id;
	}

	public void setSell_seller_id(String sell_seller_id) {
		this.sell_seller_id = sell_seller_id;
	}

	public String getSell_buyer_id() {
		return sell_buyer_id;
	}

	public void setSell_buyer_id(String sell_buyer_id) {
		this.sell_buyer_id = sell_buyer_id;
	}

	public String getSell_category_name() {
		return sell_category_name;
	}

	public void setSell_category_name(String sell_category_name) {
		this.sell_category_name = sell_category_name;
	}

	public String getSell_title() {
		return sell_title;
	}

	public void setSell_title(String sell_title) {
		this.sell_title = sell_title;
	}

	public String getSell_price() {
		return sell_price;
	}

	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}

	public String getSell_place_name() {
		return sell_place_name;
	}

	public void setSell_place_name(String sell_place_name) {
		this.sell_place_name = sell_place_name;
	}

	public String getSell_date() {
		return sell_date;
	}

	public void setSell_date(String sell_date) {
		this.sell_date = sell_date;
	}

	public String getCategory_no() {
		return category_no;
	}

	public void setCategory_no(String category_no) {
		this.category_no = category_no;
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

	public int getSell_status() {
		return sell_status;
	}

	public void setSell_status(int sell_status) {
		this.sell_status = sell_status;
	}

	
	
}
