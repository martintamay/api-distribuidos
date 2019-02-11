package com.sma.delivery.service.promotions;

import com.sma.delivery.dao.promotions.PromotionsDaoImpl;
import com.sma.delivery.domain.promotions.PromotionsDomain;
import com.sma.delivery.dto.promotions.PromotionDTO;
import com.sma.delivery.dto.promotions.PromotionResult;
import com.sma.delivery.service.base.IBaseService;

public interface IPromotionsService extends IBaseService<PromotionDTO, PromotionsDomain, PromotionsDaoImpl, PromotionResult> {

}
