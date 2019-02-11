package com.sma.delivery.dto.orders;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "orders")
public class OrderResult extends BaseResult<OrderDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<OrderDTO> getOrders() {
		return getList();
	}

	public void setOrders(List<OrderDTO> dtos) {
		super.setList(dtos);
	}
}
