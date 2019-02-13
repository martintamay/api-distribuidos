package com.sma.delivery.service.ingredients_products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.ingredients.IIngredientsDao;
import com.sma.delivery.dao.ingredients_products.IIngredientsProductsDao;
import com.sma.delivery.dao.ingredients_products.IngredientsProductsDaoImpl;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.domain.ingredients_products.IngredientsProductsDomain;
import com.sma.delivery.dto.ingredients_products.IngredientsProductsDTO;
import com.sma.delivery.dto.ingredients_products.IngredientsProductsResult;
import com.sma.delivery.service.base.BaseServiceImpl;
import com.sma.delivery.service.ingredients_products.IIngredientsProductsService;

@Service
public class IngredientsProductsServiceImpl extends BaseServiceImpl<IngredientsProductsDTO, IngredientsProductsDomain, IngredientsProductsDaoImpl, IngredientsProductsResult> implements IIngredientsProductsService {
	@Autowired
	private IIngredientsProductsDao ingredientsProductsDao;
	
	@Autowired
	private IProductsDao productsDao;
	
	@Autowired
	private IIngredientsDao ingredientsDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetailsA_' + #billsDetails.id", condition = "#dto.id!=null")
	public IngredientsProductsDTO save(IngredientsProductsDTO dto) {
		final IngredientsProductsDomain domain = convertDtoToDomain(dto);
		final IngredientsProductsDomain ingredientsProductsDomain = ingredientsProductsDao.save(domain);
		final IngredientsProductsDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ingredientsProductsA_" + domain.getId(), newDto);
		}
		return convertDomainToDto(ingredientsProductsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'billsDetails_' + #id")
	public IngredientsProductsDTO getById(Integer id) {
		final IngredientsProductsDomain domain = ingredientsProductsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	public IngredientsProductsResult getAll() {
		final List<IngredientsProductsDTO> ingredientsProducts = new ArrayList<>();
		for (IngredientsProductsDomain domain : ingredientsProductsDao.findAll()) {
			final IngredientsProductsDTO dto = convertDomainToDto(domain);
			ingredientsProducts.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + dto.getId(), dto);
			}
		}
		final IngredientsProductsResult ingredientsProductsResult = new IngredientsProductsResult();
		ingredientsProductsResult.setIngredientsProducts(ingredientsProducts);
		return ingredientsProductsResult;
	}

	@Override
	protected IngredientsProductsDTO convertDomainToDto(IngredientsProductsDomain domain) {
		final IngredientsProductsDTO dto = new IngredientsProductsDTO();
		dto.setId(domain.getId());
		dto.setAmount(domain.getAmount());
		dto.setIngredient(domain.getIngredient().getId());
		dto.setProduct(domain.getProduct().getId());
		return dto;
	}

	@Override
	protected IngredientsProductsDomain convertDtoToDomain(IngredientsProductsDTO dto) {
		final IngredientsProductsDomain domain = new IngredientsProductsDomain();
		domain.setId(dto.getId());
		domain.setAmount(dto.getAmount());
		domain.setIngredientDomain(ingredientsDao.getById(dto.getIngredient()));
		domain.setProductsDomain(productsDao.getById(dto.getProduct()));
		return domain;
	}

	@Override
	@Transactional
	public IngredientsProductsResult get(Integer page, Integer tamPag) {
		final List<IngredientsProductsDTO> clientss = new ArrayList<>();
		for (IngredientsProductsDomain domain : ingredientsProductsDao.findAll(page, tamPag)) {
			final IngredientsProductsDTO client = convertDomainToDto(domain);
			clientss.add(client);
		}

		final IngredientsProductsResult clientResult = new IngredientsProductsResult();
		clientResult.setIngredientsProducts(clientss);
		return clientResult;
		}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #dto.id")
	public IngredientsProductsDTO update(IngredientsProductsDTO dto) {
		final IngredientsProductsDomain ingredientsProductsDomain = convertDtoToDomain(dto);
		final IngredientsProductsDomain ingredientsProducts = ingredientsProductsDao.update(ingredientsProductsDomain);
		final IngredientsProductsDTO newDto = convertDomainToDto(ingredientsProducts);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsDetails_" + ingredientsProducts.getId(), newDto);
		}
		return convertDomainToDto(ingredientsProducts);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #dto.id")
	public void delete(IngredientsProductsDTO dto) {
		final IngredientsProductsDomain ingredientsProductsDomain = convertDtoToDomain(dto);
		ingredientsProductsDao.delete(ingredientsProductsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_bid' + #text")
	public IngredientsProductsResult find(String text, Integer page, Integer size) {
		final List<IngredientsProductsDTO> ingredientsProducts = new ArrayList<>();
		for (IngredientsProductsDomain domain : ingredientsProductsDao.find(text, page, size)) {
			final IngredientsProductsDTO user = convertDomainToDto(domain);
			ingredientsProducts.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + user.getId(), user);
			}
		}

		final IngredientsProductsResult ingredientsProductsResult = new IngredientsProductsResult();
		ingredientsProductsResult.setIngredientsProducts(ingredientsProducts);
		return ingredientsProductsResult;
	}
	@Override
	@Transactional
	public IngredientsProductsResult getAll(Integer page,Integer size) {
		final List<IngredientsProductsDTO> ingredientsProducts = new ArrayList<>();
		for (IngredientsProductsDomain domain : ingredientsProductsDao.findAll(page,size)) {
			final IngredientsProductsDTO dto = convertDomainToDto(domain);
			ingredientsProducts.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + dto.getId(), dto);
			}
		}
		final IngredientsProductsResult ingredientsProductsResult = new IngredientsProductsResult();
		ingredientsProductsResult.setIngredientsProducts(ingredientsProducts);
		return ingredientsProductsResult;
	}
	
	@Override
	@Transactional(readOnly = true)
	public IngredientsProductsResult getAllBy(Map<String,String> args){
		final List<IngredientsProductsDTO> ingredientsProducts = new ArrayList<>();
		for (IngredientsProductsDomain domain : ingredientsProductsDao.findAllBy(args)){
			final IngredientsProductsDTO dto = convertDomainToDto(domain);
			ingredientsProducts.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + dto.getId(), dto);
			}
		}
		final IngredientsProductsResult ingredientsProductsResult = new IngredientsProductsResult();
		ingredientsProductsResult.setIngredientsProducts(ingredientsProducts);
		return ingredientsProductsResult;
	}
	
	@Override
	public void deleteByIngredientsProducts(Integer ingredientId, Integer productId){
		ingredientsProductsDao.deleteByBill(ingredientId, productId);
	}

}
