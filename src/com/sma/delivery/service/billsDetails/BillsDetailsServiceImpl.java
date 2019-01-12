package com.sma.delivery.service.billsDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.sma.delivery.dao.bills.IBillsDao;
import com.sma.delivery.dao.billsDetails.BillsDetailsDaoImpl;
import com.sma.delivery.dao.billsDetails.IBillsDetailsDao;
import com.sma.delivery.dao.ordersDetail.IOrdersDetailDao;
import com.sma.delivery.domain.billsDetails.BillsDetailsDomain;
import com.sma.delivery.dto.billsDetails.BillsDetailsDTO;
import com.sma.delivery.dto.billsDetails.BillsDetailsResult;
import com.sma.delivery.dto.comments.CommentsDTO;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class BillsDetailsServiceImpl extends BaseServiceImpl<BillsDetailsDTO, BillsDetailsDomain, BillsDetailsDaoImpl, BillsDetailsResult> implements IBillsDetailsService {
	@Autowired
	private IBillsDetailsDao billsDetailsDao;
	
	@Autowired
	private IOrdersDetailDao orderDetailsDao;
	
	@Autowired
	private IBillsDao billsDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #billsDetails.id", condition = "#dto.id!=null")
	public BillsDetailsDTO save(BillsDetailsDTO dto) {
		final BillsDetailsDomain domain = convertDtoToDomain(dto);
		final BillsDetailsDomain billsDomain = billsDetailsDao.save(domain);
		final BillsDetailsDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsDeails_" + domain.getId(), newDto);
		}
		return convertDomainToDto(billsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'billsDetails_' + #id")
	public BillsDetailsDTO getById(Integer id) {
		final BillsDetailsDomain domain = billsDetailsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'billsDetails_' + #id")
	public BillsDetailsResult getAll() {
		final List<BillsDetailsDTO> bills = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findAll()) {
			final BillsDetailsDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillsDetailsResult billsResult = new BillsDetailsResult();
		billsResult.setBillsDetails(bills);
		return billsResult;
	}

	@Override
	protected BillsDetailsDTO convertDomainToDto(BillsDetailsDomain domain) {
		final BillsDetailsDTO dto = new BillsDetailsDTO();
		dto.setId(domain.getId());
		dto.setAmount(domain.getAmount());
		dto.setIva(domain.get_iva());
		dto.set_bills(domain.get_bills().getId());
		return dto;
	}

	@Override
	protected BillsDetailsDomain convertDtoToDomain(BillsDetailsDTO dto) {
		final BillsDetailsDomain domain = new BillsDetailsDomain();
		domain.setId(dto.getId());
		domain.set_total(dto.getAmount());
		domain.set_iva(dto.getIva());
		domain.set_bills(billsDao.getById(dto.get_bills()));
		return domain;
	}

	@Override
	@Transactional
	public BillsDetailsResult get(Integer page, Integer tamPag) {
		final List<BillsDetailsDTO> clientss = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findByParams(page, tamPag)) {
			final BillsDetailsDTO client = convertDomainToDto(domain);
			clientss.add(client);
		}

		final BillsDetailsResult clientResult = new BillsDetailsResult();
		clientResult.setBillsDetails(clientss);
		return clientResult;
		}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #dto.id")
	public BillsDetailsDTO update(BillsDetailsDTO dto) {
		final BillsDetailsDomain clientDomain = convertDtoToDomain(dto);
		final BillsDetailsDomain client = billsDetailsDao.update(clientDomain);
		final BillsDetailsDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsDetails_" + client.getId(), newDto);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #dto.id")
	public void delete(BillsDetailsDTO dto) {
		final BillsDetailsDomain billsDomain = convertDtoToDomain(dto);
		billsDetailsDao.delete(billsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_bid' + #text")
	public BillsDetailsResult find(String text, Integer page, Integer size) {
		final List<BillsDetailsDTO> bills = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.find(text, page, size)) {
			final BillsDetailsDTO user = convertDomainToDto(domain);
			bills.add(user);
		}

		final BillsDetailsResult billsResult = new BillsDetailsResult();
		billsResult.setBillsDetails(bills);
		return billsResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_bid' + #page + #size")
	public BillsDetailsResult getAll(Integer page,Integer size) {
		final List<BillsDetailsDTO> bills = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findAll(page,size)) {
			final BillsDetailsDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillsDetailsResult billsResult = new BillsDetailsResult();
		billsResult.setBillsDetails(bills);
		return billsResult;
	}

}

