package com.sma.delivery.mailers;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.sma.delivery.utils.ProyectProperties;

public class BaseMailer { 
	private static final Logger LOGGER = Logger.getLogger( BaseMailer.class.getName() );
	protected static ProyectProperties props = new ProyectProperties();
	
	
	/** Main para enviar un correo de prueba usado para debug
	 */
	public static void main(String args[]) {
		String[] recipients = { "martinhtamaym@hotmail.com" };
		new BaseMailer().send("si se fue", "Hola mundo, eureka, funciona", recipients);
	}
	
	protected boolean send(String subject, String message, String[] recipients) {
		try {
			return send(subject, message, recipients, "text/html");
		} catch (URISyntaxException e) {
			Exception ex = new NoSuchFileException("cacerts in classes");
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
			return false;
		}
	}

	protected boolean send(String subject, String message, String[] recipients, String contentType) throws URISyntaxException {
		// se le dice a java que use un keystore en específico para determinar los ssl seguros (se configura en el .properties)
		
		String absolutePath = this.getClass().getClassLoader().getResource("/cacerts").toURI().getPath();
		System.setProperty("javax.net.ssl.keyStore", absolutePath);
		System.setProperty("javax.net.ssl.trustStore", absolutePath);
		System.setProperty("javax.net.ssl.keyStorePassword", props.getProperty("email.trustStore.password"));
		// se envía el correo
		try {
			generateAndSendEmail(subject, message, recipients, contentType);
		} catch (MessagingException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			return false;
		}
		return true;
	}

	protected void generateAndSendEmail(String subject, String emailBody, String recipients[], String contentType) throws MessagingException {
		MimeMessage generateMailMessage;
		Properties mailServerProperties;
		Session getMailSession;
		// se configuran los properties para el correo
		mailServerProperties = System.getProperties();

		mailServerProperties.put("mail.smtp.port", props.getProperty("email.server.port"));
		mailServerProperties.put("mail.smtp.auth", props.getProperty("email.server.smtpAuth"));
		mailServerProperties.put("mail.smtp.starttls.enable", props.getProperty("email.server.enablettls"));

		// Se configura la sesión con la cuál se enviará el correo
		// Se cargan los properties del correo
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		// Se agregan los destinatarios
		for(String recipient : recipients) {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));	
		}
		// Se setea el mensaje y origen del correo
		generateMailMessage.setSubject(subject);
		generateMailMessage.setFrom(new InternetAddress(props.getProperty("email.address")));
		generateMailMessage.setContent(emailBody, contentType);

		// se obtiene la sesión y se envía el correo
		final Transport transport = getMailSession.getTransport("smtp");

		transport.connect(
				props.getProperty("email.server.host"), 
				props.getProperty("email.address"),
				props.getProperty("email.password")
			);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}
