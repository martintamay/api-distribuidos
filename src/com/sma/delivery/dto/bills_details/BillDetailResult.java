package com.sma.delivery.dto.bills_details;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "bill_details")
public class BillDetailResult extends BaseResult<BillDetailDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<BillDetailDTO> getBillsDetails() {
		return getList();
	}

	public void setBillsDetails(List<BillDetailDTO> dtos) {
		super.setList(dtos);
	}
}
