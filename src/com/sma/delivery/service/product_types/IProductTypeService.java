package com.sma.delivery.service.product_types;


import com.sma.delivery.dao.product_types.ProductTypeDaoImpl;
import com.sma.delivery.domain.product_types.ProductTypeDomain;
import com.sma.delivery.dto.product_types.ProductTypesDTO;
import com.sma.delivery.dto.product_types.ProductTypesResult;
import com.sma.delivery.service.base.IBaseService;

public interface IProductTypeService extends IBaseService<ProductTypesDTO, ProductTypeDomain, ProductTypeDaoImpl, ProductTypesResult> {

}
