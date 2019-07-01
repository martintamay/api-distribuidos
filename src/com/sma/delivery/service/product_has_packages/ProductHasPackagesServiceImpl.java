package com.sma.delivery.service.product_has_packages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.product_has_packages.IProductHasPackagesDao;
import com.sma.delivery.dao.product_has_packages.ProductHasPackagesDaoImpl;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.dao.packages.IPackageDao;
import com.sma.delivery.domain.product_has_packages.ProductHasPackagesDomain;
import com.sma.delivery.dto.product_has_packages.ProductHasPackagesDTO;
import com.sma.delivery.dto.product_has_packages.ProductHasPackagesResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class ProductHasPackagesServiceImpl extends BaseServiceImpl<ProductHasPackagesDTO, ProductHasPackagesDomain, ProductHasPackagesDaoImpl, ProductHasPackagesResult> implements IProductHasPackagesService {
	@Autowired
	private IProductHasPackagesDao productHasPackagesDao;
	@Autowired
	private IProductsDao productsDao;	
	@Autowired
	private IPackageDao packagesDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productHasPackagesA_' + #Packages.id", condition = "#dto.id!=null")
	public ProductHasPackagesDTO save(ProductHasPackagesDTO dto) {
		final ProductHasPackagesDomain packagesDomain = convertDtoToDomain(dto);
		final ProductHasPackagesDomain packages = productHasPackagesDao.save(packagesDomain);
		final ProductHasPackagesDTO newDto = convertDomainToDto(packages);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productHasPackagesA_" + packages.getId(), newDto);
		}
		return convertDomainToDto(packages);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'productHasPackagesA_' + #id")
	public ProductHasPackagesDTO getById(Integer id) {
		final ProductHasPackagesDomain packagesDomain = productHasPackagesDao.getById(id);
		return convertDomainToDto(packagesDomain);
	}

	@Override
	@Transactional
	public ProductHasPackagesResult getAll() {
		final List<ProductHasPackagesDTO> packages = new ArrayList<>();
		for (ProductHasPackagesDomain domain : productHasPackagesDao.findAll()) {
			final ProductHasPackagesDTO dto = convertDomainToDto(domain);
			packages.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productHasPackagesA_" + dto.getId(), dto);
			}
		}

		final ProductHasPackagesResult PackagesResult = new ProductHasPackagesResult();
		return PackagesResult;
	}

	@Override
	protected ProductHasPackagesDTO convertDomainToDto(ProductHasPackagesDomain domain) {
		final ProductHasPackagesDTO productHasPackages = new ProductHasPackagesDTO();
		productHasPackages.setId(domain.getId());
		productHasPackages.setCost(domain.getCost());
		productHasPackages.setCuantity(domain.getCuantity());
		productHasPackages.setPromotionId(domain.getPackages().getId());
		productHasPackages.setProductId(domain.getProduct().getId());
		productHasPackages.setComment(domain.getComment());
		return productHasPackages;
	}

	@Override
	protected ProductHasPackagesDomain convertDtoToDomain(ProductHasPackagesDTO dto) {
		final ProductHasPackagesDomain productHasPackages = new ProductHasPackagesDomain();
		productHasPackages.setId(dto.getId());
		productHasPackages.setCost(dto.getCost());
		productHasPackages.setCuantity(dto.getCuantity());
		productHasPackages.setPackages(packagesDao.getById(dto.getPromotionId()));
		productHasPackages.setProduct(productsDao.getById(dto.getProductId()));
		productHasPackages.setComment(dto.getComment());
		return productHasPackages;
	}

	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'productHasPackagesA_' + #dto.id")
	public void delete(ProductHasPackagesDTO dto) {
		final ProductHasPackagesDomain packagesDomain = convertDtoToDomain(dto);
		productHasPackagesDao.delete(packagesDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productHasPackagesA_' + #dto.id")
	public ProductHasPackagesDTO update(ProductHasPackagesDTO dto) {
		final ProductHasPackagesDomain productHasPackagesDomain = convertDtoToDomain(dto);
		final ProductHasPackagesDomain productHasPackages = productHasPackagesDao.update(productHasPackagesDomain);
		final ProductHasPackagesDTO newDto = convertDomainToDto(productHasPackages);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("productHasPackagesA_" + productHasPackages.getId(), newDto);
		}
		return convertDomainToDto(productHasPackages);
	}

	@Override
	@Transactional
	public ProductHasPackagesResult find(String text, Integer page, Integer size) {
		final List<ProductHasPackagesDTO> productsPackages = new ArrayList<>();
		for (ProductHasPackagesDomain domain : productHasPackagesDao.find(text, page, size)) {
			final ProductHasPackagesDTO dto = convertDomainToDto(domain);
			productsPackages.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productHasPackagesA_" + dto.getId(), dto);
			}
		}
		final ProductHasPackagesResult productsPackagesResult = new ProductHasPackagesResult();
		productsPackagesResult.setProductHasPackages(productsPackages);
		return productsPackagesResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro_has_pro' + #page + #size")
	public ProductHasPackagesResult getAll(Integer page,Integer size) {
		final List<ProductHasPackagesDTO> producHasPackages = new ArrayList<>();
		for (ProductHasPackagesDomain domain : productHasPackagesDao.findAll(page, size)) {
			final ProductHasPackagesDTO dto = convertDomainToDto(domain);
			producHasPackages.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("productHasPackagesA_" + dto.getId(), dto);
			}
		}

		final ProductHasPackagesResult productsPackagesResult = new ProductHasPackagesResult();
		productsPackagesResult.setProductHasPackages(producHasPackages);
		return productsPackagesResult;

	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductHasPackagesResult getAllBy(Map<String,String> args){
		final List<ProductHasPackagesDTO> productPackages = new ArrayList<>();
		for (ProductHasPackagesDomain domain : productHasPackagesDao.findAllBy(args)){
			final ProductHasPackagesDTO dto = convertDomainToDto(domain);
			productPackages.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + dto.getId(), dto);
			}
		}
		final ProductHasPackagesResult productsPackagesResult = new ProductHasPackagesResult();
		productsPackagesResult.setProductHasPackages(productPackages);
		return productsPackagesResult;
	}

	@Override
	public void deleteByPackages(Integer packagesId) {
		productHasPackagesDao.deleteByPackages(packagesId);
	}

}