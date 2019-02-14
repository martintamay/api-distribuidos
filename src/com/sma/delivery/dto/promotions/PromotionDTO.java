package com.sma.delivery.dto.promotions;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionDTO;
@XmlRootElement(name = "promotion")
public class PromotionDTO extends BaseDTO{

private static final long serialVersionUID = 1L;
	private String name;
	private String available;
	private String endDate;
	private List<ProductHasPromotionDTO> productHasPromotions = new ArrayList<>();

	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@XmlElement
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}	

	@XmlElement
	public List<ProductHasPromotionDTO> getProductHasPromotionsDTO() {
		return productHasPromotions;
	}
	
	public void setProductHasPromotionsDTO(List<ProductHasPromotionDTO> productHasPromotions) {
		this.productHasPromotions = productHasPromotions;
	}
}
