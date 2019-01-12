package com.sma.delivery.dao.comments;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
		return criteria.list();
	}

	@Override
	public CommentsDomain update(CommentsDomain domain) {
		sessionFactory.getCurrentSession().saveOrUpdate(domain);
		return domain;
		
	}

	@Override
	public void delete(CommentsDomain domain) {
		System.out.println(domain.getId());
		sessionFactory.getCurrentSession().delete(domain);
	}


	

	@Override
	public List<CommentsDomain> findByParams(Integer page, Integer maxPage) {
		// TODO Auto-generated method stub
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class).setMaxResults(maxPage).setFirstResult(page * maxPage);
		return criteria.list();
	}

	@Override
	public List<CommentsDomain> find(String text, Integer page, Integer size) {
		// TODO Auto-generated method stub
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class);
		Criterion title = Restrictions.like("_title", text);
		Criterion content = Restrictions.like("_content", text);
		criteria.add(Restrictions.or(title,content));
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
		
	}
	@Override
	public List<CommentsDomain> findAll(Integer page, Integer size) {
		final Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CommentsDomain.class);
		
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		return criteria.list();
	}
	

}
