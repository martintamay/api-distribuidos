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
	
	@Column(name = "iva5")
	private Integer iva5;
	
	@Column(name = "exenta")
	private Integer exenta;
	
	@Column(name = "unitary")
	private Integer unitary;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "product")
	private String product;

	@ManyToOne
	private BillsDomain bill;
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

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

	public Integer getIva5() {
		return iva5;
	}

	public void setIva5(Integer iva5) {
		this.iva5 = iva5;
	}

	public Integer getExenta() {
		return exenta;
	}

	public void setExenta(Integer exenta) {
		this.exenta = exenta;
	}

	public Integer getUnitary() {
		return unitary;
	}

	public void setUnitary(Integer unitary) {
		this.unitary = unitary;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}	
	
	
}
