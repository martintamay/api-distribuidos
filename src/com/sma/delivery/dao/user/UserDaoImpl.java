package com.sma.delivery.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.user.UserDomain;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDomain> implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDomain save(UserDomain domain) {
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}
	@Override
	public UserDomain update(UserDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
	}
	@Override
	public void delete(UserDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}

	@Override
	public UserDomain getById(Integer domainId) {
		return (UserDomain) sessionFactory.getCurrentSession().get(UserDomain.class, domainId);
	}

	@Override
	public List<UserDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDomain.class);
		return criteria.list();
	}
	@Override
	public List<UserDomain> find(String text) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDomain.class);
		Criterion email = Restrictions.like("email", text, MatchMode.START);
		Criterion firstName = Restrictions.like("firstName", text, MatchMode.START);
		Criterion lastName = Restrictions.like("lastName", text, MatchMode.START);
		criteria.add(Restrictions.or(firstName,email,lastName));
		return criteria.list();
	}
	@Override
	public List<UserDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserDomain.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}

}
