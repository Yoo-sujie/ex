package com.kg.fin.member.board.vo;

import java.util.Date;

public class ReportVO {
	private int sell_no;
	private String report_id;
	private Date report_date;
	private String sell_seller_id;
	
	public ReportVO() {}

	public ReportVO(int sell_no) {
		super();
		this.sell_no = sell_no;
	}

	public ReportVO(String report_id) {
		super();
		this.report_id = report_id;
	}

	public ReportVO(int sell_no, String report_id, Date report_date) {
		super();
		this.sell_no = sell_no;
		this.report_id = report_id;
		this.report_date = report_date;
	}

	public String getSell_seller_id() {
		return sell_seller_id;
	}


	public void setSell_seller_id(String sell_seller_id) {
		this.sell_seller_id = sell_seller_id;
	}


	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public String getReport_id() {
		return report_id;
	}

	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
}
