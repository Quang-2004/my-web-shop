package model;

public class ChiTietGioHang {
	private GioHang gioHang;
	private ThoiTrang thoiTrang;
	private int soLuong;
	
	public ChiTietGioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietGioHang(GioHang gioHang, ThoiTrang thoiTrang, int soLuong) {
		super();
		this.gioHang = gioHang;
		this.thoiTrang = thoiTrang;
		this.soLuong = soLuong;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	public ThoiTrang getThoiTrang() {
		return thoiTrang;
	}

	public void setThoiTrang(ThoiTrang thoiTrang) {
		this.thoiTrang = thoiTrang;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietGioHang [gioHang=" + gioHang + ", thoiTrang=" + thoiTrang + ", soLuong=" + soLuong
				+ ", getGioHang()=" + getGioHang() + ", getThoiTrang()=" + getThoiTrang() + ", getSoLuong()="
				+ getSoLuong() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
