package com.sma.delivery.dao.roles;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.roles.RolesDomain;

@Repository
public class RolesDaoImpl  extends BaseDaoImpl<RolesDomain> implements IRolesDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public RolesDomain save(RolesDomain domain) {
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}

	@Override
	public RolesDomain getById(Integer domainId) {
		return (RolesDomain) sessionFactory.getCurrentSession().get(RolesDomain.class, domainId);
	}

	@Override
	public List<RolesDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RolesDomain.class);
		return safeConversion(criteria.list(), RolesDomain.class);
	}

	@Override
	public RolesDomain update(RolesDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(RolesDomain domain) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public List<RolesDomain> find(String text,Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RolesDomain.class);
		Criterion userId = Restrictions.like("user_id", text);
		criteria.add(userId);
		criteria.setFirstResult((page - 1) * size); 
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), RolesDomain.class);
		
	}
	
	@Override
	public List<RolesDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RolesDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), RolesDomain.class);
	}
}

