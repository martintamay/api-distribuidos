package com.sma.delivery.dto.bills;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "bills")
public class BillsDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public Integer getOrderId() {
		return _ordersId;
	}
	
	public void setOrdersId(Integer _ordersId) {
		this._ordersId = _ordersId;
	}
	
	@XmlElement
	public String getTotal() {
		return _total;
	}

	public void setTotal(String _total) {
		this._total = _total;
	}
	@XmlElement
	public Integer getIva() {
		return _iva;
	}

	public void setIva(Integer _iva) {
		this._iva = _iva;
	}
	private String _total;
	private Integer _iva;
	private Integer _ordersId;
	

	

}
