package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GioHangDAO;
import dao.KhachHangDAO;
import model.GioHang;
import model.KhachHang;
import util.Email;
import util.MaHoa;
import util.SoNgauNhien;

/**
 * Servlet implementation class KhachHangController
 */
@WebServlet("/khach-hang-controller")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachHangController() {
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
		if(hanhDong.equals("DangNhap")) 
			DangNhap(request, response);
		else if (hanhDong.equals("DangKy")) {
			DangKy(request, response);
		}
		else if (hanhDong.equals("DangXuat")) {
			DangXuat(request, response);
		}
		else if (hanhDong.equals("DoiMatKhau")) {
			DoiMatKhau(request, response);
		}
		else if (hanhDong.equals("ThayDoiThongTin")) {
			ThayDoiThongTin(request, response);
		}
		else if (hanhDong.equals("XacThuc")) {
			XacThuc(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
private void DangNhap(HttpServletRequest request, HttpServletResponse response) {
		
		// lay ra session
		HttpSession session = request.getSession();
		
		//kiem tra thong tin
		boolean check = false;
		
		// lay thong tin cua form
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		// ma hoa matKhau
		String maHoaMatKhau = MaHoa.toSHA1(matKhau);
		
		KhachHang kh = new KhachHang();
		kh.setTenDangNhap(tenDangNhap);
		kh.setMatKhau(maHoaMatKhau);
		
		
		// tao doi tuong KhachHangDAO
		KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();

		// them vao session
		session.setAttribute("tenDangNhap", tenDangNhap);
		session.setAttribute("matKhau", matKhau);
		
		KhachHang khachHang = khachHangDAO.selectByUsernameAndPassWord(kh);

		
		// error
		String e_baoLoi = "";
		
		if(khachHang != null) {	
			if(khachHang.getTrangThaiXacThuc()) {
				session.setAttribute("loggedIn", true);
				session.setAttribute("khachHang", khachHang);
				check = true;
			}
			else {
				e_baoLoi = "Tài khoản chưa được xác thực!";
				check = false;
			}
		}
		else {
			e_baoLoi = "Tên đăng nhập hoặc mật khẩu không đúng!";
			check = false;
		}
		
		// url
		String url = "/khach-hang/Success.jsp";
		
		if(!check) {
			session.setAttribute("e_baoLoi", e_baoLoi);
			url = "/khach-hang/DangNhap.jsp";
		}
		
		// chuyen trang
		request.setAttribute("success", "đăng nhập"); // in ra man hinh dang nhap thanh cong
		RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
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
	
	private void DangXuat(HttpServletRequest request, HttpServletResponse response) {
		// Get the current session 
	    HttpSession session = request.getSession(false); 
	    session.setAttribute("loggedIn", false);
	 
	    // If a session exists, invalidate it 
	    if (session != null) { 
	        session.invalidate(); 
	    } 
	 
	    // Redirect the user to the logout page or the home page 
	    try {
			response.sendRedirect("khach-hang/DangNhap.jsp");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void DangKy(HttpServletRequest request, HttpServletResponse response) {
		// tao session
		HttpSession session = request.getSession();
		
		// tao khachHangDao
		KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
		
		
		// lay thong tin tu form
		
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		String nhapLaiMatKhau = request.getParameter("nhapLaiMatKhau");
		String tenKhachHang = request.getParameter("tenKhachHang");
		String listGioiTinh[] = request.getParameterValues("gioiTinh");
		String gioiTinh = "";
		if(listGioiTinh != null) {
			for(String xString : listGioiTinh) {
				if(xString.equals("Nam")) gioiTinh = "Nam";
				else if (xString.equals("Nữ")) {
					gioiTinh = "Nữ";
				}
				else if(xString.equals("Khác")) gioiTinh = "Khác";
			}
		}
		
		String ngaySinhString = request.getParameter("ngaySinh");
		String diaChi = request.getParameter("diaChi");
		String diaChiMuaHang = request.getParameter("diaChiMuaHang");
		String diaChiNhanHang = request.getParameter("diaChiNhanHang");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		String dongYNhanMail = request.getParameter("dongYNhanMail");
		
		
        // bien check error
        boolean check_error = true;
        
        
		// CHECK ERROR
		// check diaChiMuaHang
		String e_diaChiMuaHang = "";
		if(diaChiMuaHang.trim().length() == 0) { 
			e_diaChiMuaHang = "Vui lòng nhập địa chỉ!";
			check_error = false;
		}
		session.setAttribute("e_diaChiMuaHang", e_diaChiMuaHang);
		
		
		// check diaChiNhanHang
		String e_diaChiNhanHang = "";
		if(diaChiNhanHang.trim().length() == 0) { 
			e_diaChiNhanHang = "Vui lòng nhập địa chỉ!";
			check_error = false;
		}
		session.setAttribute("e_diaChiNhanHang", e_diaChiNhanHang);
		
		// check tenDangNhap
		String e_tenDangNhap = "";
		if(tenDangNhap.trim().length() == 0) {
			e_tenDangNhap = "Vui lòng nhập tên đăng nhập!";
			check_error = false;
		}
		else if(khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
			check_error = false;
			e_tenDangNhap = "Tên đăng nhập đã tồn tại!";
		}
		
	
		session.setAttribute("e_tenDangNhap", e_tenDangNhap);
		
		// check matKhau
		String maHoaMatKhau = MaHoa.toSHA1(matKhau);
		String e_matKhau = "";
		if(matKhau.trim().length() == 0) {
			e_matKhau = "Vui lòng nhập mật khẩu!";
			check_error = false;
		}

		
		session.setAttribute("e_matKhau", e_matKhau);
		
		// check matKhau
		String e_nhapLaiMatKhau = "";
		if(nhapLaiMatKhau.trim().length() == 0) {
			e_nhapLaiMatKhau = "Vui lòng nhập mật khẩu!";
			check_error = false;
		}
		else if (!matKhau.equals(nhapLaiMatKhau)) {
			e_nhapLaiMatKhau = "Mật khẩu không khớp!";
			check_error = false;
		}

		session.setAttribute("e_nhapLaiMatKhau", e_nhapLaiMatKhau);
		
		// check ngaySinh
		Date ngaySinh = null;
		if(ngaySinhString != null) {  
			ngaySinh = Date.valueOf(ngaySinhString);
		}
		
		// check soDienThoai
		String e_soDienThoai = "";
		Pattern soDienThoaiPattern = Pattern.compile("\\d{10}");
		Matcher soDienThoaiMatcher = soDienThoaiPattern.matcher(soDienThoai);
		if(!soDienThoaiMatcher.matches()) {
			e_soDienThoai = "Vui lòng nhập đủ 10 số!";
			check_error = false;
		}
		session.setAttribute("e_soDienThoai", e_soDienThoai);
		
		// check email
		String e_email = "";
		Pattern emailPattern = Pattern.compile("\\S+@\\w+(\\.\\w+)+(\\.\\w+)*");
		Matcher emailMatcher = emailPattern.matcher(email);
		if(!emailMatcher.matches()) {
			e_email = "Vui lòng nhập đúng email!";
			check_error = false;
		}
		session.setAttribute("e_email", e_email);
		// END CHECK
		
		// Them thuoc tinh vao session
		session.setAttribute("tenDangNhap", tenDangNhap);
		session.setAttribute("matKhau", matKhau);
		session.setAttribute("nhapLaiMatKhau", nhapLaiMatKhau);
		session.setAttribute("tenKhachHang", tenKhachHang);
		session.setAttribute("gioiTinh", gioiTinh);
		session.setAttribute("ngaySinh", ngaySinhString);
		session.setAttribute("diaChi", diaChi);
		session.setAttribute("diaChiMuaHang", diaChiMuaHang);
		session.setAttribute("diaChiNhanHang", diaChiNhanHang);
		session.setAttribute("soDienThoai", soDienThoai);
		session.setAttribute("email", email);
		session.setAttribute("dongYNhanMail", dongYNhanMail);
		
		
		String url = "/khach-hang/XacThucTaiKhoan.jsp";
		// khong đk thanh cong
		if(!check_error) {
			url = "/khach-hang/DangKy.jsp";
		}
		else {
			
			session.setAttribute("loggedIn", true);
			String maKhachHang = tenDangNhap + System.currentTimeMillis() + "";
			session.setAttribute("maKhachHang", maKhachHang);
			KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, maHoaMatKhau, tenDangNhap, gioiTinh, diaChi, diaChiMuaHang, diaChiNhanHang, ngaySinh, soDienThoai, email, dongYNhanMail!=null);
			
			if(khachHangDAO.insert(khachHang) > 0) { 
				
				// cho khachHang vao session hien tai
				session.setAttribute("khachHang", khachHang);
				
				// Day so xac thuc
				String soNgauNhien = SoNgauNhien.getSoNgauNhien();
				
				
				// Lấy thời gian hiện tại cộng thêm 2 phút
				LocalDateTime myDateObj = LocalDateTime.now().plusMinutes(2);

				// Định dạng ngày giờ
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				String thoiGianHieuLucMaXacThuc = myDateObj.format(myFormatObj);
				
				
				// Trang thai xac thuc = false
				boolean trangThaiXacThuc = false;
				
				khachHang.setMaXacThuc(soNgauNhien);
				khachHang.setThoiGianHieuLucMaXacThuc(thoiGianHieuLucMaXacThuc);
				khachHang.setTrangThaiXacThuc(trangThaiXacThuc);
				// update
				if(khachHangDAO.updateVerifyInformation(khachHang) > 0) {
					// Gui email cho khach hang
					Email.sendForClient(email,"Xác thực tài khoản tại Shopee.vn", getNoiDungEmail(khachHang));
				}
				
			}
			
		}
		
		
		RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
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
	
	public static String getNoiDungEmail(KhachHang khachHang) {
		
		String link = "http://localhost:8080/Shopee/khach-hang?hanhDong=XacThuc&maKhachHang="
				+ khachHang.getMaKhachHang() + "&maXacThuc=" + khachHang.getMaXacThuc();
		String noiDung = 
				"<p>SHOPEE xin chào bạn <strong>"+khachHang.getTenKhachHang()+",</strong></p>\r\n"
				+ "<p>Vui lòng xác thực tài khoản của bạn bằng cách nhập mã <strong>"+khachHang.getMaXacThuc()+"</strong> hoặc click trực tiếp vào đường link sau:</p>\r\n"
				+ "<p style=\"text-align: left;\"><a href="+link+">"+link+"</a></p>\r\n"
				+ "<p>[Lưu ý]: Đây là email tự động, <b style=\"color: red;\">VUI LÒNG KHÔNG TRẢ LỜI MAIL NÀY</b></p>\r\n"
				+ "<p>Trân trọng cảm ơn.</p>";
		return noiDung;
	}
	
	private void DoiMatKhau(HttpServletRequest request, HttpServletResponse response) {
		// lay ra session hien tai
		HttpSession session = request.getSession();
		
		
		// lay ra thong tin tu form
		String matKhauHienTai = request.getParameter("matKhauHienTai");
		String matKhauMoi = request.getParameter("matKhauMoi");
		String nhapLaiMatKhauMoi = request.getParameter("nhapLaiMatKhauMoi");

		// khachHangDao
		KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
		
		// ma hoa mat khau
		String maHoaMatKhauHienTai = MaHoa.toSHA1(matKhauHienTai);
		String maHoaMatKhauMoi = MaHoa.toSHA1(matKhauMoi);
		//String maHoaNhapLaiMatKhauMoi = MaHoa.toSHA1(nhapLaiMatKhauMoi);
		
		//Eror
		boolean check_error = true;
		String e_matKhauHienTai = "";
		String e_matKhauMoi = "";
		String e_nhapLaiMatKhauMoi = "";
		
		// lay ra thong tin khach hang hien tai
		KhachHang khachHang = (KhachHang)session.getAttribute("khachHang");
		if(khachHang==null) {
			try {
				response.sendRedirect("ChuaDangNhap.jsp");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			// Neu khach hang da dang nhap
			if(!maHoaMatKhauHienTai.equals(khachHang.getMatKhau())){
				e_matKhauHienTai = "Mật khẩu hiện tại không chính xác!";
				check_error = false;
			}else {
				if(!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
					e_nhapLaiMatKhauMoi = "Mật khẩu nhập lại không khớp!";
					check_error = false;
				}else {
					khachHang.setMatKhau(maHoaMatKhauMoi);
					if(khachHangDAO.changePassword(khachHang)) {
						check_error = true;
					}else {
						check_error = false;
					}
				}
			}
		}
	
		session.setAttribute("e_matKhauHienTai", e_matKhauHienTai);
		session.setAttribute("e_matKhauMoi", e_matKhauMoi);
		session.setAttribute("e_nhapLaiMatKhauMoi", e_nhapLaiMatKhauMoi);
		
		session.setAttribute("matKhauHienTai", matKhauHienTai);
		session.setAttribute("matKhauMoi", matKhauMoi);
		session.setAttribute("nhapLaiMatKhauMoi", nhapLaiMatKhauMoi);
		
		
		String url = "/khach-hang/Success.jsp";
		// khong đk thanh cong
		if(!check_error) {
			url = "/khach-hang/DoiMatKhau.jsp";
		}
		else {
			// thay doi mat khau trong database
			KhachHang khachHangNew = new KhachHang(khachHang.getMaKhachHang(), khachHang.getTenDangNhap(), maHoaMatKhauMoi, khachHang.getTenKhachHang(), khachHang.getGioiTinh(), khachHang.getDiaChi(), khachHang.getDiaChiMuaHang(), khachHang.getDiaChiNhanHang(), khachHang.getNgaySinh(), khachHang.getSoDienThoai(), khachHang.getEmail(), khachHang.isDangKiNhanThongTinEmail(), khachHang.getMaXacThuc(), khachHang.getThoiGianHieuLucMaXacThuc(), khachHang.getTrangThaiXacThuc(), khachHang.getDuongDanAnh());
			khachHangDAO.changePassword(khachHangNew);
		}
		
		// chuyen trang
		request.setAttribute("success", "đổi mật khẩu"); // in ra man hinh đổi mật khẩu thanh cong
		RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
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

	private void ThayDoiThongTin(HttpServletRequest request, HttpServletResponse response) {	
		// tao session
		HttpSession session = request.getSession();
		
		// tao khachHangDao
		KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
		
		// lay thong tin tu form
		String tenKhachHang = request.getParameter("tenKhachHang");
		String listGioiTinh[] = request.getParameterValues("gioiTinh");
		String gioiTinh = "";
		if(listGioiTinh != null) {
			for(String xString : listGioiTinh) {
				if(xString.equals("Nam")) gioiTinh = "Nam";
				else if (xString.equals("Nữ")) {
					gioiTinh = "Nữ";
				}
				else if(xString.equals("Khác")) gioiTinh = "Khác";
			}
		}
		
		String ngaySinhString = request.getParameter("ngaySinh");
		String diaChi = request.getParameter("diaChi");
		String diaChiMuaHang = request.getParameter("diaChiMuaHang");
		String diaChiNhanHang = request.getParameter("diaChiNhanHang");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		String dongYNhanMail = request.getParameter("dongYNhanMail");
		
		
        // bien check error
        boolean check_error = true;
        
        
		// CHECK ERROR
		// check diaChiMuaHang
		String e_diaChiMuaHang = "";
		if(diaChiMuaHang.trim().length() == 0) { 
			e_diaChiMuaHang = "Vui lòng nhập địa chỉ!";
			check_error = false;
		}
		session.setAttribute("e_diaChiMuaHang", e_diaChiMuaHang);
		
		
		// check diaChiNhanHang
		String e_diaChiNhanHang = "";
		if(diaChiNhanHang.trim().length() == 0) { 
			e_diaChiNhanHang = "Vui lòng nhập địa chỉ!";
			check_error = false;
		}
		session.setAttribute("e_diaChiNhanHang", e_diaChiNhanHang);
		
		// check ngaySinh
		Date ngaySinh = null;
		if(ngaySinhString != null) {  
			ngaySinh = Date.valueOf(ngaySinhString);
		}
		
		// check soDienThoai
		String e_soDienThoai = "";
		Pattern soDienThoaiPattern = Pattern.compile("\\d{10}");
		Matcher soDienThoaiMatcher = soDienThoaiPattern.matcher(soDienThoai);
		if(!soDienThoaiMatcher.matches()) {
			e_soDienThoai = "Vui lòng nhập đủ 10 số!";
			check_error = false;
		}
		session.setAttribute("e_soDienThoai", e_soDienThoai);
		
		// check email
		String e_email = "";
		Pattern emailPattern = Pattern.compile("\\S+@\\w+(\\.\\w+)+(\\.\\w+)*");
		Matcher emailMatcher = emailPattern.matcher(email);
		if(!emailMatcher.matches()) {
			e_email = "Vui lòng nhập đúng email!";
			check_error = false;
		}
		session.setAttribute("e_email", e_email);
		// END CHECK
		
		// Them thuoc tinh vao session
		session.setAttribute("tenKhachHang", tenKhachHang);
		session.setAttribute("gioiTinh", gioiTinh);
		session.setAttribute("ngaySinh", ngaySinhString);
		session.setAttribute("diaChi", diaChi);
		session.setAttribute("diaChiMuaHang", diaChiMuaHang);
		session.setAttribute("diaChiNhanHang", diaChiNhanHang);
		session.setAttribute("soDienThoai", soDienThoai);
		session.setAttribute("email", email);
		session.setAttribute("dongYNhanMail", dongYNhanMail);
		
		
		String url = "/khach-hang/Success.jsp";
		// khong đk thanh cong
		if(!check_error) {
			url = "/khach-hang/ThayDoiThongTin.jsp";
		}
		else {
			KhachHang khachHangHienTai = (KhachHang)session.getAttribute("khachHang"); // thong tin cua khach hien tai
			
			KhachHang khachHang = new KhachHang(khachHangHienTai.getMaKhachHang(), "", "", tenKhachHang, gioiTinh, diaChi, diaChiMuaHang, diaChiNhanHang, ngaySinh, soDienThoai, email, dongYNhanMail!=null, khachHangHienTai.getMaXacThuc(), khachHangHienTai.getThoiGianHieuLucMaXacThuc(), khachHangHienTai.getTrangThaiXacThuc(), khachHangHienTai.getDuongDanAnh());
			khachHangDAO.update(khachHang); // update lai thong tin khach vua doi
			
			KhachHang khachHangNew = khachHangDAO.selectByID(khachHangHienTai); // lay ra khach hang vua doi thong tin
			session.setAttribute("khachHang", khachHangNew); // cap nhat thong tin khach hang
			
		}
		
		// chuyen trang
		request.setAttribute("success", "thay đổi thông tin"); // in ra man hinh thay doi thong tin thanh cong
		
		try {
			RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
			rDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void XacThuc(HttpServletRequest request, HttpServletResponse response) {	
		// tao session
		HttpSession session = request.getSession();
		
		// tao khachHangDao
		KhachHangDAO khachHangDAO = KhachHangDAO.getInstance();
		
		// lay thong tin tu form
		String m1 = request.getParameter("1");
		String m2 = request.getParameter("2");
		String m3 = request.getParameter("3");
		String m4 = request.getParameter("4");
		String m5 = request.getParameter("5");
		String m6 = request.getParameter("6");
		String maKhachHang = session.getAttribute("maKhachHang") + "";
		String maXacThuc = m1 + m2 + m3 + m4 + m5 + m6;
		
        // bien check error
        boolean check_error = true;
        
        //Lay thong tin trong database
        KhachHang tmp = new KhachHang();
        tmp.setMaKhachHang(maKhachHang);
        KhachHang khachHang = khachHangDAO.selectByID(tmp);
        
        
        // CHECK ERROR
        String e_maXacThuc = "";
        if(khachHang != null) {
        	if(khachHang.getMaXacThuc().equals(maXacThuc)) {
            	// thanh cong
            	khachHang.setTrangThaiXacThuc(true);
            	
            	
            	// update lai trangThaiXacThuc
            	khachHangDAO.updateVerifyInformation(khachHang);
            	session.setAttribute("khachHang", khachHang);
            }
            else if(maXacThuc.trim().length() == 0) {
            	e_maXacThuc = "Vui lòng nhập mã xác thực!";
            	check_error = false;
            }
            	
            else {
            	// that bai
            	e_maXacThuc = "Mã xác thực không đúng!";
            	check_error = false;	
            }
        }
        else {
			e_maXacThuc = "Tài khoản không tồn tại!";
			check_error = false;
		}
     
			
        session.setAttribute("e_maXacThuc", e_maXacThuc);
		// END CHECK
		
		// trang thai xac thuc OTP
        session.setAttribute("otp", true); // da xac thuc thanh cong
		
		
		String url = "/khach-hang/Success.jsp";
		// khong đk thanh cong
		if(!check_error) {
			url = "/khach-hang/XacThucTaiKhoan.jsp";
		}
		
		// Khoi tao thong tin gio hang
		GioHangDAO gioHangDAO = GioHangDAO.getInstance();
		GioHang gioHang = new GioHang();
		gioHang.setMaGioHang(SoNgauNhien.getSoNgauNhien());
		gioHang.setKhachHang(khachHang);
		gioHangDAO.insert(gioHang);
		
		// chuyen trang		
		try {
			request.setAttribute("success", "đăng ký tài khoản"); // in ra man hinh dang ky tai khoan thanh cong
			RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
			rDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
