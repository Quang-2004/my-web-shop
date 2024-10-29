package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.TheLoai;

public class TheLoaiDAO implements DAOInterface<TheLoai>{
	private static TheLoaiDAO instanceDao;
	private TheLoaiDAO() {
		
	}
	public static TheLoaiDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new TheLoaiDAO();
		return instanceDao;
	}
	@Override
	public int insert(TheLoai t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		// Tao cau lenh Sql
		String sql = "insert into shop.TheLoai(maTheLoai, tenTheLoai) "
				+ "values (?, ?);";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());
			st.setString(2, t.getTenTheLoai());
			

			ketQua = st.executeUpdate();
			System.out.println("So dong dc them: " + ketQua);
			if(ketQua > 0) System.out.println("Them thanh cong");
			else System.out.println("Them that bai");
			
			
			// ngat ket noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	
	@Override
	public int insertAll(ArrayList<TheLoai> t) {
		int dem = 0;
		for (TheLoai theLoai : t) {
			dem+=this.insert(theLoai);
		}
		return dem;
	}

	@Override
	public int update(TheLoai t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE TheLoai "+
					 " SET " +
					 " tenTheLoai=?"+
					 " WHERE maTheLoai=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenTheLoai());
			st.setString(2, t.getMaTheLoai());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	
	}

	@Override
	public int delete(TheLoai t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from TheLoai "+
					 " WHERE maTheLoai=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	
	@Override
	public int deleteAll(ArrayList<TheLoai> t) {
		int dem = 0;
		for (TheLoai TheLoai : t) {
			dem += this.delete(TheLoai);
		}
		return dem;
	}

	@Override
	public ArrayList<TheLoai> selectAll() {
		ArrayList<TheLoai> ketQua = new ArrayList<TheLoai>();
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			String sqlString = "select * from shop.TheLoai;";
			
			ResultSet rSet = statement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maTheLoai = rSet.getString("maTheLoai");
				String tenTheLoai = rSet.getString("tenTheLoai");
				
				
				TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
				ketQua.add(theLoai);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public TheLoai selectByID(TheLoai t) {
		TheLoai ketQua = new TheLoai();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM shop.TheLoai WHERE maTheLoai=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());

			// Bước 3: thực thi câu lệnh SQL
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");

				ketQua = new TheLoai(maTheLoai, tenTheLoai);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	
	
	
}