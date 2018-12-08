package com.sma.delivery.dao.products;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
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
		return criteria.list();
	}

	@Override
	public ProductsDomain update(ProductsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public void delete(ProductsDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	@Override
	public List<ProductsDomain> find(String text) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductsDomain.class);
		Criterion Name = Restrictions.like("_name", text);
		criteria.add(Restrictions.or(Name));
		return criteria.list();
	}

	@Override
	public List<ProductsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductsDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	

}