package com.sma.delivery.service.promotions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.promotions.IPromotionsDao;
import com.sma.delivery.dao.promotions.PromotionsDaoImpl;
import com.sma.delivery.domain.promotions.PromotionsDomain;
import com.sma.delivery.dto.promotions.PromotionDTO;
import com.sma.delivery.dto.promotions.PromotionResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class PromotionsServiceImpl extends BaseServiceImpl<PromotionDTO, PromotionsDomain, PromotionsDaoImpl, PromotionResult> implements IPromotionsService {
	@Autowired
	private IPromotionsDao promotionsDao;
	

	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'promotionsA_' + #romotions.id", condition = "#dto.id!=null")
	public PromotionDTO save(PromotionDTO dto) {
		final PromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		final PromotionsDomain promotions = promotionsDao.save(promotionsDomain);
		final PromotionDTO newDto = convertDomainToDto(promotions);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("promotionsA_" + promotions.getId(), newDto);
		}
		return convertDomainToDto(promotions);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'promotionsA_' + #id")
	public PromotionDTO getById(Integer id) {
		final PromotionsDomain promotionsDomain = promotionsDao.getById(id);
		return convertDomainToDto(promotionsDomain);
	}

	@Override
	@Transactional
	public PromotionResult getAll() {
		final List<PromotionDTO> promotions = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.findAll()) {
			final PromotionDTO user = convertDomainToDto(domain);
			promotions.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("promotionsA_" + user.getId(), user);
			}
		}

		final PromotionResult promotionsResult = new PromotionResult();
		promotionsResult.setPromotions(promotions);
		return promotionsResult;
	}

	@Override
	protected PromotionDTO convertDomainToDto(PromotionsDomain domain) {
		final PromotionDTO promotions = new PromotionDTO();
		promotions.setId(domain.getId());
		promotions.setName(domain.getName());
		promotions.setAvailable(domain.getAvailable());
		promotions.setEndDate(domain.getEndDate());
		return promotions;
	}

	@Override
	protected PromotionsDomain convertDtoToDomain(PromotionDTO dto) {
		final PromotionsDomain promotions = new PromotionsDomain();
		promotions.setId(dto.getId());
		promotions.setName(dto.getName());
		promotions.setAvailable(dto.getAvailable());
		promotions.setEndDate(dto.getEndDate());
		return promotions;
	}

	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'promotionsA_' + #dto.id")
	public void delete(PromotionDTO dto) {
		final PromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		promotionsDao.delete(promotionsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'promotionsA_' + #dto.id")
	public PromotionDTO update(PromotionDTO dto) {
		final PromotionsDomain userDomain = convertDtoToDomain(dto);
		final PromotionsDomain user = promotionsDao.update(userDomain);
		final PromotionDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("promotionsA_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	public PromotionResult find(String text, Integer page, Integer size) {
		final List<PromotionDTO> users = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.find(text, page, size)) {
			final PromotionDTO user = convertDomainToDto(domain);
			users.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("promotionsA_" + user.getId(), user);
			}
		}

		final PromotionResult userResult = new PromotionResult();
		userResult.setPromotions(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public PromotionResult getAll(Integer page,Integer size) {
		final List<PromotionDTO> users = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.findAll(page, size)) {
			final PromotionDTO user = convertDomainToDto(domain);
			users.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("promotionsA_" + user.getId(), user);
			}
		}

		final PromotionResult promotionsResult = new PromotionResult();
		promotionsResult.setPromotions(users);
		return promotionsResult;

	}

}