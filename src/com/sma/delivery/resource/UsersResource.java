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

import com.sma.delivery.dto.users.UserDTO;
import com.sma.delivery.dto.users.UserResult;
import com.sma.delivery.service.users.IUserService;

@Path("/users")
@Component
public class UsersResource {

	@Autowired
	private IUserService userService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public UserDTO getById(@PathParam("id") Integer userId) {
		return userService.getById(userId);
	}

	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public UserResult findAll(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return userService.find(text,page,size);
	}

	@GET
	@Path("/buscar")
	@Produces({"application/xml", "application/json"})
	public UserResult find(@QueryParam("email") String text) {
		return userService.find(text,1,1);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public UserResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return userService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public UserResult getAll() {
		return userService.getAll();
	}

	@POST
	//@RolesAllowed({ "Editor", "Contributor" })
	@Produces({"application/xml", "application/json"})
	public UserDTO save(UserDTO client) {
		return userService.save(client);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer userId) {
		UserDTO user = userService.getById(userId);
		userService.delete(user);
	}

	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public UserDTO update(UserDTO user, @PathParam("id") Integer userId) {
		user.setId(userId);
		return userService.update(user);
	}
}
