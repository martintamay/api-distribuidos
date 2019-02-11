package com.sma.delivery.dao.comments;

import java.util.List;

import com.sma.delivery.dao.base.IBaseDao;
import com.sma.delivery.domain.comments.CommentsDomain;

public interface ICommentsDao extends IBaseDao<CommentsDomain>{
	
	public List<CommentsDomain> findByParams(Integer page, Integer maxPage);
}
