package model;


import java.util.Objects;

public class DonHang {
	private String maDonHang;
	private KhachHang khachHang;
	private String diaChiNguoiMua;
	private String diaChiNguoiNhan;
	private String trangThai;
	private String hinhThucThanhToan;
	private double soTienDaThanhToan;
	private double soTienConThieu;
	private String ngayDatHang;
	private String ngayGiaoHang;
	public DonHang() {

	}
	public DonHang(String maDonHang, KhachHang khachHang, String diaChiNguoiMua, String diaChiNguoiNhan,
			String trangThai, String hinhThucThanhToan, double soTienDaThanhToan, double soTienConThieu,
			String ngayDatHang, String ngayGiaoHang) {
		super();
		this.maDonHang = maDonHang;
		this.khachHang = khachHang;
		this.diaChiNguoiMua = diaChiNguoiMua;
		this.diaChiNguoiNhan = diaChiNguoiNhan;
		this.trangThai = trangThai;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.soTienDaThanhToan = soTienDaThanhToan;
		this.soTienConThieu = soTienConThieu;
		this.ngayDatHang = ngayDatHang;
		this.ngayGiaoHang = ngayGiaoHang;
	}
	public String getMaDonHang() {
		return maDonHang;
	}
	public void setMaDonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public String getDiaChiNguoiMua() {
		return diaChiNguoiMua;
	}
	public void setDiaChiNguoiMua(String diaChiNguoiMua) {
		this.diaChiNguoiMua = diaChiNguoiMua;
	}
	public String getDiaChiNguoiNhan() {
		return diaChiNguoiNhan;
	}
	public void setDiaChiNguoiNhan(String diaChiNguoiNhan) {
		this.diaChiNguoiNhan = diaChiNguoiNhan;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}
	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	public double getSoTienDaThanhToan() {
		return soTienDaThanhToan;
	}
	public void setSoTienDaThanhToan(double soTienDaThanhToan) {
		this.soTienDaThanhToan = soTienDaThanhToan;
	}
	public double getSoTienConThieu() {
		return soTienConThieu;
	}
	public void setSoTienConThieu(double soTienConThieu) {
		this.soTienConThieu = soTienConThieu;
	}
	public String getNgayDatHang() {
		return ngayDatHang;
	}
	public void setNgayDatHang(String ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}
	public String getNgayGiaoHang() {
		return ngayGiaoHang;
	}
	public void setNgayGiaoHang(String ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDonHang);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonHang other = (DonHang) obj;
		return Objects.equals(maDonHang, other.maDonHang);
	}
	@Override
	public String toString() {
		return "DonHang [maDonHang=" + maDonHang + ", khachHang=" + khachHang + ", diaChiNguoiMua=" + diaChiNguoiMua
				+ ", diaChiNguoiNhan=" + diaChiNguoiNhan + ", trangThai=" + trangThai + ", hinhThucThanhToan="
				+ hinhThucThanhToan + ", soTienDaThanhToan=" + soTienDaThanhToan + ", soTienConThieu=" + soTienConThieu
				+ ", ngayDatHang=" + ngayDatHang + ", ngayGiaoHang=" + ngayGiaoHang + "]";
	}
	
}
