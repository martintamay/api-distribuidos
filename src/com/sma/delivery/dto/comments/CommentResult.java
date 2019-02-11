package com.sma.delivery.dto.comments;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseResult;



@XmlRootElement(name = "comments")
public class CommentResult extends BaseResult<CommentDTO>{
	
	private static final long serialVersionUID = 1L;

	
	@XmlElement
	public List<CommentDTO> getComments() {
		return getList();
	}

	public void setComments(List<CommentDTO> dtos) {
		super.setList(dtos);
	}
}
