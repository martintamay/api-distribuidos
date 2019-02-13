package com.sma.delivery.security;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.domain.api_clients.ApiClientDomain;
import com.sma.delivery.domain.session.SessionDomain;
import com.sma.delivery.service.security.roles.IApiClientService;
import com.sma.delivery.service.security.session.ISessionService;
import com.sma.delivery.utils.ProyectProperties;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

@Component
@Provider
public class SdSecurityContextFilter implements ResourceFilter, ContainerRequestFilter {
	private static final Logger LOGGER = Logger.getLogger( SdSecurityContextFilter.class.getName() );
	@Autowired
	ISessionService sessionService;
	@Autowired
	IApiClientService userService;

	private ProyectProperties props = new ProyectProperties();
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		final String sessionId = request.getHeaderValue("session-id");
		ApiClientDomain user = new ApiClientDomain();
		SessionDomain session = new SessionDomain();

		if (sessionId != null && sessionId.length() > 0) {
			session = sessionService.getById(Integer.valueOf(sessionId));

			if (null != session) {
				 user = userService.getById(session.getUserId());
				session.setLastAccessedTime(new Date());
			}
		} else {
			try {
				final String token = request.getHeaderValue("token");
				if (!StringUtils.isNotBlank(token) || !checkToken(token)) {
					Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission denied").build();
					if (StringUtils.isNotBlank(token)) LOGGER.log(Level.INFO, "Connection without token or invalid token");
					else LOGGER.log(Level.INFO, String.format("token check: %s > Invalid Token", token));
					throw new WebApplicationException(denied);
				}
				session.setUserId(user.getId());
				session.setActive(true);
				session.setSecure(true);
				session.setCreateTime(new Date());
				session.setLastAccessedTime(new Date());
				sessionService.save(session);
			} catch (Exception e) {
				Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission denied").build();
				throw new WebApplicationException(denied);
			}
		}

		request.setSecurityContext(new SdSecurityContext(session, user));
		return request;
	}
	
	private boolean checkToken(String token) {
		String[] approvedTokens = props.getProperty("security.tokens").split(";");
		for (String approvedToken : approvedTokens) {
			if (approvedToken.trim().equals(token.trim())) return true;
		}
		return false;
	}

	@Override
	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return null;
	}

}
