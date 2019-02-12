package com.sma.delivery.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.sma.delivery.domain.api_clients.ApiClientDomain;
import com.sma.delivery.domain.session.SessionDomain;

public class SdSecurityContext implements SecurityContext {
	private final ApiClientDomain apiClient;
	private final SessionDomain session;

	public SdSecurityContext(SessionDomain session, ApiClientDomain apiClient) {
		this.session = session;
		this.apiClient = apiClient;
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

	@Override
	public Principal getUserPrincipal() {
		return apiClient;
	}

	@Override
	public boolean isSecure() {
		return session.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		return apiClient.getRoles().equals(ApiClientDomain.Role.valueOf(role));
	}

}
