package com.sma.delivery.service.billsDetails;


import com.sma.delivery.dao.billsDetails.BillsDetailsDaoImpl;
import com.sma.delivery.domain.billsDetails.BillsDetailsDomain;
import com.sma.delivery.dto.billsDetails.BillsDetailsDTO;
import com.sma.delivery.dto.billsDetails.BillsDetailsResult;
import com.sma.delivery.service.base.IBaseService;

public interface IBillsDetailsService extends IBaseService<BillsDetailsDTO, BillsDetailsDomain, BillsDetailsDaoImpl ,BillsDetailsResult> {
	public BillsDetailsResult get(Integer page, Integer tamPag);
}
