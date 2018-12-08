package com.sma.delivery.service.security.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.roles.IApiClientDao;
import com.sma.delivery.domain.roles.ApiClientDomain;

@Service
public class ApiClientServiceImpl implements IApiClientService {
	@Autowired
	private IApiClientDao _userDao;

	@Transactional
	public ApiClientDomain getByToken(String token) {
		return _userDao.getByToken(token);
	}
	
	@Override
	@Transactional
	public ApiClientDomain getById(Integer id) {
		return _userDao.getById(id);
	}
}
