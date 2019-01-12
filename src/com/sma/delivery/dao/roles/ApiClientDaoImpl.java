package com.sma.delivery.dao.roles;

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
		criteria.add(Restrictions.ilike("_token", token));

		return (ApiClientDomain) criteria.uniqueResult();
	}

	
	@Override
	public ApiClientDomain update(ApiClientDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ApiClientDomain domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ApiClientDomain> find(String text,Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApiClientDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ApiClientDomain.class);
		return criteria.list();
	}

	@Override
	public List<ApiClientDomain> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
