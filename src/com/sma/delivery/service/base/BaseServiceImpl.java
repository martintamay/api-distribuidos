package com.sma.delivery.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.base.BaseResult;

public abstract class BaseServiceImpl<D extends BaseDTO, O extends BaseDomain, A extends BaseDaoImpl<O>, R extends BaseResult<D>> implements IBaseService<D, O, A, R> {

	@Autowired
	private CacheManager cacheManager;

	protected CacheManager getCacheManager() {
		return cacheManager;
	}
	protected abstract D convertDomainToDto(O domain);

	protected abstract O convertDtoToDomain(D dto);

}
