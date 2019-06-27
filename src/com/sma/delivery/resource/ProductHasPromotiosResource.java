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

import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionDTO;
import com.sma.delivery.service.product_has_promotions.IProductHasPromotionsService;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionResult;

@Path("/product-has-promotions")
@Component
public class ProductHasPromotiosResource {
	@Autowired
	private IProductHasPromotionsService productHasPromotionsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPromotionDTO getById(@PathParam("id") Integer billsId) {
		return productHasPromotionsService.getById(billsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPromotionResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return productHasPromotionsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public ProductHasPromotionResult getAll() {
		return productHasPromotionsService.getAll();
	}
	
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPromotionResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return productHasPromotionsService.find(text, page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public ProductHasPromotionDTO save(ProductHasPromotionDTO productHasPromotion) {
		return productHasPromotionsService.save(productHasPromotion);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer productHasPromotionId) {
		ProductHasPromotionDTO productHasPromotion = productHasPromotionsService.getById(productHasPromotionId);
		productHasPromotionsService.delete(productHasPromotion);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public ProductHasPromotionDTO update(ProductHasPromotionDTO productHasPromotion, @PathParam("id") Integer productHasPromotionId) {
		productHasPromotion.setId(productHasPromotionId);
		return productHasPromotionsService.update(productHasPromotion);
	}
	
	@GET
	@Path("/promotionId/{promotionId}")
	@Produces("application/xml")
	public ProductHasPromotionResult getAllBy(@PathParam("promotionId") String promotionId){
		Map<String, String> args = new HashMap<>();
		args.put("promotionId", promotionId);
		return productHasPromotionsService.getAllBy(args);
	}
}
