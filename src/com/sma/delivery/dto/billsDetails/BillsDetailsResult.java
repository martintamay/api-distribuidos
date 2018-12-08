package com.sma.delivery.dto.billsDetails;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "billsDetailsResult")
public class BillsDetailsResult extends BaseResult<BillsDetailsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<BillsDetailsDTO> getBillsDetails() {
		return getList();
	}

	public void setBillsDetails(List<BillsDetailsDTO> dtos) {
		super.setList(dtos);
	}
}
