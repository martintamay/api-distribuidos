package com.sma.delivery.dao.orders_details;

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
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;

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
		return safeConversion(criteria.list(), OrdersDetailDomain.class);
	}
	
	//aca
	@Override
	public OrdersDetailDomain update(OrdersDetailDomain domain) {
		return save(domain);
	}
	@Override
	public void delete(OrdersDetailDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public List<OrdersDetailDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDetailDomain.class);
		Criterion comment = Restrictions.like("comment", text);
		criteria.add(Restrictions.or(comment));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), OrdersDetailDomain.class);		
	}

	@Override
	public List<OrdersDetailDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrdersDetailDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), OrdersDetailDomain.class);
	}
	@Override
	public List<OrdersDetailDomain> findAllBy(Map<String, String> args) {
		if(args.containsKey("orderId")){
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from orderdetails where order_id=:orderId");
			query.addEntity(OrdersDetailDomain.class); // Define el tipo de resultado de la consulta
			query.setString("orderId", args.get("orderId"));
			return query.list();
		}
		return new ArrayList<>();
	}
	
	@Override
	public void deleteByOrder(Integer id) {
		if(id!=null){
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("delete from orderdetails where order_id=:orderId");
			query.addEntity(OrdersDetailDomain.class); // Define el tipo de resultado de la consulta
			query.setInteger("orderId", id);
			query.executeUpdate();
		}
	}
}
