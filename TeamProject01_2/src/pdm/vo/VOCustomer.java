package pdm.vo;

import java.util.Date;

public class VOCustomer {
	private String customer_code;
	private String customer_name;
	private String customer_email;
	private String customer_tel;
	private String customer_address;
	private String customer_info;
	
	public VOCustomer() {}

	public VOCustomer(String customer_code, String customer_name, String customer_email, String customer_tel,
			String customer_address, String customer_info) {
		super();
		this.customer_code = customer_code;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_tel = customer_tel;
		this.customer_address = customer_address;
		this.customer_info = customer_info;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_tel() {
		return customer_tel;
	}

	public void setCustomer_tel(String customer_tel) {
		this.customer_tel = customer_tel;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_info() {
		return customer_info;
	}

	public void setCustomer_info(String customer_info) {
		this.customer_info = customer_info;
	}

	
}
