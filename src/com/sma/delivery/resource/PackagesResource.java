package com.sma.delivery.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.packages.PackageDTO;
import com.sma.delivery.dto.packages.PackageResult;
import com.sma.delivery.service.packages.IPackageService;

@Path("/packages")
@Component
public class PackagesResource {

	@Autowired
	private IPackageService packageService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public PackageDTO getById(@PathParam("id") Integer packageId) {
		return packageService.getById(packageId);
	}

	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public PackageResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return packageService.find(text, page, size);
	}
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public PackageResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return packageService.getAll(page, size);
	}
	
	@GET 
	@Produces({"application/xml", "application/json"})
	public PackageResult getAll() {
		return packageService.getAll();
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public PackageDTO save(PackageDTO packaged) {
		return packageService.save(packaged);
	}
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer id) {
		PackageDTO packaged = packageService.getById(id);
		packageService.delete(packaged);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public PackageDTO update(PackageDTO packaged, @PathParam("id") Integer id) {
		packaged.setId(id);
		return packageService.update(packaged);
	}
}
