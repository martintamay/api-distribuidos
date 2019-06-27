package com.sma.delivery.service.product_has_promotions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.product_has_promotions.IProductHasPromotionsDao;
import com.sma.delivery.dao.product_has_promotions.ProductHasPromotionsDaoImpl;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.dao.promotions.IPromotionsDao;
import com.sma.delivery.domain.product_has_promotions.ProductHasPromotionsDomain;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionDTO;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class ProductHasPromotionsServiceImpl extends BaseServiceImpl<ProductHasPromotionDTO, ProductHasPromotionsDomain, ProductHasPromotionsDaoImpl, ProductHasPromotionResult> implements IProductHasPromotionsService {
	@Autowired
	private IProductHasPromotionsDao productHasPromotionsDao;
	@Autowired
	private IProductsDao productsDao;	
	@Autowired
	private IPromotionsDao promotionsDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productHasPromotionsA_' + #romotions.id", condition = "#dto.id!=null")
	public ProductHasPromotionDTO save(ProductHasPromotionDTO dto) {
		final ProductHasPromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		final ProductHasPromotionsDomain promotions = productHasPromotionsDao.save(promotionsDomain);
		final ProductHasPromotionDTO newDto = convertDomainToDto(promotions);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productHasPromotionsA_" + promotions.getId(), newDto);
		}
		return convertDomainToDto(promotions);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'productHasPromotionsA_' + #id")
	public ProductHasPromotionDTO getById(Integer id) {
		final ProductHasPromotionsDomain promotionsDomain = productHasPromotionsDao.getById(id);
		return convertDomainToDto(promotionsDomain);
	}

	@Override
	@Transactional
	public ProductHasPromotionResult getAll() {
		final List<ProductHasPromotionDTO> promotions = new ArrayList<>();
		for (ProductHasPromotionsDomain domain : productHasPromotionsDao.findAll()) {
			final ProductHasPromotionDTO dto = convertDomainToDto(domain);
			promotions.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productHasPromotionsA_" + dto.getId(), dto);
			}
		}

		final ProductHasPromotionResult promotionsResult = new ProductHasPromotionResult();
		return promotionsResult;
	}

	@Override
	protected ProductHasPromotionDTO convertDomainToDto(ProductHasPromotionsDomain domain) {
		final ProductHasPromotionDTO productHasPromotions = new ProductHasPromotionDTO();
		productHasPromotions.setId(domain.getId());
		productHasPromotions.setCost(domain.getCost());
		productHasPromotions.setCuantity(domain.getCuantity());
		productHasPromotions.setPromotionId(domain.getPromotion().getId());
		productHasPromotions.setProductId(domain.getProduct().getId());
		productHasPromotions.setComment(domain.getComment());
		return productHasPromotions;
	}

	@Override
	protected ProductHasPromotionsDomain convertDtoToDomain(ProductHasPromotionDTO dto) {
		final ProductHasPromotionsDomain productHasPromotions = new ProductHasPromotionsDomain();
		productHasPromotions.setId(dto.getId());
		productHasPromotions.setCost(dto.getCost());
		productHasPromotions.setCuantity(dto.getCuantity());
		productHasPromotions.setPromotion(promotionsDao.getById(dto.getPromotionId()));
		productHasPromotions.setProduct(productsDao.getById(dto.getProductId()));
		productHasPromotions.setComment(dto.getComment());
		return productHasPromotions;
	}

	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'productHasPromotionsA_' + #dto.id")
	public void delete(ProductHasPromotionDTO dto) {
		final ProductHasPromotionsDomain promotionsDomain = convertDtoToDomain(dto);
		productHasPromotionsDao.delete(promotionsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productHasPromotionsA_' + #dto.id")
	public ProductHasPromotionDTO update(ProductHasPromotionDTO dto) {
		final ProductHasPromotionsDomain productHasPromotionDomain = convertDtoToDomain(dto);
		final ProductHasPromotionsDomain productHasPromotion = productHasPromotionsDao.update(productHasPromotionDomain);
		final ProductHasPromotionDTO newDto = convertDomainToDto(productHasPromotion);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productHasPromotionsA_" + productHasPromotion.getId(), newDto);
		}
		return convertDomainToDto(productHasPromotion);
	}

	@Override
	@Transactional
	public ProductHasPromotionResult find(String text, Integer page, Integer size) {
		final List<ProductHasPromotionDTO> productsPromotion = new ArrayList<>();
		for (ProductHasPromotionsDomain domain : productHasPromotionsDao.find(text, page, size)) {
			final ProductHasPromotionDTO dto = convertDomainToDto(domain);
			productsPromotion.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productHasPromotionsA_" + dto.getId(), dto);
			}
		}
		final ProductHasPromotionResult productsPromotionResult = new ProductHasPromotionResult();
		productsPromotionResult.setProductHasPromotions(productsPromotion);
		return productsPromotionResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro_has_pro' + #page + #size")
	public ProductHasPromotionResult getAll(Integer page,Integer size) {
		final List<ProductHasPromotionDTO> producHasPromotions = new ArrayList<>();
		for (ProductHasPromotionsDomain domain : productHasPromotionsDao.findAll(page, size)) {
			final ProductHasPromotionDTO dto = convertDomainToDto(domain);
			producHasPromotions.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productHasPromotionsA_" + dto.getId(), dto);
			}
		}

		final ProductHasPromotionResult productsPromotionResult = new ProductHasPromotionResult();
		productsPromotionResult.setProductHasPromotions(producHasPromotions);
		return productsPromotionResult;

	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductHasPromotionResult getAllBy(Map<String,String> args){
		final List<ProductHasPromotionDTO> productPromotions = new ArrayList<>();
		for (ProductHasPromotionsDomain domain : productHasPromotionsDao.findAllBy(args)){
			final ProductHasPromotionDTO dto = convertDomainToDto(domain);
			productPromotions.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + dto.getId(), dto);
			}
		}
		final ProductHasPromotionResult productsPromotionResult = new ProductHasPromotionResult();
		productsPromotionResult.setProductHasPromotions(productPromotions);
		return productsPromotionResult;
	}

	@Override
	public void deleteByPromotion(Integer promotionId) {
		productHasPromotionsDao.deleteByPromotion(promotionId);
	}

}