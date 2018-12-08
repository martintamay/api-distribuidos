package com.sma.delivery.dto.billsDetails;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "bills_Details")
public class BillsDetailsDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public Integer getOrderDetailsId() {
		return _orderDetailsId;
	}
	
	public void setOrderDetailsId(Integer _orderDetailsId) {
		this._orderDetailsId = _orderDetailsId;
	}
	
	@XmlElement
	public Integer getTotal() {
		return _amount;
	}

	public void setAmount(Integer _amount) {
		this._amount = _amount;
	}
	@XmlElement
	public Integer getIva() {
		return _iva10;
	}

	public void setIva(Integer _iva) {
		this._iva10 = _iva;
	}
	@XmlElement
	public Integer get_bills() {
		return _bills;
	}

	public void set_bills(Integer _bills) {
		this._bills = _bills;
	}
	
	
	
	private Integer _amount;
	private Integer _iva10;
	private Integer _orderDetailsId;
	private Integer _bills;
	

	

}
