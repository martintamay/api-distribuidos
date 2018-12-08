package com.sma.delivery.service.bills;



import com.sma.delivery.dao.bills.BillsDaoImpl;
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.dto.bills.BillsDTO;
import com.sma.delivery.dto.bills.BillsResult;
import com.sma.delivery.service.base.IBaseService;

public interface IBillsService extends IBaseService<BillsDTO, BillsDomain, BillsDaoImpl ,BillsResult> {
	public BillsResult get(Integer page, Integer tamPag);
}
