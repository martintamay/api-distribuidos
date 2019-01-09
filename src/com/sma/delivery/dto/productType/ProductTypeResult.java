package com.sma.delivery.dto.productType;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

import com.sma.delivery.dto.orders.OrdersDTO;



@XmlRootElement(name = "productsTypeResult")
public class ProductTypeResult extends BaseResult<ProductTypeDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<ProductTypeDTO> getProductsType() {
		return getList();
	}

	public void setProductsType(List<ProductTypeDTO> dtos) {
		super.setList(dtos);
	}
}
