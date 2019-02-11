package com.sma.delivery.dto.comments;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "comment")
public class CommentDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@XmlElement
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@XmlElement
	public Integer getEstablishmentId() {
		return establishmentId;
	}
	
	public void setEstablishmentId(Integer establishmentId) {
		this.establishmentId = establishmentId;
	}
	
	@XmlElement
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	private String title;
	private String content;
	private Integer userId;
	private Integer establishmentId;
	private Boolean deleted;

	

}
