package com.sma.delivery.dao.bill_details;

import java.util.List;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.bills_details.BillsDetailsDomain;

public interface IBillsDetailsDao extends IBaseDao<BillsDetailsDomain>{
	
	public List<BillsDetailsDomain> findByParams(Integer page, Integer maxPage);
}
