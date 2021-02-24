package com.kg.fin.seller.vo;

import java.util.Date;

public class SellerVO {
	
	/*
	--판매 테이블
	DROP TABLE SELL;
	CREATE TABLE SELL(
	SELL_NO                 NUMBER(8)   PRIMARY KEY  판매 번호  
	SELL_PLACE_NO           NUMBER(6)    NOT NULL          판매장소 번호
	SELL_SELLER_ID          VARCHAR2(30)   NOT NULL         판매자 아이디
	SELL_BUYER_ID           VARCHAR2(30)                    구매자 아이디                                                                 ,
	SELL_CATEGORY_NO        VARCHAR2(6)                       판매 카테고리 번호                                                                      ,
	SELL_TITLE              VARCHAR2(100) NOT NULL               판매 제목                                                                   ,
	SELL_CONTENTS           VARCHAR2(500) NOT NULL              판매 내용                                                                    ,
	SELL_PRICE              NUMBER(8)     NOT NULL                  판매 가격                                                                ,
	SELL_PW                 VARCHAR2(20)  NOT NULL                    판매 비밀번호                                                              ,
	SELL_CNT                NUMBER(8)     DEFAULT 0 NOT NULL           판매 조회수                                                             ,
	SELL_BARGAIN            VARCHAR2(1)   NOT NULL CONSTRAINT SELL_SELL_BARGAIN_CK CHECK(SELL_BARGAIN IN ('Y','N')) 판매 흥정가능여부                ,
	SELL_ACKNOWLEDGMENT     VARCHAR2(1)   NOT NULL CONSTRAINT SELL_SELL_ACKNOWLEDGMENT_CK CHECK(SELL_ACKNOWLEDGMENT IN ('Y','N')) 판매 승인 여부  ,
	SELL_WRITE_DATE         DATE          DEFAULT SYSDATE                                                                   판매 작성일        ,
	SELL_DATE               DATE                                                                                         판매 판매일           ,
	SELL_STATUS             NUMBER(1)   DEFAULT 0 CONSTRAINT SELL_SELL_STATUS_CK CHECK(SELL_STATUS IN (0,1,2))          판매 상태            ,--0 : 판매중, 1: 구매중, 2 : 구매완료 , 3 : 삭제                                       
	CONSTRAINT SELL_PLACE_NO_FK FOREIGN KEY(SELL_PLACE_NO) REFERENCES GEO.SELL_PLACE(SELL_PLACE_NO)                                 ,
	CONSTRAINT SELL_SELLER_ID_FK FOREIGN KEY(SELL_SELLER_ID) REFERENCES GEO.MEMBERS(MEMBER_ID)                                      ,
	CONSTRAINT SELL_BUYER_ID_FK FOREIGN KEY(SELL_BUYER_ID) REFERENCES GEO.MEMBERS(MEMBER_ID)                                        ,
	CONSTRAINT SELL_CATEGORY_NO_FK FOREIGN KEY(SELL_CATEGORY_NO) REFERENCES GEO.SELL_CATEGORIES(SELL_CATEGORY_NO)
	);
	
	SELECT S.SELL_NO, S.SELL_SELLER_ID, S.SELL_PLACE_NO, P.SGG_CODE, P.SELL_PLACE_NAME, M.MEMBER_NIC, 
    S.SELL_CATEGORY_NO, C.SELL_CATEGORY_NAME, S.SELL_TITLE,
	S.SELL_CONTENTS, S.SELL_PRICE, S.SELL_CNT, S.SELL_BARGAIN, S.SELL_WRITE_DATE,
    I.SELL_IMAGE_NO, I.SELL_IMAGE
	FROM SELL S
	JOIN SELL_PLACE P ON S.SELL_PLACE_NO = P.SELL_PLACE_NO
	JOIN MEMBERS M ON S.SELL_SELLER_ID = M.MEMBER_ID
	JOIN SELL_CATEGORIES C ON S.SELL_CATEGORY_NO = C.SELL_CATEGORY_NO
    JOIN SELL_IMAGES I ON S.SELL_NO = I.SELL_NO
	WHERE S.SELL_ACKNOWLEDGMENT = 'Y' AND S.SELL_STATUS = 0 
	AND S.SELL_NO = #{SELL_NO};
	*/
	
	private int sell_no; //판매 번호  
	private int sell_place_no; //판매장소 번호
	private int sell_place_item_no; //판매 보관함 번호
	private String sell_seller_id; //판매자 아이디
	private String sell_buyer_id; //구매자 아이디
	private String sgg_code; //시군구 코드
	private String sell_place_name; //역 이름
	private String member_nic; //판매자 닉네임
	private String sell_category_no; //판매 카테고리 번호  
	private String sell_category_name;
	private String sell_title; //판매 제목
	private String sell_contents; //판매 내용
	private int sell_price; //판매 가격 
	private String sell_pw; //판매 비밀번호
	private int sell_cnt; //판매 조회수
	private String sell_bargain; //판매 흥정가능여부('Y','N')
	private String sell_acknowledgment; //판매 승인 여부('Y','N')
	private Date sell_write_date; //판매 작성일 
	private Date sell_date; //판매 판매일
	private int sell_status; //판매 상태 --0 : 판매중, 1: 구매중, 2 : 구매완료     
	private int sell_image_no; //이미지 번호
	private String sell_image; //이미지 이름
	
	public SellerVO() {}

	public SellerVO(int sell_no, int sell_place_no, int sell_place_item_no, String sell_seller_id, String sell_buyer_id,
			String sgg_code, String sell_place_name, String member_nic, String sell_category_no,
			String sell_category_name, String sell_title, String sell_contents, int sell_price, String sell_pw,
			int sell_cnt, String sell_bargain, String sell_acknowledgment, Date sell_write_date, Date sell_date,
			int sell_status, int sell_image_no, String sell_image) {
		super();
		this.sell_no = sell_no;
		this.sell_place_no = sell_place_no;
		this.sell_place_item_no = sell_place_item_no;
		this.sell_seller_id = sell_seller_id;
		this.sell_buyer_id = sell_buyer_id;
		this.sgg_code = sgg_code;
		this.sell_place_name = sell_place_name;
		this.member_nic = member_nic;
		this.sell_category_no = sell_category_no;
		this.sell_category_name = sell_category_name;
		this.sell_title = sell_title;
		this.sell_contents = sell_contents;
		this.sell_price = sell_price;
		this.sell_pw = sell_pw;
		this.sell_cnt = sell_cnt;
		this.sell_bargain = sell_bargain;
		this.sell_acknowledgment = sell_acknowledgment;
		this.sell_write_date = sell_write_date;
		this.sell_date = sell_date;
		this.sell_status = sell_status;
		this.sell_image_no = sell_image_no;
		this.sell_image = sell_image;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public int getSell_place_no() {
		return sell_place_no;
	}

	public void setSell_place_no(int sell_place_no) {
		this.sell_place_no = sell_place_no;
	}

	public int getSell_place_item_no() {
		return sell_place_item_no;
	}

	public void setSell_place_item_no(int sell_place_item_no) {
		this.sell_place_item_no = sell_place_item_no;
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

	public String getSgg_code() {
		return sgg_code;
	}

	public void setSgg_code(String sgg_code) {
		this.sgg_code = sgg_code;
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

	public String getSell_pw() {
		return sell_pw;
	}

	public void setSell_pw(String sell_pw) {
		this.sell_pw = sell_pw;
	}

	public int getSell_cnt() {
		return sell_cnt;
	}

	public void setSell_cnt(int sell_cnt) {
		this.sell_cnt = sell_cnt;
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

	public Date getSell_write_date() {
		return sell_write_date;
	}

	public void setSell_write_date(Date sell_write_date) {
		this.sell_write_date = sell_write_date;
	}

	public Date getSell_date() {
		return sell_date;
	}

	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}

	public int getSell_status() {
		return sell_status;
	}

	public void setSell_status(int sell_status) {
		this.sell_status = sell_status;
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
