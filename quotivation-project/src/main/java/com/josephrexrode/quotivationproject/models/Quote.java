package com.josephrexrode.quotivationproject.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "quotes")
public class Quote{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Quote must not be blank")
	private String text;
	
	@NotBlank(message = "Author must not be blank")
	private String author;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean dailyQuote = false;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_quotes",
			joinColumns = @JoinColumn(name = "quotes_id"),
			inverseJoinColumns = @JoinColumn(name = "users_id")
			)
	private List<User> users;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", updatable = false)
	private User creator;
	
	public Quote() {}

	public Quote(@NotBlank String text, @NotBlank String author) {
		this.text = text;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getDailyQuote() {
		return dailyQuote;
	}

	public void setDailyQuote(Boolean dailyQuote) {
		this.dailyQuote = dailyQuote;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
