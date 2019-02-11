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
	public Integer getIva() {
		return iva10;
	}

	public void setIva10(Integer iva10) {
		this.iva10 = iva10;
	}
	@XmlElement
	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}
	
	
	
	private Integer amount;
	private Integer iva10;
	private Integer bill;
	

	

}
