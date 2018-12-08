package com.sma.delivery.dto.establishments;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "establishments")

public class EstablishmentsDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	@XmlElement
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_description() {
		return _description;
	}
	
	public void set_description(String _description) {
		this._description = _description;
	}
	public String get_schedule() {
		return _schedule;
	}
	
	public void set_schedule(String _schedule) {
		this._schedule = _schedule;
	}
	public String get_address() {
		return _address;
	}
	
	public void set_address(String _address) {
		this._address = _address;
	}
	public String get_phone_number() {
		return _phone_number;
	}
	
	public void set_phone_number(String _phone_number) {
		this._phone_number = _phone_number;
	}
	public String get_email() {
		return _email;
	}
	
	public void set_email(String _email) {
		this._email = _email;
	}
	public String get_creation_date() {
		return _creation_date;
	}
	
	public void set_creation_date(String _creation_date) {
		this._creation_date = _creation_date;
	}
private String _name;
private String _description;
private String _schedule;
private String _address;
private String _phone_number;
private String _email;
private String _creation_date;
}

	
	