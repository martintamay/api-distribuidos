package com.sma.delivery.dao.roles;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.roles.ApiClientDomain;

@Repository
public class ApiClientDaoImpl extends BaseDaoImpl<ApiClientDomain> implements IApiClientDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ApiClientDomain save(ApiClientDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;

	}

	@Override
	public ApiClientDomain getById(Integer domainId) {
		return (ApiClientDomain) sessionFactory.getCurrentSession().get(ApiClientDomain.class, domainId);
	}


	@Override
	public ApiClientDomain getByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApiClientDomain.class);
		criteria.add(Restrictions.ilike("token", token));

		return (ApiClientDomain) criteria.uniqueResult();
	}

	
	@Override
	public ApiClientDomain update(ApiClientDomain domain) {
		return null;
	}

	@Override
	public void delete(ApiClientDomain domain) {		
		// without use
	}

	@Override
	public List<ApiClientDomain> find(String text,Integer page, Integer size) {
		return new ArrayList<>();
	}

	@Override
	public List<ApiClientDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ApiClientDomain.class);
		return safeConversion(criteria.list(), ApiClientDomain.class);
	}

	@Override
	public List<ApiClientDomain> findAll() {
		return new ArrayList<>();
	}
}
