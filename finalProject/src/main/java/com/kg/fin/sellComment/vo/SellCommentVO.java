package com.kg.fin.sellComment.vo;

import java.util.Date;

public class SellCommentVO {

	/*
		--판매댓글 테이블
		DROP TABLE SELL_COMMENT;
		CREATE TABLE SELL_COMMENT(
		SELL_NO         NUMBER(8),  
		SELL_COMMENT_NO NUMBER(8),
		SELL_COMMENT_ORIGINNO NUMBER(8),
		SELL_COMMENT_GROUPORD NUMBER(8)   DEFAULT 0,
		SELL_COMMENT_GROUPLAYER NUMBER(8)   DEFAULT 0 CONSTRAINT SELL_COMMENT_GROUPLAYER_CK CHECK(SELL_COMMENT_GROUPLAYER IN (0,1)), --0 : 댓글, 1: 답글
		SELL_COMMENT_WRITER VARCHAR2(30) NOT NULL,
		SELL_COMMENT_CONTENTS VARCHAR2(50) NOT NULL,
		SELL_COMMENT_WRITE_DATE DATE DEFAULT SYSDATE,
		SELL_COMMENT_SECRET NUMBER(1)   DEFAULT 0 CONSTRAINT SELL_COMMENT_SECRET_CK CHECK(SELL_COMMENT_SECRET IN (0,1)),   --0 : 공개 댓글, 1: 비밀 댓글
		SELL_COMMENT_STATUS NUMBER(1)   DEFAULT 0 CONSTRAINT SELL_COMMENT_STATUS_CK CHECK(SELL_COMMENT_STATUS IN (0,1)),   --0 : 정상 댓글, 1: 삭제 댓글
		CONSTRAINT SELL_COMMENT_PK PRIMARY KEY (SELL_NO,SELL_COMMENT_NO), 
		CONSTRAINT SELL_COMMENT_SELL_NO_FK FOREIGN KEY(SELL_NO) REFERENCES geo.SELL(SELL_NO),
		CONSTRAINT SELL_COMMENT_WRITER_FK FOREIGN KEY(SELL_COMMENT_WRITER) REFERENCES geo.MEMBERS(MEMBER_ID)
		);
		
		COMMENT ON TABLE geo.SELL_COMMENT IS '판매댓글 테이블';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_NO IS '판매 번호';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_NO IS '판매댓글 번호';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_ORIGINNO IS '판매댓글 그룹 번호';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_GROUPORD IS '판매댓글 그룹 순서';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_GROUPLAYER IS '판매댓글 그룹 계층';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_WRITER IS '판매댓글 작성자';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_CONTENTS IS '판매댓글 내용';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_WRITE_DATE IS '판매댓글 작성일';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_SECRET IS '판매댓글 비밀여부';
		COMMENT ON COLUMN geo.SELL_COMMENT.SELL_COMMENT_STATUS IS '판매댓글 상태';

	*/
	
	private int sell_no; //판매 번호
	private int sell_comment_no; //판매댓글 번호
	private int sell_comment_originno; //판매댓글 그룹 번호 -- 원댓글의 comment_no
	private int sell_comment_groupord; //판매댓글 그룹 순서 -- 댓글의 답글 순서 (댓글은 0, 답글은 1,2,3...)
	private int sell_comment_grouplayer; //판매댓글 그룹 계층 -- 0:댓글, 1:답글
	private String sell_comment_writer; //판매댓글 작성자
	private String sell_comment_writer_nic; //판매댓글 작성자 닉네임
	private String sell_comment_contents; //판매댓글 내용
	private Date sell_comment_write_date; //판매댓글 작성일
	private String sell_comment_write_date_str; //판매댓글 작성일 String
	private int sell_comment_secret; //판매댓글 비밀여부  -- 0 : 공개 댓글, 1: 비밀 댓글
	private int sell_comment_status; //판매댓글 상태   --0 : 정상 댓글, 1: 삭제 댓글
	private int sell_comment_re_cnt; //판매댓글 답글 여부 --0 : 답글 없음
	private int sell_comment_re_total; //판매댓글 답글 개수
	
	public SellCommentVO() {}

	public SellCommentVO(int sell_no, int sell_comment_no, int sell_comment_originno, int sell_comment_groupord,
			int sell_comment_grouplayer, String sell_comment_writer, String sell_comment_writer_nic,
			String sell_comment_contents, Date sell_comment_write_date, String sell_comment_write_date_str,
			int sell_comment_secret, int sell_comment_status, int sell_comment_re_cnt, int sell_comment_re_total) {
		super();
		this.sell_no = sell_no;
		this.sell_comment_no = sell_comment_no;
		this.sell_comment_originno = sell_comment_originno;
		this.sell_comment_groupord = sell_comment_groupord;
		this.sell_comment_grouplayer = sell_comment_grouplayer;
		this.sell_comment_writer = sell_comment_writer;
		this.sell_comment_writer_nic = sell_comment_writer_nic;
		this.sell_comment_contents = sell_comment_contents;
		this.sell_comment_write_date = sell_comment_write_date;
		this.sell_comment_write_date_str = sell_comment_write_date_str;
		this.sell_comment_secret = sell_comment_secret;
		this.sell_comment_status = sell_comment_status;
		this.sell_comment_re_cnt = sell_comment_re_cnt;
		this.sell_comment_re_total = sell_comment_re_total;
	}

	public int getSell_no() {
		return sell_no;
	}

	public void setSell_no(int sell_no) {
		this.sell_no = sell_no;
	}

	public int getSell_comment_no() {
		return sell_comment_no;
	}

	public void setSell_comment_no(int sell_comment_no) {
		this.sell_comment_no = sell_comment_no;
	}

	public int getSell_comment_originno() {
		return sell_comment_originno;
	}

	public void setSell_comment_originno(int sell_comment_originno) {
		this.sell_comment_originno = sell_comment_originno;
	}

	public int getSell_comment_groupord() {
		return sell_comment_groupord;
	}

	public void setSell_comment_groupord(int sell_comment_groupord) {
		this.sell_comment_groupord = sell_comment_groupord;
	}

	public int getSell_comment_grouplayer() {
		return sell_comment_grouplayer;
	}

	public void setSell_comment_grouplayer(int sell_comment_grouplayer) {
		this.sell_comment_grouplayer = sell_comment_grouplayer;
	}

	public String getSell_comment_writer() {
		return sell_comment_writer;
	}

	public void setSell_comment_writer(String sell_comment_writer) {
		this.sell_comment_writer = sell_comment_writer;
	}

	public String getSell_comment_writer_nic() {
		return sell_comment_writer_nic;
	}

	public void setSell_comment_writer_nic(String sell_comment_writer_nic) {
		this.sell_comment_writer_nic = sell_comment_writer_nic;
	}

	public String getSell_comment_contents() {
		return sell_comment_contents;
	}

	public void setSell_comment_contents(String sell_comment_contents) {
		this.sell_comment_contents = sell_comment_contents;
	}

	public Date getSell_comment_write_date() {
		return sell_comment_write_date;
	}

	public void setSell_comment_write_date(Date sell_comment_write_date) {
		this.sell_comment_write_date = sell_comment_write_date;
	}

	public String getSell_comment_write_date_str() {
		return sell_comment_write_date_str;
	}

	public void setSell_comment_write_date_str(String sell_comment_write_date_str) {
		this.sell_comment_write_date_str = sell_comment_write_date_str;
	}

	public int getSell_comment_secret() {
		return sell_comment_secret;
	}

	public void setSell_comment_secret(int sell_comment_secret) {
		this.sell_comment_secret = sell_comment_secret;
	}

	public int getSell_comment_status() {
		return sell_comment_status;
	}

	public void setSell_comment_status(int sell_comment_status) {
		this.sell_comment_status = sell_comment_status;
	}

	public int getSell_comment_re_cnt() {
		return sell_comment_re_cnt;
	}

	public void setSell_comment_re_cnt(int sell_comment_re_cnt) {
		this.sell_comment_re_cnt = sell_comment_re_cnt;
	}

	public int getSell_comment_re_total() {
		return sell_comment_re_total;
	}

	public void setSell_comment_re_total(int sell_comment_re_total) {
		this.sell_comment_re_total = sell_comment_re_total;
	}

	
	
	
}
