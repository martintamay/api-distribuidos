package com.sma.delivery.service.productType;


import com.sma.delivery.dao.productType.ProductTypeDaoImpl;
import com.sma.delivery.domain.productType.ProductTypeDomain;
import com.sma.delivery.dto.productType.ProductTypeDTO;
import com.sma.delivery.dto.productType.ProductTypeResult;
import com.sma.delivery.service.base.IBaseService;

public interface IProductTypeService extends IBaseService<ProductTypeDTO, ProductTypeDomain, ProductTypeDaoImpl, ProductTypeResult> {

}
