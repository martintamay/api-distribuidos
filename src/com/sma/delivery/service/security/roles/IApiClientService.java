package com.sma.delivery.service.security.roles;

import com.sma.delivery.domain.roles.ApiClientDomain;

public interface IApiClientService {
	public ApiClientDomain getByToken(String token);
	public ApiClientDomain getById(Integer id);
	
}
