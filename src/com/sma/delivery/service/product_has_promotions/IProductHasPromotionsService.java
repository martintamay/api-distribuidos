package com.sma.delivery.service.product_has_promotions;

import com.sma.delivery.dao.product_has_promotions.ProductHasPromotionsDaoImpl;
import com.sma.delivery.domain.product_has_promotions.ProductHasPromotionsDomain;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionDTO;
import com.sma.delivery.dto.product_has_promotions.ProductHasPromotionResult;
import com.sma.delivery.service.base.IBaseService;

public interface IProductHasPromotionsService extends IBaseService<ProductHasPromotionDTO, ProductHasPromotionsDomain, ProductHasPromotionsDaoImpl, ProductHasPromotionResult> {

}
