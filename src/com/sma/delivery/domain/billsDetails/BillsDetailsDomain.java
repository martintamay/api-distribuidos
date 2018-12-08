package com.sma.delivery.domain.billsDetails;
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
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;




@Entity
@Table(name = "bills_details")
public class BillsDetailsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "amount")
	private Integer _amount;

	@Column(name = "iva10")
	private Integer _iva10;

	
	@ManyToOne
	private BillsDomain _bills;
	
	

	public BillsDomain get_bills() {
		return _bills;
	}

	public void set_bills(BillsDomain _bills) {
		this._bills = _bills;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAmount() {
		return _amount;
	}

	public void set_total(Integer _amount) {
		this._amount = _amount;
	}

	public Integer get_iva() {
		return _iva10;
	}

	public void set_iva(Integer _iva) {
		this._iva10 = _iva;
	}
	

	
}
