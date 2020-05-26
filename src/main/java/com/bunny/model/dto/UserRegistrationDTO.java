package com.bunny.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserRegistrationDTO {
	@ApiModelProperty(notes = "first name of the User")
	private String firstName;
	@ApiModelProperty(notes = "last name of the User")
	private String lastName;
	private Long mobileNumber;
	private String email;
	private String password;

	public UserRegistrationDTO() {
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

}
