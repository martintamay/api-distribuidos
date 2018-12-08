package com.sma.delivery.dto.establishments;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.sma.delivery.dto.base.BaseResult;
import com.sma.delivery.dto.establishments.EstablishmentsDTO;

@XmlRootElement(name = "establishmentsResult")
public class EstablishmentsResult extends BaseResult<EstablishmentsDTO> {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<EstablishmentsDTO> getEstablishments() {
		return getList();
	}
	public void setEstablishments(List<EstablishmentsDTO> dtos) {
		super.setList(dtos);
	}
	


	


	
}
