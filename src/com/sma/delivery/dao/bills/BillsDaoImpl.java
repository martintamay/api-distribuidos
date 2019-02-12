package com.sma.delivery.dao.bills;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.service.bill_details.IBillsDetailsService;

@Repository
public class BillsDaoImpl  extends BaseDaoImpl<BillsDomain> implements IBillsDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private IBillsDetailsService billsDetailsService;
	@Override
	public BillsDomain save(BillsDomain domain) {
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}

	@Override
	public BillsDomain getById(Integer domainId) {
		return (BillsDomain) sessionFactory.getCurrentSession().get(BillsDomain.class, domainId);
	}

	@Override
	public List<BillsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDomain.class);
		return safeConversion(criteria.list(), BillsDomain.class);
	}

	@Override
	public BillsDomain update(BillsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(BillsDomain domain) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public List<BillsDomain> find(String text,Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDomain.class);
		Criterion iva10 = Restrictions.like("iva10", text);
		Criterion total = Restrictions.like("total", text);
		criteria.add(Restrictions.or(total,iva10));
		criteria.setFirstResult((page - 1) * size); 
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), BillsDomain.class);
		
	}
	@Override
	public List<BillsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), BillsDomain.class);
	}
}

