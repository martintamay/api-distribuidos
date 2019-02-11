package com.sma.delivery.domain.establishments;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.domain.orders.OrdersDomain;
import com.sma.delivery.domain.products.ProductsDomain;
@Entity
@Table(name = "Establishments")
public class EstablishmentsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "schedule")
	private String schedule;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "creationDate")
	private String creationDate;
	
	@OneToMany(mappedBy = "establisment")
	private Set<CommentsDomain> comments = new HashSet<>();
	
	@OneToMany(mappedBy = "establishment")
	private Set<OrdersDomain> orders = new HashSet<>();
	
	@OneToMany(mappedBy = "establisment")
	private Set<ProductsDomain> products = new HashSet<>();
	
	public Set<ProductsDomain> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductsDomain> products) {
		this.products = products;
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
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<CommentsDomain> getComments() {
		return comments;
	}

	public void setComments(Set<CommentsDomain> comments) {
		this.comments = comments;
	}
	
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Set<OrdersDomain> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrdersDomain> orders) {
		this.orders = orders;
	}	
}
