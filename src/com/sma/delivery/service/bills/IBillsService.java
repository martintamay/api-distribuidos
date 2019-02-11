package com.sma.delivery.service.bills;

import com.sma.delivery.dao.bills.BillsDaoImpl;
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.dto.bills.BillDTO;
import com.sma.delivery.dto.bills.BillResult;
import com.sma.delivery.service.base.IBaseService;

public interface IBillsService extends IBaseService<BillDTO, BillsDomain, BillsDaoImpl ,BillResult> {
}

