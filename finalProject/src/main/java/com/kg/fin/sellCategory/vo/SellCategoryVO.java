package com.kg.fin.sellCategory.vo;

public class SellCategoryVO {

	/*
	 	--판매카테고리 테이블
		create table sell_categories(
		sell_category_no varchar2(6) primary key                                                                                              ,
		sell_category_name  varchar2(30) not null                                                                                             
		);
	*/
	
	private String sell_category_no;
	private String sell_category_name;
	
	public SellCategoryVO() {}

	public SellCategoryVO(String sell_category_no, String sell_category_name) {
		super();
		this.sell_category_no = sell_category_no;
		this.sell_category_name = sell_category_name;
	}

	public String getSell_category_no() {
		return sell_category_no;
	}

	public void setSell_category_no(String sell_category_no) {
		this.sell_category_no = sell_category_no;
	}

	public String getSell_category_name() {
		return sell_category_name;
	}

	public void setSell_category_name(String sell_category_name) {
		this.sell_category_name = sell_category_name;
	}
	
	
}
