package com.sma.delivery.service.bill_details;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.bill_details.BillDetailsDaoImpl;
import com.sma.delivery.dao.bill_details.IBillsDetailsDao;
import com.sma.delivery.dao.bills.IBillsDao;
import com.sma.delivery.domain.bills_details.BillsDetailsDomain;
import com.sma.delivery.dto.bills_details.BillDetailDTO;
import com.sma.delivery.dto.bills_details.BillDetailResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class BillsDetailsServiceImpl extends BaseServiceImpl<BillDetailDTO, BillsDetailsDomain, BillDetailsDaoImpl, BillDetailResult> implements IBillsDetailsService {
	@Autowired
	private IBillsDetailsDao billsDetailsDao;
	
	@Autowired
	private IBillsDao billsDao;
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #billsDetails.id", condition = "#dto.id!=null")
	public BillDetailDTO save(BillDetailDTO dto) {
		final BillsDetailsDomain domain = convertDtoToDomain(dto);
		final BillsDetailsDomain billsDomain = billsDetailsDao.save(domain);
		final BillDetailDTO newDto = convertDomainToDto(domain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsDeails_" + domain.getId(), newDto);
		}
		return convertDomainToDto(billsDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'billsDetails_' + #id")
	public BillDetailDTO getById(Integer id) {
		final BillsDetailsDomain domain = billsDetailsDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'billsDetails_' + #id")
	public BillDetailResult getAll() {
		final List<BillDetailDTO> bills = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findAll()) {
			final BillDetailDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillDetailResult billsResult = new BillDetailResult();
		billsResult.setBillsDetails(bills);
		return billsResult;
	}

	@Override
	protected BillDetailDTO convertDomainToDto(BillsDetailsDomain domain) {
		final BillDetailDTO dto = new BillDetailDTO();
		dto.setId(domain.getId());
		dto.setAmount(domain.getAmount());
		dto.setIva(domain.getIva10());
		dto.setBill(domain.getBill().getId());
		return dto;
	}

	@Override
	protected BillsDetailsDomain convertDtoToDomain(BillDetailDTO dto) {
		final BillsDetailsDomain domain = new BillsDetailsDomain();
		domain.setId(dto.getId());
		domain.setTotal(dto.getAmount());
		domain.setIva10(dto.getIva());
		domain.setBill(billsDao.getById(dto.getBill()));
		return domain;
	}

	@Override
	@Transactional
	public BillDetailResult get(Integer page, Integer tamPag) {
		final List<BillDetailDTO> clientss = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findByParams(page, tamPag)) {
			final BillDetailDTO client = convertDomainToDto(domain);
			clientss.add(client);
		}

		final BillDetailResult clientResult = new BillDetailResult();
		clientResult.setBillsDetails(clientss);
		return clientResult;
		}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #dto.id")
	public BillDetailDTO update(BillDetailDTO dto) {
		final BillsDetailsDomain clientDomain = convertDtoToDomain(dto);
		final BillsDetailsDomain client = billsDetailsDao.update(clientDomain);
		final BillDetailDTO newDto = convertDomainToDto(client);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("billsDetails_" + client.getId(), newDto);
		}
		return convertDomainToDto(client);
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'billsDetails_' + #dto.id")
	public void delete(BillDetailDTO dto) {
		final BillsDetailsDomain billsDomain = convertDtoToDomain(dto);
		billsDetailsDao.delete(billsDomain);	
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_bid' + #text")
	public BillDetailResult find(String text, Integer page, Integer size) {
		final List<BillDetailDTO> bills = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.find(text, page, size)) {
			final BillDetailDTO user = convertDomainToDto(domain);
			bills.add(user);
		}

		final BillDetailResult billsResult = new BillDetailResult();
		billsResult.setBillsDetails(bills);
		return billsResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_bid' + #page + #size")
	public BillDetailResult getAll(Integer page,Integer size) {
		final List<BillDetailDTO> bills = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findAll(page,size)) {
			final BillDetailDTO dto = convertDomainToDto(domain);
			bills.add(dto);
		}
		final BillDetailResult billsResult = new BillDetailResult();
		billsResult.setBillsDetails(bills);
		return billsResult;
	}
	
	@Override
	@Transactional(readOnly = true)
	public BillDetailResult getAllBy(Map<String,String> args){
		final List<BillDetailDTO> billsDetails = new ArrayList<>();
		for (BillsDetailsDomain domain : billsDetailsDao.findAllBy(args)){
			final BillDetailDTO dto = convertDomainToDto(domain);
			billsDetails.add(dto);
		}
		final BillDetailResult billsDetailsResult = new BillDetailResult();
		billsDetailsResult.setBillsDetails(billsDetails);
		return billsDetailsResult;
	}
	
	@Override
	public void deleteByBills(Integer id){
		billsDetailsDao.deleteByBill(id);
	}
	

}

