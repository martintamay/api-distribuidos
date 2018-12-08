package com.sma.delivery.domain.bills;
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

import com.sma.delivery.domain.billsDetails.BillsDetailsDomain;
import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.orders.OrdersDomain;




@Entity
@Table(name = "bills")
public class BillsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "total")
	private String _total;


	public Integer get_iva() {
		return _iva10;
	}

	public void set_iva(Integer _iva) {
		this._iva10 = _iva;
	}

	@Column(name = "iva10")
	private Integer _iva10;

	
	@ManyToOne
	private OrdersDomain orders;

	@OneToMany(mappedBy = "_bills")
	private Set<BillsDetailsDomain> _billsDetails = new HashSet<>();
	

	public Set<BillsDetailsDomain> get_billsDetails() {
		return _billsDetails;
	}

	public void set_billsDetails(Set<BillsDetailsDomain> _billsDetails) {
		this._billsDetails = _billsDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String get_total() {
		return _total;
	}

	public void set_total(String _total) {
		this._total = _total;
	}

	public OrdersDomain getOrders() {
		return orders;
	}

	public void setOrders(OrdersDomain _orders) {
		this.orders = _orders;
	}
	
	
}
