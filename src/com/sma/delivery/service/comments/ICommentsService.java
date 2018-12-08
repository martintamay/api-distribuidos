package com.sma.delivery.service.comments;


import com.sma.delivery.dao.comments.CommentsDaoImpl;
import com.sma.delivery.domain.comments.CommentsDomain;
import com.sma.delivery.dto.comments.CommentsDTO;
import com.sma.delivery.dto.comments.CommentsResult;
import com.sma.delivery.service.base.IBaseService;

public interface ICommentsService extends IBaseService<CommentsDTO, CommentsDomain, CommentsDaoImpl ,CommentsResult> {
	public CommentsResult get(Integer page, Integer tamPag);
}
