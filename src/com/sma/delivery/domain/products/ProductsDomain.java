package com.sma.delivery.domain.products;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;

@Entity
@Table(name = "Products")
public class ProductsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "cost", nullable = false)
	private Integer cost;

	@ManyToOne
	private EstablishmentsDomain establisment;
	
	@OneToMany(mappedBy = "product")
	private Set<OrdersDetailDomain> orderDetail = new HashSet<>();
	
	public Set<OrdersDetailDomain> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrdersDetailDomain> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public EstablishmentsDomain getEstablisment() {
		return establisment;
	}

	public void setEstablisment(EstablishmentsDomain establisment) {
		this.establisment = establisment;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

}
