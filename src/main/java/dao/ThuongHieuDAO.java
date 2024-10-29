
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ThuongHieu;

public class ThuongHieuDAO implements DAOInterface<ThuongHieu>{
	private static ThuongHieuDAO instanceDao;
	private ThuongHieuDAO() {
		
	}
	public static ThuongHieuDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new ThuongHieuDAO();
		return instanceDao;
	}
	@Override
	public int insert(ThuongHieu t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO ThuongHieu (maThuongHieu, tenThuongHieu, sanPhamChinh, noiBan) "+
					" VALUES (?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThuongHieu());
			st.setString(2, t.getTenThuongHieu());
			st.setString(3, t.getSanPhamChinh());
			st.setString(4, t.getNoiBan());
			
			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();
			
			// Bước 4:
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
	public int insertAll(ArrayList<ThuongHieu> t) {
		int dem = 0;
		for (ThuongHieu thuongHieu : t) {
			dem+=this.insert(thuongHieu);
		}
		return dem;
	}

	@Override
	public int update(ThuongHieu t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE shop.ThuongHieu "+
					 " SET " +
					 " tenThuongHieu=?"+
					 ", sanPhamChinh=?"+
					 ", noiBan=?"+
					 " WHERE maThuongHieu=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThuongHieu());
			st.setString(2, t.getTenThuongHieu());
			st.setString(3, t.getSanPhamChinh());
			st.setString(4, t.getNoiBan());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
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
	public int delete(ThuongHieu t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from shop.ThuongHieu "+
					 " WHERE maThuongHieu=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThuongHieu());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
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
	public int deleteAll(ArrayList<ThuongHieu> t) {
		int dem = 0;
		for (ThuongHieu thuongHieu : t) {
			dem+=this.delete(thuongHieu);
		}
		return dem;
	}

	@Override
	public ArrayList<ThuongHieu> selectAll() {
		ArrayList<ThuongHieu> ketQua = new ArrayList<ThuongHieu>();
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			String sqlString = "select * from shop.ThuongHieu;";
			
			ResultSet rSet = statement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maThuongHieu = rSet.getString("maThuongHieu");
				String tenThuongHieu = rSet.getString("tenThuongHieu");
				String sanPhamChinh = rSet.getString("sanPhamChinh");
				String noiBan  = rSet.getString("noiBan");
				
				ThuongHieu thuongHieu = new ThuongHieu(maThuongHieu, tenThuongHieu, sanPhamChinh, noiBan);
				ketQua.add(thuongHieu);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ThuongHieu selectByID(ThuongHieu t) {
		ThuongHieu ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM ThuongHieu WHERE maThuongHieu=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThuongHieu());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rSet = st.executeQuery();
			
			// Bước 4:
			while(rSet.next()) {
				String maThuongHieu = rSet.getString("maThuongHieu");
				String tenThuongHieu = rSet.getString("tenThuongHieu");
				String sanPhamChinh = rSet.getString("sanPhamChinh");
				String noiBan  = rSet.getString("noiBan");
				
				ketQua = new ThuongHieu(maThuongHieu, tenThuongHieu, sanPhamChinh, noiBan);
				break;
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
