package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;


public class JDBCUtil {
	public static Connection getConnection() {
	    Connection conn = null;
	    try {
	        // Nạp driver MySQL
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Thông tin kết nối
	        String url = "jdbc:mysql://localhost:3306/shop";
	        String username = "root";
	        String password = "27072004";
	        
	        // Tạo kết nối
	        conn = DriverManager.getConnection(url, username, password);
	        System.out.println("Kết nối thành công!");
	        
	    } catch (ClassNotFoundException e) {
	        System.out.println("Không tìm thấy driver JDBC: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
	    }
	    
	    if (conn == null) {
	        System.out.println("Kết nối thất bại.");
	    }
	    
	    return conn;
	}

	
	public static void closeConnection(Connection c) {
		try {
			if(c != null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printInfo(Connection c) {
		if(c != null) {
			try {
				DatabaseMetaData mtdt = (DatabaseMetaData) c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
