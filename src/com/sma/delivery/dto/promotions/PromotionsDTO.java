package com.sma.delivery.dto.promotions;
import java.sql.Time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "promotions")
public class PromotionsDTO extends BaseDTO{

private static final long serialVersionUID = 1L;


	private String name;
	private String available;
	private Time end_date;

	
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
	public Time getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Time end_date) {
		this.end_date = end_date;
	}
	



}