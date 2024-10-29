package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import dao.ChiTietDonHangDAO;
import dao.ChiTietGioHangDAO;
import dao.DonHangDAO;
import dao.GioHangDAO;
import dao.ThoiTrangDAO;
import model.ChiTietDonHang;
import model.ChiTietGioHang;
import model.DonHang;
import model.GioHang;
import model.KhachHang;
import model.ThoiTrang;
import util.SoNgauNhien;


/**
 * Servlet implementation class ChiTietSanPhamController
 */
@WebServlet("/chi-tiet-san-pham")
public class SanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPhamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String hanhDong = request.getParameter("hanhDong");
		if(hanhDong.equals("muaHang")) {
			MuaHang(request, response);
		}
		else if(hanhDong.equals("gioHang")) {
			GioHang(request, response);
		}
		else if(hanhDong.equals("xoaSanPham")) {
			XoaSanPham(request, response);
		}
		else if(hanhDong.equals("layGioHang")) {
			LayGioHang(request, response);
		}
		else if(hanhDong.equals("thayDoiSoLuong")) {
			ThayDoiSoLuong(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void MuaHang(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		// kiem tra dang nhap
		Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
		if(loggedIn == null || !loggedIn){
			try {
				response.sendRedirect("khach-hang/DangNhap.jsp");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
		DonHangDAO donHangDAO = DonHangDAO.getInstance();
		ChiTietDonHangDAO chiTietDonHangDAO = ChiTietDonHangDAO.getInstance();
		
		//lay du lieu tu form
		String maThoiTrang = request.getParameter("maThoiTrang");

		ThoiTrang thoiTrang = new ThoiTrang();
		thoiTrang.setMaThoiTrang(maThoiTrang);
		
		//lay du lieu tu database
		thoiTrang = thoiTrangDAO.selectByID(thoiTrang);
		
		// thong tin tu session
		KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
		
		// khoi tao don hang
		DonHang donHang = new DonHang();
		donHang.setMaDonHang(SoNgauNhien.getKiTuNgauNhien(15));
		donHang.setKhachHang(khachHang);
		donHang.setDiaChiNguoiMua(khachHang.getDiaChiMuaHang());
		donHang.setDiaChiNguoiNhan(khachHang.getDiaChiNhanHang());
		donHang.setTrangThai("Chưa thanh toán");
		donHang.setHinhThucThanhToan("Tiền mặt");
		donHang.setSoTienDaThanhToan(0);
		donHang.setSoTienConThieu(thoiTrang.getGiaBan() - donHang.getSoTienDaThanhToan());
		
		LocalDateTime myDate1 = LocalDateTime.now();
		LocalDateTime myDate2 = LocalDateTime.now().plusDays(3);
		
		DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String ngayDatHang = myDate1.format(myFormatter);
		String ngayGiaoHang = myDate2.format(myFormatter);
		donHang.setNgayDatHang(ngayDatHang);
		donHang.setNgayGiaoHang(ngayGiaoHang);
		
		donHangDAO.insert(donHang); // cho vao database
		
		// khoi tao chi tiet don hang
		ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
		
		chiTietDonHang.setMaChiTietDonHang(SoNgauNhien.getKiTuNgauNhien(7));
		chiTietDonHang.setDonHang(donHang);
		chiTietDonHang.setThoiTrang(thoiTrang);
		chiTietDonHang.setSoLuong(1);
		chiTietDonHang.setGiaBan(thoiTrang.getGiaBan());
		chiTietDonHang.setGiamGia(0.2);
		chiTietDonHang.setThueVat(0.1);
		
		chiTietDonHangDAO.insert(chiTietDonHang); // cho vao database
		
		// tru di so luong hang ton kho
		thoiTrang.setSoLuong(thoiTrang.getSoLuong() - chiTietDonHang.getSoLuong());
		thoiTrang.setDaBan(thoiTrang.getDaBan() + chiTietDonHang.getSoLuong());
		
		thoiTrangDAO.updateSoLuong(thoiTrang); // update so luong ton kho
		
		// chuyen trang
		request.setAttribute("success", "mua hàng"); // in ra man hinh đổi mật khẩu thanh cong
		RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher("/khach-hang/Success.jsp");
		try {
			rDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void GioHang(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		// lay thong tin khach hang
		KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
		
		// kiem tra dang nhap
		Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
		if(loggedIn == null || !loggedIn){
			try {
				response.sendRedirect("khach-hang/DangNhap.jsp");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Lấy Thông tin giỏ hàng
		GioHangDAO gioHangDAO = GioHangDAO.getInstance();
		ChiTietGioHangDAO chiTietGioHangDAO = ChiTietGioHangDAO.getInstance();
		ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
		
		GioHang gioHang = new GioHang();
		gioHang.setKhachHang(khachHang);
		gioHang = gioHangDAO.selectByID(gioHang); // gio hang
		
			
		//lay maThoiTrang và lấy ra sản phẩm 
		String maThoiTrang = request.getParameter("maThoiTrang");
		ThoiTrang thoiTrang = new ThoiTrang();
		thoiTrang.setMaThoiTrang(maThoiTrang);
		thoiTrang = thoiTrangDAO.selectByID(thoiTrang); // san pham

		
		//kiem tra no da co trong gio hang chua
		//neu co thi tang so luong
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		chiTietGioHang.setGioHang(gioHang);
		chiTietGioHang.setThoiTrang(thoiTrang);
		chiTietGioHang.setSoLuong(1);
		ArrayList<ChiTietGioHang> chiTietGioHangs = chiTietGioHangDAO.selectByAllId(chiTietGioHang);
		if(chiTietGioHangs == null || chiTietGioHangs.isEmpty()) { // gio hang chua co cai gi
			chiTietGioHangDAO.insert(chiTietGioHang);
		}
		else { // gio hang co san pham
			if(!chiTietGioHangs.contains(chiTietGioHang)) { // san pham can them kh co trong gio hang
				chiTietGioHangDAO.insert(chiTietGioHang);
			}
			else {
				ChiTietGioHang check = chiTietGioHangDAO.selectByID(chiTietGioHang); // dung de check san pham co ton tai trong gio hang kh
				
				
				ChiTietGioHang tmp = new ChiTietGioHang();
				
				String m1 = chiTietGioHang.getThoiTrang().getMaThoiTrang();
				String m2 = check.getThoiTrang().getMaThoiTrang();
				if(m1.equals(m2)) {
					tmp = check;
					tmp.setSoLuong(tmp.getSoLuong() + chiTietGioHang.getSoLuong()); // cap nhat so luong san pham khi trung
					chiTietGioHangDAO.updateSoLuong(tmp);

				}
			}
			
		}
			

		// chuyen trang
		try {
			response.sendRedirect("index.jsp");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void LayGioHang(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		
		// Lay gioHangs tu database
		KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
		if(khachHang == null){
			try {
				response.sendRedirect("khach-hang/DangNhap.jsp");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Lấy Thông tin giỏ hàng
		GioHangDAO gioHangDAO = GioHangDAO.getInstance();
		ChiTietGioHangDAO chiTietGioHangDAO = ChiTietGioHangDAO.getInstance();
		ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
		
		GioHang gioHang = new GioHang();
		gioHang.setKhachHang(khachHang);
		gioHang = gioHangDAO.selectByID(gioHang); // gio hang
		
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		chiTietGioHang.setGioHang(gioHang);
		ArrayList<ChiTietGioHang> chiTietGioHangs = chiTietGioHangDAO.selectByAllId(chiTietGioHang);
		
		// khoi tao gio hang cho khach
		ArrayList<ThoiTrang> gioHangs = new ArrayList<ThoiTrang>();
		for(ChiTietGioHang tmp : chiTietGioHangs) {
			ThoiTrang thoiTrang = thoiTrangDAO.selectByID(tmp.getThoiTrang());
			gioHangs.add(thoiTrang);
		}
		
		
		// them vao session
		session.setAttribute("gioHangs", gioHangs);
		
		 

		try {
			response.sendRedirect("san-pham/GioHang.jsp");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void XoaSanPham(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		// khach hang hien tai
		KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
		
		GioHangDAO gioHangDAO = GioHangDAO.getInstance();
		ChiTietGioHangDAO chiTietGioHangDAO = ChiTietGioHangDAO.getInstance();
		ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
		ThoiTrang thoiTrang = new ThoiTrang();
		
		String maThoiTrang = request.getParameter("maThoiTrang");
		thoiTrang.setMaThoiTrang(maThoiTrang);
		thoiTrang = thoiTrangDAO.selectByID(thoiTrang);
		
		GioHang gioHang = new GioHang();
		gioHang.setKhachHang(khachHang);
		
		// lay gio hang trong database
		gioHang = gioHangDAO.selectByID(gioHang);
		
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		chiTietGioHang.setGioHang(gioHang);
		chiTietGioHang.setThoiTrang(thoiTrang);
		
		// xoa san pham
		chiTietGioHangDAO.delete(chiTietGioHang);
		ArrayList<ChiTietGioHang> chiTietGioHangs = chiTietGioHangDAO.selectByAllId(chiTietGioHang);

		
		// khoi tao gio hang cho khach
		ArrayList<ThoiTrang> gioHangs = new ArrayList<ThoiTrang>();
		for(ChiTietGioHang tmp : chiTietGioHangs) {
			ThoiTrang mm = thoiTrangDAO.selectByID(tmp.getThoiTrang());
			gioHangs.add(mm);
		}

		
		session.setAttribute("gioHangs", gioHangs);
		
		try {
			response.sendRedirect("san-pham/GioHang.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void ThayDoiSoLuong(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
		
		// thay du lieu tu form
		String ans = request.getParameter("quantity");
		int quantity = Integer.parseInt(ans);
		String maThoiTrang = request.getParameter("maThoiTrang");
		String hanhDong1 = request.getParameter("hanhDong1");
		
		
		// Lấy Thông tin giỏ hàng
		GioHangDAO gioHangDAO = GioHangDAO.getInstance();
		ChiTietGioHangDAO chiTietGioHangDAO = ChiTietGioHangDAO.getInstance();
		ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
		
		ThoiTrang thoiTrang = new ThoiTrang();
		thoiTrang.setMaThoiTrang(maThoiTrang);
		thoiTrang = thoiTrangDAO.selectByID(thoiTrang);
		
		GioHang gioHang = new GioHang();
		gioHang.setKhachHang(khachHang);
		gioHang = gioHangDAO.selectByID(gioHang); // gio hang
		
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		chiTietGioHang.setGioHang(gioHang);
		chiTietGioHang.setThoiTrang(thoiTrang);
		chiTietGioHang.setSoLuong(quantity);
		
		
		chiTietGioHangDAO.updateSoLuong(chiTietGioHang); // update soLuongSanPham
		
		ArrayList<ChiTietGioHang> chiTietGioHangs = chiTietGioHangDAO.selectByAllId(chiTietGioHang);
		
		// khoi tao gio hang cho khach
		ArrayList<ThoiTrang> gioHangs = new ArrayList<ThoiTrang>();
		for(ChiTietGioHang tmp : chiTietGioHangs) {
			ThoiTrang mm = thoiTrangDAO.selectByID(tmp.getThoiTrang());
			gioHangs.add(mm);
		}
		
		
		// them vao session
		session.setAttribute("gioHangs", gioHangs);
		
		if(!hanhDong1.equals("khongChuyenTrang") || hanhDong1 == null) {
			try {
				response.sendRedirect("san-pham/GioHang.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
