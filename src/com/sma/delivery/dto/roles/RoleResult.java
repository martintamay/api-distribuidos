package com.sma.delivery.dto.roles;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "roles")
public class RoleResult extends BaseResult<RoleDTO>{
	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<RoleDTO> getRoles() {
		return getList();
	}

	public void setRoles(List<RoleDTO> dtos) {
		super.setList(dtos);
	}
}
