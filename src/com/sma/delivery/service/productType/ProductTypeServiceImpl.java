package com.sma.delivery.service.productType;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;










import com.sma.delivery.dao.productType.IProductTypeDao;
import com.sma.delivery.dao.productType.ProductTypeDaoImpl;
import com.sma.delivery.domain.productType.ProductTypeDomain;
import com.sma.delivery.dto.productType.ProductTypeDTO;
import com.sma.delivery.dto.productType.ProductTypeResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductTypeDTO, ProductTypeDomain, ProductTypeDaoImpl, ProductTypeResult> implements IProductTypeService {
	@Autowired
	private IProductTypeDao productTypeDao;
	
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsType_' + #productsType.id", condition = "#dto.id!=null")
	public ProductTypeDTO save(ProductTypeDTO dto) {
		final ProductTypeDomain productsDomain = convertDtoToDomain(dto);
		final ProductTypeDomain product = productTypeDao.save(productsDomain);
		final ProductTypeDTO newDto = convertDomainToDto(product);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productsType_" + product.getId(), newDto);
		}
		return convertDomainToDto(product);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'productsType_' + #id")
	public ProductTypeDTO getById(Integer id) {
		final ProductTypeDomain productsDomain = productTypeDao.getById(id);
		final ProductTypeDTO productsDTO = convertDomainToDto(productsDomain);
		return productsDTO;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'productsType_' + #id")
	public ProductTypeResult getAll() {
		final List<ProductTypeDTO> products = new ArrayList<>();
		for (ProductTypeDomain domain : productTypeDao.findAll()) {
			final ProductTypeDTO user = convertDomainToDto(domain);
			products.add(user);
		}

		final ProductTypeResult productsResult = new ProductTypeResult();
		productsResult.setProductsType(products);
		return productsResult;
	}

	@Override
	protected ProductTypeDTO convertDomainToDto(ProductTypeDomain domain) {
		final ProductTypeDTO product = new ProductTypeDTO();
		product.setId(domain.getId());
		product.setDescription(domain.getDescription());
		return product;
	}

	@Override
	protected ProductTypeDomain convertDtoToDomain(ProductTypeDTO dto) {
		final ProductTypeDomain product = new ProductTypeDomain();
		product.setId(dto.getId());
		product.setDescription(dto.getDescription());
	
		return product;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsType_' + #dto.id")
	public void delete(ProductTypeDTO dto) {
		final ProductTypeDomain productsDomain = convertDtoToDomain(dto);
		productTypeDao.delete(productsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsType_' + #dto.id")
	public ProductTypeDTO update(ProductTypeDTO dto) {
		final ProductTypeDomain userDomain = convertDtoToDomain(dto);
		final ProductTypeDomain user = productTypeDao.update(userDomain);
		final ProductTypeDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productsType_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_pro' + #text")
	public ProductTypeResult find(String text) {
		final List<ProductTypeDTO> users = new ArrayList<>();
		for (ProductTypeDomain domain : productTypeDao.find(text)) {
			final ProductTypeDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final ProductTypeResult userResult = new ProductTypeResult();
		userResult.setProductsType(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public ProductTypeResult getAll(Integer page,Integer size) {
		final List<ProductTypeDTO> users = new ArrayList<>();
		for (ProductTypeDomain domain : productTypeDao.findAll(page, size)) {
			final ProductTypeDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final ProductTypeResult productsResult = new ProductTypeResult();
		productsResult.setProductsType(users);
		return productsResult;

	}

}