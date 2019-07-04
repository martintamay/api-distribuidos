package com.sma.delivery.service.products;

import com.sma.delivery.dao.products.ProductsDaoImpl;
import com.sma.delivery.domain.products.ProductsDomain;
import com.sma.delivery.dto.products.ProductDTO;
import com.sma.delivery.dto.products.ProductResult;
import com.sma.delivery.service.base.IBaseService;

public interface IProductsService extends IBaseService<ProductDTO, ProductsDomain, ProductsDaoImpl, ProductResult> {

	ProductResult findByEstablishment(Integer establishmentId, String text, Integer page, Integer size);

}
