package pdm.view.login;

public class LoginVO {
	
	private String serialNum;
	
	public LoginVO() {}
	public LoginVO(String serialNum) {
		this.serialNum=serialNum;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@Override
	public String toString() {
		return "loginVO [serialNum=" + serialNum + "]";
	}

	

}
