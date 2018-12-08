package com.sma.delivery.service.orders;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.orders.OrdersDaoImpl;
import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.domain.orders.OrdersDomain;
import com.sma.delivery.dto.orders.OrdersDTO;
import com.sma.delivery.dto.orders.OrdersResult;
import com.sma.delivery.dto.products.ProductsDTO;
import com.sma.delivery.service.base.BaseServiceImpl;
import com.sma.delivery.dao.establishments.IEstablishmentsDao;

@Service
public class OrdersServiceImpl extends BaseServiceImpl<OrdersDTO, OrdersDomain, OrdersDaoImpl, OrdersResult> implements IOrdersService {
	
	@Autowired
	private IEstablishmentsDao establishmentsDao;
	@Autowired
	private IOrdersDao ordersDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'orders_' + #order.id", condition = "#dto.id!=null")
	public OrdersDTO save(OrdersDTO dto) {
		final OrdersDomain domain = convertDtoToDomain(dto);
		final OrdersDomain ordersDomain = ordersDao.save(domain);
		final OrdersDTO newDto = convertDomainToDto(ordersDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("orders_" + ordersDomain.getId(), newDto);
		}
		return convertDomainToDto(ordersDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'orders_' + #id")
	public OrdersDTO getById(Integer id) {
		final OrdersDomain domain = ordersDao.getById(id);
		final OrdersDTO dto = convertDomainToDto(domain);
		return dto;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'orders_' + #id")
	public OrdersResult getAll() {
		final List<OrdersDTO> orders = new ArrayList<>();
		for (OrdersDomain domain : ordersDao.findAll()) {
			final OrdersDTO dto = convertDomainToDto(domain);
			orders.add(dto);
		}
		final OrdersResult commentsResult = new OrdersResult();
		commentsResult.setOrders(orders);
		return commentsResult;
	}

	@Override
	protected OrdersDTO convertDomainToDto(OrdersDomain domain) {
		final OrdersDTO dto = new OrdersDTO();
		dto.setId(domain.getId());
		dto.setOrderNumber(domain.getOrderNumber());
		dto.setAddress(domain.getAddress());
		dto.setState(domain.getState());
		dto.setContactNumber(domain.getContactNumber());
		dto.setTotalCost(domain.getTotalCost());
		dto.setEstablishment_id(domain.getEstablishment().getId());
		return dto;
	}

	@Override
	protected OrdersDomain convertDtoToDomain(OrdersDTO dto) {
		final OrdersDomain domain = new OrdersDomain();
		domain.setId(dto.getId());
		domain.setOrderNumber(dto.getOrderNumber());
		domain.setAddress(dto.getAddress());
		domain.setState(dto.getState());
		domain.setContactNumber(dto.getContactNumber());
		domain.setTotalCost(dto.getTotalCost());
		domain.setEstablishment(establishmentsDao.getById(dto.getEstablishment_id()));
		return domain;
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'orders_' + #dto.id")
	public void delete(OrdersDTO dto) {
		final OrdersDomain userDomain = convertDtoToDomain(dto);
		ordersDao.delete(userDomain);	
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'orders_' + #dto.id")
	public OrdersDTO update(OrdersDTO dto) {
		final OrdersDomain userDomain = convertDtoToDomain(dto);
		final OrdersDomain user = ordersDao.update(userDomain);
		final OrdersDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("orders_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_ord' + #text")
	public OrdersResult find(String text) {
		final List<OrdersDTO> orders = new ArrayList<>();
		for (OrdersDomain domain : ordersDao.find(text)) {
			final OrdersDTO order = convertDomainToDto(domain);
			orders.add(order);
		}

		final OrdersResult ordersResult = new OrdersResult();
		ordersResult.setOrders(orders);
		return ordersResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_ord' + #page + #size")
	public OrdersResult getAll(Integer page,Integer size) {
		final List<OrdersDTO> orders = new ArrayList<>();
		for (OrdersDomain domain : ordersDao.findAll(page,size)) {
			final OrdersDTO dto = convertDomainToDto(domain);
			orders.add(dto);
		}
		final OrdersResult commentsResult = new OrdersResult();
		commentsResult.setOrders(orders);
		return commentsResult;
	}

}



