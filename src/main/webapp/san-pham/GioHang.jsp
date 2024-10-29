<%@page import="dao.GioHangDAO"%>
<%@page import="dao.ChiTietGioHangDAO"%>
<%@page import="dao.TheLoaiDAO"%>
<%@page import="model.TheLoai"%>
<%@page import="model.ChiTietGioHang"%>
<%@page import="model.GioHang"%>
<%@page import="model.KhachHang"%>
<%@page import="dao.KhachHangDAO"%>
<%@page import="model.ThoiTrang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Giỏ Hàng</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" ></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css" />
</head>
<body>
	
	<%
		KhachHang khachHang = (KhachHang)session.getAttribute("khachHang");
		GioHangDAO gioHangDAO = GioHangDAO.getInstance();
		GioHang gioHang = new GioHang();
		gioHang.setKhachHang(khachHang);
		gioHang = gioHangDAO.selectByID(gioHang);
		
		ChiTietGioHangDAO chiTietGioHangDAO = ChiTietGioHangDAO.getInstance();
		ChiTietGioHang chiTietGioHang = new ChiTietGioHang();
		chiTietGioHang.setGioHang(gioHang);
		
		
		if(khachHang == null){
			response.sendRedirect("khach-hang/DangNhap.jsp");
			return;
		} 
		

		// dinh dang so tien
		DecimalFormat df = new DecimalFormat("0.000"); // Định dạng 3 chữ số sau dấu phẩy
		
		ArrayList<ThoiTrang> gioHangs = (ArrayList<ThoiTrang>) session.getAttribute("gioHangs");
		if(gioHangs == null || gioHangs.isEmpty()){
			response.sendRedirect("KhongSanPham.jsp");
			return;
		}
		
		int soLuongSanPham = gioHangs.size();
		double tongTien = 0;
	%>
	<!------------------------------------------------- HEADER --------------------------------------------------------->
	<jsp:include page="../header.jsp"></jsp:include>
	<!----------------------------------------------- END HEADER ------------------------------------------------------->


	<!---------------------------------------------- PAGE CONTENT ------------------------------------------------------>
		
	
	<section class="h-100 h-custom" style="background-color: #e7e6eb;">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12">
					<div class="card card-registration card-registration-2" style="border-radius: 15px;">
						<div class="card-body p-0">
							<div class="row g-0">
								<div class="col-lg-9">
									<div class="p-5">
										<div class="d-flex justify-content-between align-items-center mb-5">
											<h2 class="fw-bold mb-0">Giỏ hàng</h2>
											<h6 class="mb-0 text-muted"><%=soLuongSanPham %> sản phẩm</h6>
										</div>
										
										<div class="row mb-2 d-flex justify-content-between align-items-center">
											<div class="col-md-2 col-lg-2 col-xl-2">
												<h6>Sản phẩm</h6>
											</div>
											<div class="col-md-3 col-lg-3 col-xl-3">
												
											</div>
											<div class="col-md-3 col-lg-3 col-xl-2 d-flex">
												<h6>Số lượng</h6>
											</div>
											<div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
												<h6>Số tiền</h6>
											</div>
											<div class="col-md-1 col-lg-1 col-xl-1 text-end">
												<h6>Xóa</h6>
											</div>
										</div>
										
										<hr class="my-4">
		<!----------------------------------- dung vong lap de bieu dien san pham ------------------------------------->
										<%
										
										for(ThoiTrang thoiTrang : gioHangs){
											TheLoaiDAO theLoaiDAO = TheLoaiDAO.getInstance();
											
											chiTietGioHang.setThoiTrang(thoiTrang);
											chiTietGioHang = chiTietGioHangDAO.selectByID(chiTietGioHang);
											
											TheLoai theLoai = new TheLoai();
											theLoai.setMaTheLoai(thoiTrang.getTheLoai().getMaTheLoai());
											
											theLoai = theLoaiDAO.selectByID(theLoai);
											
											tongTien += thoiTrang.getGiaBan()*chiTietGioHang.getSoLuong();
											%>
											<div class="row mb-4 d-flex justify-content-between align-items-center">
												<div class="col-md-2 col-lg-2 col-xl-2">
													<img src="${pageContext.request.contextPath}/img/product/shirt/<%=thoiTrang.getLinkAnh() %>"
														class="img-fluid rounded-3" alt="Cotton T-shirt">
												</div>
												<div class="col-md-3 col-lg-3 col-xl-3">
													<h6 class="text-muted"><%=theLoai.getTenTheLoai() %></h6>
													<h6 class="mb-0 text-ellipsis"><%=thoiTrang.getTenThoiTrang() %></h6>
												</div>
												
												<div class="col-md-3 col-lg-3 col-xl-2 d-flex">
												    <!-- Nút trừ -->
												    <button type="button" class="btn btn-link px-2" 
												            onclick="this.parentNode.querySelector('input[type=number]').stepDown(); updatePrice(this.parentNode.querySelector('input[type=number]'));">
												        <i class="bi bi-dash"></i>
												    </button>
												
												    <!-- Ô nhập số lượng sản phẩm -->
												    <input id="form1" min="1" name="quantity" value="<%=chiTietGioHang.getSoLuong() %>"
												        type="number" class="form-control form-control-sm text-center mx-2" 
												        data-price="<%=thoiTrang.getGiaBan() %>" data-product-id="<%=thoiTrang.getMaThoiTrang() %>" 
												        onchange="updatePrice(this)" />
												
												    <!-- Nút cộng -->
												    <button type="button" class="btn btn-link px-2" 
												            onclick="this.parentNode.querySelector('input[type=number]').stepUp(); updatePrice(this.parentNode.querySelector('input[type=number]'));">
												        <i class="bi bi-plus-lg"></i>
												    </button>
												</div>
												
												<div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
												    <h6 class="mb-0 product-total"><%=df.format(thoiTrang.getGiaBan()*chiTietGioHang.getSoLuong()) %> Đ</h6>
												</div>

												<div class="col-md-1 col-lg-1 col-xl-1 text-end">
													<a href="${pageContext.request.contextPath}/chi-tiet-san-pham?maThoiTrang=<%= thoiTrang.getMaThoiTrang() %>&hanhDong=xoaSanPham" class="text-muted">
														<i class="bi bi-x-lg"></i>
													</a>
												</div>
											</div>
	
											<hr class="my-4">
											<%
										}
										%>
									</div>
	<!----------------------------------- dung vong lap de bieu dien san pham ------------------------------------->
								</div>
								<div class="col-lg-3 bg-body-tertiary">
									<div class="p-5">
										<h4 class="fw-bold mb-5 mt-2 pt-1">Tóm tắt</h4>
										<hr class="my-4">

										<h6 class="text-uppercase mb-3">Mã giảm giá</h6>

										<div class="mb-5">
											<div data-mdb-input-init class="form-outline">
												<input type="text" id="form3Examplea2"
													class="form-control form-control-lg" /> <label
													class="form-label" for="form3Examplea2">Nhập mã của bạn</label>
											</div>
										</div>

										<hr class="my-4">

										<div class="d-flex justify-content-between mb-5">
											<h6 class="text-uppercase">Tổng tiền</h6>
											<h6 id="totalPrice"><%=df.format(tongTien) %> Đ</h6>
										</div>

										<button type="button" data-mdb-button-init
											data-mdb-ripple-init class="btn btn-danger btn-block btn-lg"
											data-mdb-ripple-color="#f6733d">Mua hàng</button>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!--------------------------------------------- END PAGE CONTENT --------------------------------------------------->

	<!------------------------------------------------- FOOTER --------------------------------------------------------->
	<%@include file="../footer.jsp" %>
	<!----------------------------------------------- END FOOTER ------------------------------------------------------->
	
</body>
</html>