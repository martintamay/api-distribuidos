package com.sma.delivery.service.establishments;

import com.sma.delivery.dao.establishments.EstablishmentsDaoImpl;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.dto.establishments.EstablishmentsDTO;
import com.sma.delivery.dto.establishments.EstablishmentsResult;
import com.sma.delivery.service.base.IBaseService;

public interface IEstablishmentsService extends IBaseService<EstablishmentsDTO, EstablishmentsDomain, EstablishmentsDaoImpl ,EstablishmentsResult> {

}