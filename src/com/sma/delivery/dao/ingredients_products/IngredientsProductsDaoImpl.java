package com.sma.delivery.dao.ingredients_products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.ingredients_products.IngredientsProductsDomain;


@Repository
public class IngredientsProductsDaoImpl extends BaseDaoImpl<IngredientsProductsDomain> implements IIngredientsProductsDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public IngredientsProductsDomain save(IngredientsProductsDomain domain) {
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}

	@Override
	public IngredientsProductsDomain getById(Integer domainId) {
		return (IngredientsProductsDomain) sessionFactory.getCurrentSession().get(IngredientsProductsDomain.class, domainId);
	}

	@Override
	public List<IngredientsProductsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IngredientsProductsDomain.class);
		return safeConversion(criteria.list(), IngredientsProductsDomain.class);
	}

	@Override
	public IngredientsProductsDomain update(IngredientsProductsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(IngredientsProductsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public List<IngredientsProductsDomain> find(String text,Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IngredientsProductsDomain.class);
		Criterion amount = Restrictions.like("amount", text);
		criteria.add(Restrictions.or(amount));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), IngredientsProductsDomain.class);
		
	}
	@Override
	public List<IngredientsProductsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(IngredientsProductsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), IngredientsProductsDomain.class);
	}
	
	@Override
	public List<IngredientsProductsDomain> findAllBy(Map<String, String> args) {
		if(args.containsKey("billId")){
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from billdetails where bill_id=:billId");
			query.addEntity(IngredientsProductsDomain.class); // Define el tipo de resultado de la consulta
			query.setString("billId", args.get("billId"));
			return query.list();
		}
		return new ArrayList<>();
	}
	
	@Override
	public void deleteByBill(Integer ingredientId, Integer productId) {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("delete from billdetails where ingredient_id=:ingredientId and product_id =:productId");
			query.addEntity(IngredientsProductsDomain.class); // Define el tipo de resultado de la consulta
			query.setInteger("ingredientId", ingredientId);
			query.setInteger("productId", ingredientId);
			query.executeUpdate();
	}

}
