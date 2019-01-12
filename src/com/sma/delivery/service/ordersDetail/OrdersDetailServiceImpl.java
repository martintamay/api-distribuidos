package com.sma.delivery.service.ordersDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.ordersDetail.OrdersDetailDaoImpl;
import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.dao.ordersDetail.IOrdersDetailDao;
import com.sma.delivery.domain.ordersDetail.OrdersDetailDomain;
import com.sma.delivery.dto.orders.OrdersDTO;
import com.sma.delivery.dto.ordersDetail.OrdersDetailDTO;
import com.sma.delivery.dto.ordersDetail.OrdersDetailResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class OrdersDetailServiceImpl extends BaseServiceImpl<OrdersDetailDTO, OrdersDetailDomain, OrdersDetailDaoImpl, OrdersDetailResult> implements IOrdersDetailService {
	@Autowired
	private IOrdersDao ordersDao;
	
	@Autowired 
	private IOrdersDetailDao ordersDetailDao;

	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersDetail_' + #ordersDetail.id", condition = "#dto.id!=null")
	public OrdersDetailDTO save(OrdersDetailDTO dto) {
		final OrdersDetailDomain domain = convertDtoToDomain(dto);
		final OrdersDetailDomain ordersDomain = ordersDetailDao.save(domain);
		final OrdersDetailDTO newDto = convertDomainToDto(ordersDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersDetail_" + ordersDomain.getId(), newDto);
		}
		return convertDomainToDto(ordersDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ordersDetail_' + #id")
	public OrdersDetailDTO getById(Integer id) {
		final OrdersDetailDomain domain = ordersDetailDao.getById(id);
		final OrdersDetailDTO dto = convertDomainToDto(domain);
		return dto;
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ordersDetail_' + #id")
	public OrdersDetailResult getAll() {
		final List<OrdersDetailDTO> orders = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.findAll()) {
			final OrdersDetailDTO dto = convertDomainToDto(domain);
			orders.add(dto);
		}
		final OrdersDetailResult commentsResult = new OrdersDetailResult();
		commentsResult.setOrdersDetail(orders);
		return commentsResult;
	}

	@Override
	protected OrdersDetailDTO convertDomainToDto(OrdersDetailDomain domain) {
		final OrdersDetailDTO dto = new OrdersDetailDTO();
		dto.setId(domain.getId());
		dto.setId(domain.getId());
		dto.setCost(domain.getCost());
		dto.setCuantity(domain.getCuantity());
		dto.setComment(domain.getComment());
		/*dto.setOrdersId(domain.getOrder().getId());*/
		dto.setPackageId(domain.getPackageId());
		dto.setPromotionsId(domain.getPromotionsId());
		return dto;
	}

	@Override
	protected OrdersDetailDomain convertDtoToDomain(OrdersDetailDTO dto) {
		final OrdersDetailDomain domain = new OrdersDetailDomain();
		domain.setId(dto.getId());
		domain.setCost(dto.getCost());
		domain.setCuantity(dto.getCuantity());
		domain.setComment(dto.getComment());
		/*domain.setOrder(ordersDao.getById(dto.getOrdersId()));*/
		domain.setPackageId(dto.getPackageId());
		domain.setPromotionsId(dto.getPromotionsId());
		return domain;
	}
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersDetail_' + #dto.id")
	public void delete(OrdersDetailDTO dto) {
		final OrdersDetailDomain userDomain = convertDtoToDomain(dto);
		ordersDetailDao.delete(userDomain);	
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersDetail_' + #dto.id")
	public OrdersDetailDTO update(OrdersDetailDTO dto) {
		final OrdersDetailDomain userDomain = convertDtoToDomain(dto);
		final OrdersDetailDomain user = ordersDetailDao.update(userDomain);
		final OrdersDetailDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersDetail_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'busqueda_od' + #text")
	public OrdersDetailResult find(String text, Integer page, Integer size) {
		final List<OrdersDetailDTO> orders = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.find(text, page, size)) {
			final OrdersDetailDTO order = convertDomainToDto(domain);
			orders.add(order);
		}

		final OrdersDetailResult ordersResult = new OrdersDetailResult();
		ordersResult.setOrdersDetail(orders);
		return ordersResult;
	}
	@Override
	@Transactional
	@Cacheable(value = "delivery-cache",  key = "'pagina_od' + #page + #size")
	public OrdersDetailResult getAll(Integer page,Integer size) {
		final List<OrdersDetailDTO> orders = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.findAll(page,size)) {
			final OrdersDetailDTO dto = convertDomainToDto(domain);
			orders.add(dto);
		}
		final OrdersDetailResult commentsResult = new OrdersDetailResult();
		commentsResult.setOrdersDetail(orders);
		return commentsResult;
	}

}