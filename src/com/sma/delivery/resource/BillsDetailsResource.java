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

import com.sma.delivery.dto.billsDetails.BillsDetailsDTO;
import com.sma.delivery.dto.billsDetails.BillsDetailsResult;
import com.sma.delivery.service.billsDetails.IBillsDetailsService;

@Path("/billsDetails")
@Component
public class BillsDetailsResource {

	@Autowired
	private IBillsDetailsService billsDetailsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillsDetailsDTO getById(@PathParam("id") Integer billsId) {
		return billsDetailsService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public BillsDetailsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return billsDetailsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public BillsDetailsResult getAll() {
		return billsDetailsService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public BillsDetailsResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return billsDetailsService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public BillsDetailsDTO save(BillsDetailsDTO bills) {
		return billsDetailsService.save(bills);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer billsId) {
		BillsDetailsDTO bills = billsDetailsService.getById(billsId);
		billsDetailsService.delete(bills);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillsDetailsDTO update(BillsDetailsDTO bills, @PathParam("id") Integer billsId) {
		bills.setId(billsId);
		return billsDetailsService.update(bills);
	}
	
}
