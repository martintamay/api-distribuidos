package com.sma.delivery.service.establishments;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.establishments.EstablishmentsDaoImpl;
import com.sma.delivery.dao.establishments.IEstablishmentsDao;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.dto.establishments.EstablishmentsDTO;
import com.sma.delivery.dto.establishments.EstablishmentsResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class EstablishmentsServiceImpl extends BaseServiceImpl<EstablishmentsDTO, EstablishmentsDomain, EstablishmentsDaoImpl, EstablishmentsResult> implements IEstablishmentsService {
	@Autowired
	private IEstablishmentsDao establishmentsDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'establishments_' + #establishments.id", condition = "#dto.id!=null")
	public EstablishmentsDTO save(EstablishmentsDTO dto) {
		final EstablishmentsDomain domain = convertDtoToDomain(dto);
		final EstablishmentsDomain establishmentsDomain = establishmentsDao.save(domain);
		final EstablishmentsDTO newDto = convertDomainToDto(establishmentsDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("establishments_" + establishmentsDomain.getId(), newDto);
		}
		return convertDomainToDto(establishmentsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'establishments_' + #id")
	public EstablishmentsDTO getById(Integer id) {
		final EstablishmentsDomain domain = establishmentsDao.getById(id);
		final EstablishmentsDTO dto = convertDomainToDto(domain);
		return dto;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'establishments_' + #id")
	public EstablishmentsResult getAll() {
		final List<EstablishmentsDTO> countries = new ArrayList<>();
		for (EstablishmentsDomain domain : establishmentsDao.findAll()) {
			final EstablishmentsDTO dto = convertDomainToDto(domain);
			countries.add(dto);
		}
		final EstablishmentsResult establishmentsResult = new EstablishmentsResult();
		establishmentsResult.setEstablishments(countries);
		return establishmentsResult;
	}

	@Override
	protected EstablishmentsDTO convertDomainToDto(EstablishmentsDomain domain) {
		final EstablishmentsDTO dto = new EstablishmentsDTO();
		dto.setId(domain.getId());
		dto.set_name(domain.getName());
		return dto;
	}

	@Override
	protected EstablishmentsDomain convertDtoToDomain(EstablishmentsDTO dto) {
		final EstablishmentsDomain domain = new EstablishmentsDomain();
		domain.setId(dto.getId());
		domain.setName(dto.get_name());
		return domain;
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'establishments_' + #dto.id")
	public void delete(EstablishmentsDTO dto) {
		final EstablishmentsDomain establishmentsDomain = convertDtoToDomain(dto);
		establishmentsDao.delete(establishmentsDomain);
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'establishments_' + #dto.id")
	public EstablishmentsDTO update(EstablishmentsDTO dto) {
		final EstablishmentsDomain establishmentsDomain = convertDtoToDomain(dto);
		final EstablishmentsDomain establishments = establishmentsDao.update(establishmentsDomain);
		final EstablishmentsDTO newDto = convertDomainToDto(establishments);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("establishments_" + establishments.getId(), newDto);
		}
		return convertDomainToDto(establishments);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_est' + #text")
	public EstablishmentsResult find(String text) {
		final List<EstablishmentsDTO> establishments = new ArrayList<>();
		for (EstablishmentsDomain domain : establishmentsDao.find(text)) {
			final EstablishmentsDTO establishment = convertDomainToDto(domain);
			establishments.add(establishment);
		}
 		final EstablishmentsResult establishmentsResult = new EstablishmentsResult();
		establishmentsResult.setEstablishments(establishments);
		return establishmentsResult;
 	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_est' + #page + #size")
	public EstablishmentsResult getAll(Integer page,Integer size) {
		final List<EstablishmentsDTO> countries = new ArrayList<>();
		for (EstablishmentsDomain domain : establishmentsDao.findAll(page,size)) {
			final EstablishmentsDTO dto = convertDomainToDto(domain);
			countries.add(dto);
		}
		final EstablishmentsResult establishmentsResult = new EstablishmentsResult();
		establishmentsResult.setEstablishments(countries);
		return establishmentsResult;
	}

}
