package com.sma.delivery.domain.ordersDetail;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.billsDetails.BillsDetailsDomain;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.domain.orders.OrdersDomain;
@Entity
@Table(name = "orders_detail")
public class OrdersDetailDomain implements BaseDomain {

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

	@Column(name = "package_id")
	private int packageId;
	
	@Column(name = "promotions_id")
	private int promotionsId;
	
	@ManyToOne
	private OrdersDomain order;

	

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

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getPromotionsId() {
		return promotionsId;
	}

	public void setPromotionsId(int promotionsId) {
		this.promotionsId = promotionsId;
	}

	public OrdersDomain getOrder() {
		return order;
	}

	public void setOrder(OrdersDomain order) {
		this.order = order;
	}


	
	
	

}