package com.sma.delivery.dto.product_has_packages;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "product_has_packages")
public class ProductHasPackagesResult extends BaseResult<ProductHasPackagesDTO>{
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<ProductHasPackagesDTO> getProductHasPackages() {
		return getList();
	}

	public void setProductHasPackages(List<ProductHasPackagesDTO> dtos) {
		super.setList(dtos);
	}
}
