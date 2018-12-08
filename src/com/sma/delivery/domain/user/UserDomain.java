package com.sma.delivery.domain.user;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sma.delivery.domain.base.BaseDomain;
import com.sma.delivery.domain.comments.CommentsDomain;

@Entity
@Table(name = "user")
public class UserDomain implements BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@OneToMany(mappedBy = "_user")
	private Set<CommentsDomain> _comments = new HashSet<>();


	@Column(name = "email")
	private String email;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phone_number;

	@Column(name = "creation_date")
	private Timestamp creation_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Set<CommentsDomain> getComments() {
		return _comments;
	}

	public void setComments(Set<CommentsDomain> comments) {
		this._comments = comments;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password, Integer userId) {
		if(userId==null){
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			this.password = hashedPassword;
		}else{
			this.password = password;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public Timestamp getCreationDate() {
		return creation_date;
	}

	public void setCreationDate(Timestamp timestamp) {
		this.creation_date = timestamp;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
