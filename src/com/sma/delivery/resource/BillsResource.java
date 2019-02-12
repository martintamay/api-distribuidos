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

import com.sma.delivery.dto.bills.BillDTO;
import com.sma.delivery.dto.bills.BillResult;
import com.sma.delivery.service.bills.IBillsService;

@Path("/bills")
@Component
public class BillsResource {

	@Autowired
	private IBillsService billsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillDTO getById(@PathParam("id") Integer billsId) {
		return billsService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public BillResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return billsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public BillResult getAll() {
		return billsService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public BillResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return billsService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public BillDTO save(BillDTO bills) {
		return billsService.save(bills);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer billsId) {
		BillDTO bills = billsService.getById(billsId);
		billsService.delete(bills);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillDTO update(BillDTO bills, @PathParam("id") Integer billsId) {
		bills.setId(billsId);
		System.out.println("actualizando..............."+bills.getBillsDetails().size());
		return billsService.update(bills);
	}
	
}
