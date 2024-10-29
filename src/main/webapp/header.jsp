<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" ></script>
</head>
<body>
	<%
		// bien check xem da dang nhap thanh cong chua
		Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
		KhachHang khachHang = (KhachHang)session.getAttribute("khachHang");
		String tenDangNhap = "";
		String duongDanAnh = "";
		if(khachHang != null){
			tenDangNhap = khachHang.getTenDangNhap() != null ? khachHang.getTenDangNhap().toString() : "";
			duongDanAnh = khachHang.getDuongDanAnh() != null ? khachHang.getDuongDanAnh().toString() : "";
			if(duongDanAnh.equals("")) duongDanAnh = "avatar.jpg";
		}
	
	%>
	<!--------------------------------------------------------------------- NAVBAR -------------------------------------------------------------------------->
	<nav class="py-2 bg-body-tertiary">
    	<div class="container d-flex flex-wrap">
	      	<ul class="nav me-auto">
	        	<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></li>
				<li class="nav-item"><a class="nav-link" href="">Compo giảm giá</a></li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> Thể loại </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="https://animetq.com/hoat-hinh-3d">Áo</a></li>
						<li><a class="dropdown-item" href="https://animetq.com/hoat-hinh-2d">Quần</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="https://animetq.com/movieova">Thể loại khác</a></li>
					</ul>
				</li>
	      </ul>
      <%
		// neu chua dang nhap thi moi hien o dang ki
		if(loggedIn == null || !loggedIn){
			%>
			<a href="${pageContext.request.contextPath}/khach-hang/DangNhap.jsp" class="btn btn-outline-secondary" style="white-space: nowrap; margin-right:10px; margin-left:20px">Đăng Nhập</a>
			<a href="${pageContext.request.contextPath}/khach-hang/DangKy.jsp" class="btn btn-outline-success" style="white-space: nowrap;">Đăng Ký</a>
			<% 
		}
		else{
			%>
			
			<main class="d-flex flex-nowrap">
				<div class="d-flex flex-column flex-shrink-0 bg-body-tertiary" style="width: 200px;">
					<div class="dropdown">
						<a href="#" class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" > 
							<img src="${pageContext.request.contextPath}/avatar/<%=duongDanAnh %>" alt="Avatar" width="32" height="32" class="rounded-circle me-2"> 
							<strong><%=tenDangNhap %></strong>
						</a>
						<ul class="dropdown-menu text-small shadow">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ThongTinTaiKhoanServlet">Thông tin tài khoản</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/khach-hang/ThayDoiAvatar.jsp">Thay đổi avatar</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/khach-hang/ThayDoiThongTin.jsp">Thay đổi thông tin</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/khach-hang/DoiMatKhau.jsp">Đổi mật khẩu</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/san-pham/DonMua.jsp">Đơn mua</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/khach-hang-controller?hanhDong=DangXuat">Đăng xuất</a></li>
						</ul>
					</div>
				</div>
			</main>
			
		<%
		}
		%>
			
    	</div>
  	</nav>
  	<header class="pb-3 mb-4 border-bottom">
    	<div class="container d-flex flex-wrap justify-content-center col">
      		<a href="${pageContext.request.contextPath}/index.jsp" class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
        		<img src="${pageContext.request.contextPath}/img/product/logo.jpg" alt="Logo" height="42">
     		</a>
	      	<form class="col-5 mb-3 mb-lg-0" role="search">
	        	<input type="search" class="form-control" placeholder="Tìm kiếm..." aria-label="Search">
	      	</form>
	      	
	      	<a href="${pageContext.request.contextPath}/chi-tiet-san-pham?hanhDong=layGioHang">
	      		<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
				  	<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
				</svg>
	      	</a>
	      	
    	</div>
    	
  	</header>
  <!--------------------------------------------------------------------------- NAVBAR ----------------------------------------------------------------------->
</body>
</html>