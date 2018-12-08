package com.sma.delivery.service.base;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.base.BaseResult;
import com.sma.delivery.dto.user.UserResult;

public interface IBaseService<DTO extends BaseDTO, DOMAIN extends BaseDomain, DAO extends BaseDaoImpl<DOMAIN>, R extends BaseResult<DTO>> {
	public DTO save(DTO dto);

	public DTO getById(Integer id);

	public R getAll();
	
	public R getAll(Integer page,Integer size);
	
	public void delete(DTO dto);

	public DTO update(DTO dto);

	public R find(String text);
}
