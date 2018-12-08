package com.sma.delivery.dto.orders;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

import com.sma.delivery.dto.orders.OrdersDTO;



@XmlRootElement(name = "ordersResult")
public class OrdersResult extends BaseResult<OrdersDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<OrdersDTO> getOrders() {
		return getList();
	}

	public void setOrders(List<OrdersDTO> dtos) {
		super.setList(dtos);
	}
}
