package com.sma.delivery.dao.products;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.products.ProductsDomain;

@Repository
public class ProductsDaoImpl extends BaseDaoImpl<ProductsDomain> implements IProductsDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductsDomain save(ProductsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public ProductsDomain getById(Integer domainId) {
		return (ProductsDomain) sessionFactory.getCurrentSession().get(ProductsDomain.class, domainId);
	}

	@Override
	public List<ProductsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductsDomain.class);
		return safeConversion(criteria.list(), ProductsDomain.class);
	}

	@Override
	public ProductsDomain update(ProductsDomain domain) {
		return save(domain);
	}

	@Override
	public void delete(ProductsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	@Override
	public List<ProductsDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductsDomain.class);
		Criterion name = Restrictions.like("name", text);
		criteria.add(Restrictions.or(name));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), ProductsDomain.class);
	}

	@Override
	public List<ProductsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductsDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), ProductsDomain.class);
	}
	

}