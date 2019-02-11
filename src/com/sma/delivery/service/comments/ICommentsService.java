package com.sma.delivery.service.comments;


import com.sma.delivery.dao.comments.CommentsDaoImpl;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.dto.comments.CommentDTO;
import com.sma.delivery.dto.comments.CommentResult;
import com.sma.delivery.service.base.IBaseService;

public interface ICommentsService extends IBaseService<CommentDTO, CommentsDomain, CommentsDaoImpl ,CommentResult> {
	public CommentResult get(Integer page, Integer tamPag);
}
