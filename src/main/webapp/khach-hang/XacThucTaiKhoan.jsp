<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/otp.css" />
	<title>OTP</title>
</head>
<body>
	<%
	String e_maXacThuc = session.getAttribute("e_maXacThuc") != null ? session.getAttribute("e_maXacThuc").toString() : "";
	KhachHang khachHang = (KhachHang)session.getAttribute("khachHang");
	if(khachHang == null){
		response.sendRedirect("ChuaDangNhap.jsp");
	}
	else{
		%>
		<div class="container">
			<form action="${pageContext.request.contextPath}/khach-hang-controller" method="post">
				<input type="hidden" name="hanhDong" value="XacThuc">
				<div class="text-center">
					<h2><b>Nhập OTP</b></h2>
					<h4>Vui lòng nhập mã otp</h4>
					<div style="color: red"><%=e_maXacThuc %></div>	
				</div>
		        <div id="inputs" class="inputs">
		            <input class="input" type="text" inputmode="numeric" name="1" maxlength="1" />
		            <input class="input" type="text" inputmode="numeric" name="2" maxlength="1" />
		            <input class="input" type="text" inputmode="numeric" name="3" maxlength="1" />
		            <input class="input" type="text" inputmode="numeric" name="4" maxlength="1" />
		            <input class="input" type="text" inputmode="numeric" name="5" maxlength="1" />
		            <input class="input" type="text" inputmode="numeric" name="6" maxlength="1" />
		        </div>
		        <div class="col-12">
				    <button class="button" type="submit">Submit form</button>
				</div>
			</form>
			
	    </div>
		<%
	}
	%>
	
</body>

<script type="text/javascript">
	const inputs = document.getElementById("inputs");
	
	inputs.addEventListener("input", function (e) {
	    const target = e.target;
	    const val = target.value;
	
	    if (isNaN(val)) {
	        target.value = "";
	        return;
	    }
	
	    if (val != "") {
	        const next = target.nextElementSibling;
	        if (next) {
	            next.focus();
	        }
	    }
	});
	
	inputs.addEventListener("keyup", function (e) {
	    const target = e.target;
	    const key = e.key.toLowerCase();
	
	    if (key == "backspace" || key == "delete") {
	        target.value = "";
	        const prev = target.previousElementSibling;
	        if (prev) {
	            prev.focus();
	        }
	        return;
	    }
	});
</script>
</html>