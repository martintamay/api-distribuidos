package com.sma.delivery.dto.order_details;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "orders_detail")
public class OrderDetailResult extends BaseResult<OrderDetailDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<OrderDetailDTO> getOrdersDetail() {
		return getList();
	}

	public void setOrdersDetail(List<OrderDetailDTO> dtos) {
		super.setList(dtos);
	}
}
