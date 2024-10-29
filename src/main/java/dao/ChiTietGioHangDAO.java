package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ChiTietGioHang;
import model.GioHang;
import model.ThoiTrang;

public class ChiTietGioHangDAO implements DAOInterface<ChiTietGioHang>{
	
	private static ChiTietGioHangDAO instanceDao;
	private ChiTietGioHangDAO() {
		
	}
	public static ChiTietGioHangDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new ChiTietGioHangDAO();
		return instanceDao;
	}
	
	@Override
	public int insert(ChiTietGioHang t) {
		int ketQua = 0;
		
		Connection connection = JDBCUtil.getConnection();
		
		String sql = "INSERT into shop.ChiTietGioHang(maGioHang_fk, maThoiTrang_fk, soLuong) " +
		" values(?, ?, ?);";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, t.getGioHang().getMaGioHang());
			st.setString(2, t.getThoiTrang().getMaThoiTrang());
			st.setInt(3, t.getSoLuong());
			
			ketQua = st.executeUpdate();
			
			if(ketQua > 0) System.out.println("INSERT ChiTietGioHang thanh cong!");
			else {
				System.out.println("INSERT ChiTietGioHang that bai!");
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<ChiTietGioHang> t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ChiTietGioHang t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int updateSoLuong(ChiTietGioHang t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
		System.out.println(t.toString());
		try {
			// Tao cau lenh Sql
			String sql = "UPDATE shop.ChiTietGioHang "
					+ " SET "
					+ " soLuong = ?"
					+ " where maGioHang_fk=? and maThoiTrang_fk=?;";
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, t.getSoLuong());
			pStatement.setString(2, t.getGioHang().getMaGioHang());
			pStatement.setString(3, t.getThoiTrang().getMaThoiTrang());
			
			ketQua = pStatement.executeUpdate();
			if(ketQua > 0) System.out.println("UPDATE soLuongSanPham thanh cong");
			else System.out.println("UPDATE soLuongSanPham that bai");
			
			// Ngat ke noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return ketQua;
	}

	@Override
	public int delete(ChiTietGioHang t) {
		int ketQua = 0;
		
		// tao ket noi
		Connection connection = JDBCUtil.getConnection();
		try {
			// tao cau lenh sql
			String sqlString = "DELETE from shop.ChiTietGioHang WHERE maGioHang_fk = ? AND maThoiTrang_fk = ?;";
			
			// tao doi tuong statement
			PreparedStatement pStatement = connection.prepareStatement(sqlString); 
			pStatement.setString(1, t.getGioHang().getMaGioHang());
			pStatement.setString(2, t.getThoiTrang().getMaThoiTrang());
			
			// thuc thi
			ketQua = pStatement.executeUpdate();
			if(ketQua > 0) System.out.println("DELETE ChiTietGioHang thanh cong");
			else System.out.println("DELETE ChiTietGioHang that bai");
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<ChiTietGioHang> t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ChiTietGioHang> selectAll() {
		ArrayList<ChiTietGioHang> ketQua = new ArrayList<ChiTietGioHang>();
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			String sql = "SELECT * from shop.ChiTietGioHang;";
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String maGioHang_fk = resultSet.getString(1);
				String maThoiTrang_fk = resultSet.getString(2);
				int soLuong = resultSet.getInt(3);
				
				GioHang gioHang = new GioHang();
				gioHang.setMaGioHang(maGioHang_fk);
				
				ThoiTrang thoiTrang = new ThoiTrang();
				thoiTrang.setMaThoiTrang(maThoiTrang_fk);
				
				ChiTietGioHang chiTietGioHang = new ChiTietGioHang(gioHang, thoiTrang, soLuong);
				ketQua.add(chiTietGioHang);				
				
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}

	@Override
	public ChiTietGioHang selectByID(ChiTietGioHang t) {
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		Connection connection = JDBCUtil.getConnection();
		try {
		
			String sql = "SELECT * from shop.chitietgiohang WHERE maGioHang_fk = ? AND maThoiTrang_fk = ?;";
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, t.getGioHang().getMaGioHang());
			st.setString(2, t.getThoiTrang().getMaThoiTrang());
			
			ResultSet resultSet = st.executeQuery();
			
			while(resultSet.next()) {
				String maGioHang_fk = resultSet.getString(1);
				String maThoiTrang_fk = resultSet.getString(2);
				int soLuong = resultSet.getInt(3);
				
				GioHang gioHang = new GioHang();
				gioHang.setMaGioHang(maGioHang_fk);
				
				ThoiTrang thoiTrang = new ThoiTrang();
				thoiTrang.setMaThoiTrang(maThoiTrang_fk);
				
				chiTietGioHang.setGioHang(gioHang);
				chiTietGioHang.setThoiTrang(thoiTrang);
				chiTietGioHang.setSoLuong(soLuong);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chiTietGioHang;
	}
	
	public ArrayList<ChiTietGioHang> selectByAllId(ChiTietGioHang t) {
		ArrayList<ChiTietGioHang> ketQua = new ArrayList<ChiTietGioHang>();
		
		Connection connection = JDBCUtil.getConnection();
		
		String sql = "SELECT * from shop.ChiTietGioHang WHERE maGioHang_fk = ?;";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, t.getGioHang().getMaGioHang());
			
			ResultSet resultSet = st.executeQuery();
			
			while(resultSet.next()) {
				String maGioHang_fk = resultSet.getString("maGioHang_fk");
				String maThoiTrang_fk = resultSet.getString("maThoiTrang_fk");
				int soLuong = resultSet.getInt("soLuong");
				
				GioHang gioHang = new GioHang();
				gioHang.setMaGioHang(maGioHang_fk);
				
				ThoiTrang thoiTrang = new ThoiTrang();
				thoiTrang.setMaThoiTrang(maThoiTrang_fk);
				
				ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
				chiTietGioHang.setGioHang(gioHang);
				chiTietGioHang.setThoiTrang(thoiTrang);
				chiTietGioHang.setSoLuong(soLuong);
				
				ketQua.add(chiTietGioHang);	
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

}
