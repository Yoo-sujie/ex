package com.kg.fin.manager.vo;

public class MemberStoppedVO {

	private String member_id;
	private String member_nic;
	private String member_name;
	private String member_phone;
	private String member_email;
	private int sell_no;
	private String member_join_date;
	private String member_status;
	
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_nic() {
		return member_nic;
	}
	public void setMember_nic(String member_nic) {
		this.member_nic = member_nic;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getSell_no() {
		return sell_no;
	}
	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}
	public String getMember_join_date() {
		return member_join_date;
	}
	public void setMember_join_date(String member_join_date) {
		this.member_join_date = member_join_date;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	
	
	public MemberStoppedVO(){}
	
	public MemberStoppedVO(String member_id, String member_nic, String member_name, String member_phone, String member_email,
			int sell_no, String member_join_date, String member_status) {
		super();
		this.member_id = member_id;
		this.member_nic = member_nic;
		this.member_name = member_name;
		this.member_phone = member_phone;
		this.member_email = member_email;
		this.sell_no = sell_no;
		this.member_join_date = member_join_date;
		this.member_status = member_status;
	}
	
	
	@Override
	public String toString() {
		return "MemberStoppedVO [member_id=" + member_id + ", member_nic=" + member_nic + ", member_name=" + member_name
				+ ", member_phone=" + member_phone + ", member_email=" + member_email + ", sell_no=" + sell_no
				+ ", member_join_date=" + member_join_date + "]";
	}
	
	
	
	
}
