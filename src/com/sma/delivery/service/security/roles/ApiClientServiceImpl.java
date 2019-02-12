package com.sma.delivery.service.security.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.api_clients.IApiClientDao;
import com.sma.delivery.domain.api_clients.ApiClientDomain;

@Service
public class ApiClientServiceImpl implements IApiClientService {
	@Autowired
	private IApiClientDao userDao;

	@Transactional
	public ApiClientDomain getByToken(String token) {
		return userDao.getByToken(token);
	}
	
	@Override
	@Transactional
	public ApiClientDomain getById(Integer id) {
		return userDao.getById(id);
	}
}
