package com.sma.delivery.service.product_has_packages;

import java.util.Map;

import com.sma.delivery.dao.product_has_packages.ProductHasPackagesDaoImpl;
import com.sma.delivery.domain.product_has_packages.ProductHasPackagesDomain;
import com.sma.delivery.dto.product_has_packages.ProductHasPackagesDTO;
import com.sma.delivery.dto.product_has_packages.ProductHasPackagesResult;
import com.sma.delivery.service.base.IBaseService;

public interface IProductHasPackagesService extends IBaseService<ProductHasPackagesDTO, ProductHasPackagesDomain, ProductHasPackagesDaoImpl, ProductHasPackagesResult> {
	ProductHasPackagesResult getAllBy(Map<String, String> args);

	void deleteByPackages(Integer promotionId);
}
