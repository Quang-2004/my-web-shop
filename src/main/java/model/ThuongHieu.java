package model;

public class ThuongHieu {
	private String maThuongHieu;
	private String tenThuongHieu;
	private String sanPhamChinh;
	private String noiBan;
	
	public ThuongHieu() {
		this.maThuongHieu = "";
		this.tenThuongHieu = "";
		this.sanPhamChinh = "";
		this.noiBan = "";
	}
	
	public ThuongHieu(String maThuongHieu, String tenThuongHieu, String sanPhamChinh, String noiBan) {
		super();
		this.maThuongHieu = maThuongHieu;
		this.tenThuongHieu = tenThuongHieu;
		this.sanPhamChinh = sanPhamChinh;
		this.noiBan = noiBan;
	}
	
	public String getMaThuongHieu() {
		return maThuongHieu;
	}
	
	public void setMaThuongHieu(String maThuongHieu) {
		this.maThuongHieu = maThuongHieu;
	}
	
	public String getTenThuongHieu() {
		return tenThuongHieu;
	}
	
	public void setTenThuongHieu(String tenThuongHieu) {
		this.tenThuongHieu = tenThuongHieu;
	}
	
	public String getSanPhamChinh() {
		return sanPhamChinh;
	}
	
	public void setSanPhamChinh(String sanPhamChinh) {
		this.sanPhamChinh = sanPhamChinh;
	}
	
	public String getNoiBan() {
		return noiBan;
	}
	
	public void setNoiBan(String noiBan) {
		this.noiBan = noiBan;
	}

	
}
