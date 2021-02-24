package com.kg.fin.manager.vo;

import java.util.Date;

public class ApprovalVO {
	
	int sellNo; //판매 번호  
	private String memberNic; //판매자 닉네임
	private String categoryNo; //판매 카테고리 번호  
	private String sellTitle; //판매 제목
	private String sellContents; //판매 내용
	private int sellPrice; //판매 가격 
	private String sellBargain; //판매 흥정가능여부('Y','N')
	private String sellAcknowledgment; //판매 승인 여부('Y','N')
	private String sellWriteDate; //판매 작성일  
	
	public ApprovalVO() {}

	public ApprovalVO(int sellNo, String memberNic, String categoryNo,
			String sellTitle, String sellContents, int sellPrice, String sellBargain,
			String sellAcknowledgment, String sellWriteDate) {
		super();
		this.sellNo = sellNo;
		this.memberNic = memberNic;
		this.categoryNo = categoryNo;
		this.sellTitle = sellTitle;
		this.sellContents = sellContents;
		this.sellPrice = sellPrice;
		this.sellBargain = sellBargain;
		this.sellAcknowledgment = sellAcknowledgment;
		this.sellWriteDate = sellWriteDate;
	}

	public int getSellNo() {
		return sellNo;
	}

	public void setSellNo(int sellNo) {
		this.sellNo = sellNo;
	}

	public String getMemberNic() {
		return memberNic;
	}

	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getSellTitle() {
		return sellTitle;
	}

	public void setSellTitle(String sellTitle) {
		this.sellTitle = sellTitle;
	}

	public String getSellContents() {
		return sellContents;
	}

	public void setSellContents(String sellContents) {
		this.sellContents = sellContents;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getSellBargain() {
		return sellBargain;
	}

	public void setSellBargain(String sellBargain) {
		this.sellBargain = sellBargain;
	}

	public String getSellAcknowledgment() {
		return sellAcknowledgment;
	}

	public void setSellAcknowledgment(String sellAcknowledgment) {
		this.sellAcknowledgment = sellAcknowledgment;
	}

	public String getSellWriteDate() {
		return sellWriteDate;
	}

	public void setSellWriteDate(String sellWriteDate) {
		this.sellWriteDate = sellWriteDate;
	}

	
	

}
