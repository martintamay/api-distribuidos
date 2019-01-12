package com.sma.delivery.dao.establishments;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;

@Repository
public class EstablishmentsDaoImpl extends BaseDaoImpl<EstablishmentsDomain> implements IEstablishmentsDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public EstablishmentsDomain save(EstablishmentsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public EstablishmentsDomain update(EstablishmentsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public void delete(EstablishmentsDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}
	@Override
	public List<EstablishmentsDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EstablishmentsDomain.class);
		Criterion firstName = Restrictions.like("_name", text);
		Criterion description = Restrictions.like("_description", text);
		criteria.add(Restrictions.or(firstName,description));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	
	}

	@Override
	public EstablishmentsDomain getById(Integer domainId) {
		return (EstablishmentsDomain) sessionFactory.getCurrentSession().get(EstablishmentsDomain.class, domainId);
	}

	@Override
	public List<EstablishmentsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EstablishmentsDomain.class);
		return criteria.list();
	}
	@Override
	public List<EstablishmentsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EstablishmentsDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	
}
