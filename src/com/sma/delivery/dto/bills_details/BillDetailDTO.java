package com.sma.delivery.dto.bills_details;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "bills_Detail")
public class BillDetailDTO extends BaseDTO{
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@XmlElement
	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}		

	@XmlElement
	public Integer getIva10() {
		return iva10;
	}

	public void setIva10(Integer iva10) {
		this.iva10 = iva10;
	}

	@XmlElement
	public Integer getIva5() {
		return iva5;
	}

	public void setIva5(Integer iva5) {
		this.iva5 = iva5;
	}

	@XmlElement
	public Integer getExenta() {
		return exenta;
	}

	public void setExenta(Integer exenta) {
		this.exenta = exenta;
	}

	@XmlElement
	public Integer getUnitary() {
		return unitary;
	}

	public void setUnitary(Integer unitary) {
		this.unitary = unitary;
	}

	@XmlElement
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	private Integer amount;
	private Integer iva10;
	private Integer bill;
	private Integer iva5;
	private Integer exenta;
	private Integer unitary;
	private Integer quantity;
	private Integer productId;
}
