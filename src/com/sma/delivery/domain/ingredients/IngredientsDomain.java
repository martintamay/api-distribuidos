package com.sma.delivery.domain.ingredients;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.ingredients_products.IngredientsProductsDomain;
import com.sma.delivery.domain.products.ProductsDomain;



@Entity
@Table(name = "Ingredients")
public class IngredientsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "ingredient")
	private Set<IngredientsProductsDomain> ingredientsProducts = new HashSet<>();

	@ManyToMany(mappedBy = "ingredients")
	private Set<ProductsDomain> products = new HashSet<>();
	
	public Set<ProductsDomain> getIngredientsHasProducts() {
		return products;
	}

	public void setIngredientsHasProducts(Set<ProductsDomain> products) {
		this.products = products;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	
	
	



}
