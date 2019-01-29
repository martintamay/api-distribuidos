package com.sma.delivery.domain.promotions;

import java.sql.Time;
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
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;

@Entity
@Table(name = "promotions")
public class PromotionsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer _id;

	@Column(name = "name", nullable = false, unique = true)
	private String _name;
	
	@Column(name = "available", nullable = true)
	private String _available;
	
	

	@Column(name = "end_date", nullable = false)
	private Time _end_date;

	
	
	@OneToMany(mappedBy = "promotion")
	private Set<OrdersDetailDomain> orderDetail = new HashSet<>();
	
	
	public Set<OrdersDetailDomain> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrdersDetailDomain> orderDetail) {
		this.orderDetail = orderDetail;
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

	public String get_available() {
		return _available;
	}

	public void set_available(String _available) {
		this._available = _available;
	}

	public Time get_end_date() {
		return _end_date;
	}

	public void set_end_date(Time _end_date) {
		this._end_date = _end_date;
	}
	

}
