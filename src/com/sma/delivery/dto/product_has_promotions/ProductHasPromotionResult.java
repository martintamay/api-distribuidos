package com.sma.delivery.dto.product_has_promotions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "product_has_promotions")
public class ProductHasPromotionResult extends BaseResult<ProductHasPromotionDTO>{
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<ProductHasPromotionDTO> getProductHasPromotions() {
		return getList();
	}

	public void setProductHasPromotions(List<ProductHasPromotionDTO> dtos) {
		super.setList(dtos);
	}
}
