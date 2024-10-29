<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đăng ký tài khoản</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" ></script>
	<style type="text/css">
		.rd{
			color:red;
		}
		.text-center{
			text-align: center;
		}
		
		
	</style>
	<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<%
	
	String tenDangNhap = session.getAttribute("tenDangNhap") != null ? (String)session.getAttribute("tenDangNhap") : "";
	String matKhau = session.getAttribute("matKhau") != null ? (String)session.getAttribute("matKhau") : "";
	String nhapLaiMatKhau = session.getAttribute("nhapLaiMatKhau") != null ? (String)session.getAttribute("nhapLaiMatKhau") : "";
	String tenKhachHang = session.getAttribute("tenKhachHang") != null ? (String)session.getAttribute("tenKhachHang") : "";
	String gioiTinh = session.getAttribute("gioiTinh") != null ? (String)session.getAttribute("gioiTinh") : "";
	String ngaySinh = session.getAttribute("ngaySinh") != null ? (String)session.getAttribute("ngaySinh") : "";
	String diaChi = session.getAttribute("diaChi") != null ? (String)session.getAttribute("diaChi") : "";
	String diaChiMuaHang = session.getAttribute("diaChiMuaHang") != null ? (String)session.getAttribute("diaChiMuaHang") : "";
	String diaChiNhanHang = session.getAttribute("diaChiNhanHang") != null ? (String)session.getAttribute("diaChiNhanHang") : "";
	String soDienThoai = session.getAttribute("soDienThoai") != null ? (String)session.getAttribute("soDienThoai") : "";
	String email = session.getAttribute("email") != null ? (String)session.getAttribute("email") : "";
	Boolean dongYNhanMail = session.getAttribute("dongYNhanMail") != null ? (Boolean)session.getAttribute("dongYNhanMail") : false;
	
	
	String e_tenDangNhap = session.getAttribute("e_tenDangNhap") != null ? session.getAttribute("e_tenDangNhap").toString() : "";
	String e_matKhau = session.getAttribute("e_matKhau") != null ? session.getAttribute("e_matKhau").toString() : "";
	String e_nhapLaiMatKhau = session.getAttribute("e_nhapLaiMatKhau") != null ? session.getAttribute("e_nhapLaiMatKhau").toString() : "";
	String e_tenKhachHang = session.getAttribute("e_tenKhachHang") != null ? session.getAttribute("e_tenKhachHang").toString() : "";
	String e_diaChi = session.getAttribute("e_diaChi") != null ? session.getAttribute("e_diaChi").toString() : "";
	String e_diaChiMuaHang = session.getAttribute("e_diaChiMuaHang") != null ? session.getAttribute("e_diaChiMuaHang").toString() : "";
	String e_diaChiNhanHang = session.getAttribute("e_diaChiNhanHang") != null ? session.getAttribute("e_diaChiNhanHang").toString() : "";
	String e_soDienThoai = session.getAttribute("e_soDienThoai") != null ? session.getAttribute("e_soDienThoai").toString() : "";
	String e_email = session.getAttribute("e_email") != null ? session.getAttribute("e_email").toString() : "";
	%>
	
	<div class="text-center">
		<h2>ĐĂNG KÝ TÀI KHOẢN</h2> <br> <br>
		 
	</div>
	<div class="container">
		<form class="row g-3 needs-validation" action="../khach-hang-controller" method="post">
		<input type="hidden" name="hanhDong" value="DangKy">
			<div class="row">
				<div class="col-md-6">
					<h3>Tài khoản</h3>
					<div class="col-md-12">
						<label for="tenDangNhap" class="form-label">Tên đăng nhập<span class="rd"> (*)</span></label>
						<input type="text" class="form-control" id="tenDangNhap" name="tenDangNhap" value="<%=tenDangNhap %>" required>
						<div class="rd"><%=e_tenDangNhap %></div>
					</div>
					<div class="col-md-12">
						<label for="matKhau" class="form-label">Mật khẩu<span class="rd"> (*)</span></label>
						<input type="password" class="form-control" id="matKhau" name="matKhau" value="<%=matKhau %>" required>
						<div class="rd"><%=e_matKhau %></div>
					</div>
					<div class="col-md-12">
						<label for="nhapLaiMatKhau" class="form-label">Nhập lại mật khẩu<span class="rd"> (*)</span> <span class="rd" id="msg"></span></label>
						<input type="password" class="form-control" id="nhapLaiMatKhau" name="nhapLaiMatKhau" value="<%=nhapLaiMatKhau %>" required onkeyup="kiemTraLaiMatKhau()">
						<div class="rd"><%=e_nhapLaiMatKhau %></div>
					</div>
					<hr>	
					<h3>Thông tin khách hàng</h3>
					<div class="col-md-12">
						<label for="tenKhachHang" class="form-label">Họ Và Tên</label>
						<input type="text" class="form-control" id="tenKhachHang" name="tenKhachHang" value="<%=tenKhachHang %>">
						<div class="rd"><%=e_tenKhachHang %></div>
					</div>
					<div class="col-md-12">
					<label for="gioiTinh" class="form-label" >Giới tính</label>
						<select class="form-select" aria-label="Default select example" name="gioiTinh">
						  <option selected></option>
						  	<option value="Nam" <%=(gioiTinh.equals("Nam"))?"selected='selected'":"" %> >Nam</option>
							<option value="Nữ" <%=(gioiTinh.equals("Nữ"))?"selected='selected'":"" %> >Nữ</option>
							<option value="Khác" <%=(gioiTinh.equals("Khác"))?"selected='selected'":"" %> >Khác</option>
						</select>
					</div>
					<div class="col-md-12">
						<label for="ngaySinh" class="form-label">Ngày sinh</label>
						<input type="date" class="form-control" id="ngaySinh" name="ngaySinh" value="<%=ngaySinh %>">
					</div>
				</div>
				
				<div class="col-md-6">
					<h3>Địa chỉ</h3>
					<div class="col-md-12">
						<label for="diaChi" class="form-label">Địa chỉ khách hàng</label>
						<input type="text" class="form-control" id="diaChi" name="diaChi" value="<%=diaChi %>">
						<div class="rd"><%=e_diaChi %></div>
					</div>
					<div class="col-md-12">
						<label for="diaChiMuaHang" class="form-label">Địa chỉ mua hàng<span class="rd"> (*)</span></label>
						<input type="text" class="form-control" id="diaChiMuaHang" name="diaChiMuaHang" value="<%=diaChiMuaHang %>" required>
						<div class="rd"><%=e_diaChiMuaHang %></div>
					</div>
					<div class="col-md-12">
						<label for="diaChiNhanHang" class="form-label">Địa chỉ nhận hàng<span class="rd"> (*)</span></label>
						<input type="text" class="form-control" id="diaChiNhanHang" name="diaChiNhanHang" value="<%=diaChiNhanHang %>" required>
						<div class="rd"><%=e_diaChiNhanHang %></div>
					</div>
					<div class="col-md-12">
						<label for="soDienThoai" class="form-label">Số điện thoại<span class="rd"> (*)</span></label>
						<input type="tel" class="form-control" id="soDienThoai" name="soDienThoai" value="<%=soDienThoai %>" required>
						<div class="rd"><%=e_soDienThoai %></div>
					</div>
					<div class="col-md-12">
						<label for="email" class="form-label">Email<span class="rd"> (*)</span></label>
						<input type="email" class="form-control" id="email" name="email" value="<%=email%>" required>
						<div class="rd"><%=e_email %></div>
					</div>
					<hr>
					<div class="col-md-12">
						<label for="dongYDieuKhoan" class="form-label">Đồng ý với điều khoản của công ty<span class="rd"> (*)</span></label>
						<input type="checkbox" class="form-check-input" id="dongYDieuKhoan" name="dongYDieuKhoan" value="" required onchange="xuLyChonTraDongY()">
						<div class="rd"></div>
					</div>
					<div class="col-md-12">
						<label for="dongYNhanMail" class="form-label">Đồng ý nhận mail</label>
						<input type="checkbox" class="form-check-input" id="dongYNhanMail" name="dongYNhanMail" <%=dongYNhanMail ? "checked":"" %>>
						<div class="rd"></div>
					</div>
					<div class="g-recaptcha" data-sitekey="6LdVskYqAAAAAKGYiMmiDl1dHXGkYN1MOM6tdAOt"></div>
					
					<div class="row">
						<label class="form-label"></label>
						<button class="btn btn-primary" type="submit" id="submit" style="margin-left:15px; visibility: hidden;">Submit form</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<br> <br> <br>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function kiemTraLaiMatKhau() {
	    matKhau = document.getElementById("matKhau").value;
	    nhapLaiMatKhau = document.getElementById("nhapLaiMatKhau").value;
	    if (matKhau != nhapLaiMatKhau) {
	        document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
	        return false;
	    } else {
	        document.getElementById("msg").innerHTML = ""; // Clear the message if passwords match
	    }
	}
	
	function xuLyChonTraDongY(){
		dongYDieuKhoan = document.getElementById("dongYDieuKhoan");
		if(dongYDieuKhoan.checked == true)
			document.getElementById("submit").style.visibility="visible";
		else
			document.getElementById("submit").style.visibility="hidden";
	}


</script>
</html>