<%@page import="java.sql.Date"%>
<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  	<meta charset="UTF-8">
  	<title>Thay đổi avatar</title>
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
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<%
	
	KhachHang khachHang = (KhachHang)session.getAttribute("khachHang");
	
	if(khachHang == null){
		response.sendRedirect("ChuaDangNhap.jsp");
	}
	else{
		String duongDanAnh = khachHang.getDuongDanAnh() != null ? khachHang.getDuongDanAnh().toString() : "";
		if(duongDanAnh.equals("")) duongDanAnh = "avatar.jpg";
		%>
		<div class="text-center" >
			<h2>THAY ĐỔI AVATAR</h2> <br> <br>
			<div class="container col-3">			
				<form class="row g-3 needs-validation" action="${pageContext.request.contextPath }/thay-doi-avatar" method="post" enctype="multipart/form-data">	
					<img src="${pageContext.request.contextPath }/avatar/<%=duongDanAnh %>" width="200" height="300" alt="Avatar">
					<div class="col-md-12">
						<label for="duongDanAnh" class="form-label">Đường dẫn ảnh</label>
						<input type="file" class="form-control" id="duongDanAnh" name="duongDanAnh" >					
					</div>
					
					<div class="row">
						<label class="form-label"></label>
						<button class="btn btn-primary" type="submit" id="submit" style="margin-left:15px">Lưu ảnh</button>
					</div>
				</form>
			</div>
		</div>
		<%
	}
	
	%>
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>
