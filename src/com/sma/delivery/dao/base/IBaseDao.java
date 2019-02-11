package com.sma.delivery.dao.base;

import java.util.List;

import com.sma.delivery.domain.base.BaseDomain;

public interface IBaseDao<D extends BaseDomain> {

	public D save(D domain);

	public D getById(Integer domainId);

	public List<D> findAll();

	public List<D> findAll(Integer page,Integer size);
	
	public D update(D domain);
	
	public void delete(D domain);

	public List<D> find(String text,Integer page,Integer size);	



}
