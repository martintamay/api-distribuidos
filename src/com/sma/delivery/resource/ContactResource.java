package com.sma.delivery.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.sma.delivery.dto.contacts.ContactDTO;
import com.sma.delivery.mailers.ContactMailer;

@Path("/contact")
@Component
public class ContactResource {

	@POST
	@Produces({"application/xml", "application/json"})
	public ContactDTO send(ContactDTO mail) {
		ContactMailer mailer =  new ContactMailer();
		if (mailer.send(mail.getFrom(), mail.getPhoneNumber(), mail.getFullname(), mail.getMessage())) {
			return mail;
		} else {
			return null;
		}
	}
}
