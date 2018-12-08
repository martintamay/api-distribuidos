package com.sma.delivery.dao.bills;

import java.util.List;


import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.bills.BillsDomain;

public interface IBillsDao extends IBaseDao<BillsDomain>{
	
	public List<BillsDomain> findByParams(Integer page, Integer maxPage);
}
