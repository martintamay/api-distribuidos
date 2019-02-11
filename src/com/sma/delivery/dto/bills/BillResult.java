package com.sma.delivery.dto.bills;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "bills")
public class BillResult extends BaseResult<BillDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<BillDTO> getBills() {
		return getList();
	}

	public void setBills(List<BillDTO> dtos) {
		super.setList(dtos);
	}
}
