package client.serviceVO;

public class ClientCategoryVO {

	private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ClientCategoryVO() {};
	
	public ClientCategoryVO(String category) {
		super();
		
		this.category = category;
	}
}
