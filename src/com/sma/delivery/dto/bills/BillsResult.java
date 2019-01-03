package com.sma.delivery.dto.bills;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "billsResult")
public class BillsResult extends BaseResult<BillsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<BillsDTO> getBills() {
		return getList();
	}

	public void setBills(List<BillsDTO> dtos) {
		super.setList(dtos);
	}
}
