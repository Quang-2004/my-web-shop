package model;

import java.sql.Date;

public class KhachHang {
	private String maKhachHang;
	private String tenDangNhap;
	private String matKhau;
	private String tenKhachHang;
	private String gioiTinh;
	private String diaChi;
	private String diaChiMuaHang;
	private String diaChiNhanHang;
	private Date ngaySinh;
	private String soDienThoai;
	private String email;
	private boolean dangKiNhanThongTinEmail;
	private String maXacThuc;
	private String thoiGianHieuLucMaXacThuc;
	private boolean trangThaiXacThuc;
	private String duongDanAnh;
	public KhachHang() {
		
	}
	public KhachHang(String maKhachHang, String tenDangNhap, String matKhau, String tenKhachHang, String gioiTinh,
			String diaChi, String diaChiMuaHang, String diaChiNhanHang, Date ngaySinh, String soDienThoai, String email,
			boolean dangKiNhanThongTinEmail) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.diaChiMuaHang = diaChiMuaHang;
		this.diaChiNhanHang = diaChiNhanHang;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.dangKiNhanThongTinEmail = dangKiNhanThongTinEmail;
	}
	
	
	public KhachHang(String maKhachHang, String tenDangNhap, String matKhau, String tenKhachHang, String gioiTinh,
			String diaChi, String diaChiMuaHang, String diaChiNhanHang, Date ngaySinh, String soDienThoai, String email,
			boolean dangKiNhanThongTinEmail, String maXacThuc, String thoiGianHieuLucMaXacThuc, boolean trangThaiXacThuc,
			String duongDanAnh) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.diaChiMuaHang = diaChiMuaHang;
		this.diaChiNhanHang = diaChiNhanHang;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.dangKiNhanThongTinEmail = dangKiNhanThongTinEmail;
		this.maXacThuc = maXacThuc;
		this.thoiGianHieuLucMaXacThuc = thoiGianHieuLucMaXacThuc;
		this.trangThaiXacThuc = trangThaiXacThuc;
		this.duongDanAnh = duongDanAnh;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getDiaChiMuaHang() {
		return diaChiMuaHang;
	}
	public void setDiaChiMuaHang(String diaChiMuaHang) {
		this.diaChiMuaHang = diaChiMuaHang;
	}
	public String getDiaChiNhanHang() {
		return diaChiNhanHang;
	}
	public void setDiaChiNhanHang(String diaChiNhanHang) {
		this.diaChiNhanHang = diaChiNhanHang;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isDangKiNhanThongTinEmail() {
		return dangKiNhanThongTinEmail;
	}
	public void setDangKiNhanThongTinEmail(boolean dangKiNhanThongTinEmail) {
		this.dangKiNhanThongTinEmail = dangKiNhanThongTinEmail;
	}
	public String getMaXacThuc() {
		return maXacThuc;
	}
	public void setMaXacThuc(String maXacThuc) {
		this.maXacThuc = maXacThuc;
	}
	public String getThoiGianHieuLucMaXacThuc() {
		return thoiGianHieuLucMaXacThuc;
	}
	public void setThoiGianHieuLucMaXacThuc(String thoiGianHieuLucMaXacThuc) {
		this.thoiGianHieuLucMaXacThuc = thoiGianHieuLucMaXacThuc;
	}
	public boolean getTrangThaiXacThuc() {
		return trangThaiXacThuc;
	}
	public void setTrangThaiXacThuc(boolean trangThaiXacThuc) {
		this.trangThaiXacThuc = trangThaiXacThuc;
	}
	public String getDuongDanAnh() {
		return duongDanAnh;
	}
	public void setDuongDanAnh(String duongDanAnh) {
		this.duongDanAnh = duongDanAnh;
	}
	
}
