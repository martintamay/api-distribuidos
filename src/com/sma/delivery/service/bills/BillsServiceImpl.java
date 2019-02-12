package com.sma.delivery.service.bills;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@CachePut(value = "delivery-cache", key = "'bills_' + #bills.id", condition = "#dto.id!=null")
	public BillDTO save(BillDTO dto) {
		final BillsDomain domain = convertDtoToDomain(dto);
		final BillsDomain billsDomain = billsDao.save(domain);
		final BillDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("bills_" + domain.getId(), newDto);
		}
		for(BillDetailDTO detail: dto.getBillsDetails()){
			detail.setBill(newDto.getId());
			billsDetailsService.save(detail);
		}
		return convertDomainToDto(billsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'bills_' + #id")
	public BillDTO getById(Integer id) {
		final BillsDomain domain = billsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'bills_' + #id")
	public BillResult getAll() {
		final List<BillDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.findAll()) {
			final BillDTO dto = convertDomainToDto(domain);
			bills.add(dto);
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
		dto.setIva(domain.getIva10());
		dto.setOrderId(domain.getOrders().getId());

		return dto;
	}

	@Override
	protected BillsDomain convertDtoToDomain(BillDTO dto) {
		final BillsDomain domain = new BillsDomain();
		domain.setId(dto.getId());
		domain.setTotal(dto.getTotal());
		domain.setIva10(dto.getIva());
		domain.setOrders(ordersDao.getById(dto.getOrderId()));
		return domain;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'bills_' + #dto.id")
	public BillDTO update(BillDTO dto) {
		final BillsDomain clientDomain = convertDtoToDomain(dto);
		final BillsDomain client = billsDao.update(clientDomain);
		final BillDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("bills_" + client.getId(), newDto);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'bills_' + #dto.id")
	public void delete(BillDTO dto) {
		final BillsDomain billsDomain = convertDtoToDomain(dto);
		billsDetailsService.deleteByBills(dto.getId());
		billsDao.delete(billsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_bil' + #text")
	public BillResult find(String text, Integer page, Integer size) {
		final List<BillDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.find(text, page, size)) {
			final BillDTO user = convertDomainToDto(domain);
			bills.add(user);
		}

		final BillResult billsResult = new BillResult();
		billsResult.setBills(bills);
		return billsResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_bil' + #page + #size")
	public BillResult getAll(Integer page,Integer size) {
		final List<BillDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.findAll(page,size)) {
			final BillDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillResult billsResult = new BillResult();
		billsResult.setBills(bills);
		return billsResult;
	}

}



