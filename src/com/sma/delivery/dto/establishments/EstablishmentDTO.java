package com.sma.delivery.dto.establishments;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "establishment")

public class EstablishmentDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	@XmlElement
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement
	public String getSchedule() {
		return schedule;
	}
	
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	@XmlElement
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	@XmlElement
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
private String name;
private String description;
private String schedule;
private String address;
private String phoneNumber;
private String email;
private String creationDate;
}

	
	