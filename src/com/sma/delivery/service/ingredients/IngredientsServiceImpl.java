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
import com.sma.delivery.dto.ingredients.IngredientDTO;
import com.sma.delivery.dto.ingredients.IngredientResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class IngredientsServiceImpl extends BaseServiceImpl<IngredientDTO, IngredientsDomain, IngredientsDaoImpl, IngredientResult> implements IIngredientsService {
	@Autowired
	private IIngredientsDao ingredientsDao;
	
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ingredients_' + #ingredients.id", condition = "#dto.id!=null")
	public IngredientDTO save(IngredientDTO dto) {
		final IngredientsDomain ingredientsDomain = convertDtoToDomain(dto);
		final IngredientsDomain ingredients = ingredientsDao.save(ingredientsDomain);
		final IngredientDTO newDto = convertDomainToDto(ingredients);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ingredients_" + ingredients.getId(), newDto);
		}
		return convertDomainToDto(ingredients);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ingredients_' + #id")
	public IngredientDTO getById(Integer id) {
		final IngredientsDomain ingredientsDomain = ingredientsDao.getById(id);
		return convertDomainToDto(ingredientsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ingredients_' + #id")
	public IngredientResult getAll() {
		final List<IngredientDTO> ingredients = new ArrayList<>();
		for (IngredientsDomain domain : ingredientsDao.findAll()) {
			final IngredientDTO user = convertDomainToDto(domain);
			ingredients.add(user);
		}

		final IngredientResult ingredientsResult = new IngredientResult();
		ingredientsResult.setIngredients(ingredients);
		return ingredientsResult;
	}

	@Override
	protected IngredientDTO convertDomainToDto(IngredientsDomain domain) {
		final IngredientDTO ingredients = new IngredientDTO();
		ingredients.setId(domain.getId());
		ingredients.setDescription(domain.getDescription());
		return ingredients;
	}

	@Override
	protected IngredientsDomain convertDtoToDomain(IngredientDTO dto) {
		final IngredientsDomain ingredients = new IngredientsDomain();
		ingredients.setId(dto.getId());
		ingredients.setDescription(dto.getDescription());
	
		return ingredients;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ingredients_' + #dto.id")
	public void delete(IngredientDTO dto) {
		final IngredientsDomain ingredientsDomain = convertDtoToDomain(dto);
		ingredientsDao.delete(ingredientsDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'productsType_' + #dto.id")
	public IngredientDTO update(IngredientDTO dto) {
		final IngredientsDomain userDomain = convertDtoToDomain(dto);
		final IngredientsDomain user = ingredientsDao.update(userDomain);
		final IngredientDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ingredients_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_pro' + #text")
	public IngredientResult find(String text, Integer page, Integer size) {
		final List<IngredientDTO> users = new ArrayList<>();
		for (IngredientsDomain domain : ingredientsDao.find(text, page, size)) {
			final IngredientDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final IngredientResult userResult = new IngredientResult();
		userResult.setIngredients(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public IngredientResult getAll(Integer page,Integer size) {
		final List<IngredientDTO> users = new ArrayList<>();
		for (IngredientsDomain domain : ingredientsDao.findAll(page, size)) {
			final IngredientDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final IngredientResult ingredientsResult = new IngredientResult();
		ingredientsResult.setIngredients(users);
		return ingredientsResult;

	}

}