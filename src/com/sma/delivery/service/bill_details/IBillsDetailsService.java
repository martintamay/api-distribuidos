package com.sma.delivery.service.bill_details;


import java.util.Map;

import com.sma.delivery.dao.bill_details.BillDetailsDaoImpl;
import com.sma.delivery.domain.bills_details.BillsDetailsDomain;
import com.sma.delivery.dto.bills_details.BillDetailDTO;
import com.sma.delivery.dto.bills_details.BillDetailResult;
import com.sma.delivery.service.base.IBaseService;

public interface IBillsDetailsService extends IBaseService<BillDetailDTO, BillsDetailsDomain, BillDetailsDaoImpl, BillDetailResult> {
	public BillDetailResult get(Integer page, Integer tamPag);

	void deleteByBills(Integer id);

	BillDetailResult getAllBy(Map<String, String> args);
}
