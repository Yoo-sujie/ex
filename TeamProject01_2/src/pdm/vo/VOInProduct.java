package pdm.vo;

public class VOInProduct {
	private String in_code;
	private String company_code;
	private String product_code;
	private int in_quantity;
	private int in_amount;
	private String output_date;
	private String in_info;
	private String company_name;
	public VOInProduct() {
		
	}
	public VOInProduct(String in_code, String company_code, String product_code, int in_quantity, int in_amount,
			String output_date, String in_info) {
		super();
		this.in_code = in_code;
		this.company_code = company_code;
		this.product_code = product_code;
		this.in_quantity = in_quantity;
		this.in_amount = in_amount;
		this.output_date = output_date;
		this.in_info = in_info;
	}
	public String getin_code() {
		return in_code;
	}
	public void setin_code(String in_code) {
		this.in_code = in_code;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public int getin_quantity() {
		return in_quantity;
	}
	public void setin_quantity(int in_quantity) {
		this.in_quantity = in_quantity;
	}
	public int getin_amount() {
		return in_amount;
	}
	public void setin_amount(int in_amount) {
		this.in_amount = in_amount;
	}
	public String getOutput_date() {
		return output_date;
	}
	public void setOutput_date(String output_date) {
		this.output_date = output_date;
	}
	public String getin_info() {
		return in_info;
	}
	public void setin_info(String in_info) {
		this.in_info = in_info;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
}
