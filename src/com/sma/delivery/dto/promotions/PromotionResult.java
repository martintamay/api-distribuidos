package com.sma.delivery.dto.promotions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "promotions")
public class PromotionResult extends BaseResult<PromotionDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<PromotionDTO> getPromotions() {
		return getList();
	}

	public void setPromotions(List<PromotionDTO> dtos) {
		super.setList(dtos);
	}
}
