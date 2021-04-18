package com.sine.student.flghtreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sine.student.flghtreservation.services.ReservationService;

import ch.qos.logback.classic.Logger;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender sender; 
	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmailUtil.class);
	@Value("${com.sine.student.flghtreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT ;
	@Value("${com.sine.student.flghtreservation.itinerary.email.body}")
	private String EMAIL_BODY ;
	
	public void sendItinerary(String toAddress, String filePath) {
		LOGGER.info("sendItinerary()");
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(toAddress);
			helper.setSubject(EMAIL_SUBJECT);
			helper.setText(EMAIL_BODY);
			helper.addAttachment("itinarery", new File(filePath));
			sender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		LOGGER.error("Exception inside sendItinerary "+e);
		}
	}
}
