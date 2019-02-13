package com.sma.delivery.service.ingredients_products;

import java.util.Map;

import com.sma.delivery.dao.ingredients_products.IngredientsProductsDaoImpl;
import com.sma.delivery.domain.ingredients_products.IngredientsProductsDomain;
import com.sma.delivery.dto.ingredients_products.IngredientsProductsDTO;
import com.sma.delivery.dto.ingredients_products.IngredientsProductsResult;
import com.sma.delivery.service.base.IBaseService;

public interface IIngredientsProductsService extends IBaseService<IngredientsProductsDTO, IngredientsProductsDomain, IngredientsProductsDaoImpl, IngredientsProductsResult> {
	public IngredientsProductsResult get(Integer page, Integer tamPag);

	IngredientsProductsResult getAllBy(Map<String, String> args);

	void deleteByIngredientsProducts(Integer ingredientId, Integer productId);
}
