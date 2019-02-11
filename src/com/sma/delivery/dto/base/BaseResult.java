package com.sma.delivery.dto.base;

import java.io.Serializable;
import java.util.List;

public abstract class BaseResult<D extends BaseDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<D> dtos;

	protected List<D> getList() {
		return dtos;
	}

	protected void setList(List<D> dtos) {
		this.dtos = dtos;
	}

	public Integer getTotal() {
		return null == dtos ? 0 : dtos.size();
	}
}
