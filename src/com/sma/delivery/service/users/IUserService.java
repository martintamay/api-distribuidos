package com.sma.delivery.service.users;

import com.sma.delivery.dao.users.UserDaoImpl;
import com.sma.delivery.domain.user.UserDomain;
import com.sma.delivery.dto.roles.RoleResult;
import com.sma.delivery.dto.users.UserDTO;
import com.sma.delivery.dto.users.UserResult;
import com.sma.delivery.service.base.IBaseService;

public interface IUserService extends IBaseService<UserDTO, UserDomain, UserDaoImpl, UserResult> {
	public RoleResult getRoles(Integer userId);

	public RoleResult addRole(Integer userId, Integer roleId);
}
