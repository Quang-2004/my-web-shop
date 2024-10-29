<%@page import="dao.KhachHangDAO"%>
<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập thành công</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #74ebd5, #ACB6E5);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 350px;
            width: 100%;
            -webkit-box-shadow: -1.5px 2px 17px 17px #c7c2c2;
			-moz-box-shadow: -1.5px 2px 17px 17px #c7c2c2;
			box-shadow: -1.5px 2px 17px 17px #c7c2c2;
        }
        h1 {
            color: #4CAF50;
            font-size: 28px;
            margin-bottom: 10px;
        }
        p {
            font-size: 18px;
            color: #555;
            margin-bottom: 20px;
        }
        .button {
            display: inline-block;
            padding: 12px 25px;
            font-size: 16px;
            color: white;
            background-color: #4CAF50;
            border-radius: 6px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
        }
        .button:hover {
            background-color: #45A049;
        }
    </style>
</head>
<body>
	<%
	String success = request.getAttribute("success") != null ? request.getAttribute("success").toString() : "";
	
	%>
    <div class="container">
        <h1>Chúc mừng!</h1>
        <p>Bạn đã <%=success %> thành công.</p>
        <i class="bi bi-emoji-sunglasses"></i>
        <i class="bi bi-emoji-sunglasses-fill"></i>
        <i class="bi bi-emoji-sunglasses"></i>
    </div>
    <script>
        setTimeout(function(){
           window.location.href = 'index.jsp';
        }, 1500);
     </script>
</body>
</html>