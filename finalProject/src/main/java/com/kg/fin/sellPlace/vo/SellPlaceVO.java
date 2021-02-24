package com.kg.fin.sellPlace.vo;

public class SellPlaceVO {

	/*
	 	--판매장소
		DROP TABLE SELL_PLACE;
		CREATE TABLE SELL_PLACE(
		SELL_PLACE_NO         NUMBER(6) PRIMARY KEY                                                                                              ,
		SIDO_CODE             VARCHAR2(2) NOT NULL                                                                                                 ,
		SGG_CODE              VARCHAR2(5) NOT NULL                                                                                                 ,
		SELL_PLACE_NAME       VARCHAR2(30) NOT NULL                                                                                                ,
		SELL_PLACE_ADDRESS    VARCHAR2(100) NOT NULL                                                                                               ,
		SELL_PLACE_LAT        VARCHAR2(20)                                                                                                         ,
		SELL_PLACE_LON        VARCHAR2(20)                                                                                                         ,
		CONSTRAINT SIDO_CODE_FK FOREIGN KEY(SIDO_CODE) REFERENCES GEO.KOREA_SD(CTPRVN_CD)                                                          ,
		CONSTRAINT SGG_CODE_FK FOREIGN KEY(SGG_CODE) REFERENCES GEO.KOREA_SGG(SIG_CD)                                                          
		
		);
	*/
	
	private int sell_place_no; //판매장소 번호
	private String sell_place_name; //역 이름
	private String sgg_code; //시군구 코드
	
	public SellPlaceVO() {}

	public SellPlaceVO(int sell_place_no, String sell_place_name, String sgg_code) {
		super();
		this.sell_place_no = sell_place_no;
		this.sell_place_name = sell_place_name;
		this.sgg_code = sgg_code;
	}

	public int getSell_place_no() {
		return sell_place_no;
	}

	public void setSell_place_no(int sell_place_no) {
		this.sell_place_no = sell_place_no;
	}

	public String getSell_place_name() {
		return sell_place_name;
	}

	public void setSell_place_name(String sell_place_name) {
		this.sell_place_name = sell_place_name;
	}

	public String getSgg_code() {
		return sgg_code;
	}

	public void setSgg_code(String sgg_code) {
		this.sgg_code = sgg_code;
	}

	

	
	
}
