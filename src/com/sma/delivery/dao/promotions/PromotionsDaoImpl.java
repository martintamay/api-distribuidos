package com.sma.delivery.dao.promotions;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.promotions.PromotionsDomain;

@Repository
public class PromotionsDaoImpl extends BaseDaoImpl<PromotionsDomain> implements IPromotionsDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PromotionsDomain save(PromotionsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public PromotionsDomain getById(Integer domainId) {
		return (PromotionsDomain) sessionFactory.getCurrentSession().get(PromotionsDomain.class, domainId);
	}

	@Override
	public List<PromotionsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PromotionsDomain.class);
		return safeConversion(criteria.list(), PromotionsDomain.class);	
	}

	@Override
	public PromotionsDomain update(PromotionsDomain domain) {
		return save(domain);
	}

	@Override
	public void delete(PromotionsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	@Override
	public List<PromotionsDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PromotionsDomain.class);
		Criterion name = Restrictions.like("_name", text);
		criteria.add(Restrictions.or(name));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), PromotionsDomain.class);	
	}

	@Override
	public List<PromotionsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PromotionsDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), PromotionsDomain.class);	
	}
}