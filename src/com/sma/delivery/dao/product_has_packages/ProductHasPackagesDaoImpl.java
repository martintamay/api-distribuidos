package com.sma.delivery.dao.product_has_packages;

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
import com.sma.delivery.domain.product_has_packages.ProductHasPackagesDomain;

@Repository
public class ProductHasPackagesDaoImpl extends BaseDaoImpl<ProductHasPackagesDomain> implements IProductHasPackagesDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductHasPackagesDomain save(ProductHasPackagesDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}

	@Override
	public ProductHasPackagesDomain getById(Integer domainId) {
		return (ProductHasPackagesDomain) sessionFactory.getCurrentSession().get(ProductHasPackagesDomain.class, domainId);
	}

	@Override
	public List<ProductHasPackagesDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductHasPackagesDomain.class);
		return safeConversion(criteria.list(), ProductHasPackagesDomain.class);	
	}

	@Override
	public ProductHasPackagesDomain update(ProductHasPackagesDomain domain) {
		return save(domain);
	}

	@Override
	public void delete(ProductHasPackagesDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}
	
	@Override
	public List<ProductHasPackagesDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductHasPackagesDomain.class);
		Criterion name = Restrictions.like("name", text);
		criteria.add(Restrictions.or(name));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), ProductHasPackagesDomain.class);	
	}

	@Override
	public List<ProductHasPackagesDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductHasPackagesDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), ProductHasPackagesDomain.class);	
	}

	@Override
	public List<ProductHasPackagesDomain> findAllBy(Map<String, String> args) {
		if(args.containsKey("packagesId")){
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from ProductHasPackages where packages_id=:packagesId");
			query.addEntity(ProductHasPackagesDomain.class); // Define el tipo de resultado de la consulta
			query.setString("packagesId", args.get("packagesId"));
			return query.list();
		}
		return new ArrayList<>();
	}

	@Override
	public void deleteByPackages(Integer packagesId) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("delete from ProductHasPackages where packages_id =:packagesId");
		query.addEntity(ProductHasPackagesDomain.class);
		query.setInteger("packagesId", packagesId);
		query.executeUpdate();
	}
}