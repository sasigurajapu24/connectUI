package com.incon.connect.ui.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Value("${mail.sender.host}")
	private String host;
	
	@Value("${smtp.authenticator.email}")
	private String username;
	
	@Value("${smtp.authenticator.password}")
	private String password;

	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		sender.setHost(host);
		//sender.setSession(getMailSession());
		
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		//Set gmail email id
		sender.setUsername(username);
		//Set gmail email password
		sender.setPassword(password);
		Properties prop = sender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "false");
		return sender;
		
		//return sender;
	}

	private Session getMailSession() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", false);
		return Session.getInstance(props, getAuthenticator());
	}

	private Authenticator getAuthenticator() {
		SmtpAuthenticator authenticator = new SmtpAuthenticator();
		authenticator.setUsername(username);
		authenticator.setPassword(password);
		return authenticator;
	}

}
