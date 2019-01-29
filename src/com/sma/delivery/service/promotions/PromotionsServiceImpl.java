package com.sma.delivery.service.promotions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.promotions.IPromotionsDao;
import com.sma.delivery.dao.promotions.PromotionsDaoImpl;
import com.sma.delivery.domain.promotions.PromotionsDomain;
import com.sma.delivery.dto.promotions.PromotionsDTO;
import com.sma.delivery.dto.promotions.PromotionsResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class PromotionsServiceImpl extends BaseServiceImpl<PromotionsDTO, PromotionsDomain, PromotionsDaoImpl, PromotionsResult> implements IPromotionsService {
	@Autowired
	private IPromotionsDao promotionsDao;
	

	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'promotions_' + #romotions.id", condition = "#dto.id!=null")
	public PromotionsDTO save(PromotionsDTO dto) {
		final PromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		final PromotionsDomain promotions = promotionsDao.save(promotionsDomain);
		final PromotionsDTO newDto = convertDomainToDto(promotions);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("promotions_" + promotions.getId(), newDto);
		}
		return convertDomainToDto(promotions);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'promotions_' + #id")
	public PromotionsDTO getById(Integer id) {
		final PromotionsDomain promotionsDomain = promotionsDao.getById(id);
		final PromotionsDTO promotionsDTO = convertDomainToDto(promotionsDomain);
		return promotionsDTO;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'promotions_' + #id")
	public PromotionsResult getAll() {
		final List<PromotionsDTO> promotions = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.findAll()) {
			final PromotionsDTO user = convertDomainToDto(domain);
			promotions.add(user);
		}

		final PromotionsResult promotionsResult = new PromotionsResult();
		promotionsResult.setPromotions(promotions);
		return promotionsResult;
	}

	@Override
	protected PromotionsDTO convertDomainToDto(PromotionsDomain domain) {
		final PromotionsDTO promotions = new PromotionsDTO();
		promotions.setId(domain.getId());
		promotions.setName(domain.getName());
		promotions.setAvailable(domain.get_available());
		promotions.setEnd_date(domain.get_end_date());
		return promotions;
	}

	@Override
	protected PromotionsDomain convertDtoToDomain(PromotionsDTO dto) {
		final PromotionsDomain promotions = new PromotionsDomain();
		promotions.setId(dto.getId());
		promotions.setName(dto.getName());
		promotions.set_available(dto.getAvailable());
		promotions.set_end_date(dto.getEnd_date());
		return promotions;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'promotions_' + #dto.id")
	public void delete(PromotionsDTO dto) {
		final PromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		promotionsDao.delete(promotionsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'products_' + #dto.id")
	public PromotionsDTO update(PromotionsDTO dto) {
		final PromotionsDomain userDomain = convertDtoToDomain(dto);
		final PromotionsDomain user = promotionsDao.update(userDomain);
		final PromotionsDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("promotions_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_pro' + #text")
	public PromotionsResult find(String text, Integer page, Integer size) {
		final List<PromotionsDTO> users = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.find(text, page, size)) {
			final PromotionsDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final PromotionsResult userResult = new PromotionsResult();
		userResult.setPromotions(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public PromotionsResult getAll(Integer page,Integer size) {
		final List<PromotionsDTO> users = new ArrayList<>();
		for (PromotionsDomain domain : promotionsDao.findAll(page, size)) {
			final PromotionsDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final PromotionsResult promotionsResult = new PromotionsResult();
		promotionsResult.setPromotions(users);
		return promotionsResult;

	}

}