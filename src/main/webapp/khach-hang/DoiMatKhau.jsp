<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đổi mật khẩu</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" ></script>
	<style type="text/css">
		.rd{
			color: red;
		}
	</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<%

	String matKhauHienTai = session.getAttribute("matKhauHienTai") != null ? (String)session.getAttribute("matKhauHienTai") : "";
	String matKhauMoi = session.getAttribute("matKhauMoi") != null ? (String)session.getAttribute("matKhauMoi") : "";
	String nhapLaiMatKhauMoi = session.getAttribute("nhapLaiMatKhauMoi") != null ? (String)session.getAttribute("nhapLaiMatKhauMoi") : "";
	
	
	String e_matKhauHienTai = session.getAttribute("e_matKhauHienTai") != null ? session.getAttribute("e_matKhauHienTai").toString() : "";
	String e_matKhauMoi = session.getAttribute("e_matKhauMoi") != null ? session.getAttribute("e_matKhauMoi").toString() : "";
	String e_nhapLaiMatKhauMoi = session.getAttribute("e_nhapLaiMatKhauMoi") != null ? session.getAttribute("e_nhapLaiMatKhauMoi").toString() : "";
	
	KhachHang khachHang = (KhachHang)session.getAttribute("khachHang");
	//boolean loggedIn = (boolean)session.getAttribute("loggedIn");
	if(khachHang == null){
		response.sendRedirect("ChuaDangNhap.jsp");
	}
	else{
		%>
			<div class="container col-4 mt-4">
				<h3 style="text-align: center;">THAY ĐỔI MẬT KHẨU</h3> <br> <br>
				<form action="../khach-hang-controller" method="post">
					<input type="hidden" name="hanhDong" value="DoiMatKhau">
					<div class="mb-3">
						<label for="matKhauHienTai" class="form-label">Mật khẩu hiện tại<span class="rd"> (*)</span></label> 
						<input type="password" class="form-control" id="matKhauHienTai" name="matKhauHienTai" value="<%=matKhauHienTai %>"  required>
						<div class="rd"><%=e_matKhauHienTai %></div>
					</div>
					<div class="mb-3">
						<label for="matKhauMoi" class="form-label">Mật khẩu mới<span class="rd"> (*)</span></label>
						<input type="password" class="form-control" id="matKhauMoi" name="matKhauMoi" value="<%=matKhauMoi %>" required>
						<div class="rd"><%=e_matKhauMoi %></div>
					</div>
					<div class="mb-3">
						<label for="nhapLaiMatKhauMoi" class="form-label">Nhập lại mật khẩu mới<span class="rd"> (*)</span> <span class="rd" id="msg"></span></label>
						<input type="password" class="form-control" id="nhapLaiMatKhauMoi" name="nhapLaiMatKhauMoi" required value="<%=nhapLaiMatKhauMoi %>" onkeyup="kiemTraLaiMatKhau()">
						<div class="rd"><%=e_nhapLaiMatKhauMoi %></div>
					</div>
					<div class="row">
						<button type="submit" class="btn btn-primary">Lưu mật khẩu</button>
					</div>
					
				</form>
			</div>
		<%
	}
	%>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	function kiemTraLaiMatKhau() {
	    matKhau = document.getElementById("matKhauMoi").value;
	    nhapLaiMatKhau = document.getElementById("nhapLaiMatKhauMoi").value;
	    if (matKhau != nhapLaiMatKhau) {
	        document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
	        return false;
	    } else {
	        document.getElementById("msg").innerHTML = ""; // Clear the message if passwords match
	    }
	}
</script>
</html>