package com.sma.delivery.service.base;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.base.BaseResult;

public interface IBaseService<D extends BaseDTO, O extends BaseDomain, A extends BaseDaoImpl<O>, R extends BaseResult<D>> {
	public D save(D dto);

	public D getById(Integer id);

	public R getAll();
	
	public R getAll(Integer page,Integer size);
	
	public void delete(D dto);

	public D update(D dto);

	public R find(String text,Integer page,Integer size);
}
