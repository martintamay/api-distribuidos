package com.sma.delivery.domain.product_has_promotions;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.products.ProductsDomain;
import com.sma.delivery.domain.promotions.PromotionsDomain;
@Entity
@Table(name = "ProductHasPromotions")
public class ProductHasPromotionsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "cost", unique = true)
	private int cost;

	@Column(name = "cuantity")
	private int cuantity;

	@Column(name = "comment")
	private String comment;

	@ManyToOne
	private ProductsDomain product;
	@ManyToOne
	private PromotionsDomain promotion;
	
	public ProductsDomain getProduct() {
		return product;
	}

	public void setProduct(ProductsDomain product) {
		this.product = product;
	}

	public PromotionsDomain getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionsDomain promotion) {
		this.promotion = promotion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCuantity() {
		return cuantity;
	}

	public void setCuantity(int cuantity) {
		this.cuantity = cuantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}