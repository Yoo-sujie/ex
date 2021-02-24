package com.kg.fin.manager.vo;

public class ManagerDetailVO {

	private int sell_no;
	private String sell_place_name;
	private String member_nic;
	private String sell_category_name;
	private String sell_title;
	private String sell_contents;
	private int sell_price;
	private String sell_bargain;
	private String sell_acknowledgment;
	private String sell_write_date;
	
	
	
	public ManagerDetailVO() {}
	
	
	
	public ManagerDetailVO(int sell_no, String sell_place_name, String member_nic, String sell_category_name,
			String sell_title, String sell_contents, int sell_price, String sell_bargain, String sell_acknowledgment, String sell_write_date) {
		super();
		this.sell_no = sell_no;
		this.sell_place_name = sell_place_name;
		this.member_nic = member_nic;
		this.sell_category_name = sell_category_name;
		this.sell_title = sell_title;
		this.sell_contents = sell_contents;
		this.sell_price = sell_price;
		this.sell_bargain = sell_bargain;
		this.sell_acknowledgment = sell_acknowledgment;
		this.sell_write_date = sell_write_date;
	}



	public int getSell_no() {
		return sell_no;
	}
	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}
	public String getSell_place_name() {
		return sell_place_name;
	}
	public void setSell_place_name(String sell_place_name) {
		this.sell_place_name = sell_place_name;
	}
	public String getMember_nic() {
		return member_nic;
	}
	public void setMember_nic(String member_nic) {
		this.member_nic = member_nic;
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
	public String getSell_contents() {
		return sell_contents;
	}
	public void setSell_contents(String sell_contents) {
		this.sell_contents = sell_contents;
	}
	public int getSell_price() {
		return sell_price;
	}
	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}
	public String getSell_bargain() {
		return sell_bargain;
	}
	public void setSell_bargain(String sell_bargain) {
		this.sell_bargain = sell_bargain;
	}
	public String getSell_acknowledgment() {
		return sell_acknowledgment;
	}
	public void setSell_acknowledgment(String sell_acknowledgment) {
		this.sell_acknowledgment = sell_acknowledgment;
	}
	public String getSell_write_date() {
		return sell_write_date;
	}
	public void setSell_write_date(String sell_write_date) {
		this.sell_write_date = sell_write_date;
	}



	@Override
	public String toString() {
		return "ManagerDetailVO [sell_no=" + sell_no + ", sell_place_name=" + sell_place_name + ", member_nic="
				+ member_nic + ", sell_category_name=" + sell_category_name + ", sell_title=" + sell_title
				+ ", sell_contents=" + sell_contents + ", sell_price=" + sell_price + ", sell_bargain=" + sell_bargain
				+ ", sell_acknowledgment=" + sell_acknowledgment + ", sell_write_date=" + sell_write_date + "]";
	}



	
	
	
}
