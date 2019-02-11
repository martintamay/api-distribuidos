package com.sma.delivery.dto.establishments;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "establishments")
public class EstablishmentResult extends BaseResult<EstablishmentDTO> {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<EstablishmentDTO> getEstablishments() {
		return getList();
	}
	public void setEstablishments(List<EstablishmentDTO> dtos) {
		super.setList(dtos);
	}
}
