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

import com.sma.delivery.dto.products.ProductsDTO;
import com.sma.delivery.dto.products.ProductsResult;
import com.sma.delivery.service.products.IProductsService;

@Path("/products")
@Component
public class ProductsResource {

	@Autowired
	private IProductsService productsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductsDTO getById(@PathParam("id") Integer productsId) {
		return productsService.getById(productsId);
	}

	@GET
	@Path("/buscar")
	@Produces({"application/xml", "application/json"})
	public ProductsResult find(@QueryParam("text") String text) {
		return productsService.find(text);
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public ProductsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return productsService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public ProductsResult getAll() {
		return productsService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public ProductsDTO save(ProductsDTO product) {
		return productsService.save(product);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer productsId) {
		ProductsDTO product = productsService.getById(productsId);
		productsService.delete(product);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductsDTO update(ProductsDTO product, @PathParam("id") Integer productsId) {
		product.setId(productsId);
		return productsService.update(product);
	}
}
