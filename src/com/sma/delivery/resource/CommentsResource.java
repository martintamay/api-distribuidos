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

import com.sma.delivery.dto.comments.CommentsDTO;
import com.sma.delivery.dto.comments.CommentsResult;
import com.sma.delivery.service.comments.ICommentsService;

@Path("/comments")
@Component
public class CommentsResource {

	@Autowired
	private ICommentsService commentsService;

	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public CommentsDTO getById(@PathParam("id") Integer commentsId) {
		return commentsService.getById(commentsId);
	}
	
	@GET
	@Path("/{page}/{size}")
	@Produces({"application/xml", "application/json"})
	public CommentsResult getAll(@PathParam("page") Integer page, @PathParam("size") Integer size) {
		return commentsService.getAll(page, size);
	}

	@GET
	@Produces({"application/xml", "application/json"})
	public CommentsResult getAll() {
		return commentsService.getAll();
	}
	
	@GET
	@Path("/search/{page}/{size}/{text}")
	@Produces({"application/xml", "application/json"})
	public CommentsResult find(@PathParam("page") Integer page,@PathParam("size") Integer size,@PathParam("text") String text) {
		return commentsService.find(text,page,size);
	}

	@POST
	@Produces({"application/xml", "application/json"})
	public CommentsDTO save(CommentsDTO comments) {
		return commentsService.save(comments);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Integer commentsId) {
		CommentsDTO comment = commentsService.getById(commentsId);
		commentsService.delete(comment);
	}
	
	@PUT
	@Path("/{id}")
	@Produces({"application/xml", "application/json"})
	public CommentsDTO update(CommentsDTO comment, @PathParam("id") Integer commentsId) {
		comment.setId(commentsId);
		return commentsService.update(comment);
	}
	
}
