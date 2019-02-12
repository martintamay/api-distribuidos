package com.sma.delivery.service.orders;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.establishments.IEstablishmentsDao;
import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.dao.orders.OrdersDaoImpl;
import com.sma.delivery.dao.users.IUserDao;
import com.sma.delivery.domain.orders.OrdersDomain;
import com.sma.delivery.dto.orders.OrderDTO;
import com.sma.delivery.dto.orders.OrderResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class OrdersServiceImpl extends BaseServiceImpl<OrderDTO, OrdersDomain, OrdersDaoImpl, OrderResult> implements IOrdersService {
	
	@Autowired
	private IEstablishmentsDao establishmentsDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IOrdersDao ordersDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersA_' + #order.id", condition = "#dto.id!=null")
	public OrderDTO save(OrderDTO dto) {
		final OrdersDomain domain = convertDtoToDomain(dto);
		final OrdersDomain ordersDomain = ordersDao.save(domain);
		final OrderDTO newDto = convertDomainToDto(ordersDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersA_" + ordersDomain.getId(), newDto);
		}
		return convertDomainToDto(ordersDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ordersA_' + #id")
	public OrderDTO getById(Integer id) {
		final OrdersDomain domain = ordersDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	public OrderResult getAll() {
		final List<OrderDTO> orders = new ArrayList<>();
		for (OrdersDomain domain : ordersDao.findAll()) {
			final OrderDTO dto = convertDomainToDto(domain);
			orders.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("ordersA_" + dto.getId(), dto);
			}
			
		}
		final OrderResult commentsResult = new OrderResult();
		commentsResult.setOrders(orders);
		return commentsResult;
	}

	@Override
	protected OrderDTO convertDomainToDto(OrdersDomain domain) {
		final OrderDTO dto = new OrderDTO();
		dto.setId(domain.getId());
		dto.setOrderNumber(domain.getOrderNumber());
		dto.setAddress(domain.getAddress());
		dto.setState(domain.getState());
		dto.setContactNumber(domain.getContactNumber());
		dto.setTotalCost(domain.getTotalCost());
		dto.setEstablishmentId(domain.getEstablishment().getId());
		dto.setUserId(domain.getUser().getId());
		return dto;
	}

	@Override
	protected OrdersDomain convertDtoToDomain(OrderDTO dto) {
		final OrdersDomain domain = new OrdersDomain();
		domain.setId(dto.getId());
		domain.setOrderNumber(dto.getOrderNumber());
		domain.setAddress(dto.getAddress());
		domain.setState(dto.getState());
		domain.setContactNumber(dto.getContactNumber());
		domain.setTotalCost(dto.getTotalCost());
		domain.setEstablishment(establishmentsDao.getById(dto.getEstablishmentId()));
		domain.setUser(userDao.getById(dto.getUserId()));
		return domain;
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'ordersA_' + #dto.id")
	public void delete(OrderDTO dto) {
		final OrdersDomain userDomain = convertDtoToDomain(dto);
		ordersDao.delete(userDomain);	
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersA_' + #dto.id")
	public OrderDTO update(OrderDTO dto) {
		final OrdersDomain userDomain = convertDtoToDomain(dto);
		final OrdersDomain user = ordersDao.update(userDomain);
		final OrderDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersA_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	public OrderResult find(String text, Integer page, Integer size) {
		final List<OrderDTO> orders = new ArrayList<>();
		for (OrdersDomain domain : ordersDao.find(text, page, size)) {
			final OrderDTO order = convertDomainToDto(domain);
			orders.add(order);
			if (order.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("ordersA_" + order.getId(), order);
			}
		}

		final OrderResult ordersResult = new OrderResult();
		ordersResult.setOrders(orders);
		return ordersResult;
	}
	@Override
	@Transactional
	public OrderResult getAll(Integer page,Integer size) {
		final List<OrderDTO> orders = new ArrayList<>();
		for (OrdersDomain domain : ordersDao.findAll(page,size)) {
			final OrderDTO dto = convertDomainToDto(domain);
			orders.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("ordersA_" + dto.getId(), dto);
			}
		}
		final OrderResult commentsResult = new OrderResult();
		commentsResult.setOrders(orders);
		return commentsResult;
	}

}



