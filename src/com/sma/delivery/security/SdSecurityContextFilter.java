package com.sma.delivery.security;

import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.domain.session.SessionDomain;
import com.sma.delivery.domain.roles.ApiClientDomain;
import com.sma.delivery.service.security.session.ISessionService;
import com.sma.delivery.service.security.roles.IApiClientService;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

@Component
@Provider
public class SdSecurityContextFilter implements ResourceFilter, ContainerRequestFilter {
	@Autowired
	ISessionService _sessionService;
	@Autowired
	IApiClientService _userService;

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		final String sessionId = request.getHeaderValue("session-id");
		ApiClientDomain user = new ApiClientDomain();
		SessionDomain session = new SessionDomain();

		if (sessionId != null && sessionId.length() > 0) {
			session = _sessionService.getById(Integer.valueOf(sessionId));

			if (null != session) {
				 user = _userService.getById(session.getUserId());
			}
			session.setLastAccessedTime(new Date());
		} else {
			try {
				final String token = request.getHeaderValue("token");
				System.out.println(token);
				if (StringUtils.isNotBlank(token)) {
					user = _userService.getByToken(token);

				}
				session.setUserId(user.getId());
				session.setActive(true);
				session.setSecure(true);
				session.setCreateTime(new Date());
				session.setLastAccessedTime(new Date());
				_sessionService.save(session);
			} catch (Exception e) {
				Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission denied").build();
				throw new WebApplicationException(denied);
			}
		}

		request.setSecurityContext(new SdSecurityContext(session, user));
		return request;
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
