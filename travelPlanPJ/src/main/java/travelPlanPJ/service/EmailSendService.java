package travelPlanPJ.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {
	@Autowired
	JavaMailSender mailSender;
	
	public void mailSend(String html, String subject, String fromEmail, String toEmail) {
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			msg.setHeader("content-type", "text/html; charset=UTF-8");
			msg.setContent(html, "text/html; charset=UTF-8");
			msg.setSubject(subject);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipient(MimeMessage.RecipientType.TO, 
					new InternetAddress(toEmail));
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
