package com.sma.delivery.dto.orders;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
import com.sma.delivery.dto.bills_details.BillDetailDTO;
import com.sma.delivery.dto.order_details.OrderDetailDTO;
@XmlRootElement(name = "order")
public class OrderDTO extends BaseDTO{
	
private static final long serialVersionUID = 1L;
	
	
	private int orderNumber;
	private String address;
	private String state;
	private String contactNumber;
	private Integer totalCost;
	private Integer establishmentId;
	private Integer userId;
	private Set<OrderDetailDTO> orderDetails = new HashSet<>();

	
	@XmlElement
	public Set<OrderDetailDTO> getOrderDetails() {
		return orderDetails;

	}
	
	public void setOrderDetails(Set<OrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}


	@XmlElement
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
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
	public Integer getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	
	@XmlElement
	public Integer getEstablishmentId() {
		return establishmentId;
	}
	public void setEstablishmentId(Integer establishmentId) {
		this.establishmentId = establishmentId;
	}
	
	
	
	

}
