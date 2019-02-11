package com.sma.delivery.service.packages;

import com.sma.delivery.dao.packages.PackageDaoImpl;
import com.sma.delivery.domain.packages.PackageDomain;
import com.sma.delivery.dto.packages.PackageDTO;
import com.sma.delivery.dto.packages.PackageResult;
import com.sma.delivery.service.base.IBaseService;

public interface IPackageService extends IBaseService<PackageDTO, PackageDomain, PackageDaoImpl, PackageResult> {

}
