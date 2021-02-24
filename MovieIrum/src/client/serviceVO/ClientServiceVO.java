package client.serviceVO;

public class ClientServiceVO {
	private String clientId;
	private int clientNum; 
	private int claimNum; 
	private String categoryName;
	private String clientTitle; 
	private String clientContent; 
	private String clientName; 
	private String clientClaimDate;
	private String claimPw;
	//admin은 따로 테이블을 뺐음
	
	public ClientServiceVO() {}
	
	//게시판 글목록 조회하는 생성자
	public ClientServiceVO(String clientId, int claimNum, String categoryName, String clientTitle,
			String clientClaimDate, String claimPw) {
		super();
		this.clientId = clientId;
		this.claimNum = claimNum;
		this.categoryName = categoryName;
		this.clientTitle = clientTitle;
		this.clientClaimDate = clientClaimDate;
		this.claimPw = claimPw;
	}
	
	public ClientServiceVO(String clientId, int clientNum, int claimNum, String categoryName,  String clientTitle, 
			String clientContent, String clientName, String clientClaimDate, String claimPw) {
		super();
		this.clientId = clientId;
		this.clientNum = clientNum;
		this.claimNum = claimNum;
		this.categoryName = categoryName;
		this.clientName = clientName;
		this.clientTitle = clientTitle;
		this.clientContent = clientContent;
		this.clientClaimDate = clientClaimDate;
		this.claimPw = claimPw;
	}

	
	
	
	public String getClientId() {
		return clientId;
	}

	public int getClientNum() {
		return clientNum;
	}

	public int getClaimNum() {
		return claimNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getClientTitle() {
		return clientTitle;
	}

	public String getClientContent() {
		return clientContent;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientClaimDate() {
		return clientClaimDate;
	}

	public String getClaimPw() {
		return claimPw;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientNum(int clientNum) {
		this.clientNum = clientNum;
	}

	public void setClaimNum(int claimNum) {
		this.claimNum = claimNum;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setClientTitle(String clientTitle) {
		this.clientTitle = clientTitle;
	}

	public void setClientContent(String clientContent) {
		this.clientContent = clientContent;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setClientClaimDate(String clientClaimDate) {
		this.clientClaimDate = clientClaimDate;
	}

	public void setClaimPw(String claimPw) {
		this.claimPw = claimPw;
	}

	
	
}