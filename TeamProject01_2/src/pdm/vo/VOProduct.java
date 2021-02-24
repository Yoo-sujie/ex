package pdm.vo;

public class VOProduct {
	
	private String product_name;
	private String product_code;
	private String large_code;
	private String medium_code;
	private String small_code;
	private int product_price;
	private String product_explain;
	private int product_stock;
	
	public VOProduct(String product_name, String product_code, String large_code, String medium_code, String small_code,
			int product_price, String product_explain,  int product_stock) {
		super();
		this.product_name = product_name;
		this.product_code = product_code;
		this.large_code = large_code;
		this.medium_code = medium_code;
		this.small_code = small_code;
		this.product_price = product_price;
		this.product_explain = product_explain;
		this.product_stock = product_stock;
	}
	public VOProduct() {}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getLarge_code() {
		return large_code;
	}
	public void setLarge_code(String large_code) {
		this.large_code = large_code;
	}
	public String getMedium_code() {
		return medium_code;
	}
	public void setMedium_code(String medium_code) {
		this.medium_code = medium_code;
	}
	public String getSmall_code() {
		return small_code;
	}
	public void setSmall_code(String small_code) {
		this.small_code = small_code;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_explain() {
		return product_explain;
	}
	public void setProduct_explain(String product_explain) {
		this.product_explain = product_explain;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	
	
	
	
}