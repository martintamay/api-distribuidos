package com.sma.delivery.service.roles;

import com.sma.delivery.dao.roles.RolesDaoImpl;
import com.sma.delivery.domain.roles.RolesDomain;
import com.sma.delivery.dto.roles.RoleDTO;
import com.sma.delivery.dto.roles.RoleResult;
import com.sma.delivery.service.base.IBaseService;

public interface IRolesService extends IBaseService<RoleDTO, RolesDomain, RolesDaoImpl ,RoleResult> {
}

