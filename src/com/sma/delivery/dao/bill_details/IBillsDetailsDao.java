package com.sma.delivery.dao.bill_details;

import java.util.List;
import java.util.Map;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.bills_details.BillsDetailsDomain;

public interface IBillsDetailsDao extends IBaseDao<BillsDetailsDomain>{
	
	public List<BillsDetailsDomain> findByParams(Integer page, Integer maxPage);

	void deleteByBill(Integer id);

	List<BillsDetailsDomain> findAllBy(Map<String, String> args);

}
