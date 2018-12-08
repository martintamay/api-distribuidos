package com.sma.delivery.dto.ordersDetail;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

import com.sma.delivery.dto.ordersDetail.OrdersDetailDTO;



@XmlRootElement(name = "ordersDetailResult")
public class OrdersDetailResult extends BaseResult<OrdersDetailDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<OrdersDetailDTO> getOrdersDetail() {
		return getList();
	}

	public void setOrdersDetail(List<OrdersDetailDTO> dtos) {
		super.setList(dtos);
	}
}
