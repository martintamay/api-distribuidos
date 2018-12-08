package com.sma.delivery.dto.comments;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sma.delivery.dto.base.BaseDTO;
@XmlRootElement(name = "comments")
public class CommentsDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public String get_title() {
		return _title;
	}
	
	public void set_title(String _title) {
		this._title = _title;
	}
	
	@XmlElement
	public String get_content() {
		return _content;
	}
	
	public void set_content(String _content) {
		this._content = _content;
	}
	
	@XmlElement
	public Integer get_users_id() {
		return _users_id;
	}
	
	public void set_users_id(Integer _users_id) {
		this._users_id = _users_id;
	}
	
	@XmlElement
	public Integer get_establishments_id() {
		return _establishments_id;
	}
	
	public void set_establishments_id(Integer _establishments_id) {
		this._establishments_id = _establishments_id;
	}
	
	@XmlElement
	public Boolean get_deleted() {
		return _deleted;
	}
	
	public void set_deleted(Boolean _deleted) {
		this._deleted = _deleted;
	}
	
	private String _title;
	private String _content;
	private Integer _users_id;
	private Integer _establishments_id;
	private Boolean _deleted;

	

}
