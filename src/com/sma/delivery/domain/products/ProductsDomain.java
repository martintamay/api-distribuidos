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
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;

@Entity
@Table(name = "products")
public class ProductsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer _id;

	@Column(name = "name", nullable = false, unique = true)
	private String _name;
	
	@Column(name = "description", nullable = true)
	private String _description;
	
	@Column(name = "cost", nullable = false)
	private Integer _cost;

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
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}
	
	public Integer getCost() {
		return _cost;
	}

	public void setCost(Integer cost) {
		_cost = cost;
	}

}
