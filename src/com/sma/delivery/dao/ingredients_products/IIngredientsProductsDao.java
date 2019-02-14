package com.sma.delivery.dao.ingredients_products;

import java.util.List;
import java.util.Map;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.ingredients_products.IngredientsProductsDomain;

public interface IIngredientsProductsDao extends IBaseDao<IngredientsProductsDomain> {
	
	List<IngredientsProductsDomain> findAllBy(Map<String, String> args);

	void deleteByProduct(Integer productId);
}
