﻿package org.sg.eventcalendar.core.services.impl;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Session;
import javax.mail.Transport;
import org.sg.eventcalendar.core.services.IEmailService;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Properties;

/**
 * Implementacja interfejsu EmailService
 */
@Service
public class EmailServiceImpl implements IEmailService {
	
	/** Pole z właściwościami */
	Properties props = new Properties();
	
	/** Obiekt zawierający sesję */
	Session session = Session.getDefaultInstance(props, null);

	/**
	 * Implementacja metody wysyłącej email
	 * @param email adres email odbiorcy
	 * @param subject temat wiadmości
	 * @param content treść
	 */
	@Override
	public void  sendEMail(String email, String subject, String content) {
		
		String msg = "";
		
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbp = new MimeBodyPart();
			
		MimeMessage message =  new MimeMessage(session);
		MimeMessageHelper mimeHelper;
		try {
			mimeHelper = new MimeMessageHelper(message,true);
			mimeHelper.setTo(email);
			
			mimeHelper.setFrom("sebastian.sobiech@gmail.com");
			
				msg = "<html><body>"+content+"</body></html>";
			
				mbp.setContent(msg, "text/html; charset=UTF-8");
				mp.addBodyPart(mbp);
				message.setContent(mp);
				message.setSubject(subject);
				
			Transport.send(message);
		} catch (MessagingException e) {

		}		
	}
}