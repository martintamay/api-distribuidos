package com.sma.delivery.domain.ingredients_products;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.ingredients.IngredientsDomain;
import com.sma.delivery.domain.products.ProductsDomain;




@Entity
@Table(name = "IngredientsProducts")
public class IngredientsProductsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "amount")
	private Integer amount;
	
	@ManyToOne
	private IngredientsDomain ingredient;
	
	@ManyToOne
	private ProductsDomain product;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setIngredientDomain(IngredientsDomain ingredient){
		this.ingredient = ingredient;
	}
	
	public IngredientsDomain getIngredient(){
		return this.ingredient;
	}
	
	public void setProductsDomain(ProductsDomain product){
		this.product = product;
	}
	
	public ProductsDomain getProduct(){
		return this.product;
	}
	
}
