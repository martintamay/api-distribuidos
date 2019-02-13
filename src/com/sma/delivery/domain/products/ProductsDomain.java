package com.sma.delivery.domain.products;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.ingredients.IngredientsDomain;
import com.sma.delivery.domain.ingredients_products.IngredientsProductsDomain;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;
import com.sma.delivery.domain.product_has_promotions.ProductHasPromotionsDomain;
import com.sma.delivery.domain.product_types.ProductTypeDomain;

@Entity
@Table(name = "Products")
public class ProductsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "cost", nullable = false)
	private Integer cost;

	@ManyToOne
	private EstablishmentsDomain establisment;

	@OneToMany(mappedBy = "product")
	private List<ProductHasPromotionsDomain> productsHasPromotions = new ArrayList<>();
	
	public List<ProductHasPromotionsDomain> getProductHasPromotions(){
		return productsHasPromotions;
	}

	public void setProductHasPromotionsDomain(List<ProductHasPromotionsDomain> productsHasPromotions) {
		this.productsHasPromotions = productsHasPromotions;
	}
	
	@ManyToMany
	@JoinTable(
		name = "product_ingredients",
		joinColumns = {@JoinColumn(name="product_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="ingredient_id", referencedColumnName="id")}
	)
	private Set<IngredientsDomain> ingredients = new HashSet<>();
	
	public Set<IngredientsDomain> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientsDomain> ingredients) {
		this.ingredients = ingredients;
	}

	@ManyToMany
	@JoinTable(
		name = "product_product_types",
		joinColumns = {@JoinColumn(name="product_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="producttype_id", referencedColumnName="id")}
	)
	private Set<ProductTypeDomain> productTypes = new HashSet<>();
	
	public Set<ProductTypeDomain> getProductTypes() {
		return productTypes;
	}
	@OneToMany(mappedBy = "product")
	private Set<IngredientsProductsDomain> ingredientsProducts = new HashSet<>();

	public void setProductTypes(Set<ProductTypeDomain> productTypes) {
		this.productTypes = productTypes;
	}
	
	@OneToMany(mappedBy = "product")
	private Set<OrdersDetailDomain> orderDetail = new HashSet<>();
	
	public Set<OrdersDetailDomain> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrdersDetailDomain> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public EstablishmentsDomain getEstablisment() {
		return establisment;
	}

	public void setEstablisment(EstablishmentsDomain establisment) {
		this.establisment = establisment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Set<IngredientsProductsDomain> getIngredientsProducts() {
		return ingredientsProducts;
	}

	public void setIngredientsProducts(Set<IngredientsProductsDomain> ingredientsProducts) {
		this.ingredientsProducts = ingredientsProducts;
	}

}
