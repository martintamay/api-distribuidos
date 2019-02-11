package com.sma.delivery.dto.users;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "users")
public class UserResult extends BaseResult<UserDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<UserDTO> getUsers() {
		return getList();
	}

	public void setUsers(List<UserDTO> dtos) {
		super.setList(dtos);
	}
}