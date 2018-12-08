package com.sma.delivery.service.orders;

import com.sma.delivery.dao.orders.OrdersDaoImpl;
import com.sma.delivery.domain.orders.OrdersDomain;
import com.sma.delivery.dto.orders.OrdersDTO;
import com.sma.delivery.dto.orders.OrdersResult;
import com.sma.delivery.service.base.IBaseService;

public interface IOrdersService extends IBaseService<OrdersDTO, OrdersDomain, OrdersDaoImpl ,OrdersResult> {

}
