package com.sma.delivery.dto.ingredients_products;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "ingredients_products")
public class IngredientsProductsDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
		
	@XmlElement
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getIngredient() {
		return ingredient;
	}

	public void setIngredient(Integer ingredient) {
		this.ingredient = ingredient;
	}

	private Integer amount;
	private Integer product;
	private Integer ingredient;

}