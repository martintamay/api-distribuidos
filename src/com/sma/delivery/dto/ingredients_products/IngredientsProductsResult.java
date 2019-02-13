package com.sma.delivery.dto.ingredients_products;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "ingredients_products")
public class IngredientsProductsResult extends BaseResult<IngredientsProductsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<IngredientsProductsDTO> getIngredientsProducts() {
		return getList();
	}

	public void setIngredientsProducts(List<IngredientsProductsDTO> dtos) {
		super.setList(dtos);
	}
}