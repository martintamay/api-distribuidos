package com.sma.delivery.domain.orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;
import com.sma.delivery.domain.user.UserDomain;
@Entity
@Table(name = "orders")
public class OrdersDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "order_number", unique = true)
	private Integer orderNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "state")
	private String state;

	@Column(name = "contact_number")
	private String contactNumber;


	@Column(name = "total_cost")
	private Integer totalCost;

	@ManyToOne
	private EstablishmentsDomain  establishment;
	
	
	@ManyToOne
	private UserDomain  user;

	

	@OneToMany(mappedBy = "orders")
	private Set<BillsDomain> bills = new HashSet<>();
	
	
	@OneToMany(mappedBy = "orders")
	private Set<OrdersDetailDomain> ordersDetail = new HashSet<>();
	
	public Set<OrdersDetailDomain> getOrdersDetail() {
		return ordersDetail;
	}


	public void setOrdersDetail(Set<OrdersDetailDomain> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}



	public Set<BillsDomain> getBills() {
		return bills;
	}


	public void setBills(Set<BillsDomain> bills) {
		this.bills = bills;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public int getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}


	public EstablishmentsDomain getEstablishment() {
		return establishment;
	}


	public void setEstablishment(EstablishmentsDomain establishment) {
		this.establishment = establishment;
	}
	
	
	public UserDomain getUser() {
		return user;
	}


	public void setUser(UserDomain user) {
		this.user = user;
	}





}
