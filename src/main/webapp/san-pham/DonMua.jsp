<%@page import="model.KhachHang"%>
<%@page import="dao.ThuongHieuDAO"%>
<%@page import="model.ThuongHieu"%>
<%@page import="dao.ThoiTrangDAO"%>
<%@page import="model.ThoiTrang"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dao.ChiTietDonHangDAO"%>
<%@page import="model.ChiTietDonHang"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn mua</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles2.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" ></script>
</head>
<body>
	<%
	KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
	if(khachHang == null){
		response.sendRedirect("khach-hang/DangNhap.jsp");
	}
	ChiTietDonHangDAO chiTietDonHangDAO = ChiTietDonHangDAO.getInstance();
	ThoiTrangDAO thoiTrangDAO = ThoiTrangDAO.getInstance();
	ThuongHieuDAO thuongHieuDAO = ThuongHieuDAO.getInstance();
	ArrayList<ChiTietDonHang> chiTietDonHangs = chiTietDonHangDAO.selectAll();
	
	DecimalFormat df = new DecimalFormat("0.000");
	%>

	<!------------------------------------------------- HEADER --------------------------------------------------------->
	<jsp:include page="../header.jsp"></jsp:include>
	<!----------------------------------------------- END HEADER ------------------------------------------------------->
	
	<%
	for(ChiTietDonHang tmp : chiTietDonHangs){
		ThoiTrang thoiTrang = thoiTrangDAO.selectByID(tmp.getThoiTrang());
		ThuongHieu thuongHieu = thuongHieuDAO.selectByID(thoiTrang.getThuongHieu());
		
		%>
		<div class="order-container">
		    <!-- Thông tin cửa hàng và trạng thái đơn hàng -->
		    <div class="shop-info">
		        <button class="favorite">❤️ Yêu thích</button>
		        <span class="shop-name"><%=thuongHieu.getTenThuongHieu() %></span>
		        <div class="shop-actions">
		            <button class="chat-btn">💬 Chat</button>
		            <button class="shop-btn">🏪 Xem Shop</button>
		        </div>
		        <span class="order-status">✔ HOÀN THÀNH</span>
		    </div>
		
		    <!-- Thông tin sản phẩm -->
		    <div class="product-info">
		        <img src="${pageContext.request.contextPath}/img/product/shirt/<%=thoiTrang.getLinkAnh() %>" alt="Sản phẩm" class="product-image">
		        <div class="product-details">
		            <h6><%=thoiTrang.getTenThoiTrang() %></h6>
		            <p class="category">Phân loại hàng: Mơ ngừa mụn</p>
		            <p class="quantity">Số lượng: <strong><%=tmp.getSoLuong() %></strong></p>
		            <button class="return-policy">🛡 Trả hàng miễn phí 15 ngày</button>
		        </div>
		        <div class="product-price">
		            <p class="original-price">₫<%=df.format(tmp.getGiaBan() + 30) %></p>
		            <p class="discounted-price">₫<%=df.format(tmp.getGiaBan()) %></p>
		        </div>
		    </div>
		
		    <!-- Tổng tiền -->
		    <div class="total-price">
		        <p>Thành tiền: <span>₫<%=df.format(tmp.getGiaBan() + tmp.getGiamGia()*tmp.getGiaBan() - tmp.getGiaBan()*tmp.getThueVat()) %></span></p>
		    </div>
		
		    <!-- Các dòng chữ và nút hành động -->
		    <div class="action-section">
		        <div class="additional-info">
		            <p>Đánh giá sản phẩm trước <span>12-10-2024</span></p>
		            <p>Đánh giá ngay và nhận <strong>200 Xu</strong></p>
		        </div>
		        <div class="action-buttons">
		            <button class="review-btn">⭐ Đánh Giá</button>
		            <button class="contact-seller-btn">📞 Liên Hệ Người Bán</button>
		            <button class="buy-again-btn">🔁 Mua Lại</button>
		        </div>
		    </div>
		</div>
		
		<%
	}
	%>
    
	
	<!------------------------------------------------- FOOTER --------------------------------------------------------->
	<%@include file="../footer.jsp" %>
	<!----------------------------------------------- END FOOTER ------------------------------------------------------->
	
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        const reviewBtn = document.querySelector('.review-btn');
        const contactSellerBtn = document.querySelector('.contact-seller-btn');
        const buyAgainBtn = document.querySelector('.buy-again-btn');

        reviewBtn.addEventListener('click', function() {
            alert('Bạn sẽ được chuyển đến trang đánh giá!');
        });

        contactSellerBtn.addEventListener('click', function() {
            alert('Liên hệ với người bán qua chat!');
        });

        buyAgainBtn.addEventListener('click', function() {
            alert('Sản phẩm đã được thêm vào giỏ hàng để mua lại!');
        });
    });

    </script>
</body>
</html>