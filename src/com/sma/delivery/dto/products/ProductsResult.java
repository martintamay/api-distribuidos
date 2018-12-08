package com.sma.delivery.dto.products;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

import com.sma.delivery.dto.orders.OrdersDTO;



@XmlRootElement(name = "productsResult")
public class ProductsResult extends BaseResult<ProductsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<ProductsDTO> getProducts() {
		return getList();
	}

	public void setProducts(List<ProductsDTO> dtos) {
		super.setList(dtos);
	}
}
