package com.sma.delivery.mailers;

import java.util.Properties;
import com.sma.delivery.utils.ProyectProperties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BaseMailer { 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	public static void main(String args[]) {
		String[] recipients = { "martinhtamaym@hotmail.com" };
		try {
			send("Hola mundo, eureka, funciona carajo", "si se fue", recipients);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void send(String message, String subject, String recipients[]) throws MessagingException {

		System.setProperty("javax.net.ssl.keyStore", ProyectProperties.getProperty("email.trustStore.location"));
		System.setProperty("javax.net.ssl.trustStore", ProyectProperties.getProperty("email.trustStore.location"));
		System.setProperty("javax.net.ssl.keyStorePassword", ProyectProperties.getProperty("email.trustStore.password"));

		generateAndSendEmail(message, subject, recipients);
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}

	public static void generateAndSendEmail(String emailBody, String subject, String recipients[]) throws MessagingException {

		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();

		mailServerProperties.put("mail.smtp.port", ProyectProperties.getProperty("email.server.port"));
		mailServerProperties.put("mail.smtp.auth", ProyectProperties.getProperty("email.server.smtpAuth"));
		mailServerProperties.put("mail.smtp.starttls.enable", ProyectProperties.getProperty("email.server.enablettls"));
		System.out.println("Mail Server Properties have been setup successfully..");

		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		// Se cargan los properties del correo
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		// Se agregan los destinatarios
		for(String recipient : recipients) {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));	
		}
		// Se setea el mensaje y origen del correo
		generateMailMessage.setSubject(subject);
		generateMailMessage.setFrom(new InternetAddress(ProyectProperties.getProperty("email.address")));
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");

		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		final Transport transport = getMailSession.getTransport("smtp");

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect(
				ProyectProperties.getProperty("email.server.host"), 
				ProyectProperties.getProperty("email.address"),
				ProyectProperties.getProperty("email.password")
			);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}
