package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.GioHang;
import model.KhachHang;

public class GioHangDAO implements DAOInterface<GioHang>{
	
	private static GioHangDAO instanceDao;
	private GioHangDAO() {
		
	}
	public static GioHangDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new GioHangDAO();
		return instanceDao;
	}
	
	@Override
	public int insert(GioHang t) {
		int ketQua = 0;
		
		Connection connection = JDBCUtil.getConnection();
		
		String sql = "INSERT into shop.GioHang(maGioHang, maKhachHang_fk) " + 
		" values(?, ?);";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, t.getMaGioHang());
			st.setString(2, t.getKhachHang().getMaKhachHang());
			
			ketQua = st.executeUpdate();
			if(ketQua > 0) System.out.println("INSERT gio hang thanh cong!");
			else System.out.println("INSERT gio hang that bai!");
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<GioHang> t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(GioHang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(GioHang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<GioHang> t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<GioHang> selectAll() {
		ArrayList<GioHang> ketQua = new ArrayList<GioHang>();
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			String sql = "SELECT * from shop.GioHang;";
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String maGioHang = resultSet.getString(1);
				String maKhachHang = resultSet.getString(2);
				
				KhachHang khachHang = new KhachHang(maKhachHang, "", "", "","","", "", "", null, "", "", false);
				GioHang gioHang = new GioHang();
				gioHang.setMaGioHang(maGioHang);
				gioHang.setKhachHang(khachHang);
				ketQua.add(gioHang);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public GioHang selectByID(GioHang t) {
		GioHang gioHang = new GioHang();
		
		Connection connection = JDBCUtil.getConnection();
		
		String sql = "SELECT * from shop.GioHang WHERE maKhachHang_fk = ?;";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, t.getKhachHang().getMaKhachHang());
			
			ResultSet resultSet = st.executeQuery();
			
			while(resultSet.next()) {
				String maGioHang = resultSet.getString("maGioHang");
				String maKhachHang = resultSet.getString("maKhachHang_fk");
				
				gioHang.setMaGioHang(maGioHang);
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKhachHang(maKhachHang);
				gioHang.setKhachHang(khachHang);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gioHang;
	}

}
