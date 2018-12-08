package com.sma.delivery.dao.billsDetails;

import java.util.List;


import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.billsDetails.BillsDetailsDomain;

public interface IBillsDetailsDao extends IBaseDao<BillsDetailsDomain>{
	
	public List<BillsDetailsDomain> findByParams(Integer page, Integer maxPage);
}
