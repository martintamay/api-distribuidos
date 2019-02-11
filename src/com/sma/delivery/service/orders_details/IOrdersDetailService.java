package com.sma.delivery.service.orders_details;

import com.sma.delivery.dao.orders_details.OrdersDetailDaoImpl;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;
import com.sma.delivery.dto.order_details.OrderDetailDTO;
import com.sma.delivery.dto.order_details.OrderDetailResult;
import com.sma.delivery.service.base.IBaseService;

public interface IOrdersDetailService extends IBaseService<OrderDetailDTO, OrdersDetailDomain, OrdersDetailDaoImpl ,OrderDetailResult> {

}