package com.sma.delivery.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.establishments.EstablishmentsDTO;
import com.sma.delivery.dto.establishments.EstablishmentsResult;
import com.sma.delivery.service.establishments.IEstablishmentsService;

@Path("/establishments")
@Component
public class EstablishmentsResource {

	@Autowired
	private IEstablishmentsService establishmentsService;

	@GET
	@Path("/buscar")
	@Produces({"application/xml", "application/json"})
	public EstablishmentsResult find(@QueryParam("text") String text) {
		return establishmentsService.find(text);
	}
	
	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public EstablishmentsDTO getById(@PathParam("id") Integer establishmentsId) {
		return establishmentsService.getById(establishmentsId);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public EstablishmentsResult getAll() {
		return establishmentsService.getAll();
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public EstablishmentsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return establishmentsService.getAll(page, size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public EstablishmentsDTO save(EstablishmentsDTO establishments) {
		return establishmentsService.save(establishments);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer establishmentsId) {
		EstablishmentsDTO establishment = establishmentsService.getById(establishmentsId);
		establishmentsService.delete(establishment);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public EstablishmentsDTO update(EstablishmentsDTO establishments, @PathParam("id") Integer establishmentsId) {
		establishments.setId(establishmentsId);
		return establishmentsService.update(establishments);
	}
}
