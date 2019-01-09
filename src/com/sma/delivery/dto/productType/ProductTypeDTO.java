package com.sma.delivery.dto.productType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "productsType")
public class ProductTypeDTO extends BaseDTO{

private static final long serialVersionUID = 1L;

private String description;

	
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	




}
