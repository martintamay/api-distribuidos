package com.sma.delivery.dao.orders_details;

import java.util.List;
import java.util.Map;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;

public interface IOrdersDetailDao extends IBaseDao<OrdersDetailDomain>{
	public static final String BY_ORDER_ID = "orderId";
	
	void deleteByOrder(Integer id);
	List<OrdersDetailDomain> findAllBy(Map<String, String> args);
}
