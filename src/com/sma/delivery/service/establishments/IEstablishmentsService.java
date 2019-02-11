package com.sma.delivery.service.establishments;

import com.sma.delivery.dao.establishments.EstablishmentsDaoImpl;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.dto.establishments.EstablishmentDTO;
import com.sma.delivery.dto.establishments.EstablishmentResult;
import com.sma.delivery.service.base.IBaseService;

public interface IEstablishmentsService extends IBaseService<EstablishmentDTO, EstablishmentsDomain, EstablishmentsDaoImpl ,EstablishmentResult> {

}