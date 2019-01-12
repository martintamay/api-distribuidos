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

import com.sma.delivery.dto.bills.BillsDTO;
import com.sma.delivery.dto.bills.BillsResult;
import com.sma.delivery.service.bills.IBillsService;

@Path("/bills")
@Component
public class BillsResource {

	@Autowired
	private IBillsService billsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillsDTO getById(@PathParam("id") Integer billsId) {
		return billsService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public BillsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return billsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public BillsResult getAll() {
		return billsService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public BillsResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return billsService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public BillsDTO save(BillsDTO bills) {
		return billsService.save(bills);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer billsId) {
		BillsDTO bills = billsService.getById(billsId);
		billsService.delete(bills);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillsDTO update(BillsDTO bills, @PathParam("id") Integer billsId) {
		bills.setId(billsId);
		return billsService.update(bills);
	}
	
}
