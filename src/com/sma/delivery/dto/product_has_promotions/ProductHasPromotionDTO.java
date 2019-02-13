package com.sma.delivery.dto.product_has_promotions;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;

@XmlRootElement(name = "product_has_promotion")
public class ProductHasPromotionDTO  extends BaseDTO{
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Integer promotionId;
	private Integer cost;
	private Integer cuantity;
	private String comment;
	
	@XmlElement
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@XmlElement
	public Integer getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	
	@XmlElement
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	@XmlElement
	public Integer getCuantity() {
		return cuantity;
	}
	public void setCuantity(Integer cuantity) {
		this.cuantity = cuantity;
	}
	
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
