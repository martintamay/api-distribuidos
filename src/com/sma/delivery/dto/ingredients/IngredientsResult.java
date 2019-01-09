package com.sma.delivery.dto.ingredients;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

import com.sma.delivery.dto.orders.OrdersDTO;



@XmlRootElement(name = "ingredientsResult")
public class IngredientsResult extends BaseResult<IngredientsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<IngredientsDTO> getIngredients() {
		return getList();
	}

	public void setIngredients(List<IngredientsDTO> dtos) {
		super.setList(dtos);
	}
}
