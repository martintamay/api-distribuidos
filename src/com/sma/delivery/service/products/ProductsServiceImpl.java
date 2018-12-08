package com.sma.delivery.service.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sma.delivery.dao.products.ProductsDaoImpl;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.domain.products.ProductsDomain;
import com.sma.delivery.dto.products.ProductsDTO;
import com.sma.delivery.dto.products.ProductsResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class ProductsServiceImpl extends BaseServiceImpl<ProductsDTO, ProductsDomain, ProductsDaoImpl, ProductsResult> implements IProductsService {
	@Autowired
	private IProductsDao productsDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'products_' + #products.id", condition = "#dto.id!=null")
	public ProductsDTO save(ProductsDTO dto) {
		final ProductsDomain productsDomain = convertDtoToDomain(dto);
		final ProductsDomain product = productsDao.save(productsDomain);
		final ProductsDTO newDto = convertDomainToDto(product);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("products_" + product.getId(), newDto);
		}
		return convertDomainToDto(product);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'products_' + #id")
	public ProductsDTO getById(Integer id) {
		final ProductsDomain productsDomain = productsDao.getById(id);
		final ProductsDTO productsDTO = convertDomainToDto(productsDomain);
		return productsDTO;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'products_' + #id")
	public ProductsResult getAll() {
		final List<ProductsDTO> products = new ArrayList<>();
		for (ProductsDomain domain : productsDao.findAll()) {
			final ProductsDTO user = convertDomainToDto(domain);
			products.add(user);
		}

		final ProductsResult productsResult = new ProductsResult();
		productsResult.setProducts(products);
		return productsResult;
	}

	@Override
	protected ProductsDTO convertDomainToDto(ProductsDomain domain) {
		final ProductsDTO product = new ProductsDTO();
		product.setId(domain.getId());
		product.setName(domain.getName());
		product.setDescription(domain.getDescription());
		product.setCost((domain.getCost()));
		return product;
	}

	@Override
	protected ProductsDomain convertDtoToDomain(ProductsDTO dto) {
		final ProductsDomain product = new ProductsDomain();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setCost((dto.getCost()));
		return product;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'products_' + #dto.id")
	public void delete(ProductsDTO dto) {
		final ProductsDomain productsDomain = convertDtoToDomain(dto);
		productsDao.delete(productsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'products_' + #dto.id")
	public ProductsDTO update(ProductsDTO dto) {
		final ProductsDomain userDomain = convertDtoToDomain(dto);
		final ProductsDomain user = productsDao.update(userDomain);
		final ProductsDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("products_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_pro' + #text")
	public ProductsResult find(String text) {
		final List<ProductsDTO> users = new ArrayList<>();
		for (ProductsDomain domain : productsDao.find(text)) {
			final ProductsDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final ProductsResult userResult = new ProductsResult();
		userResult.setProducts(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public ProductsResult getAll(Integer page,Integer size) {
		final List<ProductsDTO> users = new ArrayList<>();
		for (ProductsDomain domain : productsDao.findAll(page, size)) {
			final ProductsDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final ProductsResult productsResult = new ProductsResult();
		productsResult.setProducts(users);
		return productsResult;

	}

}