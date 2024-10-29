package model;

public class GioHang {
	private String maGioHang;
	private KhachHang khachHang;
	
	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GioHang(String maGioHang, KhachHang khachHang) {
		super();
		this.maGioHang = maGioHang;
		this.khachHang = khachHang;
	}

	public String getMaGioHang() {
		return maGioHang;
	}

	public void setMaGioHang(String maGioHang) {
		this.maGioHang = maGioHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	
}
