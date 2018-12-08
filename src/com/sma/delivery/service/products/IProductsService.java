package com.sma.delivery.service.products;

import com.sma.delivery.dao.products.ProductsDaoImpl;
import com.sma.delivery.domain.products.ProductsDomain;
import com.sma.delivery.dto.products.ProductsDTO;
import com.sma.delivery.dto.products.ProductsResult;
import com.sma.delivery.service.base.IBaseService;

public interface IProductsService extends IBaseService<ProductsDTO, ProductsDomain, ProductsDaoImpl, ProductsResult> {

}
