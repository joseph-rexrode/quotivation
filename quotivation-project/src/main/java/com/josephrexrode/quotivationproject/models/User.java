package com.josephrexrode.quotivationproject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Username is required!")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters!")
	@Pattern(regexp="^[A-Za-z]*$", message = "Username must only include letters!")
	private String username;
	
	@NotBlank(message = "Email is required!")
	@Email(message = "Must be a valid email!")
	private String email;
	
	@NotBlank(message = "Password is required!")
	@Size(min = 12, max = 256, message = "Password does not meet length requirements.")
	private String password;
	
	@Transient
	@NotBlank(message = "Confirm password is required!")
	@Size(min = 12, max = 256, message = "Confirm password does not meet length requirements.")
	private String confirm;
	
	@ManyToMany(mappedBy="users", fetch = FetchType.LAZY)
	private List<Quote> quotes;
	
	@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Quote> createdQuotes;
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public List<Quote> getCreatedQuotes() {
		return createdQuotes;
	}

	public void setCreatedQuotes(List<Quote> createdQuotes) {
		this.createdQuotes = createdQuotes;
	}
}
