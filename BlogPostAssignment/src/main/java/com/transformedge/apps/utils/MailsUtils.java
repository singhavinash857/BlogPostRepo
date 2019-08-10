package com.transformedge.apps.utils;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Configuration
@Component
@PropertySource("classpath:application.yml")

@PropertySources({
	@PropertySource("classpath:application.yml"),
	@PropertySource("classpath:application.properties")
})
public class MailsUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value( "${service.mail.username}" )
	private String SERVICE_MAIL_ID;

	@Value( "${service.mail.password}" )
	private String SERVICE_MAIL_ID_PASSWORD;
	
	@Value( "${service.mail.host}" )
	private String SERVICE_MAIL_ID_HOST;
	
	@Value( "${service.mail.port}" )
	private String SERVICE_MAIL_ID_PORT;
	
	@Value( "${service.mail.properties.mail.smtp.starttls.enable}" )
	private String SERVICE_MAIL_ID_STARTTLS;

	@Value( "${password.reset.subject}" )
	private String PASSWORD_RESET_SUBJECT;
	
	@Value( "${password.reset.url}" )
	private String PASSWORD_RESET_URL;
	
	@Value( "${baseIpPath}" )
	private String BASE_IP_URL;
	
	
	final String PASSWORD_RESET_HTMLBODY ="<h1>A request to reset your password</h1>"
			+ "<p>Hi $fisrtName, </p>"
			+ "<p>Someone has requested to reset your password with our project.<br>If it's not you, please ignore, otherwise please click on the below link to reset a new password.</p>"
			+ "<a href=$resetLinkURL>"
			+ "Click this link to Reset Password"
			+ "</a><br><br>"
			+ "Thank you!";

	final String PASSWORD_RESET_TEXTBODY = "";
	
	public boolean sendPasswordResetToken(String employeeFirstName, String username, String token) throws AddressException {
		String htmlBodyWithtoken = PASSWORD_RESET_HTMLBODY.replace("$fisrtName", employeeFirstName);
		String passwordResetUrl = PASSWORD_RESET_URL.replace("$tokenValue", token);
	    String mainPasswordResetUrl = BASE_IP_URL + passwordResetUrl;
		htmlBodyWithtoken = htmlBodyWithtoken.replace("$resetLinkURL", mainPasswordResetUrl);
		
		Properties properties = getProperties();
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SERVICE_MAIL_ID , SERVICE_MAIL_ID_PASSWORD);
			}
		};

		Session session = Session.getInstance(properties, auth);
		Message msg = new MimeMessage(session);
		InternetAddress[] toAddresses = {
				new InternetAddress(username) 
		};
		try {
			msg.setFrom(new InternetAddress(SERVICE_MAIL_ID));
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(PASSWORD_RESET_SUBJECT);
			msg.setContent(htmlBodyWithtoken, "text/html; charset=utf-8");
			msg.setSentDate(new Date());
		} catch (MessagingException e) {
			e.printStackTrace();
		}  
		// creates message part
		//MimeBodyPart messageBodyPart = new MimeBodyPart();
		try {
			//messageBodyPart.setContent(PASSWORD_RESET_HTMLBODY, "UTF-8", "html");
			Transport.send(msg);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		return properties;
	}
}
