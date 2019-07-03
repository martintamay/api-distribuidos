package com.sma.delivery.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.order_details.OrderDetailDTO;
import com.sma.delivery.dto.order_details.OrderDetailResult;
import com.sma.delivery.service.orders_details.IOrdersDetailService;

@Path("/order-details")
@Component
public class OrderDetailsResource {

	@Autowired
	private IOrdersDetailService ordersDetailService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrderDetailDTO getById(@PathParam("id") Integer orderDetailId) {
		return ordersDetailService.getById(orderDetailId);
	}
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public OrderDetailResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return ordersDetailService.find(text, page, size);
	}
	
	@GET
	@Path("/by-order-id/{orderId}")
	@Produces({"application/xml", "application/json"})
	public OrderDetailResult byOrderId(@PathParam("orderId") Integer orderId) {
		return ordersDetailService.getByOrderId(orderId);
	}


	@GET
	@Produces({"application/xml", "application/json"})
	public OrderDetailResult getAll() {
		return ordersDetailService.getAll();
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public OrderDetailResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ordersDetailService.getAll(page, size);
	}
	
	@POST
	@Produces({"application/xml", "application/json"})
	public OrderDetailDTO save(OrderDetailDTO orders) {
		return ordersDetailService.save(orders);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer orderDetailId) {
		OrderDetailDTO orderDetail = ordersDetailService.getById(orderDetailId);
		ordersDetailService.delete(orderDetail);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrderDetailDTO update(OrderDetailDTO orderDetail, @PathParam("id") Integer orderDetailId) {
		orderDetail.setId(orderDetailId);
		return ordersDetailService.update(orderDetail);
	}
}