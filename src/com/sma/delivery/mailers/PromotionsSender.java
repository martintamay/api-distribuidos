package com.sma.delivery.mailers;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import com.sma.delivery.dto.users.UserDTO;
import com.sma.delivery.service.users.IUserService;
import com.sma.delivery.utils.ProyectProperties;

@Service
public class PromotionsSender extends BaseMailer{
	private static final Logger LOGGER = Logger.getLogger( ProyectProperties.class.getName() );
	
	/** Método encargado de enviar las promociones a los usuarios
	 */
	public boolean send(IUserService userService) {		
		String messageTitle = "Promociones del día de DeliverPY";
		String messageBody = 
				"<!DOCTYPE html>\n" + 
				"<html lang=\"es\" dir=\"ltr\">\n" + 
				"  <head>\n" + 
				"    <meta charset=\"utf-8\">\n" + 
				"    <title>Promos DeliverPY</title>\n" + 
				"  </head>\n" + 
				"  <body>\n" + 
				"    <h3>Disfruta de la carta de hoy con estas promociones y no cocines más</h3>\n" + 
				"    <p>Pizza por metro al más bajo precio!</p>\n" + 
				"  </body>\n" + 
				"</html>";
		ArrayList<String> recipients = new ArrayList<>();
		if (userService==null) {
			LOGGER.log(Level.INFO, "users is null");
			return false;
		}
		for (UserDTO user : userService.getAll().getUsers()) {
			recipients.add(user.getEmail());
		}
		return send(messageTitle, messageBody, recipients.toArray(new String[recipients.size()]));
	}
}
