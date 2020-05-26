package com.bunny.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bunny.model.dto.UserRegistrationDTO;
import com.bunny.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user")
@Api(value = "User Entity Model")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@ApiModelProperty(notes = "first name of the User")
	@Column(name = "first_name")
	private String firstName;

	@ApiModelProperty(notes = "last name of the User")
	@Column(name = "last_name")
	private String lastName;

	@ApiModelProperty(notes = "mobile number of the User")
	@Column(name = "mobile_number")
	private Long mobileNumber;

	@ApiModelProperty(notes = "email id of the User")
	@Column(name = "email")
	private String email;

	@ApiModelProperty(notes = "password of the User")
	@Column(name = "password")
	private String password;

	@ApiModelProperty(notes = "user Creation Date")
	@Column(name = "create_date")
	private LocalDateTime createdDateTime;

	@ApiModelProperty(notes = "user Creation Date")
	@Column(name = "update_date")
	private LocalDateTime updateDateTime;

	public User() {
	}

	public User(UserRegistrationDTO request) {
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.email = request.getEmail();
		this.mobileNumber = request.getMobileNumber();
		this.password = request.getPassword();
		this.createdDateTime = DateUtil.today();
		this.updateDateTime = DateUtil.today();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
