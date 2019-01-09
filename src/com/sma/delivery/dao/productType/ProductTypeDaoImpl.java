package com.sma.delivery.dao.productType;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.productType.ProductTypeDomain;

@Repository
public class ProductTypeDaoImpl extends BaseDaoImpl<ProductTypeDomain> implements IProductTypeDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductTypeDomain save(ProductTypeDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public ProductTypeDomain update(ProductTypeDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public void delete(ProductTypeDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	@Override
	public List<ProductTypeDomain> find(String text) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductTypeDomain.class);
		Criterion description = Restrictions.like("description", text, MatchMode.START);
		Criterion id = Restrictions.like("id", text, MatchMode.START);
		criteria.add(Restrictions.or(id,description));
		return criteria.list();
	
	}

	@Override
	public ProductTypeDomain getById(Integer domainId) {
		return (ProductTypeDomain) sessionFactory.getCurrentSession().get(ProductTypeDomain.class, domainId);
	}

	@Override
	public List<ProductTypeDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductTypeDomain.class);
		return criteria.list();
	}
	@Override
	public List<ProductTypeDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductTypeDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
}