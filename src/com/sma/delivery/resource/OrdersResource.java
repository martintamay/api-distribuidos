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

import com.sma.delivery.dto.orders.OrdersDTO;
import com.sma.delivery.dto.orders.OrdersResult;
import com.sma.delivery.service.orders.IOrdersService;

@Path("/orders")
@Component
public class OrdersResource {

	@Autowired
	private IOrdersService ordersService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrdersDTO getById(@PathParam("id") Integer ordersId) {
		return ordersService.getById(ordersId);
	}
	
	@GET
	@Path("/buscar")
	@Produces({"application/xml", "application/json"})
	public OrdersResult find(@QueryParam("text") String text) {
		return ordersService.find(text);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public OrdersResult getAll() {
		return ordersService.getAll();
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public OrdersResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ordersService.getAll(page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public OrdersDTO save(OrdersDTO orders) {
		return ordersService.save(orders);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer orderId) {
		OrdersDTO order = ordersService.getById(orderId);
		ordersService.delete(order);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrdersDTO update(OrdersDTO order, @PathParam("id") Integer orderId) {
		order.setId(orderId);
		return ordersService.update(order);
	}
}
