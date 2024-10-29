package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.KhachHang;



public class KhachHangDAO implements DAOInterface<KhachHang>{

	private static KhachHangDAO instanceDao;
	private KhachHangDAO() {
		
	}
	public static KhachHangDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new KhachHangDAO();
		return instanceDao;
	}
	
	@Override
	public int insert(KhachHang t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		// Tao cau lenh Sql
		String sql = "insert into shop.KhachHang(maKhachHang, tenDangNhap, matKhau, tenKhachHang, gioiTinh, diaChi, diaChiMuaHang, diaChiNhanHang, ngaySinh, soDienThoai, email, dangKiNhanBanTin, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());
			st.setString(2, t.getTenDangNhap());
			st.setString(3, t.getMatKhau());
			st.setString(4, t.getTenKhachHang());
			st.setString(5, t.getGioiTinh());
			st.setString(6, t.getDiaChi());
			st.setString(7, t.getDiaChiMuaHang());
			st.setString(8, t.getDiaChiNhanHang());
			st.setDate(9, t.getNgaySinh());
			st.setString(10, t.getSoDienThoai());
			st.setString(11, t.getEmail());
			st.setBoolean(12, t.isDangKiNhanThongTinEmail());
			st.setString(13, t.getMaXacThuc());
			st.setString(14, t.getThoiGianHieuLucMaXacThuc());
			st.setBoolean(15, t.getTrangThaiXacThuc());
			st.setString(16, t.getDuongDanAnh());

			ketQua = st.executeUpdate();
			System.out.println("So dong dc INSERT: " + ketQua);
			if(ketQua > 0) System.out.println("INSERT thanh cong");
			else System.out.println("INSERT that bai");
			
			
			// ngat ket noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> t) {
		int dem = 0;
		for (KhachHang KhachHang : t) {
			dem += this.insert(KhachHang);
		}
		return dem;
	}

	@Override
	public int update(KhachHang t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		try {
			// Tao cau lenh Sql
			String sql = "UPDATE shop.KhachHang "
					+ " SET "
					+ " tenKhachHang = ?"
					+ ", gioiTinh = ?"
					+ ", diaChi = ?"
					+ ", diaChiMuaHang = ?"
					+ ", diaChiNhanHang = ?"
					+ ", ngaySinh = ?" 
					+ ", soDienThoai = ?"
					+ ", email = ?"
					+ ", dangKiNhanBanTin = ?"
					+ ", maXacThuc = ?"
					+ ", thoiGianHieuLucMaXacThuc = ?"
					+ ", trangThaiXacThuc = ?"
					+ ", duongDanAnh = ?"
					+ " where maKhachHang = ?;";
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, t.getTenKhachHang());
			pStatement.setString(2, t.getGioiTinh());
			pStatement.setString(3, t.getDiaChi());
			pStatement.setString(4, t.getDiaChiMuaHang());
			pStatement.setString(5, t.getDiaChiNhanHang());
			pStatement.setDate(6, t.getNgaySinh());
			pStatement.setString(7, t.getSoDienThoai());
			pStatement.setString(8, t.getEmail());
			pStatement.setBoolean(9, t.isDangKiNhanThongTinEmail());			
			pStatement.setString(10, t.getMaXacThuc());
			pStatement.setString(11, t.getThoiGianHieuLucMaXacThuc());
			pStatement.setBoolean(12, t.getTrangThaiXacThuc());
			pStatement.setString(13, t.getDuongDanAnh());
			pStatement.setString(14, t.getMaKhachHang());
			
			ketQua = pStatement.executeUpdate();
			if(ketQua > 0) System.out.println("UPDATE thanh cong");
			else System.out.println("UPDATE that bai");
			
			// Ngat ke noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return ketQua;
	}
	
	public int updateImage(KhachHang t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		try {
			// Tao cau lenh Sql
			String sql = "UPDATE shop.KhachHang "
					+ " SET "
					+ " duongDanAnh = ?"
					+ " where maKhachHang = ?;";
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, t.getDuongDanAnh());
			pStatement.setString(2, t.getMaKhachHang());
			
			ketQua = pStatement.executeUpdate();
			System.out.println("So dong dc UPDATE: " + ketQua);
			if(ketQua > 0) System.out.println("UPDATE img thanh cong");
			else System.out.println("UPDATE that bai");
			
			// Ngat ke noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return ketQua;
	}
	
	public int updateVerifyInformation(KhachHang t) {
		int ketQua = 0;
		
		// Tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		try {
			// Tao cau lenh Sql
			String sql = "UPDATE shop.KhachHang "
					+ " SET "
					+ " maXacThuc = ?"
					+ ", thoiGianHieuLucMaXacThuc = ?"
					+ ", trangThaiXacThuc = ?"
					+ " where maKhachHang = ?;";
			
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, t.getMaXacThuc());
			pStatement.setString(2, t.getThoiGianHieuLucMaXacThuc());
			pStatement.setBoolean(3, t.getTrangThaiXacThuc());
			pStatement.setString(4, t.getMaKhachHang());
			
			ketQua = pStatement.executeUpdate();
			System.out.println("So dong dc them: " + ketQua);
			if(ketQua > 0) System.out.println("UPDATE thanh cong");
			else System.out.println("UPDATE that bai");
			
			// Ngat ke noi
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return ketQua;
	}
	
	public boolean changePassword(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE khachhang " + " SET "  + " matKhau=?" + " WHERE maKhachHang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMatKhau());
			st.setString(2, t.getMaKhachHang());
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

		return ketQua>0;
	}

	@Override
	public int delete(KhachHang t) {
		int ketQua = 0;
		
		// tao ket noi
		Connection connection = JDBCUtil.getConnection();
		try {
			// tao cau lenh sql
			String sqlString = "delete from shop.KhachHang where maKhachHang = ?";
			
			// tao doi tuong statement
			PreparedStatement pStatement = connection.prepareStatement(sqlString); 
			pStatement.setString(1, t.getMaKhachHang());
			
			// thuc thi
			ketQua = pStatement.executeUpdate();
			System.out.println("So dong dc update: " + ketQua);
			if(ketQua > 0) System.out.println("DELETE thanh cong");
			else System.out.println("DELETE that bai");
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return ketQua;
	}
	
	public int delete(String maKhachHang) {
		int ketQua = 0;
		
		// tao ket noi
		Connection connection = JDBCUtil.getConnection();
		try {
			// tao cau lenh sql
			String sqlString = "delete from shop.KhachHang where maKhachHang = ?";
			
			// tao doi tuong statement
			PreparedStatement pStatement = connection.prepareStatement(sqlString); 
			pStatement.setString(1, maKhachHang);
			
			// thuc thi
			ketQua = pStatement.executeUpdate();
			System.out.println("So dong dc xoa: " + ketQua);
			if(ketQua > 0) System.out.println("DELETE thanh cong");
			else System.out.println("DELETE that bai");
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return ketQua;
	}
	
	@Override
	public int deleteAll(ArrayList<KhachHang> t) {
		int dem = 0;
		for (KhachHang KhachHang : t) {
			dem += this.delete(KhachHang);
		}
		return dem;
	}

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
		
		// tao ket noi
		Connection connection = JDBCUtil.getConnection();
		
		try {
			// tao doi tuong statement
			Statement statement = connection.createStatement();
			
			// tao cau lenh sql
			String sqlString = "select * from shop.khachhang;";
			
			// thuc thi
			ResultSet rSet = statement.executeQuery(sqlString);
			
			while(rSet.next()) {
				String maKhachHang = rSet.getString("maKhachHang");
				String tenDangNhap = rSet.getString("tenDangNhap");
				String matKhau = rSet.getString("matKhau");
				String tenKhachHang  = rSet.getString("tenKhachHang");
				String gioiTinh = rSet.getString("gioiTinh");
				String diaChi = rSet.getString("diaChi");
				String diaChiMuaHang = rSet.getString("diaChiMuaHang");
				String diaChiNhanHang = rSet.getString("diaChiNhanHang");
				Date ngaySinh = rSet.getDate("ngaySinh");
				String soDienThoai = rSet.getString("soDienThoai");
				String email = rSet.getString("email");
				boolean dangKiNhanThongTinEmail = rSet.getBoolean("dangKiNhanBanTin");
				String maXacThuc = rSet.getString("maXacThuc");
				String thoiGianHieuLucMaXacThuc = rSet.getString("thoiGianHieuLucMaXacThuc");
				boolean trangThaiXacThuc = rSet.getBoolean("trangThaiXacThuc");
				String duongDanAnh = rSet.getString("duongDanAnh");
				
				KhachHang khachHang = new KhachHang(maKhachHang,tenDangNhap,matKhau,tenKhachHang,gioiTinh, diaChi,
				diaChiMuaHang,diaChiNhanHang,ngaySinh,soDienThoai,email,dangKiNhanThongTinEmail, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh);
				ketQua.add(khachHang);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public KhachHang selectByID(KhachHang t) {
		KhachHang ketQua = null;
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
				
			String sqlString = "select * from shop.KhachHang where maKhachHang = ?;";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, t.getMaKhachHang());
			
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				String maKhachHang = rSet.getString("maKhachHang");
				String tenDangNhap = rSet.getString("tenDangNhap");
				String matKhau = rSet.getString("matKhau");
				String tenKhachHang  = rSet.getString("tenKhachHang");
				String gioiTinh = rSet.getString("gioiTinh");
				String diaChi = rSet.getString("diaChi");
				String diaChiMuaHang = rSet.getString("diaChiMuaHang");
				String diaChiNhanHang = rSet.getString("diaChiNhanHang");
				Date ngaySinh = rSet.getDate("ngaySinh");
				String soDienThoai = rSet.getString("soDienThoai");
				String email = rSet.getString("email");
				boolean dangKiNhanThongTinEmail = rSet.getBoolean("dangKiNhanBanTin");
				String maXacThuc = rSet.getString("maXacThuc");
				String thoiGianHieuLucMaXacThuc = rSet.getString("thoiGianHieuLucMaXacThuc");
				Boolean trangThaiXacThuc = rSet.getBoolean("trangThaiXacThuc");
				String duongDanAnh = rSet.getString("duongDanAnh");
				
				
				ketQua = new KhachHang(maKhachHang,tenDangNhap,matKhau,tenKhachHang,gioiTinh, diaChi,
				diaChiMuaHang,diaChiNhanHang,ngaySinh,soDienThoai,email,dangKiNhanThongTinEmail, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh);
				
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public boolean kiemTraTenDangNhap(String tenDangNhap) {
		boolean ketQua = false;
		try {
			Connection connection = JDBCUtil.getConnection();
				
			String sqlString = "select * from shop.KhachHang where tenDangNhap = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, tenDangNhap);
			
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				ketQua = true;
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public boolean kiemTraMatKhau(String matKhau) {
		boolean ketQua = false;
		try {
			Connection connection = JDBCUtil.getConnection();
				
			String sqlString = "select * from shop.KhachHang where matKhau = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, matKhau);
			
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				ketQua = true;
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public KhachHang selectByUsernameAndPassWord(KhachHang t) {
		KhachHang ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM shop.KhachHang WHERE tenDangNhap =? and matKhau=?;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenDangNhap());
			st.setString(2, t.getMatKhau());
			

			// Bước 3: thực thi câu lệnh SQL
			
			ResultSet rSet = st.executeQuery();

			// Bước 4:
			while (rSet.next()) {
				String maKhachHang = rSet.getString("maKhachHang");
				String tenDangNhap = rSet.getString("tenDangNhap");
				String matKhau = rSet.getString("matKhau");
				String tenKhachHang  = rSet.getString("tenKhachHang");
				String gioiTinh = rSet.getString("gioiTinh");
				String diaChi = rSet.getString("diaChi");
				String diaChiMuaHang = rSet.getString("diaChiMuaHang");
				String diaChiNhanHang = rSet.getString("diaChiNhanHang");
				Date ngaySinh = rSet.getDate("ngaySinh");
				String soDienThoai = rSet.getString("soDienThoai");
				String email = rSet.getString("email");
				boolean dangKiNhanThongTinEmail = rSet.getBoolean("dangKiNhanBanTin");
				String maXacThuc = rSet.getString("maXacThuc");
				String thoiGianHieuLucMaXacThuc = rSet.getString("thoiGianHieuLucMaXacThuc");
				boolean trangThaiXacThuc = rSet.getBoolean("trangThaiXacThuc");
				String duongDanAnh = rSet.getString("duongDanAnh");
				
				ketQua = new KhachHang(maKhachHang, tenDangNhap, matKhau, tenKhachHang, gioiTinh, diaChi, diaChiMuaHang, diaChiNhanHang,
						 ngaySinh, soDienThoai, email, dangKiNhanThongTinEmail, maXacThuc, thoiGianHieuLucMaXacThuc, trangThaiXacThuc, duongDanAnh);
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