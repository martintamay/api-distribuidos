package com.sma.delivery.dao.base;

import java.util.List;

import com.sma.delivery.domain.base.BaseDomain;

public interface IBaseDao<DOMAIN extends BaseDomain> {

	public DOMAIN save(DOMAIN domain);

	public DOMAIN getById(Integer domainId);

	public List<DOMAIN> findAll();

	public List<DOMAIN> findAll(Integer page,Integer size);
	
	public DOMAIN update(DOMAIN domain);
	
	public void delete(DOMAIN domain);

	public List<DOMAIN> find(String text);
	



}
