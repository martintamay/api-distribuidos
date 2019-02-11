package com.sma.delivery.service.ingredients;


import com.sma.delivery.dao.ingredients.IngredientsDaoImpl;
import com.sma.delivery.domain.ingredients.IngredientsDomain;
import com.sma.delivery.dto.ingredients.IngredientDTO;
import com.sma.delivery.dto.ingredients.IngredientResult;
import com.sma.delivery.service.base.IBaseService;

public interface IIngredientsService extends IBaseService<IngredientDTO, IngredientsDomain, IngredientsDaoImpl, IngredientResult> {

}
