package com.sma.delivery.service.roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.roles.IRolesDao;
import com.sma.delivery.dao.roles.RolesDaoImpl;
import com.sma.delivery.domain.roles.RolesDomain;
import com.sma.delivery.dto.roles.RoleDTO;
import com.sma.delivery.dto.roles.RoleResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class RolesServiceImpl extends BaseServiceImpl<RoleDTO, RolesDomain, RolesDaoImpl ,RoleResult> implements IRolesService {	
	@Autowired
	private IRolesDao rolesDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'roles_' + #bills.id", condition = "#dto.id!=null")
	public RoleDTO save(RoleDTO dto) {
		final RolesDomain rolesDomain = convertDtoToDomain(dto);
		final RolesDomain roles = rolesDao.save(rolesDomain);
		final RoleDTO newDto = convertDomainToDto(roles);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("roles_" + roles.getId(), newDto);
		}
		return convertDomainToDto(roles);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'roles_' + #id")
	public RoleDTO getById(Integer id) {
		final RolesDomain domain = rolesDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	public RoleResult getAll() {
		final List<RoleDTO> roles = new ArrayList<>();
		for (RolesDomain domain : rolesDao.findAll()) {
			final RoleDTO dto = convertDomainToDto(domain);
			roles.add(dto);
		}
		final RoleResult rolesResult = new RoleResult();
		rolesResult.setRoles(roles);
		return rolesResult;
	}

	@Override
	protected RoleDTO convertDomainToDto(RolesDomain domain) {
		final RoleDTO dto = new RoleDTO();
		dto.setId(domain.getId());
		dto.setName(domain.getName());

		return dto;
	}

	@Override
	protected RolesDomain convertDtoToDomain(RoleDTO dto) {
		final RolesDomain domain = new RolesDomain();
		domain.setId(dto.getId());
		domain.setName(dto.getName());
		
		return domain;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'roles_' + #dto.id")
	public RoleDTO update(RoleDTO dto) {
		final RolesDomain rolesDomain = convertDtoToDomain(dto);
		final RolesDomain role = rolesDao.update(rolesDomain);
		final RoleDTO newDto = convertDomainToDto(role);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("roles_" + role.getId(), newDto);
		}
		return convertDomainToDto(role);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'roles_' + #dto.id")
	public void delete(RoleDTO dto) {
		final RolesDomain rolesDomain = convertDtoToDomain(dto);
		rolesDao.delete(rolesDomain);	
	}
	
	@Override
	@Transactional
	public RoleResult find(String text, Integer page, Integer size) {
		final List<RoleDTO> roles = new ArrayList<>();
		for (RolesDomain domain : rolesDao.find(text, page, size)) {
			final RoleDTO role = convertDomainToDto(domain);
			roles.add(role);
		}

		final RoleResult rolesResult = new RoleResult();
		rolesResult.setRoles(roles);
		return rolesResult;
	}
	
	@Override
	@Transactional
	public RoleResult getAll(Integer page,Integer size) {
		final List<RoleDTO> roles = new ArrayList<>();
		for (RolesDomain domain : rolesDao.findAll(page,size)) {
			final RoleDTO dto = convertDomainToDto(domain);
			roles.add(dto);
		}
		final RoleResult rolesResult = new RoleResult();
		rolesResult.setRoles(roles);
		return rolesResult;
	}

}



