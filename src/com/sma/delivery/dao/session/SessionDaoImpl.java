package com.sma.delivery.dao.session;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.session.SessionDomain;

@Repository
public class SessionDaoImpl extends BaseDaoImpl<SessionDomain> implements ISessionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SessionDomain save(SessionDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public SessionDomain getById(Integer domainId) {
		return (SessionDomain) sessionFactory.getCurrentSession().get(SessionDomain.class, domainId);
	}

	

	@Override
	public List<SessionDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SessionDomain.class);
		return criteria.list();
	}

	@Override
	public SessionDomain update(SessionDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SessionDomain domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SessionDomain> find(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionDomain> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
