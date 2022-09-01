package com.josephrexrode.quotivationproject.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUser {

	@NotBlank(message = "Email is required!")
	@Email(message = "Must be a valid email!")
	private String email;
	
	@NotBlank(message = "Password is required!")
	@Size(min = 12, max = 256, message = "Password does not meet length requirements.")
	private String password;
	
	public LoginUser() {}

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
