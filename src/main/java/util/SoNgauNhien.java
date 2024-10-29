package util;

import java.util.Random;

public class SoNgauNhien {
	public static String getSoNgauNhien() {
		Random rd = new Random();
		String s1 = rd.nextInt(10) + "";
		String s2 = rd.nextInt(10) + "";
		String s3 = rd.nextInt(10) + "";
		String s4 = rd.nextInt(10) + "";
		String s5 = rd.nextInt(10) + "";
		String s6 = rd.nextInt(10) + "";
		
		String s = s1 + s2 + s3 + s4 + s5 + s6;
		
		return s;
		
	}
	public static String getKiTuNgauNhien(int length) {
		 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            // Lấy ký tự ngẫu nhiên từ chuỗi 'characters'
	            int index = random.nextInt(characters.length());
	            sb.append(characters.charAt(index));
	        }

	        return sb.toString();
		
	}
}
