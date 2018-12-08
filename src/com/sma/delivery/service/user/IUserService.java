package com.sma.delivery.service.user;

import com.sma.delivery.dao.user.UserDaoImpl;
import com.sma.delivery.domain.user.UserDomain;
import com.sma.delivery.dto.user.UserDTO;
import com.sma.delivery.dto.user.UserResult;
import com.sma.delivery.service.base.IBaseService;

public interface IUserService extends IBaseService<UserDTO, UserDomain, UserDaoImpl, UserResult> {

}
