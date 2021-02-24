package pdm.vo;

public class VOSellProduct {
	private String sell_code;
	private String company_code;
	private String product_code;
	private int sell_quantity;
	private int sell_amount;
	private String output_date;
	private String sell_info;
	
	public VOSellProduct() {}

	public VOSellProduct(String sell_code, String company_code, String product_code, int sell_quantity, int sell_amount,
			String output_date, String sell_info) {
		super();
		this.sell_code = sell_code;
		this.company_code = company_code;
		this.product_code = product_code;
		this.sell_quantity = sell_quantity;
		this.sell_amount = sell_amount;
		this.output_date = output_date;
		this.sell_info = sell_info;
	}

	public String getSell_code() {
		return sell_code;
	}

	public void setSell_code(String sell_code) {
		this.sell_code = sell_code;
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

	public int getSell_quantity() {
		return sell_quantity;
	}

	public void setSell_quantity(int sell_quantity) {
		this.sell_quantity = sell_quantity;
	}

	public int getSell_amount() {
		return sell_amount;
	}

	public void setSell_amount(int sell_amount) {
		this.sell_amount = sell_amount;
	}

	public String getOutput_date() {
		return output_date;
	}

	public void setOutput_date(String output_date) {
		this.output_date = output_date;
	}

	public String getSell_info() {
		return sell_info;
	}

	public void setSell_info(String sell_info) {
		this.sell_info = sell_info;
	}
}
