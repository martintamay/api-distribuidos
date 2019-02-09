package com.sma.delivery.mailers;

import java.security.InvalidParameterException;

import com.sma.delivery.utils.ProyectProperties;

public class ContactMailer extends BaseMailer {	
	
	/** Método que se encarga de enviar un correo de contacto ya formateado
	 * @param from Correo del que se puso en contacto
	 * @param phoneNumber Número de teléfono del que se puso en contacto
	 * @param fullname Nombre completo de quién se puso en contacto
	 * @param message Mensaje dejado
	 * @return true o false dependiendo de si hubo problemas al enviar el correo
	 */
	public boolean send(String from, String phoneNumber, String fullname, String message) {
		if (null == from || null == phoneNumber || null ==  fullname || null == message) {
			throw new InvalidParameterException();
		}
		
		from = from.trim();
		message = message.replace("\n", "<br>");
		String messageTitle = String.format("Mensaje de contacto recibido de %s", from.trim());
		String messageBody = String.format(
				"<!DOCTYPE html>\n" + 
				"<html lang=\"es\" dir=\"ltr\">\n" + 
				"  <head>\n" + 
				"    <meta charset=\"utf-8\">\n" + 
				"    <title>Mensaje de contacto de %s</title>\n" + 
				"  </head>\n" + 
				"  <body>\n" + 
				"    <h3>Mensaje de contacto recibido de %s:<a href=\"mailto:%s\">%s</a></h3>\n" + 
				"    <p>%s</p>\n" + 
				"    <p><b>Teléfono de contacto: </b><a href=\"tel:%s\">%s</a></p>\n" + 
				"  </body>\n" + 
				"</html>", 
				from, fullname, from, from, message, phoneNumber, phoneNumber);
		ProyectProperties props = new ProyectProperties();
		String[] recipients = { props.getProperty("email.contactReceiver") };
		return send(messageTitle, messageBody, recipients);
	}
}
