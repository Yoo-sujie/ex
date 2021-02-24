package store.storeVO;

public class StoreVO {

	private int productNum; //상품 번호
	private String productName; //상품 이름
	private int productPrice; //상품 가격
	private String productImg; //상품 이미지
	
	public StoreVO() {}

	public StoreVO(int productNum, String productName, int productPrice, String productImg) {
		super();
		this.productNum = productNum;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImg = productImg;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	
	
}
