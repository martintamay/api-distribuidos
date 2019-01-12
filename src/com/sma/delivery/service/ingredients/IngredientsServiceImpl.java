package com.sma.delivery.service.ingredients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.ingredients.IIngredientsDao;
import com.sma.delivery.dao.ingredients.IngredientsDaoImpl;
import com.sma.delivery.domain.ingredients.IngredientsDomain;
import com.sma.delivery.dto.ingredients.IngredientsDTO;
import com.sma.delivery.dto.ingredients.IngredientsResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class IngredientsServiceImpl extends BaseServiceImpl<IngredientsDTO, IngredientsDomain, IngredientsDaoImpl, IngredientsResult> implements IIngredientsService {
	@Autowired
	private IIngredientsDao ingredientsDao;
	
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ingredients_' + #ingredients.id", condition = "#dto.id!=null")
	public IngredientsDTO save(IngredientsDTO dto) {
		final IngredientsDomain ingredientsDomain = convertDtoToDomain(dto);
		final IngredientsDomain ingredients = ingredientsDao.save(ingredientsDomain);
		final IngredientsDTO newDto = convertDomainToDto(ingredients);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ingredients_" + ingredients.getId(), newDto);
		}
		return convertDomainToDto(ingredients);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ingredients_' + #id")
	public IngredientsDTO getById(Integer id) {
		final IngredientsDomain ingredientsDomain = ingredientsDao.getById(id);
		final IngredientsDTO ingredientsDTO = convertDomainToDto(ingredientsDomain);
		return ingredientsDTO;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ingredients_' + #id")
	public IngredientsResult getAll() {
		final List<IngredientsDTO> ingredients = new ArrayList<>();
		for (IngredientsDomain domain : ingredientsDao.findAll()) {
			final IngredientsDTO user = convertDomainToDto(domain);
			ingredients.add(user);
		}

		final IngredientsResult ingredientsResult = new IngredientsResult();
		ingredientsResult.setIngredients(ingredients);
		return ingredientsResult;
	}

	@Override
	protected IngredientsDTO convertDomainToDto(IngredientsDomain domain) {
		final IngredientsDTO ingredients = new IngredientsDTO();
		ingredients.setId(domain.getId());
		ingredients.setDescription(domain.getDescription());
		return ingredients;
	}

	@Override
	protected IngredientsDomain convertDtoToDomain(IngredientsDTO dto) {
		final IngredientsDomain ingredients = new IngredientsDomain();
		ingredients.setId(dto.getId());
		ingredients.setDescription(dto.getDescription());
	
		return ingredients;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ingredients_' + #dto.id")
	public void delete(IngredientsDTO dto) {
		final IngredientsDomain ingredientsDomain = convertDtoToDomain(dto);
		ingredientsDao.delete(ingredientsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsType_' + #dto.id")
	public IngredientsDTO update(IngredientsDTO dto) {
		final IngredientsDomain userDomain = convertDtoToDomain(dto);
		final IngredientsDomain user = ingredientsDao.update(userDomain);
		final IngredientsDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ingredients_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_pro' + #text")
	public IngredientsResult find(String text, Integer page, Integer size) {
		final List<IngredientsDTO> users = new ArrayList<>();
		for (IngredientsDomain domain : ingredientsDao.find(text, page, size)) {
			final IngredientsDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final IngredientsResult userResult = new IngredientsResult();
		userResult.setIngredients(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public IngredientsResult getAll(Integer page,Integer size) {
		final List<IngredientsDTO> users = new ArrayList<>();
		for (IngredientsDomain domain : ingredientsDao.findAll(page, size)) {
			final IngredientsDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final IngredientsResult ingredientsResult = new IngredientsResult();
		ingredientsResult.setIngredients(users);
		return ingredientsResult;

	}

}