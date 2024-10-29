
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ChiTietDonHang;
import model.DonHang;
import model.ThoiTrang;


public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang>{
	private static ChiTietDonHangDAO instanceDao;
	private ChiTietDonHangDAO() {
		
	}
	public static ChiTietDonHangDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new ChiTietDonHangDAO();
		return instanceDao;
	}
	@Override
	public int insert(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			
			// Tao ket noi
			Connection connection = JDBCUtil.getConnection();
			
			// Tao cau lenh Sql
			String sql = "INSERT into shop.ChiTietDonHang(maChiTietDonHang, maDonHangFK, maThoiTrangFK, soLuong, giaBan, giamGia, thueVat) "
					+ "values (?, ?, ?, ?, ?, ?, ?);";
					
			// Tao cau lenh statement
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, t.getMaChiTietDonHang());
			pStatement.setString(2, t.getDonHang().getMaDonHang());
			pStatement.setString(3, t.getThoiTrang().getMaThoiTrang());
			pStatement.setInt(4, t.getSoLuong());
			pStatement.setDouble(5, t.getGiaBan());
			pStatement.setDouble(6, t.getGiamGia());
			pStatement.setDouble(7, t.getThueVat());

			
			//
			ketQua = pStatement.executeUpdate();
			if(ketQua > 0) System.out.println("INSERT ChiTietDonHang thanh cong");
			else System.out.println("INSERT ChiTietDonHang that bai");
			
			// Ngat ke noi
			JDBCUtil.closeConnection(connection);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	
	@Override
	public int insertAll(ArrayList<ChiTietDonHang> t) {
		int dem = 0;
		for (ChiTietDonHang ChiTietDonHang : t) {
			dem += this.insert(ChiTietDonHang);
		}
		return dem;
	}
	
	
	@Override
	public int update(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE ChiTietDonHang SET maDonHangFK=?, maThoiTrangFK=?, soLuong=?, giaBan=?, giamGia=?, thueVat=?"
					+ " WHERE machitietdonhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getDonHang().getMaDonHang());
			st.setString(2, t.getThoiTrang().getMaThoiTrang());
			st.setDouble(3, t.getSoLuong());
			st.setDouble(4, t.getGiaBan());
			st.setDouble(5, t.getGiamGia());
			st.setDouble(6, t.getThueVat());
	

			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	
	}

	@Override
	public int delete(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from ChiTietDonHang " + " WHERE maChiTietDonHang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaChiTietDonHang());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	@Override
	public int deleteAll(ArrayList<ChiTietDonHang> t) {
		int dem = 0;
		for (ChiTietDonHang ChiTietDonHang : t) {
			dem += this.delete(ChiTietDonHang);
		}
		return dem;
	}

	@Override
	public ArrayList<ChiTietDonHang> selectAll() {
		ArrayList<ChiTietDonHang> ketQua = new ArrayList<ChiTietDonHang>();
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			// tao cau lenh sql
			String sqlString = "select * from shop.ChiTietDonHang;";
			
			
			// tao doi tuong statement
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maChiTietDonHang = rSet.getString("maChiTietDonHang");
				String maDonHangFK = rSet.getString("maDonHangFK");
				String maThoiTrangFK = rSet.getString("maThoiTrangFK");
				int soLuong  = rSet.getInt("soLuong");
				Double giaBan = rSet.getDouble("giaBan");
				Double giamGia = rSet.getDouble("giamGia");
				Double thueVat = rSet.getDouble("thueVat");
				
				DonHangDAO donHangDAO = DonHangDAO.getInstance();
				DonHang donHang = donHangDAO.selectByID(new DonHang(maDonHangFK, null, "", "", "", "", 0, 0, null, null));
				
				ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
				ThoiTrang thoiTrang = thoiTrangDAO.selectByID(new ThoiTrang(maThoiTrangFK, "", null, "", 0, 0, 0, null, "", 0, ""));
				ChiTietDonHang ctdh = new ChiTietDonHang(maChiTietDonHang, donHang, thoiTrang, soLuong, giaBan, giamGia, thueVat);
				ketQua.add(ctdh);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ChiTietDonHang selectByID(ChiTietDonHang t) {
		ChiTietDonHang ketQua = null;
		// tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		try {
			// tao cau lenh sql
			String sqlString = "select * from shop.ChiTietDonHang where ID = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, t.getMaChiTietDonHang());
			
			// tao doi tuong statement
			ResultSet rSet = pStatement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maChiTietDonHang = rSet.getString("maChiTietDonHang");
				String maDonHangFK = rSet.getString("maDonHangFK");
				String maThoiTrangFK = rSet.getString("maThoiTrangFK");
				int soLuong  = rSet.getInt("soLuong");
				Double giaBan = rSet.getDouble("giaBan");
				Double giamGia = rSet.getDouble("giamGia");
				Double thueVat = rSet.getDouble("thueVat");
				
				
				DonHangDAO donHangDAO = DonHangDAO.getInstance();
				DonHang donHang = donHangDAO.selectByID(new DonHang(maDonHangFK, null, "", "", "", "", 0, 0, null, null));
				
				ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
				ThoiTrang thoiTrang = thoiTrangDAO.selectByID(new ThoiTrang(maThoiTrangFK, "", null, "", 0, 0, 0, null, "", 0, ""));
				ketQua = new ChiTietDonHang(maChiTietDonHang, donHang, thoiTrang, soLuong, giaBan, giamGia, thueVat);
				
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	

	
	
}
