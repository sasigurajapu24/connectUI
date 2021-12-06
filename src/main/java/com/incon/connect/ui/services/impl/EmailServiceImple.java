package com.incon.connect.ui.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.incon.connect.ui.dto.EmailTemplateDto;
import com.incon.connect.ui.mail.MailConfig;
import com.incon.connect.ui.services.EmailService;

@Service
public class EmailServiceImple implements EmailService {

	@Autowired
	private MailConfig mailconfig;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Override
	@Async
	public void sendTemplate(final EmailTemplateDto templateDTOMap) {
		try {
			JavaMailSender mailSender = mailconfig.javaMailSender();
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mailMsg.setFrom("intruecheck@gmail.com");
			mailMsg.setTo("bogavalli24@gmail.com");
//			mailMsg.setTo("nxl.naveen@gmail.com");
			mailMsg.setCc("nxl.naveen@gmail.com");
			mailMsg.setSubject("True Check QR Codes");
			mailMsg.setText("Please find attached PDF file with generated QR codes");
			// add the attachment file resources

			mailMsg.addAttachment(templateDTOMap.getProductName()+"_"+templateDTOMap.getBatchNo()+".pdf", templateDTOMap.getFile());

			final Context ctx = new Context();
			// ctx.setVariable("object", templateDTOMap.getFile());
			final String htmlContent = templateEngine.process("email", ctx);
			mailMsg.setText(htmlContent, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendPassword(EmailTemplateDto templateDTOMap) {
		try {
			JavaMailSender mailSender = mailconfig.javaMailSender();
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mailMsg.setFrom("intruecheck@gmail.com");
//			mailMsg.setTo("nxl.naveen@gmail.com");
			mailMsg.setTo(templateDTOMap.getTo());
			mailMsg.addCc("bogavalli24@gmail.com" );
			mailMsg.addCc("nxl.naveen@gmail.com");
			mailMsg.setSubject(templateDTOMap.getSubject());
			mailMsg.setText(templateDTOMap.getMessage(), true);
			// add the attachment file resources

//			final Context ctx = new Context();
			// ctx.setVariable("object", templateDTOMap.getFile());
//			final String htmlContent = templateEngine.process("email", ctx);
//			mailMsg.setText(htmlContent, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
