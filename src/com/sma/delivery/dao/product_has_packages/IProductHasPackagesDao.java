package com.sma.delivery.dao.product_has_packages;

import java.util.List;
import java.util.Map;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.product_has_packages.ProductHasPackagesDomain;

public interface IProductHasPackagesDao extends IBaseDao<ProductHasPackagesDomain> {
	
	List<ProductHasPackagesDomain> findAllBy(Map<String, String> args);

	void deleteByPackages(Integer packagesId);
}
