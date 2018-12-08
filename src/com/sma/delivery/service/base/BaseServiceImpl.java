package com.sma.delivery.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.sma.delivery.dao.base.BaseDaoImpl;
import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.base.BaseResult;

public abstract class BaseServiceImpl<DTO extends BaseDTO, DOMAIN extends BaseDomain, DAO extends BaseDaoImpl<DOMAIN>, RESULT extends BaseResult<DTO>> implements IBaseService<DTO, DOMAIN, DAO, RESULT> {

	@Autowired
	private CacheManager _cacheManager;

	protected CacheManager getCacheManager() {
		return _cacheManager;
	}
	protected abstract DTO convertDomainToDto(DOMAIN domain);

	protected abstract DOMAIN convertDtoToDomain(DTO dto);

}
