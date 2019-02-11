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

import com.sma.delivery.dto.promotions.PromotionDTO;
import com.sma.delivery.dto.promotions.PromotionResult;
import com.sma.delivery.service.promotions.IPromotionsService;

@Path("/promotions")
@Component
public class PromotionsResource {

	@Autowired
	private IPromotionsService promotionsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public PromotionDTO getById(@PathParam("id") Integer promotionsId) {
		return promotionsService.getById(promotionsId);
	}

	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public PromotionResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return promotionsService.find(text, page, size);
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public PromotionResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return promotionsService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public PromotionResult getAll() {
		return promotionsService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public PromotionDTO save(PromotionDTO promotions) {
		return promotionsService.save(promotions);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer promotionsId) {
		PromotionDTO promotions = promotionsService.getById(promotionsId);
		promotionsService.delete(promotions);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public PromotionDTO update(PromotionDTO promotions, @PathParam("id") Integer promotionsId) {
		promotions.setId(promotionsId);
		return promotionsService.update(promotions);
	}
}
