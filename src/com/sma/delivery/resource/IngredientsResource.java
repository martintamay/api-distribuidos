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

import com.sma.delivery.dto.ingredients.IngredientDTO;
import com.sma.delivery.dto.ingredients.IngredientResult;
import com.sma.delivery.service.ingredients.IIngredientsService;

@Path("/ingredients")
@Component
public class IngredientsResource {

	@Autowired
	private IIngredientsService ingredientsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public IngredientDTO getById(@PathParam("id") Integer ingredientsId) {
		return ingredientsService.getById(ingredientsId);
	}

	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public IngredientResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return ingredientsService.find(text,page,size);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public IngredientResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ingredientsService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public IngredientResult getAll() {
		return ingredientsService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public IngredientDTO save(IngredientDTO product) {
		return ingredientsService.save(product);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer ingredientsId) {
		IngredientDTO ing = ingredientsService.getById(ingredientsId);
		ingredientsService.delete(ing);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public IngredientDTO update(IngredientDTO ing, @PathParam("id") Integer ingredientsId) {
		ing.setId(ingredientsId);
		return ingredientsService.update(ing);
	}
}
