package com.sma.delivery.dto.user;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;

@XmlRootElement(name = "user")
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private String _firstName;
	private String _lastName;
	private String _password;
	private String _email;
	private String _phone_number;
	private String _address;
	private Date _creation_date;
	@XmlElement
	public String getPassword() {
		return _password;
	}

	public void setPassword(String _password) {
		this._password = _password;
	}
	@XmlElement
	public String getEmail() {
		return _email;
	}

	public void setEmail(String _email) {
		this._email = _email;
	}
	@XmlElement
	public String getPhoneNumber() {
		return _phone_number;
	}

	public void setPhoneNumber(String _phone_number) {
		this._phone_number = _phone_number;
	}
	@XmlElement
	public String getAddress() {
		return _address;
	}

	public void setAddress(String _address) {
		this._address = _address;
	}
	@XmlElement
	public Date getCreationDate() {
		return _creation_date;
	}

	public void setCreationDate(Date _creation_date) {
		this._creation_date = _creation_date;
	}
	@XmlElement
	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	@XmlElement
	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

}
