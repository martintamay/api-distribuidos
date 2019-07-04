package com.sma.delivery.service.promotions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.promotions.IPromotionsDao;
import com.sma.delivery.dao.promotions.PromotionsDaoImpl;
import com.sma.delivery.domain.promotions.PromotionsDomain;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionDTO;
import com.sma.delivery.dto.promotions.PromotionDTO;
import com.sma.delivery.dto.promotions.PromotionResult;
import com.sma.delivery.service.base.BaseServiceImpl;
import com.sma.delivery.service.product_has_promotions.IProductHasPromotionsService;
import com.sma.delivery.utils.ProyectProperties;

@Service
public class PromotionsServiceImpl extends BaseServiceImpl<PromotionDTO, PromotionsDomain, PromotionsDaoImpl, PromotionResult> implements IPromotionsService {
	private static final Logger LOGGER = Logger.getLogger( ProyectProperties.class.getName() );

	@Autowired
	private IPromotionsDao promotionsDao;

	@Autowired
	private IProductHasPromotionsService productHasPromotionsService;

	
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
		for(ProductHasPromotionDTO detail: dto.getProductHasPromotionsDTO()){
			detail.setPromotionId(newDto.getId());
			productHasPromotionsService.save(detail);
		}
		if (dto.getProductHasPromotionsDTO().isEmpty()) LOGGER.log(Level.INFO, "Promotion vac�o recibido");
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
			final PromotionDTO dto = convertDomainToDto(domain);
			promotions.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("promotionsA_" + dto.getId(), dto);
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
		promotions.setEndDate(domain.getEndDate().toString());
		return promotions;
	}

	@Override
	protected PromotionsDomain convertDtoToDomain(PromotionDTO dto) {
		final PromotionsDomain promotions = new PromotionsDomain();
		promotions.setId(dto.getId());
		promotions.setName(dto.getName());
		promotions.setAvailable(dto.getAvailable());
		promotions.setEndDate(Date.valueOf(dto.getEndDate()));
		return promotions;
	}

	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'promotionsA_' + #dto.id")
	public void delete(PromotionDTO dto) {
		final PromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		productHasPromotionsService.deleteByPromotion(dto.getId());
		promotionsDao.delete(promotionsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'promotionsA_' + #dto.id")
	public PromotionDTO update(PromotionDTO dto) {
		final PromotionsDomain promotionDomain = convertDtoToDomain(dto);
		final PromotionsDomain promotion = promotionsDao.update(promotionDomain);
		final PromotionDTO newDto = convertDomainToDto(promotion);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("promotionsA_" + promotion.getId(), newDto);
		}
		for(ProductHasPromotionDTO detail: dto.getProductHasPromotionsDTO()){
			detail.setPromotionId(newDto.getId());
			productHasPromotionsService.update(detail);
		}
		return convertDomainToDto(promotion);
	}

	@Override
	@Transactional
	public PromotionResult find(String text, Integer page, Integer size) {
		final List<PromotionDTO> promotions = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.find(text, page, size)) {
			final PromotionDTO dto = convertDomainToDto(domain);
			promotions.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("promotionsA_" + dto.getId(), dto);
			}
		}

		final PromotionResult userResult = new PromotionResult();
		userResult.setPromotions(promotions);
		return userResult;

	}
	@Override
	@Transactional
	public PromotionResult getAll(Integer page,Integer size) {
		final List<PromotionDTO> promotions = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.findAll(page, size)) {
			final PromotionDTO dto = convertDomainToDto(domain);
			promotions.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("promotionsA_" + dto.getId(), dto);
			}
		}

		final PromotionResult promotionsResult = new PromotionResult();
		promotionsResult.setPromotions(promotions);
		return promotionsResult;

	}

}