package com.sma.delivery.dto.packages;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;

@XmlRootElement(name = "packages")
public class PackageResult extends BaseResult<PackageDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<PackageDTO> getPackages() {
		return getList();
	}

	public void setPackage(List<PackageDTO> dtos) {
		super.setList(dtos);
	}
}
