package com.sma.delivery.service.orders_details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sma.delivery.dao.orders.IOrdersDao;
import com.sma.delivery.dao.orders_details.IOrdersDetailDao;
import com.sma.delivery.dao.orders_details.OrdersDetailDaoImpl;
import com.sma.delivery.dao.packages.IPackageDao;
import com.sma.delivery.dao.products.IProductsDao;
import com.sma.delivery.dao.promotions.IPromotionsDao;
import com.sma.delivery.domain.orders_details.OrdersDetailDomain;
import com.sma.delivery.domain.packages.PackageDomain;
import com.sma.delivery.domain.products.ProductsDomain;
import com.sma.delivery.domain.promotions.PromotionsDomain;
import com.sma.delivery.dto.order_details.OrderDetailDTO;
import com.sma.delivery.dto.order_details.OrderDetailResult;
import com.sma.delivery.service.base.BaseServiceImpl;
import com.sma.delivery.service.orders.OrdersServiceImpl;

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
	static final Logger logger = Logger.getLogger(OrdersDetailServiceImpl.class);
	
	
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@CachePut(value = "delivery-cache", key = "'ordersDetailA_' + #ordersDetail.id", condition = "#dto.id!=null")
	public OrderDetailDTO save(OrderDetailDTO dto) {
		final OrdersDetailDomain detailDomain = convertDtoToDomain(dto);
		logger.info("detail save 1: "+(detailDomain.getOrders() == null ? "null" : dto.getOrderId()));
		
		final OrdersDetailDomain detail = ordersDetailDao.save(detailDomain);
		logger.info("detail save 2: "+(detail.getOrders() == null ? "null" : detail.getOrders().getId()));
		return convertDomainToDto(detail);
		/*
		final OrdersDetailDomain domain = convertDtoToDomain(dto);
		final OrdersDetailDomain ordersDomain = ordersDetailDao.save(domain);

		logger.info("el id del order es ");
		logger.info(ordersDomain.getOrders() == null ? "null" : ordersDomain.getOrders().getId());
		final OrderDetailDTO newDto = convertDomainToDto(ordersDomain);
		if (dto.getId() == null) {
			getCacheManager().getCache("delivery-cache").put("ordersDetailA_" + ordersDomain.getId(), newDto);
		}
		return convertDomainToDto(ordersDomain);*/
	}

	@Override
	@Transactional(isolation=Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
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
		dto.setQuantity(domain.getQuantity());
		dto.setComment(domain.getComment());
		PackageDomain packageD = domain.getPackages();
		dto.setPackageId(packageD != null ? packageD.getId() : 0);
		ProductsDomain product = domain.getProduct();
		dto.setProductId(product != null ? product.getId() : 0);
		PromotionsDomain promotion = domain.getPromotion();
		dto.setPromotionId(promotion != null ? promotion.getId() : 0);
		dto.setOrderId(domain.getOrders().getId());

		
		return dto;
	}

	@Override
	protected OrdersDetailDomain convertDtoToDomain(OrderDetailDTO dto) {
		final OrdersDetailDomain domain = new OrdersDetailDomain();
		domain.setId(dto.getId());
		domain.setCost(dto.getCost());
		domain.setQuantity(dto.getQuantity());
		domain.setComment(dto.getComment());
		if (dto.getPackageId() != null)
			domain.setPackages(packageDao.getById(dto.getPackageId()));
		if (dto.getProductId() != null)
			domain.setProduct(productsDao.getById(dto.getProductId()));
		if (dto.getPromotionId() != null)
			domain.setPromotion(promotionsDao.getById(dto.getPromotionId()));

		logger.info("id en dto to domain : "+ (dto.getOrderId() == null ? "null" : dto.getOrderId()));
		logger.info("order en dto to domain : "+ (ordersDao.getById(dto.getOrderId())== null ? "null" : ordersDao.getById(dto.getOrderId()).getId() ));
		
		
		domain.setOrders(ordersDao.getById(dto.getOrderId()));
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
		final OrdersDetailDomain detailDomain = convertDtoToDomain(dto);
		final OrdersDetailDomain detail = ordersDetailDao.update(detailDomain);
		return convertDomainToDto(detail);
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
	public OrderDetailResult getByOrderId(Integer orderId) {
		final List<OrderDetailDTO> orders = new ArrayList<>();
		Map<String,String> filter = new HashMap<>();
		filter.put(IOrdersDetailDao.BY_ORDER_ID, orderId.toString());
		for (OrdersDetailDomain domain : ordersDetailDao.findAllBy(filter)) {
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
	@Override
	@Transactional(readOnly = true)
	public OrderDetailResult getAllBy(Map<String,String> args){
		final List<OrderDetailDTO> billsDetails = new ArrayList<>();
		for (OrdersDetailDomain domain : ordersDetailDao.findAllBy(args)){
			final OrderDetailDTO dto = convertDomainToDto(domain);
			billsDetails.add(dto);
			if (dto.getId() != null) {
				getCacheManager().getCache("delivery-cache").put("billsDetailsA_" + dto.getId(), dto);
			}
		}
		final OrderDetailResult billsDetailsResult = new OrderDetailResult();
		billsDetailsResult.setOrdersDetail(billsDetails);
		return billsDetailsResult;
	}
	
	
	@Override
	public void deleteByOrders(Integer id){
		ordersDetailDao.deleteByOrder(id);
	}

}