package com.sma.delivery.domain.promotions;

import java.sql.Date;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;

@Entity
@Table(name = "Promotions")
public class PromotionsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "available", nullable = true)
	private String available;
	
	@Column(name = "enddate", nullable = false)
	private Date endDate;

	@OneToMany(mappedBy = "promotion")
	private Set<OrdersDetailDomain> orderDetails = new HashSet<>();
	
	public Set<OrdersDetailDomain> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrdersDetailDomain> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
