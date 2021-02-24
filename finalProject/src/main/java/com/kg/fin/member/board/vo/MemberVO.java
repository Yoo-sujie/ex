package com.kg.fin.member.board.vo;

import java.util.Date;

public class MemberVO {
	
	private String member_id;
	private String member_pw;
	private String member_nic;
	private String member_name;
	private String member_phone;
	private String member_email;
	private String member_status;
	private Date member_join_date;
	
	public MemberVO() {}

	// 로그인 유저 마이페이지 로딩 정보
	public MemberVO(String member_name, String member_id, String member_pw, String member_nic,  String member_phone,
			String member_email, Date member_join_date) {
		super();
		this.member_name = member_name;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_nic = member_nic;
		this.member_phone = member_phone;
		this.member_email = member_email;
		this.member_join_date = member_join_date;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
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

	public String getMember_status() {
		return member_status;
	}

	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

	public Date getMember_join_date() {
		return member_join_date;
	}

	public void setMember_join_date(Date member_join_date) {
		this.member_join_date = member_join_date;
	}
	
	

}
