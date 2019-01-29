package com.sma.delivery.service.packaged;

import com.sma.delivery.dao.packaged.PackageDaoImpl;
import com.sma.delivery.domain.packaged.PackageDomain;
import com.sma.delivery.dto.packaged.PackageDTO;
import com.sma.delivery.dto.packaged.PackageResult;
import com.sma.delivery.service.base.IBaseService;

public interface IPackageService extends IBaseService<PackageDTO, PackageDomain, PackageDaoImpl, PackageResult> {

}
