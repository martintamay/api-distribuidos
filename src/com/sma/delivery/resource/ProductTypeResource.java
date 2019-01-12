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

import com.sma.delivery.dto.productType.ProductTypeDTO;
import com.sma.delivery.dto.productType.ProductTypeResult;
import com.sma.delivery.service.productType.IProductTypeService;

@Path("/productType")
@Component
public class ProductTypeResource {

	@Autowired
	private IProductTypeService productTypeService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductTypeDTO getById(@PathParam("id") Integer productsTypeId) {
		return productTypeService.getById(productsTypeId);
	}

	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public ProductTypeResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return productTypeService.find(text, page, size);
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public ProductTypeResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return productTypeService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public ProductTypeResult getAll() {
		return productTypeService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public ProductTypeDTO save(ProductTypeDTO product) {
		return productTypeService.save(product);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer productsTypeId) {
		ProductTypeDTO product = productTypeService.getById(productsTypeId);
		productTypeService.delete(product);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductTypeDTO update(ProductTypeDTO product, @PathParam("id") Integer productsTypeId) {
		product.setId(productsTypeId);
		return productTypeService.update(product);
	}
}
