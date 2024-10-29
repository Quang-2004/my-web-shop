package util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;


public class MaHoa {
	// md5
	// sha-1 => thường được sử dụng
	public static String toSHA1(String str) {
		String salt = "asd124#SND236&%#jghfksnfJFSJBk.sdf/!";
		String result = null;
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			
			MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
			
			result = Base64.encodeBase64String(mDigest.digest(dataBytes));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(toSHA1("123456"));
	}
}
