package com.sma.delivery.service.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.user.UserDaoImpl;
import com.sma.delivery.dao.user.IUserDao;
import com.sma.delivery.domain.user.UserDomain;
import com.sma.delivery.dto.products.ProductsDTO;
import com.sma.delivery.dto.user.UserDTO;
import com.sma.delivery.dto.user.UserResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDomain, UserDaoImpl, UserResult> implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'user_' + #user.id", condition = "#dto.id!=null")
	public UserDTO save(UserDTO dto) {
		final UserDomain userDomain = convertDtoToDomain(dto);
		final UserDomain user = userDao.save(userDomain);
		final UserDTO newDto = convertDomainToDto(userDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("user_" + userDomain.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'user_' + #id")
	public UserDTO getById(Integer id) {
		final UserDomain userDomain = userDao.getById(id);
		final UserDTO userDTO = convertDomainToDto(userDomain);
		return userDTO;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'user_' + #id")
	public UserResult getAll() {
		final List<UserDTO> users = new ArrayList<>();
		for (UserDomain domain : userDao.findAll()) {
			final UserDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final UserResult userResult = new UserResult();
		userResult.setUsers(users);
		return userResult;
	}

	@Override
	protected UserDTO convertDomainToDto(UserDomain domain) {
		final UserDTO user = new UserDTO();
		user.setId(domain.getId());
		user.setFirstName(domain.getFirstName());
		user.setLastName(domain.getLastName());
		user.setEmail(domain.getEmail());
		user.setPassword(domain.getPassword());
		user.setAddress(domain.getAddress());
		user.setPhoneNumber(domain.getPhoneNumber());
		return user;
	}

	@Override
	protected UserDomain convertDtoToDomain(UserDTO dto) {
		final UserDomain user = new UserDomain();
		user.setId(dto.getId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword(), dto.getId());
		user.setAddress(dto.getAddress());
		user.setPhoneNumber(dto.getPhoneNumber());
		user.setCreationDate(new Timestamp(new Date().getTime()));
		return user;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'user_' + #dto.id")
	public void delete(UserDTO dto) {
		final UserDomain userDomain = convertDtoToDomain(dto);
		userDao.delete(userDomain);
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'user_' + #dto.id")
	public UserDTO update(UserDTO dto) {
		final UserDomain userDomain = convertDtoToDomain(dto);
		final UserDomain user = userDao.update(userDomain);
		final UserDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("user_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_use' + #text")
	public UserResult find(String text,Integer page,Integer size) {
		final List<UserDTO> users = new ArrayList<>();
		for (UserDomain domain : userDao.find(text,page,size)) {
			final UserDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final UserResult userResult = new UserResult();
		userResult.setUsers(users);
		return userResult;

	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_use' + #page + #size")
	public UserResult getAll(Integer page,Integer size) {
		final List<UserDTO> users = new ArrayList<>();
		for (UserDomain domain : userDao.findAll(page, size)) {
			final UserDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final UserResult userResult = new UserResult();
		userResult.setUsers(users);
		return userResult;

	}

}
