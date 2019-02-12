package com.sma.delivery.service.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.establishments.IEstablishmentsDao;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.dao.products.ProductsDaoImpl;
import com.sma.delivery.domain.products.ProductsDomain;
import com.sma.delivery.dto.products.ProductDTO;
import com.sma.delivery.dto.products.ProductResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class ProductsServiceImpl extends BaseServiceImpl<ProductDTO, ProductsDomain, ProductsDaoImpl, ProductResult> implements IProductsService {
	@Autowired
	private IProductsDao productsDao;
	@Autowired
	private IEstablishmentsDao establishmentsDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'products_' + #products.id", condition = "#dto.id!=null")
	public ProductDTO save(ProductDTO dto) {
		final ProductsDomain productsDomain = convertDtoToDomain(dto);
		final ProductsDomain product = productsDao.save(productsDomain);
		final ProductDTO newDto = convertDomainToDto(product);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("products_" + product.getId(), newDto);
		}
		return convertDomainToDto(product);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'products_' + #id")
	public ProductDTO getById(Integer id) {
		final ProductsDomain productsDomain = productsDao.getById(id);
		return convertDomainToDto(productsDomain);
	}

	@Override
	@Transactional
	public ProductResult getAll() {
		final List<ProductDTO> products = new ArrayList<>();
		for (ProductsDomain domain : productsDao.findAll()) {
			final ProductDTO user = convertDomainToDto(domain);
			products.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("products_" + user.getId(), user);
			}
		}

		final ProductResult productsResult = new ProductResult();
		productsResult.setProducts(products);
		return productsResult;
	}

	@Override
	protected ProductDTO convertDomainToDto(ProductsDomain domain) {
		final ProductDTO product = new ProductDTO();
		product.setId(domain.getId());
		product.setName(domain.getName());
		product.setDescription(domain.getDescription());
		product.setCost((domain.getCost()));
		product.setEstablishmentId(domain.getEstablisment().getId());
		return product;
	}

	@Override
	protected ProductsDomain convertDtoToDomain(ProductDTO dto) {
		final ProductsDomain product = new ProductsDomain();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setCost((dto.getCost()));
		product.setEstablisment(establishmentsDao.getById(dto.getEstablishmentId()));
		return product;
	}

	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'products_' + #dto.id")
	public void delete(ProductDTO dto) {
		final ProductsDomain productsDomain = convertDtoToDomain(dto);
		productsDao.delete(productsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'products_' + #dto.id")
	public ProductDTO update(ProductDTO dto) {
		final ProductsDomain userDomain = convertDtoToDomain(dto);
		final ProductsDomain user = productsDao.update(userDomain);
		final ProductDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("products_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	public ProductResult find(String text, Integer page, Integer size) {
		final List<ProductDTO> users = new ArrayList<>();
		
		for (ProductsDomain domain : productsDao.find(text, page, size)) {
			final ProductDTO user = convertDomainToDto(domain);
			users.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("products_" + user.getId(), user);
			}
		}

		final ProductResult userResult = new ProductResult();
		userResult.setProducts(users);
		return userResult;

	}
	@Override
	@Transactional
	public ProductResult getAll(Integer page,Integer size) {
		final List<ProductDTO> users = new ArrayList<>();		
		
		for (ProductsDomain domain : productsDao.findAll(page, size)) {
			final ProductDTO user = convertDomainToDto(domain);
			users.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("products_" + user.getId(), user);
			}
		}

		final ProductResult productsResult = new ProductResult();
		productsResult.setProducts(users);
		return productsResult;

	}

}