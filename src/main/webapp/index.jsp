<%@page import="java.util.ArrayList"%>
<%@page import="model.ThuongHieu"%>
<%@page import="dao.ThuongHieuDAO"%>
<%@page import="model.ThoiTrang"%>
<%@page import="dao.ThoiTrangDAO"%>
<%@page import="model.KhachHang"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TRANG CHỦ</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css" />
</head>
<body>
	
	<%
	
	// dinh dang so tien
	DecimalFormat df = new DecimalFormat("0.000"); // Định dạng 3 chữ số sau dấu phẩy
		
	// lay thong tin san pham tu database
	ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
	ThuongHieuDAO thuongHieuDAO = ThuongHieuDAO.getInstance();
	
	ArrayList<ThoiTrang> productList = new ArrayList<ThoiTrang>();
	productList = thoiTrangDAO.selectAll();
	
	%>

	<!------------------------------------------------- HEADER --------------------------------------------------------->
	<jsp:include page="./header.jsp"></jsp:include>
	<!----------------------------------------------- END HEADER ------------------------------------------------------->
	
	
	

	<!---------------------------------------------- PAGE CONTENT ------------------------------------------------------>
	<div class="container text-center">
		<div class="row">
		
		
	<!----------------------------------------------- MENU LEFT -------------------------------------------------------->
		<%@include file="left.jsp" %>
	<!--------------------------------------------- END MENU LEFT ------------------------------------------------------>
	
	
		
	<!------------------------------------------- Slider And Product --------------------------------------------------->	
			<div class="col-lg-9">
				<div id="carouselExampleIndicators" class="carousel slide">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1">
						</button>
						
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="1" aria-label="Slide 2">
						</button>
						
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="2" aria-label="Slide 3">
						</button>						
						
					</div>
					
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="img/slider/slider1.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/slider/slider2.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/slider/slider3.png" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					
					<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
	<!---------------------------------------------- Product ------------------------------------------------------------>
				<div class="row">
				<%
				for(ThoiTrang thoiTrang : productList){
					ThuongHieu thuongHieu = thuongHieuDAO.selectByID(thoiTrang.getThuongHieu());
					%>
					<div class="col-lg-3 col-md-6 mb-4">
						<div class="card h-100">
							<a href="${pageContext.request.contextPath }/san-pham/ChiTietSanPham.jsp?maThoiTrang=<%= thoiTrang.getMaThoiTrang() %>"><img class="card-img-top" src="img/product/shirt/<%=thoiTrang.getLinkAnh() %>" alt=""></a>
							<div class="card-body">
								<p class="text-ellipsis">
									<%=thoiTrang.getTenThoiTrang() %>
								</p>
								<p class="rd"><%=df.format(thoiTrang.getGiaBan()) %> đ</p>
								
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
								  	<path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10m0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6"/>
								  	<span> <%=thuongHieu.getNoiBan() %></span>
								</svg>
								
								<p>Đã bán <%=thoiTrang.getDaBan() %></p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
							</div>
						</div>
					</div>		
					<% 
					
				}
				%>
				</div>
	<!------------------------------------------------- Product -------------------------------------------------------->
	
	<!------------------------------------------ End Slider And Product ------------------------------------------------>
			</div>
		</div>
	</div>
	<!--------------------------------------------- END PAGE CONTENT --------------------------------------------------->
	
	
	
	<!------------------------------------------------ PAGINATION ------------------------------------------------------>
	<div class="container col-1">
		<nav aria-label="...">
			<ul class="pagination">
				<li class="page-item">
					<a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a>
				</li>
				
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item active" aria-current="page"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				
				<li class="page-item">
					<a class="page-link" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
				</li>
			</ul>
		</nav>
	</div>
	
	<!--------------------------------------------- END PAGINATION ----------------------------------------------------->
	
	
	
	<!------------------------------------------------- FOOTER --------------------------------------------------------->
	<%@include file="footer.jsp" %>
	<!----------------------------------------------- END FOOTER ------------------------------------------------------->
	
</body>	
</html>