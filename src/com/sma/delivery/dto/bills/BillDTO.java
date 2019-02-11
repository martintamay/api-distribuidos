package com.sma.delivery.dto.bills;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "bill")
public class BillDTO extends BaseDTO{
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@XmlElement
	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}
	
	@XmlElement
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	private String total;
	private Integer iva;
	private Integer orderId;
}

