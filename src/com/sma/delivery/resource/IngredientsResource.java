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

import com.sma.delivery.dto.ingredients.IngredientsDTO;
import com.sma.delivery.dto.ingredients.IngredientsResult;
import com.sma.delivery.dto.productType.ProductTypeDTO;
import com.sma.delivery.dto.productType.ProductTypeResult;
import com.sma.delivery.service.ingredients.IIngredientsService;
import com.sma.delivery.service.productType.IProductTypeService;

@Path("/ingredients")
@Component
public class IngredientsResource {

	@Autowired
	private IIngredientsService ingredientsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public IngredientsDTO getById(@PathParam("id") Integer ingredientsId) {
		return ingredientsService.getById(ingredientsId);
	}

	@GET
	@Path("/buscar")
	@Produces({"application/xml", "application/json"})
	public IngredientsResult find(@QueryParam("text") String text) {
		return ingredientsService.find(text);
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public IngredientsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ingredientsService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public IngredientsResult getAll() {
		return ingredientsService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public IngredientsDTO save(IngredientsDTO product) {
		return ingredientsService.save(product);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer ingredientsId) {
		IngredientsDTO ing = ingredientsService.getById(ingredientsId);
		ingredientsService.delete(ing);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public IngredientsDTO update(IngredientsDTO ing, @PathParam("id") Integer ingredientsId) {
		ing.setId(ingredientsId);
		return ingredientsService.update(ing);
	}
}
