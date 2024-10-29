<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    
</head>
<body>
	<%
	
	String tenDangNhap = session.getAttribute("tenDangNhap") != null ? (String)session.getAttribute("tenDangNhap") : "";
	String matKhau = session.getAttribute("matKhau") != null ? (String)session.getAttribute("matKhau") : "";
	
	String e_baoLoi = session.getAttribute("e_baoLoi") != null ? (String)session.getAttribute("e_baoLoi") : "";
	%>
    <div class="login-container">
        <div class="login-box">
        <img src="${pageContext.request.contextPath}/img/logo_dangNhap.png" alt="Logo" class="logo">
            <h2>Đăng Nhập</h2>
            <form action="${pageContext.request.contextPath }/khach-hang-controller" method="POST">
            	<input type="hidden" name="hanhDong" value="DangNhap">
                <div class="textbox">
                    <input type="text" placeholder="Tên đăng nhập" name="tenDangNhap" value="<%=tenDangNhap %>" required>
                    <div class="rd"><%=e_baoLoi %></div>
                </div>
                
                
                <div class="textbox">
                    <input type="password" placeholder="Mật khẩu" name="matKhau"value="<%=matKhau %>" required>
                    <div class="rd"><%=e_baoLoi %></div>
                </div>
                
                
                <input type="checkbox" id="nhoThongTin" name="nhoThongTin" value="">
                <label for="nhoThongTin">Lưu tài khoản này</label> <br> <br>
                
                <button type="submit" class="btn">Đăng Nhập</button>
                
                <p class="forgot-password"><a href="#">Quên mật khẩu?</a></p>
            </form>
            <p class="signup-link">Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/khach-hang/DangKy.jsp">Đăng ký</a></p>
        </div>
    </div>
</body>
</html>