package com.sma.delivery.dto.promotions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

import com.sma.delivery.dto.orders.OrdersDTO;



@XmlRootElement(name = "promotionsResult")
public class PromotionsResult extends BaseResult<PromotionsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<PromotionsDTO> getPromotions() {
		return getList();
	}

	public void setPromotions(List<PromotionsDTO> dtos) {
		super.setList(dtos);
	}
}
