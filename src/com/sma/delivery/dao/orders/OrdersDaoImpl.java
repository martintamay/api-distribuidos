package com.sma.delivery.dao.orders;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.orders.OrdersDomain;

@Repository
public class OrdersDaoImpl extends BaseDaoImpl<OrdersDomain> implements IOrdersDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public OrdersDomain save(OrdersDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public OrdersDomain getById(Integer domainId) {
		return (OrdersDomain) sessionFactory.getCurrentSession().get(OrdersDomain.class, domainId);
	}

	@Override
	public List<OrdersDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDomain.class);
		return criteria.list();
	}
	//aca
	@Override
	public OrdersDomain update(OrdersDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public void delete(OrdersDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public List<OrdersDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDomain.class);
		Criterion address = Restrictions.like("address", text);
		criteria.add(Restrictions.or(address));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	@Override
	public List<OrdersDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	

}