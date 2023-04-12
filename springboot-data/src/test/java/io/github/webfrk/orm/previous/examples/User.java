package io.github.webfrk.orm.previous.examples;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_tb")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false, insertable =  
			true, updatable = true)
	private Long id;

	@Column(name = "user_name")
	@Size(max = 20, min = 3, message = "{user.name.invalid}")
	@NotEmpty(message = "Please Enter your name")
	private String name;

	@Column(name = "user_email", unique = true)
	@Email(message = "{user.email.invalid}")
	@NotEmpty(message = "Please Enter your email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}