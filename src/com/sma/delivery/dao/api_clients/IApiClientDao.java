package com.sma.delivery.dao.api_clients;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.api_clients.ApiClientDomain;

public interface IApiClientDao extends IBaseDao<ApiClientDomain> {
	public ApiClientDomain getByToken(String token);
}
