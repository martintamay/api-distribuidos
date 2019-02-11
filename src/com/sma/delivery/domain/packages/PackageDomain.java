package com.sma.delivery.domain.packages;

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
@Table(name = "Packages")
public class PackageDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "cost", nullable = false)
	private Integer cost;

	@OneToMany(mappedBy = "packages")
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
	
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
