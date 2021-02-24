package com.kg.fin.koreasd.vo;

public class KoreasdVO {
	
	/*
		SELECT B.SIDO_CODE,A.CTP_KOR_NM FROM KOREA_SD A 
		JOIN sell_place B ON(A.CTPRVN_CD = B.SIDO_CODE) 
		GROUP BY B.SIDO_CODE,A.CTP_KOR_NM
	*/
	
	private String sido_code;
	private String ctp_kor_nm;
	private String ctprvn_cd;
	
	public KoreasdVO() {}

	public KoreasdVO(String sido_code, String ctp_kor_nm, String korea_sd, String ctprvn_cd) {
		super();
		this.sido_code = sido_code;
		this.ctp_kor_nm = ctp_kor_nm;
		this.ctprvn_cd = ctprvn_cd;
	}

	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}

	public String getCtp_kor_nm() {
		return ctp_kor_nm;
	}

	public void setCtp_kor_nm(String ctp_kor_nm) {
		this.ctp_kor_nm = ctp_kor_nm;
	}

	public String getCtprvn_cd() {
		return ctprvn_cd;
	}

	public void setCtprvn_cd(String ctprvn_cd) {
		this.ctprvn_cd = ctprvn_cd;
	}

	
	
	
	
	
}
