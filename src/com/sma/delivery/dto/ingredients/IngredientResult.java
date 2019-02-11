package com.sma.delivery.dto.ingredients;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "ingredients")
public class IngredientResult extends BaseResult<IngredientDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<IngredientDTO> getIngredients() {
		return getList();
	}

	public void setIngredients(List<IngredientDTO> dtos) {
		super.setList(dtos);
	}
}
