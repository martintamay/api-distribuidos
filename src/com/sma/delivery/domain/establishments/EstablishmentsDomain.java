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
@Table(name = "establishments")
public class EstablishmentsDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "name")
	private String _name;

	@Column(name = "description")
	private String _description;
	
	@Column(name = "schedule")
	private String _schedule;
	
	@Column(name = "address")
	private String _address;
	
	@Column(name = "phone_number")
	private String _phone_number;
	
	@Column(name = "email")
	private String _email;
	
	@Column(name = "creation_date")
	private String _creation_date;
	@OneToMany(mappedBy = "_establisment")
	private Set<CommentsDomain> _comments = new HashSet<>();
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
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		this._description = description;
	}
	public String getSchedule() {
		return _schedule;
	}

	public void setSchedule(String schedule) {
		this._schedule = schedule;
	}
	
public String getAddress(){
	return _address;
}
	public void setAddress(String address){
		this._address=address;
	}
	public String getPhoneNumber() {
		return _phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this._phone_number = phone_number;
	}
	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		this._email = email;
	}
	public Set<CommentsDomain> getComments() {
		return _comments;
	}

	public void setComments(Set<CommentsDomain> comments) {
		this._comments = comments;
	}
	public String CreationDate() {
		return _creation_date;
	}

	public void setCreationDate(String creation_date) {
		this._creation_date = creation_date;
	}

	public Set<OrdersDomain> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrdersDomain> orders) {
		this.orders = orders;
	}
	
	
	
}
