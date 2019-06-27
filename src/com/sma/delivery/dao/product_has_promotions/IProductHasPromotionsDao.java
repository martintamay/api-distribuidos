package com.sma.delivery.dao.product_has_promotions;

import java.util.List;
import java.util.Map;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.product_has_promotions.ProductHasPromotionsDomain;

public interface IProductHasPromotionsDao extends IBaseDao<ProductHasPromotionsDomain> {
	
	List<ProductHasPromotionsDomain> findAllBy(Map<String, String> args);

	void deleteByPromotion(Integer promotionId);
}
