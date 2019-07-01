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

import com.sma.delivery.dto.product_has_packages.ProductHasPackagesDTO;
import com.sma.delivery.service.product_has_packages.IProductHasPackagesService;
import com.sma.delivery.dto.product_has_packages.ProductHasPackagesResult;

@Path("/product-has-packages")
@Component
public class ProductHasPackagesResource {
	@Autowired
	private IProductHasPackagesService productHasPackagesService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPackagesDTO getById(@PathParam("id") Integer billsId) {
		return productHasPackagesService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPackagesResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return productHasPackagesService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public ProductHasPackagesResult getAll() {
		return productHasPackagesService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPackagesResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return productHasPackagesService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public ProductHasPackagesDTO save(ProductHasPackagesDTO productHasPackages) {
		return productHasPackagesService.save(productHasPackages);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer productHasPackagesId) {
		ProductHasPackagesDTO productHasPackages = productHasPackagesService.getById(productHasPackagesId);
		productHasPackagesService.delete(productHasPackages);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPackagesDTO update(ProductHasPackagesDTO productHasPackages, @PathParam("id") Integer productHasPackagesId) {
		productHasPackages.setId(productHasPackagesId);
		return productHasPackagesService.update(productHasPackages);
	}
	
	@GET
	@Path("/promotionId/{promotionId}")
	@Produces("application/xml")
	public ProductHasPackagesResult getAllBy(@PathParam("promotionId") String promotionId){
		Map<String, String> args = new HashMap<>();
		args.put("promotionId", promotionId);
		return productHasPackagesService.getAllBy(args);
	}
}
