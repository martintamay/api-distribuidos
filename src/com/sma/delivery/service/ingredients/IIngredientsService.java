package com.sma.delivery.service.ingredients;


import com.sma.delivery.dao.ingredients.IngredientsDaoImpl;
import com.sma.delivery.domain.ingredients.IngredientsDomain;
import com.sma.delivery.dto.ingredients.IngredientsDTO;
import com.sma.delivery.dto.ingredients.IngredientsResult;
import com.sma.delivery.service.base.IBaseService;

public interface IIngredientsService extends IBaseService<IngredientsDTO, IngredientsDomain, IngredientsDaoImpl, IngredientsResult> {

}
