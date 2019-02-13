package com.sma.delivery.dao.product_has_promotions;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.product_has_promotions.ProductHasPromotionsDomain;

@Repository
public class ProductHasPromotionsDaoImpl extends BaseDaoImpl<ProductHasPromotionsDomain> implements IProductHasPromotionsDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductHasPromotionsDomain save(ProductHasPromotionsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public ProductHasPromotionsDomain getById(Integer domainId) {
		return (ProductHasPromotionsDomain) sessionFactory.getCurrentSession().get(ProductHasPromotionsDomain.class, domainId);
	}

	@Override
	public List<ProductHasPromotionsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductHasPromotionsDomain.class);
		return safeConversion(criteria.list(), ProductHasPromotionsDomain.class);	
	}

	@Override
	public ProductHasPromotionsDomain update(ProductHasPromotionsDomain domain) {
		return save(domain);
	}

	@Override
	public void delete(ProductHasPromotionsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	@Override
	public List<ProductHasPromotionsDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductHasPromotionsDomain.class);
		Criterion name = Restrictions.like("name", text);
		criteria.add(Restrictions.or(name));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), ProductHasPromotionsDomain.class);	
	}

	@Override
	public List<ProductHasPromotionsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductHasPromotionsDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), ProductHasPromotionsDomain.class);	
	}
}