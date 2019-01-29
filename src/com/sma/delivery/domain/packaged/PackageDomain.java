package com.sma.delivery.domain.packaged;

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
@Table(name = "package")
public class PackageDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer _id;

	@Column(name = "name", nullable = false, unique = true)
	private String _name;
	
	@Column(name = "cost", nullable = false)
	private Integer _cost;

	
	
	@OneToMany(mappedBy = "packages")
	private Set<OrdersDetailDomain> _orderDetail = new HashSet<>();
	
	
	public Set<OrdersDetailDomain> get_orderDetail() {
		return _orderDetail;
	}

	public void set_orderDetail(Set<OrdersDetailDomain> _orderDetail) {
		this._orderDetail = _orderDetail;
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
	
	public Integer getCost() {
		return _cost;
	}

	public void setCost(Integer cost) {
		_cost = cost;
	}

}
