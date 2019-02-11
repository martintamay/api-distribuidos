package com.sma.delivery.service.security.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.session.ISessionDao;
import com.sma.delivery.domain.session.SessionDomain;

@Service
public class SessionServiceImpl implements ISessionService {

	@Autowired
	private ISessionDao sessionDao;

	@Override
	@Transactional
	public SessionDomain getById(Integer id) {
		return sessionDao.getById(id);
	}

	@Override
	@Transactional
	public SessionDomain save(SessionDomain domain) {
		return sessionDao.save(domain);
	}

}
