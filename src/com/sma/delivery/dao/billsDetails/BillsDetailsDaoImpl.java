package com.sma.delivery.dao.billsDetails;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.sma.delivery.dao.base.BaseDaoImpl;

import com.sma.delivery.domain.billsDetails.BillsDetailsDomain;


@Repository
public class BillsDetailsDaoImpl extends BaseDaoImpl<BillsDetailsDomain> implements IBillsDetailsDao{
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
		return criteria.list();
	}

	@Override
	public BillsDetailsDomain update(BillsDetailsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(BillsDetailsDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}


	

	@Override
	public List<BillsDetailsDomain> findByParams(Integer page, Integer maxPage) {
		// TODO Auto-generated method stub
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class).setMaxResults(maxPage).setFirstResult(page * maxPage);
		return criteria.list();
	}

	@Override
	public List<BillsDetailsDomain> find(String text) {
		// TODO Auto-generated method stub
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class);
		Criterion iva10 = Restrictions.like("_amount", text);
		Criterion total = Restrictions.like("_iva10", text);
		criteria.add(Restrictions.or(total,iva10));
		return criteria.list();
		
	}
	@Override
	public List<BillsDetailsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BillsDetailsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	

}
