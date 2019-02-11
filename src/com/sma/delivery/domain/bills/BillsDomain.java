package com.sma.delivery.domain.bills;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.orders.OrdersDomain;

@Entity
@Table(name = "Bills")
public class BillsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "total")
	private String total;
	
	@Column(name = "iva10")
	private Integer iva10;
	
	@ManyToOne
	private OrdersDomain orders;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getIva10() {
		return iva10;
	}

	public void setIva10(Integer iva10) {
		this.iva10 = iva10;
	}

	public OrdersDomain getOrders() {
		return orders;
	}

	public void setOrders(OrdersDomain orders) {
		this.orders = orders;
	}
}
