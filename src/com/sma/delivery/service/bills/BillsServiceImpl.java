package com.sma.delivery.service.bills;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.bills.BillsDaoImpl;
import com.sma.delivery.dao.bills.IBillsDao;
import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.dto.bills.BillDTO;
import com.sma.delivery.dto.bills.BillResult;
import com.sma.delivery.dto.bills_details.BillDetailDTO;
import com.sma.delivery.service.base.BaseServiceImpl;
import com.sma.delivery.service.bill_details.IBillsDetailsService;

@Service
public class BillsServiceImpl extends BaseServiceImpl<BillDTO, BillsDomain, BillsDaoImpl, BillResult> implements IBillsService {
	@Autowired
	private IBillsDao billsDao;
	
	@Autowired
	private IOrdersDao ordersDao;
	@Autowired
	private IBillsDetailsService billsDetailsService;
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsA_' + #bills.id", condition = "#dto.id!=null")
	public BillDTO save(BillDTO dto) {
		final BillsDomain domain = convertDtoToDomain(dto);
		final BillsDomain billsDomain = billsDao.save(domain);
		final BillDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsA_" + domain.getId(), newDto);
		}
		for(BillDetailDTO detail: dto.getBillsDetails()){
			detail.setBill(newDto.getId());
			billsDetailsService.save(detail);
		}
		return convertDomainToDto(billsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'billsA_' + #id")
	public BillDTO getById(Integer id) {
		final BillsDomain domain = billsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	public BillResult getAll() {
		final List<BillDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.findAll()) {
			final BillDTO dto = convertDomainToDto(domain);
			bills.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsA_" + dto.getId(), dto);
			}
		}
		final BillResult billsResult = new BillResult();
		billsResult.setBills(bills);
		return billsResult;
	}

	@Override
	protected BillDTO convertDomainToDto(BillsDomain domain) {
		final BillDTO dto = new BillDTO();
		dto.setId(domain.getId());
		dto.setTotal(domain.getTotal());
		dto.setIva10(domain.getIva10());
		dto.setIva5(domain.getIva5());
		dto.setNombre(domain.getNombre());
		dto.setDireccion(domain.getDireccion());
		dto.setTimbrado(domain.getTimbrado());
		dto.setRuc(domain.getRuc());
		dto.setNum1(domain.getNum1());
		dto.setNum2(domain.getNum2());
		dto.setNum3(domain.getNum3());
		dto.setFecha(domain.getFecha());
		dto.setOrderId(domain.getOrders().getId());

		return dto;
	}

	@Override
	protected BillsDomain convertDtoToDomain(BillDTO dto) {
		final BillsDomain domain = new BillsDomain();
		domain.setId(dto.getId());
		domain.setTotal(dto.getTotal());
		domain.setIva10(dto.getIva10());
		domain.setIva5(dto.getIva5());
		domain.setNombre(dto.getNombre());
		domain.setDireccion(dto.getDireccion());
		domain.setTimbrado(dto.getTimbrado());
		domain.setRuc(dto.getRuc());
		domain.setNum1(dto.getNum1());
		domain.setNum2(dto.getNum2());
		domain.setNum3(dto.getNum3());
		domain.setFecha(dto.getFecha());
		domain.setOrders(ordersDao.getById(dto.getOrderId()));
		return domain;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsA_' + #dto.id")
	public BillDTO update(BillDTO dto) {
		final BillsDomain clientDomain = convertDtoToDomain(dto);
		final BillsDomain client = billsDao.update(clientDomain);
		final BillDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsA_" + client.getId(), newDto);
		}
		for(BillDetailDTO detail: dto.getBillsDetails()){
			detail.setBill(newDto.getId());
			billsDetailsService.update(detail);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'billsA_' + #dto.id")
	public void delete(BillDTO dto) {
		final BillsDomain billsDomain = convertDtoToDomain(dto);
		billsDetailsService.deleteByBills(dto.getId());
		billsDao.delete(billsDomain);	
	}

	@Override
	@Transactional
	public BillResult find(String text, Integer page, Integer size) {
		final List<BillDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.find(text, page, size)) {
			final BillDTO user = convertDomainToDto(domain);
			bills.add(user);
			if (user.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsA_" + user.getId(), user);
			}
		}

		final BillResult billsResult = new BillResult();
		billsResult.setBills(bills);
		return billsResult;
	}
	@Override
	@Transactional
	public BillResult getAll(Integer page,Integer size) {
		final List<BillDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.findAll(page,size)) {
			final BillDTO dto = convertDomainToDto(domain);
			bills.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsA_" + dto.getId(), dto);
			}
		}
		final BillResult billsResult = new BillResult();
		billsResult.setBills(bills);
		return billsResult;
	}

}



