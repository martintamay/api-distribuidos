package com.sma.delivery.domain.comments;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.user.UserDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;



@Entity
@Table(name = "comments")
public class CommentsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "title")
	private String _title;

	@Column(name = "content")
	private String _content;

	
	@ManyToOne
	private EstablishmentsDomain _establisment;
	

	@Column(name = "deleted")
	private Boolean _deleted;
	@ManyToOne
	private UserDomain _user;

	public UserDomain getUser() {
		return _user;
	}

	public void setUser(UserDomain user) {
		this._user = user;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}

	public EstablishmentsDomain get_establisments() {
		return _establisment;
	}

	public void set_establisments(EstablishmentsDomain _establisment) {
		this._establisment = _establisment;
	}

	
	public Boolean get_deleted() {
		return _deleted;
	}

	public void set_deleted(Boolean _deleted) {
		this._deleted = _deleted;
	}
	
}
