package com.sma.delivery.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sma.delivery.dto.roles.RoleResult;
import com.sma.delivery.dto.roles.RoleDTO;
import com.sma.delivery.service.roles.IRolesService;

@Path("/roles")
@Component
public class RolesResource {

	@Autowired
	private IRolesService roleService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public RoleDTO getById(@PathParam("id") Integer roleId) {
		return roleService.getById(roleId);
	}

	@GET
	@RolesAllowed({ "Admin" })
	@Produces({"application/xml", "application/json"})
	public RoleResult getAll() {
		return roleService.getAll();
	}

	@POST
	//@RolesAllowed({ "Editor", "Contributor" })
	@Produces({"application/xml", "application/json"})
	public RoleDTO save(RoleDTO client) {
		return roleService.save(client);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer roleId) {
		RoleDTO role = roleService.getById(roleId);
		roleService.delete(role);
	}

	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public RoleDTO update(RoleDTO role, @PathParam("id") Integer roleId) {
		role.setId(roleId);
		return roleService.update(role);
	}
}
