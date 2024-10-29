package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DonHang;
import model.KhachHang;


public class DonHangDAO implements DAOInterface<DonHang>{
	private static DonHangDAO instanceDao;
	private DonHangDAO() {
		
	}
	public static DonHangDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new DonHangDAO();
		return instanceDao;
	}
	
	@Override
	public int insert(DonHang t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
					
		try {
			String sql = "INSERT into shop.DonHang(maDonHang, maKhachHangFK, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, t.getMaDonHang());
			pStatement.setString(2, t.getKhachHang().getMaKhachHang());
			pStatement.setString(3, t.getDiaChiNguoiMua());
			pStatement.setString(4, t.getDiaChiNguoiNhan());
			pStatement.setString(5, t.getTrangThai());
			pStatement.setString(6, t.getHinhThucThanhToan());
			pStatement.setDouble(7, t.getSoTienDaThanhToan());
			pStatement.setDouble(8, t.getSoTienConThieu());
			pStatement.setString(9, t.getNgayDatHang());
			pStatement.setString(10, t.getNgayGiaoHang());
			
			
			ketQua = pStatement.executeUpdate();
			if(ketQua > 0) System.out.println("INSERT DON HANG thanh cong");
			else System.out.println("INSERT DON HANG that bai");
			
			// Ngat ke noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return ketQua;
	}
	
	@Override
	public int insertAll(ArrayList<DonHang> t) {
		int kq = 0;
		for (DonHang donHang : t) {
			kq += this.insert(donHang);
		}
		return kq;
	}
	
	@Override
	public int update(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();

		String sql = "UPDATE DonHang" + " SET " + "maKhachHangFK=?" + ", diaChiNguoiMua=?" + ",diaChiNguoiNhan=?"
				+ ",trangThai=?" + ",hinhThucThanhToan=?" + ",soTienDaThanhToan=?" + ",soTienConthieu=?" + ",ngayDatHang=?"
				+ ",ngayGiaoHang=?" + " WHERE maDonHang=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getKhachHang().getMaKhachHang());
			st.setString(2, t.getDiaChiNguoiMua());
			st.setString(3, t.getDiaChiNguoiNhan());
			st.setString(4, t.getTrangThai());
			st.setString(5, t.getHinhThucThanhToan());
			st.setDouble(6, t.getSoTienDaThanhToan());
			st.setDouble(7, t.getSoTienConThieu());
			st.setString(8, t.getNgayDatHang());
			st.setString(9, t.getNgayGiaoHang());
			st.setString(10, t.getMaDonHang());

			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int delete(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "DELETE FROM DonHang WHERE maDonHang = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	@Override
	public int deleteAll(ArrayList<DonHang> t) {
		int dem = 0;
		for (DonHang donHang : t) {
			dem += this.delete(donHang);
		}
		return dem;
	}

	@Override
	public ArrayList<DonHang> selectAll() {
		ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			String sqlString = "select * from shop.donhang;";
			
			ResultSet rSet = statement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maDonHang = rSet.getString("maDonHang");
				String maKhachHangFK = rSet.getString("maKhachHangFK");
				String diaChiNguoiMua = rSet.getString("diaChiNguoiMua");
				String diaChiNhanHang  = rSet.getString("diaChiNhanHang");
				String trangThai = rSet.getString("trangThai");
				String hinhThucThanhToan = rSet.getString("hinhThucThanhToan");
				Double soTienDaThanhToan = rSet.getDouble("soTienDaThanhToan");
				Double soTienConThieu = rSet.getDouble("soTienConThieu");
				String ngayDatHang = rSet.getString("ngayDatHang");
				String ngayGiaoHang = rSet.getString("ngayGiaoHang");
				
				KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
				KhachHang khachHang = khachHangDAO.selectByID(new KhachHang(maKhachHangFK, "", "", "","","", "", "", null, "", "", false));
				DonHang donHang = new DonHang(maDonHang, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);
				ketQua.add(donHang);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public DonHang selectByID(DonHang t) {
		DonHang ketQua = null;
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
			
			String sqlString = "select * from shop.DonHang where ID = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			ResultSet rSet = pStatement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maDonHang = rSet.getString("maDonHang");
				String maKhachHangFK = rSet.getString("maKhachHangFK");
				String diaChiNguoiMua = rSet.getString("diaChiNguoiMua");
				String diaChiNhanHang  = rSet.getString("diaChiNhanHang");
				String trangThai = rSet.getString("trangThai");
				String hinhThucThanhToan = rSet.getString("hinhThucThanhToan");
				Double soTienDaThanhToan = rSet.getDouble("soTienDaThanhToan");
				Double soTienConThieu = rSet.getDouble("soTienConThieu");
				String ngayDatHang = rSet.getString("ngayDatHang");
				String ngayGiaoHang = rSet.getString("ngayGiaoHang");
				
				
				KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
				KhachHang khachHang = khachHangDAO.selectByID(new KhachHang(maKhachHangFK, "","","", "", "", "", "", null, "", "", false));
				ketQua = new DonHang(maDonHang, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	
	
	
	
}