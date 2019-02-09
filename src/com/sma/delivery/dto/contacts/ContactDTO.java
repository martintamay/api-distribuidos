package com.sma.delivery.dto.contacts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;

@XmlRootElement(name = "contact")
public class ContactDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private String from;
	private String fullname;
	private String phoneNumber;
	private String message;
	
	@XmlElement
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	@XmlElement
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@XmlElement
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@XmlElement
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
