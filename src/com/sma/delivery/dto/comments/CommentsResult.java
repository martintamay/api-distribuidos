package com.sma.delivery.dto.comments;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;
import com.sma.delivery.dto.comments.CommentsDTO;



@XmlRootElement(name = "commentsResult")
public class CommentsResult extends BaseResult<CommentsDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<CommentsDTO> getComments() {
		return getList();
	}

	public void setComments(List<CommentsDTO> dtos) {
		super.setList(dtos);
	}
}
