package com.sma.delivery.dto.product_types;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "product_types")
public class ProductTypesResult extends BaseResult<ProductTypesDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<ProductTypesDTO> getProductsType() {
		return getList();
	}

	public void setProductsType(List<ProductTypesDTO> dtos) {
		super.setList(dtos);
	}
}
