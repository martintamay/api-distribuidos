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

import com.sma.delivery.dto.products.ProductDTO;
import com.sma.delivery.dto.products.ProductResult;
import com.sma.delivery.service.products.IProductsService;

@Path("/products")
@Component
public class ProductsResource {

	@Autowired
	private IProductsService productsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductDTO getById(@PathParam("id") Integer productsId) {
		return productsService.getById(productsId);
	}

	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public ProductResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return productsService.find(text, page, size);
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public ProductResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return productsService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public ProductResult getAll() {
		return productsService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public ProductDTO save(ProductDTO product) {
		return productsService.save(product);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer productsId) {
		ProductDTO product = productsService.getById(productsId);
		productsService.delete(product);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductDTO update(ProductDTO product, @PathParam("id") Integer productsId) {
		product.setId(productsId);
		return productsService.update(product);
	}
}
