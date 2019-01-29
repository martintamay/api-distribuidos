package com.sma.delivery.dao.packaged;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.packaged.PackageDomain;

@Repository
public class PackageDaoImpl extends BaseDaoImpl<PackageDomain> implements IPackageDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PackageDomain save(PackageDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public PackageDomain getById(Integer domainId) {
		return (PackageDomain) sessionFactory.getCurrentSession().get(PackageDomain.class, domainId);
	}

	@Override
	public List<PackageDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PackageDomain.class);
		return criteria.list();
	}

	@Override
	public PackageDomain update(PackageDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public void delete(PackageDomain domain) {
		
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	@Override
	public List<PackageDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PackageDomain.class);
		Criterion Name = Restrictions.like("_name", text);
		criteria.add(Restrictions.or(Name));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}

	@Override
	public List<PackageDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PackageDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	

}