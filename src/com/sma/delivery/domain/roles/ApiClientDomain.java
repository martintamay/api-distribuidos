package com.sma.delivery.domain.roles;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;


@Entity
@Table(name = "api_client")
public class ApiClientDomain implements BaseDomain, Principal  {
	public enum Role {
		Editor, Visitor, Contributor;
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer _id;

	@Column(name = "name")
	private String _name;

	@Column(name = "token")
	private String _token;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING) 
	private Role _role;

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public Role getRoles() {
		return _role;
	}

	public void setRoles(Role role) {
		_role = role;
	}

	@Override
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}
}