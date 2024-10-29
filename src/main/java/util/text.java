package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class text {
	 public static void main(String[] args) {
		 // Lấy thời gian hiện tại
		 LocalDateTime myDateObj = LocalDateTime.now();
		 System.out.println("Before formatting: " + myDateObj);

		 // Cộng thêm 1 phút vào thời gian hiện tại
		 LocalDateTime newDateObj = myDateObj.plusMinutes(1);
		 System.out.println("After adding 1 minute: " + newDateObj);

		 // Định dạng ngày giờ
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String formattedDate = newDateObj.format(myFormatObj);
		 System.out.println("Formatted date after adding 1 minute: " + formattedDate);
	 }
}
