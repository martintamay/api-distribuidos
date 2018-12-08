package com.sma.delivery.dto.ordersDetail;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "ordersdetail")
public class OrdersDetailDTO extends BaseDTO{
	
private static final long serialVersionUID = 1L;
	
	
	private int cost;
	private int cuantity;
	private String comment;
	private int ordersId;
	private int packageId;
	private int promotionsId;
	
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
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	
	@XmlElement
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	
	@XmlElement
	public int getPromotionsId() {
		return promotionsId;
	}
	public void setPromotionsId(int promotionsId) {
		this.promotionsId = promotionsId;
	}
	
	
	
	
	
	

}