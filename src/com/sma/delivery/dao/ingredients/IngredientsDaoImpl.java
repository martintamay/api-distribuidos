package com.sma.delivery.dao.ingredients;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.ingredients.IngredientsDomain;


@Repository
public class IngredientsDaoImpl extends BaseDaoImpl<IngredientsDomain> implements IIngredientsDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public IngredientsDomain save(IngredientsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public IngredientsDomain update(IngredientsDomain domain) {
		return save(domain);
	}
	@Override
	public void delete(IngredientsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	@Override
	public List<IngredientsDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IngredientsDomain.class);
		Criterion description = Restrictions.like("description", text, MatchMode.START);
		criteria.add(Restrictions.or(description));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), IngredientsDomain.class);
	}
	
	@Override
	public IngredientsDomain getById(Integer domainId) {
		return (IngredientsDomain) sessionFactory.getCurrentSession().get(IngredientsDomain.class, domainId);
	}

	@Override
	public List<IngredientsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IngredientsDomain.class);
		return safeConversion(criteria.list(), IngredientsDomain.class);
	}
	@Override
	public List<IngredientsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IngredientsDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), IngredientsDomain.class);
	}
}