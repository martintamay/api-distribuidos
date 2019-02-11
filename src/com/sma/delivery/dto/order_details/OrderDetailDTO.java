package com.sma.delivery.dto.order_details;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "order_detail")
public class OrderDetailDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	private int cost;
	private int cuantity;
	private String comment;
	private Integer orderId;
	private Integer packageId;
	private Integer promotionId;
	private Integer productId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	@XmlElement
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@XmlElement
	public int getCuantity() {
		return cuantity;
	}
	public void setCuantity(int cuantity) {
		this.cuantity = cuantity;
	}
	
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@XmlElement
	public int getPackage() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	
	@XmlElement
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}