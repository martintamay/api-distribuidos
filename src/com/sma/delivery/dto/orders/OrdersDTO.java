package com.sma.delivery.dto.orders;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "orders")
public class OrdersDTO extends BaseDTO{
	
private static final long serialVersionUID = 1L;
	
	
	private int orderNumber;
	private String address;
	private String state;
	private String contactNumber;
	private int totalCost;
	private int establishment_id;
	
	@XmlElement
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@XmlElement
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@XmlElement
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@XmlElement
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@XmlElement
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	@XmlElement
	public int getEstablishment_id() {
		return establishment_id;
	}
	public void setEstablishment_id(int establishment_id) {
		this.establishment_id = establishment_id;
	}
	
	
	
	

}
