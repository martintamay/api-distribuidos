package com.sma.delivery.service.orders_details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.dao.orders_details.IOrdersDetailDao;
import com.sma.delivery.dao.orders_details.OrdersDetailDaoImpl;
import com.sma.delivery.dao.packages.IPackageDao;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.dao.promotions.IPromotionsDao;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;
import com.sma.delivery.dto.order_details.OrderDetailDTO;
import com.sma.delivery.dto.order_details.OrderDetailResult;
import com.sma.delivery.service.base.BaseServiceImpl;

@Service
public class OrdersDetailServiceImpl extends BaseServiceImpl<OrderDetailDTO, OrdersDetailDomain, OrdersDetailDaoImpl, OrderDetailResult> implements IOrdersDetailService {
	@Autowired
	private IOrdersDao ordersDao;
	
	@Autowired 
	private IOrdersDetailDao ordersDetailDao;
	@Autowired 
	private IPromotionsDao promotionsDao;
	@Autowired 
	private IPackageDao packageDao;
	@Autowired 
	private IProductsDao productsDao;
	
	
	
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersDetailA_' + #ordersDetail.id", condition = "#dto.id!=null")
	public OrderDetailDTO save(OrderDetailDTO dto) {
		final OrdersDetailDomain domain = convertDtoToDomain(dto);
		final OrdersDetailDomain ordersDomain = ordersDetailDao.save(domain);
		final OrderDetailDTO newDto = convertDomainToDto(ordersDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersDetailA_" + ordersDomain.getId(), newDto);
		}
		return convertDomainToDto(ordersDomain);
	}

	@Override
	@Transactional
	@Cacheable(value = "delivery-cache", key = "'ordersDetailA_' + #id")
	public OrderDetailDTO getById(Integer id) {
		final OrdersDetailDomain domain = ordersDetailDao.getById(id);
		return convertDomainToDto(domain);
	}

	@Override
	@Transactional
	public OrderDetailResult getAll() {
		final List<OrderDetailDTO> orders = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.findAll()) {
			final OrderDetailDTO dto = convertDomainToDto(domain);
			orders.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("ordersDetailA_" + dto.getId(), dto);
			}
		}
		final OrderDetailResult commentsResult = new OrderDetailResult();
		commentsResult.setOrdersDetail(orders);
		return commentsResult;
	}

	@Override
	protected OrderDetailDTO convertDomainToDto(OrdersDetailDomain domain) {
		final OrderDetailDTO dto = new OrderDetailDTO();
		dto.setId(domain.getId());
		dto.setCost(domain.getCost());
		dto.setCuantity(domain.getCuantity());
		dto.setComment(domain.getComment());
		dto.setPackageId(domain.getPackages().getId());
		dto.setProductId(domain.getProduct().getId());
		dto.setOrderId(domain.getOrders().getId());
		dto.setPromotionId(domain.getPromotion().getId());

		
		return dto;
	}

	@Override
	protected OrdersDetailDomain convertDtoToDomain(OrderDetailDTO dto) {
		final OrdersDetailDomain domain = new OrdersDetailDomain();
		domain.setId(dto.getId());
		domain.setCost(dto.getCost());
		domain.setCuantity(dto.getCuantity());
		domain.setComment(dto.getComment());
		domain.setPackages(packageDao.getById(dto.getPackage()));
		domain.setProduct(productsDao.getById(dto.getProductId()));
		domain.setOrders(ordersDao.getById(dto.getOrderId()));
		domain.setPromotion(promotionsDao.getById(dto.getPromotionId()));
		return domain;
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "delivery-cache", key = "'ordersDetailA_' + #dto.id")
	public void delete(OrderDetailDTO dto) {
		final OrdersDetailDomain userDomain = convertDtoToDomain(dto);
		ordersDetailDao.delete(userDomain);	
	}
	@Override
	@Transactional
	@CachePut(value = "delivery-cache", key = "'ordersDetailA_' + #dto.id")
	public OrderDetailDTO update(OrderDetailDTO dto) {
		final OrdersDetailDomain userDomain = convertDtoToDomain(dto);
		final OrdersDetailDomain user = ordersDetailDao.update(userDomain);
		final OrderDetailDTO newDto = convertDomainToDto(user);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersDetailA_" + user.getId(), newDto);
		}
		return convertDomainToDto(user);
	}

	@Override
	@Transactional
	public OrderDetailResult find(String text, Integer page, Integer size) {
		final List<OrderDetailDTO> orders = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.find(text, page, size)) {
			final OrderDetailDTO order = convertDomainToDto(domain);
			orders.add(order);
			if (order.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("ordersDetailA_" + order.getId(), order);
			}
		}

		final OrderDetailResult ordersResult = new OrderDetailResult();
		ordersResult.setOrdersDetail(orders);
		return ordersResult;
	}
	@Override
	@Transactional
	public OrderDetailResult getAll(Integer page,Integer size) {
		final List<OrderDetailDTO> orders = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.findAll(page,size)) {
			final OrderDetailDTO dto = convertDomainToDto(domain);
			orders.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("ordersDetailA_" + dto.getId(), dto);
			}
		}
		final OrderDetailResult commentsResult = new OrderDetailResult();
		commentsResult.setOrdersDetail(orders);
		return commentsResult;
	}

}