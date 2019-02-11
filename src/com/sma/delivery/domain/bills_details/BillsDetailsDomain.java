package com.sma.delivery.domain.bills_details;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.bills.BillsDomain;




@Entity
@Table(name = "BillDetails")
public class BillsDetailsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "amount")
	private Integer amount;

	@Column(name = "iva10")
	private Integer iva10;

	
	@ManyToOne
	private BillsDomain bill;
	
	

	public BillsDomain getBill() {
		return bill;
	}

	public void setBill(BillsDomain bill) {
		this.bill = bill;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setTotal(Integer amount) {
		this.amount = amount;
	}

	public Integer getIva10() {
		return iva10;
	}

	public void setIva10(Integer iva10) {
		this.iva10 = iva10;
	}	
}
