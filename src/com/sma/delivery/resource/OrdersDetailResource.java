package com.sma.delivery.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.ordersDetail.OrdersDetailDTO;
import com.sma.delivery.dto.ordersDetail.OrdersDetailResult;
import com.sma.delivery.service.ordersDetail.IOrdersDetailService;

@Path("/ordersdetail")
@Component
public class OrdersDetailResource {

	@Autowired
	private IOrdersDetailService ordersDetailService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrdersDetailDTO getById(@PathParam("id") Integer orderDetailId) {
		return ordersDetailService.getById(orderDetailId);
	}
	
	@GET
	@Path("/buscar")
	@Produces({"application/xml", "application/json"})
	public OrdersDetailResult find(@QueryParam("text") String text) {
		return ordersDetailService.find(text);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public OrdersDetailResult getAll() {
		return ordersDetailService.getAll();
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public OrdersDetailResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ordersDetailService.getAll(page, size);
	}
	
	@POST
	@Produces({"application/xml", "application/json"})
	public OrdersDetailDTO save(OrdersDetailDTO orders) {
		return ordersDetailService.save(orders);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer orderDetailId) {
		OrdersDetailDTO orderDetail = ordersDetailService.getById(orderDetailId);
		ordersDetailService.delete(orderDetail);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrdersDetailDTO update(OrdersDetailDTO orderDetail, @PathParam("id") Integer orderDetailId) {
		orderDetail.setId(orderDetailId);
		return ordersDetailService.update(orderDetail);
	}
}