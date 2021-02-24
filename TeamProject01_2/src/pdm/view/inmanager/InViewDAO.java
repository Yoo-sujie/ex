package pdm.view.inmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pdm.vo.*;
import pdm.connection.DBConn;

public class InViewDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SimpleDateFormat dateformat;
	
	public InViewDAO() throws ClassNotFoundException, SQLException {	
		con = new DBConn().getConnection();	
	}

	public ArrayList<VOProduct> getProductList(String searchPd) {
		ArrayList<VOProduct> listpd = new ArrayList<VOProduct>();

		String queryPd =  "Select product_code, product_name From PRODUCT"
				+ " WHERE product_name like '%' || ? || '%'";
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
	public ArrayList<VOInProduct> setTodayIn() {
		ArrayList<VOInProduct> inpdlist = new ArrayList<VOInProduct>();
		SimpleDateFormat dataformat = new SimpleDateFormat("yy-MM-dd");
		String queryCtm =  "SELECT * FROM In_PRODUCT"
				+ " WHERE output_date = ?";
		String date = dataformat.format(new Date());
		try {
			pstmt = con.prepareStatement(queryCtm);
			pstmt.setString(1,date);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOInProduct inpd = new VOInProduct();
				inpd.setin_code(rs.getString(1));
				inpd.setCompany_code(rs.getString(2));
				inpd.setProduct_code(rs.getString(3));
				inpd.setin_quantity(rs.getInt(4));
				inpd.setin_amount(rs.getInt(5));
				inpd.setOutput_date(rs.getString(6));
				inpd.setin_info(rs.getString(7));
				inpdlist.add(inpd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inpdlist;
	}

	public ArrayList<VOCustomer> getCustomerList(String searchCtm) {
		ArrayList<VOCustomer> listctm = new ArrayList<VOCustomer>();
		try {
			String queryCtm =  "SELECT company_code, company_name FROM COMPANY_INFO"
					+ " WHERE company_name like '%' || ? || '%'";
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
		String code = "i" + dateformat.format(today);
		dateformat = new SimpleDateFormat("yy-MM-dd");
		String date = dateformat.format(today);
		String querySelectPrice = "SELECT product_price FROM PRODUCT WHERE product_code = ?";
		String queryInsert = "INSERT INTO IN_product "
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
			pstmt.setString(7, info);

			int result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	public ArrayList<VOInProduct> selectAll(String orderBy, String orderValue) {
		ArrayList<VOInProduct> tradepdlist = new ArrayList<VOInProduct>();
		try {
			String queryCtm =  "SELECT * FROM IN_PRODUCT"
					+ " ORDER BY " + orderValue + " " + orderBy;
			pstmt = con.prepareStatement(queryCtm);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOInProduct tradepd = new VOInProduct();
				tradepd.setin_code(rs.getString(1));
				tradepd.setCompany_code(rs.getString(2));
				tradepd.setProduct_code(rs.getString(3));
				tradepd.setin_quantity(rs.getInt(4));
				tradepd.setin_amount(rs.getInt(5));
				tradepd.setOutput_date(rs.getString(6));
				tradepd.setin_info(rs.getString(7));
				tradepdlist.add(tradepd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tradepdlist;
	}
	public int delectTradeTable(String INCode, int Quantity,int amount, String pdCode, String pdName,String date, String info) {
		String queryTradeDelect =  "DELECT FROM IN_PRODUCT "
				+ "WHERE IN_code = ?";
		
		try {
			int result = pstmt.executeUpdate(queryTradeDelect);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
	}

	public int[] updateTradeTable(String inCode, int quantity, String pdCode, String pdName, String date, String info, int total) {
		String queryTradeUpdate =  "UPDATE IN_PRODUCT "
				+ "SET IN_quantity = ?, IN_amount = ?, output_date = ?, IN_info = ?"
				+ "WHERE IN_code = ?";
		int[] resultAndQuantity = {0,0};
		String querygetQuantity = "SELECT IN_quantity FROM IN_PRODUCT WHERE IN_code = ?";
		try {
			pstmt = con.prepareStatement(querygetQuantity);
			pstmt.setString(1, inCode);
			rs = pstmt.executeQuery();
			if(rs.next());
			resultAndQuantity[0] = rs.getInt(1);
			
			total = (total / rs.getInt(1)) * quantity;

			pstmt = con.prepareStatement(queryTradeUpdate);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, total);
			pstmt.setString(3, date);
			pstmt.setString(4, info);
			pstmt.setString(5, inCode);
			resultAndQuantity[1] = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultAndQuantity;
	}

	public ArrayList<VOInProduct> searchSelected(String target, String searchText, String orderBy, String orderValue) {
		ArrayList<VOInProduct> tradepdlist = new ArrayList<VOInProduct>();
		try {
			String querySelect;
			if(target.equals("product_code")) {
			querySelect =  "SELECT s.* FROM IN_PRODUCT s, PRODUCT p"
					+ " WHERE s.product_code = p.product_code AND p.product_name like '%' || ? || '%'"
					+ " ORDER BY " + orderValue + " " + orderBy;
			}
			else {
				querySelect =  "SELECT s.* FROM IN_PRODUCT s, COMPANY_INFO c"
						+ " WHERE s.company_code = c.company_code AND c.company_name like '%' || ? || '%'"
						+ " ORDER BY " + orderValue + " " + orderBy;
			}
			pstmt = con.prepareStatement(querySelect);
			pstmt.setString(1, searchText);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOInProduct tradepd = new VOInProduct();
				tradepd.setin_code(rs.getString(1));
				tradepd.setCompany_code(rs.getString(2));
				tradepd.setProduct_code(rs.getString(3));
				tradepd.setin_quantity(rs.getInt(4));
				tradepd.setin_amount(rs.getInt(5));
				tradepd.setOutput_date(rs.getString(6));
				tradepd.setin_info(rs.getString(7));
				tradepdlist.add(tradepd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tradepdlist;
	}
	public int deleteIn(String inCode) {
		String sqlDelete = "DELETE FROM IN_PRODUCT WHERE In_code = ?";
		try {
			pstmt = con.prepareCall(sqlDelete);
			pstmt.setString(1, inCode);
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
			rs.next();
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
			rs.next();
			ctmName =  rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctmName;
	}
	
	public int reflectStock(String pdCode, int quantity) {
		String sql = "UPDATE PRODUCT SET product_stock = product_stock + ? "
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
		String sql = "UPDATE PRODUCT SET product_stock = product_stock + ? "
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

}

