package com.sma.delivery.dto.promotions;
import java.sql.Time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "promotion")
public class PromotionDTO extends BaseDTO{

private static final long serialVersionUID = 1L;
	private String name;
	private String available;
	private Time endDate;

	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@XmlElement
	public Time getEndDate() {
		return endDate;
	}
	public void setEndDate(Time endDate) {
		this.endDate = endDate;
	}
	



}
