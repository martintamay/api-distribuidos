package com.sma.delivery.dao.ordersDetail;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.dao.ordersDetail.IOrdersDetailDao;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.orders.OrdersDomain;
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;

@Repository
public class OrdersDetailDaoImpl extends BaseDaoImpl<OrdersDetailDomain> implements IOrdersDetailDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public OrdersDetailDomain save(OrdersDetailDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public OrdersDetailDomain getById(Integer domainId) {
		return (OrdersDetailDomain) sessionFactory.getCurrentSession().get(OrdersDetailDomain.class, domainId);
	}

	@Override
	public List<OrdersDetailDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDetailDomain.class);
		return criteria.list();
	}
	
	//aca
	@Override
	public OrdersDetailDomain update(OrdersDetailDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public void delete(OrdersDetailDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public List<OrdersDetailDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDetailDomain.class);
		Criterion comment = Restrictions.like("comment", text);
		criteria.add(Restrictions.or(comment));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
		
	}

	@Override
	public List<OrdersDetailDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDetailDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}

}
