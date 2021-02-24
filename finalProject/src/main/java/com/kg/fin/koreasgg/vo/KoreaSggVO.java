package com.kg.fin.koreasgg.vo;

public class KoreaSggVO {

	/*
	 	SELECT B.SGG_CODE,A.SIG_KOR_NM FROM KOREA_SGG A 
	 	JOIN SELL_PLACE B ON(A.SIG_CD = B.SGG_CODE) 
	 	WHERE B.SIDO_CODE = '#{sido_code}' GROUP BY B.SGG_CODE,A.SIG_KOR_NM;
	*/
	
	private String sgg_code;
	private String sig_kor_nm;
	private String sig_cd;
	private String sido_code;
	
	public KoreaSggVO() {}

	public KoreaSggVO(String sgg_code, String sig_kor_nm, String sig_cd, String sido_code) {
		super();
		this.sgg_code = sgg_code;
		this.sig_kor_nm = sig_kor_nm;
		this.sig_cd = sig_cd;
		this.sido_code = sido_code;
	}

	public String getSgg_code() {
		return sgg_code;
	}

	public void setSgg_code(String sgg_code) {
		this.sgg_code = sgg_code;
	}

	public String getSig_kor_nm() {
		return sig_kor_nm;
	}

	public void setSig_kor_nm(String sig_kor_nm) {
		this.sig_kor_nm = sig_kor_nm;
	}

	public String getSig_cd() {
		return sig_cd;
	}

	public void setSig_cd(String sig_cd) {
		this.sig_cd = sig_cd;
	}

	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	
	
}
