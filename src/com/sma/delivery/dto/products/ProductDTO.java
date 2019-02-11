package com.sma.delivery.dto.products;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "product")
public class ProductDTO extends BaseDTO{

private static final long serialVersionUID = 1L;


	private String name;
	private String description;
	private int cost;
	private Integer establishmentId;

	@XmlElement
	public Integer getEstablishmentId() {
		return establishmentId;
	}
	public void setEstablishmentId(Integer establishmentId) {
		this.establishmentId = establishmentId;
	}

	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}




}
