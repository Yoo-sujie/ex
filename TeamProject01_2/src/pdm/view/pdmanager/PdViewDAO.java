package pdm.view.pdmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pdm.connection.DBConn;
import pdm.vo.VOProduct;

public class PdViewDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	public PdViewDAO() throws ClassNotFoundException, SQLException {	
		con = new DBConn().getConnection();
	}

	//전체조회
	public ArrayList<VOProduct> selectAll(String orderBy, String orderValue) {
		ArrayList<VOProduct> pdlist = new ArrayList<VOProduct>();
		try {
			String queryCtm =  "SELECT * FROM PRODUCT"
					+ " ORDER BY " + orderValue + " " + orderBy;
			pstmt = con.prepareStatement(queryCtm);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOProduct pd = new VOProduct();
				pd.setProduct_name(rs.getString(1));
				pd.setProduct_code(rs.getString(2));
				pd.setLarge_code(rs.getString(3));
				pd.setMedium_code(rs.getString(4));
				pd.setSmall_code(rs.getString(5));
				pd.setProduct_price(rs.getInt(6));
				pd.setProduct_explain(rs.getString(7));
				pd.setProduct_stock(rs.getInt(8));

				pdlist.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdlist;
	}

	//검색부분
	public ArrayList<VOProduct> selectTarget(String target,String searchText, String orderBy, String orderValue) {
		ArrayList<VOProduct> pdlist = new ArrayList<VOProduct>();
		try {
			String queryCtm =  "SELECT * FROM PRODUCT WHERE " + target +" LIKE '%' || ? || '%'"
					+ " ORDER BY " + orderValue + " " + orderBy;
			pstmt = con.prepareStatement(queryCtm);
			//			pstmt.setString(1, target);
			pstmt.setString(1, searchText);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOProduct pd = new VOProduct();
				pd.setProduct_name(rs.getString(1));
				pd.setProduct_code(rs.getString(2));
				pd.setLarge_code(rs.getString(3));
				pd.setMedium_code(rs.getString(4));
				pd.setSmall_code(rs.getString(5));
				pd.setProduct_price(rs.getInt(6));
				pd.setProduct_explain(rs.getString(7));
				pd.setProduct_stock(rs.getInt(8));

				pdlist.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdlist;
	}
	
	public int insertproduct(String name, String large, String medium, String small, int price,String info,
			int stock) throws SQLException {
		VOProduct pd = new VOProduct();
		String[] typeCode = getTypeCode(large, medium,  small);
		String query_count = "Select count(*) count From PRODUCT";
		pstmt = con.prepareStatement(query_count);
		rs = pstmt.executeQuery();
		rs.next();
		String code = "P" +typeCode[0] + typeCode[1] + typeCode[2] +  String.format("%02d", rs.getInt("count") + 1);

		String query_insert = "INSERT INTO product"
				+ " VALUES(?,?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(query_insert);

		pstmt.setString(1, name);
		pstmt.setString(2, code);
		pstmt.setString(3, typeCode[0]);
		pstmt.setString(4, typeCode[1]);
		pstmt.setString(5, typeCode[2]);
		pstmt.setInt(6, price);
		pstmt.setString(7, ""+info);
		pstmt.setInt(8,  stock);


		int result = pstmt.executeUpdate();
		if(result > 0) {
			pstmt.close();
			return result;
		}
		else {
			con.rollback();
			return -1;

		}
	}
	
	

	public int getUpdate(String product_code, String product_name ,int product_price, int product_stock, String product_explain) {

		try{
			String sql = "UPDATE PRODUCT SET product_name = ? , product_price = ? , product_stock = ? , product_explain = ?"
					+ " WHERE product_code = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,""+product_name);
			pstmt.setString(2,""+product_price);
			pstmt.setString(3,""+product_stock);
			pstmt.setString(4,""+product_explain);
			pstmt.setString(5,""+product_code);
			int updatecnt = pstmt.executeUpdate();
			return updatecnt;

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;

	}
	public int getdelete(String product_code) {
		try {
			String sql = "DELETE FROM PRODUCT WHERE product_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1 , product_code);
			int result = pstmt.executeUpdate();
			return result;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public String[] getTypeCode(String large, String medium, String small) {
		String[] typeCode = new String[3];
		
		String queryGetL = "SELECT large_code FROM LARGE_CATEGORY WHERE large_category = '" + large + "'";
		String queryGetM = "SELECT medium_code FROM MEDIUM_CATEGORY WHERE medium_category = '" + medium + "'";
		String queryGetS = "SELECT small_code FROM SMALL_CATEGORY WHERE small_category= '" + small + "'";
		
		try {
			pstmt = con.prepareStatement(queryGetL);
			rs = pstmt.executeQuery(); 
			if(rs.next());
			typeCode[0] = rs.getString(1);
			
			pstmt = con.prepareStatement(queryGetM);
			rs = pstmt.executeQuery(); 
			if(rs.next());
			typeCode[1] = rs.getString(1);
			
			pstmt = con.prepareStatement(queryGetS);
			rs = pstmt.executeQuery();
			if(rs.next());
			typeCode[2] = rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return typeCode;
	}
}

