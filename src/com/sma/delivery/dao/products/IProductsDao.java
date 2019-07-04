package com.sma.delivery.dao.products;

import java.util.List;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.products.ProductsDomain;

public interface IProductsDao extends IBaseDao<ProductsDomain> {

	 List<ProductsDomain> findByEstablishment(Integer establishmentId, String text, Integer page, Integer size);

}
