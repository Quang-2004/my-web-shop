package util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class Email {
	// email: nguyen2707Quang@gmail.com
	// pass: quang/2707
	static final String from = "nguyen2707quang@gmail.com";
	static final String password = "inrp pazo qete ighe";
	
	public static boolean sendEmail(String to, String noiDung) {
	    // Properties : khai báo các thông tin
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
	    properties.put("mail.smtp.port", "587"); // TLS 587 and SSL 465
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");

	    // create Authenticator
	    Authenticator authenticator = new Authenticator() {

	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, password);
	        }
	    };

	    // Phiên làm việc
	    Session session = Session.getInstance(properties, authenticator);

	    try {
	        // Tạo MimeMessage
	        MimeMessage mimeMessage = new MimeMessage(session);

	        // Định dạng email để hỗ trợ ký tự UTF-8
	        mimeMessage.addHeader("Content-type", "text/html; charset=UTF-8");

	        // Người gửi
	        mimeMessage.setFrom(new InternetAddress(from, "Tên Người Gửi", "UTF-8"));

	        // Người nhận
	        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

	        // Tiêu đề email (đảm bảo UTF-8)
	        mimeMessage.setSubject("Thử nghiệm email", "UTF-8");

	        // Ngày gửi
	        mimeMessage.setSentDate(new Date());

	        // Nội dung email
	        mimeMessage.setContent(noiDung, "text/html; charset=UTF-8");

	        // Gửi email
	        Transport.send(mimeMessage);
	        System.out.println("Done");
	        return true;
	    } catch (Exception e) {
	        System.out.println("Error");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static void sendForClient(String to, String tieuDe, String noiDung) {
		
		
		// Properties
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		// Authenicator
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
			
		};
		
		// Session
		Session session = Session.getInstance(props, auth);
		
		// MimeMessage
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// Người gửi
			mimeMessage.setFrom(new InternetAddress(from, "Shopee", "UTF-8"));
			
			// Người nhận
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			
			// Tiêu đề email
			mimeMessage.setSubject("MÃ XÁC THỰC TÀI KHOẢN");
			
			// Quy định ngày gửi
			mimeMessage.setSentDate(new Date());
			
			// Quy định email nhận phản hồi
			mimeMessage.setReplyTo(InternetAddress.parse(from, false));
			
			// Nội dung
			mimeMessage.setContent(noiDung, "text/html; charset=UTF-8");
			// Gửi email
			Transport.send(mimeMessage);
			
		} catch (MessagingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sendFileMail(String to, String topic) {

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
	    	
		};
	    Session session = Session.getInstance(props, authenticator);

	    try {
	    	
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        message.setSubject(topic);
	        //message.setText("PFA");
	        
	        // tao 1 phan chua file
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        
	        String file = "C://Users//Admin//Downloads//LISTENING PRACTICE 1.pdf/";
	        String fileName = "LISTENING PRACTICE 1.pdf";
	        DataSource source = new FileDataSource(file);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(fileName);
	        
	        // giup gop cac phan cua mail: gop text, img,... lai voi nhau
	        MimeMultipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        
	        // bo phan file vao mimeMessage
	        message.setContent(multipart);

	        System.out.println("Sending");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("error");
	    }
	  
	}
	

}
