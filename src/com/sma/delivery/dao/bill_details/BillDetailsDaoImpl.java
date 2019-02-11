package com.sma.delivery.dao.bill_details;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.bills_details.BillsDetailsDomain;


@Repository
public class BillDetailsDaoImpl extends BaseDaoImpl<BillsDetailsDomain> implements IBillsDetailsDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillsDetailsDomain save(BillsDetailsDomain domain) {
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}

	@Override
	public BillsDetailsDomain getById(Integer domainId) {
		return (BillsDetailsDomain) sessionFactory.getCurrentSession().get(BillsDetailsDomain.class, domainId);
	}

	@Override
	public List<BillsDetailsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class);
		return safeConversion(criteria.list(), BillsDetailsDomain.class);
	}

	@Override
	public BillsDetailsDomain update(BillsDetailsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(BillsDetailsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}


	

	@Override
	public List<BillsDetailsDomain> findByParams(Integer page, Integer maxPage) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class).setMaxResults(maxPage).setFirstResult(page * maxPage);
		return safeConversion(criteria.list(), BillsDetailsDomain.class);
	}

	@Override
	public List<BillsDetailsDomain> find(String text,Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class);
		Criterion iva10 = Restrictions.like("amount", text);
		Criterion total = Restrictions.like("iva10", text);
		criteria.add(Restrictions.or(total,iva10));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), BillsDetailsDomain.class);
		
	}
	@Override
	public List<BillsDetailsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), BillsDetailsDomain.class);
	}
	

}
