package com.sma.delivery.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.bills_details.BillDetailDTO;
import com.sma.delivery.dto.bills_details.BillDetailResult;
import com.sma.delivery.service.bill_details.IBillsDetailsService;

@Path("/bill-details")
@Component
public class BillDetailsResource {

	@Autowired
	private IBillsDetailsService billsDetailsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillDetailDTO getById(@PathParam("id") Integer billsId) {
		return billsDetailsService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public BillDetailResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return billsDetailsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public BillDetailResult getAll() {
		return billsDetailsService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public BillDetailResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return billsDetailsService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public BillDetailDTO save(BillDetailDTO bills) {
		return billsDetailsService.save(bills);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer billsId) {
		BillDetailDTO bills = billsDetailsService.getById(billsId);
		billsDetailsService.delete(bills);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public BillDetailDTO update(BillDetailDTO bills, @PathParam("id") Integer billsId) {
		bills.setId(billsId);
		return billsDetailsService.update(bills);
	}
	
	@GET
	@Path("/billId/{billId}")
	@Produces("application/xml")
	public BillDetailResult getAllBy(@PathParam("billId") String billId){
		Map<String, String> args = new HashMap<>();
		args.put("billId", billId);
		return billsDetailsService.getAllBy(args);
	}
	
}
