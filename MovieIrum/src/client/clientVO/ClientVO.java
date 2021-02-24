package client.clientVO;

import java.util.Date;

public class ClientVO {

	private String clientId; //회원 아이디
	private String clientPw; //회원 비밀번호
	private String clientName; //회원 이름
	private Date clientBirth; //회원 생일
	private String clientEmail; //회원이메일
	private String clientTel; //회원 전화번호
	private int clientLevel; // 1= 회원 0= 관리자
	private Date clientJoinDate; //회원 가입 날짜
	private int clientPoint; //회원 포인트(적립금)
	
	
	public ClientVO() {}


	public ClientVO(String clientId, String clientPw, String clientName, Date clientBirth, String clientEmail,
			String clientTel, int clientLevel, Date clientJoinDate, int clientPoint) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.clientName = clientName;
		this.clientBirth = clientBirth;
		this.clientEmail = clientEmail;
		this.clientTel = clientTel;
		this.clientLevel = clientLevel;
		this.clientJoinDate = clientJoinDate;
		this.clientPoint = clientPoint;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getClientPw() {
		return clientPw;
	}


	public void setClientPw(String clientPw) {
		this.clientPw = clientPw;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public Date getClientBirth() {
		return clientBirth;
	}


	public void setClientBirth(Date clientBirth) {
		this.clientBirth = clientBirth;
	}


	public String getClientEmail() {
		return clientEmail;
	}


	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}


	public String getClientTel() {
		return clientTel;
	}


	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}


	public int getClientLevel() {
		return clientLevel;
	}


	public void setClientLevel(int clientLevel) {
		this.clientLevel = clientLevel;
	}


	public Date getClientJoinDate() {
		return clientJoinDate;
	}


	public void setClientJoinDate(Date clientJoinDate) {
		this.clientJoinDate = clientJoinDate;
	}


	public int getClientPoint() {
		return clientPoint;
	}


	public void setClientPoint(int clientPoint) {
		this.clientPoint = clientPoint;
	}
}