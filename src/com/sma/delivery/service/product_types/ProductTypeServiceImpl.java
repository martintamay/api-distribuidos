package com.sma.delivery.service.product_types;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.product_types.IProductTypeDao;
import com.sma.delivery.dao.product_types.ProductTypeDaoImpl;
import com.sma.delivery.domain.product_types.ProductTypeDomain;
import com.sma.delivery.dto.product_types.ProductTypesDTO;
import com.sma.delivery.dto.product_types.ProductTypesResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductTypesDTO, ProductTypeDomain, ProductTypeDaoImpl, ProductTypesResult> implements IProductTypeService {
	@Autowired
	private IProductTypeDao productTypeDao;
	
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsTypeA_' + #productsType.id", condition = "#dto.id!=null")
	public ProductTypesDTO save(ProductTypesDTO dto) {
		final ProductTypeDomain productsDomain = convertDtoToDomain(dto);
		final ProductTypeDomain product = productTypeDao.save(productsDomain);
		final ProductTypesDTO newDto = convertDomainToDto(product);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productsTypeA_" + product.getId(), newDto);
		}
		return convertDomainToDto(product);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'productsTypeA_' + #id")
	public ProductTypesDTO getById(Integer id) {
		final ProductTypeDomain productsDomain = productTypeDao.getById(id);
		return convertDomainToDto(productsDomain);
	}

	@Override
	@Transactional
	public ProductTypesResult getAll() {
		final List<ProductTypesDTO> products = new ArrayList<>();
		for (ProductTypeDomain domain : productTypeDao.findAll()) {
			final ProductTypesDTO user = convertDomainToDto(domain);
			products.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productsTypeA_" + user.getId(), user);
			}
		}

		final ProductTypesResult productsResult = new ProductTypesResult();
		productsResult.setProductsType(products);
		return productsResult;
	}

	@Override
	protected ProductTypesDTO convertDomainToDto(ProductTypeDomain domain) {
		final ProductTypesDTO product = new ProductTypesDTO();
		product.setId(domain.getId());
		product.setDescription(domain.getDescription());
		return product;
	}

	@Override
	protected ProductTypeDomain convertDtoToDomain(ProductTypesDTO dto) {
		final ProductTypeDomain product = new ProductTypeDomain();
		product.setId(dto.getId());
		product.setDescription(dto.getDescription());
	
		return product;
	}

	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'productsTypeA_' + #dto.id")
	public void delete(ProductTypesDTO dto) {
		final ProductTypeDomain productsDomain = convertDtoToDomain(dto);
		productTypeDao.delete(productsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsTypeA_' + #dto.id")
	public ProductTypesDTO update(ProductTypesDTO dto) {
		final ProductTypeDomain userDomain = convertDtoToDomain(dto);
		final ProductTypeDomain user = productTypeDao.update(userDomain);
		final ProductTypesDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productsTypeA_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	public ProductTypesResult find(String text, Integer page, Integer size) {
		final List<ProductTypesDTO> users = new ArrayList<>();
		for (ProductTypeDomain domain : productTypeDao.find(text, page, size)) {
			final ProductTypesDTO user = convertDomainToDto(domain);
			users.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productsTypeA_" + user.getId(), user);
			}
		}

		final ProductTypesResult userResult = new ProductTypesResult();
		userResult.setProductsType(users);
		return userResult;

	}
	@Override
	@Transactional
	public ProductTypesResult getAll(Integer page,Integer size) {
		final List<ProductTypesDTO> users = new ArrayList<>();
		for (ProductTypeDomain domain : productTypeDao.findAll(page, size)) {
			final ProductTypesDTO user = convertDomainToDto(domain);
			users.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productsTypeA_" + user.getId(), user);
			}
		}

		final ProductTypesResult productsResult = new ProductTypesResult();
		productsResult.setProductsType(users);
		return productsResult;

	}

}