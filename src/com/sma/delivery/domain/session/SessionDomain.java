package com.sma.delivery.domain.session;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sma.delivery.domain.base.BaseDomain;

@Entity
@Table(name = "session")
public class SessionDomain implements BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer _id;

	@Column(name = "userId")
	private Integer _userId;

	@Column(name = "active")
	private boolean _active;

	@Column(name = "secure")
	private boolean _secure;

	@Column(name = "createTime")
	private Date _createTime;

	@Column(name = "lastAccessedTime")
	private Date _lastAccessedTime;

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
	}

	public Integer getUserId() {
		return _userId;
	}

	public void setUserId(Integer userId) {
		_userId = userId;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public boolean isSecure() {
		return _secure;
	}

	public void setSecure(boolean secure) {
		_secure = secure;
	}

	public Date getCreateTime() {
		return _createTime;
	}

	public void setCreateTime(Date createTime) {
		_createTime = createTime;
	}

	public Date getLastAccessedTime() {
		return _lastAccessedTime;
	}

	public void setLastAccessedTime(Date lastAccessedTime) {
		_lastAccessedTime = lastAccessedTime;
	}

}
