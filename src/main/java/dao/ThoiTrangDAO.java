package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ThoiTrang;
import model.ThuongHieu;
import model.TheLoai;


public class ThoiTrangDAO implements DAOInterface<ThoiTrang>{
	private static ThoiTrangDAO instanceDao;
	private ThoiTrangDAO() {
		
	}
	public static ThoiTrangDAO getInstance() {
		if(instanceDao == null)
			instanceDao = new ThoiTrangDAO();
		return instanceDao;
	}
	@Override
	public int insert(ThoiTrang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO shop.ThoiTrang (maThoiTrang, tenThoiTrang, maThuongHieu, namSanXuat, giaNhap, giaBan, soLuong, maTheLoai, moTa, daBan, linkAnh) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThoiTrang());
			st.setString(2, t.getTenThoiTrang());
			st.setString(3, t.getThuongHieu().getMaThuongHieu());
			st.setString(4, t.getNamSanXuat());
			st.setDouble(5, t.getGiaNhap());
			st.setDouble(6, t.getGiaBan());
			st.setInt(7, t.getSoLuong());
			st.setString(8, t.getTheLoai().getMaTheLoai());
			st.setString(9, t.getMoTa());
			st.setInt(10, t.getDaBan());
			st.setString(11, t.getLinkAnh());

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("INSERT thoiTrang thanh cong!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	@Override
	public int insertAll(ArrayList<ThoiTrang> t) {
		int dem = 0;
		for (ThoiTrang SanPham : t) {
			dem += this.insert(SanPham);
		}
		return dem;
	}

	@Override
	public int update(ThoiTrang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE shop.ThoiTrang " + " SET " + "tenThoiTrang=?, maThuongHieu=?, namSanXuat=?, giaNhap=?, giaBan=?, "
					+ "soluong=?, maTheLoai=?, moTa=?, daBan=?, linkAnh=?" + " WHERE maThoiTrang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThoiTrang());
			st.setString(2, t.getTenThoiTrang());
			st.setString(3, t.getThuongHieu().getMaThuongHieu());
			st.setString(4, t.getNamSanXuat());
			st.setDouble(5, t.getGiaNhap());
			st.setDouble(6, t.getGiaBan());
			st.setInt(7, t.getSoLuong());
			st.setString(8, t.getTheLoai().getMaTheLoai());
			st.setString(9, t.getMoTa());
			st.setInt(10, t.getDaBan());
			st.setString(11, t.getLinkAnh());
			
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public int updateSoLuong(ThoiTrang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE shop.ThoiTrang SET soluong=?, daBan=? WHERE maThoiTrang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getSoLuong());
			st.setInt(2, t.getDaBan());
			st.setString(3, t.getMaThoiTrang());
			
			
			// Bước 3: thực thi câu lệnh SQL

			ketQua = st.executeUpdate();

			if(ketQua > 0) System.out.println("UPDATE soLuong ton kho thanh cong!");
			else {
				System.out.println("UPDATE soLuong ton kho khong thanh cong!");
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int delete(ThoiTrang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// tao cau lenh sql
			String sql = "DELETE from shop.ThoiTrang " + " WHERE maThoiTrang=?";
			
			// Bước 2: tạo ra đối tượng statement
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaThoiTrang());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
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
	public int deleteAll(ArrayList<ThoiTrang> t) {
		int dem = 0;
		for (ThoiTrang SanPham : t) {
			dem += this.delete(SanPham);
		}
		return dem;
	}

	@Override
	public ArrayList<ThoiTrang> selectAll() {
		ArrayList<ThoiTrang> ketQua = new ArrayList<ThoiTrang>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM shop.ThoiTrang";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rSet = st.executeQuery();

			// Bước 4:
			while (rSet.next()) {
				String maThoiTrang = rSet.getString("maThoiTrang");
				String tenThoiTrang = rSet.getString("tenThoiTrang");
				String maThuongHieu = rSet.getString("maThuongHieu");
				String namSanXuat  = rSet.getString("namSanXuat");
				Double giaNhap = rSet.getDouble("giaNhap");
				Double giaBan = rSet.getDouble("giaBan");
				int soLuong = rSet.getInt("soLuong");
				String maTheLoai = rSet.getString("maTheLoai");
				String moTa = rSet.getString("moTa");
				int daBan = rSet.getInt("daBan");
				String linkAnh = rSet.getString("linkAnh");

				ThuongHieu thuongHieu = ThuongHieuDAO.getInstance().selectByID(new ThuongHieu(maThuongHieu, "", "", ""));
				TheLoai theLoai = TheLoaiDAO.getInstance().selectByID(new TheLoai(maTheLoai, ""));

				ThoiTrang thoiTrang = new ThoiTrang(maThoiTrang, tenThoiTrang, thuongHieu, namSanXuat, giaNhap, giaBan, soLuong, theLoai, moTa, daBan, linkAnh);
				ketQua.add(thoiTrang);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public ThoiTrang selectByID(ThoiTrang t) {
		ThoiTrang ketQua = null;
		
		Connection connection = JDBCUtil.getConnection();
		
		try {
				
			String sqlString = "select * from shop.ThoiTrang where maThoiTrang = ?";
			PreparedStatement pStatement = connection.prepareStatement(sqlString);
			pStatement.setString(1, t.getMaThoiTrang());
			
			ResultSet rSet = pStatement.executeQuery();
			
			while(rSet.next()) {
				String maThoiTrang = rSet.getString("maThoiTrang");
				String tenThoiTrang = rSet.getString("tenThoiTrang");
				String maThuongHieu = rSet.getString("maThuongHieu");
				String namSanXuat  = rSet.getString("namSanXuat");
				Double giaNhap = rSet.getDouble("giaNhap");
				Double giaBan = rSet.getDouble("giaBan");
				int soLuong = rSet.getInt("soLuong");
				String maTheLoai = rSet.getString("maTheLoai");
				String moTa = rSet.getString("moTa");
				int daBan = rSet.getInt("daBan");
				String linkAnh = rSet.getString("linkAnh");

				ThuongHieu thuongHieu = ThuongHieuDAO.getInstance().selectByID(new ThuongHieu(maThuongHieu, "", "", ""));
				TheLoai theLoai = TheLoaiDAO.getInstance().selectByID(new TheLoai(maTheLoai, ""));
				
				
				ketQua = new ThoiTrang(maThoiTrang, tenThoiTrang, thuongHieu, namSanXuat, giaNhap, giaBan, soLuong, theLoai, moTa, daBan, linkAnh);
			}
			
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	
	
	
}