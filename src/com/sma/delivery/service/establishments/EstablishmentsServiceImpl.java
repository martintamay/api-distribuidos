package com.sma.delivery.service.establishments;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.establishments.EstablishmentsDaoImpl;
import com.sma.delivery.dao.establishments.IEstablishmentsDao;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.dto.establishments.EstablishmentDTO;
import com.sma.delivery.dto.establishments.EstablishmentResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class EstablishmentsServiceImpl extends BaseServiceImpl<EstablishmentDTO, EstablishmentsDomain, EstablishmentsDaoImpl, EstablishmentResult> implements IEstablishmentsService {
	@Autowired
	private IEstablishmentsDao establishmentsDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'establishmentsA_' + #establishments.id", condition = "#dto.id!=null")
	public EstablishmentDTO save(EstablishmentDTO dto) {
		final EstablishmentsDomain domain = convertDtoToDomain(dto);
		final EstablishmentsDomain establishmentsDomain = establishmentsDao.save(domain);
		final EstablishmentDTO newDto = convertDomainToDto(establishmentsDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("establishmentsA_" + establishmentsDomain.getId(), newDto);
		}
		return convertDomainToDto(establishmentsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'establishmentsA_' + #id")
	public EstablishmentDTO getById(Integer id) {
		final EstablishmentsDomain domain = establishmentsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'establishmentsA_' + #id")
	public EstablishmentResult getAll() {
		final List<EstablishmentDTO> countries = new ArrayList<>();
		for (EstablishmentsDomain domain : establishmentsDao.findAll()) {
			final EstablishmentDTO dto = convertDomainToDto(domain);
			countries.add(dto);
		}
		final EstablishmentResult establishmentsResult = new EstablishmentResult();
		establishmentsResult.setEstablishments(countries);
		return establishmentsResult;
	}

	@Override
	protected EstablishmentDTO convertDomainToDto(EstablishmentsDomain domain) {
		final EstablishmentDTO dto = new EstablishmentDTO();
		dto.setId(domain.getId());
		dto.setName(domain.getName());
		dto.setAddress(domain.getAddress());
		dto.setDescription(domain.getDescription());
		dto.setEmail(domain.getEmail());
		dto.setPhoneNumber(domain.getPhoneNumber());
		dto.setSchedule(domain.getSchedule());
		return dto;
	}

	@Override
	protected EstablishmentsDomain convertDtoToDomain(EstablishmentDTO dto) {
		final EstablishmentsDomain domain = new EstablishmentsDomain();
		domain.setId(dto.getId());
		domain.setName(dto.getName());
		domain.setAddress(dto.getAddress());
		domain.setDescription(dto.getDescription());
		domain.setEmail(dto.getEmail());
		domain.setPhoneNumber(dto.getPhoneNumber());
		domain.setSchedule(dto.getSchedule());
		return domain;
	}
	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'establishmentsA_' + #dto.id")
	public void delete(EstablishmentDTO dto) {
		final EstablishmentsDomain establishmentsDomain = convertDtoToDomain(dto);
		establishmentsDao.delete(establishmentsDomain);
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'establishmentAs_' + #dto.id")
	public EstablishmentDTO update(EstablishmentDTO dto) {
		final EstablishmentsDomain establishmentsDomain = convertDtoToDomain(dto);
		final EstablishmentsDomain establishments = establishmentsDao.update(establishmentsDomain);
		final EstablishmentDTO newDto = convertDomainToDto(establishments);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("establishmentsA_" + establishments.getId(), newDto);
		}
		return convertDomainToDto(establishments);
	}

	@Override
	@Transactional
	public EstablishmentResult find(String text, Integer page, Integer size) {
		final List<EstablishmentDTO> establishments = new ArrayList<>();
		for (EstablishmentsDomain domain : establishmentsDao.find(text, page, size)) {
			final EstablishmentDTO establishment = convertDomainToDto(domain);
			establishments.add(establishment);
			if (establishment.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("establishmentsA_" + establishment.getId(), establishment);
			}
		}
 		final EstablishmentResult establishmentsResult = new EstablishmentResult();
		establishmentsResult.setEstablishments(establishments);
		return establishmentsResult;
 	}
	@Override
	@Transactional
	public EstablishmentResult getAll(Integer page,Integer size) {
		final List<EstablishmentDTO> countries = new ArrayList<>();
		for (EstablishmentsDomain domain : establishmentsDao.findAll(page,size)) {
			final EstablishmentDTO dto = convertDomainToDto(domain);
			countries.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("establishmentsA_" + dto.getId(), dto);
			}
		}
		final EstablishmentResult establishmentsResult = new EstablishmentResult();
		establishmentsResult.setEstablishments(countries);
		return establishmentsResult;
	}

}
