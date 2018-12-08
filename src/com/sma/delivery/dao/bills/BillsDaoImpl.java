package com.sma.delivery.dao.bills;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.sma.delivery.dao.base.BaseDaoImpl;

import com.sma.delivery.domain.bills.BillsDomain;


@Repository
public class BillsDaoImpl extends BaseDaoImpl<BillsDomain> implements IBillsDao{
	@Autowired
	private SessionFactory sessionFactory;

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
		return criteria.list();
	}

	@Override
	public BillsDomain update(BillsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(BillsDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}


	

	@Override
	public List<BillsDomain> findByParams(Integer page, Integer maxPage) {
		// TODO Auto-generated method stub
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDomain.class).setMaxResults(maxPage).setFirstResult(page * maxPage);
		return criteria.list();
	}

	@Override
	public List<BillsDomain> find(String text) {
		// TODO Auto-generated method stub
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDomain.class);
		Criterion iva10 = Restrictions.like("_iva10", text);
		Criterion total = Restrictions.like("_total", text);
		criteria.add(Restrictions.or(total,iva10));
		return criteria.list();
		
	}
	@Override
	public List<BillsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	

}
