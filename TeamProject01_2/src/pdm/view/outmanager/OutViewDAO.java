package pdm.view.outmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pdm.vo.*;
import pdm.connection.DBConn;

public class OutViewDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SimpleDateFormat dateformat;
	public OutViewDAO() throws ClassNotFoundException, SQLException {	
		con = new DBConn().getConnection();	
	}

	public ArrayList<VOProduct> getProductList(String searchPd) {
		ArrayList<VOProduct> listpd = new ArrayList<VOProduct>();

		String queryPd =  "Select product_code, product_name From PRODUCT"
				+ " WHERE product_name like '%' || ? || '%'"
				+ "ORDER BY product_code ASC";
		try {
			pstmt = con.prepareStatement(queryPd);
			pstmt.setString(1, searchPd);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOProduct pd = new VOProduct();
				pd.setProduct_code(rs.getString(1));
				pd.setProduct_name(rs.getString(2));
				listpd.add(pd);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listpd;
	}

	public ArrayList<VOCustomer> getCustomerList(String searchCtm) {
		ArrayList<VOCustomer> listctm = new ArrayList<VOCustomer>();
		try {
			String queryCtm =  "SELECT company_code, company_name FROM COMPANY_INFO"
					+ " WHERE company_name like '%' || ? || '%'"
					+ "ORDER BY company_code ASC";
			pstmt = con.prepareStatement(queryCtm);
			pstmt.setString(1, searchCtm);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOCustomer ctm = new VOCustomer();
				ctm.setCustomer_code(rs.getString(1));
				ctm.setCustomer_name(rs.getString(2));
				listctm.add(ctm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listctm;
	}

	public int insertData(String pdCode, String ctmCode, int Quantity, String info) {
		dateformat = new SimpleDateFormat("yyMMddHHmmss");
		Date today = new Date();
		String code = "o" + dateformat.format(today);
		dateformat = new SimpleDateFormat("yy-MM-dd");
		String date = dateformat.format(today);
		String querySelectPrice = "SELECT product_price FROM PRODUCT WHERE product_code = ?";
		String queryInsert = "INSERT INTO sell_product "
				+ "VALUES(?,?,?,?,?,?,?)";

		int ammount;
		try {
			pstmt = con.prepareStatement(querySelectPrice);
			pstmt.setString(1, pdCode);
			rs = pstmt.executeQuery();
			rs.next();
			ammount = Quantity * rs.getInt(1);

			pstmt = con.prepareStatement(queryInsert);
			pstmt.setString(1, code);
			pstmt.setString(2, ctmCode);
			pstmt.setString(3, pdCode);
			pstmt.setInt(4, Quantity);
			pstmt.setInt(5, ammount);
			pstmt.setString(6, date);
			pstmt.setString(7, info+" ");

			int result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public ArrayList<VOSellProduct> selectAll(String orderBy, String orderValue) {
		ArrayList<VOSellProduct> sellpdlist = new ArrayList<VOSellProduct>();
		try {
			String queryCtm =  "SELECT * FROM SELL_PRODUCT"
					+ " ORDER BY " + orderValue + " " + orderBy;
			pstmt = con.prepareStatement(queryCtm);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOSellProduct sellpd = new VOSellProduct();
				sellpd.setSell_code(rs.getString(1));
				sellpd.setCompany_code(rs.getString(2));
				sellpd.setProduct_code(rs.getString(3));
				sellpd.setSell_quantity(rs.getInt(4));
				sellpd.setSell_amount(rs.getInt(5));
				sellpd.setOutput_date(rs.getString(6));
				sellpd.setSell_info(rs.getString(7));
				sellpdlist.add(sellpd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellpdlist;
	}

	public int[] updateSellTable(String sellCode, int quantity, String pdCode, String pdName,String date, String info, int total) {
		int[] resultAndQuantity = {0,0};
		
		String querySellUpdate =  "UPDATE SELL_PRODUCT "
				+ "SET SELL_quantity = ?, SELL_amount = ?, output_date = ?, sell_info = ?"
				+ "WHERE sell_code = ?";
		
		String querygetQuantity = "SELECT SELL_quantity FROM SELL_PRODUCT WHERE SELL_code = ?";
		try {
			pstmt = con.prepareStatement(querygetQuantity);
			pstmt.setString(1, sellCode);
			rs = pstmt.executeQuery();
			if(rs.next());
			resultAndQuantity[0] = rs.getInt(1);
			
			total = (total / rs.getInt(1)) * quantity;

			pstmt = con.prepareStatement(querySellUpdate);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, total);
			pstmt.setString(3, date);
			pstmt.setString(4, info);
			pstmt.setString(5, sellCode);
			resultAndQuantity[1] = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultAndQuantity;
	}

	public ArrayList<VOSellProduct> searchSelected(String target, String searchText, String orderBy, String orderValue) {
		ArrayList<VOSellProduct> sellpdlist = new ArrayList<VOSellProduct>();
		try {
			String querySelect;
			if(target.equals("product_code")) {
			querySelect =  "SELECT s.* FROM SELL_PRODUCT s, PRODUCT p"
					+ " WHERE s.product_code = p.product_code AND p.product_name like '%' || ? || '%'"
					+ " ORDER BY " + orderValue + " " + orderBy;
			}
			else {
				querySelect =  "SELECT s.* FROM SELL_PRODUCT s, COMPANY_INFO c"
						+ " WHERE s.company_code = c.company_code AND c.company_name like '%' || ? || '%'"
						+ " ORDER BY " + orderValue + " " + orderBy;
			}
			pstmt = con.prepareStatement(querySelect);
			pstmt.setString(1, searchText);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOSellProduct sellpd = new VOSellProduct();
				sellpd.setSell_code(rs.getString(1));
				sellpd.setCompany_code(rs.getString(2));
				sellpd.setProduct_code(rs.getString(3));
				sellpd.setSell_quantity(rs.getInt(4));
				sellpd.setSell_amount(rs.getInt(5));
				sellpd.setOutput_date(rs.getString(6));
				sellpd.setSell_info(rs.getString(7));
				sellpdlist.add(sellpd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellpdlist;
	}
	
	public ArrayList<VOSellProduct> setTodaySell() {
		ArrayList<VOSellProduct> sellpdlist = new ArrayList<VOSellProduct>();
		SimpleDateFormat dataformat = new SimpleDateFormat("yy-MM-dd");
		String queryCtm =  "SELECT * FROM SELL_PRODUCT"
				+ " WHERE output_date = ?";
		String date = dataformat.format(new Date());
		try {
			pstmt = con.prepareStatement(queryCtm);
			pstmt.setString(1,date);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOSellProduct sellpd = new VOSellProduct();
				sellpd.setSell_code(rs.getString(1));
				sellpd.setCompany_code(rs.getString(2));
				sellpd.setProduct_code(rs.getString(3));
				sellpd.setSell_quantity(rs.getInt(4));
				sellpd.setSell_amount(rs.getInt(5));
				sellpd.setOutput_date(rs.getString(6));
				sellpd.setSell_info(rs.getString(7));
				sellpdlist.add(sellpd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellpdlist;
	}
	
	public int reflectStock(String pdCode, int quantity) {
		String sql = "UPDATE PRODUCT SET product_stock = product_stock - ? "
				+ " WHERE product_code = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setString(2, pdCode);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int reflectStockToUpdate(String pdCode, int quantity, int preQuantity) {
		String sql = "UPDATE PRODUCT SET product_stock = product_stock - ? "
				+ " WHERE product_code = ?";
		int num = quantity - preQuantity;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, pdCode);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int deleteSell(String sellCode) {
		String sqlDelete = "DELETE FROM SELL_PRODUCT WHERE SELL_code = ?";
		try {
			pstmt = con.prepareCall(sqlDelete);
			pstmt.setString(1, sellCode);
			int result = pstmt.executeUpdate();
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getPdName(String pdCode) {
		String queryCtm =  "SELECT product_name FROM PRODUCT"
				+ " WHERE product_code = ?";
		String pdName = "";
		try {
			pstmt = con.prepareStatement(queryCtm);
			pstmt.setString(1, pdCode);
			rs = pstmt.executeQuery();
			while(rs.next())
				pdName =  rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdName;
	}

	public String getCtmName(String ctmCode) {
		String queryCtm =  "SELECT company_name FROM COMPANY_INFO"
				+ " WHERE company_code = ?";
		String ctmName = "";
		try {
			pstmt = con.prepareStatement(queryCtm);
			pstmt.setString(1, ctmCode);
			rs = pstmt.executeQuery();
			if(rs.next());
			ctmName =  rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctmName;
	}



}

