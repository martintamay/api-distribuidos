package com.sma.delivery.domain.comments;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.establishments.EstablishmentsDomain;
import com.sma.delivery.domain.user.UserDomain;



@Entity
@Table(name = "Comments")
public class CommentsDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	
	@ManyToOne
	private EstablishmentsDomain establisment;
	

	@Column(name = "deleted")
	private Boolean deleted;
	@ManyToOne
	private UserDomain user;

	public UserDomain getUser() {
		return user;
	}

	public void setUser(UserDomain user) {
		this.user = user;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EstablishmentsDomain getEstablisment() {
		return establisment;
	}

	public void setEstablisment(EstablishmentsDomain establisment) {
		this.establisment = establisment;
	}

	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
