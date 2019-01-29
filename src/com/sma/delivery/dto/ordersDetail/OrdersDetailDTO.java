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
	private Integer orders;
	private Integer packages;
	private Integer promotion;
	private Integer products;
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public Integer getPromotion() {
		return promotion;
	}
	public void setPromotion(Integer promotion) {
		this.promotion = promotion;
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
		return packages;
	}
	public void setPackageId(Integer packages) {
		this.packages = packages;
	}
	
	@XmlElement
	public Integer getProducts() {
		return products;
	}
	public void setProducts(Integer products) {
		this.products = products;
	}
	
	
	
	

}