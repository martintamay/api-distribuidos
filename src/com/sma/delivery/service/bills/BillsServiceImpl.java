package com.sma.delivery.service.bills;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.sma.delivery.dao.bills.BillsDaoImpl;
import com.sma.delivery.dao.bills.IBillsDao;
import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.domain.bills.BillsDomain;
import com.sma.delivery.dto.bills.BillsDTO;
import com.sma.delivery.dto.bills.BillsResult;
import com.sma.delivery.dto.comments.CommentsDTO;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class BillsServiceImpl extends BaseServiceImpl<BillsDTO, BillsDomain, BillsDaoImpl, BillsResult> implements IBillsService {
	@Autowired
	private IBillsDao billsDao;
	
	@Autowired
	private IOrdersDao ordersDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'bills_' + #bills.id", condition = "#dto.id!=null")
	public BillsDTO save(BillsDTO dto) {
		final BillsDomain domain = convertDtoToDomain(dto);
		final BillsDomain billsDomain = billsDao.save(domain);
		final BillsDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("bills_" + domain.getId(), newDto);
		}
		return convertDomainToDto(billsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'bills_' + #id")
	public BillsDTO getById(Integer id) {
		final BillsDomain domain = billsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'bills_' + #id")
	public BillsResult getAll() {
		final List<BillsDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.findAll()) {
			final BillsDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillsResult billsResult = new BillsResult();
		billsResult.setBills(bills);
		return billsResult;
	}

	@Override
	protected BillsDTO convertDomainToDto(BillsDomain domain) {
		final BillsDTO dto = new BillsDTO();
		dto.setId(domain.getId());
		dto.setTotal(domain.getTotal());
		dto.setIva(domain.getIva10());
		dto.setOrder_id(domain.getOrders().getId());

		return dto;
	}

	@Override
	protected BillsDomain convertDtoToDomain(BillsDTO dto) {
		final BillsDomain domain = new BillsDomain();
		domain.setId(dto.getId());
		domain.setTotal(dto.getTotal());
		domain.setIva10(dto.getIva());
		//domain.setOrders(ordersDao.getById(1));
		domain.setOrders(ordersDao.getById(dto.getOrder_id()));
		return domain;
	}

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'bills_' + #dto.id")
	public BillsDTO update(BillsDTO dto) {
		final BillsDomain clientDomain = convertDtoToDomain(dto);
		final BillsDomain client = billsDao.update(clientDomain);
		final BillsDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("bills_" + client.getId(), newDto);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'bills_' + #dto.id")
	public void delete(BillsDTO dto) {
		final BillsDomain billsDomain = convertDtoToDomain(dto);
		billsDao.delete(billsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_bil' + #text")
	public BillsResult find(String text, Integer page, Integer size) {
		final List<BillsDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.find(text, page, size)) {
			final BillsDTO user = convertDomainToDto(domain);
			bills.add(user);
		}

		final BillsResult billsResult = new BillsResult();
		billsResult.setBills(bills);
		return billsResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_bil' + #page + #size")
	public BillsResult getAll(Integer page,Integer size) {
		final List<BillsDTO> bills = new ArrayList<>();
		for (BillsDomain domain : billsDao.findAll(page,size)) {
			final BillsDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillsResult billsResult = new BillsResult();
		billsResult.setBills(bills);
		return billsResult;
	}

}



