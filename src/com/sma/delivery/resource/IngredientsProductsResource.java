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

import com.sma.delivery.dto.ingredients_products.IngredientsProductsDTO;
import com.sma.delivery.dto.ingredients_products.IngredientsProductsResult;
import com.sma.delivery.service.ingredients_products.IIngredientsProductsService;

@Path("/ingredients-products")
@Component
public class IngredientsProductsResource {

	@Autowired
	private IIngredientsProductsService ingredientsProductsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public IngredientsProductsDTO getById(@PathParam("id") Integer billsId) {
		return ingredientsProductsService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public IngredientsProductsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return ingredientsProductsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public IngredientsProductsResult getAll() {
		return ingredientsProductsService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public IngredientsProductsResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return ingredientsProductsService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public IngredientsProductsDTO save(IngredientsProductsDTO bills) {
		return ingredientsProductsService.save(bills);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer ingredientsProductsId) {
		IngredientsProductsDTO ingredientsProducts = ingredientsProductsService.getById(ingredientsProductsId);
		ingredientsProductsService.delete(ingredientsProducts);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public IngredientsProductsDTO update(IngredientsProductsDTO ingredientsProducts, @PathParam("id") Integer ingredientsProductsId) {
		ingredientsProducts.setId(ingredientsProductsId);
		return ingredientsProductsService.update(ingredientsProducts);
	}
	
	@GET
	@Path("/productId/{productId}")
	@Produces("application/xml")
	public IngredientsProductsResult getAllBy(@PathParam("productId") String productId){
		Map<String, String> args = new HashMap<>();
		args.put("productId", productId);
		return ingredientsProductsService.getAllBy(args);
	}
	
}
