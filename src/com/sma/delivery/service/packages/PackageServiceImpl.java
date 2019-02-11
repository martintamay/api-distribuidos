package com.sma.delivery.service.packages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.packages.IPackageDao;
import com.sma.delivery.dao.packages.PackageDaoImpl;
import com.sma.delivery.domain.packages.PackageDomain;
import com.sma.delivery.dto.packages.PackageDTO;
import com.sma.delivery.dto.packages.PackageResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class PackageServiceImpl extends BaseServiceImpl<PackageDTO, PackageDomain, PackageDaoImpl, PackageResult> implements IPackageService {
	@Autowired
	private IPackageDao packageDao;
	

	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'package_' + #package.id", condition = "#dto.id!=null")
	public PackageDTO save(PackageDTO dto) {
		final PackageDomain packageDomain = convertDtoToDomain(dto);
		final PackageDomain packaged = packageDao.save(packageDomain);
		final PackageDTO newDto = convertDomainToDto(packaged);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("package_" + packaged.getId(), newDto);
		}
		return convertDomainToDto(packaged);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'package_' + #id")
	public PackageDTO getById(Integer id) {
		final PackageDomain packageDomain = packageDao.getById(id);
		return convertDomainToDto(packageDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'package_' + #id")
	public PackageResult getAll() {
		final List<PackageDTO> packaged = new ArrayList<>();
		for (PackageDomain domain : packageDao.findAll()) {
			final PackageDTO user = convertDomainToDto(domain);
			packaged.add(user);
		}

		final PackageResult packageResult = new PackageResult();
		packageResult.setPackage(packaged);
		return packageResult;
	}

	@Override
	protected PackageDTO convertDomainToDto(PackageDomain domain) {
		final PackageDTO packaged = new PackageDTO();
		packaged.setId(domain.getId());
		packaged.setName(domain.getName());
		packaged.setCost((domain.getCost()));
		return packaged;
	}

	@Override
	protected PackageDomain convertDtoToDomain(PackageDTO dto) {
		final PackageDomain packaged = new PackageDomain();
		packaged.setId(dto.getId());
		packaged.setName(dto.getName());
		packaged.setCost((dto.getCost()));
		return packaged;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'package_' + #dto.id")
	public void delete(PackageDTO dto) {
		final PackageDomain packageDomain = convertDtoToDomain(dto);
		packageDao.delete(packageDomain);	
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'package_' + #dto.id")
	public PackageDTO update(PackageDTO dto) {
		final PackageDomain userDomain = convertDtoToDomain(dto);
		final PackageDomain user = packageDao.update(userDomain);
		final PackageDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("package_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_pro' + #text")
	public PackageResult find(String text, Integer page, Integer size) {
		final List<PackageDTO> users = new ArrayList<>();
		for (PackageDomain domain : packageDao.find(text, page, size)) {
			final PackageDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final PackageResult userResult = new PackageResult();
		userResult.setPackage(users);
		return userResult;

	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_pro' + #page + #size")
	public PackageResult getAll(Integer page,Integer size) {
		final List<PackageDTO> users = new ArrayList<>();
		for (PackageDomain domain : packageDao.findAll(page, size)) {
			final PackageDTO user = convertDomainToDto(domain);
			users.add(user);
		}

		final PackageResult packageResult = new PackageResult();
		packageResult.setPackage(users);
		return packageResult;

	}

}