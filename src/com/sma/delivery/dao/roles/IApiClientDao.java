package com.sma.delivery.dao.roles;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.roles.ApiClientDomain;

public interface IApiClientDao extends IBaseDao<ApiClientDomain> {
	public ApiClientDomain getByToken(String token);
}
