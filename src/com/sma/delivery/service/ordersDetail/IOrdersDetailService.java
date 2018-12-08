package com.sma.delivery.service.ordersDetail;
import com.sma.delivery.dao.ordersDetail.OrdersDetailDaoImpl;
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;
import com.sma.delivery.dto.ordersDetail.OrdersDetailDTO;
import com.sma.delivery.dto.ordersDetail.OrdersDetailResult;
import com.sma.delivery.service.base.IBaseService;

public interface IOrdersDetailService extends IBaseService<OrdersDetailDTO, OrdersDetailDomain, OrdersDetailDaoImpl ,OrdersDetailResult> {

}