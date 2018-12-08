package com.sma.delivery.service.security.session;

import com.sma.delivery.domain.session.SessionDomain;

public interface ISessionService {

	public SessionDomain getById(Integer id);

	public SessionDomain save(SessionDomain domain);
}
