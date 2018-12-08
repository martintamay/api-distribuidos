package com.sma.delivery.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.sma.delivery.domain.session.SessionDomain;
import com.sma.delivery.domain.roles.ApiClientDomain;

public class SdSecurityContext implements SecurityContext {
	private final ApiClientDomain _apiClient;
	private final SessionDomain _session;

	public SdSecurityContext(SessionDomain session, ApiClientDomain apiClient) {
		_session = session;
		_apiClient = apiClient;
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

	@Override
	public Principal getUserPrincipal() {
		return _apiClient;
	}

	@Override
	public boolean isSecure() {
		return _session.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		/*if (null == _session || !_session.isActive()) {
			Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission denied").build();
			throw new WebApplicationException(denied);
		}*/

		try {
			return _apiClient.getRoles().equals(ApiClientDomain.Role.valueOf(role));
		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}
		// return false;
	}

}
