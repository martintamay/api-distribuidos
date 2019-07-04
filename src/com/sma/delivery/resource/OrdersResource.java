package com.sma.delivery.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.orders.OrderDTO;
import com.sma.delivery.dto.orders.OrderResult;
import com.sma.delivery.service.orders.IOrdersService;

@Path("/orders")
@Component
public class OrdersResource {

	@Autowired
	private IOrdersService ordersService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrderDTO getById(@PathParam("id") Integer ordersId) {
		return ordersService.getById(ordersId);
	}
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public OrderResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return ordersService.find(text, page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public OrderResult getAll() {
		return ordersService.getAll();
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public OrderResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ordersService.getAll(page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public OrderDTO save(OrderDTO orders) {
		return ordersService.save(orders);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer orderId) {
		OrderDTO order = ordersService.getById(orderId);
		ordersService.delete(order);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public OrderDTO update(OrderDTO order, @PathParam("id") Integer orderId) {
		Logger logger = Logger.getLogger(OrdersResource.class);
		logger.info("Se recibe el pedido");
		order.setId(orderId);
		return ordersService.update(order);
	}
}
