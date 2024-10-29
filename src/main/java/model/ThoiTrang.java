package model;

public class ThoiTrang {
	private String maThoiTrang;
	private String tenThoiTrang;
	private ThuongHieu thuongHieu;
	private String namSanXuat;
	private double giaNhap;
	private double giaBan;
	private int soLuong;
	private TheLoai theLoai;
	private String moTa;
	private int daBan;
	private String linkAnh;
	public ThoiTrang() {
		this.maThoiTrang = "";
		this.tenThoiTrang = "";
		this.namSanXuat = "";
		this.giaNhap = 0;
		this.giaBan = 0;
		this.soLuong = 0;
		this.moTa = "";
		this.daBan = 0;
		linkAnh = "";
	}
	
	public ThoiTrang(String maThoiTrang, String tenThoiTrang, ThuongHieu thuongHieu, String namSanXuat, double giaNhap,
			double giaBan, int soLuong, TheLoai theLoai, String moTa, int daBan, String linkAnh) {
		super();
		this.maThoiTrang = maThoiTrang;
		this.tenThoiTrang = tenThoiTrang;
		this.thuongHieu = thuongHieu;
		this.namSanXuat = namSanXuat;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
		this.theLoai = theLoai;
		this.moTa = moTa;
		this.daBan = daBan;
		this.linkAnh = linkAnh;
	}

	public String getMaThoiTrang() {
		return maThoiTrang;
	}
	public void setMaThoiTrang(String maThoiTrang) {
		this.maThoiTrang = maThoiTrang;
	}
	public String getTenThoiTrang() {
		return tenThoiTrang;
	}
	public void setTenThoiTrang(String tenThoiTrang) {
		this.tenThoiTrang = tenThoiTrang;
	}
	public ThuongHieu getThuongHieu() {
		return thuongHieu;
	}
	public void setThuongHieu(ThuongHieu thuongHieu) {
		this.thuongHieu = thuongHieu;
	}
	public String getNamSanXuat() {
		return namSanXuat;
	}
	public void setNamSanXuat(String namSanXuat) {
		this.namSanXuat = namSanXuat;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public TheLoai getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getDaBan() {
		return daBan;
	}

	public void setDaBan(int daBan) {
		this.daBan = daBan;
	}
	
	public String getLinkAnh() {
		return linkAnh;
	}

	public void setLinkAnh(String linkAnh) {
		this.linkAnh = linkAnh;
	}

	@Override
	public String toString() {
		return "ThoiTrang [maThoiTrang=" + maThoiTrang + ", tenThoiTrang=" + tenThoiTrang + ", thuongHieu=" + thuongHieu
				+ ", namSanXuat=" + namSanXuat + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", soLuong=" + soLuong
				+ ", theLoai=" + theLoai + ", moTa=" + moTa + ", daBan=" + daBan + "]";
	}
	
	
	
}
