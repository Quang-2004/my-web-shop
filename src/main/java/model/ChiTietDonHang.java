package model;

public class ChiTietDonHang {
	private String maChiTietDonHang;
	private DonHang donHang;
	private ThoiTrang thoiTrang;
	private int soLuong;
	private double giaBan;
	private double giamGia;
	private double thueVat;
	public ChiTietDonHang() {
		
	}
	public ChiTietDonHang(String maChiTietDonHang, DonHang donHang, ThoiTrang thoiTrang, int soLuong, double giaBan,
			double giamGia, double thueVat) {
		super();
		this.maChiTietDonHang = maChiTietDonHang;
		this.donHang = donHang;
		this.thoiTrang = thoiTrang;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.giamGia = giamGia;
		this.thueVat= thueVat;
	}
	public String getMaChiTietDonHang() {
		return maChiTietDonHang;
	}
	public void setMaChiTietDonHang(String maChiTietDonHang) {
		this.maChiTietDonHang = maChiTietDonHang;
	}
	public DonHang getDonHang() {
		return donHang;
	}
	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
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
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public double getGiamGia() {
		return giamGia;
	}
	public void setGiamGia(double giamGia) {
		this.giamGia = giamGia;
	}
	public double getThueVat() {
		return thueVat;
	}
	public void setThueVat(double thueVat) {
		this.thueVat = thueVat;
	}
	@Override
	public String toString() {
		return "ChiTietDonHang [maChiTietDonHang=" + maChiTietDonHang + ", donHang=" + donHang + ", thoiTrang="
				+ thoiTrang + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", giamGia=" + giamGia + ", thueVat="
				+ thueVat + "]";
	}
	
	
	
}
