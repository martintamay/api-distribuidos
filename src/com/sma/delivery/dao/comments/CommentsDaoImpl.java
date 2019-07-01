package com.sma.delivery.dao.comments;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.comments.CommentsDomain;

@Repository
public class CommentsDaoImpl extends BaseDaoImpl<CommentsDomain> implements ICommentsDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CommentsDomain save(CommentsDomain domain) {
		sessionFactory.getCurrentSession().save(domain);
		return domain;
	}

	@Override
	public CommentsDomain getById(Integer domainId) {
		return (CommentsDomain) sessionFactory.getCurrentSession().get(CommentsDomain.class, domainId);
	}

	@Override
	public List<CommentsDomain> findAll() {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class);
		return safeConversion(criteria.list(), CommentsDomain.class);
	}

	@Override
	public CommentsDomain update(CommentsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(CommentsDomain domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}


	

	@Override
	public List<CommentsDomain> findByParams(Integer page, Integer maxPage) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class).setMaxResults(maxPage).setFirstResult(page * maxPage);
		return safeConversion(criteria.list(), CommentsDomain.class);
	}

	@Override
	public List<CommentsDomain> find(String text, Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class);
		Criterion title = Restrictions.like("title", text, MatchMode.START);
		Criterion content = Restrictions.like("content", text, MatchMode.START);
		criteria.add(Restrictions.or(title,content));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), CommentsDomain.class);
	}
	@Override
	public List<CommentsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return safeConversion(criteria.list(), CommentsDomain.class);
	}

}
